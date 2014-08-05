package com.vw.lang.sink.java.interpreter.datastructure.resource.manager;

import com.vw.lang.sink.java.VWMLContextsRepository;
import com.vw.lang.sink.java.VWMLObjectsRepository;
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
	
	private VWMLResourceHostManagerMT() {
		
	}

	public static VWMLResourceHostManagerMT instance() {
		return s_hostedManager;
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
				VWMLObjectsRepository repo = new VWMLObjectsRepository();
				repo.init();
				r.setObjectsRepo(repo);
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
				VWMLContextsRepository repo = new VWMLContextsRepository();
				repo.init();
				r.setContextsRepo(repo);
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
			}
		}
	}
}
