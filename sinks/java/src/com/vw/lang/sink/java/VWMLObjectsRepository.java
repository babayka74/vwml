package com.vw.lang.sink.java;

import java.util.HashMap;
import java.util.Map;

import com.vw.lang.sink.java.VWMLContextsRepository.ContextIdPair;
import com.vw.lang.sink.java.VWMLObjectBuilder.VWMLObjectType;
import com.vw.lang.sink.java.entity.VWMLComplexEntity;
import com.vw.lang.sink.java.entity.VWMLEntity;
import com.vw.lang.sink.java.entity.VWMLTerm;
import com.vw.lang.sink.java.interpreter.datastructure.VWMLContext;
import com.vw.lang.sink.java.interpreter.datastructure.resource.manager.VWMLGarbageManager;
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
	
	private static String predefinedEntitiesId[] = {
		VWMLEntity.s_NilEntityId,
		VWMLEntity.s_NullEntityId,
		VWMLEntity.s_EmptyEntityId,
		VWMLEntity.s_doNothingEntityId,
		VWMLEntity.s_doNothingEntityId2,
		VWMLEntity.s_doNothingEntityId3,
		VWMLEntity.s_trueEntityId,
		VWMLEntity.s_falseEntityId
	};
	
	// builds association between object's id and its instance
	private Map<Object, VWMLObject> repo = null;
	private Map<VWMLObject, VWMLObject> translatedObjects  = new HashMap<VWMLObject, VWMLObject>();
	private VWMLGarbageManager garbageManager = null;
	// sets to 'true' during model's linkage phase
	private boolean isUnderConstruction = false;
	
	public static VWMLObjectsRepository instance() {
		return VWMLResourceHostManagerFactory.hostManagerInstance().requestObjectsRepo();
	}

	public static boolean isEntityPredefined(VWMLEntity e) {
		for(String id : predefinedEntitiesId) {
			if (e.getId().equals(id)) {
				return true;
			}
		}
		return false;
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
			obj.setHashId(id);
			((VWMLEntity)obj).setOriginal(asOriginalOnCreation);
			if (instance().isUnderConstruction()) {
				if (!contextChanged) {
					instance().add((VWMLEntity)obj);
				}
				else {
					instance().addByEntityKey((VWMLEntity)obj, c);
				}
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
			id = (String)prototype.getHashId();
		}
		else {
			id = (String)prototype.getId();
		}
		ContextIdPair prototypeCPair = VWMLContextsRepository.instance().wellFormedContext(prototype.getContext().getContext());
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
				if (!prototype.isDynamicAddressedInRunTime() && lookedEntity != null) {
					ContextIdPair lookedCtxIdPair = VWMLContextsRepository.instance().wellFormedContext(lookedEntity.getContext().getContext());
					if (cPair.getEffectiveContextId().equals(lookedCtxIdPair.getEffectiveContextId())) {
						lookedEntity = null;
					}
				}
			}
			if (lookedEntity == null) {
				VWMLEntity onModelEntity = null;
				VWMLContext ctxOnModel = null;
				if (!prototype.isDynamicAddressedInRunTime()) {
					// get context pair of prototype's context
					VWMLContext firstClonedCtx = ctxEffective.getFirstCloned();
					if (firstClonedCtx == null) {
						throw new Exception("not found first cloned on '" + ctxEffective.getContext() + "'");
					}
					ContextIdPair firstClonedCtxPair = VWMLContextsRepository.instance().wellFormedContext(firstClonedCtx.getContext());
					// whether prototype belongs to 'current operational' context or no - searching for model's context
					if (!VWMLContext.isContextChildOf(firstClonedCtxPair, prototypeCPair.getOrigContextId())) {
						ctxOnModel = VWMLContextsRepository.instance().get(prototypeCPair.getOrigContextId());
						onModelEntity = (VWMLEntity)VWMLObjectsRepository.instance().get(id, ctxOnModel);
						if (onModelEntity == null) {
							onModelEntity = (VWMLEntity)VWMLObjectsRepository.instance().checkObjectOnContext(id, ctxOnModel);
						}
						if (onModelEntity == null) {
							onModelEntity = prototype;
						}
						
						return onModelEntity;
					}
					else {
						VWMLContext[] lookupCtxs = new VWMLContext[2];
						// checking its on original (aka model)
						lookupCtxs[0] = VWMLContextsRepository.instance().get(prototypeCPair.getOrigContextId());
						lookupCtxs[1] = VWMLContextsRepository.instance().get(cPair.getOrigContextId());
						if (lookupCtxs[0].equals(lookupCtxs[1])) {
							lookupCtxs[1] = null;
						}
						for(VWMLContext c : lookupCtxs) {
							if (c == null) {
								break;
							}
							ctxOnModel = c;
							onModelEntity = (VWMLEntity)VWMLObjectsRepository.instance().get(id, ctxOnModel);
							if (onModelEntity == null) {
								onModelEntity = (VWMLEntity)VWMLObjectsRepository.instance().checkObjectOnContext(id, ctxOnModel);
							}
							if (onModelEntity != null) {
								if (VWMLContext.isContextChildOf(firstClonedCtxPair, onModelEntity.getContext().getContext())) {
									break;
								}
							}
						}
						if (onModelEntity == null) {
							lookedEntity = (VWMLEntity)VWMLObjectsRepository.instance().get(id, ctxEffective);
							if (lookedEntity != null) {
								prototype = lookedEntity;
							}
							return prototype;
						}
						String modelRelCtx = VWMLContext.getRelContextPath(firstClonedCtxPair.getOrigContextId(), onModelEntity.getContext().getContext());
						if (modelRelCtx == null) {
							return onModelEntity;
						}
						ctxEffective = VWMLContextsRepository.instance().createContextIfNotExists(VWMLContext.constructContextNameFromParts(firstClonedCtxPair.getEffectiveContextId(), modelRelCtx));
						// re-check, maybe it has already been created before, so no need to check it on 'acquire' operation
						lookedEntity = (VWMLEntity)VWMLObjectsRepository.instance().checkObjectOnContext(id, ctxEffective);
						if (lookedEntity != null) {
							return lookedEntity;
						}
						cPair = VWMLContextsRepository.instance().wellFormedContext(ctxEffective.getContext());
						prototype = onModelEntity;
					}
				}
				else {
					cPair = VWMLContextsRepository.instance().wellFormedContext(ctxEffective.getContext());
					ctxOnModel = VWMLContextsRepository.instance().get(cPair.getOrigContextId());
					onModelEntity = (VWMLEntity)VWMLObjectsRepository.instance().get(id, ctxOnModel);
					if (onModelEntity == null) {
						onModelEntity = (VWMLEntity)VWMLObjectsRepository.instance().checkObjectOnContext(id, ctxOnModel);
					}
					if (onModelEntity == null) {
						return prototype;
					}
					prototype = onModelEntity;
				}
				lookedEntity = createFromPrototypeAndLinkInCaseOfClone(cPair, prototype, ctxEffective);
			}
		}
		if (lookedEntity != null && lookedEntity.isMarkedAsPotentialInvalid()) {
			lookedEntity.setMarkedAsPotentialInvalid(false);
			cPair = VWMLContextsRepository.instance().wellFormedContext(lookedEntity.getContext().getContext());
			VWMLObjectsRepository.instance().remove(lookedEntity);
			lookedEntity = (VWMLEntity) getAndCreateInCaseOfClone(cPair, prototype);
		}
		return lookedEntity;
	}

	/**
	 * Clones, if needed, interpreted part of interpreting entity (A <interpreting> ias B <interpreted>)
	 * @param interpretingPair
	 * @param interpretingContext
	 * @param interpreting
	 * @throws Exception
	 */
	public static VWMLObject getAndCreateInterpretedInCaseOfClone(ContextIdPair interpretingPair, VWMLContext interpretingContext, VWMLEntity interpreting) throws Exception {
		VWMLEntity e = null;
		if (interpreting.getOriginalInterpreting() != null) {
			if (!interpreting.getOriginalInterpreting().isRecursiveInterpretationOnOriginal()) {
				// get readableId
				String readableId = null;
				if (interpreting.getReadableId() == null) {
					readableId = interpreting.buildReadableId();
				}
				else {
					readableId = interpreting.getReadableId();
				}
				// suppose that prototype.id identifies some context
				String ci = VWMLContext.constructContextNameFromParts(interpretingPair.getOrigContextId(), readableId);
				// check it by creating pair
				ContextIdPair cPairI = VWMLContextsRepository.instance().wellFormedContext(ci);
				if (cPairI != null) {
					ci = VWMLContext.constructContextNameFromParts(interpretingPair.getEffectiveContextId(), readableId);
					// ... looks like context, on model, exists - so we have to clone new one 
					interpretingContext = VWMLContextsRepository.instance().createContextIfNotExists(ci);
					// ... and update pair
					interpretingPair = VWMLContextsRepository.instance().wellFormedContext(interpretingContext.getContext());
				}
				e = (VWMLEntity) getAndCreateInCaseOfClone(interpretingPair, interpreting.getOriginalInterpreting());
			}
			else {
				e = (VWMLEntity)VWMLObjectsRepository.acquire(interpreting.getOriginalInterpreting().deduceEntityType(),
															interpreting.getOriginalInterpreting().getNativeId(),
															interpretingContext.getContext(),
															interpreting.getOriginalInterpreting().getInterpretationHistorySize(),
															VWMLObjectsRepository.notAsOriginal,
															interpreting.getOriginalInterpreting().getLink().getLinkOperationVisitor());
				instance().lateBinding(e, (String)interpreting.getOriginalInterpreting().getNativeId());
			}
		}
		return e;
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
		garbageManager = VWMLResourceHostManagerFactory.hostManagerInstance().requestGarbageManager();
		VWMLContext defaultContext = VWMLContextsRepository.instance().getDefaultContext();
		VWMLEntity e = null;
		// built-in complex entity id
		e = (VWMLEntity)VWMLObjectBuilder.build(VWMLObjectType.COMPLEX_ENTITY, VWMLEntity.s_EmptyEntityId, VWMLEntity.s_EmptyEntityId, defaultContext, 0, null);
		e.setOriginal(true);
		e.setNativeId(VWMLEntity.s_EmptyEntityNId);
		add(e);
		// built-in simple entity id
		e = (VWMLEntity)VWMLObjectBuilder.build(VWMLObjectType.SIMPLE_ENTITY, VWMLEntity.s_NilEntityId, VWMLEntity.s_NilEntityId, defaultContext, 0, null);
		e.setOriginal(true);
		e.setNativeId(VWMLEntity.s_NilEntityNId);
		add(e);
		// built-in simple entity id
		e = (VWMLEntity)VWMLObjectBuilder.build(VWMLObjectType.SIMPLE_ENTITY, VWMLEntity.s_NullEntityId, VWMLEntity.s_NullEntityId, defaultContext, 0, null);
		e.setOriginal(true);
		e.setNativeId(VWMLEntity.s_NullEntityNId);
		e.setReadableId((String)e.getNativeId());
		add(e);
		// when interpreter encounters such entity - then implicit operation 'doNothing' is activated
		e = (VWMLEntity)VWMLObjectBuilder.build(VWMLObjectType.SIMPLE_ENTITY, VWMLEntity.s_doNothingEntityId, VWMLEntity.s_doNothingEntityId, defaultContext, 0, null);
		e.setOriginal(true);
		e.setNativeId(VWMLEntity.s_doNothingEntityNId);
		e.setReadableId((String)e.getNativeId());
		add(e);
		// when interpreter encounters such entity - then implicit operation 'doNothing' is activated
		e = (VWMLEntity)VWMLObjectBuilder.build(VWMLObjectType.SIMPLE_ENTITY, VWMLEntity.s_doNothingEntityId2, VWMLEntity.s_doNothingEntityId2, defaultContext, 0, null);
		e.setOriginal(true);
		e.setNativeId(VWMLEntity.s_doNothingEntityNId2);
		e.setReadableId((String)e.getNativeId());
		add(e);
		// when interpreter encounters such entity - then implicit operation 'doNothing' is activated
		e = (VWMLEntity)VWMLObjectBuilder.build(VWMLObjectType.SIMPLE_ENTITY, VWMLEntity.s_doNothingEntityId3, VWMLEntity.s_doNothingEntityId3, defaultContext, 0, null);
		e.setOriginal(true);
		e.setNativeId(VWMLEntity.s_doNothingEntityNId3);
		e.setReadableId((String)e.getNativeId());
		add(e);
		// built-in logical 'false' entity id
		e = (VWMLEntity)VWMLObjectBuilder.build(VWMLObjectType.SIMPLE_ENTITY, VWMLEntity.s_falseEntityId, VWMLEntity.s_falseEntityId, defaultContext, 0, null);
		e.setOriginal(true);
		e.setNativeId(VWMLEntity.s_falseEntityNId);
		e.setReadableId((String)e.getNativeId());
		add(e);
		// built-in logical 'true' entity id
		e = (VWMLEntity)VWMLObjectBuilder.build(VWMLObjectType.SIMPLE_ENTITY, VWMLEntity.s_trueEntityId, VWMLEntity.s_trueEntityId, defaultContext, 0, null);
		e.setOriginal(true);
		e.setNativeId(VWMLEntity.s_trueEntityNId);
		e.setReadableId((String)e.getNativeId());
		add(e);
	}
	
	public VWMLGarbageManager getGarbageManager() {
		return garbageManager;
	}

	/**
	 * Called during construction phase in order to build hash ids which substitute primary id
	 * and can be constructed from readable id
	 */
	public void rebuildEntitiesPrimaryIds() {
		Map<Object, VWMLObject> newRepo = VWMLResourceHostManagerFactory.hostManagerInstance().requestObjectsRepoContainer();
		for(VWMLObject o : repo.values()) {
			VWMLEntity e = (VWMLEntity)o;
			if (isEntityPredefined(e)) {
				newRepo.put(e.getRegistrationKey(), e);
				continue;
			}
			if (e.isTerm()) {
				e.setNativeId(e.getId());
				newRepo.put(e.getRegistrationKey(), e);
				VWMLObject associated = rebuildEntityCredentials(newRepo, ((VWMLTerm)e).getAssociatedEntity());
				if (associated != ((VWMLTerm)e).getAssociatedEntity()) {
					((VWMLTerm)e).setAssociatedEntity((VWMLEntity)associated);
				}
				continue;
			}
			rebuildEntityCredentials(newRepo, e);
		}
		System.out.println("Code squeeze factor is '" + (float)repo.size() / (float)newRepo.size() + "'");
		repo.clear();
		repo = newRepo;
	}
	
	/**
	 * Implements late binding by recalculating entity's id
	 * @param e
	 * @param nativeId
	 */
	public Object lateBinding(VWMLEntity e, String nativeId) {
		e.getContext().unAssociateEntity(e);
		if (nativeId != null) {
			e.setNativeId(nativeId);
		}
		Object hashId = e.buildCompleteHashId();
		e.setHashId(hashId);
		e.setId(hashId);
		add(e);
		return hashId;
	}
	
	/**
	 * Adds created entity by context's key
	 * @param entity
	 * @param context
	 * @return
	 */
	public void addByEntityKey(VWMLEntity entity, VWMLContext context) throws Exception {
		String k = null;
		if (isUnderConstruction) {
			k = buildAssociationKey(context.getContext(), entity.getSimpleName());
		}
		else {
			k = buildAssociationKey(context.getContext(), (String)entity.getHashId());
		}
		if (!repo.containsKey(k)) {
			repo.put(k, entity);
			entity.setRegistrationKey(k);
			// associates acquired entity with context
			context.associateEntity(entity);
		}
	}

	
	public void add(VWMLEntity obj) {
		if (obj.getContext() == null) {
			return; // temporary entity
		}
		String key = null;
		if (isUnderConstruction) {
			key = buildAssociatingKeyOnContext(obj);
		}
		else {
			key = buildAssociationKey(obj.getContext().getContext(), (String)obj.getHashId());
		}
		if (!repo.containsKey(key)) {
			repo.put(key, obj);
			obj.setRegistrationKey(key);
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
		boolean b = false;
		if (obj.getContext() == null) {
			return false; // temporary entity
		}
		String[] keys = null;
		if (obj.getRegistrationKey() == null) {
			if (obj.isStaticAdressedInRunTime()) {
				keys = new String [] {	buildAssociationKey(obj.getContext().getContext(), obj.getSimpleName()),
								 		buildAssociatingReadableKeyOnContext(obj)
								 	 };
			}
			else {
				keys = new String [] {
						buildAssociatingKeyOnContext(obj),
						buildAssociatingReadableKeyOnContext(obj)
				};
			}
			for(String key : keys) {
				if (repo.containsKey(key)) {
					b = (repo.remove(key) != null);
					break;
				}
			}
		}
		else {
			b = (repo.remove(obj.getRegistrationKey()) != null);
		}
		return b;
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

	protected String buildAssociatingReadableKeyOnContext(VWMLEntity obj) {
		return buildAssociationKey(obj.getContext().getContext(), (String)obj.getReadableId());
	}
	
	protected void markAsInvalid() {
		VWMLResourceHostManagerFactory.hostManagerInstance().markObjectsRepoAsInvalid();
	}
	
	protected VWMLObject rebuildEntityCredentials(Map<Object, VWMLObject> newRepo, VWMLEntity e) {
		VWMLObject o = null;
		e.getContext().unAssociateEntity(e);
		Object hashId = null;
		if (e.isMarkedAsComplexEntity() && e.getLink().getLinkedObjectsOnThisTime() == 0) {
			hashId = VWMLEntity.buildHashIdFrom(e.getId());
		}
		else {
			hashId = e.buildCompleteHashId();
		}
		String nkey = buildAssociationKey(e.getContext().getContext(), (String)hashId);
		if (!newRepo.containsKey(nkey)) {
			e.setHashId(hashId);
			e.setNativeId(e.getId());
			e.setId(e.getHashId());
			newRepo.put(nkey, e);
			e.setRegistrationKey(nkey);
			e.getContext().associateEntity(e);
			o = e;
//			System.out.println("Entity '" + e.getNativeId() + "/" + e.getId() + "/" + nkey + "' registered on context '" + e.getContext().getContext() + "'");
		}
		else {
			o = newRepo.get(nkey);
//			System.out.println("Entity '" + e.getNativeId() + "/" + e.getId() + "/" + nkey + "' exists on context '" + e.getContext().getContext() + "'");
		}
		return o;
	}
	
	protected static VWMLEntity createFromPrototypeAndLinkInCaseOfClone(ContextIdPair cPair, VWMLEntity prototype, VWMLContext ctxEffective) throws Exception {
		// System.out.println("getAndCreateInCaseOfClone for '" + prototype.buildReadableId() + "' " + prototype.isTerm() + "'");
		// found on original - creating in effective (cloned)
		VWMLEntity lookedEntity = 	(VWMLEntity)VWMLObjectsRepository.acquire(prototype.deduceEntityType(),
									prototype.getId(),
									ctxEffective.getContext(),
									prototype.getInterpretationHistorySize(),
									VWMLObjectsRepository.notAsOriginal,
									prototype.getLink().getLinkOperationVisitor());
		if (prototype.isTerm()) {
			VWMLEntity eA = ((VWMLTerm)prototype).getAssociatedEntity();
			//System.out.println("eA '" + eA.buildReadableId() + "'");
			eA = (VWMLEntity) getAndCreateInCaseOfClone(cPair, eA);
			((VWMLTerm)lookedEntity).setAssociatedEntity(eA);
			((VWMLTerm)lookedEntity).copyOperations((VWMLTerm)prototype);
		}
		lookedEntity.setAsArgPair(prototype.getAsArgPair());
		lookedEntity.setSynthetic(prototype.isSynthetic());
		lookedEntity.setClonedFrom(prototype);
		VWMLLinkIncrementalIterator it = prototype.getLink().acquireLinkedObjectsIterator();
		if (it != null) {
			for(; it.isCorrect(); it.next()) {
				VWMLEntity e = (VWMLEntity)prototype.getLink().getConcreteLinkedEntity(it.getIt());
				VWMLEntity eC = (VWMLEntity) getAndCreateInCaseOfClone(cPair, e);
				lookedEntity.getLink().link(eC);
			}
		}
		instance().lateBinding(lookedEntity, (String)prototype.getNativeId());
		lookedEntity.buildReadableId();
		lookedEntity.setInterpreting((VWMLEntity)getAndCreateInterpretedInCaseOfClone(cPair, ctxEffective, prototype));
		return lookedEntity;
	}
}
