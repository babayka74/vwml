package com.vw.lang.sink.java.interpreter.datastructure.resource.manager;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.vw.lang.sink.java.VWMLContextsRepository;
import com.vw.lang.sink.java.VWMLObject;
import com.vw.lang.sink.java.VWMLObjectsRepository;
import com.vw.lang.sink.java.entity.VWMLEntity;
import com.vw.lang.sink.java.interpreter.VWMLInterpreterConfiguration;
import com.vw.lang.sink.java.interpreter.VWMLInterpreterImpl;
import com.vw.lang.sink.java.interpreter.datastructure.VWMLContext;
import com.vw.lang.sink.java.interpreter.datastructure.ring.VWMLConflictRing;
import com.vw.lang.sink.java.interpreter.datastructure.ring.VWMLConflictRingNode;
import com.vw.lang.sink.java.operations.VWMLOperationUtils;

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
	public VWMLConflictRingNode buildConflictRingNode(Object id, String readableId) {
		return new VWMLConflictRingNode(id, readableId);
	}
	
	@Override
	public void activateNodeOnRemoteRing(VWMLConflictRing ring, VWMLInterpreterImpl interpreter, VWMLEntity cloned, VWMLEntity clonedSourceLft) throws Exception {
		VWMLOperationUtils.activateClonedTerm(ring, interpreter, cloned, clonedSourceLft);
	}
	
	@Override
	public VWMLConflictRing findMostFreeRing(VWMLInterpreterConfiguration conf) {
		VWMLHostedResources r = getHostedResourcesContainer().get(requestKey());
		return r.getRing();
	}
	
	
	@Override
	public Map<Object, VWMLContext> requestContextsRepoContainer() {
		return new HashMap<Object, VWMLContext>();
	}

	@Override
	public Map<Object, VWMLObject> requestObjectsRepoContainer() {
		return new HashMap<Object, VWMLObject>();
	}

	@Override
	public Map<VWMLEntity, VWMLEntity> requestEntityAssociatedContainer() {
		return new HashMap<VWMLEntity, VWMLEntity>();
	}
	
	@Override
	public Set<VWMLEntity> requestEntityAssociatedSet() {
		return new HashSet<VWMLEntity>();
	}
	
	@Override
	public VWMLConflictRing findRingByExecutingTerm(VWMLEntity executingTerm) {
		VWMLHostedResources r = getHostedResourcesContainer().get(requestKey());
		VWMLConflictRing ring = r.getRing();
		if (ring != null) {
			if (ring.findNodeExecutingTerm(executingTerm) == null) {
				ring = null;
			}
		}
		return ring;
	}
	
	@Override
	public void remoteLock(VWMLInterpreterImpl interpreter, VWMLConflictRingNode from, VWMLConflictRingNode node) {
	}

	@Override
	public void remoteUnlock(VWMLInterpreterImpl interpreter, VWMLConflictRingNode from, VWMLConflictRingNode node) {
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
