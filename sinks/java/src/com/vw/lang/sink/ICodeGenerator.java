package com.vw.lang.sink;

import com.vw.lang.sink.java.link.IVWMLLinkVisitor;

/**
 * Root class for all code generators
 * @author ogibayev
 *
 */
public interface ICodeGenerator {

	/**
	 * Placeholder for module's properties
	 * @author ogibayev
	 *
	 */
	public static class StartModuleProps {
		private ICodeGenerator codeGenerator = null;

		public ICodeGenerator getCodeGenerator() {
			return codeGenerator;
		}

		public void setCodeGenerator(ICodeGenerator codeGenerator) {
			this.codeGenerator = codeGenerator;
		}
	}
	
	/**
	 * Builds module's properties instance
	 * @return
	 */
	public StartModuleProps buildProps();
	
	/**
	 * Returns source's path
	 * @param props
	 * @return
	 */
	public String getSourcePath(StartModuleProps props);
	
	/**
	 * Called by VWMLProcessor when new software module is generated
	 * @param props
	 */
	public void startModule(StartModuleProps props) throws Exception;
	
	/**
	 * Actually generates module's code
	 * @param modProps
	 */
	public void generate(StartModuleProps props) throws Exception;

	/**
	 * Finishes generation of module
	 * @param props
	 * @throws Exception
	 */
	public void finishModule(StartModuleProps props) throws Exception;

	/**
	 * Declares simple entity
	 * @param id
	 * @throws Exception
	 */
	public void declareSimpleEntity(Object id) throws Exception;
	
	/**
	 * Declares complex entity
	 * @param id
	 * @throws Exception
	 */
	public void declareComplexEntity(Object id) throws Exception;
	
	/**
	 * Declares term
	 * @param id
	 * @throws Exception
	 */
	public void declareTerm(Object id) throws Exception;
	
	/**
	 * Links objects using their ids
	 * @param id
	 * @param linkedObjId
	 */
	public void linkObjects(Object id, Object linkedObjId);
	
	/**
	 * Builds association between object and operation
	 * @param id
	 * @param op
	 */
	public void associateOperation(Object id, String op);
	
	/**
	 * Set interpreting link between objects
	 * @param id
	 * @param interpretingObjId
	 */
	public void interpretObjects(Object id, Object interpretingObjId);
	
	/**
	 * Returns associated visitor; see {@link setVisitor}
	 * @return
	 */
	public IVWMLLinkVisitor getVisitor();

	/**
	 * Associates visitor with code generator
	 * @param visitor
	 */
	public void setVisitor(IVWMLLinkVisitor visitor);
	
	/**
	 * Returns language's name as string
	 * @return
	 */
	public String getLangAsString();
}
