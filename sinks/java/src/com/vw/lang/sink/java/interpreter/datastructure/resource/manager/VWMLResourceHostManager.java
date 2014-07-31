package com.vw.lang.sink.java.interpreter.datastructure.resource.manager;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.vw.lang.sink.java.VWMLContextsRepository;
import com.vw.lang.sink.java.VWMLObjectsRepository;
import com.vw.lang.sink.java.interpreter.datastructure.ring.VWMLConflictRing;

/**
 * Managers resources which are active on the same host
 * @author Oleg
 *
 */
public abstract class VWMLResourceHostManager {
		
	protected static class VWMLHostedResources {
		private VWMLConflictRing ring = null;
		private VWMLObjectsRepository objectsRepo = null;
		private VWMLContextsRepository contextsRepo = null;
		
		public VWMLConflictRing getRing() {
			return ring;
		}
		
		public void setRing(VWMLConflictRing ring) {
			this.ring = ring;
		}
		
		public VWMLObjectsRepository getObjectsRepo() {
			return objectsRepo;
		}

		public void setObjectsRepo(VWMLObjectsRepository objectsRepo) {
			this.objectsRepo = objectsRepo;
		}

		public VWMLContextsRepository getContextsRepo() {
			return contextsRepo;
		}

		public void setContextsRepo(VWMLContextsRepository contextsRepo) {
			this.contextsRepo = contextsRepo;
		}
	}
	
	private Map<Long, VWMLHostedResources> hostedResources = new ConcurrentHashMap<Long, VWMLHostedResources>();

	/**
	 * Requests and initializes ring
	 * @return
	 */
	public VWMLConflictRing requestRing() {
		VWMLHostedResources r = getHostedResource();
		ringInit(r);
		return r.getRing();
	}
	
	/**
	 * Removes ring from host storage, allowing it to be re-created upon next request
	 */
	public void markRingAsInvalid() {
		VWMLHostedResources r = getHostedResource();
		ringDone(r);
	}
	
	/**
	 * Requests objects' repository
	 * @return
	 */
	public VWMLObjectsRepository requestObjectsRepo() {
		VWMLHostedResources r = getHostedResource();
		objectsRepoInit(r);
		return r.getObjectsRepo();
	}

	/**
	 * Removes objects repository from host storage, allowing it to be re-created upon next request
	 */
	public void markObjectsRepoAsInvalid() {
		VWMLHostedResources r = getHostedResource();
		objectsRepoDone(r);
	}

	/**
	 * Requests contexts' repository
	 * @return
	 */
	public VWMLContextsRepository requestContextsRepo() {
		VWMLHostedResources r = getHostedResource();
		contextsRepoInit(r);
		return r.getContextsRepo();
	}

	/**
	 * Removes contexts repository from host storage, allowing it to be re-created upon next request
	 */
	public void markContextsRepoAsInvalid() {
		VWMLHostedResources r = getHostedResource();
		contextsRepoDone(r);
	}
	
	/**
	 * Initializes conflict ring
	 * @param r
	 */
	protected abstract void ringInit(VWMLHostedResources r);

	/**
	 * Uninitializes conflict ring
	 * @param r
	 */
	protected abstract void ringDone(VWMLHostedResources r);
	
	/**
	 * Initializes requested objects repository
	 * @param r
	 */
	protected abstract void objectsRepoInit(VWMLHostedResources r);
	
	/**
	 * Releases objects repository, next time will be create new one
	 * @param r
	 */
	protected abstract void objectsRepoDone(VWMLHostedResources r);

	/**
	 * Initializes requested context repository
	 * @param r
	 */
	protected abstract void contextsRepoInit(VWMLHostedResources r);
	
	/**
	 * Releases contexts repository, next time will be create new one
	 * @param r
	 */
	protected abstract void contextsRepoDone(VWMLHostedResources r);
	
	/**
	 * Requests key depending on resources' strategy
	 * @return
	 */
	protected abstract Long requestKey();

	private VWMLHostedResources getHostedResource() {
		Long key = requestKey();
		VWMLHostedResources r = hostedResources.get(key);
		if (r == null) {
			r = new VWMLHostedResources();
			hostedResources.put(key, r);
		}
		return r;
	}
}
