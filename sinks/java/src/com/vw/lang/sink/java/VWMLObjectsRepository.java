package com.vw.lang.sink.java;

import java.util.HashMap;
import java.util.Map;

import com.vw.lang.sink.java.VWMLContextsRepository.ContextIdPair;
import com.vw.lang.sink.java.VWMLObjectBuilder.VWMLObjectType;
import com.vw.lang.sink.java.entity.VWMLComplexEntity;
import com.vw.lang.sink.java.entity.VWMLEntity;
import com.vw.lang.sink.java.entity.VWMLTerm;
import com.vw.lang.sink.java.interpreter.datastructure.VWMLContext;
import com.vw.lang.sink.java.interpreter.datastructure.resource.manager.VWMLResourceHostManagerFactory;
import com.vw.lang.sink.java.link.AbstractVWMLLinkVisitor;
import com.vw.lang.sink.java.link.VWMLLinkIncrementalIterator;
import com.vw.lang.sink.java.repository.VWMLRepository;


/**
 * Acts as repository for VWMLObjects
 * @author ogibayev
 *
 */
public class VWMLObjectsRepository extends VWMLRepository {
	
	public static boolean notAsOriginal = false;
	public static boolean asOriginal = true;
	
	// builds association between object's id and its instance
	private Map<Object, VWMLObject> repo = null;
	private Map<VWMLObject, VWMLObject> translatedObjects  = new HashMap<VWMLObject, VWMLObject>();
	// sets to 'true' during model's linkage phase
	private boolean isUnderConstruction = false;
	
	public static VWMLObjectsRepository instance() {
		return VWMLResourceHostManagerFactory.hostManagerInstance().requestObjectsRepo();
	}

	/**
	 * Acquires object identified by type and id and puts it to repository in case if it isn't found there
	 * @param type
	 * @param id
	 * @param context
	 * @param entityHistorySize
	 * @param asOriginalOnCreation
	 * @param visitor
	 * @return
	 */
	public static VWMLObject acquire(VWMLObjectBuilder.VWMLObjectType type, Object id, String context, Integer entityHistorySize, boolean asOriginalOnCreation, AbstractVWMLLinkVisitor visitor) throws Exception {
		VWMLContext c = VWMLContextsRepository.instance().get(context);
		if (c == null) {
			throw new Exception("coudln't find context '" + context + "'");
		}
		// checks if object has been created before
		VWMLObject obj = instance().checkObjectOnContext(id, c);
		if (obj == null) { // not found in repository...
			boolean contextChanged = false;
			if (isStaticallyAddressed((String)id)) {
				c = instance().getEffectiveContextFromEntityId(id, context);
				if (c == null) {
					throw new Exception("couldn't find context for entity '" + id + "'");
				}
				contextChanged = true;
			}
			obj = VWMLObjectBuilder.build(type, c.getContext(), id, c, entityHistorySize, visitor);
			((VWMLEntity)obj).setOriginal(asOriginalOnCreation);
			if (!contextChanged) {
				instance().add((VWMLEntity)obj);
			}
			else {
				instance().addByEntityKey((VWMLEntity)obj, c);
			}
		}
		return obj;
	}

	/**
	 * Acquires object identified by type and id and puts it to repository
	 * @param type
	 * @param id
	 * @param context
	 * @param entityHistorySize
	 * @param asOriginalOnCreation
	 * @param visitor
	 * @return
	 */
	public static VWMLObject acquireWithoutCheckingOnExistence(VWMLObjectBuilder.VWMLObjectType type, Object id, VWMLContext context, Integer entityHistorySize, boolean asOriginalOnCreation, AbstractVWMLLinkVisitor visitor) throws Exception {
		VWMLObject obj = VWMLObjectBuilder.build(type, context.getContext(), id, context, entityHistorySize, visitor);
		((VWMLEntity)obj).setOriginal(asOriginalOnCreation);
		instance().addByEntityKey((VWMLEntity)obj, context);
		return obj;
	}	
	
