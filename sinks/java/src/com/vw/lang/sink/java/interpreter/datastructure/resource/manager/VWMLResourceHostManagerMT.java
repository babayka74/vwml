package com.vw.lang.sink.java.interpreter.datastructure.resource.manager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

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
import com.vw.lang.sink.java.interpreter.datastructure.resource.manager.timer.mt.VWMLTimerManagerMTBroker;
import com.vw.lang.sink.java.interpreter.datastructure.ring.VWMLConflictRing;
import com.vw.lang.sink.java.interpreter.datastructure.ring.VWMLConflictRingNode;
import com.vw.lang.sink.java.interpreter.datastructure.ring.mt.VWMLConflictRingMT;
import com.vw.lang.sink.java.interpreter.datastructure.ring.mt.VWMLConflictRingNodeMT;
import com.vw.lang.sink.java.interpreter.datastructure.timer.VWMLInterpreterTimer;

/**
 * Multithreaded manager
 * @author Oleg
 *
 */
public class VWMLResourceHostManagerMT extends VWMLResourceHostManager {

	private static VWMLResourceHostManagerMT s_hostedManager = new VWMLResourceHostManagerMT();
	private static VWMLContextsRepository s_contextsRepo = null;
	private static VWMLObjectsRepository s_objectsRepo = null;
	private static VWMLInterceptorsRepository s_interceptorsRepo = null;
	private static VWMLGatesRepository s_gatesRepo = null;
	private static VWMLTimerManagerMTBroker s_timerManagerMTBroker = null;
	private static AtomicInteger s_objectsRepoCounter = new AtomicInteger(0);
	private static AtomicInteger s_contextsRepoCounter = new AtomicInteger(0);
	private static AtomicInteger s_interceptorsRepoCounter = new AtomicInteger(0);
	private static AtomicInteger s_gatesRepoCounter = new AtomicInteger(0);
	private static AtomicInteger s_timerManagerBrokerCounter = new AtomicInteger(0);
	
	private VWMLResourceHostManagerMT() {
	}

	public static VWMLResourceHostManagerMT instance() {
		return s_hostedManager;
	}

	@Override
	public VWMLConflictRingNode buildConflictRingNode(Object id, String readableId) {
		return new VWMLConflictRingNodeMT(id, readableId);
	}
	
