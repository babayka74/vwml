
/**
---------------------- Virtual World's language grammar ------------------------------------

*/

grammar VirtualWorldModelingLanguage;
 
tokens {
    IAS='ias';
    LIFETERM='lifeterm';
    OPJOIN='Join';
    OPINTERSECT='Intersect';
    OPSUBSTRUCT='Substruct';
    OPFIRST='First';
    OPLAST='Last';
    OPBEGIN='Begin';
    OPREST='Rest';
    OPCARTESIAN='Cartesian';
    OPRANDOM='Random';
    OPIN='In';
    OPINCL='Include';
    OPEQ='Eq';
    OPIDENT='Ident';
    OPSQU='Squeeze';
    OPINTERPRET='~';
    OPCREATEEXPR= '^';
    OPEXECUTE='Exe';
    OPACTIVATECTX=':';
    OPACTIVATEONFRINGE='Do';
    OPRELAX='Relax';
    OPSTARTCONFLICTGROUP = '[';
    OPENDCONFLICTGROUP = ']';
    OPBREAKPOINT = 'Bp';
    OPAPPLYTOCONTEXT='Context';
    OPCLONE='Clone';
    OPBORN='Born';   
    OPPROJECTION='Projection';
    OPFOREACH='ForEach';
    OPDYNCONTEXT='->';
    OPSIZE='Size';
    OPINTERRUPT='Interrupt';
    OPCALLP='CallP';
    OPGET='Get';
    OPFIND='Find';
    OPGATE='Gate';
    OPRECALL='Recall';
    OPREPEAT='Repeat';
    OPACTIVATE='Activate';
    OPCOPY='Copy';
    OPSTARTINTERCEPTION='StartInterception';
    OPFINISHINTERCEPTION='FinishInterception';
    OPSTARTINTERCEPTION_S='SI';
    OPFINISHINTERCEPTION_S='FI';
    OPRELEASE='Release';
    OPEXISTSI='ExistsI';
    OPEXISTSI_S='EI';
    OPLTT='LTT';
    // languages
    JAVA='__java__';
    C='__c__';
    CPP='__cpp__';
    OBJECTIVEC='__objective_c__';
    // DIRECTIVES
    P_DEBUG  = '#if_debug';
    P_IF     = '#if';
    P_ELSE   = '#else';
    P_ENDIF  = '#endif';
    P_OP_AND = '&';
    P_OP_OR  = '|';
    P_OP_B   = '>';
    P_OP_L   = '<';
    P_OP_E   = '=';
}

@header {
package com.vw.lang.grammar;

// Exceptions
import java.lang.Throwable;

// builder
import com.vw.lang.processor.model.builder.VWMLModelBuilder;
import com.vw.lang.processor.model.builder.VWMLModuleInfo;
import com.vw.lang.processor.context.builder.VWMLContextBuilder;
import com.vw.lang.processor.context.builder.VWMLContextBuilder.ContextBunch;
import com.vw.lang.processor.context.builder.VWMLContextBuilder.ContextBunchElement;

// general code generator
import com.vw.lang.sink.ICodeGenerator;
import com.vw.lang.sink.ICodeGenerator.StartModuleProps;
import com.vw.lang.sink.utils.ComplexEntityNameBuilder;
import com.vw.lang.sink.utils.EntityWalker;
import com.vw.lang.sink.utils.EntityWalker.EntityDescriptor;
import com.vw.lang.sink.utils.EntityWalker.ComplexContextDescriptor;
import com.vw.lang.sink.utils.GeneralUtils;

// specific code generator
import com.vw.lang.sink.java.link.AbstractVWMLLinkVisitor;
import com.vw.lang.sink.java.code.JavaCodeGenerator;
import com.vw.lang.sink.java.code.JavaCodeGenerator.JavaModuleStartProps;

// preprocessor
import com.vw.lang.grammar.preprocessor.VWMLPreprocessor;
import com.vw.lang.grammar.preprocessor.VWMLPreprocessor.VWMLPreprocessorIfDirective;

// compilation sink
import com.vw.lang.processor.model.sink.CompilationSink;

// logger
import org.apache.log4j.Logger;

}

@lexer::header { 
package com.vw.lang.grammar;
}

@lexer::members {
        private static final int NATIVE_CODE_CHANNEL = 199;
}

@rulecatch {
    catch (RecognitionException e) {
        throw e;
    }
    catch (Exception e) {
    	rethrowVWMLExceptionAsRecognitionException(e);
    }
}