	/**
	 * Finds object on specified context (the context is represented by context name)
	 * @param contextName
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public static VWMLObject findObject(String contextName, Object id) throws Exception {
		VWMLContext c = VWMLContextsRepository.instance().get(contextName);
		if (c == null) {
			throw new Exception("coudln't find context '" + contextName + "' for object '" + id + "'");
		}
		// checks if object has been created before
		VWMLObject obj = instance().checkObjectOnContext(id, c);
		return obj;
	}

	/**
	 * Lookups for entity on context pair, supposing that context may be cloned
	 * 1. lookup on original context
	 * 2. in case if entity found on original context it must be created on effective (meaning that effective is cloned_
	 * 3. in case if entity not found on original context - exception is thrown
	 * @param cPair
	 * @param prototype
	 * @return
	 * @throws Exception
	 */
	public static VWMLObject getAndCreateInCaseOfClone(ContextIdPair cPair, VWMLEntity prototype) throws Exception {
		return getAndCreateInCaseOfClone(cPair, prototype, false, false);
	}

	/**
	 * Lookups for entity on context pair, supposing that context may be cloned
	 * 1. lookup on original context
	 * 2. in case if entity found on original context it must be created on effective (meaning that effective is cloned_
	 * 3. in case if entity not found on original context - exception is thrown
	 * @param cPair
	 * @param prototype
	 * @return
	 * @throws Exception
	 */
	public static VWMLObject getAndCreateInCaseOfCloneOnStackInspector(ContextIdPair cPair, VWMLEntity prototype) throws Exception {
		return getAndCreateInCaseOfClone(cPair, prototype, false, true);
	}
	
	/**
	 * See 'getAndCreateInCaseOfClone' below
	 * @param contextId
	 * @param prototypeId
	 * @return
	 * @throws Exception
	 */
	public static VWMLObject getAndCreateInCaseOfClone(String contextId, String prototypeId) throws Exception {
		VWMLEntity prototype = (VWMLEntity)VWMLObjectBuilder.build(VWMLObjectType.SIMPLE_ENTITY, prototypeId, prototypeId, null, 0, null);
		ContextIdPair cPair = VWMLContextsRepository.instance().wellFormedContext(contextId);
		if (cPair == null) {
			return null;
		}
		prototype.setReadableId(prototypeId);
		return getAndCreateInCaseOfClone(cPair, prototype, true, false);
	}

	/**
	 * See 'getAndCreateInCaseOfClone' below
	 * @param contextId
	 * @param prototypeId
	 * @return
	 * @throws Exception
	 */
	public static VWMLObject getAndCreateInCaseOfClone(String contextId, VWMLEntity prototype, boolean lookupByReadableId) throws Exception {
		ContextIdPair cPair = VWMLContextsRepository.instance().wellFormedContext(contextId);
		if (cPair == null) {
			return null;
		}
		return getAndCreateInCaseOfClone(cPair, prototype, true, false);
	}	
	
