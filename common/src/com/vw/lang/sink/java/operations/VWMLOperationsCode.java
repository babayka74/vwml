package com.vw.lang.sink.java.operations;

/**
 * VWML's codes of operations
 * @author ogibayev
 *
 */
public enum VWMLOperationsCode {
    OPJOIN("Join"),
    OPINTERSECT("Intersect"),
    OPSUBSTRUCT("Substruct"),
    OPFIRST("First"),
    OPLAST("Last"),
    OPBEGIN("Begin"),
    OPREST("Rest"),
    OPCARTESIAN("Cartesian"),
    OPRANDOM("Random"),
    OPIN("In"),
    OPINCL("Include"),
    OPEQ("Eq"),
    OPIDENT("Ident"),
    OPSQU("Squeeze"),
    OPINTERPRET("~"),
    OPACTIVATECTX(":"),
    OPCREATEEXPR("^"),
    OPEXECUTE("Exe"),
    OPDO("Do"),
    OPRELAX("Relax"),
    OPCONFLICTSITUATIONSTART("["),
    OPCONFLICTSITUATIONEND("]"),
    OPBREAKPOINT("Bp"),
    OPAPPLYTOCONTEXT("Context"),
    OPCLONE("Clone"),
    OPBORN("Born"),
    OPPROJECTION("Projection"),
    OPFOREACH("ForEach"),
    OPNOP("Nop"),
    OPDYNCONTEXT("->"),
    OPSIZE("Size"),
    OPINTERRUPT("Interrupt"),
    OPCALLP("CallP"),
    // special implicit operations
    OPIMPLICITASSEMBLE("__assemble__");    
    
	private final String value;
	
    VWMLOperationsCode(String value) {
		this.value = value;
	}
	
	public static VWMLOperationsCode fromValue(String value) {
		if (value != null) {
			for(VWMLOperationsCode o : values()) {
				if (o.value.equals(value)) {
					return o;
				}
			}
		}
		return getDefault();
	}
	
	public String toValue() {
		return value;
	}
	
	public static VWMLOperationsCode getDefault() {
		return OPNOP;
	}
	
	public static int numValues() {
		return values().length;
	}
	
	public static int index(String value) {
		int i = 0;
		if (value != null) {
			for(VWMLOperationsCode o : values()) {
				if (o.value.equals(value)) {
					return i;
				}
				i++;
			}
		}
		return i;
	}
	
	public static VWMLOperationsCode index(int value) {
		VWMLOperationsCode o = VWMLOperationsCode.OPJOIN;
		if (value < numValues()) {
			o = VWMLOperationsCode.values()[value];
		}
		return o;
	}
}
