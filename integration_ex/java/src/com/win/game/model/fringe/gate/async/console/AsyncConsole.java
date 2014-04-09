package com.win.game.model.fringe.gate.async.console;

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
	}
	
	/**
	 * Reads from 'console'
	 * @author Oleg
	 *
	 */
	protected static class ConsoleInActivity extends ConsoleActivity implements Runnable {
		private ConcurrentLinkedQueue<EWEntity> in = new ConcurrentLinkedQueue<EWEntity>();

		public EWEntity read() {
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

	private static AsyncConsole s_instance = null;
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
		if (((String)commandId).equals(Console.getInMethod())) {
			e = cin.read();
		}
		else
		if (((String)commandId).equals(Console.getOutMethod())) {
			cout.write(commandArgs);
		}		
		return e;
	}

	@Override
	public void init() throws Exception {
		cin.start();
		cout.start();
	}

	@Override
	public void done() throws Exception {
		cin.stop();
		cout.stop();
	}

	@Override
	public void activateConfiguration(Properties props) throws Exception {
	}
}
