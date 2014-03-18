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
	 * Returns last link object's uniq Id
	 * @return
	 */
	public Object getLastLinksUniqId();
	
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
	 * @param contexts
	 * @throws Exception
	 */
	public void markEntityAsTerm(Object id, String[] contexts) throws Exception;
	
	/**
	 * Marks entity as lifeterm; the entity had to added and marked as term before this method is called
	 * @param id (REL)
	 * @param asSource lifeterm is considered as source term
	 * @throws Exception
	 */
	public void markEntityAsLifeTerm(Object id, boolean asSource) throws Exception;
	
	/**
	 * Marks entity as lifeterm; the entity had to added and marked as term before this method is called
	 * @param id (REL)
	 * @param asSource lifeterm is considered as source term
	 * @param contexts  on which contexts it should be marked as lifeterm
	 * @throws Exception
	 */
	public void markEntityAsLifeTermOnContexts(Object id, boolean asSource, String[] contexts) throws Exception;
	
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
	 * @param contexts
	 */
	public boolean removeComplexEntityFromDeclarationAndLinkage(Object id, String[] contexts);
	
	/**
	 * Changes object's id from 'id' to 'idTo'
	 * @param id
	 * @param idTo
	 * @param contexts
	 */
	public void changeObjectIdTo(Object id, Object idTo, String[] contexts);
	
	/**
	 * Changes object's id from 'id' to 'idTo'
	 * @param id
	 * @param idTo
	 * @param contexts
	 */
	public void changeObjectIdToImmidiatly(Object id, Object idTo, String[] contexts);

	
	/**
	 * Changes object's id for declared objects only
	 * @param id
	 * @param idTo
	 */
	public void changeObjectIdToForDeclaredObjectsOnly(Object id, Object idTo, String[] contexts);	
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
	 * Links context and bunch to which context belongs
	 * @param contextId
	 * @param bunch
	 * @param bunch
	 */
	public void linkContextAndBunch(Object contextId, Object bunch);
	
	/**
	 * Links objects using their ids
	 * @param id
	 * @param linkedObjId
	 * @param linkingContext
	 * @param activeContext
	 * @param uniqId
	 */
	public void linkObjects(Object id, Object linkedObjId, String linkingContext, String activeContext, Object uniqId);
	
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
	 * @param linkingContext
	 * @param activeContext
	 */
	public void interpretObjects(Object id, Object interpretingObjId, String linkingContext, String activeContext);
	
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
	
	/**
	 * Starts definition of conflict on the ring
	 * @param conflictDefinitionName
	 */
	public void startConflictDefinitionOnRing(String conflictDefinitionName) throws Exception;
	
	/**
	 * Adds definition of conflict, on the ring, to the ring
	 * @param conflictDefinitionName
	 */
	public void addConflictDefinitionOnRing(String conflictDefinitionName) throws Exception;
	
	/**
	 * Ends definition of conflict on the ring
	 * @param conflictDefinitionName
	 */
	public void endConflictDefinitionOnRing() throws Exception;
}
