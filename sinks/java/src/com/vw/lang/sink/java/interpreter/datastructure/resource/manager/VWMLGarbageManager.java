package com.vw.lang.sink.java.interpreter.datastructure.resource.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.vw.lang.sink.java.VWMLContextsRepository;
import com.vw.lang.sink.java.VWMLObjectBuilder;
import com.vw.lang.sink.java.entity.VWMLEntity;
import com.vw.lang.sink.java.interpreter.datastructure.VWMLContext;

/**
 * Managers of removed entities on specified 'garbage' context
 * @author Oleg
 *
 */
public class VWMLGarbageManager {

	protected static class VWMLGarbageEntityInfo {
		private VWMLEntity entity;

		public VWMLGarbageEntityInfo(VWMLEntity entity) {
			super();
			this.entity = entity;
		}

		public VWMLEntity getEntity() {
			return entity;
		}

		public void setEntity(VWMLEntity entity) {
			this.entity = entity;
		}
	}
	
	private static String garbageContextName = "__vwml_garbage_context__";
	private VWMLContext garbageContext = null;
	private Map<VWMLObjectBuilder.VWMLObjectType, List<VWMLGarbageEntityInfo>> garbageStorage = VWMLResourceHostManagerFactory.hostManagerInstance().requestGarbageManagerContainer();
	
	private VWMLGarbageManager() {
		
	}
	
	public static VWMLGarbageManager instance() {
		return new VWMLGarbageManager();
	}
	
	public static String getGarbageContextName() {
		return garbageContextName;
	}
	
	public void init() {
		garbageContext = VWMLContextsRepository.instance().createContextIfNotExists(garbageContextName);
	}
	
	public void done() {
		for(VWMLObjectBuilder.VWMLObjectType t : garbageStorage.keySet()) {
			List<VWMLGarbageEntityInfo> eis = garbageStorage.get(t);
			if (eis != null) {
				eis.clear();
			}
		}
		garbageStorage.clear();
	}
	
	/**
	 * Entity is added during release operation. 
	 * All entities are added to the same context event if entity with the same id has already been added
	 * @param garbageEntity
	 */
	public void garbageEntity(VWMLEntity garbageEntity) {
		// garbageEntity.deduceEntityType()
		garbageContext.associateEntity(garbageEntity);
		garbageEntity.setContext(garbageContext);
		garbageEntity.rebuildHashId(garbageEntity.getReadableId());
		VWMLObjectBuilder.VWMLObjectType eType = garbageEntity.deduceEntityType();
		List<VWMLGarbageEntityInfo> ges = garbageStorage.get(eType);
		if (ges == null) {
			ges = new ArrayList<VWMLGarbageManager.VWMLGarbageEntityInfo>();
			garbageStorage.put(eType, ges);
		}
		synchronized(ges) {
			ges.add(new VWMLGarbageEntityInfo(garbageEntity));
		}
	}
	
	/**
	 * Requests for entity of specified type
	 * @param eType
	 * @return
	 */
	public VWMLEntity requestForEntity(VWMLObjectBuilder.VWMLObjectType eType) {
		VWMLEntity e = null;
		List<VWMLGarbageEntityInfo> ges = garbageStorage.get(eType);
		if (ges != null && ges.size() > 0) {
			synchronized(ges) {
				e = ges.get(0).getEntity();
				e.getContext().unAssociateEntity(e);
				ges.remove(0);
				e.resurrect();
			}
		}		
		return e;
	}
}
