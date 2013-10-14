package com.vw.lang.beyond.java.fringe.gate.console;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.vw.lang.beyond.java.fringe.EWEntity;
import com.vw.lang.beyond.java.fringe.EWEntityBuilder;
import com.vw.lang.beyond.java.fringe.gate.IEW2VWMLGate;
import com.vw.lang.beyond.java.fringe.gate.IVWML2EWGate;

/**
 * Console - used for communication between VWML and EW worlds
 * @author Oleg
 *
 */
public class Console implements IVWML2EWGate, IEW2VWMLGate {

	private static String s_exportedInMethod = "read";
	private static String s_exportedOutMethod = "write";
	
	private static String[] s_exportedMethods = {s_exportedInMethod, s_exportedOutMethod};
	
	public Console() {
		s_exportedOutMethod.intern();
		s_exportedInMethod.intern();
	}
	
	@Override
	public EWEntity invokeVW(Object commandId, EWEntity commandArgs) {
		return null;
	}

	@Override
	public EWEntity invokeEW(Object commandId, EWEntity commandArgs) {
		EWEntity e = null;
		for(String method : s_exportedMethods) {
			if (method.intern() == s_exportedOutMethod) {
				write(commandArgs.getReadableId());
				break;
			}
			else
			if (method.intern() == s_exportedInMethod) {
				String data = null;
				while (data == null) {
					data = read();
				}
				try {
					e = EWEntityBuilder.buildFromString(data);
				} catch (Exception ex) {
					System.err.println("Exception caught '" + e + "'");
				}
				break;
			}
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
