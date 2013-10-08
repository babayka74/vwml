package com.vw.lang.sink.java.code.templates;



/**
 * Parts of code needed to java code generator
 * @author ogibayev
 *
 */
public final class JavaCodeGeneratorTemplates {
	
	public static String s_VWMLRepositoryCodeTemplate = "" +
	"\tpublic void setPreprocessorStructureVisualizer(AbstractVWMLLinkVisitor preprocessorStructureVisualizer) {\r\n" +
		"\t\tthis.preprocessorStructureVisualizer = preprocessorStructureVisualizer;\r\n" +
	"\t}\r\n\r\n" +
	"\tpublic AbstractVWMLLinkVisitor getPreprocessorStructureVisualizer() {\r\n" +
	"\t\treturn this.preprocessorStructureVisualizer;\r\n" +
	"\t}\r\n\r\n";
	
	public static String s_VWMLLinkWrapTemplate = "" +
	"\tprivate static class VWMLLinkWrap { \r\n\r\n" +
	"\t\tpublic static enum MARKED { \r\n" +
	"\t\t\tENTITY,\r\n" +
	"\t\t\tTERM,\r\n" +
	"\t\t\tLIFETERM\r\n" +
	"\t\t} ;\r\n\r\n" + 
	"\t\tprivate Object id; \r\n" +
	"\t\tprivate Object linkedId;\r\n" +
	"\t\tprivate MARKED marked = MARKED.ENTITY;\r\n" +
	"\t\tprivate Object uniqId = null;\r\n\r\n" +
	"\t\tprivate String[] contextPath = null;\r\n\r\n" +
	"\t\tprivate String context = null;\r\n\r\n" +
	"\t\tpublic VWMLLinkWrap(Object id, Object linkedId) {\r\n" +
	"\t\t\tsuper();\r\n" +
	"\t\t\tthis.id = id;\r\n" +
	"\t\t\tthis.linkedId = linkedId;\r\n" +
	"\t\t}\r\n\r\n" +
	"\t\tpublic VWMLLinkWrap(Object id, Object linkedId, MARKED marked) {\r\n" +
	"\t\t\tsuper();\r\n" +
	"\t\t\tthis.id = id;\r\n" +
	"\t\t\tthis.linkedId = linkedId;\r\n" +
	"\t\t\tthis.marked = marked;\r\n" +
	"\t\t}\r\n\r\n" +
	"\t\tpublic VWMLLinkWrap(Object id, Object linkedId, MARKED marked, Object uniqId) {\r\n" +
	"\t\t\tsuper();\r\n" +
	"\t\t\tthis.id = id;\r\n" +
	"\t\t\tthis.linkedId = linkedId;\r\n" +
	"\t\t\tthis.marked = marked;\r\n" +
	"\t\t\tthis.uniqId = uniqId;\r\n" +
	"\t\t}\r\n\r\n" +
	"\t\tpublic VWMLLinkWrap(Object id, Object linkedId, MARKED marked, Object uniqId, String context, String[] contextPath) {\r\n" +
	"\t\t\tsuper();\r\n" +
	"\t\t\tthis.id = id;\r\n" +
	"\t\t\tthis.linkedId = linkedId;\r\n" +
	"\t\t\tthis.marked = marked;\r\n" +
	"\t\t\tthis.uniqId = uniqId;\r\n" +
	"\t\t\tthis.contextPath = contextPath;\r\n" +
	"\t\t\tthis.context = context;\r\n" +
	"\t\t}\r\n\r\n" +	
	"\t\tpublic Object getId() {\r\n" +
	"\t\t\treturn id;\r\n" +
	"\t\t}\r\n\r\n" +
	"\t\tpublic Object getLinkedId() {\r\n" +
	"\t\t\treturn linkedId;\r\n" +
	"\t\t}\r\n\r\n" +
	"\t\tpublic Object getUniqId() {\r\n" +
	"\t\t\treturn uniqId;\r\n" +
	"\t\t}\r\n\r\n" +
	"\t\tpublic boolean isMarkedAsTerm() {\r\n" +
	"\t\t\treturn (marked == MARKED.TERM) || isMarkedAsLifeTerm();\r\n" +
	"\t\t}\r\n\r\n" +
	"\t\tpublic boolean isMarkedAsLifeTerm() {\r\n" +
	"\t\t\treturn (marked == MARKED.LIFETERM);\r\n" +
	"\t\t}\r\n\r\n" +	
	"\t\tpublic String[] getContextPath() {\r\n" +
	"\t\t\treturn contextPath;\r\n" +
	"\t\t}\r\n\r\n" +
	"\t\tpublic String getContext() {\r\n" +
	"\t\t\treturn context;\r\n" +
	"\t\t}\r\n\r\n" +
	"\t}\r\n\r\n" +
	"\tprivate static class VWMLOperationLink { \r\n\r\n" +
	"\t\tpublic static enum REL {  \r\n" +
	"\t\t\tASSOCIATION,  \r\n" +
	"\t\t\tLINK,  \r\n" +
	"\t\t\tNONE  \r\n" +
	"\t\t}  \r\n\r\n" +	
	"\t\tprivate Object entityId; \r\n" +
	"\t\tprivate Object linkId; \r\n" +
	"\t\tprivate VWMLOperationLink.REL rel; \r\n" +
	"\t\tprivate String[] associatedOperations; \r\n\r\n" +
	"\t\tpublic VWMLOperationLink() { \r\n" +
	"\t\t\tsuper(); \r\n" +
	"\t\t} \r\n\r\n" +
	"\t\tpublic VWMLOperationLink(Object entityId, Object linkId, String[] associatedOperations, VWMLOperationLink.REL rel) { \r\n" +
	"\t\t\tsuper(); \r\n" +
	"\t\t\tthis.entityId = entityId; \r\n" +
	"\t\t\tthis.linkId = linkId; \r\n" +
	"\t\t\tthis.associatedOperations = associatedOperations; \r\n" +
	"\t\t\tthis.rel = rel; \r\n" +
	"\t\t} \r\n\r\n" +
	"\t\tpublic Object getEntityId() { \r\n" + 
	"\t\t\treturn entityId;\r\n" +
	"\t\t}\r\n\r\n" +
	"\t\tpublic Object getLinkId() {\r\n" +
	"\t\t\treturn linkId;\r\n" +
	"\t\t}\r\n\r\n" +
	"\t\tpublic String[] getAssociatedOperations() {\r\n" +
	"\t\t\treturn associatedOperations;\r\n" +
	"\t\t}\r\n" +
	"\t\tpublic VWMLOperationLink.REL getRel() {\r\n" +
	"\t\t\treturn rel;\r\n" +
	"\t\t}\r\n" +
	"\t}\r\n\r\n";

