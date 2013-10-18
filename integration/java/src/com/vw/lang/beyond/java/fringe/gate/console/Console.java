package com.vw.lang.beyond.java.fringe.gate.console;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.vw.lang.beyond.java.fringe.entity.EWEntity;
import com.vw.lang.beyond.java.fringe.entity.EWEntityBuilder;
import com.vw.lang.beyond.java.fringe.gate.IVWMLGate;

/**
 * Console - used for communication between VWML and EW worlds
 * @author Oleg
 *
 */
public class Console implements IVWMLGate {

	private static String s_exportedInMethod = "read";
	private static String s_exportedOutMethod = "write";
	private static int READ  = 0x0;
	private static int WRITE = 0x1;
	
	private static String[] s_exportedMethods = {s_exportedInMethod, s_exportedOutMethod};
	
	private static Console s_instance = null;
	
	private Console() {
		s_exportedOutMethod.intern();
		s_exportedInMethod.intern();
	}
	
	public static synchronized Console instance() {
		if (s_instance != null) {
			return s_instance;
		}
		s_instance = new Console();
		try {
			s_instance.init();
		} catch (Exception e) {
			s_instance = null;
		}
		return s_instance;
	}
	
	@Override
	public void init() throws Exception {
	}

	@Override
	public void done() throws Exception {
	}
	
	@Override
	public EWEntity invokeVW(Object commandId, EWEntity commandArgs) {
		return null;
	}

	@Override
	public EWEntity invokeEW(Object commandId, EWEntity commandArgs) {
		EWEntity e = null;
		if (((String)commandId).intern() == s_exportedMethods[READ]) {
			String data = null;
			while (data == null) {
				data = read();
			}
			try {
				e = EWEntityBuilder.buildFromString(data);
			} catch (Exception ex) {
				System.err.println("Exception caught '" + e + "'");
			}
		}
		else
		if (((String)commandId).intern() == s_exportedMethods[WRITE]) {
			write(commandArgs.getReadableId());
		}
		return e;
	}

	/**
	 * Simple outputs data
	 * @param data
	 */
	protected void write(String data) {
		System.out.println(data);
	}
	
	/**
	 * Reads string from command line
	 * @return
	 * @throws Exception
	 */
	protected String read() {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			return br.readLine();
		} catch (IOException e) {
			return null;
		}
	}
}
