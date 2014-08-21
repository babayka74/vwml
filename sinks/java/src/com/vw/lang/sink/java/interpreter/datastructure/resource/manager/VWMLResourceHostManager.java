package com.vw.lang.sink.java.interpreter.datastructure.resource.manager;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import com.vw.lang.sink.java.VWMLContextsRepository;
import com.vw.lang.sink.java.VWMLObject;
import com.vw.lang.sink.java.VWMLObjectsRepository;
import com.vw.lang.sink.java.entity.VWMLEntity;
import com.vw.lang.sink.java.interpreter.VWMLInterpreterConfiguration;
import com.vw.lang.sink.java.interpreter.VWMLInterpreterImpl;
import com.vw.lang.sink.java.interpreter.datastructure.VWMLContext;
import com.vw.lang.sink.java.interpreter.datastructure.ring.VWMLConflictRing;
import com.vw.lang.sink.java.interpreter.datastructure.ring.VWMLConflictRingNode;

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
	 * Get resource's uniq id
	 * @return
	 */
	public Long getUniqId() {
		return requestKey();
	}
	
	/**
	 * Returns true in case if resource is available
	 * @return
	 */
	public boolean isResourceAvailable() {
		Long key = requestKey();
		VWMLHostedResources r = hostedResources.get(key);
		return (r != null);
	}
	
	/**
	 * Removes resource from storage
	 */
	public void clearResource() {
		removeResource();
	}
	
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
	 * Builds conflict ring node depending on threading model
	 * @param id
	 * @param readableId
	 * @return
	 */
	public abstract VWMLConflictRingNode buildConflictRingNode(Object id, String readableId);
	
	/**
	 * Instantiates and activates new node on remote ring
	 * @param ring
	 * @param interpreter
	 * @param cloned
	 * @param clonedSourceLft
	 */
	public abstract void activateNodeOnRemoteRing(VWMLConflictRing ring, VWMLInterpreterImpl interpreter, VWMLEntity cloned, VWMLEntity clonedSourceLft) throws Exception;
	
	/**
	 * Finds most free ring
	 * @param conf
	 */
	public abstract VWMLConflictRing findMostFreeRing(VWMLInterpreterConfiguration conf);
	
	/**
	 * Requests associated set
	 * @return
	 */
	public abstract Set<VWMLEntity> requestEntityAssociatedSet();
	
	/**
	 * Requests associated container
	 * @return
	 */
	public abstract Map<VWMLEntity, VWMLEntity> requestEntityAssociatedContainer();
	
	/**
	 * Requests container which is used by contexts' repository
	 * @return
	 */
	public abstract Map<Object, VWMLContext> requestContextsRepoContainer();

	/**
	 * Requests container which is used by objects' repository
	 * @return
	 */
	public abstract Map<Object, VWMLObject> requestObjectsRepoContainer();
	
	/**
	 * Looks up for context which can be defined on remote ring
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public abstract VWMLContext remoteFindContext(String id) throws Exception;
	
	/**
	 * Marks remote node as locked, which has the same id as given node
	 * @param interpreter
	 * @param from
	 * @param node
	 */
	public abstract void remoteLock(VWMLInterpreterImpl interpreter, VWMLConflictRingNode from, VWMLConflictRingNode node);

	/**
	 * Marks remote node as locked, which has the same id as given node 
	 * @param interpreter
	 * @param from
	 * @param node
	 */
	public abstract void remoteUnlock(VWMLInterpreterImpl interpreter, VWMLConflictRingNode from, VWMLConflictRingNode node);
	
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
	
	protected Map<Long, VWMLHostedResources> getHostedResourcesContainer() {
		return hostedResources;
	}
	
	private VWMLHostedResources getHostedResource() {
		Long key = requestKey();
		VWMLHostedResources r = hostedResources.get(key);
		if (r == null) {
			r = new VWMLHostedResources();
			hostedResources.put(key, r);
		}
		return r;
	}
	
	private void removeResource() {
		Long key = requestKey();
		VWMLHostedResources r = hostedResources.get(key);
		ringDone(r);
		objectsRepoDone(r);
		contextsRepoDone(r);
		hostedResources.remove(key);
	}
}
