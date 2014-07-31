package com.vw.lang.sink.java.interpreter.datastructure.resource.manager;

import com.vw.lang.sink.java.VWMLContextsRepository;
import com.vw.lang.sink.java.VWMLObjectsRepository;
import com.vw.lang.sink.java.interpreter.datastructure.ring.VWMLConflictRing;

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
				VWMLConflictRing ring = new VWMLConflictRing();
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
