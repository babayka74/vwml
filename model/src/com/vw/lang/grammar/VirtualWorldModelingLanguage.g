
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
    OPPROJECTION_1='Projection_1';
    OPPROJECTION_2='Projection_2';
    OPPROJECTION_3='Projection_3';
    OPPROJECTION_4='Projection_4';
    OPPROJECTION_5='Projection_5';
    OPPROJECTION_6='Projection_6';
    OPPROJECTION_7='Projection_7';
    OPPROJECTION_8='Projection_8';
    OPPROJECTION_9='Projection_9';
    
    // languages
    JAVA='__java__';
    C='__c__';
    CPP='__cpp__';
    OBJECTIVEC='__objective_c__';
}

@header {
package com.vw.lang.grammar;

// Exceptions
import java.lang.Throwable;

// builder
import com.vw.lang.processor.model.builder.VWMLModelBuilder;
import com.vw.lang.processor.model.builder.VWMLModuleInfo;
import com.vw.lang.processor.context.builder.VWMLContextBuilder;

// general code generator
import com.vw.lang.sink.ICodeGenerator;
import com.vw.lang.sink.ICodeGenerator.StartModuleProps;
import com.vw.lang.sink.utils.ComplexEntityNameBuilder;
import com.vw.lang.sink.utils.EntityWalker;
import com.vw.lang.sink.utils.EntityWalker.ComplexContextDescriptor;
import com.vw.lang.sink.utils.GeneralUtils;

// specific code generator
import com.vw.lang.sink.java.link.AbstractVWMLLinkVisitor;
import com.vw.lang.sink.java.code.JavaCodeGenerator;
import com.vw.lang.sink.java.code.JavaCodeGenerator.JavaModuleStartProps;

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
}


