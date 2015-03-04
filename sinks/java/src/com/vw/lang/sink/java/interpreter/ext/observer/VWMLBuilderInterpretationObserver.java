package com.vw.lang.sink.java.interpreter.ext.observer;

import org.apache.log4j.Logger;

import com.vw.lang.sink.java.entity.InterpretationObserver;
import com.vw.lang.sink.java.entity.VWMLEntity;

public class VWMLBuilderInterpretationObserver extends InterpretationObserver {

	private static VWMLBuilderInterpretationObserver instance = new VWMLBuilderInterpretationObserver();
	private static Logger logger = Logger.getLogger(VWMLBuilderInterpretationObserver.class);
	
	public static VWMLBuilderInterpretationObserver instance() {
		return instance;
	}
	
	@Override
	public void ambiguous(VWMLEntity interpreted, VWMLEntity interpreting) {
		logger.warn("ambiguous interpretation of '" + interpreted.getContext().getContext() + "." + interpreted.getId() + " -> " + "[" + interpreted.getInterpreting().getContext().getContext() + "." + interpreted.getInterpreting().getId() + "] / [" + interpreting.getContext().getContext() + "." + interpreting.getId() + "]");
	}

	@Override
	public void unresolvedAmbiguous(VWMLEntity interpreted, VWMLEntity interpreting) {
		logger.error("unresolved ambiguous interpretation of '" + interpreted.getContext().getContext() + "." + interpreted.getId() + " -> " + "[" + interpreted.getInterpreting().getContext().getContext() + "." + interpreted.getInterpreting().getId() + "] / [" + interpreting.getContext().getContext() + "." + interpreting.getId() + "]");
	}

	@Override
	public void resolvedAmbiguous() {
		logger.info("all unresolved ambiguous interpretations were resolved successfully");
	}
}
