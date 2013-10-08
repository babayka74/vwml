package com.vw.lang.sink.java.code;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.log4j.Logger;

import com.vw.lang.sink.ICodeGenerator;
import com.vw.lang.sink.java.VWMLJavaExportUtils;
import com.vw.lang.sink.java.VWMLObject;
import com.vw.lang.sink.java.VWMLObjectBuilder;
import com.vw.lang.sink.java.VWMLObjectsRepository;
import com.vw.lang.sink.java.code.linkage.JavaCodeGeneratorLinkage;
import com.vw.lang.sink.java.code.module.JavaCodeGeneratorModule;
import com.vw.lang.sink.java.code.repository.JavaCodeGeneratorRepository;
import com.vw.lang.sink.java.code.utils.JavaCodeGeneratorUtils;
import com.vw.lang.sink.java.link.AbstractVWMLLinkVisitor;
import com.vw.lang.sink.utils.EntityWalker;

/**
 * Used by VWMLProcessor for code generation
 * @author ogibayev
 *
 */
public class JavaCodeGenerator implements ICodeGenerator {
	
	public static class JavaModuleStartProps extends StartModuleProps {
		private String srcPath;
		private String commonPackage;
		private String moduleName;
		private String modulePackage;
		private String author;
		private String projectName;
		private String description;
		private String entityHistorySize;
		private String date;
		private AbstractVWMLLinkVisitor visitor;
		private String visitorDataPath;
		
		public JavaModuleStartProps() {
			super();
		}

		public JavaModuleStartProps(String srcPath, String commonPackage,
				String moduleName, String modulePackage, String author, String projectName,
				String description, String entityHistorySize, String date, AbstractVWMLLinkVisitor visitor,
				String visitorDataPath) {
			super();
			this.srcPath = srcPath;
			this.commonPackage = commonPackage;
			this.moduleName = moduleName;
			this.modulePackage = modulePackage;
			this.author = author;
			this.projectName = projectName;
			this.description = description;
			this.entityHistorySize = entityHistorySize;
			this.date = date;
			this.visitor = visitor;
			this.visitorDataPath = visitorDataPath;
		}

		public String getSrcPath() {
			return srcPath;
		}

		public void setSrcPath(String srcPath) {
			this.srcPath = srcPath;
		}

		public String getCommonPackage() {
			return commonPackage;
		}

		public void setCommonPackage(String commonPackage) {
			this.commonPackage = commonPackage;
		}

		public String getModuleName() {
			return moduleName;
		}

		public void setModuleName(String moduleName) {
			this.moduleName = moduleName;
		}

		public String getModulePackage() {
			return modulePackage;
		}

		public void setModulePackage(String modulePackage) {
			this.modulePackage = modulePackage;
		}

		public String getAuthor() {
			return author;
		}

		public void setAuthor(String author) {
			this.author = author;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public String getDate() {
			return date;
		}

		public void setDate(String date) {
			this.date = date;
		}

		public AbstractVWMLLinkVisitor getVisitor() {
			return visitor;
		}

		public void setVisitor(AbstractVWMLLinkVisitor visitor) {
			this.visitor = visitor;
		}

		public String getVisitorDataPath() {
			return visitorDataPath;
		}

		public void setVisitorDataPath(String visitorDataPath) {
			this.visitorDataPath = visitorDataPath;
		}

		public String getProjectName() {
			return projectName;
		}

		public void setProjectName(String projectName) {
			this.projectName = projectName;
		}

		public String getEntityHistorySize() {
			return entityHistorySize;
		}

		public void setEntityHistorySize(String entityHistorySize) {
			this.entityHistorySize = entityHistorySize;
		}

		@Override
		public String toString() {
			return "JavaModuleStartProps [srcPath=" + srcPath
					+ ", commonPackage=" + commonPackage + ", moduleName="
					+ moduleName + ", modulePackage=" + modulePackage
					+ ", author=" + author + ", projectName=" + projectName
					+ ", description=" + description + ", entityHistorySize="
					+ entityHistorySize + ", date=" + date + ", visitor="
					+ visitor + ", visitorDataPath=" + visitorDataPath + "]";
		}
	}
	
	public static enum ModuleFiles {
		MODULE("Module"),
		REPOSITORY("Repository"),
		LINKAGE("Linkage");
		