	/**
	 * Lookups for entity on context pair, supposing that context may be cloned
	 * 1. lookup on original context
	 * 2. in case if entity found on original context it must be created on effective (meaning that effective is cloned_
	 * 3. in case if entity not found on original context - exception is thrown
	 * @param cPair
	 * @param prototype
	 * @return
	 * @throws Exception
	 */
	public static VWMLObject getAndCreateInCaseOfClone(ContextIdPair cPair, VWMLEntity prototype, boolean lookupByReadableId, boolean lookupOnStackInspector) throws Exception {
		VWMLEntity lookedEntity = null;
		// 1. checks for special kind of entities
		// 2. checks whether context is child of cPair or no
		if (!prototype.isDynamicAddressedInRunTime() &&
			(prototype.isSynthetic() || prototype.getAsArgPair() != null)) {
			return prototype;
		}
		String id = null;
		if (lookupByReadableId || prototype.isLookedByReadableId()) {
			id = prototype.buildReadableId();
		}
		else {
			id = (String)prototype.getId();
		}
		// get context by effective (interpreter) id
		VWMLContext ctxEffective = VWMLContextsRepository.instance().get(cPair.getEffectiveContextId());
		if (!cPair.isCloneOfOriginal()) {
			if (ctxEffective == null) {
				throw new Exception("unknown context '" + cPair.getEffectiveContextId() + "'");
			}
			// if not cloned - do as usual
			lookedEntity = (VWMLEntity)VWMLObjectsRepository.instance().checkObjectOnContext(id, ctxEffective);
			if (lookedEntity == null && !prototype.isDynamicAddressedInRunTime()) {
				lookedEntity = (VWMLEntity)VWMLObjectsRepository.instance().get(id, ctxEffective);
			}
		}
		else {
			// otherwise creating context
			if (ctxEffective != null) {
				// if entity wasn't found on cloned context
				lookedEntity = (VWMLEntity)VWMLObjectsRepository.instance().checkObjectOnContext(id, ctxEffective);
			}
			if (lookedEntity == null) {
				VWMLContext ctxOnModel = null;
				// get context pair of prototype's context
				ContextIdPair prototypeCPair = VWMLContextsRepository.instance().wellFormedContext(prototype.getContext().getContext());
				// whether prototype belongs to 'current operational' context or no - searching for model's context
				String cp = VWMLContext.getCommonContextPath(cPair.getOrigContextId(), prototypeCPair.getOrigContextId());
				if (cp == null) {
					ctxOnModel = VWMLContextsRepository.instance().get(prototypeCPair.getOrigContextId());
				}
				else {
					ctxOnModel = VWMLContextsRepository.instance().get(cPair.getOrigContextId());
				}
				if (ctxOnModel == null) {
					throw new Exception("unknown context '" + cPair.getOrigContextId() + "'");
				}
				VWMLEntity onModelEntity = (VWMLEntity)VWMLObjectsRepository.instance().get(id, ctxOnModel);
				if (onModelEntity == null) {
					onModelEntity = (VWMLEntity)VWMLObjectsRepository.instance().checkObjectOnContext(id, ctxOnModel);
				}
				if (onModelEntity == null) {
					// looks like entity has already been created during implicit assembling operation
					lookedEntity = onModelEntity;
					return lookedEntity;
				}
				cp = VWMLContext.getCommonContextPath(cPair.getOrigContextId(), onModelEntity.getContext().getContext());
				if (cp == null) {
					// means that model entity is out-of-cloned-scope
					lookedEntity = onModelEntity;
					return lookedEntity;
				}
				// sub-context which is considered as cloneable
				String sc = VWMLContext.getSubContextAsString(cPair.getEffectiveContextId(), VWMLJavaExportUtils.parseContext(cp).length);
				ctxEffective = VWMLContextsRepository.instance().createContextIfNotExists(sc);
				// re-check, maybe it has already been created before, so no need to check it on 'acquire' operation
				lookedEntity = (VWMLEntity)VWMLObjectsRepository.instance().checkObjectOnContext(id, ctxEffective);
				if (lookedEntity != null) {
					return lookedEntity;
				}
				cPair = VWMLContextsRepository.instance().wellFormedContext(ctxEffective.getContext());
				prototype = onModelEntity;
				// found on original - creating in effective (cloned)
				lookedEntity = 	(VWMLEntity)VWMLObjectsRepository.acquire(prototype.deduceEntityType(),
											prototype.getId(),
											ctxEffective.getContext(),
											prototype.getInterpretationHistorySize(),
											VWMLObjectsRepository.notAsOriginal,
											prototype.getLink().getLinkOperationVisitor());
				if (prototype.isTerm()) {
					VWMLEntity eA = ((VWMLTerm)prototype).getAssociatedEntity();
					eA = (VWMLEntity) getAndCreateInCaseOfClone(cPair, eA);
					((VWMLTerm)lookedEntity).setAssociatedEntity(eA);
					((VWMLTerm)lookedEntity).copyOperations((VWMLTerm)prototype);
				}
				if (prototype.getOriginalInterpreting() != null) {
					// get readableId
					String readableId = prototype.buildReadableId();
					// special case when context is entity
					if (readableId != null && readableId.equals(VWMLEntity.s_EmptyEntityId)) {
						readableId = (String)prototype.getId();
					}
					// suppose that prototype.id identifies some context
					String ci = VWMLContext.constructContextNameFromParts(cPair.getOrigContextId(), readableId);
					// check it by creating pair
					ContextIdPair cPairI = VWMLContextsRepository.instance().wellFormedContext(ci);
					if (cPairI != null) {
						ci = VWMLContext.constructContextNameFromParts(cPair.getEffectiveContextId(), readableId);
						// ... looks like context, on model, exists - so we have to clone new one 
						ctxEffective = VWMLContextsRepository.instance().createContextIfNotExists(ci);
						// ... and update pair
						cPair = VWMLContextsRepository.instance().wellFormedContext(ctxEffective.getContext());
					}
					VWMLEntity e = (VWMLEntity) getAndCreateInCaseOfClone(cPair, prototype.getOriginalInterpreting());
					lookedEntity.setInterpreting(e);
				}
				lookedEntity.setAsArgPair(prototype.getAsArgPair());
				lookedEntity.setSynthetic(prototype.isSynthetic());
				lookedEntity.setClonedFrom(prototype);
				VWMLLinkIncrementalIterator it = prototype.getLink().acquireLinkedObjectsIterator();
				if (it != null) {
					for(; it.isCorrect(); it.next()) {
						VWMLEntity e = (VWMLEntity)prototype.getLink().getConcreteLinkedEntity(it.getIt());
						lookedEntity.getLink().link(e);
					}
				}
				lookedEntity.buildReadableId();
			}
		}
		return lookedEntity;
	}
	
