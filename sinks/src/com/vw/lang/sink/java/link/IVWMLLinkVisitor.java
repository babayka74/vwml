package com.vw.lang.sink.java.link;

import com.vw.lang.sink.java.VWMLObject;
import com.vw.lang.sink.java.operations.VWMLOperation;

/**
 * Used in debug purposes in order to visualize structure of objects' relations
 * @author ogibayev
 *
 */
public interface IVWMLLinkVisitor {
	
	/**
	 * Called upon visitor's schema start
	 * @param schemaName
	 */
	public void init(String schemaName, String schemaPath);
	
	/**
	 * Called upon visitor's schema end
	 * @param schemaName
	 */
	public void done(String schemaName);
	
	/**
	 * Called when objects are linked
	 * @param obj
	 * @param objLinked
	 */
	public void link(VWMLObject obj, VWMLObject objLinked);
	
	/**
	 * Called when objects are going to be unlinked
	 * @param obj
	 * @param objUnlinked
	 */
	public void unlink(VWMLObject obj, VWMLObject objUnlinked);
	
	/**
	 * Called when interpreting linkage is established between objects
	 * @param obj
	 * @param objUnlinked
	 */
	public void interpretObjectAs(VWMLObject obj, VWMLObject interpreting);
	
	/**
	 * Builds association between object and operation
	 * @param obj
	 * @param op
	 */
	public void associateOperation(VWMLObject obj, VWMLOperation op);
	
	/**
	 * Removes operation from object's association
	 * @param obj
	 * @param op
	 */
	public void removeOperationFromAssociation(VWMLObject obj, VWMLOperation op);
}
