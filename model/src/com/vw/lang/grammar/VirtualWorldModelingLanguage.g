
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
}

@lexer::header { 
package com.vw.lang.grammar;
}

@rulecatch {
    catch (RecognitionException e) {
        throw e;
    }
}

@members {

	//private VWMLModelBuilder s_vwmlModelBuilder = null;
 	private boolean inDebug = false;
	
	protected void setInDebug(boolean inDebug) {
		this.inDebug = inDebug;
		// s_vwmlModelBuilder.setDebug(inDebug);		
	}
	
	protected boolean isInDebug() {
		return this.inDebug;
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
    : lang generatedFileLocation
    ;

lang
    : ('language' '=' JAVA) => langJava
    | otherLanguages
    ;	

otherLanguages
    :	
    ;

langJava
    : 'language' '=' JAVA '{' javaProps '}'
    ;
    
javaProps
    : propPackage
    ;
    	
propPackage
    : 'package' '=' packageName
    ;

packageName
    : STRING_LITERAL
    ;

generatedFileLocation
    : 'path' '=' path
    ;	

path
    : STRING_LITERAL
    ;


module
    : 'module' ID body EOF { }
    ;
    
body
   : '{' (expression (expression)*)? '}'
   ;

 
expression
    : (entity_decl IAS) => entity_def
    | term_def (SEMICOLON)?
    ;

entity_def
    : entity_decl IAS term (SEMICOLON)?
    ;

term_def
    : entity (oplist)*
    ;

entity_decl
    : simple_entity_decl
    | complex_entity_decl
    ;
    
simple_entity_decl
    : ID {System.out.println("e name '" + $ID.getText() + "'");}
    ;
    
complex_entity_decl
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
    : ID
    ;

complex_entity
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

fragment
LETTER
	: 'A'..'Z'
	|  'a'..'z'
	|  '_'
	;