@members {

	public static class VWMLCodeGeneratorRecognitionException extends RecognitionException {
		private com.vw.lang.sink.OperationInfo opInfo;
		
		public VWMLCodeGeneratorRecognitionException() {
			super();
		}
		
		public VWMLCodeGeneratorRecognitionException(com.vw.lang.sink.OperationInfo opInfo) {
			initCause(new Throwable("compilation failed"));
			this.opInfo = opInfo;
		}
		
		public com.vw.lang.sink.OperationInfo getLastOperationInfo() {
			return opInfo;
		}
	}

	private VWMLModelBuilder vwmlModelBuilder = null;
	private VWMLContextBuilder vwmlContextBuilder = VWMLContextBuilder.instance();
	private VWMLContextBuilder.ContextBunch lastProcessedContextBunch = null;
	private ICodeGenerator codeGenerator = null;
	private StartModuleProps modProps = null;
	private ComplexEntityNameBuilder complexEntityNameBuilderDecl = ComplexEntityNameBuilder.instance();
	private ComplexEntityNameBuilder complexEntityNameBuilderDef = null;
	private EntityWalker entityWalker = EntityWalker.instance();
	private EntityWalker contextWalker = EntityWalker.instance();
	private EntityWalker.Relation lastProcessedEntity = null;
	private String activeFringe = null;
	private String lastDeclaredCreatureId = null;
	private String lastDeclaredCreatureProps = null;
	private String lastProcessedComplexEntityId = null;
	private boolean lastProcessedEntityAsTerm = false;
	private boolean sourceLifeTermDetectedFlag = false;
	private boolean conflictDefinitionOnRingStarted = false;
	private String modName = null;
 	private boolean inDebug = false;
 	private boolean moduleInProgress = false;
 	private List<String> deferredIncludes = new ArrayList<String>();
 	private List<String> externalContexts = new ArrayList<String>();
 	private List<String> externalEntities = new ArrayList<String>();
 	private VWMLPreprocessor preprocessor = VWMLPreprocessor.instance();
 	private CompilationSink compilationSink = null;
 	
 	private String lastProcessedIAS = null;
 	
 	private Logger logger = Logger.getLogger(this.getClass());
	
	
	@Override
	public String getErrorMessage(RecognitionException e, String[] tokenNames) {
		String msg = super.getErrorMessage(e, tokenNames);
		if (msg != null && msg.length() != 0) {
			logger.error(getErrorHeader(e) + " " + msg);
		}
		CompilationSink csink = getCompilationSink();
		if (csink != null) {
    			com.vw.lang.sink.OperationInfo opInfo = new com.vw.lang.sink.OperationInfo();
    			opInfo.setLine(e.line);
    			opInfo.setPosition(e.charPositionInLine);
    			opInfo.setFileName(getSourceName());
    			opInfo.setDescription(msg);
 			csink.delegateErrorCompilationMessage(opInfo);
		}
		return msg;
	}
	
	public void setVwmlModelBuilder(VWMLModelBuilder vwmlModelBuilder) {
		this.vwmlModelBuilder = vwmlModelBuilder;
	}
	
	public void setCompilationSink(CompilationSink compilationSink) {
		this.compilationSink = compilationSink;
	}
	
	public CompilationSink getCompilationSink() {
		return this.compilationSink;
	}
	
	public void setupProps() {
    		if (modProps == null) {
    			if (codeGenerator == null && vwmlModelBuilder.getProjectProps() != null) {
    				codeGenerator = vwmlModelBuilder.getProjectProps().getCodeGenerator();
    			}
			// instantiating module's properties which will be filled later
			modProps = (codeGenerator != null) ? codeGenerator.buildProps() : null;
			// tell to builder reference to module's properties
			if (vwmlModelBuilder.getProjectProps() == null) {
				vwmlModelBuilder.setProjectProps(modProps);
			}
		}	
	}

	public StartModuleProps getModuleProps() {
		return modProps;
	}
	
	public void setModuleProps(StartModuleProps modProps) {
		this.modProps = modProps;
	}

	protected void startModule(String modName) throws RecognitionException {
    		if (logger.isInfoEnabled()) {
    			logger.info("Compiling module '" + modName + "'");
    		}
    		setupProps();
    		// normalizes module's properties; if some properties were not set they are filled by project's properties
    		// ... so it is way to override them 
    		modProps = vwmlModelBuilder.normalizeProps(modProps);
    		// associates module's name with module info structure (will be used on last dource generation phase, especially during unit-test generation)
    		vwmlModelBuilder.addModuleInfo(modName, VWMLModuleInfo.build(modProps, null));
    		if (modProps != null) {
    			((JavaCodeGenerator.JavaModuleStartProps)modProps).setModuleName(modName);
	    		((JavaCodeGenerator.JavaModuleStartProps)modProps).setSourceName(getSourceName());
	    		try {
	    			if (codeGenerator == null) {
	    				codeGenerator = modProps.getCodeGenerator();
	    			}
	    			if (codeGenerator == null) {
	    				throw new Exception("Code generator can't be 'null'");
	    			}
	    			codeGenerator.startModule(modProps);
	    			moduleInProgress = true;
	    		}
	    		catch(Exception e) {
	    			logger.error("Caught exception '" + e + "'");
	    			rethrowVWMLExceptionAsRecognitionException(e);
	    		}
    		}
	}
	
	protected void endModule() throws RecognitionException {
        	try {
                	// sets special interpretation properties
                        // these properties are defined by user and passed by VWML tool to VWML builder 
                        modProps.setInterpretationProps(vwmlModelBuilder.getInterpretationProps());
                        // process externals
                        processExternals();
                        // actually generates source code
                        codeGenerator.generate(modProps);
                        // finalizes source generation phase for this module
                        codeGenerator.finishModule(modProps);
                        // module parsed and finished
                        moduleInProgress = false;
                        // includes processing now...
                        processDeferredIncludes();
                }
                catch(Exception e) {
		    	logger.error("Caught exception '" + e + "'");
		    	rethrowVWMLExceptionAsRecognitionException(e);
                }
	}
		
	protected void setInDebug(boolean inDebug) {
		this.inDebug = inDebug;
		vwmlModelBuilder.setDebug(inDebug);		
	}
	
	protected boolean isInDebug() {
		return this.inDebug;
	}
	
	protected void setActiveFringe(String activeFringe) {
		this.activeFringe = activeFringe;
	}
	
	protected String getActiveFringe() {
		return this.activeFringe;
	}
	
	protected void addLastDeclaredCreature(String creatureId) {
		lastDeclaredCreatureId = creatureId;
	}
	
	protected void addLastDeclaredCreatureProps(String creatureProps) {
		lastDeclaredCreatureProps = creatureProps;
	}
	
	protected String getLastDeclaredCreature() {
		return lastDeclaredCreatureId;
	}
	
	protected String getLastDeclaredCreatureProps() {
		return lastDeclaredCreatureProps;
	}

	protected String simpleEntityDeclaration(String id) throws RecognitionException {
    		// means that complex entity's name is being built
    		if (complexEntityNameBuilderDecl.isInProgress()) {
    		        if (logger.isDebugEnabled()) {
    		        	logger.debug("simple entity '" + id + "' is added as part of complex entity");
    		        }
    			complexEntityNameBuilderDecl.addObjectId(id);
    		}
    		else {
    			try {
    				VWMLContextBuilder.Contexts contexts = vwmlContextBuilder.buildContext();
    				if (codeGenerator != null) {
    					for(String c = contexts.first(); c != null; c = contexts.next()) {
    						codeGenerator.declareSimpleEntity(id, c);
     		        			if (logger.isDebugEnabled()) {
    		        				logger.debug("simple entity '" + id + "' is declared; context '" + c + "'");
    		        			}
    					}
    				}
    			}
    			catch(Exception e) {
    				rethrowVWMLExceptionAsRecognitionException(e);
    			}
    		}
    		return id;
	}
	
	protected void complexEntityDeclarationPhase1() throws RecognitionException {
    	    	complexEntityNameBuilderDecl.startProgress();
	    	if (logger.isDebugEnabled()) {
	    		logger.debug("complex entity declaration process - started");
	    	}	
	}
	
	protected void complexEntityDeclarationPhase2() throws RecognitionException {
		complexEntityNameBuilderDecl.stopProgress();
	    	if (logger.isDebugEnabled()) {
	    		logger.debug("complex entity declaration process - stopped");
	    	}			
	}
	
	protected String complexEntityDeclarationPhase3() throws RecognitionException {
              	String id = complexEntityNameBuilderDecl.build();
              	complexEntityNameBuilderDecl.clear();
		try {
			VWMLContextBuilder.Contexts contexts = vwmlContextBuilder.buildContext();
			if (codeGenerator != null) {
				for(String c = contexts.first(); c != null; c = contexts.next()) {
					codeGenerator.declareComplexEntity(id, null, c);
				}
			}    	
			if (logger.isDebugEnabled()) {
				logger.debug("complex entity '" + id + "' is declared; contexts '" + contexts + "'");
				logger.debug("complex entity declaration process - finished");
			}    	
		}
		catch(Exception e) {
			rethrowVWMLExceptionAsRecognitionException(e);
		}
		return id;            					
	}

	protected void declareAbsoluteContextByIASRelation() throws RecognitionException {
		// point to check deffered actions on effective context
		unwindEffectiveContext();
    		// get context's bunch from stack
    		Object bunch = vwmlContextBuilder.peek();
    		entityWalker.markFutureEntityAsIAS(bunch);
    		if (logger.isDebugEnabled()) {
    			logger.debug("Context bunch '" + bunch + "' was marked as IAS - pushed to stack");
    		}
    		if (codeGenerator != null) {
    			VWMLContextBuilder.Contexts contexts = vwmlContextBuilder.buildContext();
    			for(String c = contexts.first(); c != null; c = contexts.next()) {
    				codeGenerator.declareContext(c);
    				if (logger.isDebugEnabled()) {
    					logger.debug("Context '" + c + "' was declared");
    				}
    			}
    		}
	}

	protected void handleProcessedAbsoluteContextbyIASRelation() {
    		Object bunch = vwmlContextBuilder.peek();
    		if (lastProcessedEntity != null) {
    			if (logger.isDebugEnabled()) {
    				logger.debug("Context bunch '" + bunch + "' which was marked as IAS - removed from context builder stack");
    			}
    		      	vwmlContextBuilder.pop();
    			if (logger.isDebugEnabled()) {
    				logger.debug("!!!!! '" + vwmlContextBuilder.peek() + "'");
    			}    		      	    		      	
    		}
    		else {
    			if (logger.isDebugEnabled()) {
    				logger.debug("Context bunch '" + bunch + "' which was marked as IAS - stayed at context builder stack");
    			}
    		}
	}

	protected String unwindEffectiveContext() throws RecognitionException {
		ComplexContextDescriptor contextDescriptor = (ComplexContextDescriptor)contextWalker.peek();
		EntityWalker.EntityDescriptor entityDescr = null;
		String c = "";
		// top pushed entity's id should be changed on updated in case if its id is the same
		EntityWalker.Relation rel = (EntityWalker.Relation)entityWalker.peek();		
		if (contextDescriptor != null && logger.isDebugEnabled()) {
			logger.debug("Starting unwinding process of defferred effective context; top pushed entity is '" + rel.getObj() + "'");
		}
		while(contextDescriptor != null) {
			if (entityDescr == null) {
				entityDescr = (EntityWalker.EntityDescriptor)contextDescriptor.getUserData();
				if (logger.isDebugEnabled()) {
					logger.debug("creating effective context for entity descriptor '" + entityDescr + "'");
				}
			}
			// building context
			VWMLContextBuilder effectiveContextBuilder = (VWMLContextBuilder)contextDescriptor.getVwmlEffectiveContextBuilder();
			if (effectiveContextBuilder == null) {
				rethrowVWMLExceptionAsRecognitionException(new Exception("'null' effective context builder encountered during effective context building operation"));
			}
			c = effectiveContextBuilder.getEffectiveContext() + "." + c;
			contextWalker.pop();
			contextDescriptor = (ComplexContextDescriptor)contextWalker.peek();
		}
		String newEntityId = null;
		if (entityDescr != null) {
			newEntityId = VWMLContextBuilder.buildFullEntityName(c, (String)entityDescr.getId());
			if (codeGenerator != null) {
				if (rel != null && rel.getObj().equals(entityDescr.getId())) {
					if (logger.isDebugEnabled()) {
						logger.debug("Top pushed entity '" + entityDescr.getId() + "' is changed to '" + newEntityId + "' also");
					}
					rel.setObj(newEntityId);
				}
				codeGenerator.changeObjectIdToImmidiatly(entityDescr.getId(), newEntityId, (String[])entityDescr.getContexts());
				if (logger.isDebugEnabled()) {
					logger.debug("Entity '" + entityDescr.getId() + "' changed to '" + newEntityId + "'; contexts '" + entityDescr.getContexts() + "'");
				}
			}
			if (logger.isDebugEnabled()) {
				logger.debug("Finished unwinding process of defferred effective context");
			}
		}
		return newEntityId;
	}

	protected void addEffectiveContext(Object contextId) {
  		VWMLContextBuilder effectiveContextBuilder = null;
      		ComplexContextDescriptor contextDescriptor = (ComplexContextDescriptor)contextWalker.peek();
      		if (contextDescriptor != null) {
      			contextDescriptor.setAddressingByComplexContextEncountered(true);
      			contextDescriptor.setUserData(null);
      		}
    		effectiveContextBuilder = VWMLContextBuilder.instance();
   		// becomes part of 'complex context'
    		effectiveContextBuilder.addEffectiveContext((String)contextId);
    		// effective context on simple entity is equal '.' operator for complex entity
    		contextWalker.push(ComplexContextDescriptor.build(effectiveContextBuilder, false));
    		if (logger.isDebugEnabled()) {
    			logger.debug("effective context detected '" + contextId + "'");
    		}    			
	}

	protected void processComplexContext(EntityWalker.Relation rel) throws RecognitionException {
		if (codeGenerator != null) {
			// removes entity from declaration and linkage storage; entity is interpreted as 'complex context'
			String[] contexts = vwmlContextBuilder.buildContext().asStrings();
			codeGenerator.removeComplexEntityFromDeclarationAndLinkage(rel, contexts);
			if (logger.isDebugEnabled()) {
				logger.debug("entity '" + rel.getObj() + "' removed since it was recognized as complex context");
			}
			// throws relation to another entity... the relation is thrown until non-context entity is found
			if (EntityWalker.REL.ASSOCIATION == rel.getRelation()) {
    				Object fIAS = vwmlContextBuilder.peek();
    				if (logger.isDebugEnabled()) {
    					logger.debug("Object '" + fIAS + "' marked again as IAS");
    				}
    				entityWalker.markFutureEntityAsIAS(fIAS);
			}
   			// so entity is considered as effective context
   			if (lastProcessedComplexEntityId == null) {
				rethrowVWMLExceptionAsRecognitionException(new Exception("invalid context; single context indicator '.' detected"));
   			}
   			if (logger.isDebugEnabled()) {
   				logger.debug("part of complex context '" + lastProcessedComplexEntityId + "' detected");
   			}
   			// adds effective context
   			addEffectiveContext(lastProcessedComplexEntityId);
		}
	}

	protected EntityWalker.Relation simpleEntityAssembling(String id) throws RecognitionException {
		EntityWalker.Relation rel = null;
		// since entity's id may include '.' we should check correctness of name
    		// if name ends with '.' we can suppouse that this name can be considered as complex entity's effective context
    		ComplexContextDescriptor contextDescriptor = (ComplexContextDescriptor)contextWalker.peek();
    		if (!VWMLContextBuilder.isEffectiveContext(id)) {
    			EntityWalker.Relation relParent = (EntityWalker.Relation)entityWalker.peek();
    			boolean partOfComplexEntity = false;
    			if (relParent != null) {
    				complexEntityNameBuilderDef = (ComplexEntityNameBuilder)relParent.getData();
	    			if (complexEntityNameBuilderDef.isInProgress()) {
	    				complexEntityNameBuilderDef.addObjectId(id);
	    				partOfComplexEntity = true;
	    			}
    			}
    			// standalone simple entity - chance to unwind defferred effective context
    			if (contextDescriptor != null && (relParent == null || !relParent.isParticipatesInComplexContextBuildingProcess())) {
    				if (contextDescriptor.getUserData() == null) {
    					String[] contexts = vwmlContextBuilder.buildContext().asStrings();
    					contextDescriptor.setUserData(EntityDescriptor.build(id, contexts));
					String newEntityId = unwindEffectiveContext();
					if (newEntityId != null) {
						id = newEntityId;
					}
				}
    			}    			
   			rel = buildRelation(id);
    			if (logger.isDebugEnabled()) {
    				logger.debug("processed simple entity '" + rel + "'");
    			}
    		}
    		else {
    			// effective context on simple entity is equal '.' operator for complex entity
   			// adds effective context
   			addEffectiveContext(id);
 			
    		}
		return rel;	
	}

	protected void complexEntityStartAssembling() throws RecognitionException {
    		// id and name is the same
    		complexEntityNameBuilderDef = ComplexEntityNameBuilder.instance();
    		complexEntityNameBuilderDef.startProgress();
    		String ceId = complexEntityNameBuilderDecl.generateRandomName();
    		boolean participatesInComplexContextBuildingProcess = false;
    		try {
    			if (codeGenerator != null) {
    				VWMLContextBuilder.Contexts contexts = vwmlContextBuilder.buildContext();
    				ComplexContextDescriptor contextDescriptor = (ComplexContextDescriptor)contextWalker.peek();
    				if (contextDescriptor != null) {
    					if (contextDescriptor.getUserData() == null) {
    						String[] contextsAsStrings = vwmlContextBuilder.buildContext().asStrings();
    						contextDescriptor.setUserData(EntityDescriptor.build(ceId, contextsAsStrings));
    						participatesInComplexContextBuildingProcess = true;
    					}
					else {
						// next complex entity - chance to unwind deffreed effective context
						unwindEffectiveContext();
					}
    				}
    				for(String c = contexts.first(); c != null; c = contexts.next()) {
    					codeGenerator.declareComplexEntity(ceId, null, c);
    				}
    			}
    		}
    		catch(Exception e) {
    			rethrowVWMLExceptionAsRecognitionException(e);
    		}
        	// the complex enity (name/id is generated) is pushed to stack (here complex entity is part of expression)
    		EntityWalker.Relation rel = buildRelation(ceId);
    		rel.setData(complexEntityNameBuilderDef);
    		rel.setParticipatesInComplexContextBuildingProcess(participatesInComplexContextBuildingProcess);
    		entityWalker.push(rel);
   		if (logger.isDebugEnabled()) {
   			logger.debug("complex entity '" + rel.getObj() + "' is declared");
   		}
	}
	
	protected EntityWalker.Relation complexEntityStopAssembling() throws RecognitionException {
        	// remove it from stack
    		EntityWalker.Relation rel = (EntityWalker.Relation)entityWalker.pop();
    		// builds complex entity readable name instead of generated
    		complexEntityNameBuilderDef = (ComplexEntityNameBuilder)rel.getData();
    		complexEntityNameBuilderDef.stopProgress();
        	lastProcessedComplexEntityId = complexEntityNameBuilderDef.build();
        	complexEntityNameBuilderDef.clear();    		
    		if (logger.isDebugEnabled()) {
    			logger.debug("processed complex entity '" + rel + "'");
    		}
    		return rel;
	}
	
	protected Object buildIASAssociation(Object id) throws RecognitionException {
    		VWMLContextBuilder.ContextBunch bunch = (VWMLContextBuilder.ContextBunch)entityWalker.getEntityMarkedAsIAS();
    		Object objLinkedId = id;
    		entityWalker.resetFutureEntityAsIAS();
       		// creates 'IAS' association
    		try {
    			// asking for current/active context
    			if (codeGenerator != null) {
    				if (logger.isDebugEnabled()) {
   					logger.debug("Interpreting bunch '" + bunch + "'");
   				}
   				Object lastUniqId = null;
 				VWMLContextBuilder.Contexts contexts = vwmlContextBuilder.buildReducedContextList();
    				for(VWMLContextBuilder.ContextBunchElement cbe = bunch.first(); cbe != null; cbe = bunch.next()) {
    					String firstRelatedContext = contexts.first();
    					for(String c = contexts.first(); c != null; c = contexts.next()) {
    						codeGenerator.interpretObjects(cbe.getId(), objLinkedId, c, c, lastUniqId);
    						lastUniqId = codeGenerator.getLastLinksUniqId();
    						if (logger.isDebugEnabled()) {
   							logger.debug("Interpreting object '" + cbe.getId() + "' -> '" + objLinkedId + "'; on context '" + c + "'; uniq id '" + lastUniqId + "'");
   						}
    					}
    				}
   /*
   				VWMLContextBuilder.Contexts contexts = bunch.getContexts();
    				for(VWMLContextBuilder.ContextBunchElement cbe = bunch.first(); cbe != null; cbe = bunch.next()) {
    					String firstRelatedContext = contexts.first();
    					for(String c = contexts.first(); c != null; c = contexts.next()) {
    					        if (!c.endsWith((String)cbe.getId())) {
    					        	continue;
    					        }
    						codeGenerator.interpretObjects(cbe.getId(), objLinkedId, c, firstRelatedContext);
    						if (logger.isDebugEnabled()) {
   							logger.debug("Interpreting object '" + cbe.getId() + "' -> '" + objLinkedId + "'; on context '" + c + "'");
   						}
    					}
    				}
   */ 				
    			}
    		}
    		catch(Exception e) {
    			rethrowVWMLExceptionAsRecognitionException(e);
    		}	
    		return (codeGenerator != null) ? codeGenerator.getLastLink() : null;
	}
	
	protected Object buildLinkingAssociation(Object linkedObj) throws RecognitionException {
  		Object rel = entityWalker.peek();
  		if (rel == null) {
  			rel = EntityWalker.Relation.build(ComplexEntityNameBuilder.generateRootId(modName), EntityWalker.REL.NONE, null);
  		}
    		if (rel != null) {
    			try {
    				// asking for current/active context
    				VWMLContextBuilder.Contexts contexts = vwmlContextBuilder.buildContext();
    				Object linkingObjId = ((EntityWalker.Relation)rel).getObj();
    				if (codeGenerator != null) {
    					String c = contexts.first();
    					codeGenerator.linkObjects(linkingObjId, linkedObj, c, c, null);
    					/*
    					Object uniqId = null;
    					for(String c = contexts.first(); c != null; c = contexts.next()) {
    						codeGenerator.linkObjects(linkingObjId, linkedObj, c, c, uniqId);
    						if (uniqId == null) {
    							uniqId = codeGenerator.getLastLinksUniqId();
    						}
     						if (logger.isDebugEnabled()) {
    							logger.debug("Linked objects '" + linkingObjId + "' -> '" + linkedObj + "'; on context '" + c + "'");
    						}
    					}
    					*/
    				}
    			}
    			catch(Exception e) {
    				rethrowVWMLExceptionAsRecognitionException(e);
    			}
    		}
    		return (codeGenerator != null) ? codeGenerator.getLastLink() : null;
	}
	
	protected EntityWalker.Relation buildRelation(Object eId) throws RecognitionException {
		Object lastLink = null;
	     	EntityWalker.REL relType = EntityWalker.REL.NONE;
	    	// in case if entity was marked as IAS we have to build IAS association
	    	if (entityWalker.getEntityMarkedAsIAS() != null) {
	    		relType = EntityWalker.REL.ASSOCIATION;
	    		lastLink = buildIASAssociation(eId);
	    	}
	    	else {
	    		relType = EntityWalker.REL.LINK;
	    		// ... otherwise linkage
	  	
	  		lastLink = buildLinkingAssociation(eId);
	  	}
 		return EntityWalker.Relation.build(eId, relType, lastLink);
	}	
	
	protected void deferInclude(String file) {
		deferredIncludes.add(file);
		if (compilationSink != null) {
			compilationSink.handleInclude(file);
		}
	}
	
	protected void processDeferredIncludes() throws RecognitionException {
		for(String file : deferredIncludes) {
			processInclude(file);
		}
	}
	
	protected void processInclude(String file) throws RecognitionException {
		try {
    			if (logger.isInfoEnabled()) {
    				logger.info("including '" + file + "'");
    			}		
			vwmlModelBuilder.compile(file);
		}
		catch(Exception e) {
			rethrowVWMLExceptionAsRecognitionException(e);
		}
	} 
	
	protected void startConflictDefinitionOnRing(String conflictDefOnRing) throws RecognitionException {
		try {
			if (codeGenerator != null) {
				codeGenerator.startConflictDefinitionOnRing(conflictDefOnRing);
			}
			conflictDefinitionOnRingStarted = true;
		}
		catch(Exception e) {
			rethrowVWMLExceptionAsRecognitionException(e);
		}
	}

	protected void addConflictDefinitionOnRing(String conflictDefOnRing) throws RecognitionException {
		try {
			if (conflictDefinitionOnRingStarted && codeGenerator != null) {
				codeGenerator.addConflictDefinitionOnRing(conflictDefOnRing);
			}
		}
		catch(Exception e) {
			rethrowVWMLExceptionAsRecognitionException(e);
		}

	}

	protected void endConflictDefinitionOnRing() throws RecognitionException {
		try {
			if (codeGenerator != null) {
				codeGenerator.endConflictDefinitionOnRing();
			}
			conflictDefinitionOnRingStarted = false;
		}
		catch(Exception e) {
			rethrowVWMLExceptionAsRecognitionException(e);
		}
	}
	
	protected void externalContext(String externalContext) {
		externalContexts.add(externalContext);
	}

	protected void externalEntity(String externalEntity) {
		externalEntities.add(externalEntity);
	}
	
	protected void processExternals() throws RecognitionException {
		for(String context : externalContexts) {
			if (logger.isInfoEnabled()) {
				logger.info("processing external context '" + context + "'");
			}
			if (codeGenerator != null) {
				codeGenerator.declareContext(context);
			}
		}
	}
	
	protected void rethrowVWMLExceptionAsRecognitionException(Exception e) throws RecognitionException {
    		com.vw.lang.sink.OperationInfo opInfo = new com.vw.lang.sink.OperationInfo();
    		org.antlr.runtime.Token token = getTokenStream().get(getTokenStream().index());
    		opInfo.setLine(token.getLine());
    		opInfo.setPosition(token.getCharPositionInLine());
    		opInfo.setFileName(getSourceName());
    		opInfo.setNextToken(token.getText());
		throw new VWMLCodeGeneratorRecognitionException(opInfo);
	}
	
	// DIRECTIVES
	
	protected boolean scanOnly() {
		if (compilationSink != null && compilationSink.getMode() == CompilationSink.Mode.SCAN_ONLY) {
			return true;
		}
		return false;
	}
	
	protected boolean skipOff() throws RecognitionException {
		try {
			boolean b = !preprocessor.getResultOfProcessingDirectiveIf();
			if (b && compilationSink != null) {
    				com.vw.lang.sink.OperationInfo opInfo = new com.vw.lang.sink.OperationInfo();
    				org.antlr.runtime.Token token = getTokenStream().get(getTokenStream().index());
    				opInfo.setLine(token.getLine());
    				opInfo.setPosition(token.getCharPositionInLine());
    				opInfo.setFileName(getSourceName());
    				opInfo.setNextToken(token.getText());
				compilationSink.skippedCode(opInfo);
			}
			return b;
		}
		catch(Exception e) {
			rethrowVWMLExceptionAsRecognitionException(e);
		}
		return false;
	}
	
}