	/**
	 * Returns 'true' in case if entity should be swallowed. Usually checks on 'Exe' operation, when it is finished
	 * @param e
	 * @return
	 */
	public static boolean shouldEntityBeConsumed(VWMLEntity e) throws Exception {
		boolean r = false;
		if (e != null) {
			if (e.isMarkedAsComplexEntity() && ((VWMLComplexEntity)e).getLink().getLinkedObjectsOnThisTime() == 0) {
				r = true;
			}
			else
			if (!e.isMarkedAsComplexEntity()) {
				String[] donothing = {VWMLEntity.s_doNothingEntityId, VWMLEntity.s_doNothingEntityId2, VWMLEntity.s_doNothingEntityId3};
				for(String s : donothing) {
					VWMLEntity entity = (VWMLEntity)instance().get(s, VWMLContextsRepository.instance().getDefaultContext());
					if (entity.equals(e)) {
						r = true;
						break;
					}
				}
			}
		}
		return r;
	}

	/**
	 * Returns 'true' in case entity's id is statically addressed
	 * @param id
	 * @return
	 */
	public static boolean isStaticallyAddressed(String id) {
		int bracket = id.indexOf('(');
		int dot = id.indexOf('.');
		return ((dot != -1 && bracket != -1 && dot < bracket) || (dot != -1 && bracket == -1));
	}
	
