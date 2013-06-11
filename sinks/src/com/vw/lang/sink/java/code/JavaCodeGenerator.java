package com.vw.lang.sink.java.code;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.vw.lang.sink.CodeGenerator;
import com.vw.lang.sink.java.VWMLObject;
import com.vw.lang.sink.java.VWMLObjectBuilder;
import com.vw.lang.sink.java.VWMLObjectsRepository;
import com.vw.lang.sink.java.code.templates.JavaCodeGeneratorTemplates;
import com.vw.lang.sink.java.link.IVWMLLinkVisitor;

/**
 * Used by VWMLProcessor for code generation
 * @author ogibayev
 *
 */
public class JavaCodeGenerator extends CodeGenerator {
	
	public static class JavaModuleStartProps extends StartModuleProps {
		private String moduleName;
		private String modulePackage;
		private String modulePath;
		private String author;
		private String description;
		private String date;
		private IVWMLLinkVisitor visitor;
		
		public JavaModuleStartProps() {
			super();
		}

		public JavaModuleStartProps(String moduleName, String modulePackage,
				String modulePath, String author, String description,
				String date, IVWMLLinkVisitor visitor) {
			super();
			this.moduleName = moduleName;
			this.modulePackage = modulePackage;
			this.modulePath = modulePath;
			this.author = author;
			this.description = description;
			this.date = date;
			this.visitor = visitor;
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
		
		public String getModulePath() {
			return modulePath;
		}
		
		public void setModulePath(String modulePath) {
			this.modulePath = modulePath;
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

		@Override
		public String toString() {
			return "JavaModuleStartProps [moduleName=" + moduleName
					+ ", modulePackage=" + modulePackage + ", modulePath="
					+ modulePath + ", author=" + author + ", description="
					+ description + ", date=" + date + ", visitor=" + visitor
					+ "]";
		}		
	}
	
	public static enum ModuleFiles {
		MODULE(""),
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
		private Object objId;

		public VWMLObjWrap(Object objId) {
			super();
			this.objId = objId;
		}

		public Object getObjId() {
			return objId;
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
	// declared VWML objects (simple entity, complex entity and terms
	private Map<VWMLObjectBuilder.VWMLObjectType, VWMLObjWrap> declaredObjects = new HashMap<VWMLObjectBuilder.VWMLObjectType, VWMLObjWrap>();
	// defines objects' linkage
	private List<VWMLLinkWrap> linkage = new ArrayList<VWMLLinkWrap>();
	// defines objects' interpretation linkage (special type of linkage, one object is interpreted as second)
	private List<VWMLLinkWrap> interpret = new ArrayList<VWMLLinkWrap>();
	
	private static String s_caption = "/** \r\n*  This code was generated by VWML processor \r\n*  Description: %s \r\n*  Author: %s \r\n*  Date  : %s \r\n*/";
	private static String s_classStartDef = "public class %s extends VWMLModule {";
	private static String s_classEndDef = "}";

	/**
	 * Returns module's caption constructed from module's properties
	 * @param props
	 * @return
	 */
	public String prepareCaption(StartModuleProps props) throws Exception {
		JavaModuleStartProps modProps = (JavaModuleStartProps)props;		
		return String.format(s_caption, modProps.getDescription(), modProps.getAuthor(), modProps.getDate());
	}
	
	/**
	 * Called by VWMLProcessor when new module is generated
	 * @param props
	 */
	public void startModule(StartModuleProps props) throws Exception {
		JavaModuleStartProps modProps = (JavaModuleStartProps)props;
		String[] startLines = {
			// 1. caption
			prepareCaption(modProps) + "\r\n\r\n",
			// 2. package
			"package " + modProps.getModulePackage() + "\r\n",
			// 3. imports
			prepareImports() + "\r\n"
		};
		// files related to module => 
		// {module itself, repository (where objects are created), linkage (where objects are linked)}
		String[] modFiles = {
			generateClassName(ModuleFiles.MODULE + modProps.getModuleName()) + ".java",
			generateClassName(ModuleFiles.REPOSITORY +  modProps.getModuleName()) + ".java",
			generateClassName(ModuleFiles.LINKAGE + modProps.getModuleName()) + ".java",
		};
		File f = new File(modProps.getModulePath());
		if (!f.exists() && !f.mkdirs()) {
			throw new Exception("Couldn't create path '" + modProps.getModulePath() + "'");
		}
		// creates files and adds caption, package name and imports
		for(int i = 0; i < fws.length; i++) {
			fws[i] = new FileWriter(modProps.getModulePath() + "/" + modFiles[i]);
			for(String line : startLines) {
				fws[i].write(line);
			}
		}
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
		linkage.clear();
		interpret.clear();
	}	
	
	/**
	 * Declares simple entity
	 * @param id
	 * @throws Exception
	 */
	public void declareSimpleEntity(Object id) throws Exception {
		declaredObjects.put(VWMLObjectBuilder.VWMLObjectType.SIMPLE_ENTITY, new VWMLObjWrap(id));
	}
	
	/**
	 * Declares complex entity
	 * @param id
	 * @throws Exception
	 */
	public void declareComplexEntity(Object id) throws Exception {
		declaredObjects.put(VWMLObjectBuilder.VWMLObjectType.COMPLEX_ENTITY, new VWMLObjWrap(id));		
	}
	
	/**
	 * Declares term
	 * @param id
	 * @throws Exception
	 */
	public void declareTerm(Object id) throws Exception {
		declaredObjects.put(VWMLObjectBuilder.VWMLObjectType.TERM, new VWMLObjWrap(id));
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
	 * Builds repository part - defines method 'build' which adds entities
	 * listed in VWML's code
	 * @param modProps
	 * @throws Exception
	 */
	protected void buildModuleRepositoryPart(JavaModuleStartProps modProps) throws Exception {
		// caption and common imports are added before (see startModule) method
		FileWriter fw = fws[ModuleFiles.index(ModuleFiles.REPOSITORY.toValue())];
		// adds visitor's interface in any case
		fw.write("import " + IVWMLLinkVisitor.class.getPackage().getName() + ";\r\n");
		// adds visitor's import, if it exists
		if (getVisitor() != null) {
			String visitorImport = "import " + getVisitor().getClass().getPackage().getName() + ";\r\n\r\n";
			fw.write(visitorImport);
		}
		// starts class definition
		fw.write(generateClassDef(ModuleFiles.REPOSITORY.toValue(), modProps));
		// instantiates visitor
		if (getVisitor() != null) {
			fw.write("\tprivate IVWMLLinkVisitor preprocessorStructureVisualizer = " + getVisitor().getClass().getSimpleName() + ".instance();\r\n\r\n");
		}
		else {
			fw.write("\tprivate IVWMLLinkVisitor preprocessorStructureVisualizer = null;\r\n\r\n");
		}
		// adds method's 'build' definition
		fw.write("\tpublic void build() throws Exception {\r\n");
		for(VWMLObjectBuilder.VWMLObjectType type : declaredObjects.keySet()) {
			VWMLObjWrap obj = declaredObjects.get(type);
			fw.write("\t\t// constructs entity '" + obj.getObjId() + "'");
			fw.write("\t\tVWMLObjectsRepository.acquire(" + type.toValue() + ", " + obj.getObjId() + ", preprocessorStructureVisualizer);\r\n");
		}
		// closes 'build' method
		fw.write("\t}\r\n");
		// closes class
		fw.write("}\r\n");
	}

	/**
	 * Links acquired objects 
	 * @param modProps
	 * @throws Exception
	 */
	protected void buildModuleLinkagePart(JavaModuleStartProps modProps) throws Exception {
		// caption and common imports are added before (see startModule) method
		FileWriter fw = fws[ModuleFiles.index(ModuleFiles.LINKAGE.toValue())];
		// starts class definition
		fw.write(generateClassDef(ModuleFiles.LINKAGE.toValue(), modProps));
		// wrapper for linked objects
		fw.write(JavaCodeGeneratorTemplates.s_VWMLLinkWrapTemplate);
		// generates list of wrapped objects prepared for linkage operation
		String listOfLinkedObjects = generateLinkedObjectsDefinition("linkedObjectPairs");
		fw.write(listOfLinkedObjects);
		String listOfInterpretedObjects = generateLinkedObjectsDefinition("interpretedObjectPairs");
		fw.write(listOfInterpretedObjects);
		// link and aux. methods
		String[] methodsDef =  {
								JavaCodeGeneratorTemplates.s_VWMLLinkageCodeTemplate,
								JavaCodeGeneratorTemplates.s_VWMLInterpretingCodeTemplate,
								JavaCodeGeneratorTemplates.s_VWMLLinkageAuxCodeTemplate
							   };
		for(String methodDef : methodsDef) {
			fw.write(methodDef);
		}
		// closes class
		fw.write("}\r\n");
	}

	private String generateLinkedObjectsDefinition(String objName) {
		boolean ft = true;
		String list = "\tprivate VWMLLinkWrap[] " + objName + " = {";
		for(VWMLLinkWrap obj : linkage) {
			if (!ft) {
				list += ", \r\n";
			}
			list += "\tnew VWMLLinkWrap(\"" + obj.getId() + "\", \"" + obj.getLinkedId() + "\")";
			ft = false;
		}
		list += "\r\n\t};\r\n\r\n";
		return list;
	}
	
	private String generateClassDef(String prefix, JavaModuleStartProps modProps) {
		return String.format(s_classStartDef, prefix + generateClassName(modProps.getModuleName()) + "\r\n");
	}
	
	private String prepareImports() {
		Class<?> importedClasses[] = {VWMLObjectsRepository.class, VWMLObject.class};
		String imports = new String();
		for(Class<?> ic : importedClasses) {
			imports += "import " + ic.getPackage().getName() + ";\r\n";
		}
		return imports;
	}
	
	private String generateClassName(String suffix) {
		return "VWML" + suffix;
	}
}
