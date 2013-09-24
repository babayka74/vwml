package com.vw.lang.sink.java.code.module;

import java.io.File;
import java.io.FileWriter;

import org.apache.log4j.Logger;

import com.vw.lang.sink.java.code.JavaCodeGeneratorComponent;
import com.vw.lang.sink.java.code.JavaCodeGenerator.JavaModuleStartProps;
import com.vw.lang.sink.java.code.JavaCodeGenerator.ModuleFiles;
import com.vw.lang.sink.java.code.templates.JavaCodeGeneratorTemplates;
import com.vw.lang.sink.java.code.utils.JavaCodeGeneratorUtils;
import com.vw.lang.sink.java.link.AbstractVWMLLinkVisitor;
import com.vw.lang.sink.java.link.VWMLLinkage;
import com.vw.lang.sink.java.module.VWMLModule;
import com.vw.lang.sink.java.repository.VWMLRepository;

/**
 * Generates code for module's phase
 * @author ogibayev
 *
 */
public class JavaCodeGeneratorModule extends JavaCodeGeneratorComponent {

	private Logger logger = Logger.getLogger(JavaCodeGeneratorModule.class);

	
	public JavaCodeGeneratorModule() {
		super();
	}

	public JavaCodeGeneratorModule(FileWriter fw) {
		super(fw);
	}

	/**
	 * Builds module's body
	 * @param modProps
	 * @throws Exception
	 */
	public void buildModuleBody(JavaModuleStartProps modProps, AbstractVWMLLinkVisitor visitor) throws Exception {
		getFw().write("import " + VWMLModule.class.getName() + ";\r\n");
		getFw().write("import " + VWMLRepository.class.getName() + ";\r\n");
		getFw().write("import " + VWMLLinkage.class.getName() + ";\r\n");
		if (visitor != null) {
			// adds visitor's interface in any case
			getFw().write("import " + AbstractVWMLLinkVisitor.class.getName() + ";\r\n");
			String visitorImport = "import " + visitor.getClass().getName() + ";\r\n";
			getFw().write(visitorImport);
		}
		// starts class definition
		getFw().write("\r\n" + JavaCodeGeneratorUtils.generateClassDef(ModuleFiles.MODULE.toValue(), " extends VWMLModule", modProps));
		String repositoryClassName = JavaCodeGeneratorUtils.generateClassName(ModuleFiles.REPOSITORY.toValue() + JavaCodeGeneratorUtils.capitalizeFirstLetter(modProps.getModuleName()));
		String linkageClassName = JavaCodeGeneratorUtils.generateClassName(ModuleFiles.LINKAGE.toValue() + JavaCodeGeneratorUtils.capitalizeFirstLetter(modProps.getModuleName()));
		getFw().write("\tprivate " + repositoryClassName + " repository = new " + repositoryClassName + "();\r\n");
		getFw().write("\tprivate " + linkageClassName + " linkage = new " + linkageClassName + "();\r\n\r\n");
		// generates build method which calls repository's and link's methods
		getFw().write("\t@Override\r\n\tpublic void build() throws Exception {\r\n");
		if (visitor != null) {
			String path = modProps.getVisitorDataPath().replaceAll("\\\\", "/");
			File f = new File(path);
			if (!f.exists() && !f.mkdirs()) {
				logger.warn("Couldn't create path (for visualizer) '" + path + "'");
			}
			path += "/" + modProps.getModuleName() + ".dot";
			getFw().write("\t\tAbstractVWMLLinkVisitor preprocessorStructureVisualizer = " + visitor.getClass().getSimpleName() + ".instance();\r\n");			
			getFw().write("\t\tpreprocessorStructureVisualizer.init(\"" + modProps.getModuleName() + "\", \"" + path + "\");\r\n");
			getFw().write("\t\trepository.setPreprocessorStructureVisualizer(preprocessorStructureVisualizer);\r\n");
			getFw().write("\t\tlinkage.setPreprocessorStructureVisualizer(preprocessorStructureVisualizer);\r\n");
			if (logger.isInfoEnabled()) {
				logger.info("The visualizer '" + visitor.getClass().getSimpleName() + "' for module '" + modProps.getModuleName() + "' installed; output '" + modProps.getVisitorDataPath() + "'");
			}
		}
		getFw().write("\t\trepository.acquireEntities();\r\n");
		getFw().write("\t\tlinkage.linkEntities();\r\n");
		if (visitor != null) {
			getFw().write("\t\tpreprocessorStructureVisualizer.done(\"" + modProps.getModuleName() + "\");\r\n");
		}		
		getFw().write("\t}\r\n\r\n");
		getFw().write(JavaCodeGeneratorTemplates.s_VWMLModuleMethods);
		if (logger.isInfoEnabled()) {
			logger.info("Module '" + modProps.getModuleName() + "'; body - OK");
		}
	}

}
