package com.vw.lang.sink.java.code.templates;

/**
 * Parts of code needed to java code generator
 * @author ogibayev
 *
 */
public final class JavaCodeGeneratorTemplates {
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
	"\t}\r\n";

	public static String s_VWMLLinkageCodeTemplate = "" +
	"\tpublic void link() throws Exception {\r\n" +
		"\t\tfor(VWMLLinkWrap obj : linkedObjectPairs) {\r\n" +
			"\t\tVWMLObject entity = getEntityById(obj.getId());\r\n" +
			"\t\tVWMLObject linkedEntity = getEntityById(obj.getLinkedId());\r\n" +
			"\t\tentity.link(linkedEntity);\r\n" +
		"\t\t}\r\n\r\n" +
	"\t}\r\n\r\n";
	
	public static String s_VWMLInterpretingCodeTemplate = "" +
	"\tpublic void interpret() throws Exception {\r\n" +
		"\t\tfor(VWMLLinkWrap obj : interpretedObjectPairs) {\r\n" +
			"\t\tVWMLEntity entity = (VWMLEntity)getEntityById(obj.getId());\r\n" +
			"\t\tVWMLEntity linkedEntity = (VWMLEntity)getEntityById(obj.getLinkedId());\r\n" +
			"\t\tentity.interpret(linkedEntity);\r\n" +
		"\t\t}\r\n\r\n" +
	"\t}\r\n\r\n";
	
	public static String s_VWMLLinkageAuxCodeTemplate = "" +
	"\tprivate VWMLObject getEntityById(Object id) {\r\n" +
	"\t\tVWMLObject entity = VWMLObjectsRepository.instance().get(id);\r\n" +
	"\t\tif (entity == null) {\r\n" +
		"\t\t\tthrow new Exception(\"unrecognized entity '\" + id + \"'\")\r\n" +
	"\t\t}\r\n" +
	"\t}\r\n\r\n";
}
