package com.vw.lang.sink.java.code;

import java.io.FileWriter;

import com.vw.lang.sink.CodeGenerator;

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
		
		public JavaModuleStartProps() {
			super();
		}

		public JavaModuleStartProps(String moduleName, String modulePackage,
				String modulePath, String author, String description,
				String date) {
			super();
			this.moduleName = moduleName;
			this.modulePackage = modulePackage;
			this.modulePath = modulePath;
			this.author = author;
			this.description = description;
			this.date = date;
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

		@Override
		public String toString() {
			return "JavaModuleStartProps [moduleName=" + moduleName
					+ ", modulePackage=" + modulePackage + ", modulePath="
					+ modulePath + "]";
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
	
	// set of writers, for each file type
	private FileWriter fws[] = new FileWriter[ModuleFiles.numValues()];
	
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
		// creates files and adds caption, package name and imports
		for(int i = 0; i < fws.length; i++) {
			fws[i] = new FileWriter(modProps.getModulePath() + "/" + modFiles[i]);
			for(String line : startLines) {
				fws[i].write(line);
			}
			fws[i].write(generateClassDef(ModuleFiles.index(i).toValue(), modProps));
		}
	}
	
	private String generateClassDef(String prefix, JavaModuleStartProps modProps) {
		return String.format(s_classStartDef, prefix + generateClassName(modProps.getModuleName()) + "\r\n");
	}
	
	private String prepareImports() {
		return "";
	}
	
	private String generateClassName(String suffix) {
		return "VWML" + suffix;
	}

	private void generateObjectRepositoryAndLinkage() throws Exception {
		
	}
}