filedef
    : props? (include (include)*)? external? module? EOF {
                             	if (!scanOnly() && moduleInProgress && modProps != null) {
                             		endModule();
                  	     	} 
                  	     }
    ;	 

external
    : 'external' '{' externalBody '}'
    ;

externalBody
    : externalContexts? externalEntities? 
    ;

externalContexts
    :	'contexts' '{' (externalContext)* '}'
    ;

externalEntities
    :	'entities' '{' (externalEntity)* '}'
    ;
    
externalContext
    :  string 	{
        		if (logger.isInfoEnabled()) {
    				logger.info("external context '" + $string.text + "'");
    			}
    			externalContext(GeneralUtils.trimQuotes($string.text));
    		}
    ;

externalEntity
    :  string 	{
        		if (logger.isInfoEnabled()) {
    				logger.info("external entity '" + $string.text + "'");
    			}
    			externalEntity(GeneralUtils.trimQuotes($string.text));
    		}
    ;

include
    : include_vwml {
    			if (logger.isInfoEnabled()) {
    				logger.info("deferring include '" + $include_vwml.id + "'");
    			}
    			deferInclude(GeneralUtils.trimQuotes($include_vwml.id));    			
                   }
    ;
    
include_vwml returns [String id]
    :  'include' STRING_LITERAL {id = $STRING_LITERAL.text;}
    ;