	public void init() {
		repo = VWMLResourceHostManagerFactory.hostManagerInstance().requestObjectsRepoContainer();
		VWMLContext defaultContext = VWMLContextsRepository.instance().getDefaultContext();
		VWMLEntity e = null;
		// built-in complex entity id
		e = (VWMLEntity)VWMLObjectBuilder.build(VWMLObjectType.COMPLEX_ENTITY, VWMLEntity.s_EmptyEntityId, VWMLEntity.s_EmptyEntityId, defaultContext, 0, null);
		e.setOriginal(true);
		add(e);
		// built-in simple entity id
		e = (VWMLEntity)VWMLObjectBuilder.build(VWMLObjectType.SIMPLE_ENTITY, VWMLEntity.s_NilEntityId, VWMLEntity.s_NilEntityId, defaultContext, 0, null);
		e.setOriginal(true);
		add(e);
		// built-in simple entity id
		e = (VWMLEntity)VWMLObjectBuilder.build(VWMLObjectType.SIMPLE_ENTITY, VWMLEntity.s_NullEntityId, VWMLEntity.s_NullEntityId, defaultContext, 0, null);
		e.setOriginal(true);
		add(e);
		// when interpreter encounters such entity - then implicit operation 'doNothing' is activated
		e = (VWMLEntity)VWMLObjectBuilder.build(VWMLObjectType.SIMPLE_ENTITY, VWMLEntity.s_doNothingEntityId, VWMLEntity.s_doNothingEntityId, defaultContext, 0, null);
		e.setOriginal(true);
		add(e);
		// when interpreter encounters such entity - then implicit operation 'doNothing' is activated
		e = (VWMLEntity)VWMLObjectBuilder.build(VWMLObjectType.SIMPLE_ENTITY, VWMLEntity.s_doNothingEntityId2, VWMLEntity.s_doNothingEntityId2, defaultContext, 0, null);
		e.setOriginal(true);
		add(e);
		// when interpreter encounters such entity - then implicit operation 'doNothing' is activated
		e = (VWMLEntity)VWMLObjectBuilder.build(VWMLObjectType.SIMPLE_ENTITY, VWMLEntity.s_doNothingEntityId3, VWMLEntity.s_doNothingEntityId3, defaultContext, 0, null);
		e.setOriginal(true);
		add(e);
		// built-in logical 'false' entity id
		e = (VWMLEntity)VWMLObjectBuilder.build(VWMLObjectType.SIMPLE_ENTITY, VWMLEntity.s_falseEntityId, VWMLEntity.s_falseEntityId, defaultContext, 0, null);
		e.setOriginal(true);
		add(e);
		// built-in logical 'true' entity id
		e = (VWMLEntity)VWMLObjectBuilder.build(VWMLObjectType.SIMPLE_ENTITY, VWMLEntity.s_trueEntityId, VWMLEntity.s_trueEntityId, defaultContext, 0, null);
		e.setOriginal(true);
		add(e);
	}
	
	public void add(VWMLEntity obj) {
		if (obj.getContext() == null) {
			return; // temporary entity
		}
		String key = buildAssociatingKeyOnContext(obj);
		if (!repo.containsKey(key)) {
			repo.put(key, obj);
			// associates acquired entity with context
			obj.getContext().associateEntity((VWMLEntity)obj);
		}
	}
	
	public void remove(VWMLEntity obj) {
		if (removeWithoutContextCleaning(obj)) {
			obj.getContext().unAssociateEntity(obj);
		}
	}

	public void removeAll() {
		for(VWMLObject o : repo.values()) {
			VWMLEntity e = (VWMLEntity)o;
			e.getLink().unlinkFromAll();
			e.getContext().unAssociateEntity(e);
		}
		repo.clear();
		markAsInvalid();
	}
	
	public boolean removeWithoutContextCleaning(VWMLEntity obj) {
		if (obj.getContext() == null) {
			return false; // temporary entity
		}
		String key = buildAssociatingKeyOnContext(obj);
		repo.remove(key);
		return true;
	}
	
	public VWMLObject get(Object id, VWMLContext context) throws Exception {
		return getByContext(id, context, false);
	}

	public VWMLObject checkObjectOnContext(Object id, VWMLContext context) throws Exception {
		return getByContext(id, context, true);
	}

	/**
	 * Searches entity on concrete context (no ascending lookup is performed) by readable id
	 * @param id
	 * @param context
	 * @return
	 */
	public VWMLObject findOnConcreteContextByReadableId(Object id, VWMLContext context) {
		for(VWMLEntity e : context.getAssociatedEntities()) {
			if (e.getReadableId() == null) {
				e.buildReadableId();
			}
			if (id.equals(e.getReadableId())) {
				return e;
			}
		}
		return null;
	}
	
