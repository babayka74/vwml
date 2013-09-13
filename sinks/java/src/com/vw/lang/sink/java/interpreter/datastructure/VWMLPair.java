package com.vw.lang.sink.java.interpreter.datastructure;

/**
 * Dynamic association name => value 
 * @author ogibayev
 *
 */
public class VWMLPair {
	private String name;
	private String value;
	
	public VWMLPair() {
		super();
	}

	public VWMLPair(String name, String value) {
		super();
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getValue() {
		return value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "VWMLPair [name=" + name + ", value=" + value + "]";
	}
}
