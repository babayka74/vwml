package com.vw.lang.sink.java.code;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.vw.lang.sink.ICodeGenerator;
import com.vw.lang.sink.ICodeGenerator.StartModuleProps;
import com.vw.lang.sink.java.VWMLObject;
import com.vw.lang.sink.java.VWMLObjectBuilder;
import com.vw.lang.sink.java.VWMLObjectsRepository;
import com.vw.lang.sink.java.code.templates.JavaCodeGeneratorTemplates;
import com.vw.lang.sink.java.code.utils.JavaCodeGeneratorUtils;
import com.vw.lang.sink.java.entity.VWMLEntity;
import com.vw.lang.sink.java.link.IVWMLLinkVisitor;
import com.vw.lang.sink.java.module.VWMLModule;
import com.vw.lang.sink.java.operations.VWMLOperation;
import com.vw.lang.sink.java.operations.VWMLOperationsCode;

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
		
		public VWMLLinkWrap(Object id, Object linkedId) {
			super();
			this.id = id;
			this.linkedId = linkedId;
		}

		public Object getId() {
			return id;
		}

		public Object getLinkedId() {
			return linkedId;
		}
	}
	
	// set of writers, for each file type
	private FileWriter fws[] = new FileWriter[ModuleFiles.numValues()];
	// used for debug purposes in order to visualize objects' linkage
	private IVWMLLinkVisitor visitor = null;
	// declared VWML objects (simple entity, complex entity and terms)
	private List<VWMLObjWrap> declaredObjects = new ArrayList<VWMLObjWrap>();
	// defines objects' linkage
	private List<VWMLLinkWrap> linkage = new ArrayList<VWMLLinkWrap>();
	// defines objects' interpretation linkage (special type of linkage, one object is interpreted as second)
	private List<VWMLLinkWrap> interpret = new ArrayList<VWMLLinkWrap>();
	// associates object and operations
	private Map<Object, List<String>> operations = new HashMap<Object, List<String>>();
	
	private Logger logger = Logger.getLogger(JavaCodeGenerator.class);
	
	private static String s_caption = "/** \r\n*  This code was generated by VWML processor \r\n*  Description: %s \r\n*  Author: %s \r\n*  Date  : %s \r\n*/";
	private static String s_classStartDef = "public class %s {";
	private static String s_classEndDef = "}";

	public static JavaCodeGenerator instance() {
		return new JavaCodeGenerator();
	}
	
	/**
	 * Builds module's properties instance
	 * @return
	 */
	public StartModuleProps buildProps() {
		return new JavaModuleStartProps();
	}

	/**
	 * Returns source's path
	 * @param props
	 * @return
	 */
	public String getSourcePath(StartModuleProps props) {
		JavaModuleStartProps modProps = (JavaModuleStartProps)props;
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
		if (logger.isInfoEnabled()) {
			logger.info("The module '" + ((JavaModuleStartProps)props).getModuleName() + "' was built");
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
		linkage.add(new VWMLLinkWrap(id, linkedObjId));
	}
	
	/**
	 * Builds association between object and operation
	 * @param id
	 * @param op
	 */
	public void associateOperation(Object id, String op) {
		List<String> associatedOps = operations.get(id);
		if (associatedOps == null) {
			associatedOps = new ArrayList<String>();
			operations.put(id, associatedOps);
		}
		associatedOps.add(op);
	}
	
	/**
	 * Set interpreting link between objects
	 * @param id
	 * @param interpretingObjId
	 */
	public void interpretObjects(Object id, Object interpretingObjId) {
		interpret.add(new VWMLLinkWrap(id, interpretingObjId));
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
		fw.write("\tpublic void build() throws Exception {\r\n");
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
			list += "\r\n\t\tnew VWMLLinkWrap(\"" + obj.getId() + "\", \"" + obj.getLinkedId() + "\")";
			ft = false;
		}
		list += "\r\n\t};\r\n\r\n";
		return list;
	}
	
	private String generateOperation2ObjectAssociations() {
		boolean ft = true;
		String appliedOperations = "\tprivate Map<Object, String[]> appliedOperations = new HashMap<Object, String[]>() {\r\n";
		for(Object id : operations.keySet()) {
			List<String> ops = operations.get(id);
			String opsAsList = new String();
			boolean fp = true;
			for(String op : ops) {
				if (!fp) {
					opsAsList += ",";
				}
				opsAsList += "\"" + op + "\"";
				fp = false;
			}
			if (!ft) {
				appliedOperations += ",\r\n";
			}
			appliedOperations += "\t\t{put(\"" + id + "\", new String[] {" + opsAsList + "});}\r\n";
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
