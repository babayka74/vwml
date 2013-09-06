package com.vw.lang.sink;

import com.vw.lang.sink.entity.InterpretationOfUndefinedEntityStrategyId;
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
		private String actualModuleName = null;
		private InterpretationProps interpretationProps = null;
		private static String s_sourcesPath = null;

		public ICodeGenerator getCodeGenerator() {
			return codeGenerator;
		}

		public void setCodeGenerator(ICodeGenerator codeGenerator) {
			this.codeGenerator = codeGenerator;
		}

		public String getActualModuleName() {
			return actualModuleName;
		}

		public void setActualModuleName(String actualModuleName) {
			this.actualModuleName = actualModuleName;
		}

		public InterpretationProps getInterpretationProps() {
			return interpretationProps;
		}

		public void setInterpretationProps(InterpretationProps interpretationProps) {
			this.interpretationProps = interpretationProps;
		}

		public static String getSourcesPath() {
			return s_sourcesPath;
		}

		public static void setSourcesPath(String sourcesPath) {
			StartModuleProps.s_sourcesPath = sourcesPath;
		}
	}
	
	/**
	 * Returns last link object
	 * @return
	 */
	public Object getLastLink();
	
	/**
	 * Builds module's properties instance
	 * @return
	 */
	public StartModuleProps buildProps();
	
	/**
	 * Normalizes properties by setting non-set values by project properties values
	 * @param props
	 * @param projectProps
	 * @return
	 */
	public StartModuleProps normalizeProps(StartModuleProps props, StartModuleProps projectProps);
	
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
	 * Marks entity as term
	 * @param id
	 * @throws Exception
	 */
	public void markEntityAsTerm(Object id) throws Exception;
	
	/**
	 * Marks entity as lifeterm; the entity had to added and marked as term before this method is called
	 * @param id
	 * @throws Exception
	 */
	public void markEntityAsLifeTerm(Object id) throws Exception;	
	
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
