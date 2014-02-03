package com.vw.lang.sink.java;

import java.util.HashMap;
import java.util.Map;

import com.vw.lang.sink.java.entity.VWMLEntity;

/**
 * Contains cached entities which has already been cloned
 * @author Oleg
 *
 */
public class VWMLCloneAuxCache {
	private Map<String, VWMLEntity> cached = new HashMap<String, VWMLEntity>();
	
	private VWMLCloneAuxCache() {
		
	}
	
	public static VWMLCloneAuxCache instance() {
		return new VWMLCloneAuxCache();
	}
	
	/**
	 * Adds entity to cache
	 * @param orig
	 * @param cloned
	 */
	public void add(VWMLEntity orig, VWMLEntity cloned) {
		String key = buildAssociatingKey(orig);
		cached.put(key, cloned);
	}
	
	/**
	 * Returns cloned entity by original entity
	 * @param e
	 * @return
	 */
	public VWMLEntity get(VWMLEntity e) {
		String key = buildAssociatingKey(e);
		return cached.get(key);
	}
	
	/**
	 * Returns 'true' in case if object has already been placed to cache, otherwise 'false' is returned
	 * @param e
	 * @return
	 */
	public boolean check(VWMLEntity e) {
		String key = buildAssociatingKey(e);
		return cached.containsKey(key);
	}
	
	/**
	 * Removes entity from the cache
	 * @param e
	 */
	public void remove(VWMLEntity e) {
		String key = buildAssociatingKey(e);
		cached.remove(key);
	}
	
	/**
	 * Removes all entities from the cache; resets cache
	 */
	public void reset() {
		cached.clear();
	}
	
	private String buildAssociatingKey(VWMLEntity e) {
		return e.getContext().getContext() + "." + e.getSimpleName();
	}
}
