package com.vw.lang.sink.java;

import java.util.Map;

import com.vw.lang.sink.java.gate.VWMLGate;
import com.vw.lang.sink.java.interpreter.datastructure.resource.manager.VWMLResourceHostManagerFactory;


/**
 * Contains set of registred gates. Each live entity may register gate for communication purposes
 * @author Oleg
 *
 */
public class VWMLGatesRepository {
	private Map<String, VWMLGate> repo = null;

	public static VWMLGatesRepository instance() {
		return VWMLResourceHostManagerFactory.hostManagerInstance().requestGatesRepo();
	}
	
	/**
	 * Repository's initialization steps
	 */
	public void init() {
		repo = VWMLResourceHostManagerFactory.hostManagerInstance().requestGatesRepoContainer();
	}
	
	/**
	 * Repository's clearing steps
	 */
	public void done() {
		repo.clear();
		repo = null;
	}
	
	/**
	 * Registers gate
	 * @param name
	 * @param gate
	 */
	public void registerGate(String name, VWMLGate gate) {
		repo.put(name, gate);
	}
	
	/**
	 * Unregisters gate
	 * @param name
	 */
	public void unregisterGate(String name) {
		repo.remove(name);
	}
	
	public VWMLGate getGate(String name) {
		return repo.get(name);
	}
}