	/**
	 * Adds created entity to storage
	 * @param entity
	 * @param context
	 * @return
	 */
	public VWMLEntity addConcrete(VWMLEntity entity, VWMLContext context) throws Exception {
		VWMLEntity existedEntity = (VWMLEntity)get(entity.getId(), context);
		if (existedEntity == null) {
			VWMLObjectsRepository.instance().add(entity);
			existedEntity = entity;
		}
		return existedEntity;
	}

	/**
	 * Returns pre-created default 'empty' entity
	 * @return
	 */
	public VWMLEntity getEmptyEntity() {
		try {
			return (VWMLEntity)get(VWMLEntity.s_EmptyEntityId, VWMLContextsRepository.instance().getDefaultContext());
		}
		catch(Exception e) {
			return null;
		}
	}

	/**
	 * Returns pre-created default 'nil' entity
	 * @return
	 */
	public VWMLEntity getNilEntity() {
		try {
			return (VWMLEntity)get(VWMLEntity.s_NilEntityId, VWMLContextsRepository.instance().getDefaultContext());
		}
		catch(Exception e) {
			return null;
		}
	}

	/**
	 * Returns pre-created default 'null' entity
	 * @return
	 */
	public VWMLEntity getNullEntity() {
		try {
			return (VWMLEntity)get(VWMLEntity.s_NullEntityId, VWMLContextsRepository.instance().getDefaultContext());
		}
		catch(Exception e) {
			return null;
		}
	}
	
	/**
	 * Returns pre-created default 'true' entity
	 * @return
	 */
	public VWMLEntity getTrueEntity() {
		try {
			return (VWMLEntity)get(VWMLEntity.s_trueEntityId, VWMLContextsRepository.instance().getDefaultContext());
		}
		catch(Exception e) {
			return null;
		}
	}

	/**
	 * Returns pre-created default 'false' entity
	 * @return
	 */
	public VWMLEntity getFalseEntity() {
		try {
			return (VWMLEntity)get(VWMLEntity.s_falseEntityId, VWMLContextsRepository.instance().getDefaultContext());
		}
		catch(Exception e) {
			return null;
		}
	}
	
	/**
	 * Adds created entity by context's key
	 * @param entity
	 * @param context
	 * @return
	 */
	public void addByEntityKey(VWMLEntity entity, VWMLContext context) throws Exception {
		String k = buildAssociationKey(context.getContext(), entity.getSimpleName());
		if (!repo.containsKey(k)) {
			repo.put(k, entity);
			// associates acquired entity with context
			context.associateEntity(entity);
		}
	}

	/**
	 * Adds translated objects; used fro translation between entity readable id and real entity
	 * @param translationKey
	 * @param obj
	 */
	public void addTranslatedObject(VWMLObject from, VWMLObject to) {
		translatedObjects.put(from, to);
	}
	
	public VWMLObject getTranslatedObject(Object from) {
		return translatedObjects.get(from);
	}

	public Map<VWMLObject, VWMLObject> resolve() {
		Map<VWMLObject, VWMLObject> nonResolved = new HashMap<VWMLObject, VWMLObject>();
		for(VWMLObject from : translatedObjects.keySet()) {
			VWMLObject to = translatedObjects.get(from);
			if (((VWMLEntity)from).deduceEntityType() == ((VWMLEntity)to).deduceEntityType()) {
				if (from.equals(to)) {
					continue;
				}
				if (((VWMLEntity)from).isTerm()) {
					if (((VWMLTerm)from).getAssociatedEntity().equals(((VWMLTerm)to).getAssociatedEntity())) {
						continue;
					}
					else {
						// non - resolvable
						nonResolved.put(from, to);
					}
				}
				else {
					VWMLLinkIncrementalIterator it = ((VWMLEntity)from).getLink().acquireLinkedObjectsIterator();
					if (it != null) {
						for(; it.isCorrect(); it.next()) {
							VWMLObject o = ((VWMLEntity)from).getLink().getConcreteLinkedEntity(it.getIt());
							to.getLink().link(o);
						}
						from.getLink().clear();
						from.getLink().setParent(null);
					}
				}
			}
			else {
				// non - resolvable
				nonResolved.put(from, to);
			}
		}
		translatedObjects.clear();
		return nonResolved;
	}
	