props
    : 'options' '{' optionsList '}'
    ;
    
optionsList
    : lang conflictring?
    ;

lang
    : ('language' '=' JAVA) => langJava
    | otherLanguages
    ;	

otherLanguages
    :	
    ;

langJava
    @init {
       codeGenerator = vwmlModelBuilder.getCodeGenerator(VWMLModelBuilder.SINK_TYPE.JAVA);
       if (vwmlModelBuilder.getProjectProps() != null && vwmlModelBuilder.getProjectProps().getCodeGenerator() == null) {
       		vwmlModelBuilder.getProjectProps().setCodeGenerator(codeGenerator);
       }
       if (logger.isDebugEnabled()) {
       		logger.debug("Code generator '" + codeGenerator + "'");
       }
    }
    : 'language' '=' JAVA '{' javaProps '}'
    ;

javaProps
    @init {
    	setupProps();
    }
    : obligatoryProps optionalProps

    ;

obligatoryProps
    : (obligatoryProp)*
    ;

obligatoryProp
    : propPackage
    | generatedFileLocation
    | pjavaPropsBlock
    | pswitch
    ;
    

propPackage
    : 'package' '=' packageName {
	    			  if (modProps != null && !skipOff()) {
	    			  	String packageName = GeneralUtils.trimQuotes($packageName.text);
	    				((JavaCodeGenerator.JavaModuleStartProps)modProps).setModulePackage(packageName);
	    			  	if (compilationSink != null) {
	    			  		compilationSink.publishModulePackage(packageName);
	    			  	}
	    			  }
    			        }
    ;