		private final String value;
		
		ModuleFiles(String value) {
			this.value = value;
		}
		
		public static ModuleFiles fromValue(String value) {
			if (value != null) {
				for(ModuleFiles m : values()) {
					if (m.value.equals(value)) {
						return m;
					}
				}
			}
			return getDefault();
		}
		
		public String toValue() {
			return value;
		}
		
		public static ModuleFiles getDefault() {
			return MODULE;
		}
		
		public static int numValues() {
			return values().length;
		}
		
		public static int index(String value) {
			int i = 0;
			if (value != null) {
				for(ModuleFiles m : values()) {
					if (m.value.equals(value)) {
						return i;
					}
					i++;
				}
			}
			return i;
		}
		
		public static ModuleFiles index(int value) {
			ModuleFiles m = ModuleFiles.MODULE;
			if (value < numValues()) {
				m = ModuleFiles.values()[value];
			}
			return m;
		}
	};
	
	/**
	 * Simple wrapper for VWMLObject class; used during code generation
	 * @author ogibayev
	 *
	 */
	public static class VWMLObjWrap {
		private VWMLObjectBuilder.VWMLObjectType type;
		private Object objId;
		private Object readableId;
		private String context;

		public VWMLObjWrap(VWMLObjectBuilder.VWMLObjectType type, Object objId, String context) {
			super();
			this.type = type;
			this.objId = objId;
			this.context = context;
		}

		public VWMLObjWrap(VWMLObjectBuilder.VWMLObjectType type, Object objId, Object readableId, String context) {
			super();
			this.type = type;
			this.objId = objId;
			this.readableId = readableId;
			this.context = context;
		}
	
		public Object getObjId() {
			return objId;
		}

		public void setObjId(Object objId) {
			this.objId = objId;
		}
	
		public VWMLObjectBuilder.VWMLObjectType getType() {
			return type;
		}

		public void setReadableId(Object readableId) {
			this.readableId = readableId;
		}

		public Object getReadableId() {
			return readableId;
		}

		public String getContext() {
			return context;
		}
	}
	
	/**
	 * Describes type of association between two entities (the type can be link or association)
	 * @author ogibayev
	 *
	 */
	public static class VWMLLinkWrap {
		private Object id;
		private Object linkedId;
		private boolean asTerm = false;
		private boolean asLifeTerm = false;
		private String uniqId = "VWMLLINK_" + UUID.randomUUID().toString();
		private String[] contextPath = null; // associates with linkedId (its concrete context)
		private String activeContext = null; // active context - CE's context
		
		public VWMLLinkWrap(Object id, Object linkedId) {
			super();
			this.id = id;
			this.linkedId = linkedId;
			parseContextPath();
		}

		public VWMLLinkWrap(Object id, Object linkedId, boolean asTerm) {
			super();
			this.id = id;
			this.linkedId = linkedId;
			this.asTerm = asTerm;
			parseContextPath();
		}

		public VWMLLinkWrap(Object id, Object linkedId, boolean asTerm, boolean asLifeTerm) {
			super();
			this.id = id;
			this.linkedId = linkedId;
			this.asTerm = asTerm;
			this.asLifeTerm = asLifeTerm;
			parseContextPath();
		}
		
		public Object getId() {
			return id;
		}

		public void setId(Object id) {
			this.id = id;
		}

		public Object getLinkedId() {
			return linkedId;
		}
		
		public void setLinkedId(Object linkedId) {
			this.linkedId = linkedId;
		}

		public void setUniqId(String uniqId) {
			this.uniqId = uniqId;
		}

		public boolean isAsTerm() {
			return asTerm;
		}

		public void setAsTerm(boolean asTerm) {
			this.asTerm = asTerm;
		}

		public boolean isAsLifeTerm() {
			return asLifeTerm;
		}

		public void setAsLifeTerm(boolean asLifeTerm) {
			this.asLifeTerm = asLifeTerm;
		}

		public String getUniqId() {
			return uniqId;
		}

		public String[] getContextPath() {
			return contextPath;
		}
		
		public String getActiveContext() {
			return activeContext;
		}

		public void setActiveContext(String activeContext) {
			this.activeContext = activeContext;
		}

