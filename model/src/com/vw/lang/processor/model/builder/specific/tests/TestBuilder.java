package com.vw.lang.processor.model.builder.specific.tests;

import java.util.List;

import com.vw.lang.sink.java.entity.InterpretationObserver;
import com.vw.lang.sink.java.interpreter.ext.observer.VWMLBuilderInterpretationObserver;

public class TestBuilder {
	private InterpretationObserver interpretationObserver = null;
	private List<String> warningFlags;

	public List<String> getWarningFlags() {
		return warningFlags;
	}

	public void setWarningFlags(List<String> warningFlags) {
		this.warningFlags = warningFlags;
	}
	
	public boolean isWarnOn() {
		return (warningFlags != null && warningFlags.size() != 0);
	}

	public void buildInterpretationObserver() {
		if (isWarnOn()) {
			interpretationObserver = VWMLBuilderInterpretationObserver.instance();
		}
	}
	
	public InterpretationObserver getInterpretationObserver() {
		return interpretationObserver;
	}
}
