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
	private Map<VWMLEntity, VWMLEntity> cached = new HashMap<VWMLEntity, VWMLEntity>();
	
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
		cached.put(orig, cloned);
	}
	
	/**
	 * Returns cloned entity by original entity
	 * @param e
	 * @return
	 */
	public VWMLEntity get(VWMLEntity e) {
		return cached.get(e);
	}
	
	/**
	 * Returns 'true' in case if object has already been placed to cache, otherwise 'false' is returned
	 * @param e
	 * @return
	 */
	public boolean check(VWMLEntity e) {
		return cached.containsKey(e);
	}
	
	/**
	 * Removes entity from the cache
	 * @param e
	 */
	public void remove(VWMLEntity e) {
		cached.remove(e);
	}
	
	/**
	 * Removes all entities from the cache; resets cache
	 */
	public void reset() {
		cached.clear();
	}
}
