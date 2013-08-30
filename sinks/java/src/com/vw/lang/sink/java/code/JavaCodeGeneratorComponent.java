package com.vw.lang.sink.java.code;

import java.io.FileWriter;

/**
 * Java code generator's root component
 * @author ogibayev
 *
 */
public class JavaCodeGeneratorComponent {
	private FileWriter fw = null;

	public JavaCodeGeneratorComponent() {
		super();
	}

	public JavaCodeGeneratorComponent(FileWriter fw) {
		super();
		this.fw = fw;
	}

	public FileWriter getFw() {
		return fw;
	}

	public void setFw(FileWriter fw) {
		this.fw = fw;
	}
	
}
