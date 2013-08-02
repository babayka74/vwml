
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

import com.vw.lang.processor.model.builder.VWMLModelBuilder;
import com.vw.lang.sink.ICodeGenerator;
import com.vw.lang.sink.ICodeGenerator.StartModuleProps;
import com.vw.lang.sink.utils.ComplexEntityNameBuilder;
import com.vw.lang.sink.utils.EntityWalker;
import com.vw.lang.sink.utils.GeneralUtils;

import com.vw.lang.sink.java.code.JavaCodeGenerator;
import com.vw.lang.sink.java.code.JavaCodeGenerator.JavaModuleStartProps;

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
	private String modName = null;
 	private boolean inDebug = false;
	
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
    		// creates 'IAS' association
    		entityWalker.resetFutureEntityAsIAS();
    		try {
    			codeGenerator.interpretObjects(objLinkingId, objLinkedId);
   			System.out.println("Interpreting objects '" + objLinkingId + "' -> '" + objLinkedId + "'");
    		}
    		catch(Exception e) {
    			rethrowVWMLExceptionAsRecognitionException(e);
    		}	
	}
	
	protected void buildLinkingAssociation(Object linkedObj) throws RecognitionException {
  		Object linkingObjId = entityWalker.peek();
    		if (linkingObjId != null) {
    			try {
    				codeGenerator.linkObjects(linkingObjId, linkedObj);
    				System.out.println("Linked objects '" + linkingObjId + "' -> '" + linkedObj + "'");
    			}
    			catch(Exception e) {
    				rethrowVWMLExceptionAsRecognitionException(e);
    			}
    		}
	}
	
	protected void rethrowVWMLExceptionAsRecognitionException(Exception e) throws RecognitionException {
		throw new RecognitionException(new ANTLRStringStream(e.getMessage()));
	}
}


filedef
    : props module
    | module
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
       System.out.println("Code generator '" + codeGenerator + "'");
    }
    : 'language' '=' JAVA '{' javaProps '}'
    ;

javaProps
    @init {
	// instantiating module's properties which will be filled later
	modProps = (codeGenerator != null) ? codeGenerator.buildProps() : null;
    }
    : propPackage generatedFileLocation optionalProps
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
    : author? description?
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

path
    : STRING_LITERAL
    ;


module
    : 'module' ID { 
    			modName = $ID.getText(); 
    			if (modProps != null) {
    				((JavaCodeGenerator.JavaModuleStartProps)modProps).setModuleName(modName);
	    			try {
	    				codeGenerator.startModule(modProps);
	    			}
	    			catch(Exception e) {
	    				System.out.println("Caught exception '" + e + "'");
	    				rethrowVWMLExceptionAsRecognitionException(e);
	    			}
    			}
    			// starts module's definition
                  } body EOF { }
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
    			System.out.println("e name '" + $entity_decl.id + "'");
    			entityWalker.markFutureEntityAsIAS($entity_decl.id);
    		      } term (SEMICOLON)?
    ;

term_def
    : entity (oplist)*
    ;

entity_decl returns [String id]
    : simple_entity_decl  {id = $simple_entity_decl.id;}
    | complex_entity_decl {id = $complex_entity_decl.id;}
    ;
    
simple_entity_decl returns [String id]
    : ID {
    		id = $ID.getText();
    		// means that complex entity's name is being built
    		if (complexEntityNameBuilder.isInProgress()) {
    			complexEntityNameBuilder.addObjectId(id);
    		}
    		else {
    			try {
    				codeGenerator.declareSimpleEntity(id);
    			}
    			catch(Exception e) {
    				rethrowVWMLExceptionAsRecognitionException(e);
    			}
    		}
         }
    ;
    
complex_entity_decl returns [String id]
    @init {
    	complexEntityNameBuilder.startProgress();
    }
    @after {
    	complexEntityNameBuilder.stopProgress();
    	id = complexEntityNameBuilder.build();
    	try {
    		codeGenerator.declareComplexEntity(id);
    	}
    	catch(Exception e) {
    		rethrowVWMLExceptionAsRecognitionException(e);
    	}
    }
    : ('(' (simple_entity_decl)+ ')')?
    ;

term
    : expression
    ;  

entity
    : simple_entity
    | complex_entity 
    ;


simple_entity
    : ID {
    	    	if (entityWalker.getEntityMarkedAsIAS() != null) {
    			buildIASAssociation($ID.text);
    		}
    		else {
   			buildLinkingAssociation($ID.text);
    		}
         }
    ;

complex_entity
    @init {
    	// id and name is the same
    	String ceId = ComplexEntityNameBuilder.generateRandomName();
    	try {
    		codeGenerator.declareComplexEntity(ceId);
    	}
    	catch(Exception e) {
    		rethrowVWMLExceptionAsRecognitionException(e);
    	}
    	// in case if entity was marked as IAS we have to build IAS association
    	if (entityWalker.getEntityMarkedAsIAS() != null) {
    		buildIASAssociation(ceId);
    	}
    	else {
   		buildLinkingAssociation(ceId);
    	}
        // the complex enity (name/id is generated) is pushed to stack (here complex entity is part of expression)
    	entityWalker.push(ceId);
    }
    @after {
        // remove it from stack
    	entityWalker.pop();    
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
    : opclist
    | opprojection
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