packageName
    : STRING_LITERAL
    ;

generatedFileLocation
    : 'path' '=' path {
    			if (modProps != null && !skipOff()) {
    				String fileLocation = GeneralUtils.trimQuotes($path.text);
    				((JavaCodeGenerator.JavaModuleStartProps)modProps).setSrcPath(fileLocation);
 	    			if (compilationSink != null) {
	    				compilationSink.publishFileLocation(fileLocation);
	    			}
    			}
    		      }
    ;	

optionalProps
    : author? projname? description? entity_history_size? visualizer? beyond_the_fringe? conflictring?
    ;

author
    : 'author' '=' string {
	    			if (modProps != null) {
	    				String author = GeneralUtils.trimQuotes($string.text);
	    				((JavaCodeGenerator.JavaModuleStartProps)modProps).setAuthor(author);
 	    				if (compilationSink != null) {
	    					compilationSink.publishAuthor(author);
	    				}
	    			}
    			  }
    ;

projname
    : 'project_name' '=' string {
	    			if (modProps != null) {
	    				String projectName = GeneralUtils.trimQuotes($string.text);
	    				((JavaCodeGenerator.JavaModuleStartProps)modProps).setProjectName(projectName);
 	    				if (compilationSink != null) {
	    					compilationSink.publishProjectName(projectName);
	    				}
	    			}
    			  }
    ;
    
