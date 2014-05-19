package com.win.game.model.fringe.gate.async.console;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentLinkedQueue;

import com.vw.lang.beyond.java.fringe.entity.EWEntity;
import com.vw.lang.beyond.java.fringe.entity.EWEntityBuilder;
import com.vw.lang.beyond.java.fringe.gate.IEW2VWMLGate;
import com.vw.lang.beyond.java.fringe.gate.IVWMLGate;
import com.vw.lang.beyond.java.fringe.gate.console.Console;

public class AsyncConsole implements IVWMLGate {

	protected static abstract class ConsoleActivity implements Runnable {

		private Console console = Console.instance();
		private Thread activity = null;
		private boolean stop = false;
		private AsyncConsole gate = null;
		private List<DispatcherPairs> dispatchedQueues = null;

		public void start() {
			stop = false;
			activity = new Thread(this);
			activity.start();
		}

		public void stop() {
			if (activity != null) {
				stop = true;
				activity.interrupt();
				activity = null;
			}
		}
		
		public Console getConsole() {
			return console;
		}

		public boolean isStop() {
			return stop;
		}

		public List<DispatcherPairs> getDispatchedQueues() {
			return dispatchedQueues;
		}

		public void setDispatchedQueues(List<DispatcherPairs> dispatchedQueues) {
			this.dispatchedQueues = dispatchedQueues;
		}

		public AsyncConsole getGate() {
			return gate;
		}

		public void setGate(AsyncConsole gate) {
			this.gate = gate;
		}
	}
	
	/**
	 * Reads from 'console'
	 * @author Oleg
	 *
	 */
	protected static class ConsoleInActivity extends ConsoleActivity implements Runnable {
		private ConcurrentLinkedQueue<EWEntity> inDefault = new ConcurrentLinkedQueue<EWEntity>();
		private ConcurrentLinkedQueue<EWEntity> in = null;

		public EWEntity read(String qName) {
			in = inDefault;
			if (getGate() != null && qName != null && !qName.equals(EWEntity.s_EmptyEntityId)) {
				in = getGate().getQueueByName(qName);
				if (in == null) {
					in = inDefault;
				}
			}
			EWEntity e = in.poll();
			if (e == null) {
				e = EWEntityBuilder.buildSimpleEntity(EWEntity.s_NilEntityId, EWEntity.s_NilEntityId);
			}
			return e;
		}
		
		@Override
		public void run() {
			while (!isStop()) {
				EWEntity e = getConsole().invokeEW(Console.getInMethod(), null);
				if (e == null) {
					e = EWEntityBuilder.buildSimpleEntity(EWEntity.s_NilEntityId, EWEntity.s_NilEntityId);
				}
				in = inDefault;
				if (getGate() != null) {
					in = getGate().getQueueByExpectedValue((String)e.getId());
					if (in == null) {
						in = inDefault;
					}
				}
				in.add(e);
			}
		}
	}
	
	/**
	 * Write to 'console'
	 * @author Oleg
	 *
	 */
	protected static class ConsoleOutActivity extends ConsoleActivity implements Runnable {
		private Integer outReadySignal = new Integer(0x1234);
		private ConcurrentLinkedQueue<EWEntity> out = new ConcurrentLinkedQueue<EWEntity>();

		public void write(EWEntity e) {
			out.add(e);
			synchronized(outReadySignal) {
				outReadySignal.notify();
			}
		}
		
		@Override
		public void run() {
			while(!isStop()) {
				EWEntity e = out.poll();
				if (e == null) {
					synchronized(outReadySignal) {
						try {
							outReadySignal.wait();
						} catch (InterruptedException ex) {
						}
					}
				}
				else {
					getConsole().invokeEW(Console.getOutMethod(), e);
				}
			}
 		}
	}

	protected static class DispatcherPairs {
		private ConcurrentLinkedQueue<EWEntity> queue = null;
		private String qName = null;
		private String value = null;

		public DispatcherPairs(ConcurrentLinkedQueue<EWEntity> queue, String qName, String value) {
			super();
			this.queue = queue;
			this.qName = qName;
			this.value = value;
		}

		public ConcurrentLinkedQueue<EWEntity> getQueue() {
			return queue;
		}

		public String getqName() {
			return qName;
		}

		public String getValue() {
			return value;
		}
	}
	
