package com.vw.lang.sink.java.code;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.log4j.Logger;

import com.vw.lang.sink.ICodeGenerator;
import com.vw.lang.sink.java.VWMLObject;
import com.vw.lang.sink.java.VWMLObjectBuilder;
import com.vw.lang.sink.java.VWMLObjectBuilder.VWMLObjectType;
import com.vw.lang.sink.java.VWMLObjectsRepository;
import com.vw.lang.sink.java.code.templates.JavaCodeGeneratorTemplates;
import com.vw.lang.sink.java.code.utils.JavaCodeGeneratorUtils;
import com.vw.lang.sink.java.entity.VWMLEntity;
import com.vw.lang.sink.java.link.IVWMLLinkVisitor;
import com.vw.lang.sink.java.module.VWMLModule;
import com.vw.lang.sink.java.operations.VWMLOperation;
import com.vw.lang.sink.java.operations.VWMLOperationsCode;
import com.vw.lang.sink.utils.ComplexEntityNameBuilder;
import com.vw.lang.sink.utils.EntityWalker;
import com.vw.lang.sink.utils.EntityWalker.REL;

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
		private String description;
		private String date;
		private IVWMLLinkVisitor visitor;
		private String visitorDataPath;
		
		public JavaModuleStartProps() {
			super();
		}

		public JavaModuleStartProps(String srcPath, String commonPackage,
				String moduleName, String modulePackage, String author,
				String description, String date, IVWMLLinkVisitor visitor,
				String visitorDataPath) {
			super();
			this.srcPath = srcPath;
			this.commonPackage = commonPackage;
			this.moduleName = moduleName;
			this.modulePackage = modulePackage;
			this.author = author;
			this.description = description;
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

		public IVWMLLinkVisitor getVisitor() {
			return visitor;
		}

		public void setVisitor(IVWMLLinkVisitor visitor) {
			this.visitor = visitor;
		}

		public String getVisitorDataPath() {
			return visitorDataPath;
		}

		public void setVisitorDataPath(String visitorDataPath) {
			this.visitorDataPath = visitorDataPath;
		}

		@Override
		public String toString() {
			return "JavaModuleStartProps [srcPath=" + srcPath
					+ ", commonPackage=" + commonPackage + ", moduleName="
					+ moduleName + ", modulePackage=" + modulePackage
					+ ", author=" + author + ", description=" + description
					+ ", date=" + date + ", visitor=" + visitor
					+ ", visitorDataPath=" + visitorDataPath + "]";
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
	protected static class VWMLObjWrap {
		private VWMLObjectBuilder.VWMLObjectType type;
		private Object objId;

		public VWMLObjWrap(VWMLObjectBuilder.VWMLObjectType type, Object objId) {
			super();
			this.type = type;
			this.objId = objId;
		}

		public Object getObjId() {
			return objId;
		}

		public VWMLObjectBuilder.VWMLObjectType getType() {
			return type;
		}
	}
	
	protected static class VWMLLinkWrap {
		private Object id;
		private Object linkedId;
		private boolean asTerm = false;
		private String uniqId = "VWMLLINK_" + UUID.randomUUID().toString();
		
		public VWMLLinkWrap(Object id, Object linkedId) {
			super();
			this.id = id;
			this.linkedId = linkedId;
		}

		public VWMLLinkWrap(Object id, Object linkedId, boolean asTerm) {
			super();
			this.id = id;
			this.linkedId = linkedId;
			this.asTerm = asTerm;
		}
		
		public Object getId() {
			return id;
		}

		public Object getLinkedId() {
			return linkedId;
		}

		public boolean isAsTerm() {
			return asTerm;
		}

		public void setAsTerm(boolean asTerm) {
			this.asTerm = asTerm;
		}

		public String getUniqId() {
			return uniqId;
		}

		@Override
		public String toString() {
			return "VWMLLinkWrap [id=" + id + ", linkedId=" + linkedId
					+ ", asTerm=" + asTerm + ", uniqId=" + uniqId + "]";
		}
	}
	
	/**
	 * Links list of operations with entity, by creating 'term'
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
	private IVWMLLinkVisitor visitor = null;
	// declared VWML objects (simple entity, complex entity and terms)
	private List<VWMLObjWrap> declaredObjects = new ArrayList<VWMLObjWrap>();
	// contains list of entity ids which were marked as 'term'
	private List<Object> markedAsTerm = new ArrayList<Object>();
	// defines objects' linkage
	private List<VWMLLinkWrap> linkage = new ArrayList<VWMLLinkWrap>();
	// defines objects' interpretation linkage (special type of linkage, one object is interpreted as second)
	private List<VWMLLinkWrap> interpret = new ArrayList<VWMLLinkWrap>();
	// associates object and operations
	private Map<Object, VWMLOperationLink> operations = new HashMap<Object, VWMLOperationLink>();
	
	private VWMLLinkWrap lastLink = null;
	
	private Logger logger = Logger.getLogger(JavaCodeGenerator.class);
	
	private static String s_caption = "/** \r\n*  This code was generated by VWML processor \r\n*  Description: %s \r\n*  Author: %s \r\n*  Date  : %s \r\n*/";
		
	private static String s_classStartDef = "public class %s {";
	private static String s_classEndDef = "}";

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
			prepareCaption(modProps) + "\r\n\r\n",
			// 2. package
			"package " + modProps.getModulePackage() + ";\r\n\r\n",
			// 3. imports
			prepareImports()
		};
		String moduleFileName = JavaCodeGeneratorUtils.capitalizeFirstLetter(modProps.getModuleName());
		// files related to module => 
		// {module itself, repository (where objects are created), linkage (where objects are linked)}
		String[] modFiles = {
			generateClassName(ModuleFiles.MODULE.toValue() + moduleFileName) + ".java",
			generateClassName(ModuleFiles.REPOSITORY.toValue() +  moduleFileName) + ".java",
			generateClassName(ModuleFiles.LINKAGE.toValue() + moduleFileName) + ".java",
		};
		// stores actual module's name inside the properties for further processing
		modProps.setActualModuleName(generateClassName(ModuleFiles.MODULE.toValue() + moduleFileName));
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
		JavaModuleStartProps modProps = (JavaModuleStartProps)props;		
		buildModuleBody(modProps);
		buildModuleRepositoryPart(modProps);
		buildModuleLinkagePart(modProps);
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
		fw.write(s_classEndDef);
		for(int i = 0; i < fws.length; i++) {
			fws[i].flush();
			fws[i].close();
		}		
		declaredObjects.clear();
		operations.clear();
		linkage.clear();
		interpret.clear();
		markedAsTerm.clear();
		if (logger.isInfoEnabled()) {
			logger.info("The module '" + ((JavaModuleStartProps)props).getModuleName() + "' was built");
		}
	}	
	
	/**
	 * Marks entity as term
	 * @param id
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
	 * Declares simple entity
	 * @param id
	 * @throws Exception
	 */
	public void declareSimpleEntity(Object id) throws Exception {
		declaredObjects.add(new VWMLObjWrap(VWMLObjectBuilder.VWMLObjectType.SIMPLE_ENTITY, id));
	}
	
	/**
	 * Declares complex entity; the object id is compound object; consists from set of simple entity ids
	 * @param id
	 * @throws Exception
	 */
	public void declareComplexEntity(Object id) throws Exception {
		declaredObjects.add(new VWMLObjWrap(VWMLObjectBuilder.VWMLObjectType.COMPLEX_ENTITY, id));		
	}
	
	/**
	 * Declares term
	 * @param id
	 * @throws Exception
	 */
	public void declareTerm(Object id) throws Exception {
		declaredObjects.add(new VWMLObjWrap(VWMLObjectBuilder.VWMLObjectType.TERM, id));
	}
	
	/**
	 * Links objects using their ids
	 * @param id
	 * @param linkedObjId
	 */
	public void linkObjects(Object id, Object linkedObjId) {
		lastLink = new VWMLLinkWrap(id, linkedObjId);
		linkage.add(lastLink);
	}
	
	/**
	 * Builds association between object and operation
	 * @param id
	 * @param op
	 */
	public void associateOperation(Object id, String op) {
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
	 * @param id
	 * @param interpretingObjId
	 */
	public void interpretObjects(Object id, Object interpretingObjId) {
		lastLink = new VWMLLinkWrap(id, interpretingObjId);
		interpret.add(lastLink);
	}
	

	public IVWMLLinkVisitor getVisitor() {
		return visitor;
	}

	public void setVisitor(IVWMLLinkVisitor visitor) {
		this.visitor = visitor;
	}

	/**
	 * Returns language's name as string
	 * @return
	 */
	public String getLangAsString() {
		return "Java";
	}	
	
	/**
	 * Returns module's caption constructed from module's properties
	 * @param props
	 * @return
	 */
	protected String prepareCaption(StartModuleProps props) throws Exception {
		JavaModuleStartProps modProps = (JavaModuleStartProps)props;		
		return String.format(s_caption, modProps.getDescription(), modProps.getAuthor(), modProps.getDate());
	}
		
	/**
	 * Builds module's body
	 * @param modProps
	 * @throws Exception
	 */
	protected void buildModuleBody(JavaModuleStartProps modProps) throws Exception {
		FileWriter fw = fws[ModuleFiles.index(ModuleFiles.MODULE.toValue())];
		fw.write("import " + VWMLModule.class.getName() + ";\r\n");
		if (getVisitor() != null) {
			// adds visitor's interface in any case
			fw.write("import " + IVWMLLinkVisitor.class.getName() + ";\r\n");
			String visitorImport = "import " + getVisitor().getClass().getName() + ";\r\n";
			fw.write(visitorImport);
		}
		// starts class definition
		fw.write("\r\n" + generateClassDef(ModuleFiles.MODULE.toValue(), " extends VWMLModule", modProps));
		String repositoryClassName = generateClassName(ModuleFiles.REPOSITORY.toValue() + JavaCodeGeneratorUtils.capitalizeFirstLetter(modProps.getModuleName()));
		String linkageClassName = generateClassName(ModuleFiles.LINKAGE.toValue() + JavaCodeGeneratorUtils.capitalizeFirstLetter(modProps.getModuleName()));
		fw.write("\tprivate " + repositoryClassName + " repository = new " + repositoryClassName + "();\r\n");
		fw.write("\tprivate " + linkageClassName + " linkage = new " + linkageClassName + "();\r\n\r\n");
		// generates build method which calls repository's and link's methods
		fw.write("\t@Override\r\n\tpublic void build() throws Exception {\r\n");
		if (getVisitor() != null) {
			String path = modProps.getVisitorDataPath().replaceAll("\\\\", "/");
			File f = new File(path);
			if (!f.exists() && !f.mkdirs()) {
				logger.warn("Couldn't create path (for visualizer) '" + path + "'");
			}
			path += "/" + modProps.getModuleName() + ".dot";
			fw.write("\t\tIVWMLLinkVisitor preprocessorStructureVisualizer = " + getVisitor().getClass().getSimpleName() + ".instance();\r\n");			
			fw.write("\t\tpreprocessorStructureVisualizer.init(\"" + modProps.getModuleName() + "\", \"" + path + "\");\r\n");
			fw.write("\t\trepository.setPreprocessorStructureVisualizer(preprocessorStructureVisualizer);\r\n");
			if (logger.isInfoEnabled()) {
				logger.info("The visualizer '" + getVisitor().getClass().getSimpleName() + "' for module '" + modProps.getModuleName() + "' installed; output '" + modProps.getVisitorDataPath() + "'");
			}
		}
		fw.write("\t\trepository.acquireEntities();\r\n");
		fw.write("\t\tlinkage.linkEntities();\r\n");
		if (getVisitor() != null) {
			fw.write("\t\tpreprocessorStructureVisualizer.done(\"" + modProps.getModuleName() + "\");\r\n");
		}		
		fw.write("\t}\r\n\r\n");
		fw.write(JavaCodeGeneratorTemplates.s_VWMLModuleDebugMethods);
		if (logger.isInfoEnabled()) {
			logger.info("Module '" + modProps.getModuleName() + "'; body - OK");
		}
	}
	
	/**
	 * Builds repository part - defines method 'build' which adds entities
	 * listed in VWML's code
	 * @param modProps
	 * @throws Exception
	 */
	protected void buildModuleRepositoryPart(JavaModuleStartProps modProps) throws Exception {
		declaredObjects.add(new VWMLObjWrap(VWMLObjectType.COMPLEX_ENTITY, ComplexEntityNameBuilder.generateRootId(modProps.getModuleName())));
		// caption and common imports are added before (see startModule) method
		FileWriter fw = fws[ModuleFiles.index(ModuleFiles.REPOSITORY.toValue())];
		fw.write("import " + VWMLObjectBuilder.VWMLObjectType.class.getCanonicalName() + ";\r\n");		
		// adds visitor's interface in any case
		fw.write("import " + IVWMLLinkVisitor.class.getName() + ";\r\n");
		// starts class definition
		fw.write("\r\n" + generateClassDef(ModuleFiles.REPOSITORY.toValue(), "", modProps));
		fw.write("\tprivate IVWMLLinkVisitor preprocessorStructureVisualizer = null;\r\n\r\n");
		fw.write(JavaCodeGeneratorTemplates.s_VWMLRepositoryCodeTemplate);
		// adds method's 'build' definition
		fw.write("\tpublic void acquireEntities() throws Exception {\r\n");
		for(VWMLObjWrap obj : declaredObjects) {
			fw.write("\t\t// constructs entity '" + obj.getObjId() + "'\r\n");
			fw.write("\t\tVWMLObjectsRepository.acquire(" + obj.getType().getClass().getSimpleName()+ "." + obj.getType().toValue() + ", \"" + obj.getObjId() + "\", preprocessorStructureVisualizer);\r\n");
		}
		// closes 'build' method
		fw.write("\t}\r\n");
		// closes class
		fw.write("}\r\n");
		if (logger.isInfoEnabled()) {
			logger.info("Module '" + modProps.getModuleName() + "'; repository - OK");
		}		
	}

	/**
	 * Links acquired objects 
	 * @param modProps
	 * @throws Exception
	 */
	protected void buildModuleLinkagePart(JavaModuleStartProps modProps) throws Exception {
		// caption and common imports are added before (see startModule) method
		FileWriter fw = fws[ModuleFiles.index(ModuleFiles.LINKAGE.toValue())];
		String linkageImports[] = {
				"java.util.HashMap",
				"java.util.Map",
				VWMLEntity.class.getName(),
				VWMLOperationsCode.class.getName(),
				VWMLOperation.class.getName()
		};
		for(String linkageImport : linkageImports) {
			fw.write("import " + linkageImport + ";\r\n");
		}
		// starts class definition
		fw.write("\r\n" + generateClassDef(ModuleFiles.LINKAGE.toValue(), "", modProps));
		// wrapper for linked objects
		fw.write("\r\n" + JavaCodeGeneratorTemplates.s_VWMLLinkWrapTemplate);
		// generates list of wrapped objects prepared for linkage operation
		String listOfLinkedObjects = generateLinkedObjectsDefinition("linkedObjectPairs", linkage);
		fw.write(listOfLinkedObjects);
		String listOfInterpretedObjects = generateLinkedObjectsDefinition("interpretedObjectPairs", interpret);
		fw.write(listOfInterpretedObjects);
		String operation2ObjectsAssociation = generateOperation2ObjectAssociations();
		fw.write(operation2ObjectsAssociation);
		String entityMarkedAsTerm = generateObjectsDefinition("entityMarkedAsTerms", markedAsTerm);
		fw.write(entityMarkedAsTerm);
		// link and aux. methods
		String[] methodsDef =  {
								JavaCodeGeneratorTemplates.s_VWMLLinkageCodeTemplate,
							   };
		for(String methodDef : methodsDef) {
			fw.write(methodDef);
		}
		// closes class
		fw.write("}\r\n");
		if (logger.isInfoEnabled()) {
			logger.info("Module '" + modProps.getModuleName() + "'; linkage - OK");
		}		
	}

	private String generateLinkedObjectsDefinition(String objName, List<VWMLLinkWrap> store) {
		boolean ft = true;
		String list = "\tprivate VWMLLinkWrap[] " + objName + " = {";
		for(VWMLLinkWrap obj : store) {
			if (!ft) {
				list += ",";
			}
			if (!obj.isAsTerm()) {
				list += "\r\n\t\tnew VWMLLinkWrap(\"" + obj.getId() + "\", \"" + obj.getLinkedId() + "\", VWMLLinkWrap.MARKED.ENTITY, \"" + obj.getUniqId() + "\")";
			}
			else {
				list += "\r\n\t\tnew VWMLLinkWrap(\"" + obj.getId() + "\", \"" + obj.getLinkedId() + "\", VWMLLinkWrap.MARKED.TERM, \"" + obj.getUniqId() + "\")";
			}
			ft = false;
		}
		list += "\r\n\t};\r\n\r\n";
		return list;
	}
	
	private String generateObjectsDefinition(String objName, List<Object> store) {
		boolean ft = true;
		String list = "\tprivate String[] " + objName + " = {";
		for(Object obj : store) {
			if (!ft) {
				list += ",";
			}
			list += "\r\n\t\t\"" + obj + "\"";
			ft = false;
		}
		list += "\r\n\t};\r\n\r\n";
		return list;
	}	
	
	private String generateOperation2ObjectAssociations() {
		boolean ft = true;
		// key is uniq entity's key, which describes entity's position in AST
		String appliedOperations = "\tprivate Map<Object, VWMLOperationLink> appliedOperations = new HashMap<Object, VWMLOperationLink>() {\r\n";
		for(Object id : operations.keySet()) {
			VWMLOperationLink link = operations.get(id);
			String opsAsList = new String();
			boolean fp = true;
			for(String op : link.getAssociatedOperations()) {
				if (!fp) {
					opsAsList += ",";
				}
				opsAsList += "\"" + op + "\"";
				fp = false;
			}
			if (!ft) {
				appliedOperations += ",\r\n";
			}
			String rel = (link.getRel() == REL.LINK) ? "VWMLOperationLink.REL.LINK" : "VWMLOperationLink.REL.ASSOCIATION";
			appliedOperations += "\t\t{put(\"" + id + "\", new VWMLOperationLink(\"" + link.getEntityId() + "\", \"" + link.getLinkId() + "\", new String[] {" + opsAsList + "}, " + rel + "));}\r\n";
		}
		appliedOperations += "\t};\r\n\r\n";
		return appliedOperations;
	}
	
	private String generateClassDef(String prefix, String suffix, JavaModuleStartProps modProps) {
		return String.format(s_classStartDef, generateClassName(prefix + JavaCodeGeneratorUtils.capitalizeFirstLetter(modProps.getModuleName())) + suffix) + "\r\n";
	}
	
	private String prepareImports() {
		Class<?> importedClasses[] = {VWMLObjectsRepository.class, VWMLObject.class};
		String imports = new String();
		for(Class<?> ic : importedClasses) {
			imports += "import " + ic.getName() + ";\r\n";
		}
		return imports;
	}
	
	private String generateClassName(String suffix) {
		return "VWML" + suffix;
	}
}
