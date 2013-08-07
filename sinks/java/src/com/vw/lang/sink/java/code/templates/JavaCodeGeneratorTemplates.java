package com.vw.lang.sink.java.code.templates;

import com.vw.lang.sink.java.entity.VWMLEntity;
import com.vw.lang.sink.java.operations.VWMLOperationsCode;

/**
 * Parts of code needed to java code generator
 * @author ogibayev
 *
 */
public final class JavaCodeGeneratorTemplates {
	
	public static String s_VWMLRepositoryCodeTemplate = "" +
	"\tpublic void setPreprocessorStructureVisualizer(IVWMLLinkVisitor preprocessorStructureVisualizer) {\r\n" +
		"\t\tthis.preprocessorStructureVisualizer = preprocessorStructureVisualizer;\r\n" +
	"\t}\r\n\r\n";
	
	public static String s_VWMLLinkWrapTemplate = "" +
	"\tprivate static class VWMLLinkWrap { \r\n" +
	"\t\tprivate Object id; \r\n" +
	"\t\tprivate Object linkedId;\r\n\r\n" +
	"\t\tpublic VWMLLinkWrap(Object id, Object linkedId) {\r\n" +
	"\t\t\tsuper();\r\n" +
	"\t\t\tthis.id = id;\r\n" +
	"\t\t\tthis.linkedId = linkedId;\r\n" +
	"\t\t}\r\n\r\n" +
	"\t\tpublic Object getId() {\r\n" +
	"\t\t\treturn id;\r\n" +
	"\t\t}\r\n\r\n" +
	"\t\tpublic Object getLinkedId() {\r\n" +
	"\t\t\treturn linkedId;\r\n" +
	"\t\t}\r\n" +
	"\t}\r\n\r\n";

	public static String s_VWMLLinkageCodeTemplate = "" +
	"\tpublic void linkEntities() throws Exception {\r\n" +
	"\t\tbuildLinkingAssociation();\r\n" +
	"\t\tbuildInterpretingAssociation();\r\n" +
	"\t\tbuildOperationsAssociation();\r\n" +	
	"\t}\r\n\r\n" +
	"\tprotected void buildLinkingAssociation() throws Exception {\r\n" +
		"\t\tfor(VWMLLinkWrap obj : linkedObjectPairs) {\r\n" +
			"\t\t\tVWMLObject entity = getEntityById(obj.getId());\r\n" +
			"\t\t\tVWMLObject linkedEntity = getEntityById(obj.getLinkedId());\r\n" +
			"\t\t\tentity.link(linkedEntity);\r\n" +
		"\t\t}\r\n\r\n" +
	"\t}\r\n\r\n" +
	"\tprotected void buildInterpretingAssociation() throws Exception {\r\n" +
		"\t\tfor(VWMLLinkWrap obj : interpretedObjectPairs) {\r\n" +
			"\t\t\tVWMLEntity entity = (VWMLEntity)getEntityById(obj.getId());\r\n" +
			"\t\t\tVWMLEntity linkedEntity = (VWMLEntity)getEntityById(obj.getLinkedId());\r\n" +
			"\t\t\tentity.setInterpreting(linkedEntity);\r\n" +
		"\t\t}\r\n\r\n" +
	"\t}\r\n\r\n" +
	"\tprotected void buildOperationsAssociation() throws Exception {\r\n" +
	"\t\tfor(Object id : appliedOperations.keySet()) {\r\n" +
		"\t\t\tString[] ops = appliedOperations.get(id);\r\n" +
		"\t\t\tVWMLEntity entity = (VWMLEntity)getEntityById(id);\r\n" +
		"\t\t\tfor(String op : ops) {\r\n" +
			"\t\t\t\tVWMLOperationsCode opCode = VWMLOperationsCode.fromValue(op);\r\n" +
			"\t\t\t\tif (opCode == VWMLOperationsCode.OPNOP) {\r\n" +
				"\t\t\t\t\tthrow new Exception(\"invalid operation '\" + op + \"'\");\r\n" +
			"\t\t\t\t}\r\n" +
			"\t\t\t\tentity.addOperation(new VWMLOperation(opCode));\r\n" +
		"\t\t\t}\r\n" +
	"\t\t}\r\n" +
	"\t}\r\n\r\n" +	
	"\tprivate VWMLObject getEntityById(Object id) throws Exception {\r\n" +
	"\t\tVWMLObject entity = VWMLObjectsRepository.instance().get(id);\r\n" +
	"\t\tif (entity == null) {\r\n" +
		"\t\t\tthrow new Exception(\"unrecognized entity '\" + id + \"'\");\r\n" +
	"\t\t}\r\n" +
	"\t\treturn entity;\r\n" +
	"\t}\r\n\r\n";
	
	public static String s_VWMLModuleDebugMethods = "" +
	"\t/**\r\n" +
	"\t* Used in debug purposes when need to know name of rule which is going to be processed\r\n" +
	"\t* @param to\r\n" +
	"\t*/\r\n" +
	"\tpublic void debugEnter(Object to) {\r\n" +
	"\t}\r\n\r\n" +
	"\t/**\r\n" +
	"\t* Used in debug purposes when need to know name of rule which has been processed\r\n" +
	"\t* @param from\r\n" +
	"\t*/\r\n" +
	"\tpublic void debugExit(Object from) {\r\n" +
	"\t}\r\n\r\n" +
	"\t/**\r\n" +
	"\t* Traces independent string\r\n" +
	"\t* @param trace\r\n" +
	"\t*/\r\n" +
	"\tpublic void trace(String trace) {\r\n" +
	"\t}\r\n\r\n";
}