	private static AsyncConsole s_instance = null;
	private static String s_initMethod = "init";
	private static int s_initialized_entities = 2;
	private List<DispatcherPairs> dispatchedQueues = new ArrayList<DispatcherPairs>();
	private Map<String, ConcurrentLinkedQueue<EWEntity>> availableQueues = new HashMap<String, ConcurrentLinkedQueue<EWEntity>>();
	private ConsoleInActivity cin = new ConsoleInActivity();
	private ConsoleOutActivity cout = new ConsoleOutActivity();
	
	private AsyncConsole() {
		
	}
	
	public static synchronized AsyncConsole instance() {
		if (s_instance != null) {
			return s_instance;
		}
		s_instance = new AsyncConsole();
		try {
			s_instance.init();
		} catch (Exception e) {
			s_instance = null;
		}
		return s_instance;
	}
	
	public static String getInitMethod() {
		return s_initMethod;
	}
	
	@Override
	public EWEntity invokeVW(String commandId, EWEntity commandArgs) {
		return null;
	}

	@Override
	public void activateVWCallback(IEW2VWMLGate gate) {
	}

	@Override
	public EWEntity invokeEW(String commandId, EWEntity commandArgs) {
		EWEntity e = null;
		if (commandId.equals(s_initMethod)) {
			dispatcherInit(commandArgs);
		}
		else
		if (commandId.equals(Console.getInMethod())) {
			String fromQ = null;
			if (commandArgs != null) {
				fromQ = (String)commandArgs.getId();
			}
			e = cin.read(fromQ);
		}
		else
		if (commandId.equals(Console.getOutMethod())) {
			cout.write(commandArgs);
		}		
		return e;
	}

	@Override
	public void init() throws Exception {
		cin.setGate(this);
		cout.setGate(this);
		cin.setDispatchedQueues(dispatchedQueues);
		cout.setDispatchedQueues(dispatchedQueues);
		cin.start();
		cout.start();
	}

	@Override
	public void done() throws Exception {
		cin.stop();
		cout.stop();
		dispatchedQueues.clear();
		availableQueues.clear();
	}

	@Override
	public void activateConfiguration(Properties props) throws Exception {
	}
	
	protected void dispatcherInit(EWEntity commandArgs) {
		if (commandArgs.isMarkedAsComplexEntity()) {
			for(int i = 0; i < commandArgs.getLink().getLinkedObjectsOnThisTime(); i++) {
				queueInitialization((EWEntity)commandArgs.getLink().getConcreteLinkedEntity(i));
			}
		}
	}
	
	protected void queueInitialization(EWEntity queueArgs) {
		if (queueArgs.isMarkedAsComplexEntity() && queueArgs.getLink().getLinkedObjectsOnThisTime() == s_initialized_entities) {
			EWEntity eQName = (EWEntity)queueArgs.getLink().getConcreteLinkedEntity(0);
			EWEntity eRightValues = (EWEntity)queueArgs.getLink().getConcreteLinkedEntity(1);
			if (eRightValues.isMarkedAsComplexEntity()) {
				ConcurrentLinkedQueue<EWEntity> queue = availableQueues.get((String)eQName.getId());
				if (queue == null) {
					queue = new ConcurrentLinkedQueue<EWEntity>();
					availableQueues.put((String)eQName.getId(), queue);
				}
				for(int i = 0; i < eRightValues.getLink().getLinkedObjectsOnThisTime(); i++) {
					EWEntity e = (EWEntity)eRightValues.getLink().getConcreteLinkedEntity(i);
					DispatcherPairs p = new DispatcherPairs(queue, (String)eQName.getId(), (String)e.getId());
					dispatchedQueues.add(p);
				}
			}
		}
	}
	
	protected ConcurrentLinkedQueue<EWEntity> getQueueByExpectedValue(String expectedValue) {
		ConcurrentLinkedQueue<EWEntity> q = null;
		for(DispatcherPairs pair : dispatchedQueues) {
			if (pair.getValue().equals(expectedValue)) {
				q = pair.getQueue();
			}
		}
		return q;
	}
	
	protected ConcurrentLinkedQueue<EWEntity> getQueueByName(String name) {
		ConcurrentLinkedQueue<EWEntity> q = null;
		for(DispatcherPairs pair : dispatchedQueues) {
			if (pair.getqName().equals(name)) {
				q = pair.getQueue();
			}
		}
		return q;
	}
	
}
