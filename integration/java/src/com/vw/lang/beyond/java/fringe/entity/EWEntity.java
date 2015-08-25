package com.vw.lang.beyond.java.fringe.entity;

/**
 * EW's base entity (see language specification)
 * @author ogibayev
 *
 */
public class EWEntity extends EWObject {

	public static final String s_NilEntityId       = "nil";
	public static final String s_EmptyEntityId     = "()";
	public static final String s_doNothingEntityId = "doNothing";
	public static final String s_doNothingEntityId2 = "DoNothing";
	public static final String s_doNothingEntityId3 = "donothing";	
	public static final String s_trueEntityId      = "true";
	public static final String s_falseEntityId     = "false";
	
	private String context;
	protected boolean isMarkedAsComplexEntity = false;
	protected boolean regeneratable = false;

	public static boolean isNilEntity(EWEntity e) {
		return e.getId().equals(s_NilEntityId);
	}

	public static boolean isTrueEntity(EWEntity e) {
		return e.getId().equals(s_trueEntityId);
	}

	public static boolean isFalseEntity(EWEntity e) {
		return e.getId().equals(s_falseEntityId);
	}

	public static boolean isEmptyEntity(EWEntity e) {
		return e.getId().equals(s_EmptyEntityId);
	}
	
	public EWEntity() {
		super();
	}

	public EWEntity(Object id, String readableId) {
		super(id, readableId);
	}

	public boolean isMarkedAsComplexEntity() {
		return isMarkedAsComplexEntity;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}
	
	public void release() {
		EWEntityBuilder.returnToPool(this);
	}

	public boolean isRegeneratable() {
		return regeneratable;
	}

	public void setRegeneratable(boolean regeneratable) {
		this.regeneratable = regeneratable;
	}
}
