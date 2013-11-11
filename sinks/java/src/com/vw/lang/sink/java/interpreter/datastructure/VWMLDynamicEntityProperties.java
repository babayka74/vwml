package com.vw.lang.sink.java.interpreter.datastructure;

/**
 * Entity's properties which are set during interpretation process
 * @author Oleg
 *
 */
public class VWMLDynamicEntityProperties {
	// set when operation is added to entity, but initially entity wasn't marked as term
	private boolean isMarkedAsArtificalTerm = false;
	// marked by interpreter in case when operation EXE is being applied
	private boolean isOperatesByExe = false;

	public boolean isMarkedAsArtificalTerm() {
		return isMarkedAsArtificalTerm;
	}
	
	public void setMarkedAsArtificalTerm(boolean isMarkedAsArtificalTerm) {
		this.isMarkedAsArtificalTerm = isMarkedAsArtificalTerm;
	}
	
	public boolean isOperatesByExe() {
		return isOperatesByExe;
	}
	
	public void setOperatesByExe(boolean isOperatesByExe) {
		this.isOperatesByExe = isOperatesByExe;
	}
}