description
    : 'description' '=' string { 
    				if (modProps != null) {
    					String projectDescription = GeneralUtils.trimQuotes($string.text);
    					((JavaCodeGenerator.JavaModuleStartProps)modProps).setDescription(projectDescription);
 	    				if (compilationSink != null) {
	    					compilationSink.publishProjectDescription(projectDescription);
	    				}	    			
    				}
    			       }
    ;

entity_history_size
    : 'entity_history_size' '=' string { 
    				if (modProps != null) {
    					((JavaCodeGenerator.JavaModuleStartProps)modProps).setEntityHistorySize(GeneralUtils.trimQuotes($string.text));
    				}
    			       }
    ;

// VISUALISER (DEBUGGING)
visualizer
    : 'visualizer' '{' visualizer_body '}'	
    ;

visualizer_body
    : visualizer_class visualizer_datapath
    |
    ;

visualizer_class
    : 'class' '=' string { 
    				if (modProps != null) {
    					((JavaCodeGenerator.JavaModuleStartProps)modProps).setVisitor((AbstractVWMLLinkVisitor)GeneralUtils.instantiateClassThroughStaticMethod(GeneralUtils.trimQuotes($string.text), "instance"));
    				}
    			 }
    ;
    
visualizer_datapath
    : 'data' '=' string { 
    				if (modProps != null) {
    					((JavaCodeGenerator.JavaModuleStartProps)modProps).setVisitorDataPath(GeneralUtils.trimQuotes($string.text));
    				}
    			}
    ;    

path
    : STRING_LITERAL
    ;

// BEYOND THE FRINGE
beyond_the_fringe
    : 'beyond' '{' beyond_the_fringe_body '}'
    ;
    
beyond_the_fringe_body
    :  finges
    ;

finges
    :  (fringe)+
    ;

fringe
    :  'fringe' ID 'ias'
    		{
    			setActiveFringe($ID.getText());
    		}
                '(' creatures ')'
    ;

creatures
    : (creatureblock)+
    ;

creatureblock
    : creature
    | pfringedefblock
    | pswitch
    ;

creature
    @after 	{
			if (codeGenerator != null && !skipOff()) {
				try {
					codeGenerator.declareCreature(getLastDeclaredCreature(), getLastDeclaredCreatureProps(), getActiveFringe());
				}
				catch(Exception e) {
	    				logger.error("Caught exception '" + e + "'");
	    				rethrowVWMLExceptionAsRecognitionException(e);
				}
			}    
    		}
    : ID 	{
    			if (!skipOff()) {
    				addLastDeclaredCreature($ID.getText());
    			}
    	 	} 'ias' string 
    	 	{
    	 		if (!skipOff()) {
    	 			addLastDeclaredCreatureProps(GeneralUtils.trimQuotes($string.text));
    	 		}
    	 	}
    ;

