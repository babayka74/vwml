
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
	private ComplexEntityNameBuilder complexEntityNameBuilderDef = ComplexEntityNameBuilder.instance();
	private EntityWalker entityWalker = EntityWalker.instance();
	private EntityWalker.Relation lastProcessedEntity = null;
	private String activeFringe = null;
	private String lastDeclaredCreatureId = null;
	private String lastDeclaredCreatureProps = null;
	private boolean lastProcessedEntityAsTerm = false;
	private String modName = null;
 	private boolean inDebug = false;
 	private boolean moduleInProgress = false;
 	
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
   				logger.debug("Interpreting objects '" + objLinkingId + "' -> '" + objLinkedId + "'");
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
    					logger.debug("Linked objects '" + linkingObjId + "' -> '" + linkedObj + "'");
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
    : author? projname? description? entity_history_size? visualizer? beyond_the_fringe?
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
    			vwmlContextBuilder.push($entity_decl.id);
    			entityWalker.markFutureEntityAsIAS($entity_decl.id);
    			if (codeGenerator != null) {
    				codeGenerator.declareContext(vwmlContextBuilder.buildContext());
    			}
    			// we should link this entity with parent, if exists
    			 buildLinkingAssociation($entity_decl.id);
    		      } term 
    		      {
    		      	// removes top entity from stack
    		      	vwmlContextBuilder.pop();
    		      }
    		      (SEMICOLON)?
    ;

check_term_def
    : LIFETERM '=' lifeterm_def
    | term_def
    ;
    
lifeterm_def
    :  term_def {
    			if (logger.isInfoEnabled()) {
    				logger.info("Lifeterm '" + lastProcessedEntity + "' found");
    			}
    			if (codeGenerator != null) {
  	       			try {
					codeGenerator.markEntityAsLifeTerm(lastProcessedEntity);
					if (logger.isDebugEnabled()) {
						logger.debug("entity '" + lastProcessedEntity + "' marked as lifeterm");
					}
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
    | '(' {
    	    	complexEntityNameBuilderDecl.startProgress();
	    	if (logger.isDebugEnabled()) {
	    		logger.debug("complex entity declaration process - started");
	    	}
          }  complex_entity_decl ')' {
              				id = complexEntityNameBuilderDecl.build();
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
          			     }
    ;

compound_entity_decl
    : entity_decl
    ;
    
simple_entity_decl returns [String id]
    : ID {
    		id = $ID.getText();
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
         }
    ;
    
complex_entity_decl
    @after {
    	complexEntityNameBuilderDecl.stopProgress();
    }
    : (compound_entity_decl)+
    ;

term
    : expression
    ;  

entity returns [EntityWalker.Relation rel]
    : simple_entity         {rel = $simple_entity.rel; }
    | complex_entity        {rel = $complex_entity.rel;}
    | syncronization_entity {rel = $syncronization_entity.rel;}
    ;


syncronization_entity returns [EntityWalker.Relation rel]
    : '[' entity {rel = $entity.rel; } ']' 
    ;

simple_entity returns [EntityWalker.Relation rel]
    : ID {
    		// since entity's id may include '.' we should check correctness of name
    		// if name ends with '.' we can suppouse that this name can be considered as complex entity's effective context
    		if (!vwmlContextBuilder.isEffectiveContext($ID.text)) {
    			if (complexEntityNameBuilderDef.isInProgress()) {
    				complexEntityNameBuilderDef.addObjectId($ID.text);
    			}
   			rel = buildRelation($ID.text);
    			if (logger.isDebugEnabled()) {
    				logger.debug("processed simple entity '" + rel + "'");
    			}
    		}
    		else {
    			// so entity is considered as effective context
    			vwmlContextBuilder.setEffectiveContext($ID.text);
     			if (logger.isDebugEnabled()) {
    				logger.debug("effective context detected '" + vwmlContextBuilder.getEffectiveContext() + "'");
    			}   			
    		}
         }
    ;

complex_entity returns [EntityWalker.Relation rel]
    @init {
    	// id and name is the same
    	complexEntityNameBuilderDef.startProgress();
    	String ceId = complexEntityNameBuilderDecl.generateRandomName();
    	try {
    		if (codeGenerator != null) {
    			String context = null;
    			if (!vwmlContextBuilder.isEffectiveContextUsed()) { 
    				context = vwmlContextBuilder.buildContext();
    			}
    			else {
    				context = vwmlContextBuilder.getEffectiveContext();
	    			if (logger.isDebugEnabled()) {
	    				logger.debug("complex entity '" + ceId + "' belongs to context '" + context + "'");
	    			}
	    			ceId = vwmlContextBuilder.createAbsoluteEffectiveContextPath(ceId);
	    			vwmlContextBuilder.resetEffectiveContext();
    			}
    			codeGenerator.declareComplexEntity(ceId, null, context);
    		}
    	}
    	catch(Exception e) {
    		rethrowVWMLExceptionAsRecognitionException(e);
    	}
        // the complex enity (name/id is generated) is pushed to stack (here complex entity is part of expression)
    	rel = buildRelation(ceId);
    	entityWalker.push(rel);
   	if (logger.isDebugEnabled()) {
   		logger.debug("complex entity '" + rel + "' is declared");
   	}    	
    }
    @after {
    	// builds complex entity readable name instead of generated
    	complexEntityNameBuilderDef.stopProgress();
        ceId = complexEntityNameBuilderDef.build();
        complexEntityNameBuilderDef.clear();
        // remove it from stack
    	rel = (EntityWalker.Relation)entityWalker.pop();
    	if (logger.isDebugEnabled()) {
    		logger.debug("processed complex entity '" + rel + "'");
    	}    
    }
    : '(' (term)* ')'
    ;

ID
    : LETTER (LETTER|'0'..'9'|'.')* // ('a'..'z'|'A'..'Z'|'0'..'9'|'.'|'_'|'*'|'-')+
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
	|  'a'..'z'
	|  '_'
	;