		@Override
		public String toString() {
			return "VWMLLinkWrap [id=" + id + ", linkedId=" + linkedId
					+ ", asTerm=" + asTerm + ", asLifeTerm=" + asLifeTerm
					+ ", uniqId=" + uniqId + ", contextPath="
					+ Arrays.toString(contextPath) + ", activeContext="
					+ activeContext + "]";
		}
		
		protected void parseContextPath() {
			String[] contextPath =  VWMLJavaExportUtils.parseContext((String)linkedId);
			if (contextPath != null && contextPath.length > 1) {
				this.contextPath = contextPath;
			}
		}
	}
	
	/**
	 * Links list of operations with entity, actually creates 'term'
	 * @author ogibayev
	 *
	 */
	public static class VWMLOperationLink {
		private Object entityId;
		private Object linkId;
		private List<String> associatedOperations;
		private EntityWalker.REL rel = EntityWalker.REL.NONE;
		
		public VWMLOperationLink() {
			super();
		}

		public VWMLOperationLink(Object entityId, Object linkId, List<String> associatedOperations, EntityWalker.REL rel) {
			super();
			this.entityId = entityId;
			this.linkId = linkId;
			this.associatedOperations = associatedOperations;
			this.rel = rel;
		}

		public Object getEntityId() {
			return entityId;
		}

		public void setEntityId(Object entityId) {
			this.entityId = entityId;
		}

		public Object getLinkId() {
			return linkId;
		}

		public EntityWalker.REL getRel() {
			return rel;
		}

		public void setLinkId(Object linkId) {
			this.linkId = linkId;
		}

		public List<String> getAssociatedOperations() {
			return associatedOperations;
		}

		public void setAssociatedOperations(List<String> associatedOperations) {
			this.associatedOperations = associatedOperations;
		}
	}
	
	// set of writers, for each file type
	private FileWriter fws[] = new FileWriter[ModuleFiles.numValues()];
	// used for debug purposes in order to visualize objects' linkage
	private AbstractVWMLLinkVisitor visitor = null;
	// declared VWML objects (simple entity, complex entity and terms)
	private List<VWMLObjWrap> declaredObjects = new ArrayList<VWMLObjWrap>();
	// declared contexts
	private List<VWMLObjWrap> declaredContexts = new ArrayList<VWMLObjWrap>();
	// declared creatures
	private List<VWMLObjWrap> declaredCreatures = new ArrayList<VWMLObjWrap>();
	// contains list of entity ids which were marked as 'term'
	private List<Object> markedAsTerm = new ArrayList<Object>();
	// defines objects' linkage
	private List<VWMLLinkWrap> linkage = new ArrayList<VWMLLinkWrap>();
	// defines objects' interpretation linkage (special type of linkage, one object is interpreted as second)
	private List<VWMLLinkWrap> interpret = new ArrayList<VWMLLinkWrap>();
	// associates object and operations
	private Map<Object, VWMLOperationLink> operations = new HashMap<Object, VWMLOperationLink>();
	// objects Id translation
	private Map<Object, Object> idTranslationMap = new HashMap<Object, Object>();
	
	private VWMLLinkWrap lastLink = null;
	
	private Logger logger = Logger.getLogger(JavaCodeGenerator.class);
	
	public static JavaCodeGenerator instance() {
		return new JavaCodeGenerator();
	}
	
	public Object getLastLink() {
		return lastLink;
	}

	/**
	 * Builds module's properties instance
	 * @return
	 */
	public StartModuleProps buildProps() {
		JavaModuleStartProps props = new JavaModuleStartProps();
		props.setCodeGenerator(this);
		return props;
	}
	
