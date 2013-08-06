package com.vw.lang.sink.java;

import java.util.HashMap;
import java.util.Map;

import com.vw.lang.sink.java.link.IVWMLLinkVisitor;


/**
 * Acts as repository for VWMLObjects
 * @author ogibayev
 *
 */
public class VWMLObjectsRepository {
	
	
	private VWMLObjectsRepository() {
		
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
	 * @param visitor
	 * @return
	 */
	public static VWMLObject acquire(VWMLObjectBuilder.VWMLObjectType type, Object id, IVWMLLinkVisitor visitor) {
		// checks if object has been created before
		VWMLObject obj = instance().get(id);
		if (obj == null) { // not found in repository...
			obj = VWMLObjectBuilder.build(type, id, visitor);
			instance().add(obj);
		}
		return obj;
	}
	
	public void add(VWMLObject obj) {
		if (!repo.containsKey(obj.getId())) {
			repo.put(obj.getId(), obj);
		}
	}
	
	public void remove(VWMLObject obj) {
		repo.remove(obj.getId());
	}
	
	public VWMLObject get(Object id) {
		return repo.get(id);
	}
}