	@Override
	public VWMLConflictRing findMostFreeRing(VWMLInterpreterConfiguration conf) {
		int min = -1;
		VWMLConflictRing free = null;
		for(VWMLHostedResources r : getHostedResourcesContainer().values()) {
			synchronized(r) {
				if (r.getRing() != null && !r.getRing().isMaster() && conf.getNodesPerRing() > r.getRing().calculateNumberOfNodes()) {
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
	public Map<String, VWMLInterceptor> requestInterceptorsRepoContainer() {
		return new ConcurrentHashMap<String, VWMLInterceptor>();
	}

	@Override
	public Map<String, VWMLGate> requestGatesRepoContainer() {
		return new ConcurrentHashMap<String, VWMLGate>();
	}

	@Override
	public List<VWMLInterpreterTimer> requestTimerManagerContainer() {
		return Collections.synchronizedList(new ArrayList<VWMLInterpreterTimer>());
	}
	
	@Override
	public VWMLConflictRing findRingByExecutingTerm(VWMLEntity executingTerm) {
		VWMLConflictRing ring = null;
		for(VWMLHostedResources r : getHostedResourcesContainer().values()) {
			synchronized(r) {
				ring = r.getRing();
			}
			if (ring != null && !ring.isMaster()) {
				if (ring.findNodeExecutingTerm(executingTerm) != null) {
					break;
				}
			}
		}
		return ring;
	}
	
	@Override
	public void remoteLock(VWMLInterpreterImpl interpreter, VWMLConflictRingNode from, VWMLConflictRingNode node) {
		VWMLConflictRing ring = null;
		VWMLConflictRingNode rtNode = interpreter.getRtNode();
		for(VWMLHostedResources r : getHostedResourcesContainer().values()) {
			synchronized(r) {
				ring = r.getRing();
			}
			if (ring != null && !ring.isMaster() && node.getExecutionGroup().getRing() != ring) {
				try {
					ring.askLockRequestFor(rtNode, from, node.getId());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void remoteUnlock(VWMLInterpreterImpl interpreter, VWMLConflictRingNode from, VWMLConflictRingNode node) {
		VWMLConflictRing ring = null;
		VWMLConflictRingNode rtNode = interpreter.getRtNode();
		for(VWMLHostedResources r : getHostedResourcesContainer().values()) {
			synchronized(r) {
				ring = r.getRing();
			}
			if (ring != null && !ring.isMaster() && node.getExecutionGroup().getRing() != ring) {
				try {
					ring.askUnlockRequestFor(rtNode, from, node.getId());
				} catch (Exception e) {
					// swallow for now
				}
			}
		}
	}
	
	@Override
	public VWMLContext remoteFindContext(String id) throws Exception {
		VWMLContext ctx = null;
		VWMLConflictRing ring = null;
		VWMLContextsRepository repo = null;
		VWMLContextsRepository curRepo = requestContextsRepo();
		for(VWMLHostedResources r : getHostedResourcesContainer().values()) {
			synchronized(r) {
				repo = r.getContextsRepo();
				ring = r.getRing();
			}
			if (repo != null && ring != null && !ring.isMaster() && curRepo != repo && repo.belong(id)) {
				try {
					ctx = ring.askContextFindRequest(id);
				} catch (Exception e) {
					// swallow for now
				}
				break;
			}
		}
		return ctx;
	}
	
	@Override
	public void activateNodeOnRemoteRing(VWMLConflictRing ring, VWMLInterpreterImpl interpreter, VWMLEntity cloned, VWMLEntity clonedSourceLft) throws Exception {
		
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
	protected void interceptorsRepoInit(VWMLHostedResources r) {
		if (r.getInterceptorsRepo() == null) {
			synchronized(r) {
				if (r.getInterceptorsRepo() != null) {
					return;
				}
				if (s_interceptorsRepo == null) {
					synchronized(VWMLInterceptorsRepository.class) {
						if (s_interceptorsRepo == null) {
							s_interceptorsRepo = new VWMLInterceptorsRepository();
							s_interceptorsRepo.init();
						}
					}
				}
				r.setInterceptorsRepo(s_interceptorsRepo);
				s_interceptorsRepoCounter.incrementAndGet();
			}
		}
	}
	
	@Override
	protected void interceptorsRepoDone(VWMLHostedResources r) {
		if (r.getInterceptorsRepo() != null) {
			synchronized(r) {
				if (r.getInterceptorsRepo() == null) {
					return;
				}
				r.setInterceptorsRepo(null);
				if (s_interceptorsRepoCounter.decrementAndGet() == 0) {
					s_interceptorsRepo.done();
					s_interceptorsRepo = null;
				}
			}
		}
	}

	@Override
	protected void gatesRepoInit(VWMLHostedResources r) {
		if (r.getGatesRepo() == null) {
			synchronized(r) {
				if (r.getGatesRepo() != null) {
					return;
				}
				if (s_gatesRepo == null) {
					synchronized(VWMLGatesRepository.class) {
						if (s_gatesRepo == null) {
							s_gatesRepo = new VWMLGatesRepository();
							s_gatesRepo.init();
						}
					}
				}
				r.setGatesRepo(s_gatesRepo);
				s_gatesRepoCounter.incrementAndGet();
			}
		}
	}

	@Override
	protected void gatesRepoDone(VWMLHostedResources r) {
		if (r.getGatesRepo() != null) {
			synchronized(r) {
				if (r.getGatesRepo() == null) {
					return;
				}
				r.setGatesRepo(null);
				if (s_gatesRepoCounter.decrementAndGet() == 0) {
					s_gatesRepo.done();
					s_gatesRepo = null;
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
	
	@Override
	protected void timerManagerInit(VWMLHostedResources r) {
		if (r.getTimerManagerBroker() == null) {
			synchronized(r) {
				if (r.getTimerManagerBroker() != null) {
					return;
				}
				if (s_timerManagerMTBroker == null) {
					synchronized(VWMLTimerManagerMTBroker.class) {
						if (s_timerManagerMTBroker == null) {
							s_timerManagerMTBroker = VWMLTimerManagerMTBroker.instance();
							s_timerManagerMTBroker.init();
						}
					}
				}
				r.setTimerManagerBroker(s_timerManagerMTBroker);
				s_timerManagerBrokerCounter.incrementAndGet();
			}
		}
	}

	@Override
	protected void timerManagerDone(VWMLHostedResources r) {
		if (r.getTimerManagerBroker() != null) {
			synchronized(r) {
				if (r.getTimerManagerBroker() == null) {
					return;
				}
				r.setTimerManagerBroker(null);
				if (s_timerManagerBrokerCounter.decrementAndGet() == 0) {
					r.getTimerManagerBroker().done();
				}
			}
		}
	}
	
}
