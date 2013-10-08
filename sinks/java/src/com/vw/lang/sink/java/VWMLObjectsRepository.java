package com.vw.lang.sink.java;

import java.util.HashMap;
import java.util.Map;

import com.vw.lang.sink.java.VWMLObjectBuilder.VWMLObjectType;
import com.vw.lang.sink.java.entity.VWMLEntity;
import com.vw.lang.sink.java.interpreter.datastructure.VWMLContext;
import com.vw.lang.sink.java.link.AbstractVWMLLinkVisitor;


/**
 * Acts as repository for VWMLObjects
 * @author ogibayev
 *
 */
public class VWMLObjectsRepository {

	// defines static types of unchanged entities
	private VWMLObjectsRepository() {
		VWMLContext defaultContext = VWMLContextsRepository.instance().getDefaultContext();
		// built-in complex entity id
		add((VWMLEntity)VWMLObjectBuilder.build(VWMLObjectType.SIMPLE_ENTITY, VWMLEntity.s_EmptyEntityId, defaultContext, 0, null));
		// built-in simple entity id
		add((VWMLEntity)VWMLObjectBuilder.build(VWMLObjectType.SIMPLE_ENTITY, VWMLEntity.s_NilEntityId, defaultContext, 0, null));
		// when interpreter encounters such entity - then implicit operation 'doNothing' is activated
		add((VWMLEntity)VWMLObjectBuilder.build(VWMLObjectType.SIMPLE_ENTITY, VWMLEntity.s_doNothingEntityId, defaultContext, 0, null));
		// built-in logical 'false' entity id
		add((VWMLEntity)VWMLObjectBuilder.build(VWMLObjectType.SIMPLE_ENTITY, VWMLEntity.s_falseEntityId, defaultContext, 0, null));
		// built-in logical 'true' entity id
		add((VWMLEntity)VWMLObjectBuilder.build(VWMLObjectType.SIMPLE_ENTITY, VWMLEntity.s_trueEntityId, defaultContext, 0, null));
	}

	// builds association between object's id and its instance
	private Map<Object, VWMLObject> repo = new HashMap<Object, VWMLObject>();
	
	private static final VWMLObjectsRepository s_repo = new VWMLObjectsRepository();
	
	public static VWMLObjectsRepository instance() {
		return s_repo;
	}
	
	/**
	 * Acquires object identified by type and id and puts it to repository in case if it isn't found there
	 * @param type
	 * @param id
	 * @param context
	 * @param entityHistorySize
	 * @param visitor
	 * @return
	 */
	public static VWMLObject acquire(VWMLObjectBuilder.VWMLObjectType type, Object id, String context, Integer entityHistorySize, AbstractVWMLLinkVisitor visitor) throws Exception {
		VWMLContext c = VWMLContextsRepository.instance().get(context);
		if (c == null) {
			throw new Exception("coudln't find context '" + context + "'");
		}
		// checks if object has been created before
		VWMLObject obj = instance().get(id, c);
		if (obj == null) { // not found in repository...
			obj = VWMLObjectBuilder.build(type, id, c, entityHistorySize, visitor);
			instance().add((VWMLEntity)obj);
		}
		return obj;
	}
	
	public void add(VWMLEntity obj) {
		String key = buildAssociatingKeyOnContext(obj);
		if (!repo.containsKey(key)) {
			repo.put(key, obj);
			// associates acquired entity with context
			obj.getContext().associateEntity((VWMLEntity)obj);
		}
	}
	
	public void remove(VWMLEntity obj) {
		String key = buildAssociatingKeyOnContext(obj);
		repo.remove(key);
		obj.getContext().unAssociateEntity(obj);
	}
	
	public VWMLObject get(Object id, VWMLContext context) throws Exception {
		String ids = (String)id;
		VWMLContext effectiveContext = context;
		if (ids.contains(".")) {
			return getByFullSpecifiedPath(id, context);
		}
		if (effectiveContext == null) {
			effectiveContext = VWMLContextsRepository.instance().getDefaultContext();
		}
		return getByEffectiveContext(id, effectiveContext, false);
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
	public VWMLEntity getEmptyEntity() throws Exception {
		return (VWMLEntity)get(VWMLEntity.s_EmptyEntityId, VWMLContextsRepository.instance().getDefaultContext());
	}
	
	protected VWMLObject getByFullSpecifiedPath(Object id, VWMLContext context) throws Exception { 
		String ids = (String)id;
		int le = ids.lastIndexOf(".");
		String contextId = ids.substring(0, le);
		VWMLContext effectiveContext = VWMLContextsRepository.instance().get(contextId);
		if (effectiveContext == null) {
			throw new Exception("couldn't find context identified by '" + contextId + "'");
		}
		ids = ids.substring(le + 1);
		return repo.get(buildAssociationKey(effectiveContext, ids));
	}

	protected VWMLObject getByEffectiveContext(Object id, VWMLContext context, boolean concreteContext) {
		VWMLObject o = null;
		if (concreteContext) {
			o = repo.get(buildAssociationKey(context, (String)id));
		}
		else {
			while(context != null && o == null) { 
				o = repo.get(buildAssociationKey(context, (String)id));
				context = (VWMLContext)context.getLink().getParent();
			}
		}
		return o;
	}
	
	protected String buildAssociationKey(VWMLContext ctx, String id) {
		return ctx.getContext() + "." + id;
	}
	
	protected String buildAssociatingKeyOnContext(VWMLEntity obj) {
		return buildAssociationKey(obj.getContext(), (String)obj.getId());
	}
}
