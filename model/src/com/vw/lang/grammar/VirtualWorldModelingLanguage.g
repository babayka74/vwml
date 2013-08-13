
/**
---------------------- Virtual World's language grammar ------------------------------------

*/

grammar VirtualWorldModelingLanguage;
 
tokens {
    IAS='ias';
    NIL='nil';
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

// builder
import com.vw.lang.processor.model.builder.VWMLModelBuilder;
import com.vw.lang.processor.model.builder.VWMLModuleInfo;

// general code generator
import com.vw.lang.sink.ICodeGenerator;
import com.vw.lang.sink.ICodeGenerator.StartModuleProps;
import com.vw.lang.sink.utils.ComplexEntityNameBuilder;
import com.vw.lang.sink.utils.EntityWalker;
import com.vw.lang.sink.utils.GeneralUtils;

// specific code generator
import com.vw.lang.sink.java.link.IVWMLLinkVisitor;
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
	private VWMLModelBuilder vwmlModelBuilder = VWMLModelBuilder.instance();
	private ICodeGenerator codeGenerator = null;
	private StartModuleProps modProps = null;
	private ComplexEntityNameBuilder complexEntityNameBuilder = ComplexEntityNameBuilder.instance();
	private EntityWalker entityWalker = EntityWalker.instance();
	private String lastProcessedEntityId = null;
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
	
	protected void buildIASAssociation(Object id) throws RecognitionException {
    		Object objLinkingId = entityWalker.getEntityMarkedAsIAS();
    		Object objLinkedId = id;
    		entityWalker.resetFutureEntityAsIAS();
       		// creates 'IAS' association
    		try {
    			if (codeGenerator != null) codeGenerator.interpretObjects(objLinkingId, objLinkedId);
    			if (logger.isDebugEnabled()) {
   				logger.debug("Interpreting objects '" + objLinkingId + "' -> '" + objLinkedId + "'");
   			}
    		}
    		catch(Exception e) {
    			rethrowVWMLExceptionAsRecognitionException(e);
    		}	
	}
	
	protected void buildLinkingAssociation(Object linkedObj) throws RecognitionException {
  		Object linkingObjId = entityWalker.peek();
  		if (linkingObjId == null) {
  			linkingObjId = ComplexEntityNameBuilder.generateRootId(modName);
  		}
    		if (linkingObjId != null) {
    			try {
    				if (codeGenerator != null) codeGenerator.linkObjects(linkingObjId, linkedObj);
    				if (logger.isDebugEnabled()) {
    					logger.debug("Linked objects '" + linkingObjId + "' -> '" + linkedObj + "'");
    				}
    			}
    			catch(Exception e) {
    				rethrowVWMLExceptionAsRecognitionException(e);
    			}
    		}
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
		throw new RecognitionException(new ANTLRStringStream(e.getMessage()));
	}	
}