	/**
	 * Normalizes properties by setting non-set values by project properties values
	 * @param props
	 * @param projectProps
	 * @return
	 */
	public StartModuleProps normalizeProps(StartModuleProps props, StartModuleProps projectProps) {
		StartModuleProps normalizedProps = props;
		if (normalizedProps == null) {
			normalizedProps = projectProps;
		}
		else {
			JavaModuleStartProps javaModNormalizedProps = (JavaModuleStartProps)normalizedProps;
			JavaModuleStartProps javaProjProps = (JavaModuleStartProps)projectProps;
			if (javaModNormalizedProps.getAuthor() == null) {
				javaModNormalizedProps.setAuthor(javaProjProps.getAuthor());
			}
			if (javaModNormalizedProps.getDescription() == null) {
				javaModNormalizedProps.setDescription(javaProjProps.getDescription());
			}
			if (javaModNormalizedProps.getDate() == null) {
				javaModNormalizedProps.setDate(javaProjProps.getDate());
			}
			if (javaModNormalizedProps.getSrcPath() == null) {
				javaModNormalizedProps.setSrcPath(javaProjProps.getSrcPath());
			}
			if (javaModNormalizedProps.getCommonPackage() == null) {
				javaModNormalizedProps.setCommonPackage(javaProjProps.getCommonPackage());
			}
			if (javaModNormalizedProps.getModulePackage() == null) {
				javaModNormalizedProps.setModulePackage(javaProjProps.getModulePackage());
			}
			if (javaModNormalizedProps.getVisitor() == null) {
				javaModNormalizedProps.setVisitor(javaProjProps.getVisitor());
			}
			if (javaModNormalizedProps.getVisitorDataPath() == null) {
				javaModNormalizedProps.setVisitorDataPath(javaProjProps.getVisitorDataPath());
			}
			if (javaModNormalizedProps.getProjectName() == null) {
				javaModNormalizedProps.setProjectName(javaProjProps.getProjectName());
			}
			if (javaModNormalizedProps.getInterpretationProps() == null) {
				javaModNormalizedProps.setInterpretationProps(javaProjProps.getInterpretationProps());
			}
			if (javaModNormalizedProps.getEntityHistorySize() == null) {
				javaModNormalizedProps.setEntityHistorySize(javaProjProps.getEntityHistorySize());
			}
		}
		return normalizedProps;
	}
	
	/**
	 * Returns source's path
	 * @param props
	 * @return
	 */
	public String getSourcePath(StartModuleProps props) {
		JavaModuleStartProps modProps = (JavaModuleStartProps)props;
		if (StartModuleProps.getSourcesPath() == null) {
			StartModuleProps.setSourcesPath(modProps.getSrcPath());
		}
		return modProps.getSrcPath() + "/" + modProps.getModulePackage().replaceAll("\\.", "/");
	}
	
	/**
	 * Called by VWMLProcessor when new software module is generated
	 * @param props
	 */
	public void startModule(StartModuleProps props) throws Exception {
		JavaModuleStartProps modProps = (JavaModuleStartProps)props;
		String[] startLines = {
			// 1. caption
			JavaCodeGeneratorUtils.prepareCaption(modProps) + "\r\n\r\n",
			// 2. package
			"package " + modProps.getModulePackage() + ";\r\n\r\n",
			// 3. imports
			prepareImports()
		};
		String moduleFileName = JavaCodeGeneratorUtils.capitalizeFirstLetter(modProps.getModuleName());
		// files related to module => 
		// {module itself, repository (where objects are created), linkage (where objects are linked)}
		String[] modFiles = {
			JavaCodeGeneratorUtils.generateClassName(ModuleFiles.MODULE.toValue() + moduleFileName) + ".java",
			JavaCodeGeneratorUtils.generateClassName(ModuleFiles.REPOSITORY.toValue() +  moduleFileName) + ".java",
			JavaCodeGeneratorUtils.generateClassName(ModuleFiles.LINKAGE.toValue() + moduleFileName) + ".java",
		};
		// stores actual module's name inside the properties for further processing
		modProps.setActualModuleName(JavaCodeGeneratorUtils.generateClassName(ModuleFiles.MODULE.toValue() + moduleFileName));
		String moduleFullPath = getSourcePath(modProps);
		File f = new File(moduleFullPath);
		if (!f.exists() && !f.mkdirs()) {
			throw new Exception("Couldn't create path '" + moduleFullPath + "'");
		}
		// creates files and adds caption, package name and imports
		for(int i = 0; i < fws.length; i++) {
			fws[i] = new FileWriter(moduleFullPath + "/" + modFiles[i]);
			for(String line : startLines) {
				fws[i].write(line);
			}
		}
		if (modProps.getVisitor() != null && getVisitor() == null) {
			setVisitor(modProps.getVisitor());
		}
		if (logger.isInfoEnabled()) {
			logger.info("The module '" + modProps.getModuleName() + "' is being built");
		}
	}

