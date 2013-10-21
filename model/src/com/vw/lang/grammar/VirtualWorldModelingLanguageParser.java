// $ANTLR 3.4 C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g 2013-10-21 07:26:08

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



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

/**
---------------------- Virtual World's language grammar ------------------------------------

*/
@SuppressWarnings({"all", "warnings", "unchecked"})
public class VirtualWorldModelingLanguageParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "C", "COMMA", "COMMENT", "CPP", "DQUOTE", "IAS", "ID", "JAVA", "LETTER", "LIFETERM", "LINE_COMMENT", "NATIVE_CODE", "OBJECTIVEC", "OPACTIVATECTX", "OPACTIVATEONFRINGE", "OPBEGIN", "OPCARTESIAN", "OPCREATEEXPR", "OPEQ", "OPEXECUTE", "OPFIRST", "OPIDENT", "OPIN", "OPINCL", "OPINTERPRET", "OPINTERSECT", "OPJOIN", "OPLAST", "OPPROJECTION_1", "OPPROJECTION_2", "OPPROJECTION_3", "OPPROJECTION_4", "OPPROJECTION_5", "OPPROJECTION_6", "OPPROJECTION_7", "OPPROJECTION_8", "OPPROJECTION_9", "OPRANDOM", "OPRELAX", "OPREST", "OPSQU", "OPSUBSTRUCT", "SEMICOLON", "STRING_LITERAL", "WS", "'('", "')'", "'='", "'['", "']'", "'author'", "'beyond'", "'class'", "'data'", "'description'", "'entity_history_size'", "'fringe'", "'include'", "'language'", "'module'", "'options'", "'package'", "'path'", "'project_name'", "'visualizer'", "'{'", "'}'"
    };

    public static final int EOF=-1;
    public static final int T__49=49;
    public static final int T__50=50;
    public static final int T__51=51;
    public static final int T__52=52;
    public static final int T__53=53;
    public static final int T__54=54;
    public static final int T__55=55;
    public static final int T__56=56;
    public static final int T__57=57;
    public static final int T__58=58;
    public static final int T__59=59;
    public static final int T__60=60;
    public static final int T__61=61;
    public static final int T__62=62;
    public static final int T__63=63;
    public static final int T__64=64;
    public static final int T__65=65;
    public static final int T__66=66;
    public static final int T__67=67;
    public static final int T__68=68;
    public static final int T__69=69;
    public static final int T__70=70;
    public static final int C=4;
    public static final int COMMA=5;
    public static final int COMMENT=6;
    public static final int CPP=7;
    public static final int DQUOTE=8;
    public static final int IAS=9;
    public static final int ID=10;
    public static final int JAVA=11;
    public static final int LETTER=12;
    public static final int LIFETERM=13;
    public static final int LINE_COMMENT=14;
    public static final int NATIVE_CODE=15;
    public static final int OBJECTIVEC=16;
    public static final int OPACTIVATECTX=17;
    public static final int OPACTIVATEONFRINGE=18;
    public static final int OPBEGIN=19;
    public static final int OPCARTESIAN=20;
    public static final int OPCREATEEXPR=21;
    public static final int OPEQ=22;
    public static final int OPEXECUTE=23;
    public static final int OPFIRST=24;
    public static final int OPIDENT=25;
    public static final int OPIN=26;
    public static final int OPINCL=27;
    public static final int OPINTERPRET=28;
    public static final int OPINTERSECT=29;
    public static final int OPJOIN=30;
    public static final int OPLAST=31;
    public static final int OPPROJECTION_1=32;
    public static final int OPPROJECTION_2=33;
    public static final int OPPROJECTION_3=34;
    public static final int OPPROJECTION_4=35;
    public static final int OPPROJECTION_5=36;
    public static final int OPPROJECTION_6=37;
    public static final int OPPROJECTION_7=38;
    public static final int OPPROJECTION_8=39;
    public static final int OPPROJECTION_9=40;
    public static final int OPRANDOM=41;
    public static final int OPRELAX=42;
    public static final int OPREST=43;
    public static final int OPSQU=44;
    public static final int OPSUBSTRUCT=45;
    public static final int SEMICOLON=46;
    public static final int STRING_LITERAL=47;
    public static final int WS=48;

    // delegates
    public Parser[] getDelegates() {
        return new Parser[] {};
    }

    // delegators


    public VirtualWorldModelingLanguageParser(TokenStream input) {
        this(input, new RecognizerSharedState());
    }
    public VirtualWorldModelingLanguageParser(TokenStream input, RecognizerSharedState state) {
        super(input, state);
    }

    public String[] getTokenNames() { return VirtualWorldModelingLanguageParser.tokenNames; }
    public String getGrammarFileName() { return "C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g"; }



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
    		



    // $ANTLR start "filedef"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:239:1: filedef : ( props )? ( include ( include )* )? ( module )? EOF ;
    public final void filedef() throws RecognitionException {
        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:240:5: ( ( props )? ( include ( include )* )? ( module )? EOF )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:240:7: ( props )? ( include ( include )* )? ( module )? EOF
            {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:240:7: ( props )?
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0==64) ) {
                alt1=1;
            }
            switch (alt1) {
                case 1 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:240:7: props
                    {
                    pushFollow(FOLLOW_props_in_filedef383);
                    props();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }


            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:240:14: ( include ( include )* )?
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==61) ) {
                alt3=1;
            }
            switch (alt3) {
                case 1 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:240:15: include ( include )*
                    {
                    pushFollow(FOLLOW_include_in_filedef387);
                    include();

                    state._fsp--;
                    if (state.failed) return ;

                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:240:23: ( include )*
                    loop2:
                    do {
                        int alt2=2;
                        int LA2_0 = input.LA(1);

                        if ( (LA2_0==61) ) {
                            alt2=1;
                        }


                        switch (alt2) {
                    	case 1 :
                    	    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:240:24: include
                    	    {
                    	    pushFollow(FOLLOW_include_in_filedef390);
                    	    include();

                    	    state._fsp--;
                    	    if (state.failed) return ;

                    	    }
                    	    break;

                    	default :
                    	    break loop2;
                        }
                    } while (true);


                    }
                    break;

            }


            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:240:36: ( module )?
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==63) ) {
                alt4=1;
            }
            switch (alt4) {
                case 1 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:240:36: module
                    {
                    pushFollow(FOLLOW_module_in_filedef396);
                    module();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }


            match(input,EOF,FOLLOW_EOF_in_filedef399); if (state.failed) return ;

            if ( state.backtracking==0 ) {
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

            }

        }

            catch (RecognitionException e) {
                throw e;
            }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "filedef"



    // $ANTLR start "include"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:261:1: include : include_vwml ;
    public final void include() throws RecognitionException {
        String include_vwml1 =null;


        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:262:5: ( include_vwml )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:262:7: include_vwml
            {
            pushFollow(FOLLOW_include_vwml_in_include420);
            include_vwml1=include_vwml();

            state._fsp--;
            if (state.failed) return ;

            if ( state.backtracking==0 ) {
                			if (logger.isInfoEnabled()) {
                				logger.info("including '" + include_vwml1 + "'");
                			}
                			processInclude(GeneralUtils.trimQuotes(include_vwml1)); 
                               }

            }

        }

            catch (RecognitionException e) {
                throw e;
            }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "include"



    // $ANTLR start "include_vwml"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:270:1: include_vwml returns [String id] : 'include' STRING_LITERAL ;
    public final String include_vwml() throws RecognitionException {
        String id = null;


        Token STRING_LITERAL2=null;

        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:271:5: ( 'include' STRING_LITERAL )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:271:8: 'include' STRING_LITERAL
            {
            match(input,61,FOLLOW_61_in_include_vwml448); if (state.failed) return id;

            STRING_LITERAL2=(Token)match(input,STRING_LITERAL,FOLLOW_STRING_LITERAL_in_include_vwml450); if (state.failed) return id;

            if ( state.backtracking==0 ) {id = (STRING_LITERAL2!=null?STRING_LITERAL2.getText():null);}

            }

        }

            catch (RecognitionException e) {
                throw e;
            }

        finally {
        	// do for sure before leaving
        }
        return id;
    }
    // $ANTLR end "include_vwml"



    // $ANTLR start "props"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:274:1: props : 'options' '{' optionsList '}' ;
    public final void props() throws RecognitionException {
        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:275:5: ( 'options' '{' optionsList '}' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:275:7: 'options' '{' optionsList '}'
            {
            match(input,64,FOLLOW_64_in_props469); if (state.failed) return ;

            match(input,69,FOLLOW_69_in_props471); if (state.failed) return ;

            pushFollow(FOLLOW_optionsList_in_props473);
            optionsList();

            state._fsp--;
            if (state.failed) return ;

            match(input,70,FOLLOW_70_in_props475); if (state.failed) return ;

            }

        }

            catch (RecognitionException e) {
                throw e;
            }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "props"



    // $ANTLR start "optionsList"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:278:1: optionsList : lang ;
    public final void optionsList() throws RecognitionException {
        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:279:5: ( lang )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:279:7: lang
            {
            pushFollow(FOLLOW_lang_in_optionsList496);
            lang();

            state._fsp--;
            if (state.failed) return ;

            }

        }

            catch (RecognitionException e) {
                throw e;
            }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "optionsList"



    // $ANTLR start "lang"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:282:1: lang : ( ( 'language' '=' JAVA )=> langJava | otherLanguages );
    public final void lang() throws RecognitionException {
        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:283:5: ( ( 'language' '=' JAVA )=> langJava | otherLanguages )
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==62) && (synpred1_VirtualWorldModelingLanguage())) {
                alt5=1;
            }
            else if ( (LA5_0==70) ) {
                alt5=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 5, 0, input);

                throw nvae;

            }
            switch (alt5) {
                case 1 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:283:7: ( 'language' '=' JAVA )=> langJava
                    {
                    pushFollow(FOLLOW_langJava_in_lang523);
                    langJava();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:284:7: otherLanguages
                    {
                    pushFollow(FOLLOW_otherLanguages_in_lang531);
                    otherLanguages();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }
        }

            catch (RecognitionException e) {
                throw e;
            }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "lang"



    // $ANTLR start "otherLanguages"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:287:1: otherLanguages :;
    public final void otherLanguages() throws RecognitionException {
        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:288:5: ()
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:289:5: 
            {
            }

        }
        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "otherLanguages"



    // $ANTLR start "langJava"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:291:1: langJava : 'language' '=' JAVA '{' javaProps '}' ;
    public final void langJava() throws RecognitionException {

               codeGenerator = vwmlModelBuilder.getCodeGenerator(VWMLModelBuilder.SINK_TYPE.JAVA);
               if (logger.isDebugEnabled()) {
               		logger.debug("Code generator '" + codeGenerator + "'");
               }
            
        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:298:5: ( 'language' '=' JAVA '{' javaProps '}' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:298:7: 'language' '=' JAVA '{' javaProps '}'
            {
            match(input,62,FOLLOW_62_in_langJava574); if (state.failed) return ;

            match(input,51,FOLLOW_51_in_langJava576); if (state.failed) return ;

            match(input,JAVA,FOLLOW_JAVA_in_langJava578); if (state.failed) return ;

            match(input,69,FOLLOW_69_in_langJava580); if (state.failed) return ;

            pushFollow(FOLLOW_javaProps_in_langJava582);
            javaProps();

            state._fsp--;
            if (state.failed) return ;

            match(input,70,FOLLOW_70_in_langJava584); if (state.failed) return ;

            }

        }

            catch (RecognitionException e) {
                throw e;
            }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "langJava"



    // $ANTLR start "javaProps"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:301:1: javaProps : propPackage ( generatedFileLocation )? optionalProps ;
    public final void javaProps() throws RecognitionException {

        	// instantiating module's properties which will be filled later
        	modProps = (codeGenerator != null) ? codeGenerator.buildProps() : null;
        	// tell to builder reference to module's properties
        	if (vwmlModelBuilder.getProjectProps() == null) {
        		vwmlModelBuilder.setProjectProps(modProps);
        	}
            
        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:310:5: ( propPackage ( generatedFileLocation )? optionalProps )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:310:7: propPackage ( generatedFileLocation )? optionalProps
            {
            pushFollow(FOLLOW_propPackage_in_javaProps610);
            propPackage();

            state._fsp--;
            if (state.failed) return ;

            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:310:19: ( generatedFileLocation )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==66) ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:310:19: generatedFileLocation
                    {
                    pushFollow(FOLLOW_generatedFileLocation_in_javaProps612);
                    generatedFileLocation();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }


            pushFollow(FOLLOW_optionalProps_in_javaProps615);
            optionalProps();

            state._fsp--;
            if (state.failed) return ;

            }

        }

            catch (RecognitionException e) {
                throw e;
            }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "javaProps"



    // $ANTLR start "propPackage"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:313:1: propPackage : 'package' '=' packageName ;
    public final void propPackage() throws RecognitionException {
        VirtualWorldModelingLanguageParser.packageName_return packageName3 =null;


        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:314:5: ( 'package' '=' packageName )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:314:7: 'package' '=' packageName
            {
            match(input,65,FOLLOW_65_in_propPackage637); if (state.failed) return ;

            match(input,51,FOLLOW_51_in_propPackage639); if (state.failed) return ;

            pushFollow(FOLLOW_packageName_in_propPackage641);
            packageName3=packageName();

            state._fsp--;
            if (state.failed) return ;

            if ( state.backtracking==0 ) {
            	    			  if (modProps != null) {
            	    				((JavaCodeGenerator.JavaModuleStartProps)modProps).setModulePackage(GeneralUtils.trimQuotes((packageName3!=null?input.toString(packageName3.start,packageName3.stop):null)));
            	    			  }
                			        }

            }

        }

            catch (RecognitionException e) {
                throw e;
            }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "propPackage"


    public static class packageName_return extends ParserRuleReturnScope {
    };


    // $ANTLR start "packageName"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:321:1: packageName : STRING_LITERAL ;
    public final VirtualWorldModelingLanguageParser.packageName_return packageName() throws RecognitionException {
        VirtualWorldModelingLanguageParser.packageName_return retval = new VirtualWorldModelingLanguageParser.packageName_return();
        retval.start = input.LT(1);


        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:322:5: ( STRING_LITERAL )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:322:7: STRING_LITERAL
            {
            match(input,STRING_LITERAL,FOLLOW_STRING_LITERAL_in_packageName660); if (state.failed) return retval;

            }

            retval.stop = input.LT(-1);


        }

            catch (RecognitionException e) {
                throw e;
            }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "packageName"



    // $ANTLR start "generatedFileLocation"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:325:1: generatedFileLocation : 'path' '=' path ;
    public final void generatedFileLocation() throws RecognitionException {
        VirtualWorldModelingLanguageParser.path_return path4 =null;


        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:326:5: ( 'path' '=' path )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:326:7: 'path' '=' path
            {
            match(input,66,FOLLOW_66_in_generatedFileLocation677); if (state.failed) return ;

            match(input,51,FOLLOW_51_in_generatedFileLocation679); if (state.failed) return ;

            pushFollow(FOLLOW_path_in_generatedFileLocation681);
            path4=path();

            state._fsp--;
            if (state.failed) return ;

            if ( state.backtracking==0 ) {
                			if (modProps != null) {
                				((JavaCodeGenerator.JavaModuleStartProps)modProps).setSrcPath(GeneralUtils.trimQuotes((path4!=null?input.toString(path4.start,path4.stop):null)));
                			}
                		      }

            }

        }

            catch (RecognitionException e) {
                throw e;
            }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "generatedFileLocation"



    // $ANTLR start "optionalProps"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:333:1: optionalProps : ( author )? ( projname )? ( description )? ( entity_history_size )? ( visualizer )? ( beyond_the_fringe )? ;
    public final void optionalProps() throws RecognitionException {
        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:334:5: ( ( author )? ( projname )? ( description )? ( entity_history_size )? ( visualizer )? ( beyond_the_fringe )? )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:334:7: ( author )? ( projname )? ( description )? ( entity_history_size )? ( visualizer )? ( beyond_the_fringe )?
            {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:334:7: ( author )?
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==54) ) {
                alt7=1;
            }
            switch (alt7) {
                case 1 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:334:7: author
                    {
                    pushFollow(FOLLOW_author_in_optionalProps701);
                    author();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }


            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:334:15: ( projname )?
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==67) ) {
                alt8=1;
            }
            switch (alt8) {
                case 1 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:334:15: projname
                    {
                    pushFollow(FOLLOW_projname_in_optionalProps704);
                    projname();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }


            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:334:25: ( description )?
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==58) ) {
                alt9=1;
            }
            switch (alt9) {
                case 1 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:334:25: description
                    {
                    pushFollow(FOLLOW_description_in_optionalProps707);
                    description();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }


            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:334:38: ( entity_history_size )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==59) ) {
                alt10=1;
            }
            switch (alt10) {
                case 1 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:334:38: entity_history_size
                    {
                    pushFollow(FOLLOW_entity_history_size_in_optionalProps710);
                    entity_history_size();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }


            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:334:59: ( visualizer )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==68) ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:334:59: visualizer
                    {
                    pushFollow(FOLLOW_visualizer_in_optionalProps713);
                    visualizer();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }


            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:334:71: ( beyond_the_fringe )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==55) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:334:71: beyond_the_fringe
                    {
                    pushFollow(FOLLOW_beyond_the_fringe_in_optionalProps716);
                    beyond_the_fringe();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }


            }

        }

            catch (RecognitionException e) {
                throw e;
            }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "optionalProps"



    // $ANTLR start "author"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:337:1: author : 'author' '=' string ;
    public final void author() throws RecognitionException {
        VirtualWorldModelingLanguageParser.string_return string5 =null;


        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:338:5: ( 'author' '=' string )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:338:7: 'author' '=' string
            {
            match(input,54,FOLLOW_54_in_author734); if (state.failed) return ;

            match(input,51,FOLLOW_51_in_author736); if (state.failed) return ;

            pushFollow(FOLLOW_string_in_author738);
            string5=string();

            state._fsp--;
            if (state.failed) return ;

            if ( state.backtracking==0 ) {
            	    			if (modProps != null) {
            	    				((JavaCodeGenerator.JavaModuleStartProps)modProps).setAuthor(GeneralUtils.trimQuotes((string5!=null?input.toString(string5.start,string5.stop):null)));
            	    			}
                			  }

            }

        }

            catch (RecognitionException e) {
                throw e;
            }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "author"



    // $ANTLR start "projname"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:345:1: projname : 'project_name' '=' string ;
    public final void projname() throws RecognitionException {
        VirtualWorldModelingLanguageParser.string_return string6 =null;


        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:346:5: ( 'project_name' '=' string )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:346:7: 'project_name' '=' string
            {
            match(input,67,FOLLOW_67_in_projname757); if (state.failed) return ;

            match(input,51,FOLLOW_51_in_projname759); if (state.failed) return ;

            pushFollow(FOLLOW_string_in_projname761);
            string6=string();

            state._fsp--;
            if (state.failed) return ;

            if ( state.backtracking==0 ) {
            	    			if (modProps != null) {
            	    				((JavaCodeGenerator.JavaModuleStartProps)modProps).setProjectName(GeneralUtils.trimQuotes((string6!=null?input.toString(string6.start,string6.stop):null)));
            	    			}
                			  }

            }

        }

            catch (RecognitionException e) {
                throw e;
            }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "projname"



    // $ANTLR start "description"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:353:1: description : 'description' '=' string ;
    public final void description() throws RecognitionException {
        VirtualWorldModelingLanguageParser.string_return string7 =null;


        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:354:5: ( 'description' '=' string )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:354:7: 'description' '=' string
            {
            match(input,58,FOLLOW_58_in_description784); if (state.failed) return ;

            match(input,51,FOLLOW_51_in_description786); if (state.failed) return ;

            pushFollow(FOLLOW_string_in_description788);
            string7=string();

            state._fsp--;
            if (state.failed) return ;

            if ( state.backtracking==0 ) { 
                				if (modProps != null) {
                					((JavaCodeGenerator.JavaModuleStartProps)modProps).setDescription(GeneralUtils.trimQuotes((string7!=null?input.toString(string7.start,string7.stop):null)));
                				}
                			       }

            }

        }

            catch (RecognitionException e) {
                throw e;
            }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "description"



    // $ANTLR start "entity_history_size"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:361:1: entity_history_size : 'entity_history_size' '=' string ;
    public final void entity_history_size() throws RecognitionException {
        VirtualWorldModelingLanguageParser.string_return string8 =null;


        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:362:5: ( 'entity_history_size' '=' string )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:362:7: 'entity_history_size' '=' string
            {
            match(input,59,FOLLOW_59_in_entity_history_size807); if (state.failed) return ;

            match(input,51,FOLLOW_51_in_entity_history_size809); if (state.failed) return ;

            pushFollow(FOLLOW_string_in_entity_history_size811);
            string8=string();

            state._fsp--;
            if (state.failed) return ;

            if ( state.backtracking==0 ) { 
                				if (modProps != null) {
                					((JavaCodeGenerator.JavaModuleStartProps)modProps).setEntityHistorySize(GeneralUtils.trimQuotes((string8!=null?input.toString(string8.start,string8.stop):null)));
                				}
                			       }

            }

        }

            catch (RecognitionException e) {
                throw e;
            }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "entity_history_size"



    // $ANTLR start "visualizer"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:370:1: visualizer : 'visualizer' '{' visualizer_body '}' ;
    public final void visualizer() throws RecognitionException {
        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:371:5: ( 'visualizer' '{' visualizer_body '}' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:371:7: 'visualizer' '{' visualizer_body '}'
            {
            match(input,68,FOLLOW_68_in_visualizer831); if (state.failed) return ;

            match(input,69,FOLLOW_69_in_visualizer833); if (state.failed) return ;

            pushFollow(FOLLOW_visualizer_body_in_visualizer835);
            visualizer_body();

            state._fsp--;
            if (state.failed) return ;

            match(input,70,FOLLOW_70_in_visualizer837); if (state.failed) return ;

            }

        }

            catch (RecognitionException e) {
                throw e;
            }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "visualizer"



    // $ANTLR start "visualizer_body"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:374:1: visualizer_body : ( visualizer_class visualizer_datapath |);
    public final void visualizer_body() throws RecognitionException {
        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:375:5: ( visualizer_class visualizer_datapath |)
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==56) ) {
                alt13=1;
            }
            else if ( (LA13_0==70) ) {
                alt13=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 13, 0, input);

                throw nvae;

            }
            switch (alt13) {
                case 1 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:375:7: visualizer_class visualizer_datapath
                    {
                    pushFollow(FOLLOW_visualizer_class_in_visualizer_body855);
                    visualizer_class();

                    state._fsp--;
                    if (state.failed) return ;

                    pushFollow(FOLLOW_visualizer_datapath_in_visualizer_body857);
                    visualizer_datapath();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:377:5: 
                    {
                    }
                    break;

            }
        }

            catch (RecognitionException e) {
                throw e;
            }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "visualizer_body"



    // $ANTLR start "visualizer_class"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:379:1: visualizer_class : 'class' '=' string ;
    public final void visualizer_class() throws RecognitionException {
        VirtualWorldModelingLanguageParser.string_return string9 =null;


        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:380:5: ( 'class' '=' string )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:380:7: 'class' '=' string
            {
            match(input,56,FOLLOW_56_in_visualizer_class880); if (state.failed) return ;

            match(input,51,FOLLOW_51_in_visualizer_class882); if (state.failed) return ;

            pushFollow(FOLLOW_string_in_visualizer_class884);
            string9=string();

            state._fsp--;
            if (state.failed) return ;

            if ( state.backtracking==0 ) { 
                				if (modProps != null) {
                					((JavaCodeGenerator.JavaModuleStartProps)modProps).setVisitor((AbstractVWMLLinkVisitor)GeneralUtils.instantiateClassThroughStaticMethod(GeneralUtils.trimQuotes((string9!=null?input.toString(string9.start,string9.stop):null)), "instance"));
                				}
                			 }

            }

        }

            catch (RecognitionException e) {
                throw e;
            }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "visualizer_class"



    // $ANTLR start "visualizer_datapath"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:387:1: visualizer_datapath : 'data' '=' string ;
    public final void visualizer_datapath() throws RecognitionException {
        VirtualWorldModelingLanguageParser.string_return string10 =null;


        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:388:5: ( 'data' '=' string )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:388:7: 'data' '=' string
            {
            match(input,57,FOLLOW_57_in_visualizer_datapath907); if (state.failed) return ;

            match(input,51,FOLLOW_51_in_visualizer_datapath909); if (state.failed) return ;

            pushFollow(FOLLOW_string_in_visualizer_datapath911);
            string10=string();

            state._fsp--;
            if (state.failed) return ;

            if ( state.backtracking==0 ) { 
                				if (modProps != null) {
                					((JavaCodeGenerator.JavaModuleStartProps)modProps).setVisitorDataPath(GeneralUtils.trimQuotes((string10!=null?input.toString(string10.start,string10.stop):null)));
                				}
                			}

            }

        }

            catch (RecognitionException e) {
                throw e;
            }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "visualizer_datapath"


    public static class path_return extends ParserRuleReturnScope {
    };


    // $ANTLR start "path"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:395:1: path : STRING_LITERAL ;
    public final VirtualWorldModelingLanguageParser.path_return path() throws RecognitionException {
        VirtualWorldModelingLanguageParser.path_return retval = new VirtualWorldModelingLanguageParser.path_return();
        retval.start = input.LT(1);


        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:396:5: ( STRING_LITERAL )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:396:7: STRING_LITERAL
            {
            match(input,STRING_LITERAL,FOLLOW_STRING_LITERAL_in_path934); if (state.failed) return retval;

            }

            retval.stop = input.LT(-1);


        }

            catch (RecognitionException e) {
                throw e;
            }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "path"



    // $ANTLR start "beyond_the_fringe"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:400:1: beyond_the_fringe : 'beyond' '{' beyond_the_fringe_body '}' ;
    public final void beyond_the_fringe() throws RecognitionException {
        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:401:5: ( 'beyond' '{' beyond_the_fringe_body '}' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:401:7: 'beyond' '{' beyond_the_fringe_body '}'
            {
            match(input,55,FOLLOW_55_in_beyond_the_fringe952); if (state.failed) return ;

            match(input,69,FOLLOW_69_in_beyond_the_fringe954); if (state.failed) return ;

            pushFollow(FOLLOW_beyond_the_fringe_body_in_beyond_the_fringe956);
            beyond_the_fringe_body();

            state._fsp--;
            if (state.failed) return ;

            match(input,70,FOLLOW_70_in_beyond_the_fringe958); if (state.failed) return ;

            }

        }

            catch (RecognitionException e) {
                throw e;
            }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "beyond_the_fringe"



    // $ANTLR start "beyond_the_fringe_body"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:404:1: beyond_the_fringe_body : finges ;
    public final void beyond_the_fringe_body() throws RecognitionException {
        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:405:5: ( finges )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:405:8: finges
            {
            pushFollow(FOLLOW_finges_in_beyond_the_fringe_body980);
            finges();

            state._fsp--;
            if (state.failed) return ;

            }

        }

            catch (RecognitionException e) {
                throw e;
            }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "beyond_the_fringe_body"



    // $ANTLR start "finges"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:408:1: finges : ( fringe )+ ;
    public final void finges() throws RecognitionException {
        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:409:5: ( ( fringe )+ )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:409:8: ( fringe )+
            {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:409:8: ( fringe )+
            int cnt14=0;
            loop14:
            do {
                int alt14=2;
                int LA14_0 = input.LA(1);

                if ( (LA14_0==60) ) {
                    alt14=1;
                }


                switch (alt14) {
            	case 1 :
            	    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:409:9: fringe
            	    {
            	    pushFollow(FOLLOW_fringe_in_finges999);
            	    fringe();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    if ( cnt14 >= 1 ) break loop14;
            	    if (state.backtracking>0) {state.failed=true; return ;}
                        EarlyExitException eee =
                            new EarlyExitException(14, input);
                        throw eee;
                }
                cnt14++;
            } while (true);


            }

        }

            catch (RecognitionException e) {
                throw e;
            }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "finges"



    // $ANTLR start "fringe"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:412:1: fringe : 'fringe' ID 'ias' '(' creatures ')' ;
    public final void fringe() throws RecognitionException {
        Token ID11=null;

        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:413:5: ( 'fringe' ID 'ias' '(' creatures ')' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:413:8: 'fringe' ID 'ias' '(' creatures ')'
            {
            match(input,60,FOLLOW_60_in_fringe1019); if (state.failed) return ;

            ID11=(Token)match(input,ID,FOLLOW_ID_in_fringe1021); if (state.failed) return ;

            match(input,IAS,FOLLOW_IAS_in_fringe1023); if (state.failed) return ;

            if ( state.backtracking==0 ) {
                			setActiveFringe(ID11.getText());
                		   }

            match(input,49,FOLLOW_49_in_fringe1046); if (state.failed) return ;

            pushFollow(FOLLOW_creatures_in_fringe1048);
            creatures();

            state._fsp--;
            if (state.failed) return ;

            match(input,50,FOLLOW_50_in_fringe1050); if (state.failed) return ;

            }

        }

            catch (RecognitionException e) {
                throw e;
            }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "fringe"



    // $ANTLR start "creatures"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:419:1: creatures : ( creature )+ ;
    public final void creatures() throws RecognitionException {
        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:420:5: ( ( creature )+ )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:420:7: ( creature )+
            {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:420:7: ( creature )+
            int cnt15=0;
            loop15:
            do {
                int alt15=2;
                int LA15_0 = input.LA(1);

                if ( (LA15_0==ID) ) {
                    alt15=1;
                }


                switch (alt15) {
            	case 1 :
            	    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:420:8: creature
            	    {
            	    pushFollow(FOLLOW_creature_in_creatures1068);
            	    creature();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    if ( cnt15 >= 1 ) break loop15;
            	    if (state.backtracking>0) {state.failed=true; return ;}
                        EarlyExitException eee =
                            new EarlyExitException(15, input);
                        throw eee;
                }
                cnt15++;
            } while (true);


            }

        }

            catch (RecognitionException e) {
                throw e;
            }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "creatures"



    // $ANTLR start "creature"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:423:1: creature : ID 'ias' string ;
    public final void creature() throws RecognitionException {
        Token ID12=null;
        VirtualWorldModelingLanguageParser.string_return string13 =null;


        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:435:5: ( ID 'ias' string )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:435:7: ID 'ias' string
            {
            ID12=(Token)match(input,ID,FOLLOW_ID_in_creature1096); if (state.failed) return ;

            if ( state.backtracking==0 ) {
                		addLastDeclaredCreature(ID12.getText());
                	 }

            match(input,IAS,FOLLOW_IAS_in_creature1100); if (state.failed) return ;

            pushFollow(FOLLOW_string_in_creature1102);
            string13=string();

            state._fsp--;
            if (state.failed) return ;

            if ( state.backtracking==0 ) {
                	 			addLastDeclaredCreatureProps(GeneralUtils.trimQuotes((string13!=null?input.toString(string13.start,string13.stop):null)));
                	 		}

            }

            if ( state.backtracking==0 ) {
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
        }

            catch (RecognitionException e) {
                throw e;
            }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "creature"



    // $ANTLR start "module"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:442:1: module : 'module' ID body ;
    public final void module() throws RecognitionException {
        Token ID14=null;

        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:443:5: ( 'module' ID body )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:443:7: 'module' ID body
            {
            match(input,63,FOLLOW_63_in_module1122); if (state.failed) return ;

            ID14=(Token)match(input,ID,FOLLOW_ID_in_module1124); if (state.failed) return ;

            if ( state.backtracking==0 ) { 
                			modName = ID14.getText();
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
                              }

            pushFollow(FOLLOW_body_in_module1128);
            body();

            state._fsp--;
            if (state.failed) return ;

            }

        }

            catch (RecognitionException e) {
                throw e;
            }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "module"



    // $ANTLR start "body"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:471:1: body : '{' ( expression ( expression )* )? '}' ;
    public final void body() throws RecognitionException {
        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:472:4: ( '{' ( expression ( expression )* )? '}' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:472:6: '{' ( expression ( expression )* )? '}'
            {
            match(input,69,FOLLOW_69_in_body1148); if (state.failed) return ;

            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:472:10: ( expression ( expression )* )?
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==ID||LA17_0==LIFETERM||LA17_0==49||LA17_0==52) ) {
                alt17=1;
            }
            switch (alt17) {
                case 1 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:472:11: expression ( expression )*
                    {
                    pushFollow(FOLLOW_expression_in_body1151);
                    expression();

                    state._fsp--;
                    if (state.failed) return ;

                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:472:22: ( expression )*
                    loop16:
                    do {
                        int alt16=2;
                        int LA16_0 = input.LA(1);

                        if ( (LA16_0==ID||LA16_0==LIFETERM||LA16_0==49||LA16_0==52) ) {
                            alt16=1;
                        }


                        switch (alt16) {
                    	case 1 :
                    	    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:472:23: expression
                    	    {
                    	    pushFollow(FOLLOW_expression_in_body1154);
                    	    expression();

                    	    state._fsp--;
                    	    if (state.failed) return ;

                    	    }
                    	    break;

                    	default :
                    	    break loop16;
                        }
                    } while (true);


                    }
                    break;

            }


            match(input,70,FOLLOW_70_in_body1160); if (state.failed) return ;

            }

        }

            catch (RecognitionException e) {
                throw e;
            }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "body"



    // $ANTLR start "expression"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:476:1: expression : ( ( entity_decl IAS )=> entity_def | check_term_def );
    public final void expression() throws RecognitionException {
        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:477:5: ( ( entity_decl IAS )=> entity_def | check_term_def )
            int alt18=2;
            switch ( input.LA(1) ) {
            case ID:
                {
                int LA18_1 = input.LA(2);

                if ( (synpred2_VirtualWorldModelingLanguage()) ) {
                    alt18=1;
                }
                else if ( (true) ) {
                    alt18=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return ;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 18, 1, input);

                    throw nvae;

                }
                }
                break;
            case 49:
                {
                int LA18_2 = input.LA(2);

                if ( (synpred2_VirtualWorldModelingLanguage()) ) {
                    alt18=1;
                }
                else if ( (true) ) {
                    alt18=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return ;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 18, 2, input);

                    throw nvae;

                }
                }
                break;
            case LIFETERM:
            case 52:
                {
                alt18=2;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 18, 0, input);

                throw nvae;

            }

            switch (alt18) {
                case 1 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:477:7: ( entity_decl IAS )=> entity_def
                    {
                    pushFollow(FOLLOW_entity_def_in_expression1186);
                    entity_def();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:478:7: check_term_def
                    {
                    pushFollow(FOLLOW_check_term_def_in_expression1194);
                    check_term_def();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }
        }

            catch (RecognitionException e) {
                throw e;
            }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "expression"



    // $ANTLR start "entity_def"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:481:1: entity_def : entity_decl IAS term ( SEMICOLON )? ;
    public final void entity_def() throws RecognitionException {
        String entity_decl15 =null;


        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:482:5: ( entity_decl IAS term ( SEMICOLON )? )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:482:7: entity_decl IAS term ( SEMICOLON )?
            {
            pushFollow(FOLLOW_entity_decl_in_entity_def1211);
            entity_decl15=entity_decl();

            state._fsp--;
            if (state.failed) return ;

            match(input,IAS,FOLLOW_IAS_in_entity_def1213); if (state.failed) return ;

            if ( state.backtracking==0 ) {
                			// adds entity id to context stack
                			vwmlContextBuilder.push(entity_decl15);
                			entityWalker.markFutureEntityAsIAS(entity_decl15);
                			if (codeGenerator != null) {
                				codeGenerator.declareContext(vwmlContextBuilder.buildContext());
                			}
                			// we should link this entity with parent, if exists
                			// buildLinkingAssociation(entity_decl15);
                		      }

            pushFollow(FOLLOW_term_in_entity_def1217);
            term();

            state._fsp--;
            if (state.failed) return ;

            if ( state.backtracking==0 ) {
                		      	// removes top entity from stack
                		      	vwmlContextBuilder.pop();
                		      }

            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:496:13: ( SEMICOLON )?
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( (LA19_0==SEMICOLON) ) {
                alt19=1;
            }
            switch (alt19) {
                case 1 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:496:14: SEMICOLON
                    {
                    match(input,SEMICOLON,FOLLOW_SEMICOLON_in_entity_def1247); if (state.failed) return ;

                    }
                    break;

            }


            }

        }

            catch (RecognitionException e) {
                throw e;
            }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "entity_def"



    // $ANTLR start "check_term_def"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:499:1: check_term_def : ( LIFETERM '=' lifeterm_def | term_def );
    public final void check_term_def() throws RecognitionException {
        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:500:5: ( LIFETERM '=' lifeterm_def | term_def )
            int alt20=2;
            int LA20_0 = input.LA(1);

            if ( (LA20_0==LIFETERM) ) {
                alt20=1;
            }
            else if ( (LA20_0==ID||LA20_0==49||LA20_0==52) ) {
                alt20=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 20, 0, input);

                throw nvae;

            }
            switch (alt20) {
                case 1 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:500:7: LIFETERM '=' lifeterm_def
                    {
                    match(input,LIFETERM,FOLLOW_LIFETERM_in_check_term_def1266); if (state.failed) return ;

                    match(input,51,FOLLOW_51_in_check_term_def1268); if (state.failed) return ;

                    pushFollow(FOLLOW_lifeterm_def_in_check_term_def1270);
                    lifeterm_def();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:501:7: term_def
                    {
                    pushFollow(FOLLOW_term_def_in_check_term_def1278);
                    term_def();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }
        }

            catch (RecognitionException e) {
                throw e;
            }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "check_term_def"



    // $ANTLR start "lifeterm_def"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:504:1: lifeterm_def : term_def ;
    public final void lifeterm_def() throws RecognitionException {
        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:505:5: ( term_def )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:505:8: term_def
            {
            pushFollow(FOLLOW_term_def_in_lifeterm_def1300);
            term_def();

            state._fsp--;
            if (state.failed) return ;

            if ( state.backtracking==0 ) {
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

            }

        }

            catch (RecognitionException e) {
                throw e;
            }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "lifeterm_def"



    // $ANTLR start "term_def"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:523:1: term_def : entity ( oplist )* ;
    public final void term_def() throws RecognitionException {
        EntityWalker.Relation entity16 =null;


        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:524:5: ( entity ( oplist )* )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:524:7: entity ( oplist )*
            {
            pushFollow(FOLLOW_entity_in_term_def1319);
            entity16=entity();

            state._fsp--;
            if (state.failed) return ;

            if ( state.backtracking==0 ) {
                		lastProcessedEntity = entity16;
                		lastProcessedEntityAsTerm = false;
                	     }

            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:527:13: ( oplist )*
            loop21:
            do {
                int alt21=2;
                int LA21_0 = input.LA(1);

                if ( ((LA21_0 >= OPACTIVATECTX && LA21_0 <= OPSUBSTRUCT)) ) {
                    alt21=1;
                }


                switch (alt21) {
            	case 1 :
            	    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:527:14: oplist
            	    {
            	    pushFollow(FOLLOW_oplist_in_term_def1324);
            	    oplist();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop21;
                }
            } while (true);


            if ( state.backtracking==0 ) {
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

            }

        }

            catch (RecognitionException e) {
                throw e;
            }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "term_def"



    // $ANTLR start "entity_decl"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:543:1: entity_decl returns [String id] : ( simple_entity_decl | '(' complex_entity_decl ')' );
    public final String entity_decl() throws RecognitionException {
        String id = null;


        String simple_entity_decl17 =null;


        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:544:5: ( simple_entity_decl | '(' complex_entity_decl ')' )
            int alt22=2;
            int LA22_0 = input.LA(1);

            if ( (LA22_0==ID) ) {
                alt22=1;
            }
            else if ( (LA22_0==49) ) {
                alt22=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return id;}
                NoViableAltException nvae =
                    new NoViableAltException("", 22, 0, input);

                throw nvae;

            }
            switch (alt22) {
                case 1 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:544:7: simple_entity_decl
                    {
                    pushFollow(FOLLOW_simple_entity_decl_in_entity_decl1358);
                    simple_entity_decl17=simple_entity_decl();

                    state._fsp--;
                    if (state.failed) return id;

                    if ( state.backtracking==0 ) {id = simple_entity_decl17;}

                    }
                    break;
                case 2 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:545:7: '(' complex_entity_decl ')'
                    {
                    match(input,49,FOLLOW_49_in_entity_decl1369); if (state.failed) return id;

                    if ( state.backtracking==0 ) {
                        	    	complexEntityNameBuilderDecl.startProgress();
                    	    	if (logger.isDebugEnabled()) {
                    	    		logger.debug("complex entity declaration process - started");
                    	    	}
                              }

                    pushFollow(FOLLOW_complex_entity_decl_in_entity_decl1374);
                    complex_entity_decl();

                    state._fsp--;
                    if (state.failed) return id;

                    match(input,50,FOLLOW_50_in_entity_decl1376); if (state.failed) return id;

                    if ( state.backtracking==0 ) {
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

                    }
                    break;

            }
        }

            catch (RecognitionException e) {
                throw e;
            }

        finally {
        	// do for sure before leaving
        }
        return id;
    }
    // $ANTLR end "entity_decl"



    // $ANTLR start "compound_entity_decl"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:569:1: compound_entity_decl : entity_decl ;
    public final void compound_entity_decl() throws RecognitionException {
        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:570:5: ( entity_decl )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:570:7: entity_decl
            {
            pushFollow(FOLLOW_entity_decl_in_compound_entity_decl1395);
            entity_decl();

            state._fsp--;
            if (state.failed) return ;

            }

        }

            catch (RecognitionException e) {
                throw e;
            }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "compound_entity_decl"



    // $ANTLR start "simple_entity_decl"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:573:1: simple_entity_decl returns [String id] : ID ;
    public final String simple_entity_decl() throws RecognitionException {
        String id = null;


        Token ID18=null;

        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:574:5: ( ID )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:574:7: ID
            {
            ID18=(Token)match(input,ID,FOLLOW_ID_in_simple_entity_decl1420); if (state.failed) return id;

            if ( state.backtracking==0 ) {
                		id = ID18.getText();
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

            }

        }

            catch (RecognitionException e) {
                throw e;
            }

        finally {
        	// do for sure before leaving
        }
        return id;
    }
    // $ANTLR end "simple_entity_decl"



    // $ANTLR start "complex_entity_decl"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:600:1: complex_entity_decl : ( compound_entity_decl )+ ;
    public final void complex_entity_decl() throws RecognitionException {
        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:604:5: ( ( compound_entity_decl )+ )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:604:7: ( compound_entity_decl )+
            {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:604:7: ( compound_entity_decl )+
            int cnt23=0;
            loop23:
            do {
                int alt23=2;
                int LA23_0 = input.LA(1);

                if ( (LA23_0==ID||LA23_0==49) ) {
                    alt23=1;
                }


                switch (alt23) {
            	case 1 :
            	    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:604:8: compound_entity_decl
            	    {
            	    pushFollow(FOLLOW_compound_entity_decl_in_complex_entity_decl1453);
            	    compound_entity_decl();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    if ( cnt23 >= 1 ) break loop23;
            	    if (state.backtracking>0) {state.failed=true; return ;}
                        EarlyExitException eee =
                            new EarlyExitException(23, input);
                        throw eee;
                }
                cnt23++;
            } while (true);


            }

            if ( state.backtracking==0 ) {
                	complexEntityNameBuilderDecl.stopProgress();
                }
        }

            catch (RecognitionException e) {
                throw e;
            }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "complex_entity_decl"



    // $ANTLR start "term"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:607:1: term : expression ;
    public final void term() throws RecognitionException {
        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:608:5: ( expression )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:608:7: expression
            {
            pushFollow(FOLLOW_expression_in_term1472);
            expression();

            state._fsp--;
            if (state.failed) return ;

            }

        }

            catch (RecognitionException e) {
                throw e;
            }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "term"



    // $ANTLR start "entity"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:611:1: entity returns [EntityWalker.Relation rel] : ( simple_entity | complex_entity | syncronization_entity );
    public final EntityWalker.Relation entity() throws RecognitionException {
        EntityWalker.Relation rel = null;


        EntityWalker.Relation simple_entity19 =null;

        EntityWalker.Relation complex_entity20 =null;

        EntityWalker.Relation syncronization_entity21 =null;


        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:612:5: ( simple_entity | complex_entity | syncronization_entity )
            int alt24=3;
            switch ( input.LA(1) ) {
            case ID:
                {
                alt24=1;
                }
                break;
            case 49:
                {
                alt24=2;
                }
                break;
            case 52:
                {
                alt24=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return rel;}
                NoViableAltException nvae =
                    new NoViableAltException("", 24, 0, input);

                throw nvae;

            }

            switch (alt24) {
                case 1 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:612:7: simple_entity
                    {
                    pushFollow(FOLLOW_simple_entity_in_entity1495);
                    simple_entity19=simple_entity();

                    state._fsp--;
                    if (state.failed) return rel;

                    if ( state.backtracking==0 ) {rel = simple_entity19; }

                    }
                    break;
                case 2 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:613:7: complex_entity
                    {
                    pushFollow(FOLLOW_complex_entity_in_entity1513);
                    complex_entity20=complex_entity();

                    state._fsp--;
                    if (state.failed) return rel;

                    if ( state.backtracking==0 ) {rel = complex_entity20;}

                    }
                    break;
                case 3 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:614:7: syncronization_entity
                    {
                    pushFollow(FOLLOW_syncronization_entity_in_entity1530);
                    syncronization_entity21=syncronization_entity();

                    state._fsp--;
                    if (state.failed) return rel;

                    if ( state.backtracking==0 ) {rel = syncronization_entity21;}

                    }
                    break;

            }
        }

            catch (RecognitionException e) {
                throw e;
            }

        finally {
        	// do for sure before leaving
        }
        return rel;
    }
    // $ANTLR end "entity"



    // $ANTLR start "syncronization_entity"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:618:1: syncronization_entity returns [EntityWalker.Relation rel] : '[' entity ']' ;
    public final EntityWalker.Relation syncronization_entity() throws RecognitionException {
        EntityWalker.Relation rel = null;


        EntityWalker.Relation entity22 =null;


        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:619:5: ( '[' entity ']' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:619:7: '[' entity ']'
            {
            match(input,52,FOLLOW_52_in_syncronization_entity1554); if (state.failed) return rel;

            pushFollow(FOLLOW_entity_in_syncronization_entity1556);
            entity22=entity();

            state._fsp--;
            if (state.failed) return rel;

            if ( state.backtracking==0 ) {rel = entity22; }

            match(input,53,FOLLOW_53_in_syncronization_entity1560); if (state.failed) return rel;

            }

        }

            catch (RecognitionException e) {
                throw e;
            }

        finally {
        	// do for sure before leaving
        }
        return rel;
    }
    // $ANTLR end "syncronization_entity"



    // $ANTLR start "simple_entity"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:622:1: simple_entity returns [EntityWalker.Relation rel] : ID ;
    public final EntityWalker.Relation simple_entity() throws RecognitionException {
        EntityWalker.Relation rel = null;


        Token ID23=null;

        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:623:5: ( ID )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:623:7: ID
            {
            ID23=(Token)match(input,ID,FOLLOW_ID_in_simple_entity1582); if (state.failed) return rel;

            if ( state.backtracking==0 ) {
                		// since entity's id may include '.' we should check correctness of name
                		// if name ends with '.' we can suppouse that this name can be considered as complex entity's effective context
                		if (!vwmlContextBuilder.isEffectiveContext((ID23!=null?ID23.getText():null))) {
                			if (complexEntityNameBuilderDef.isInProgress()) {
                				complexEntityNameBuilderDef.addObjectId((ID23!=null?ID23.getText():null));
                			}
               			rel = buildRelation((ID23!=null?ID23.getText():null));
                			if (logger.isDebugEnabled()) {
                				logger.debug("processed simple entity '" + rel + "'");
                			}
                		}
                		else {
                			// so entity is considered as effective context
                			vwmlContextBuilder.setEffectiveContext((ID23!=null?ID23.getText():null));
                 			if (logger.isDebugEnabled()) {
                				logger.debug("effective context detected '" + vwmlContextBuilder.getEffectiveContext() + "'");
                			}   			
                		}
                     }

            }

        }

            catch (RecognitionException e) {
                throw e;
            }

        finally {
        	// do for sure before leaving
        }
        return rel;
    }
    // $ANTLR end "simple_entity"



    // $ANTLR start "complex_entity"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:645:1: complex_entity returns [EntityWalker.Relation rel] : '(' ( term )* ')' ;
    public final EntityWalker.Relation complex_entity() throws RecognitionException {
        EntityWalker.Relation rel = null;



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
            
        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:688:5: ( '(' ( term )* ')' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:688:7: '(' ( term )* ')'
            {
            match(input,49,FOLLOW_49_in_complex_entity1623); if (state.failed) return rel;

            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:688:11: ( term )*
            loop25:
            do {
                int alt25=2;
                int LA25_0 = input.LA(1);

                if ( (LA25_0==ID||LA25_0==LIFETERM||LA25_0==49||LA25_0==52) ) {
                    alt25=1;
                }


                switch (alt25) {
            	case 1 :
            	    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:688:12: term
            	    {
            	    pushFollow(FOLLOW_term_in_complex_entity1626);
            	    term();

            	    state._fsp--;
            	    if (state.failed) return rel;

            	    }
            	    break;

            	default :
            	    break loop25;
                }
            } while (true);


            match(input,50,FOLLOW_50_in_complex_entity1630); if (state.failed) return rel;

            }

            if ( state.backtracking==0 ) {
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
        }

            catch (RecognitionException e) {
                throw e;
            }

        finally {
        	// do for sure before leaving
        }
        return rel;
    }
    // $ANTLR end "complex_entity"



    // $ANTLR start "oplist"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:699:1: oplist : ( opclist | opprojection );
    public final void oplist() throws RecognitionException {
        VirtualWorldModelingLanguageParser.opclist_return opclist24 =null;

        VirtualWorldModelingLanguageParser.opprojection_return opprojection25 =null;


        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:701:5: ( opclist | opprojection )
            int alt26=2;
            int LA26_0 = input.LA(1);

            if ( ((LA26_0 >= OPACTIVATECTX && LA26_0 <= OPLAST)||(LA26_0 >= OPRANDOM && LA26_0 <= OPSUBSTRUCT)) ) {
                alt26=1;
            }
            else if ( ((LA26_0 >= OPPROJECTION_1 && LA26_0 <= OPPROJECTION_9)) ) {
                alt26=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 26, 0, input);

                throw nvae;

            }
            switch (alt26) {
                case 1 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:701:7: opclist
                    {
                    pushFollow(FOLLOW_opclist_in_oplist1713);
                    opclist24=opclist();

                    state._fsp--;
                    if (state.failed) return ;

                    if ( state.backtracking==0 ) {
                        			if (lastProcessedEntity != null && codeGenerator != null) { 
                        				lastProcessedEntityAsTerm = true;
                        				codeGenerator.associateOperation(lastProcessedEntity, (opclist24!=null?input.toString(opclist24.start,opclist24.stop):null), vwmlContextBuilder.buildContext());
                        			} 
                        		    }

                    }
                    break;
                case 2 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:707:7: opprojection
                    {
                    pushFollow(FOLLOW_opprojection_in_oplist1729);
                    opprojection25=opprojection();

                    state._fsp--;
                    if (state.failed) return ;

                    if ( state.backtracking==0 ) {
                        			if (lastProcessedEntity != null && codeGenerator != null) {
                        				lastProcessedEntityAsTerm = true;
                        				codeGenerator.associateOperation(lastProcessedEntity, (opprojection25!=null?input.toString(opprojection25.start,opprojection25.stop):null), vwmlContextBuilder.buildContext());
                        			}
                        		    }

                    }
                    break;

            }
        }

            catch (RecognitionException e) {
                throw e;
            }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "oplist"


    public static class opclist_return extends ParserRuleReturnScope {
    };


    // $ANTLR start "opclist"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:715:1: opclist : ( OPJOIN | OPINTERSECT | OPSUBSTRUCT | OPFIRST | OPLAST | OPBEGIN | OPREST | OPCARTESIAN | OPIN | OPINCL | OPEQ | OPIDENT | OPSQU | OPINTERPRET | OPCREATEEXPR | OPEXECUTE | OPRANDOM | OPACTIVATECTX | OPACTIVATEONFRINGE | OPRELAX );
    public final VirtualWorldModelingLanguageParser.opclist_return opclist() throws RecognitionException {
        VirtualWorldModelingLanguageParser.opclist_return retval = new VirtualWorldModelingLanguageParser.opclist_return();
        retval.start = input.LT(1);


        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:716:5: ( OPJOIN | OPINTERSECT | OPSUBSTRUCT | OPFIRST | OPLAST | OPBEGIN | OPREST | OPCARTESIAN | OPIN | OPINCL | OPEQ | OPIDENT | OPSQU | OPINTERPRET | OPCREATEEXPR | OPEXECUTE | OPRANDOM | OPACTIVATECTX | OPACTIVATEONFRINGE | OPRELAX )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:
            {
            if ( (input.LA(1) >= OPACTIVATECTX && input.LA(1) <= OPLAST)||(input.LA(1) >= OPRANDOM && input.LA(1) <= OPSUBSTRUCT) ) {
                input.consume();
                state.errorRecovery=false;
                state.failed=false;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            }

            retval.stop = input.LT(-1);


        }

            catch (RecognitionException e) {
                throw e;
            }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "opclist"


    public static class opprojection_return extends ParserRuleReturnScope {
    };


    // $ANTLR start "opprojection"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:738:1: opprojection : ( OPPROJECTION_1 | OPPROJECTION_2 | OPPROJECTION_3 | OPPROJECTION_4 | OPPROJECTION_5 | OPPROJECTION_6 | OPPROJECTION_7 | OPPROJECTION_8 | OPPROJECTION_9 );
    public final VirtualWorldModelingLanguageParser.opprojection_return opprojection() throws RecognitionException {
        VirtualWorldModelingLanguageParser.opprojection_return retval = new VirtualWorldModelingLanguageParser.opprojection_return();
        retval.start = input.LT(1);


        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:739:5: ( OPPROJECTION_1 | OPPROJECTION_2 | OPPROJECTION_3 | OPPROJECTION_4 | OPPROJECTION_5 | OPPROJECTION_6 | OPPROJECTION_7 | OPPROJECTION_8 | OPPROJECTION_9 )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:
            {
            if ( (input.LA(1) >= OPPROJECTION_1 && input.LA(1) <= OPPROJECTION_9) ) {
                input.consume();
                state.errorRecovery=false;
                state.failed=false;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            }

            retval.stop = input.LT(-1);


        }

            catch (RecognitionException e) {
                throw e;
            }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "opprojection"



    // $ANTLR start "termLanguages"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:750:1: termLanguages : ( JAVA | C | CPP | OBJECTIVEC );
    public final void termLanguages() throws RecognitionException {
        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:751:5: ( JAVA | C | CPP | OBJECTIVEC )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:
            {
            if ( input.LA(1)==C||input.LA(1)==CPP||input.LA(1)==JAVA||input.LA(1)==OBJECTIVEC ) {
                input.consume();
                state.errorRecovery=false;
                state.failed=false;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            }

        }

            catch (RecognitionException e) {
                throw e;
            }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "termLanguages"


    public static class string_return extends ParserRuleReturnScope {
    };


    // $ANTLR start "string"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:757:1: string : STRING_LITERAL ;
    public final VirtualWorldModelingLanguageParser.string_return string() throws RecognitionException {
        VirtualWorldModelingLanguageParser.string_return retval = new VirtualWorldModelingLanguageParser.string_return();
        retval.start = input.LT(1);


        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:758:5: ( STRING_LITERAL )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:758:7: STRING_LITERAL
            {
            match(input,STRING_LITERAL,FOLLOW_STRING_LITERAL_in_string2040); if (state.failed) return retval;

            }

            retval.stop = input.LT(-1);


        }

            catch (RecognitionException e) {
                throw e;
            }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "string"

    // $ANTLR start synpred1_VirtualWorldModelingLanguage
    public final void synpred1_VirtualWorldModelingLanguage_fragment() throws RecognitionException {
        // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:283:7: ( 'language' '=' JAVA )
        // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:283:8: 'language' '=' JAVA
        {
        match(input,62,FOLLOW_62_in_synpred1_VirtualWorldModelingLanguage514); if (state.failed) return ;

        match(input,51,FOLLOW_51_in_synpred1_VirtualWorldModelingLanguage516); if (state.failed) return ;

        match(input,JAVA,FOLLOW_JAVA_in_synpred1_VirtualWorldModelingLanguage518); if (state.failed) return ;

        }

    }
    // $ANTLR end synpred1_VirtualWorldModelingLanguage

    // $ANTLR start synpred2_VirtualWorldModelingLanguage
    public final void synpred2_VirtualWorldModelingLanguage_fragment() throws RecognitionException {
        // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:477:7: ( entity_decl IAS )
        // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:477:8: entity_decl IAS
        {
        pushFollow(FOLLOW_entity_decl_in_synpred2_VirtualWorldModelingLanguage1179);
        entity_decl();

        state._fsp--;
        if (state.failed) return ;

        match(input,IAS,FOLLOW_IAS_in_synpred2_VirtualWorldModelingLanguage1181); if (state.failed) return ;

        }

    }
    // $ANTLR end synpred2_VirtualWorldModelingLanguage

    // Delegated rules

    public final boolean synpred1_VirtualWorldModelingLanguage() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred1_VirtualWorldModelingLanguage_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred2_VirtualWorldModelingLanguage() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred2_VirtualWorldModelingLanguage_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }


 

    public static final BitSet FOLLOW_props_in_filedef383 = new BitSet(new long[]{0xA000000000000000L});
    public static final BitSet FOLLOW_include_in_filedef387 = new BitSet(new long[]{0xA000000000000000L});
    public static final BitSet FOLLOW_include_in_filedef390 = new BitSet(new long[]{0xA000000000000000L});
    public static final BitSet FOLLOW_module_in_filedef396 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_filedef399 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_include_vwml_in_include420 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_61_in_include_vwml448 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_STRING_LITERAL_in_include_vwml450 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_64_in_props469 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_69_in_props471 = new BitSet(new long[]{0x4000000000000000L});
    public static final BitSet FOLLOW_optionsList_in_props473 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_70_in_props475 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_lang_in_optionsList496 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_langJava_in_lang523 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_otherLanguages_in_lang531 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_62_in_langJava574 = new BitSet(new long[]{0x0008000000000000L});
    public static final BitSet FOLLOW_51_in_langJava576 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_JAVA_in_langJava578 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_69_in_langJava580 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
    public static final BitSet FOLLOW_javaProps_in_langJava582 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_70_in_langJava584 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_propPackage_in_javaProps610 = new BitSet(new long[]{0x0CC0000000000000L,0x000000000000001CL});
    public static final BitSet FOLLOW_generatedFileLocation_in_javaProps612 = new BitSet(new long[]{0x0CC0000000000000L,0x0000000000000018L});
    public static final BitSet FOLLOW_optionalProps_in_javaProps615 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_65_in_propPackage637 = new BitSet(new long[]{0x0008000000000000L});
    public static final BitSet FOLLOW_51_in_propPackage639 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_packageName_in_propPackage641 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_LITERAL_in_packageName660 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_66_in_generatedFileLocation677 = new BitSet(new long[]{0x0008000000000000L});
    public static final BitSet FOLLOW_51_in_generatedFileLocation679 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_path_in_generatedFileLocation681 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_author_in_optionalProps701 = new BitSet(new long[]{0x0C80000000000002L,0x0000000000000018L});
    public static final BitSet FOLLOW_projname_in_optionalProps704 = new BitSet(new long[]{0x0C80000000000002L,0x0000000000000010L});
    public static final BitSet FOLLOW_description_in_optionalProps707 = new BitSet(new long[]{0x0880000000000002L,0x0000000000000010L});
    public static final BitSet FOLLOW_entity_history_size_in_optionalProps710 = new BitSet(new long[]{0x0080000000000002L,0x0000000000000010L});
    public static final BitSet FOLLOW_visualizer_in_optionalProps713 = new BitSet(new long[]{0x0080000000000002L});
    public static final BitSet FOLLOW_beyond_the_fringe_in_optionalProps716 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_54_in_author734 = new BitSet(new long[]{0x0008000000000000L});
    public static final BitSet FOLLOW_51_in_author736 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_string_in_author738 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_67_in_projname757 = new BitSet(new long[]{0x0008000000000000L});
    public static final BitSet FOLLOW_51_in_projname759 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_string_in_projname761 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_58_in_description784 = new BitSet(new long[]{0x0008000000000000L});
    public static final BitSet FOLLOW_51_in_description786 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_string_in_description788 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_59_in_entity_history_size807 = new BitSet(new long[]{0x0008000000000000L});
    public static final BitSet FOLLOW_51_in_entity_history_size809 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_string_in_entity_history_size811 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_68_in_visualizer831 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_69_in_visualizer833 = new BitSet(new long[]{0x0100000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_visualizer_body_in_visualizer835 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_70_in_visualizer837 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_visualizer_class_in_visualizer_body855 = new BitSet(new long[]{0x0200000000000000L});
    public static final BitSet FOLLOW_visualizer_datapath_in_visualizer_body857 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_56_in_visualizer_class880 = new BitSet(new long[]{0x0008000000000000L});
    public static final BitSet FOLLOW_51_in_visualizer_class882 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_string_in_visualizer_class884 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_57_in_visualizer_datapath907 = new BitSet(new long[]{0x0008000000000000L});
    public static final BitSet FOLLOW_51_in_visualizer_datapath909 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_string_in_visualizer_datapath911 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_LITERAL_in_path934 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_55_in_beyond_the_fringe952 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_69_in_beyond_the_fringe954 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_beyond_the_fringe_body_in_beyond_the_fringe956 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_70_in_beyond_the_fringe958 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_finges_in_beyond_the_fringe_body980 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_fringe_in_finges999 = new BitSet(new long[]{0x1000000000000002L});
    public static final BitSet FOLLOW_60_in_fringe1019 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_ID_in_fringe1021 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_IAS_in_fringe1023 = new BitSet(new long[]{0x0002000000000000L});
    public static final BitSet FOLLOW_49_in_fringe1046 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_creatures_in_fringe1048 = new BitSet(new long[]{0x0004000000000000L});
    public static final BitSet FOLLOW_50_in_fringe1050 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_creature_in_creatures1068 = new BitSet(new long[]{0x0000000000000402L});
    public static final BitSet FOLLOW_ID_in_creature1096 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_IAS_in_creature1100 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_string_in_creature1102 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_63_in_module1122 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_ID_in_module1124 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_body_in_module1128 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_69_in_body1148 = new BitSet(new long[]{0x0012000000002400L,0x0000000000000040L});
    public static final BitSet FOLLOW_expression_in_body1151 = new BitSet(new long[]{0x0012000000002400L,0x0000000000000040L});
    public static final BitSet FOLLOW_expression_in_body1154 = new BitSet(new long[]{0x0012000000002400L,0x0000000000000040L});
    public static final BitSet FOLLOW_70_in_body1160 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_entity_def_in_expression1186 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_check_term_def_in_expression1194 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_entity_decl_in_entity_def1211 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_IAS_in_entity_def1213 = new BitSet(new long[]{0x0012000000002400L});
    public static final BitSet FOLLOW_term_in_entity_def1217 = new BitSet(new long[]{0x0000400000000002L});
    public static final BitSet FOLLOW_SEMICOLON_in_entity_def1247 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LIFETERM_in_check_term_def1266 = new BitSet(new long[]{0x0008000000000000L});
    public static final BitSet FOLLOW_51_in_check_term_def1268 = new BitSet(new long[]{0x0012000000000400L});
    public static final BitSet FOLLOW_lifeterm_def_in_check_term_def1270 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_term_def_in_check_term_def1278 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_term_def_in_lifeterm_def1300 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_entity_in_term_def1319 = new BitSet(new long[]{0x00003FFFFFFE0002L});
    public static final BitSet FOLLOW_oplist_in_term_def1324 = new BitSet(new long[]{0x00003FFFFFFE0002L});
    public static final BitSet FOLLOW_simple_entity_decl_in_entity_decl1358 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_49_in_entity_decl1369 = new BitSet(new long[]{0x0002000000000400L});
    public static final BitSet FOLLOW_complex_entity_decl_in_entity_decl1374 = new BitSet(new long[]{0x0004000000000000L});
    public static final BitSet FOLLOW_50_in_entity_decl1376 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_entity_decl_in_compound_entity_decl1395 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_simple_entity_decl1420 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_compound_entity_decl_in_complex_entity_decl1453 = new BitSet(new long[]{0x0002000000000402L});
    public static final BitSet FOLLOW_expression_in_term1472 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simple_entity_in_entity1495 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_complex_entity_in_entity1513 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_syncronization_entity_in_entity1530 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_52_in_syncronization_entity1554 = new BitSet(new long[]{0x0012000000000400L});
    public static final BitSet FOLLOW_entity_in_syncronization_entity1556 = new BitSet(new long[]{0x0020000000000000L});
    public static final BitSet FOLLOW_53_in_syncronization_entity1560 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_simple_entity1582 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_49_in_complex_entity1623 = new BitSet(new long[]{0x0016000000002400L});
    public static final BitSet FOLLOW_term_in_complex_entity1626 = new BitSet(new long[]{0x0016000000002400L});
    public static final BitSet FOLLOW_50_in_complex_entity1630 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_opclist_in_oplist1713 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_opprojection_in_oplist1729 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_LITERAL_in_string2040 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_62_in_synpred1_VirtualWorldModelingLanguage514 = new BitSet(new long[]{0x0008000000000000L});
    public static final BitSet FOLLOW_51_in_synpred1_VirtualWorldModelingLanguage516 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_JAVA_in_synpred1_VirtualWorldModelingLanguage518 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_entity_decl_in_synpred2_VirtualWorldModelingLanguage1179 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_IAS_in_synpred2_VirtualWorldModelingLanguage1181 = new BitSet(new long[]{0x0000000000000002L});

}