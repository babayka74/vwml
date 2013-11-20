package com.vw.lang.sink.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.vw.lang.sink.java.entity.VWMLEntity;

/**
 * Helper class which allows to build complex entity name from set of objects' ids
 * @author ogibayev
 *
 */
public class ComplexEntityNameBuilder {
	
	protected static class Entity {
		private String id;

		public Entity() {
			super();
		}

		public Entity(String id) {
			super();
			this.id = id;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}
	}
	
	protected static class ComplexEntity extends Entity {
		private List<Entity> entities = new ArrayList<Entity>();
		
		public void addEntity(Entity entity) {
			entities.add(entity);
		}
		
		public List<Entity> getEntities() {
			return entities;
		}
		
		public String build() {
			String str = "";
			for(Entity e : entities) {
				str += e.getId() + " ";
			}
			return str.trim();
		}
		
		public void clear() {
			entities.clear();
		}
	}
	
	protected static class SimpleEntity extends Entity {
		public SimpleEntity(String id) {
			super(id);
		}
	}
	
	
	private ComplexEntity currentComplexEntity = null;
	private ComplexEntity rootComplexEntity = null;
	private EntityWalker walker = EntityWalker.instance();
	
	private static String s_empty_name = VWMLEntity.s_EmptyEntityId;
	
	private ComplexEntityNameBuilder() {
		
	}
	
	/**
	 * Simple instance creator; incapsulates builder's creation mechanism
	 * @return
	 */
	public static ComplexEntityNameBuilder instance() {
		return new ComplexEntityNameBuilder();
	}
	
	/**
	 * Generates random name / id
	 * @return
	 */
	public static String generateRandomName() {
		return "CE_" + UUID.randomUUID().toString();
	}
	
	/**
	 * Generates default root CE for module
	 * @param modName
	 * @return
	 */
	public static String generateRootId(String modName) {
		return "CERModule_" + modName;
	}
	
	/**
	 * Generates empty complex entity
	 * @return
	 */
	public static String generateEmptyComplexEntity() {
		ComplexEntityNameBuilder cenb = ComplexEntityNameBuilder.instance();
		cenb.startProgress();
		cenb.stopProgress();
		return cenb.build();
	}
	
	/**
	 * Adds object's id to complex entity
	 */
	public void addObjectId(Object id) {
		ComplexEntity ce = (ComplexEntity)walker.peek();
		ce.addEntity(new SimpleEntity((String)id));
	}
	
	/**
	 * Actually builds entity's name which, in turn, is considered as its Id
	 * @return
	 */
	public String build() {
		if (rootComplexEntity == null) {
			return s_empty_name;
		}
		return build(rootComplexEntity, "").trim();
	}
	
	/**
	 * Set start/stop progress's status
	 */
	public void startProgress() {
		ComplexEntity e = new ComplexEntity();
		if (rootComplexEntity == null) {
			currentComplexEntity = rootComplexEntity = e;
		}
		else {
			currentComplexEntity.addEntity(e);
			currentComplexEntity = e;
		}
		walker.push(currentComplexEntity);
	}
	
	public void stopProgress() {
		walker.pop();
		currentComplexEntity = (ComplexEntity)walker.peek();
	}
	
	/**
	 * Returns 'true' in case if entity's name is in building process
	 * @return
	 */
	public boolean isInProgress() {
		return currentComplexEntity != null;
	}
	
	/**
	 * Returns 'true' in case if build operation is allowed
	 * @return
	 */
	public boolean isBuildAllowed() {
		return (rootComplexEntity != null);
	}
	
	/**
	 * Clears name builder's storage
	 */
	public void clear() {
		currentComplexEntity = rootComplexEntity = null;
		walker.clear();
	}
	
	public String build(ComplexEntity ce, String name) {
		String str = name + "(";
		List<Entity> entities = ce.getEntities();
		for(Entity e : entities) {
			if (e instanceof ComplexEntity) {
				str = build((ComplexEntity)e, str);
			}
			else {
				str += e.getId() + " ";
			}
		}
		ce.clear();
		str = str.trim();
		str += ") ";
		return str;
	}
	
}

/*
('(' {
	    	complexEntityNameBuilder.startProgress();
	    	if (logger.isDebugEnabled()) {
	    		logger.debug("complex entity declaration process - started");
	    	}
           } (compound_entity_decl)+ ')')?

*/