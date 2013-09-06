package com.vw.lang.sink.java.code.repository;

import java.io.FileWriter;
import java.util.List;

import org.apache.log4j.Logger;

import com.vw.lang.sink.java.VWMLObjectBuilder;
import com.vw.lang.sink.java.VWMLObjectBuilder.VWMLObjectType;
import com.vw.lang.sink.java.code.JavaCodeGenerator.JavaModuleStartProps;
import com.vw.lang.sink.java.code.JavaCodeGenerator.ModuleFiles;
import com.vw.lang.sink.java.code.JavaCodeGenerator.VWMLObjWrap;
import com.vw.lang.sink.java.code.JavaCodeGeneratorComponent;
import com.vw.lang.sink.java.code.templates.JavaCodeGeneratorTemplates;
import com.vw.lang.sink.java.code.utils.JavaCodeGeneratorUtils;
import com.vw.lang.sink.java.link.IVWMLLinkVisitor;
import com.vw.lang.sink.java.repository.VWMLRepository;
import com.vw.lang.sink.utils.ComplexEntityNameBuilder;

/**
 * Generates code for repository phase
 * @author ogibayev
 *
 */
public class JavaCodeGeneratorRepository extends JavaCodeGeneratorComponent {

	private Logger logger = Logger.getLogger(JavaCodeGeneratorRepository.class);
	
	public JavaCodeGeneratorRepository() {
		super();
	}
	
	public JavaCodeGeneratorRepository(FileWriter fw) {
		super(fw);
	}

   /**
	* Builds repository part - defines method 'build' which adds entities
    * listed in VWML's code
	* @param modProps
	* @param declaredObjects
	* @throws Exception
	*/
	public void buildModuleRepositoryPart(JavaModuleStartProps modProps,
			                              List<VWMLObjWrap> declaredObjects) throws Exception {
		declaredObjects.add(new VWMLObjWrap(VWMLObjectType.COMPLEX_ENTITY, ComplexEntityNameBuilder.generateRootId(modProps.getModuleName())));
		// caption and common imports are added before (see startModule) method
		getFw().write("import " + VWMLRepository.class.getCanonicalName() + ";\r\n");
		getFw().write("import " + VWMLObjectBuilder.VWMLObjectType.class.getCanonicalName() + ";\r\n");		
		// adds visitor's interface in any case
		getFw().write("import " + IVWMLLinkVisitor.class.getName() + ";\r\n");
		// starts class definition
		getFw().write("\r\n" + JavaCodeGeneratorUtils.generateClassDef(ModuleFiles.REPOSITORY.toValue(), " extends VWMLRepository ", modProps));
		getFw().write("\tprivate IVWMLLinkVisitor preprocessorStructureVisualizer = null;\r\n\r\n");
		getFw().write(JavaCodeGeneratorTemplates.s_VWMLRepositoryCodeTemplate);
		// adds method's 'build' definition
		getFw().write("\tpublic void acquireEntities() throws Exception {\r\n");
		for(VWMLObjWrap obj : declaredObjects) {
			getFw().write("\t\t// constructs entity '" + obj.getObjId() + "'\r\n");
			getFw().write("\t\tVWMLObjectsRepository.acquire(" + obj.getType().getClass().getSimpleName()+ "." + obj.getType().toValue() + ", \"" + obj.getObjId() + "\", preprocessorStructureVisualizer);\r\n");
		}
		// closes 'build' method
		getFw().write("\t}\r\n");
		// closes class
		getFw().write("}\r\n");
		if (logger.isInfoEnabled()) {
			logger.info("Module '" + modProps.getModuleName() + "'; repository - OK");
		}		
	}
}
