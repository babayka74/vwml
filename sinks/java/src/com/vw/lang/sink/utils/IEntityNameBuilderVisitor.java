package com.vw.lang.sink.utils;

import com.vw.lang.sink.java.VWMLObject;

/**
 * Used in order to extend functionality of name entity builder
 * @author Oleg
 *
 */
public interface IEntityNameBuilderVisitor {

	/**
	 * Forces delimiter's index which reflects tabbed position of entity in generated code
	 * @return
	 */
	public void forceStartingDelimIndex(int index);
	
	/**
	 * Returns delimiter's index which reflects tabbed position of entity in generated code
	 * @return
	 */
	public int getStartingDelimIndex();
	
	/**
	 * Injection on start, regardless what entity is created
	 * The injection is added just before ')'
	 * @return
	 */
	public String injectionOnStart(VWMLObject e);

	/**
	 * Injection on finish, regardless what entity has been created 
	 * The injection is added just after ')'
	 * @return
	 */
	public String injectionOnFinish(VWMLObject e);
	
	/**
	 * Injects string when parent entity is created, the injected string is added just after '('
	 * @return
	 */
	public String injectionOnParentStart(VWMLObject e);

	/**
	 * Injects string when parent entity has been created and built, the injected string is added just before ')'
	 * @return
	 */
	public String injectionOnParentFinish(VWMLObject e);

	/**
	 * Injects string when child entity is created, the injected string is added just after '('
	 * @return
	 */
	public String injectionOnChildStart(VWMLObject e);

	/**
	 * Injects string when child entity has been created and built, the injected string is added just before ')'
	 * @return
	 */
	public String injectionOnChildFinish(VWMLObject e);
}
