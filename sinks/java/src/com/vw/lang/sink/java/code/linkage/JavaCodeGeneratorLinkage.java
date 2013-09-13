package com.vw.lang.sink.java.code.linkage;

import java.io.FileWriter;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.vw.lang.sink.entity.InterpretationOfUndefinedEntityStrategyId;
import com.vw.lang.sink.java.code.JavaCodeGenerator.JavaModuleStartProps;
import com.vw.lang.sink.java.code.JavaCodeGenerator.ModuleFiles;
import com.vw.lang.sink.java.code.JavaCodeGenerator.VWMLLinkWrap;
import com.vw.lang.sink.java.code.JavaCodeGenerator.VWMLOperationLink;
import com.vw.lang.sink.java.code.JavaCodeGeneratorComponent;
import com.vw.lang.sink.java.code.templates.JavaCodeGeneratorTemplates;
import com.vw.lang.sink.java.code.utils.JavaCodeGeneratorUtils;
import com.vw.lang.sink.java.entity.VWMLEntity;
import com.vw.lang.sink.java.entity.undefined.strategy.UndefinedEntityAsEmptyComplexEntityInterpretationStrategy;
import com.vw.lang.sink.java.entity.undefined.strategy.UndefinedEntityAsEntityInterpretationStrategy;
import com.vw.lang.sink.java.entity.undefined.strategy.UndefinedEntityAsNilEntityInterpretationStrategy;
import com.vw.lang.sink.java.entity.undefined.strategy.UndefinedEntityStrictInterpretationStrategy;
import com.vw.lang.sink.java.link.IVWMLLinkVisitor;
import com.vw.lang.sink.java.link.VWMLLinkage;
import com.vw.lang.sink.java.operations.VWMLOperation;
import com.vw.lang.sink.java.operations.VWMLOperationsCode;
import com.vw.lang.sink.utils.EntityWalker.REL;

/**
 * Generates code related to linkage phase
 * @author ogibayev
 *
 */
public class JavaCodeGeneratorLinkage extends JavaCodeGeneratorComponent {

	private Logger logger = Logger.getLogger(JavaCodeGeneratorLinkage.class);
	
	public JavaCodeGeneratorLinkage() {
		
	}
	
	public JavaCodeGeneratorLinkage(FileWriter fw) {
		super(fw);
	}

	/**
	 * Links acquired objects 
	 * @param modProps
	 * @param linkage
	 * @param interpret
	 * @param markedAsTerm
	 * @param operations
	 * @throws Exception
	 */
	public void buildModuleLinkagePart(JavaModuleStartProps modProps,
			                           List<VWMLLinkWrap> linkage,
			                           List<VWMLLinkWrap> interpret,
			                           List<Object> markedAsTerm,
			                           Map<Object, VWMLOperationLink> operations) throws Exception {
		// caption and common imports are added before (see startModule) method
		String linkageImports[] = {
				"java.util.HashMap",
				"java.util.Map",
				VWMLEntity.class.getName(),
				VWMLOperationsCode.class.getName(),
				VWMLOperation.class.getName(),
				VWMLLinkage.class.getName(),
				IVWMLLinkVisitor.class.getName(),
				InterpretationOfUndefinedEntityStrategyId.class.getName(),
				UndefinedEntityAsEmptyComplexEntityInterpretationStrategy.class.getName(),
				UndefinedEntityAsEntityInterpretationStrategy.class.getName(),
				UndefinedEntityAsNilEntityInterpretationStrategy.class.getName(),
				UndefinedEntityStrictInterpretationStrategy.class.getName()
		};
		for(String linkageImport : linkageImports) {
			getFw().write("import " + linkageImport + ";\r\n");
		}
		// starts class definition
		getFw().write("\r\n" + JavaCodeGeneratorUtils.generateClassDef(ModuleFiles.LINKAGE.toValue(), " extends VWMLLinkage", modProps));
		// wrapper for linked objects
		getFw().write("\r\n" + JavaCodeGeneratorTemplates.s_VWMLLinkWrapTemplate);
		// generates list of wrapped objects prepared for linkage operation
		String listOfLinkedObjects = generateLinkedObjectsDefinition("linkedObjectPairs", linkage);
		getFw().write(listOfLinkedObjects);
		String listOfInterpretedObjects = generateLinkedObjectsDefinition("interpretedObjectPairs", interpret);
		getFw().write(listOfInterpretedObjects);
		String operation2ObjectsAssociation = generateOperation2ObjectAssociations(operations);
		getFw().write(operation2ObjectsAssociation);
		String entityMarkedAsTerm = JavaCodeGeneratorUtils.generateObjectsDefinition("entityMarkedAsTerms", markedAsTerm);
		getFw().write(entityMarkedAsTerm);
		getFw().write("\tprivate IVWMLLinkVisitor preprocessorStructureVisualizer = null;\r\n\r\n");
		// defined strategy for undefined entity
		getFw().write(generateDeclarationsFromInterpretationProps(modProps) + "\r\n");
		// and entity's history size
		getFw().write(generateEntitySpecificDeclaration(modProps) + "\r\n");
		// link and aux. methods
		String[] methodsDef =  {
								JavaCodeGeneratorTemplates.s_VWMLLinkageCodeTemplate,
							   };
		for(String methodDef : methodsDef) {
			getFw().write(methodDef);
		}
		// closes class
		getFw().write("}\r\n");
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
			String s = JavaCodeGeneratorUtils.convertStaticStringArrayToString(obj.getContextPath());
			String arrayAsStr = (s == null) ? null : "\"" + s + "\"";
			if (!obj.isAsTerm()) {
				list += "\r\n\t\tnew VWMLLinkWrap(\"" + obj.getId() + "\", \"" + obj.getLinkedId() + "\", VWMLLinkWrap.MARKED.ENTITY, \"" + obj.getUniqId() + "\", \"" + "\", " + arrayAsStr + ")";
			}
			else {
				String mark = "VWMLLinkWrap.MARKED.TERM";
				if (obj.isAsLifeTerm()) {
					mark = "VWMLLinkWrap.MARKED.LIFETERM";
				}
				list += "\r\n\t\tnew VWMLLinkWrap(\"" + obj.getId() + "\", \"" + obj.getLinkedId() + "\", " + mark + ", \"" + obj.getUniqId() + "\", \"" + "\", " + arrayAsStr + ")";
			}
			ft = false;
		}
		list += "\r\n\t};\r\n\r\n";
		return list;
	}

	private String generateDeclarationsFromInterpretationProps(JavaModuleStartProps modProps) {
		String s = "\tprivate final InterpretationOfUndefinedEntityStrategyId interpretationOfUndefinedEntityStrategyId = InterpretationOfUndefinedEntityStrategyId.";
		if (modProps.getInterpretationProps() != null) {
			s += modProps.getInterpretationProps().getInterpretationOfUndefinedEntityStrategyId().toValue().toUpperCase() + ";\r\n";
		}
		else {
			s += InterpretationOfUndefinedEntityStrategyId.STRICT.toValue().toUpperCase() + ";\r\n";
		}
		return s;
	}

	private String generateEntitySpecificDeclaration(JavaModuleStartProps modProps) {
		String s = "\tprivate final int entityHistorySize = ";
		if (modProps.getEntityHistorySize() != null) {
			s += modProps.getEntityHistorySize() + ";\r\n";
		}
		else {
			s += "0;\r\n";
		}
		return s;
	}
	
	private String generateOperation2ObjectAssociations(Map<Object, VWMLOperationLink> operations) {
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
}
