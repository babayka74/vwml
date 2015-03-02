package com.vw.lang.processor.model.builder.specific.tests;

import java.util.List;

import com.vw.lang.sink.java.entity.InterpretationObserver;
import com.vw.lang.sink.java.interpreter.ext.observer.VWMLBuilderInterpretationObserver;

public class TestBuilder {
	private static final String ambiguousInterpretationFlag = "ambiguousInterpretation";
	
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
			for(String flag : warningFlags) {
				if (flag.equals(ambiguousInterpretationFlag)) {
					interpretationObserver.setAmbiguosOn(true);
				}
			}
		}
	}
	
	public InterpretationObserver getInterpretationObserver() {
		return interpretationObserver;
	}
}
