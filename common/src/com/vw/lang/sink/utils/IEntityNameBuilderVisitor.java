package com.vw.lang.sink.utils;

/**
 * Used in order to extend functionality of name entity builder
 * @author Oleg
 *
 */
public interface IEntityNameBuilderVisitor {

	/**
	 * Injection on start, regardless what entity is created
	 * The injection is added just before ')'
	 * @return
	 */
	public String injectionOnStart();

	/**
	 * Injection on finish, regardless what entity has been created 
	 * The injection is added just after ')'
	 * @return
	 */
	public String injectionOnFinish();
	
	/**
	 * Injects string when parent entity is created, the injected string is added just after '('
	 * @return
	 */
	public String injectionOnParentStart();

	/**
	 * Injects string when parent entity has been created and built, the injected string is added just before ')'
	 * @return
	 */
	public String injectionOnParentFinish();

	/**
	 * Injects string when child entity is created, the injected string is added just after '('
	 * @return
	 */
	public String injectionOnChildStart();

	/**
	 * Injects string when child entity has been created and built, the injected string is added just before ')'
	 * @return
	 */
	public String injectionOnChildFinish();
}