	/**
	 * Actually generates module's code
	 * @param modProps
	 */
	public void generate(StartModuleProps props) throws Exception {
		normalizeCode();
		JavaModuleStartProps modProps = (JavaModuleStartProps)props;		
		new JavaCodeGeneratorModule(fws[ModuleFiles.index(ModuleFiles.MODULE.toValue())]).buildModuleBody(modProps, getVisitor());
		new JavaCodeGeneratorRepository(fws[ModuleFiles.index(ModuleFiles.REPOSITORY.toValue())]).buildModuleRepositoryPart(modProps, declaredObjects, declaredContexts);
		new JavaCodeGeneratorLinkage(fws[ModuleFiles.index(ModuleFiles.LINKAGE.toValue())]).buildModuleLinkagePart(modProps, linkage, interpret, markedAsTerm, operations);
	}
	
	/**
	 * Finishes generation of module
	 * @param props
	 * @throws Exception
	 */
	public void finishModule(StartModuleProps props) throws Exception {
		// finalizes builder and linkage
		// finalizes module
		FileWriter fw = fws[ModuleFiles.index(ModuleFiles.MODULE.toValue())];
		fw.write(JavaCodeGeneratorUtils.getS_classEndDef());
		for(int i = 0; i < fws.length; i++) {
			fws[i].flush();
			fws[i].close();
		}		
		declaredObjects.clear();
		operations.clear();
		linkage.clear();
		declaredContexts.clear();
		declaredCreatures.clear();
		interpret.clear();
		markedAsTerm.clear();
		idTranslationMap.clear();
		if (logger.isInfoEnabled()) {
			logger.info("The module '" + ((JavaModuleStartProps)props).getModuleName() + "' was built");
		}
	}	
	
	/**
	 * Marks entity as term
	 * @param id (REL)
	 * @throws Exception
	 */
	public void markEntityAsTerm(Object id) throws Exception {
		EntityWalker.Relation rel = (EntityWalker.Relation)id;
		markedAsTerm.add(rel.getObj());
		if (rel.getLastLink() != null) {
			((VWMLLinkWrap)rel.getLastLink()).setAsTerm(true);
		}
	}

	/**
	 * Marks entity as lifeterm; the entity had to added and marked as term before this method is called
	 * @param id (REL)
	 * @throws Exception
	 */
	public void markEntityAsLifeTerm(Object id) throws Exception {
		boolean found = false;
		EntityWalker.Relation rel = (EntityWalker.Relation)id;
		// looking for the term which was added before
		for(Object objId : markedAsTerm) {
			if (objId == rel.getObj()) {
				found = true;
				break;
			}
		}
		if (!found) {
			throw new Exception("The term identified by '" + id + "' should be added and marked as term before markEntityAsLifeTerm is called");
		}
		if (rel.getLastLink() != null && ((VWMLLinkWrap)rel.getLastLink()).isAsTerm()) {
			((VWMLLinkWrap)rel.getLastLink()).setAsLifeTerm(true);
		}
		else {
			throw new Exception("Inconsistency found; object identified by '" + id + "' found in markedAsTerm collection but it is not marked as term");
		}
	}
	
	/**
	 * Declares simple entity
	 * @param id (ID)
	 * @param context
	 * @throws Exception
	 */
	public void declareSimpleEntity(Object id, String context) throws Exception {
		declaredObjects.add(new VWMLObjWrap(VWMLObjectBuilder.VWMLObjectType.SIMPLE_ENTITY, id, context));
	}
	
	/**
	 * Declares complex entity; the object id is compound object; consists from set of simple entity ids
	 * @param id (ID)
	 * @param readableId (ID)
	 * @param context
	 * @throws Exception
	 */
	public void declareComplexEntity(Object id, Object readableId, String context) throws Exception {
		declaredObjects.add(new VWMLObjWrap(VWMLObjectBuilder.VWMLObjectType.COMPLEX_ENTITY, id, readableId, context));		
	}