filedef
    : props? (include (include)*)? module? EOF {
                             	if (moduleInProgress && modProps != null) {
                             		try {
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
    : author? description? visualizer?
    ;

author
    : 'author' '=' string {
	    			if (modProps != null) {
	    				((JavaCodeGenerator.JavaModuleStartProps)modProps).setAuthor(GeneralUtils.trimQuotes($string.text));
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
    					((JavaCodeGenerator.JavaModuleStartProps)modProps).setVisitor((IVWMLLinkVisitor)GeneralUtils.instantiateClassThroughStaticMethod(GeneralUtils.trimQuotes($string.text), "instance"));
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
    | term_def (SEMICOLON)?
    ;

entity_def
    : entity_decl IAS {
    			entityWalker.markFutureEntityAsIAS($entity_decl.id);
    			// we should link this entity with parent, if exists
    			 buildLinkingAssociation($entity_decl.id);
    		      } term (SEMICOLON)?
    ;

term_def
    : entity {
    		lastProcessedEntityId = $entity.id;
    		lastProcessedEntityAsTerm = false;
    	     } (oplist)* 
  	     {
  	       if (lastProcessedEntityAsTerm && codeGenerator != null) {
  	       		try {
				codeGenerator.markEntityAsTerm(lastProcessedEntityId);
				if (logger.isDebugEnabled()) {
					logger.debug("entity '" + lastProcessedEntityId + "' marked as term");
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
    	    	complexEntityNameBuilder.startProgress();
	    	if (logger.isDebugEnabled()) {
	    		logger.debug("complex entity declaration process - started");
	    	}
          }  complex_entity_decl ')' {
              				id = complexEntityNameBuilder.build();
              				complexEntityNameBuilder.clear();
				    	try {
				    		if (codeGenerator != null) codeGenerator.declareComplexEntity(id);    	
				    		if (logger.isDebugEnabled()) {
				    			logger.debug("complex entity '" + id + "' is declared");
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
    		if (complexEntityNameBuilder.isInProgress()) {
    		        if (logger.isDebugEnabled()) {
    		        	logger.debug("simple entity '" + id + "' is added as part of complex entity");
    		        }
    			complexEntityNameBuilder.addObjectId(id);
    		}
    		else {
    			try {
    		        	if (logger.isDebugEnabled()) {
    		        		logger.debug("simple entity '" + id + "' is declared");
    		        	}
    				if (codeGenerator != null) codeGenerator.declareSimpleEntity(id);
    			}
    			catch(Exception e) {
    				rethrowVWMLExceptionAsRecognitionException(e);
    			}
    		}
         }
    ;
    
complex_entity_decl
    @after {
    	complexEntityNameBuilder.stopProgress();
    }
    : (compound_entity_decl)+
    ;

term
    : expression
    ;  

entity returns [String id]
    : simple_entity   {id = $simple_entity.id; }
    | complex_entity  {id = $complex_entity.id;}
    ;


simple_entity returns [String id]
    : ID {
    	    	if (entityWalker.getEntityMarkedAsIAS() != null) {
    			buildIASAssociation($ID.text);
    		}
    		else {
   			buildLinkingAssociation($ID.text);
    		}
    		id = $ID.text;
    		if (logger.isDebugEnabled()) {
    			logger.debug("processed simple entity '" + id + "'");
    		}
         }
    ;

complex_entity returns [String id]
    @init {
    	// id and name is the same
    	String ceId = ComplexEntityNameBuilder.generateRandomName();
    	try {
    		if (logger.isDebugEnabled()) {
    			logger.debug("complex entity '" + ceId + "' is declared");
    		}    	
    		if (codeGenerator != null) codeGenerator.declareComplexEntity(ceId);
    	}
    	catch(Exception e) {
    		rethrowVWMLExceptionAsRecognitionException(e);
    	}
    	// in case if entity was marked as IAS we have to build IAS association
    	if (entityWalker.getEntityMarkedAsIAS() != null) {
    		buildIASAssociation(ceId);
    	}
    	else {
    		// ... otherwise linkage
  		buildLinkingAssociation(ceId);
  	}
        // the complex enity (name/id is generated) is pushed to stack (here complex entity is part of expression)
    	entityWalker.push(ceId);
    }
    @after {
        // remove it from stack
    	id = (String)entityWalker.pop();
    	if (logger.isDebugEnabled()) {
    		logger.debug("processed complex entity '" + id + "'");
    	}    
    }
    : '(' (term)* ')'
    ;

ID
    : LETTER (LETTER|'0'..'9')* // ('a'..'z'|'A'..'Z'|'0'..'9'|'.'|'_'|'*'|'-')+
    ;

STRING_LITERAL
    :  '"' ( ~('"') )* '"'
    ;    

oplist
    // associates operation with entity
    : opclist       { if (lastProcessedEntityId != null && codeGenerator != null) { lastProcessedEntityAsTerm = true; codeGenerator.associateOperation(lastProcessedEntityId, $opclist.text); } }
    | opprojection  { if (lastProcessedEntityId != null && codeGenerator != null) { lastProcessedEntityAsTerm = true; codeGenerator.associateOperation(lastProcessedEntityId, $opprojection.text); } }
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
