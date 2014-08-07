package com.vw.lang.sink.java.interpreter.datastructure.resource.manager;

import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import com.vw.lang.sink.java.VWMLContextsRepository;
import com.vw.lang.sink.java.VWMLObject;
import com.vw.lang.sink.java.VWMLObjectsRepository;
import com.vw.lang.sink.java.entity.VWMLEntity;
import com.vw.lang.sink.java.interpreter.datastructure.VWMLContext;
import com.vw.lang.sink.java.interpreter.datastructure.ring.VWMLConflictRing;
import com.vw.lang.sink.java.interpreter.datastructure.ring.VWMLConflictRingNode;
import com.vw.lang.sink.java.interpreter.datastructure.ring.mt.VWMLConflictRingMT;

/**
 * Multithreaded manager
 * @author Oleg
 *
 */
public class VWMLResourceHostManagerMT extends VWMLResourceHostManager {

	private static VWMLResourceHostManagerMT s_hostedManager = new VWMLResourceHostManagerMT();
	private static VWMLContextsRepository s_contextsRepo = null;
	private static VWMLObjectsRepository s_objectsRepo = null;
	private static AtomicInteger s_objectsRepoCounter = new AtomicInteger(0);
	private static AtomicInteger s_contextsRepoCounter = new AtomicInteger(0);
	
	private VWMLResourceHostManagerMT() {
	}

	public static VWMLResourceHostManagerMT instance() {
		return s_hostedManager;
	}

	@Override
	public VWMLConflictRing findMostFreeRing() {
		int min = -1;
		VWMLConflictRing free = null;
		for(VWMLHostedResources r : getHostedResourcesContainer().values()) {
			synchronized(r) {
				if (r.getRing() != null) {
					if (min == -1 || min < r.getRing().calculateNumberOfNodes()) {
						min = r.getRing().calculateNumberOfNodes();
						free = r.getRing();
					}
				}
			}
		}
		return free;
	}
	
	@Override
	public Map<Object, VWMLContext> requestContextsRepoContainer() {
		return new ConcurrentHashMap<Object, VWMLContext>();
	}

	@Override
	public Map<Object, VWMLObject> requestObjectsRepoContainer() {
		return new ConcurrentHashMap<Object, VWMLObject>();
	}

	@Override
	public Map<VWMLEntity, VWMLEntity> requestEntityAssociatedContainer() {
		return new ConcurrentHashMap<VWMLEntity, VWMLEntity>();
	}

	@Override
	public Set<VWMLEntity> requestEntityAssociatedSet() {
		return Collections.synchronizedSet(new HashSet<VWMLEntity>());
	}
	
	@Override
	public void remoteLock(VWMLConflictRingNode node) {
		for(VWMLHostedResources r : getHostedResourcesContainer().values()) {
			synchronized(r) {
				VWMLConflictRing ring = r.getRing();
				if (ring != null && node.getExecutionGroup().getRing() != ring && ring.belong(node.getId())) {
					try {
						ring.sendLockRequestFor(node.getId());
					} catch (Exception e) {
						// swallow for now
					}
				}
			}
		}
	}

	@Override
	public void remoteUnlock(VWMLConflictRingNode node) {
		for(VWMLHostedResources r : getHostedResourcesContainer().values()) {
			synchronized(r) {
				VWMLConflictRing ring = r.getRing();
				if (ring != null && node.getExecutionGroup().getRing() != ring && ring.belong(node.getId())) {
					try {
						ring.sendUnlockRequestFor(node.getId());
					} catch (Exception e) {
						// swallow for now
					}
				}
			}
		}
	}
	
	@Override
	public VWMLContext remoteFindContext(String id) throws Exception {
		VWMLContext ctx = null;
		VWMLContextsRepository curRepo = requestContextsRepo();
		for(VWMLHostedResources r : getHostedResourcesContainer().values()) {
			synchronized(r) {
				VWMLContextsRepository repo = r.getContextsRepo();
				if (repo != null && r.getRing() != null && curRepo != repo && repo.belong(id)) {
					try {
						ctx = r.getRing().sendContextFindRequest(id);
					} catch (Exception e) {
						// swallow for now
					}
					break;
				}
			}
		}
		return ctx;
	}
	
	protected Long requestKey() {
		return Long.valueOf(Thread.currentThread().getId());
	}

	@Override
	protected void ringInit(VWMLHostedResources r) {
		if (r.getRing() == null) {
			synchronized(r) {
				if (r.getRing() != null) {
					return;
				}
				VWMLConflictRingMT ring = new VWMLConflictRingMT();
				ring.init();
				r.setRing(ring);
			}
		}
	}

	@Override
	protected void ringDone(VWMLHostedResources r) {
		if (r.getRing() != null) {
			synchronized(r) {
				if (r.getRing() == null) {
					return;
				}
				r.setRing(null);
			}
		}
	}

	@Override
	protected void objectsRepoInit(VWMLHostedResources r) {
		if (r.getObjectsRepo() == null) {
			synchronized(r) {
				if (r.getObjectsRepo() != null) {
					return;
				}
				if (s_objectsRepo == null) {
					synchronized(VWMLObjectsRepository.class) {
						if (s_objectsRepo == null) {
							s_objectsRepo = new VWMLObjectsRepository();
							s_objectsRepo.init();
						}
					}
				}
				r.setObjectsRepo(s_objectsRepo);
				s_objectsRepoCounter.incrementAndGet();
			}
		}
	}

	@Override
	protected void objectsRepoDone(VWMLHostedResources r) {
		if (r.getObjectsRepo() != null) {
			synchronized(r) {
				if (r.getObjectsRepo() == null) {
					return;
				}
				r.setObjectsRepo(null);
				if (s_objectsRepoCounter.decrementAndGet() == 0) {
					s_objectsRepo = null;
				}
			}
		}
	}
	
	@Override
	protected void contextsRepoInit(VWMLHostedResources r) {
		if (r.getContextsRepo() == null) {
			synchronized(r) {
				if (r.getContextsRepo() != null) {
					return;
				}
				if (s_contextsRepo == null) {
					synchronized(VWMLContextsRepository.class) {
						if (s_contextsRepo == null) {
							s_contextsRepo = new VWMLContextsRepository();
							s_contextsRepo.init();
						}
					}
				}
				r.setContextsRepo(s_contextsRepo);
				s_contextsRepoCounter.incrementAndGet();
			}
		}
	}

	@Override
	protected void contextsRepoDone(VWMLHostedResources r) {
		if (r.getContextsRepo() != null) {
			synchronized(r) {
				if (r.getContextsRepo() == null) {
					return;
				}
				r.setContextsRepo(null);
				if (s_contextsRepoCounter.decrementAndGet() == 0) {
					s_contextsRepo = null;
				}
			}
		}
	}
}
