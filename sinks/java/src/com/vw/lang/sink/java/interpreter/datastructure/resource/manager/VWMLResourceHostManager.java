package com.vw.lang.sink.java.interpreter.datastructure.resource.manager;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import com.vw.lang.sink.java.VWMLContextsRepository;
import com.vw.lang.sink.java.VWMLGatesRepository;
import com.vw.lang.sink.java.VWMLInterceptorsRepository;
import com.vw.lang.sink.java.VWMLObject;
import com.vw.lang.sink.java.VWMLObjectsRepository;
import com.vw.lang.sink.java.entity.VWMLEntity;
import com.vw.lang.sink.java.gate.VWMLGate;
import com.vw.lang.sink.java.interceptor.VWMLInterceptor;
import com.vw.lang.sink.java.interpreter.VWMLInterpreterConfiguration;
import com.vw.lang.sink.java.interpreter.VWMLInterpreterImpl;
import com.vw.lang.sink.java.interpreter.datastructure.VWMLContext;
import com.vw.lang.sink.java.interpreter.datastructure.resource.manager.timer.TimerManagerBroker;
import com.vw.lang.sink.java.interpreter.datastructure.ring.VWMLConflictRing;
import com.vw.lang.sink.java.interpreter.datastructure.ring.VWMLConflictRingNode;
import com.vw.lang.sink.java.interpreter.datastructure.timer.VWMLInterpreterTimer;
import com.vw.lang.sink.java.interpreter.datastructure.timer.VWMLInterpreterTimerManager;

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
		private VWMLInterceptorsRepository interceptorsRepo = null;
		private VWMLGatesRepository gatesRepo = null;
		private TimerManagerBroker timerManagerBroker = null;
		
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

		public VWMLInterceptorsRepository getInterceptorsRepo() {
			return interceptorsRepo;
		}

		public void setInterceptorsRepo(VWMLInterceptorsRepository interceptorsRepo) {
			this.interceptorsRepo = interceptorsRepo;
		}

		public VWMLGatesRepository getGatesRepo() {
			return gatesRepo;
		}

		public void setGatesRepo(VWMLGatesRepository gatesRepo) {
			this.gatesRepo = gatesRepo;
		}

		public TimerManagerBroker getTimerManagerBroker() {
			return timerManagerBroker;
		}

		public void setTimerManagerBroker(TimerManagerBroker timerManagerBroker) {
			this.timerManagerBroker = timerManagerBroker;
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
	 * Requests interceptors' repository
	 * @return
	 */
	public VWMLInterceptorsRepository requestInterceptorsRepo() {
		VWMLHostedResources r = getHostedResource();
		interceptorsRepoInit(r);
		return r.getInterceptorsRepo();
	}

	/**
	 * Removes interceptors repository from host storage, allowing it to be re-created upon next request
	 */
	public void markInterceptorsRepoAsInvalid() {
		VWMLHostedResources r = getHostedResource();
		interceptorsRepoDone(r);
	}
	
	/**
	 * Requests gates' repository
	 * @return
	 */
	public VWMLGatesRepository requestGatesRepo() {
		VWMLHostedResources r = getHostedResource();
		gatesRepoInit(r);
		return r.getGatesRepo();
	}

	/**
	 * Removes gates repository from host storage, allowing it to be re-created upon next request
	 */
	public void markGatesRepoAsInvalid() {
		VWMLHostedResources r = getHostedResource();
		gatesRepoDone(r);
	}

	/**
	 * Requests timer manager
	 * @return
	 */
	public VWMLInterpreterTimerManager requestTimerManager() {
		VWMLHostedResources r = getHostedResource();
		timerManagerInit(r);
		return r.getTimerManagerBroker().getTimerManager();
	}

	/**
	 * Removes timer manager from host storage, allowing it to be re-created upon next request
	 */
	public void markTimerManagerAsInvalid() {
		VWMLHostedResources r = getHostedResource();
		timerManagerDone(r);
	}
	
	/**
	 * The transportedEntity is sent to ring identified by ringDestTerm and handler identified by handlerDestTerm
	 * The handler is interpreted by destination
	 * @param ring
	 * @param ringDestTerm
	 * @param transportedEntity
	 * @param handlerDestTerm
	 * @throws Exception
	 */
	public void activateGate(VWMLConflictRing ring, VWMLEntity ringDestTerm, VWMLEntity transportedEntity, VWMLEntity handlerDestTerm) throws Exception {
		ring.askActivateGate(ringDestTerm, transportedEntity, handlerDestTerm);
	}
	
	/**
	 * Lookups for ring which has node which in turn executes given term
	 * @param executingTerm
	 * @return
	 */
	public abstract VWMLConflictRing findRingByExecutingTerm(VWMLEntity executingTerm);
	
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
	 * Requests container which is used by interceptor's repository
	 * @return
	 */
	public abstract Map<String, VWMLInterceptor> requestInterceptorsRepoContainer();

	/**
	 * Requests container which is used by gate's repository
	 * @return
	 */
	public abstract Map<String, VWMLGate> requestGatesRepoContainer();

	/**
	 * Requests container which is used by timer manager
	 * @return
	 */
	public abstract List<VWMLInterpreterTimer> requestTimerManagerContainer();
	
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
	 * Initializes requested interceptor repository
	 * @param r
	 */
	protected abstract void interceptorsRepoInit(VWMLHostedResources r);
	
	/**
	 * Releases interceptors repository, next time will be create new one
	 * @param r
	 */
	protected abstract void interceptorsRepoDone(VWMLHostedResources r);

	/**
	 * Initializes requested gate repository
	 * @param r
	 */
	protected abstract void gatesRepoInit(VWMLHostedResources r);
	
	/**
	 * Releases gate repository, next time will be create new one
	 * @param r
	 */
	protected abstract void gatesRepoDone(VWMLHostedResources r);

	/**
	 * Timer manager initialization depending on MT strategy
	 * @param r
	 */
	protected abstract void timerManagerInit(VWMLHostedResources r);

	/**
	 * Timer manager uninitialization depending on MT strategy
	 * @param r
	 */
	protected abstract void timerManagerDone(VWMLHostedResources r);
	
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
