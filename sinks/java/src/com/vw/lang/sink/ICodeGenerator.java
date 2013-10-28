package com.vw.lang.sink;

import java.util.Properties;

import com.vw.lang.sink.java.link.AbstractVWMLLinkVisitor;

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
		private Properties dynamicProps = new Properties();
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

		public void addProperty(String name, String value) {
			dynamicProps.put(name, value);
		}
		
		public String getProperty(String name) {
			return dynamicProps.getProperty(name);
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
	 * @param context
	 * @throws Exception
	 */
	public void declareSimpleEntity(Object id, String context) throws Exception;
	
	/**
	 * Declares complex entity; the object id is compound object; consists from set of simple entity ids
	 * @param id (ID)
	 * @param readableId (ID)
	 * @param context
	 * @throws Exception
	 */
	public void declareComplexEntity(Object id, Object readableId, String context) throws Exception;
	
	/**
	 * Declares creature; special entity which lives on intersection of worlds
	 * @param id (ID)
	 * @param props (STRING)
	 * @param context
	 * @throws Exception
	 */
	public void declareCreature(Object id, Object props, String context) throws Exception;
	
	/**
	 * Removes last declared complex entity; complex context detected
	 * @param id (REL)
	 */
	public boolean removeComplexEntityFromDeclarationAndLinkage(Object id);
	
	/**
	 * Changes object's id from 'id' to 'idTo'
	 * @param id
	 * @param idTo
	 */
	public void changeObjectIdTo(Object id, Object idTo);
	
	/**
	 * Changes object's id from 'id' to 'idTo'
	 * @param id
	 * @param idTo
	 */
	public void changeObjectIdToImmidiatly(Object id, Object idTo);

	
	/**
	 * Changes object's id for declared objects only
	 * @param id
	 * @param idTo
	 */
	public void changeObjectIdToForDeclaredObjectsOnly(Object id, Object idTo);	
	/**
	 * Declares term
	 * @param id
	 * @param context
	 * @throws Exception
	 */
	public void declareTerm(Object id, String context) throws Exception;
	
	/**
	 * Declares context; it is used during code generation phase; all entities should be linked with contexts
	 * @param contextId
	 */
	public void declareContext(Object contextId);
	
	/**
	 * Links objects using their ids
	 * @param id
	 * @param linkedObjId
	 * @param activeContext
	 */
	public void linkObjects(Object id, Object linkedObjId, String activeContext);
	
	/**
	 * Builds association between object and operation
	 * @param id
	 * @param op
	 * @param activeContext
	 */
	public void associateOperation(Object id, String op, String activeContext);
	
	/**
	 * Set interpreting link between objects
	 * @param id
	 * @param interpretingObjId
	 * @param activeContext
	 */
	public void interpretObjects(Object id, Object interpretingObjId, String activeContext);
	
	/**
	 * Returns associated visitor; see {@link setVisitor}
	 * @return
	 */
	public AbstractVWMLLinkVisitor getVisitor();

	/**
	 * Associates visitor with code generator
	 * @param visitor
	 */
	public void setVisitor(AbstractVWMLLinkVisitor visitor);
	
	/**
	 * Returns language's name as string
	 * @return
	 */
	public String getLangAsString();
}