	public boolean isUnderConstruction() {
		return isUnderConstruction;
	}

	public void setUnderConstruction(boolean isUnderConstruction) {
		this.isUnderConstruction = isUnderConstruction;
	}

	protected VWMLContext findInheritedContext(String contextId, VWMLContext parent) {
		VWMLContext context = null;
		if (parent.getContextPath() != null) {
			parent.setContextPath(VWMLJavaExportUtils.parseContext(parent.getContext()));
		}
		String[] contextIdAsPath = VWMLJavaExportUtils.parseContext(contextId);
		String[] p = parent.getContextPath();
		VWMLContext root = VWMLContextsRepository.instance().getRootContext();
		if (contextIdAsPath.length > 0) {
			// find new root
			root = VWMLContextsRepository.instance().getByParsedPath(root, p, 0, contextIdAsPath[0]);
			if (root != null) {
				context = VWMLContextsRepository.instance().getByParsedPath(root, contextIdAsPath, 1, null);
			}
		}
		else {
			context = VWMLContextsRepository.instance().getByParsedPath(root, p, 0, contextId);
		}
		return context;
	}

	protected VWMLObject getByFullSpecifiedPath(Object id, VWMLContext context) throws Exception { 
		String contextId = null;
		String ids = (String)id;
		int le = ids.lastIndexOf(".");
		if (le == -1) {
			throw new Exception("invalid full specified path '" + id + "'");
		}
		else {
			contextId = ids.substring(0, le);
		}
		String lookupObject = ids.substring(le + 1);
		VWMLContext effectiveContext = findInheritedContext(contextId, context);
		if (effectiveContext == null) {
			throw new Exception("couldn't find context identified by '" + id + "'");
		}
		VWMLObject obj = repo.get(buildAssociationKey(effectiveContext.getContext(), lookupObject));
		return obj;
	}
	
	protected VWMLContext getEffectiveContextFromEntityId(Object id, String parentContext) {
		String ids = (String)id;
		int le = ids.lastIndexOf(".");
		String contextId = ids.substring(0, le);
		VWMLContext context = VWMLContextsRepository.instance().get(contextId);
		if (context == null) {
			VWMLContext parent = VWMLContextsRepository.instance().get(parentContext);
			context = findInheritedContext(contextId, parent);
		}
		return context;
	}

	protected VWMLObject getByContext(Object id, VWMLContext context, boolean concreteContext) throws Exception {
		VWMLContext effectiveContext = context;
		if (isStaticallyAddressed((String)id)) {
			return getByFullSpecifiedPath(id, context);
		}
		if (effectiveContext == null) {
			effectiveContext = VWMLContextsRepository.instance().getDefaultContext();
		}
		return getByEffectiveContext(id, effectiveContext, concreteContext);
	}
		
	protected VWMLObject getByEffectiveContext(Object id, VWMLContext context, boolean concreteContext) {
		VWMLObject o = null;
		if (concreteContext) {
			o = repo.get(buildAssociationKey(context.getContext(), (String)id));
		}
		else {
			while(context != null && o == null) {
				o = repo.get(buildAssociationKey(context.getContext(), (String)id));
				context = (VWMLContext)context.getLink().getParent();
			}
		}
		return o;
	}
	
	protected String buildAssociationKey(String ctx, String id) {
		return ctx + "." + id;
	}
	
	protected String buildAssociatingKeyOnContext(VWMLEntity obj) {
		return buildAssociationKey(obj.getContext().getContext(), (String)obj.getId());
	}
	
	protected void markAsInvalid() {
		VWMLResourceHostManagerFactory.hostManagerInstance().markObjectsRepoAsInvalid();
	}
}
