package com.vw.lang.sink.java;

import java.util.HashMap;
import java.util.Map;

import com.vw.lang.sink.java.VWMLObjectBuilder.VWMLObjectType;
import com.vw.lang.sink.java.entity.VWMLEntity;
import com.vw.lang.sink.java.link.IVWMLLinkVisitor;


/**
 * Acts as repository for VWMLObjects
 * @author ogibayev
 *
 */
public class VWMLObjectsRepository {

	// defines static types of unchanged entities
	private VWMLObjectsRepository() {
		add(VWMLObjectBuilder.build(VWMLObjectType.SIMPLE_ENTITY, VWMLEntity.s_EmptyEntityId, "", 0, null));
		add(VWMLObjectBuilder.build(VWMLObjectType.SIMPLE_ENTITY, VWMLEntity.s_NilEntityId, "", 0, null));
	}

	// builds association between object's id and its instance
	private Map<Object, VWMLObject> repo = new HashMap<Object, VWMLObject>();
	
	private static volatile boolean s_initialized = false;
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
	public static VWMLObject acquire(VWMLObjectBuilder.VWMLObjectType type, Object id, String context, Integer entityHistorySize, IVWMLLinkVisitor visitor) {
		// checks if object has been created before
		VWMLObject obj = instance().get(id, context);
		if (obj == null) { // not found in repository...
			obj = VWMLObjectBuilder.build(type, id, context, entityHistorySize, visitor);
			instance().add(obj);
		}
		return obj;
	}
	
	public void add(VWMLObject obj) {
		String key = buildAssociatingKeyOnContext(obj);
		if (!repo.containsKey(key)) {
			repo.put(key, obj);
		}
	}
	
	public void remove(VWMLObject obj) {
		String key = buildAssociatingKeyOnContext(obj);
		repo.remove(key);
	}
	
	public VWMLObject get(Object id, String context) {
		String ids = (String)id;
		String effectiveContext = context;
		if (ids.contains(".")) {
			effectiveContext = null;
		}
		return repo.get(buildAssociationKey(effectiveContext, ids));
	}

	/**
	 * Adds created entity to storage
	 * @param entity
	 * @param context
	 * @return
	 */
	public VWMLEntity addConcrete(VWMLEntity entity, String context) {
		VWMLEntity existedEntity = (VWMLEntity)VWMLObjectsRepository.instance().get(entity.getId(), context);
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
		return (VWMLEntity)get(VWMLEntity.s_EmptyEntityId, "");
	}
	
	protected String buildAssociationKey(String prefix, String id) {
		return (prefix != null && prefix.length() > 0) ? (prefix + "." + id) : id;
	}
	
	protected String buildAssociatingKeyOnContext(VWMLObject obj) {
		return buildAssociationKey(obj.getOriginalContext(), (String)obj.getId());
	}
}
