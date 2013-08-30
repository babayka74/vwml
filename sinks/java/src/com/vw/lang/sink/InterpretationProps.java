package com.vw.lang.sink;

import com.vw.lang.sink.entity.InterpretationOfUndefinedEntityStrategyId;

/**
 * Static properties related to interpretation process
 * @author ogibayev
 *
 */
public class InterpretationProps {
	private InterpretationOfUndefinedEntityStrategyId interpretationOfUndefinedEntityStrategyId = InterpretationOfUndefinedEntityStrategyId.STRICT;

	public InterpretationOfUndefinedEntityStrategyId getInterpretationOfUndefinedEntityStrategyId() {
		return interpretationOfUndefinedEntityStrategyId;
	}

	public void setInterpretationOfUndefinedEntityStrategyId(
			InterpretationOfUndefinedEntityStrategyId interpretationOfUndefinedEntityStrategyId) {
		this.interpretationOfUndefinedEntityStrategyId = interpretationOfUndefinedEntityStrategyId;
	}
}