	public static String s_VWMLLinkageCodeTemplate = "" +
	"\tpublic void setPreprocessorStructureVisualizer(AbstractVWMLLinkVisitor preprocessorStructureVisualizer) {\r\n" +
	"\t\tthis.preprocessorStructureVisualizer = preprocessorStructureVisualizer;\r\n" +
	"\t}\r\n\r\n" +
	"\tpublic AbstractVWMLLinkVisitor getPreprocessorStructureVisualizer() {\r\n" +
	"\t\treturn this.preprocessorStructureVisualizer;\r\n" +
	"\t}\r\n\r\n" +
	"\tpublic InterpretationOfUndefinedEntityStrategyId getUndefinedEntityInterpretationStrategyId() {\r\n" +
	"\t\treturn interpretationOfUndefinedEntityStrategyId;\r\n" +
	"\t}\r\n\r\n" +
	"\tpublic int getEntityHistorySize() {\r\n" +
	"\t\treturn entityHistorySize;\r\n" +
	"\t}\r\n\r\n" +
	"\tpublic void linkEntities() throws Exception {\r\n" +
	"\t\tbuildLinkingAssociation();\r\n" +
	"\t\tbuildInterpretingAssociation();\r\n" +
	"\t}\r\n\r\n" +
	"\tpublic VWMLObject interpretUndefinedEntity(Object id, String context) throws Exception {\r\n" +
	"\t\tif (interpretationOfUndefinedEntityStrategyId == InterpretationOfUndefinedEntityStrategyId.STRICT) {\r\n" +
	"\t\t\treturn new UndefinedEntityStrictInterpretationStrategy().process(context, id, getPreprocessorStructureVisualizer(), this);\r\n" +
	"\t\t}\r\n" +
	"\t\telse\r\n" +
	"\t\tif (interpretationOfUndefinedEntityStrategyId == InterpretationOfUndefinedEntityStrategyId.UE_IM1) {\r\n" +
	"\t\t\treturn new UndefinedEntityAsEmptyComplexEntityInterpretationStrategy().process(context, id, getPreprocessorStructureVisualizer(), this);\r\n" +
	"\t\t}\r\n" +
	"\t\telse\r\n" +
	"\t\tif (interpretationOfUndefinedEntityStrategyId == InterpretationOfUndefinedEntityStrategyId.UE_IM2) {\r\n" +
	"\t\t\treturn new UndefinedEntityAsNilEntityInterpretationStrategy().process(context, id, getPreprocessorStructureVisualizer(), this);\r\n" +
	"\t\t}\r\n" +
	"\t\telse\r\n" +
	"\t\tif (interpretationOfUndefinedEntityStrategyId == InterpretationOfUndefinedEntityStrategyId.UE_IM3) {\r\n" +
	"\t\t\treturn new UndefinedEntityAsEntityInterpretationStrategy().process(context, id, getPreprocessorStructureVisualizer(), this);\r\n" +
	"\t\t}\r\n" +
	"\t\treturn null;\r\n" +
	"\t}\r\n\r\n" +	
	"\tprotected void buildLinkingAssociation() throws Exception {\r\n" +
		"\t\tfor(VWMLLinkWrap obj : linkedObjectPairs) {\r\n" +
			"\t\t\tVWMLObject entity = getEntityById(obj.getId(), obj.getContext());\r\n" +
			"\t\t\tVWMLObject linkedEntity = processLinkedEntity(obj);\r\n" +
			"\t\t\tif (obj.isMarkedAsLifeTerm()) {\r\n" +
			"\t\t\t\t((VWMLEntity)linkedEntity).setLifeTerm(true);\r\n" +
			"\t\t\t\tVWMLLinkage.addLifeTerm((VWMLEntity)linkedEntity);\r\n" +
			"\t\t\t}\r\n" +
			"\t\t\tentity.link(linkedEntity);\r\n" +
		"\t\t}\r\n\r\n" +
	"\t}\r\n\r\n" +
	"\tprotected void buildInterpretingAssociation() throws Exception {\r\n" +
		"\t\tfor(VWMLLinkWrap obj : interpretedObjectPairs) {\r\n" +
			"\t\t\tVWMLEntity entity = (VWMLEntity)getEntityById(obj.getId(), obj.getContext());\r\n" +
			"\t\t\tVWMLEntity linkedEntity = (VWMLEntity)processLinkedEntity(obj);\r\n" +
			"\t\t\tentity.setInterpreting(linkedEntity);\r\n" +
		"\t\t}\r\n\r\n" +
	"\t}\r\n\r\n" +
	"\tprotected void buildOperationsAssociationFor(VWMLEntity entity, Object uniqId, String context) throws Exception {\r\n" +
	"\t\tVWMLOperationLink link = appliedOperations.get(uniqId);\r\n" +
	"\t\tif (link != null) {\r\n" +
	"\t\t\tString[] ops = link.getAssociatedOperations();\r\n" +
	"\t\t\tfor(String op : ops) {\r\n" +
	"\t\t\t\tVWMLOperationsCode opCode = VWMLOperationsCode.fromValue(op);\r\n" +
	"\t\t\t\tif (opCode == VWMLOperationsCode.OPNOP) {\r\n" +
	"\t\t\t\t\tthrow new Exception(\"invalid operation '\" + op + \"'\");\r\n" +
	"\t\t\t\t}\r\n" +
	"\t\t\t\tentity.addOperation(new VWMLOperation(opCode));\r\n" +
	"\t\t\t}\r\n" +
	"\t\t}\r\n" +
	"\t}\r\n\r\n" +
	"\tprivate VWMLObject getEntityById(Object id, String context) throws Exception {\r\n" +
	"\t\tVWMLContext c = VWMLContextsRepository.instance().get(context);\r\n" +
	"\t\tif (c == null) {\r\n" +
	"\t\t\tthrow new Exception(\"coudln't find context '\" + context + \"'\");\r\n" +
	"\t\t}\r\n" +
	"\t\tVWMLObject entity = VWMLObjectsRepository.instance().get(id, c);\r\n" +
	"\t\tif (entity == null) {\r\n" +
	"\t\t\tentity = interpretUndefinedEntity(id, context);\r\n" +
	"\t\t\tif (entity == null) {\r\n" +
	"\t\t\t\tthrow new Exception(\"undefined entity \'\" + id + \"'; context '\" + context + \"'\");\r\n" +
	"\t\t\t}\r\n" +	
	"\t\t}\r\n" +
	"\t\treturn entity;\r\n" +
	"\t}\r\n\r\n" +
	"\tprivate VWMLObject getTermById(Object id, Object termId, String context) throws Exception {\r\n" +
	"\t\tVWMLEntity e = (VWMLEntity)getEntityById(id, context);\r\n" +
	"\t\tVWMLTerm term = (VWMLTerm)VWMLObjectsRepository.acquire(VWMLObjectType.TERM, termId, context, e.getInterpretationHistorySize(), e.getLink().getLinkOperationVisitor());\r\n" +
	"\t\tterm.setAssociatedEntity(e);\r\n" +
	"\t\treturn term;\r\n" +
	"\t}\r\n\r\n" +
	"\tprivate VWMLObject processLinkedEntity(VWMLLinkWrap obj) throws Exception {\r\n" +
	"\t\tVWMLObject linkedEntity = null;\r\n" +
	"\t\tif (obj.isMarkedAsTerm()) {\r\n" +
	"\t\t\tlinkedEntity = getTermById(obj.getLinkedId(), obj.getUniqId(), obj.getContext());\r\n" +
	"\t\t\tbuildOperationsAssociationFor((VWMLEntity)linkedEntity, obj.getUniqId(), obj.getContext());\r\n" +
	"\t\t}\r\n" +
	"\t\telse {\r\n" +
	"\t\t\tlinkedEntity = getEntityById(obj.getLinkedId(), obj.getContext());\r\n" +
	"\t\t}\r\n" +
	"\t\treturn linkedEntity;\r\n" +
	"\t}\r\n\r\n";
	
	public static String s_VWMLModuleMethods = "" +
	"\tpublic VWMLRepository getRepository() {\r\n" +
	"\t\treturn repository;\r\n" +
	"\t}\r\n\r\n" +
	"\tpublic VWMLLinkage getLinkage() {\r\n" +
	"\t\treturn linkage;\r\n" +
	"\t}\r\n\r\n" +
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