	/**
	 * Declares creature; special entity which lives on intersection of worlds
	 * @param id (ID)
	 * @param props (STRING)
	 * @param context
	 * @throws Exception
	 */
	public void declareCreature(Object id, Object props, String context) throws Exception {
		declaredCreatures.add(new VWMLObjWrap(VWMLObjectBuilder.VWMLObjectType.CREATURE, id, props, null));		
	}
	
	/**
	 * Declares term
	 * @param id (ID)
	 * @param context
	 * @throws Exception
	 */
	public void declareTerm(Object id, String context) throws Exception {
		declaredObjects.add(new VWMLObjWrap(VWMLObjectBuilder.VWMLObjectType.TERM, id, context));
	}

	/**
	 * Declares context; it is used during code generation phase; all entities should be linked with contexts
	 * @param contextId
	 */
	public void declareContext(Object contextId) {
		declaredContexts.add(new VWMLObjWrap(VWMLObjectBuilder.VWMLObjectType.CONTEXT, contextId, null));
	}
	
	/**
	 * Changes object's id from 'id' to 'idTo'
	 * @param id
	 * @param idTo
	 */
	public void changeObjectIdTo(Object id, Object idTo) {
		idTranslationMap.put(id, idTo);
	}
	
	/**
	 * Links objects using their ids
	 * @param id (ID)
	 * @param linkedObjId
	 * @param activeContext
	 */
	public void linkObjects(Object id, Object linkedObjId, String activeContext) {
		lastLink = new VWMLLinkWrap(id, linkedObjId);
		lastLink.setActiveContext(activeContext);
		linkage.add(lastLink);
	}
	
	/**
	 * Builds association between object and operation
	 * @param id (REL)
	 * @param op
	 * @param activeContext
	 */
	public void associateOperation(Object id, String op, String activeContext) {
		EntityWalker.Relation rel = (EntityWalker.Relation)id;
		if (rel == null || rel.getLastLink() == null) {
			logger.error("couldn't associated operation '" + op + "' since last linkage operation is absent");
			return;
		}
		if (logger.isDebugEnabled()) {
			logger.debug("Object '" + id + "' -> op '" + op + "'");
		}
		String uniqId = ((VWMLLinkWrap)rel.getLastLink()).getUniqId();
		VWMLOperationLink associatedOps = operations.get(uniqId);
		if (associatedOps == null) {
			associatedOps = new VWMLOperationLink(rel.getObj(), uniqId, new ArrayList<String>(), rel.getRelation());
			operations.put(uniqId, associatedOps);
		}
		associatedOps.getAssociatedOperations().add(op);
	}
	
	/**
	 * Set interpreting link between objects
	 * @param id (ID)
	 * @param interpretingObjId
	 * @param activeContext
	 */
	public void interpretObjects(Object id, Object interpretingObjId, String activeContext) {
		lastLink = new VWMLLinkWrap(id, interpretingObjId);
		lastLink.setActiveContext(activeContext);
		interpret.add(lastLink);
	}
	

	public AbstractVWMLLinkVisitor getVisitor() {
		return visitor;
	}

	public void setVisitor(AbstractVWMLLinkVisitor visitor) {
		this.visitor = visitor;
	}

	/**
	 * Returns language's name as string
	 * @return
	 */
	public String getLangAsString() {
		return "Java";
	}	

	protected void normalizeCode() {
		for(Object id : idTranslationMap.keySet()) {
			Object idTo = idTranslationMap.get(id);
			for(VWMLObjWrap o : declaredObjects) {
				if (o.getObjId().equals(id)) {
					o.setObjId(idTo);
				}
			}
			changeObjectIdToIn(id, idTo, linkage);
			changeObjectIdToIn(id, idTo, interpret);
		}
	}
	
	private String prepareImports() {
		Class<?> importedClasses[] = {VWMLObjectsRepository.class, VWMLObject.class};
		String imports = new String();
		for(Class<?> ic : importedClasses) {
			imports += "import " + ic.getName() + ";\r\n";
		}
		return imports;
	}
	
	private void changeObjectIdToIn(Object id, Object idTo, List<VWMLLinkWrap> link) {
		for(VWMLLinkWrap w : link) {
			if (w.getId().equals(id)) {
				w.setId(idTo);
			}
			if (w.getLinkedId().equals(id)) {
				w.setLinkedId(idTo);
			}
		}
	}
	
}
