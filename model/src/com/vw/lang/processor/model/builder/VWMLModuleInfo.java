package com.vw.lang.processor.model.builder;

import com.vw.lang.sink.ICodeGenerator.StartModuleProps;

/**
 * Encapsulates module properties and test cases
 * @author ogibayev
 *
 */
public class VWMLModuleInfo {
	private StartModuleProps props;
	private String testCode;
	
	public VWMLModuleInfo() {
		super();
	}

	public VWMLModuleInfo(StartModuleProps props, String testCode) {
		super();
		this.props = props;
		this.testCode = testCode;
	}
	
	public static VWMLModuleInfo build(StartModuleProps props, String testCode) {
		return new VWMLModuleInfo(props, testCode);
	}
	
	public StartModuleProps getProps() {
		return props;
	}
	
	public void setProps(StartModuleProps props) {
		this.props = props;
	}
	
	public String getTestCode() {
		return testCode;
	}
	
	public void setTestCode(String testCode) {
		this.testCode = testCode;
	}

	@Override
	public String toString() {
		return "ModuleInfo [props=" + props + ", testCode=" + testCode
				+ "]";
	}
}
