package com.vw.lang.sink.entity;

/**
 * Identifies processing logic of undefined entity
 * @author ogibayev
 *
 */
public enum InterpretationOfUndefinedEntityStrategyId {
	STRICT("strict"),
	UE_IM1("ue_im1"),
	UE_IM2("ue_im2"),
	UE_IM3("ue_im3");
	
	private final String value;

	private InterpretationOfUndefinedEntityStrategyId(String value) {
		this.value = value;
	}
	public static InterpretationOfUndefinedEntityStrategyId fromValue(String value) {
		if (value != null) {
			for(InterpretationOfUndefinedEntityStrategyId m : values()) {
				if (m.value.equals(value)) {
					return m;
				}
			}
		}
		return getDefault();
	}
	
	public String toValue() {
		return value;
	}
	
	public static InterpretationOfUndefinedEntityStrategyId getDefault() {
		return STRICT;
	}
	
	public static int numValues() {
		return values().length;
	}
	
	public static int index(String value) {
		int i = 0;
		if (value != null) {
			for(InterpretationOfUndefinedEntityStrategyId m : values()) {
				if (m.value.equals(value)) {
					return i;
				}
				i++;
			}
		}
		return i;
	}
	
	public static InterpretationOfUndefinedEntityStrategyId index(int value) {
		InterpretationOfUndefinedEntityStrategyId m = InterpretationOfUndefinedEntityStrategyId.STRICT;
		if (value < numValues()) {
			m = InterpretationOfUndefinedEntityStrategyId.values()[value];
		}
		return m;
	}
}
