package com.vw.lang.sink.java.link;

import com.vw.lang.sink.IVWMLLinkVisitor;
import com.vw.lang.sink.java.VWMLObject;
import com.vw.lang.sink.java.operations.VWMLOperation;

/**
 * Used in debug purposes in order to visualize structure of objects' relations
 * @author ogibayev
 *
 */
public abstract class AbstractVWMLLinkVisitor implements IVWMLLinkVisitor {
	// source generation phase, runs when data structure's consistency is checked
	public static int BUILD_PHASE   = 0x00;
	// entity's mutations are propagated
	public static int RUNTIME_PHASE = 0x01;
	
	private int phase = BUILD_PHASE;
	
	public int getPhase() {
		return phase;
	}

	public void setPhase(int phase) {
		this.phase = phase;
	}

	/**
	 * Called upon visitor's schema start
	 * @param schemaName
	 */
	public abstract void init(String schemaName, String schemaPath);
	
	/**
	 * Called upon visitor's schema end
	 * @param schemaName
	 */
	public abstract void done(String schemaName);
	
	/**
	 * Called when objects are linked
	 * @param obj
	 * @param objLinked
	 */
	public abstract void link(VWMLObject obj, VWMLObject objLinked);
	
	/**
	 * Called when objects are going to be unlinked
	 * @param obj
	 * @param objUnlinked
	 */
	public abstract void unlink(VWMLObject obj, VWMLObject objUnlinked);
	
	/**
	 * Called when interpreting linkage is established between objects
	 * @param obj
	 * @param objUnlinked
	 */
	public abstract void interpretObjectAs(VWMLObject obj, VWMLObject interpreting);
	
	/**
	 * Builds association between object and operation
	 * @param obj
	 * @param op
	 */
	public abstract void associateOperation(VWMLObject obj, VWMLOperation op);
	
	/**
	 * Removes operation from object's association
	 * @param obj
	 * @param op
	 */
	public abstract void removeOperationFromAssociation(VWMLObject obj, VWMLOperation op);
}