conflictring
    : 'conflictring' '{' conflictdef* '}'
    ;	

conflictdef
    : name_of_conflict_on_ring { startConflictDefinitionOnRing(GeneralUtils.trimQuotes($name_of_conflict_on_ring.id)); } 'conflicts' '(' name_of_related_conflict_on_ring+ ')' { endConflictDefinitionOnRing(); }
    ;
    
name_of_conflict_on_ring returns [String id]
    : string { id = $string.text; }	
    ;	

name_of_related_conflict_on_ring
    : string { addConflictDefinitionOnRing(GeneralUtils.trimQuotes($string.text)); }
    ;

module
    : 'module' ID {
    			if (logger.isInfoEnabled()) {
    				logger.info("Scan mode '" + scanOnly() + "'");
    			}
    			if (!scanOnly()) {
    				startModule($ID.getText());
    			}
    			else {
 	    			if (compilationSink != null) {
	    				compilationSink.publishModuleName($ID.getText());
	    			}
    			}
    			// starts module's definition
                  } body
    ;
    
body
   : '{' (expression (expression)*)? '}'
   ;

 
expression
    : (bunch_of_entity_decls IAS) => entity_def
    | check_term_def
    | pvwmlblock
    | pswitch
    ;

entity_def
    : bunch_of_entity_decls IAS
    		{
    			if (!skipOff() && !scanOnly()) {
	    			// adds entity id to context stack
	    			declareAbsoluteContextByIASRelation();
    			}
    		} (term)* SEMICOLON
		{
    		      	if (!skipOff() && !scanOnly()) {
    		      		// removes top entity from stack
    		      		handleProcessedAbsoluteContextbyIASRelation();
    		      	}
		}
    		      
    ;

check_term_def
    : source_lifetrerm? LIFETERM '=' lifeterm_def
    | term_def
    ;

source_lifetrerm
    : 'source' {
    			if (!skipOff() && !scanOnly()) {
    				if (logger.isDebugEnabled()) {
    					logger.debug("source lifeterm indicator detected");
    				}
    				sourceLifeTermDetectedFlag = true;
    			}
    	       }
    ;

lifeterm_def
    :  term_def {
    			if (!skipOff() && !scanOnly()) {
    				if (logger.isInfoEnabled()) {
    					logger.info("Lifeterm '" + lastProcessedEntity + "' found");
    				}
    				if (codeGenerator != null) {
  	       				try {
						codeGenerator.markEntityAsLifeTerm(lastProcessedEntity, sourceLifeTermDetectedFlag);
						if (logger.isDebugEnabled()) {
							logger.debug("entity '" + lastProcessedEntity + "' marked as lifeterm; is source '" + sourceLifeTermDetectedFlag + "'");
						}
						sourceLifeTermDetectedFlag = false;
					}
					catch(Exception e) {
						rethrowVWMLExceptionAsRecognitionException(e);
					}
	       			} 
	       		}
    		}
    ;

term_def
    : entity 	{
    			if (!skipOff() && !scanOnly()) {
    				lastProcessedEntity = $entity.rel;
    				lastProcessedEntityAsTerm = false;
    				if (lastProcessedEntity != null && logger.isDebugEnabled()) {
    					logger.debug(">> '" + lastProcessedEntity.getObj() + "' <<");
    				}
    			}
    	     	} (oplist)* 
  	     	{  
  	       		if (lastProcessedEntityAsTerm && codeGenerator != null && !skipOff() && !scanOnly()) {
  	       			try {
  	       				VWMLContextBuilder.Contexts contexts = vwmlContextBuilder.buildContext();
					if (logger.isDebugEnabled()) {
						logger.debug("entity '" + lastProcessedEntity + "' checking term prop on contexts '" + contexts + "'");
					}
					codeGenerator.markEntityAsTerm(lastProcessedEntity, contexts.asStrings());
					if (logger.isDebugEnabled()) {
						logger.debug("entity '" + lastProcessedEntity + "' marked as term on contexts '" + contexts + "'");
					}
				}
				catch(Exception e) {
					rethrowVWMLExceptionAsRecognitionException(e);
				}
	       		} 
	     	}
    ;

entity_decl
    : simple_entity_decl
    		{
    			if (!skipOff() && !complexEntityNameBuilderDecl.isInProgress() && !scanOnly()) {
    				lastProcessedContextBunch.add(ContextBunchElement.build($simple_entity_decl.id));
    				if (logger.isDebugEnabled()) {
    					logger.debug("+++++++++++++++++++++++ " + $simple_entity_decl.id);
    				}
    			}
    		}
    | complex_entity_decl
    		{
    			if (!skipOff() && !scanOnly() && complexEntityNameBuilderDecl.isRootEntityFinishedProgress()) {
  				Object id = complexEntityDeclarationPhase3();
    				lastProcessedContextBunch.add(ContextBunchElement.build(id));
    			}
    		}
    ;

bunch_of_entity_decls
    @after 	{
    			if (!skipOff() && !scanOnly()) {
    				VWMLContextBuilder.Contexts contexts = vwmlContextBuilder.buildContext();
        			vwmlContextBuilder.push(lastProcessedContextBunch);
        			if (logger.isDebugEnabled()) {
        				logger.debug("Pushed '" + lastProcessedContextBunch + "'; parent contexts '" + contexts + "'");
        			}
        		}
    		}
    : 
    		{
    			if (!skipOff() && !scanOnly()) {
    				lastProcessedContextBunch = VWMLContextBuilder.ContextBunch.instance();
    				if (logger.isDebugEnabled()) {
    					logger.debug("Created bunch");
    				}
    			}
    		} entity_decl (COMMA entity_decl)*
    ;	

    
simple_entity_decl returns [String id]
    : ID 	{
    			if (!skipOff() && !scanOnly()) {
    				id = simpleEntityDeclaration($ID.getText());
    			}
    		}
    ;
    
complex_entity_decl
    : '(' 	{
    			if (!skipOff() && !scanOnly()) {
    				complexEntityDeclarationPhase1();
    			}
    		} (entity_decl)*
    		{
    			if (!skipOff() && !scanOnly()) {
    				complexEntityDeclarationPhase2();
    			}
    		}
    	')'
    ;

term
    : expression
    ;  

entity returns [EntityWalker.Relation rel]
    : simple_entity
    		{ 
    			rel = $simple_entity.rel;
    		}

    | complex_entity
    		{ 
    			rel = $complex_entity.rel;
    		}
    | '.'       {
                	if (!skipOff() && !scanOnly()) {
                		processComplexContext(lastProcessedEntity);
                	}
             	}
    ;


