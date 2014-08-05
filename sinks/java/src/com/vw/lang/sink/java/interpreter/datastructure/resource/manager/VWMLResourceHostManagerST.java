package com.vw.lang.sink.java.interpreter.datastructure.resource.manager;

import com.vw.lang.sink.java.VWMLContextsRepository;
import com.vw.lang.sink.java.VWMLObjectsRepository;
import com.vw.lang.sink.java.interpreter.datastructure.VWMLContext;
import com.vw.lang.sink.java.interpreter.datastructure.ring.VWMLConflictRing;
import com.vw.lang.sink.java.interpreter.datastructure.ring.VWMLConflictRingNode;

/**
 * Singlethreaded manager
 * @author Oleg
 *
 */
public class VWMLResourceHostManagerST extends VWMLResourceHostManager {
	private static VWMLResourceHostManagerST s_hostedManager = new VWMLResourceHostManagerST();
	
	private VWMLResourceHostManagerST() {
		
	}

	public static VWMLResourceHostManagerST instance() {
		return s_hostedManager;
	}

	@Override
	public void remoteLock(VWMLConflictRingNode node) {
	}

	@Override
	public void remoteUnlock(VWMLConflictRingNode node) {
	}

	@Override
	public VWMLContext remoteFindContext(String id) throws Exception {
		return null;
	}
	
	protected Long requestKey() {
		return Long.valueOf(0x1234);
	}
	
	protected void objectsRepoInit(VWMLHostedResources r) {
		if (r.getObjectsRepo() == null) {
			VWMLObjectsRepository repo = new VWMLObjectsRepository();
			repo.init();
			r.setObjectsRepo(repo);
		}
	}
	
	protected void objectsRepoDone(VWMLHostedResources r) {
		r.setObjectsRepo(null);
	}
	
	protected void ringInit(VWMLHostedResources r) {
		if (r.getRing() == null) {
			VWMLConflictRing ring = new VWMLConflictRing();
			ring.init();
			r.setRing(ring);
		}
	}

	protected void ringDone(VWMLHostedResources r) {
		r.setRing(null);
	}
	
	@Override
	protected void contextsRepoInit(VWMLHostedResources r) {
		if (r.getContextsRepo() == null) {
			VWMLContextsRepository repo = new VWMLContextsRepository();
			repo.init();
			r.setContextsRepo(repo);
		}
	}

	@Override
	protected void contextsRepoDone(VWMLHostedResources r) {
		r.setContextsRepo(null);
	}
}
