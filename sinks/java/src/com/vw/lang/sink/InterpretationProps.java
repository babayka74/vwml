package com.vw.lang.sink;

import java.util.Properties;

import com.vw.lang.sink.entity.InterpretationOfUndefinedEntityStrategyId;

/**
 * Static properties related to interpretation process (built during compilation phase and passed as arguments by user)
 * @author ogibayev
 *
 */
public class InterpretationProps {
	private String interpretersSrcPath;
	private String interpretersPackage;
	private InterpretationOfUndefinedEntityStrategyId interpretationOfUndefinedEntityStrategyId = InterpretationOfUndefinedEntityStrategyId.STRICT;
	private Properties dynamicProps = null;
	
	public InterpretationOfUndefinedEntityStrategyId getInterpretationOfUndefinedEntityStrategyId() {
		return interpretationOfUndefinedEntityStrategyId;
	}

	public void setInterpretationOfUndefinedEntityStrategyId(
			InterpretationOfUndefinedEntityStrategyId interpretationOfUndefinedEntityStrategyId) {
		this.interpretationOfUndefinedEntityStrategyId = interpretationOfUndefinedEntityStrategyId;
	}

	public String getInterpretersPackage() {
		return interpretersPackage;
	}

	public void setInterpretersPackage(String interpretersPackage) {
		this.interpretersPackage = interpretersPackage;
	}

	public String getInterpretersSrcPath() {
		return interpretersSrcPath;
	}

	public void setInterpretersSrcPath(String interpretersSrcPath) {
		this.interpretersSrcPath = interpretersSrcPath;
	}

	
	public void setDynamicProps(Properties dynamicProps) {
		this.dynamicProps = dynamicProps;
	}

	public String getDynamicPropsByName(String name) {
		return (dynamicProps != null) ? dynamicProps.getProperty(name) : null;
	}
	
	public Properties getDynamicProps() {
		return dynamicProps;
	}
	
	@Override
	public String toString() {
		return "InterpretationProps [interpretersSrcPath="
				+ interpretersSrcPath + ", interpretersPackage="
				+ interpretersPackage
				+ ", interpretationOfUndefinedEntityStrategyId="
				+ interpretationOfUndefinedEntityStrategyId + ", dynamicProps="
				+ dynamicProps + "]";
	}
}