simple_entity returns [EntityWalker.Relation rel]
    : ID 	{
    			if (!skipOff() && !scanOnly()) {
    				rel = simpleEntityAssembling($ID.text);
    			} else {
    				rel = null;
    			}
         	}
    ;

complex_entity returns [EntityWalker.Relation rel]
    @init 	{
    			if (!skipOff() && !scanOnly()) {
    				complexEntityStartAssembling();
    			}
    		}
    @after 	{
    			if (!skipOff() && !scanOnly()) {
        			rel = complexEntityStopAssembling();
        		}
    		}
    : '(' (term)* ')'
    ;


ID
    : LETTER (LETTER | '.')* // ('a'..'z'|'A'..'Z'|'0'..'9'|'.'|'_'|'*'|'-')+
    ;


STRING_LITERAL
    :  '"' ( ~('"') )* '"'
    ;    

oplist
    // associates operation with entity
    : opclist
    		{
    			if (!skipOff() && !scanOnly() && lastProcessedEntity != null && codeGenerator != null) { 
    				lastProcessedEntityAsTerm = true;
    				VWMLContextBuilder.Contexts contexts = vwmlContextBuilder.buildContext();
    				String c = contexts.first();
    				com.vw.lang.sink.OperationInfo opInfo = new com.vw.lang.sink.OperationInfo();
    				org.antlr.runtime.Token nextToken = getTokenStream().LT(1);
    				opInfo.setOpCode($opclist.text);
    				opInfo.setLine(nextToken.getLine());
    				opInfo.setPosition(nextToken.getCharPositionInLine());
    				opInfo.setFileName(getSourceName());
    				opInfo.setNextToken(nextToken.getText());
    				codeGenerator.associateOperation(lastProcessedEntity, $opclist.text, c, opInfo);
    			} 
    		}
    ;

opclist
    : OPJOIN
    | OPINTERSECT
    | OPSUBSTRUCT
    | OPFIRST
    | OPLAST
    | OPBEGIN
    | OPREST
    | OPCARTESIAN
    | OPIN
    | OPINCL
    | OPEQ
    | OPIDENT
    | OPSQU
    | OPINTERPRET
    | OPCREATEEXPR
    | OPEXECUTE
    | OPRANDOM
    | OPACTIVATECTX
    | OPACTIVATEONFRINGE
    | OPRELAX
    | OPSTARTCONFLICTGROUP
    | OPENDCONFLICTGROUP
    | OPBREAKPOINT
    | OPAPPLYTOCONTEXT
    | OPCLONE
    | OPBORN
    | OPPROJECTION
    | OPFOREACH
    | OPDYNCONTEXT
    | OPSIZE
    | OPINTERRUPT
    | OPCALLP
    | OPGET
    | OPFIND
    | OPGATE
    | OPRECALL
    | OPREPEAT
    | OPACTIVATE
    | OPCOPY    
    | OPSTARTINTERCEPTION
    | OPFINISHINTERCEPTION
    | OPSTARTINTERCEPTION_S
    | OPFINISHINTERCEPTION_S
    | OPRELEASE
    | OPEXISTSI
    | OPEXISTSI_S
    | OPLTT
    ;

termLanguages
    : JAVA
    | C
    | CPP
    | OBJECTIVEC
    ;

string
    : STRING_LITERAL
    ;


// PREPROCESSOR
pfringedefblock
    : pstart pexpressions 
    		{
    			preprocessor.processDirectiveIf();
    		}
    		creatures
      pend
    ;

pjavaPropsBlock
    : pstart pexpressions 
    		{
    			preprocessor.processDirectiveIf();
    		}
    		javaProps
      pend
    ;


pvwmlblock
    : pstart pexpressions 
    		{ 
    			preprocessor.processDirectiveIf();
    		} 
    		(expression)*
      pend
    ;

pexpressions
    : '('	{
    			preprocessor.getTopDirectiveIf().addExpressionItem();
    		}	
    	(pexpression)* 
      ')'	{
    			preprocessor.getTopDirectiveIf().removeTop();
    		}
    ;

pstart
    : P_IF 	{
   			preprocessor.startDirectiveIf();
    		}
    ;

pend
    : P_ENDIF	{
    			preprocessor.endDirectiveIf();
    		}
    		
    ;

pswitch
    : P_ELSE  	{
    			preprocessor.reverseResultOfProcessingDirectiveIf();
    		}
    ;

pexpression
    : pitem (poperation (pexpressions)*)*
    ;
    
pitem
    : ID	{
    			preprocessor.getTopDirectiveIf().addRegularItem($ID.text);
    		}
    ;

poperation
    : P_OP_AND	{
    			preprocessor.getTopDirectiveIf().addOperation($P_OP_AND.text);
    		}
    | P_OP_OR	{
    			preprocessor.getTopDirectiveIf().addOperation($P_OP_OR.text);
    		}
    | P_OP_B	{
    			preprocessor.getTopDirectiveIf().addOperation($P_OP_B.text);
    		}
    | P_OP_L	{
    			preprocessor.getTopDirectiveIf().addOperation($P_OP_L.text);
    		}
    | P_OP_E	{
    			preprocessor.getTopDirectiveIf().addOperation($P_OP_E.text);
    		}
    ;

//////////////////////
        
COMMA
    : ','
    ;
  
DQUOTE
    : '"'
    ;
    
SEMICOLON
    : ';'
    ;	

WS
    : (' '|'\t'|'\n'|'\r')* {$channel=HIDDEN;}
    ;


NATIVE_CODE
    : '<*' .* '*>' {$channel=NATIVE_CODE_CHANNEL;}
    ;

COMMENT
    : '/*' ( options {greedy=false;} : . )* '*/' {$channel=HIDDEN;}
    ;
    
LINE_COMMENT
    : '//' ~('\n'|'\r')* '\r'? '\n' {$channel=HIDDEN;}
    ;

fragment
LETTER
	: 'A'..'Z'
	| 'a'..'z'
	| '0'..'9' 
	| '_'
	| '-'
	| '!'
	| '?'
	| '$'
	;

/*

(SEMICOLON {
    				logger.info("!!!!!!!!!!!");
    				Object o = vwmlContextBuilder.peek();
    				vwmlContextBuilder.pop();
    				if (logger.isDebugEnabled()) {
    					logger.debug("Context '" + o + "' removed from context builder stack; next '" + vwmlContextBuilder.peek() + "'");
    				}   				
    			    })?

*/