@members {

	public static class VWMLCodeGeneratorRecognitionException extends RecognitionException {
		public VWMLCodeGeneratorRecognitionException() {
			super();
		}
		
		public VWMLCodeGeneratorRecognitionException(String message) {
			initCause(new Throwable(message));
		}
	}

	private VWMLModelBuilder vwmlModelBuilder = VWMLModelBuilder.instance();
	private VWMLContextBuilder vwmlContextBuilder = VWMLContextBuilder.instance();
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
 	
 	private String lastProcessedIAS = null;
 	
 	private Logger logger = Logger.getLogger(this.getClass());
	
	public StartModuleProps getModuleProps() {
		return modProps;
	}
	
	public void setModuleProps(StartModuleProps modProps) {
		this.modProps = modProps;
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
    				String context = vwmlContextBuilder.buildContext();
    		        	if (logger.isDebugEnabled()) {
    		        		logger.debug("simple entity '" + id + "' is declared; context '" + context + "'");
    		        	}
    				if (codeGenerator != null) {
    					codeGenerator.declareSimpleEntity(id, context);
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
	}
	
	protected String complexEntityDeclarationPhase3() throws RecognitionException {
              	String id = complexEntityNameBuilderDecl.build();
              	complexEntityNameBuilderDecl.clear();
		try {
			String context = vwmlContextBuilder.buildContext();
			if (codeGenerator != null) {
				codeGenerator.declareComplexEntity(id, null, context);
			}    	
			if (logger.isDebugEnabled()) {
				logger.debug("complex entity '" + id + "' is declared; context '" + context + "'");
				logger.debug("complex entity declaration process - finished");
			}    	
		}
		catch(Exception e) {
			rethrowVWMLExceptionAsRecognitionException(e);
		}
		return id;            					
	}

	protected void declareAbsoluteContextByIASRelation(String context) throws RecognitionException {
		// point to check deffered actions on effective context
		unwindEffectiveContext();
    		// adds entity id to context stack
    		vwmlContextBuilder.push(context);
    		entityWalker.markFutureEntityAsIAS(context);
    		if (logger.isDebugEnabled()) {
    			logger.debug("Entity '" + context + "' was marked as IAS - pushed to stack");
    		}
    		if (codeGenerator != null) {
    			codeGenerator.declareContext(vwmlContextBuilder.buildContext());
    		}
	}

	protected void handleProcessedAbsoluteContextbyIASRelation(String context) {
    		if (lastProcessedEntity != null) {
    			if (logger.isDebugEnabled()) {
    				logger.debug("Entity '" + context + "' which was marked as IAS - removed from context builder stack");
    			}
    		      	vwmlContextBuilder.pop();
    			if (logger.isDebugEnabled()) {
    				logger.debug("!!!!! '" + vwmlContextBuilder.peek() + "'");
    			}    		      	    		      	
    		}
    		else {
    			if (logger.isDebugEnabled()) {
    				logger.debug("Entity '" + context + "' which was marked as IAS - stayed at context builder stack");
    			}
    		}
	}

	protected String unwindEffectiveContext() throws RecognitionException {
		ComplexContextDescriptor contextDescriptor = (ComplexContextDescriptor)contextWalker.peek();
		Object entityId = null;
		String c = "";
		if (contextDescriptor != null && logger.isDebugEnabled()) {
			logger.debug("Starting unwinding process of defferred effective context");
		}
		while(contextDescriptor != null) {
			if (entityId == null) {
				entityId = contextDescriptor.getUserData();
				if (logger.isDebugEnabled()) {
					logger.debug("creating effective context for entity '" + entityId + "'");
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
		if (entityId != null) {
			newEntityId = VWMLContextBuilder.buildFullEntityName(c, (String)entityId);
			if (codeGenerator != null) {
				codeGenerator.changeObjectIdToImmidiatly(entityId, newEntityId);
				if (logger.isDebugEnabled()) {
					logger.debug("Entity '" + entityId + "' changed to '" + newEntityId + "'");
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
			codeGenerator.removeComplexEntityFromDeclarationAndLinkage(rel);
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
				rethrowVWMLExceptionAsRecognitionException(new Exception("invalid complex context; single context indicator '.' detected"));
   			}
   			if (logger.isDebugEnabled()) {
   				logger.debug("complex context '" + lastProcessedComplexEntityId + "' detected");
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
    					contextDescriptor.setUserData(id);
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
    				String context = vwmlContextBuilder.buildContext();
    				ComplexContextDescriptor contextDescriptor = (ComplexContextDescriptor)contextWalker.peek();
    				if (contextDescriptor != null) {
    					if (contextDescriptor.getUserData() == null) {
    						contextDescriptor.setUserData(ceId);
    						participatesInComplexContextBuildingProcess = true;
    					}
					else {
						// next complex entity - chance to unwind deffreed effective context
						unwindEffectiveContext();
					}
    				}
    				codeGenerator.declareComplexEntity(ceId, null, context);
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
    		Object objLinkingId = entityWalker.getEntityMarkedAsIAS();
    		Object objLinkedId = id;
    		entityWalker.resetFutureEntityAsIAS();
       		// creates 'IAS' association
    		try {
    			// asking for current/active context
    			String context = vwmlContextBuilder.buildContext();
    			if (codeGenerator != null) {
    				codeGenerator.interpretObjects(objLinkingId, objLinkedId, context);
    			}
    			if (logger.isDebugEnabled()) {
   				logger.debug("Interpreting objects '" + objLinkingId + "' -> '" + objLinkedId + "'; on context '" + context + "'");
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
    				String context = vwmlContextBuilder.buildContext();
    				Object linkingObjId = ((EntityWalker.Relation)rel).getObj();
    				if (codeGenerator != null) {
    					codeGenerator.linkObjects(linkingObjId, linkedObj, context);
    				}
    				if (logger.isDebugEnabled()) {
    					logger.debug("Linked objects '" + linkingObjId + "' -> '" + linkedObj + "'; on context '" + context + "'");
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
	
	protected void processInclude(String file) throws RecognitionException {
		try {
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
	
	protected void rethrowVWMLExceptionAsRecognitionException(Exception e) throws RecognitionException {
		throw new VWMLCodeGeneratorRecognitionException(e.getMessage());
	}
		
}


filedef
    : props? (include (include)*)? module? EOF {
                             	if (moduleInProgress && modProps != null) {
                             		try {
                             			// sets special interpretation properties
                             			// these properties are defined by user and passed by VWML tool to VWML builder 
                             			modProps.setInterpretationProps(vwmlModelBuilder.getInterpretationProps());
                             			// actually generates source code
                             			codeGenerator.generate(modProps);
                             			// finalizes source generation phase for this module
                             			codeGenerator.finishModule(modProps);
                             			// module parsed and finished
                             			moduleInProgress = false;
                             		}
                             		catch(Exception e) {
		    				logger.error("Caught exception '" + e + "'");
		    				rethrowVWMLExceptionAsRecognitionException(e);
                             		}
                  	     	} 
                  	     }
    ;	 

include
    : include_vwml {
    			if (logger.isInfoEnabled()) {
    				logger.info("including '" + $include_vwml.id + "'");
    			}
    			processInclude(GeneralUtils.trimQuotes($include_vwml.id)); 
                   }
    ;
    
include_vwml returns [String id]
    :  'include' STRING_LITERAL {id = $STRING_LITERAL.text;}
    ;

props
    : 'options' '{' optionsList '}'
    ;
    
optionsList
    : lang
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
       if (logger.isDebugEnabled()) {
       		logger.debug("Code generator '" + codeGenerator + "'");
       }
    }
    : 'language' '=' JAVA '{' javaProps '}'
    ;

javaProps
    @init {
	// instantiating module's properties which will be filled later
	modProps = (codeGenerator != null) ? codeGenerator.buildProps() : null;
	// tell to builder reference to module's properties
	if (vwmlModelBuilder.getProjectProps() == null) {
		vwmlModelBuilder.setProjectProps(modProps);
	}
    }
    : propPackage generatedFileLocation? optionalProps
    ;
    	
propPackage
    : 'package' '=' packageName {
	    			  if (modProps != null) {
	    				((JavaCodeGenerator.JavaModuleStartProps)modProps).setModulePackage(GeneralUtils.trimQuotes($packageName.text));
	    			  }
    			        }
    ;

packageName
    : STRING_LITERAL
    ;

generatedFileLocation
    : 'path' '=' path {
    			if (modProps != null) {
    				((JavaCodeGenerator.JavaModuleStartProps)modProps).setSrcPath(GeneralUtils.trimQuotes($path.text));
    			}
    		      }
    ;	

optionalProps
    : author? projname? description? entity_history_size? visualizer? beyond_the_fringe? conflictring?
    ;

author
    : 'author' '=' string {
	    			if (modProps != null) {
	    				((JavaCodeGenerator.JavaModuleStartProps)modProps).setAuthor(GeneralUtils.trimQuotes($string.text));
	    			}
    			  }
    ;

projname
    : 'project_name' '=' string {
	    			if (modProps != null) {
	    				((JavaCodeGenerator.JavaModuleStartProps)modProps).setProjectName(GeneralUtils.trimQuotes($string.text));
	    			}
    			  }
    ;
    
description
    : 'description' '=' string { 
    				if (modProps != null) {
    					((JavaCodeGenerator.JavaModuleStartProps)modProps).setDescription(GeneralUtils.trimQuotes($string.text));
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
    :  'fringe' ID 'ias' {
    			setActiveFringe($ID.getText());
    		   }
                   '(' creatures ')'
    ;

creatures
    : (creature)+
    ;

creature
    @after {
	if (codeGenerator != null) {
		try {
			codeGenerator.declareCreature(getLastDeclaredCreature(), getLastDeclaredCreatureProps(), getActiveFringe());
		}
		catch(Exception e) {
	    		logger.error("Caught exception '" + e + "'");
	    		rethrowVWMLExceptionAsRecognitionException(e);
		}
	}    
    }
    : ID {
    		addLastDeclaredCreature($ID.getText());
    	 } 'ias' string  {
    	 			addLastDeclaredCreatureProps(GeneralUtils.trimQuotes($string.text));
    	 		}
    ;

conflictring
    : 'conflictring' '{' conflictdef? '}'
    ;	

conflictdef
    : name_of_conflict_on_ring { startConflictDefinitionOnRing($name_of_conflict_on_ring.id); } 'conflicts' '(' name_of_related_conflict_on_ring? ')' { endConflictDefinitionOnRing(); }
    ;
    
name_of_conflict_on_ring returns [String id]
    : ID { id = $ID.getText(); }	
    ;	

name_of_related_conflict_on_ring
    : ID { addConflictDefinitionOnRing($ID.getText()); }
    ;

module
    : 'module' ID { 
    			modName = $ID.getText();
    			// normalizes module's properties; if some properties were not set they are filled by project's properties
    			// ... so it is way to override them 
    			modProps = vwmlModelBuilder.normalizeProps(modProps);
    			// associates module's name with module info structure (will be used on last dource generation phase, especially during unit-test generation)
    			vwmlModelBuilder.addModuleInfo(modName, VWMLModuleInfo.build(modProps, null)); 
    			if (modProps != null) {
    				((JavaCodeGenerator.JavaModuleStartProps)modProps).setModuleName(modName);
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
    			// starts module's definition
                  } body
    ;
    
body
   : '{' (expression (expression)*)? '}'
   ;

 
expression
    : (entity_decl IAS) => entity_def
    | check_term_def
    ;

entity_def
    : entity_decl IAS {
    			// adds entity id to context stack
    			declareAbsoluteContextByIASRelation($entity_decl.id);
    		      } (term)* SEMICOLON
    		      {
    		      	// removes top entity from stack
    		      	handleProcessedAbsoluteContextbyIASRelation($entity_decl.id);
    		      }
    		      
    ;

check_term_def
    : source_lifetrerm? LIFETERM '=' lifeterm_def
    | term_def
    ;

source_lifetrerm
    : 'source' {
    			if (logger.isDebugEnabled()) {
    				logger.debug("source lifeterm indicator detected");
    			}
    			sourceLifeTermDetectedFlag = true;
    	       }
    ;

lifeterm_def
    :  term_def {
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
    ;

term_def
    : entity {
    		lastProcessedEntity = $entity.rel;
    		lastProcessedEntityAsTerm = false;
    		if (lastProcessedEntity != null && logger.isDebugEnabled()) {
    			logger.debug(">> '" + lastProcessedEntity.getObj() + "' <<");
    		}
    	     } (oplist)* 
  	     {
  	       if (lastProcessedEntityAsTerm && codeGenerator != null) {
  	       		try {
				codeGenerator.markEntityAsTerm(lastProcessedEntity);
				if (logger.isDebugEnabled()) {
					logger.debug("entity '" + lastProcessedEntity + "' marked as term");
				}
			}
			catch(Exception e) {
				rethrowVWMLExceptionAsRecognitionException(e);
			}
	       } 
	     }
    ;

entity_decl returns [String id]
    : simple_entity_decl  {id = $simple_entity_decl.id;}
    | '(' { complexEntityDeclarationPhase1(); }  complex_entity_decl ')' { id = complexEntityDeclarationPhase3(); }
    ;

compound_entity_decl
    : entity_decl
    ;
    
simple_entity_decl returns [String id]
    : ID { id = simpleEntityDeclaration($ID.getText()); }
    ;
    
complex_entity_decl
    @after {
    	complexEntityDeclarationPhase2();
    }
    : (compound_entity_decl)+
    ;

term
    : expression
    ;  

entity returns [EntityWalker.Relation rel]
    : simple_entity         { 
    				rel = $simple_entity.rel;
    			    }

    | complex_entity        { 
    				rel = $complex_entity.rel;
    			    }
    | '.'                   {
                            	processComplexContext(lastProcessedEntity);
                            }
    ;


simple_entity returns [EntityWalker.Relation rel]
    : ID {
    		rel = simpleEntityAssembling($ID.text);
         }
    ;

complex_entity returns [EntityWalker.Relation rel]
    @init {
    	complexEntityStartAssembling();
    }
    @after {
        rel = complexEntityStopAssembling();
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
    : opclist       {
    			if (lastProcessedEntity != null && codeGenerator != null) { 
    				lastProcessedEntityAsTerm = true;
    				codeGenerator.associateOperation(lastProcessedEntity, $opclist.text, vwmlContextBuilder.buildContext());
    			} 
    		    }
    | opprojection  {
    			if (lastProcessedEntity != null && codeGenerator != null) {
    				lastProcessedEntityAsTerm = true;
    				codeGenerator.associateOperation(lastProcessedEntity, $opprojection.text, vwmlContextBuilder.buildContext());
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
    ;

opprojection
    : OPPROJECTION_1
    | OPPROJECTION_2
    | OPPROJECTION_3
    | OPPROJECTION_4
    | OPPROJECTION_5
    | OPPROJECTION_6
    | OPPROJECTION_7
    | OPPROJECTION_8
    | OPPROJECTION_9
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
    : (' '|'\t'|'\n'|'\r') {$channel=HIDDEN;}
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
	| '!'
	| '?'
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