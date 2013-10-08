// $ANTLR 3.4 C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g 2013-10-07 11:56:14

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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "C", "COMMA", "COMMENT", "CPP", "DQUOTE", "IAS", "ID", "JAVA", "LETTER", "LIFETERM", "LINE_COMMENT", "NATIVE_CODE", "OBJECTIVEC", "OPBEGIN", "OPCARTESIAN", "OPCREATEEXPR", "OPEQ", "OPEXECUTE", "OPFIRST", "OPIDENT", "OPIN", "OPINCL", "OPINTERPRET", "OPINTERSECT", "OPJOIN", "OPLAST", "OPPROJECTION_1", "OPPROJECTION_2", "OPPROJECTION_3", "OPPROJECTION_4", "OPPROJECTION_5", "OPPROJECTION_6", "OPPROJECTION_7", "OPPROJECTION_8", "OPPROJECTION_9", "OPRANDOM", "OPREST", "OPSQU", "OPSUBSTRUCT", "SEMICOLON", "STRING_LITERAL", "WS", "'('", "')'", "'='", "'['", "']'", "'author'", "'class'", "'data'", "'description'", "'entity_history_size'", "'fringe'", "'include'", "'is'", "'language'", "'module'", "'options'", "'package'", "'path'", "'project_name'", "'visualizer'", "'{'", "'}'"
    };

    public static final int EOF=-1;
    public static final int T__46=46;
    public static final int T__47=47;
    public static final int T__48=48;
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
    public static final int OPBEGIN=17;
    public static final int OPCARTESIAN=18;
    public static final int OPCREATEEXPR=19;
    public static final int OPEQ=20;
    public static final int OPEXECUTE=21;
    public static final int OPFIRST=22;
    public static final int OPIDENT=23;
    public static final int OPIN=24;
    public static final int OPINCL=25;
    public static final int OPINTERPRET=26;
    public static final int OPINTERSECT=27;
    public static final int OPJOIN=28;
    public static final int OPLAST=29;
    public static final int OPPROJECTION_1=30;
    public static final int OPPROJECTION_2=31;
    public static final int OPPROJECTION_3=32;
    public static final int OPPROJECTION_4=33;
    public static final int OPPROJECTION_5=34;
    public static final int OPPROJECTION_6=35;
    public static final int OPPROJECTION_7=36;
    public static final int OPPROJECTION_8=37;
    public static final int OPPROJECTION_9=38;
    public static final int OPRANDOM=39;
    public static final int OPREST=40;
    public static final int OPSQU=41;
    public static final int OPSUBSTRUCT=42;
    public static final int SEMICOLON=43;
    public static final int STRING_LITERAL=44;
    public static final int WS=45;

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
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:227:1: filedef : ( props )? ( include ( include )* )? ( module )? EOF ;
    public final void filedef() throws RecognitionException {
        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:228:5: ( ( props )? ( include ( include )* )? ( module )? EOF )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:228:7: ( props )? ( include ( include )* )? ( module )? EOF
            {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:228:7: ( props )?
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0==61) ) {
                alt1=1;
            }
            switch (alt1) {
                case 1 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:228:7: props
                    {
                    pushFollow(FOLLOW_props_in_filedef356);
                    props();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }


            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:228:14: ( include ( include )* )?
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==57) ) {
                alt3=1;
            }
            switch (alt3) {
                case 1 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:228:15: include ( include )*
                    {
                    pushFollow(FOLLOW_include_in_filedef360);
                    include();

                    state._fsp--;
                    if (state.failed) return ;

                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:228:23: ( include )*
                    loop2:
                    do {
                        int alt2=2;
                        int LA2_0 = input.LA(1);

                        if ( (LA2_0==57) ) {
                            alt2=1;
                        }


                        switch (alt2) {
                    	case 1 :
                    	    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:228:24: include
                    	    {
                    	    pushFollow(FOLLOW_include_in_filedef363);
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


            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:228:36: ( module )?
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==60) ) {
                alt4=1;
            }
            switch (alt4) {
                case 1 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:228:36: module
                    {
                    pushFollow(FOLLOW_module_in_filedef369);
                    module();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }


            match(input,EOF,FOLLOW_EOF_in_filedef372); if (state.failed) return ;

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
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:249:1: include : include_vwml ;
    public final void include() throws RecognitionException {
        String include_vwml1 =null;


        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:250:5: ( include_vwml )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:250:7: include_vwml
            {
            pushFollow(FOLLOW_include_vwml_in_include393);
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
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:258:1: include_vwml returns [String id] : 'include' STRING_LITERAL ;
    public final String include_vwml() throws RecognitionException {
        String id = null;


        Token STRING_LITERAL2=null;

        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:259:5: ( 'include' STRING_LITERAL )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:259:8: 'include' STRING_LITERAL
            {
            match(input,57,FOLLOW_57_in_include_vwml421); if (state.failed) return id;

            STRING_LITERAL2=(Token)match(input,STRING_LITERAL,FOLLOW_STRING_LITERAL_in_include_vwml423); if (state.failed) return id;

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
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:262:1: props : 'options' '{' optionsList '}' ;
    public final void props() throws RecognitionException {
        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:263:5: ( 'options' '{' optionsList '}' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:263:7: 'options' '{' optionsList '}'
            {
            match(input,61,FOLLOW_61_in_props442); if (state.failed) return ;

            match(input,66,FOLLOW_66_in_props444); if (state.failed) return ;

            pushFollow(FOLLOW_optionsList_in_props446);
            optionsList();

            state._fsp--;
            if (state.failed) return ;

            match(input,67,FOLLOW_67_in_props448); if (state.failed) return ;

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
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:266:1: optionsList : lang ;
    public final void optionsList() throws RecognitionException {
        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:267:5: ( lang )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:267:7: lang
            {
            pushFollow(FOLLOW_lang_in_optionsList469);
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
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:270:1: lang : ( ( 'language' '=' JAVA )=> langJava | otherLanguages );
    public final void lang() throws RecognitionException {
        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:271:5: ( ( 'language' '=' JAVA )=> langJava | otherLanguages )
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==59) && (synpred1_VirtualWorldModelingLanguage())) {
                alt5=1;
            }
            else if ( (LA5_0==67) ) {
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
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:271:7: ( 'language' '=' JAVA )=> langJava
                    {
                    pushFollow(FOLLOW_langJava_in_lang496);
                    langJava();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:272:7: otherLanguages
                    {
                    pushFollow(FOLLOW_otherLanguages_in_lang504);
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
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:275:1: otherLanguages :;
    public final void otherLanguages() throws RecognitionException {
        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:276:5: ()
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:277:5: 
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
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:279:1: langJava : 'language' '=' JAVA '{' javaProps '}' ;
    public final void langJava() throws RecognitionException {

               codeGenerator = vwmlModelBuilder.getCodeGenerator(VWMLModelBuilder.SINK_TYPE.JAVA);
               if (logger.isDebugEnabled()) {
               		logger.debug("Code generator '" + codeGenerator + "'");
               }
            
        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:286:5: ( 'language' '=' JAVA '{' javaProps '}' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:286:7: 'language' '=' JAVA '{' javaProps '}'
            {
            match(input,59,FOLLOW_59_in_langJava547); if (state.failed) return ;

            match(input,48,FOLLOW_48_in_langJava549); if (state.failed) return ;

            match(input,JAVA,FOLLOW_JAVA_in_langJava551); if (state.failed) return ;

            match(input,66,FOLLOW_66_in_langJava553); if (state.failed) return ;

            pushFollow(FOLLOW_javaProps_in_langJava555);
            javaProps();

            state._fsp--;
            if (state.failed) return ;

            match(input,67,FOLLOW_67_in_langJava557); if (state.failed) return ;

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
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:289:1: javaProps : propPackage ( generatedFileLocation )? optionalProps ;
    public final void javaProps() throws RecognitionException {

        	// instantiating module's properties which will be filled later
        	modProps = (codeGenerator != null) ? codeGenerator.buildProps() : null;
        	// tell to builder reference to module's properties
        	if (vwmlModelBuilder.getProjectProps() == null) {
        		vwmlModelBuilder.setProjectProps(modProps);
        	}
            
        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:298:5: ( propPackage ( generatedFileLocation )? optionalProps )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:298:7: propPackage ( generatedFileLocation )? optionalProps
            {
            pushFollow(FOLLOW_propPackage_in_javaProps583);
            propPackage();

            state._fsp--;
            if (state.failed) return ;

            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:298:19: ( generatedFileLocation )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==63) ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:298:19: generatedFileLocation
                    {
                    pushFollow(FOLLOW_generatedFileLocation_in_javaProps585);
                    generatedFileLocation();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }


            pushFollow(FOLLOW_optionalProps_in_javaProps588);
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
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:301:1: propPackage : 'package' '=' packageName ;
    public final void propPackage() throws RecognitionException {
        VirtualWorldModelingLanguageParser.packageName_return packageName3 =null;


        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:302:5: ( 'package' '=' packageName )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:302:7: 'package' '=' packageName
            {
            match(input,62,FOLLOW_62_in_propPackage610); if (state.failed) return ;

            match(input,48,FOLLOW_48_in_propPackage612); if (state.failed) return ;

            pushFollow(FOLLOW_packageName_in_propPackage614);
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
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:309:1: packageName : STRING_LITERAL ;
    public final VirtualWorldModelingLanguageParser.packageName_return packageName() throws RecognitionException {
        VirtualWorldModelingLanguageParser.packageName_return retval = new VirtualWorldModelingLanguageParser.packageName_return();
        retval.start = input.LT(1);


        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:310:5: ( STRING_LITERAL )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:310:7: STRING_LITERAL
            {
            match(input,STRING_LITERAL,FOLLOW_STRING_LITERAL_in_packageName633); if (state.failed) return retval;

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
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:313:1: generatedFileLocation : 'path' '=' path ;
    public final void generatedFileLocation() throws RecognitionException {
        VirtualWorldModelingLanguageParser.path_return path4 =null;


        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:314:5: ( 'path' '=' path )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:314:7: 'path' '=' path
            {
            match(input,63,FOLLOW_63_in_generatedFileLocation650); if (state.failed) return ;

            match(input,48,FOLLOW_48_in_generatedFileLocation652); if (state.failed) return ;

            pushFollow(FOLLOW_path_in_generatedFileLocation654);
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
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:321:1: optionalProps : ( author )? ( projname )? ( description )? ( entity_history_size )? ( visualizer )? ( beyond_the_fringe )? ;
    public final void optionalProps() throws RecognitionException {
        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:322:5: ( ( author )? ( projname )? ( description )? ( entity_history_size )? ( visualizer )? ( beyond_the_fringe )? )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:322:7: ( author )? ( projname )? ( description )? ( entity_history_size )? ( visualizer )? ( beyond_the_fringe )?
            {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:322:7: ( author )?
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==51) ) {
                alt7=1;
            }
            switch (alt7) {
                case 1 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:322:7: author
                    {
                    pushFollow(FOLLOW_author_in_optionalProps674);
                    author();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }


            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:322:15: ( projname )?
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==64) ) {
                alt8=1;
            }
            switch (alt8) {
                case 1 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:322:15: projname
                    {
                    pushFollow(FOLLOW_projname_in_optionalProps677);
                    projname();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }


            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:322:25: ( description )?
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==54) ) {
                alt9=1;
            }
            switch (alt9) {
                case 1 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:322:25: description
                    {
                    pushFollow(FOLLOW_description_in_optionalProps680);
                    description();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }


            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:322:38: ( entity_history_size )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==55) ) {
                alt10=1;
            }
            switch (alt10) {
                case 1 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:322:38: entity_history_size
                    {
                    pushFollow(FOLLOW_entity_history_size_in_optionalProps683);
                    entity_history_size();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }


            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:322:59: ( visualizer )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==65) ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:322:59: visualizer
                    {
                    pushFollow(FOLLOW_visualizer_in_optionalProps686);
                    visualizer();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }


            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:322:71: ( beyond_the_fringe )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==66) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:322:71: beyond_the_fringe
                    {
                    pushFollow(FOLLOW_beyond_the_fringe_in_optionalProps689);
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
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:325:1: author : 'author' '=' string ;
    public final void author() throws RecognitionException {
        VirtualWorldModelingLanguageParser.string_return string5 =null;


        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:326:5: ( 'author' '=' string )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:326:7: 'author' '=' string
            {
            match(input,51,FOLLOW_51_in_author707); if (state.failed) return ;

            match(input,48,FOLLOW_48_in_author709); if (state.failed) return ;

            pushFollow(FOLLOW_string_in_author711);
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
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:333:1: projname : 'project_name' '=' string ;
    public final void projname() throws RecognitionException {
        VirtualWorldModelingLanguageParser.string_return string6 =null;


        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:334:5: ( 'project_name' '=' string )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:334:7: 'project_name' '=' string
            {
            match(input,64,FOLLOW_64_in_projname730); if (state.failed) return ;

            match(input,48,FOLLOW_48_in_projname732); if (state.failed) return ;

            pushFollow(FOLLOW_string_in_projname734);
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
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:341:1: description : 'description' '=' string ;
    public final void description() throws RecognitionException {
        VirtualWorldModelingLanguageParser.string_return string7 =null;


        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:342:5: ( 'description' '=' string )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:342:7: 'description' '=' string
            {
            match(input,54,FOLLOW_54_in_description757); if (state.failed) return ;

            match(input,48,FOLLOW_48_in_description759); if (state.failed) return ;

            pushFollow(FOLLOW_string_in_description761);
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
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:349:1: entity_history_size : 'entity_history_size' '=' string ;
    public final void entity_history_size() throws RecognitionException {
        VirtualWorldModelingLanguageParser.string_return string8 =null;


        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:350:5: ( 'entity_history_size' '=' string )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:350:7: 'entity_history_size' '=' string
            {
            match(input,55,FOLLOW_55_in_entity_history_size780); if (state.failed) return ;

            match(input,48,FOLLOW_48_in_entity_history_size782); if (state.failed) return ;

            pushFollow(FOLLOW_string_in_entity_history_size784);
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
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:358:1: visualizer : 'visualizer' '{' visualizer_body '}' ;
    public final void visualizer() throws RecognitionException {
        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:359:5: ( 'visualizer' '{' visualizer_body '}' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:359:7: 'visualizer' '{' visualizer_body '}'
            {
            match(input,65,FOLLOW_65_in_visualizer804); if (state.failed) return ;

            match(input,66,FOLLOW_66_in_visualizer806); if (state.failed) return ;

            pushFollow(FOLLOW_visualizer_body_in_visualizer808);
            visualizer_body();

            state._fsp--;
            if (state.failed) return ;

            match(input,67,FOLLOW_67_in_visualizer810); if (state.failed) return ;

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
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:362:1: visualizer_body : ( visualizer_class visualizer_datapath |);
    public final void visualizer_body() throws RecognitionException {
        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:363:5: ( visualizer_class visualizer_datapath |)
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==52) ) {
                alt13=1;
            }
            else if ( (LA13_0==67) ) {
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
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:363:7: visualizer_class visualizer_datapath
                    {
                    pushFollow(FOLLOW_visualizer_class_in_visualizer_body828);
                    visualizer_class();

                    state._fsp--;
                    if (state.failed) return ;

                    pushFollow(FOLLOW_visualizer_datapath_in_visualizer_body830);
                    visualizer_datapath();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:365:5: 
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
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:367:1: visualizer_class : 'class' '=' string ;
    public final void visualizer_class() throws RecognitionException {
        VirtualWorldModelingLanguageParser.string_return string9 =null;


        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:368:5: ( 'class' '=' string )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:368:7: 'class' '=' string
            {
            match(input,52,FOLLOW_52_in_visualizer_class853); if (state.failed) return ;

            match(input,48,FOLLOW_48_in_visualizer_class855); if (state.failed) return ;

            pushFollow(FOLLOW_string_in_visualizer_class857);
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
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:375:1: visualizer_datapath : 'data' '=' string ;
    public final void visualizer_datapath() throws RecognitionException {
        VirtualWorldModelingLanguageParser.string_return string10 =null;


        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:376:5: ( 'data' '=' string )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:376:7: 'data' '=' string
            {
            match(input,53,FOLLOW_53_in_visualizer_datapath880); if (state.failed) return ;

            match(input,48,FOLLOW_48_in_visualizer_datapath882); if (state.failed) return ;

            pushFollow(FOLLOW_string_in_visualizer_datapath884);
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
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:383:1: path : STRING_LITERAL ;
    public final VirtualWorldModelingLanguageParser.path_return path() throws RecognitionException {
        VirtualWorldModelingLanguageParser.path_return retval = new VirtualWorldModelingLanguageParser.path_return();
        retval.start = input.LT(1);


        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:384:5: ( STRING_LITERAL )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:384:7: STRING_LITERAL
            {
            match(input,STRING_LITERAL,FOLLOW_STRING_LITERAL_in_path907); if (state.failed) return retval;

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
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:388:1: beyond_the_fringe : '{' beyond_the_fringe_body '}' ;
    public final void beyond_the_fringe() throws RecognitionException {
        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:389:5: ( '{' beyond_the_fringe_body '}' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:389:7: '{' beyond_the_fringe_body '}'
            {
            match(input,66,FOLLOW_66_in_beyond_the_fringe925); if (state.failed) return ;

            pushFollow(FOLLOW_beyond_the_fringe_body_in_beyond_the_fringe927);
            beyond_the_fringe_body();

            state._fsp--;
            if (state.failed) return ;

            match(input,67,FOLLOW_67_in_beyond_the_fringe929); if (state.failed) return ;

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
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:392:1: beyond_the_fringe_body : 'fringe' '{' creatures '}' ;
    public final void beyond_the_fringe_body() throws RecognitionException {
        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:393:5: ( 'fringe' '{' creatures '}' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:393:8: 'fringe' '{' creatures '}'
            {
            match(input,56,FOLLOW_56_in_beyond_the_fringe_body951); if (state.failed) return ;

            match(input,66,FOLLOW_66_in_beyond_the_fringe_body953); if (state.failed) return ;

            pushFollow(FOLLOW_creatures_in_beyond_the_fringe_body955);
            creatures();

            state._fsp--;
            if (state.failed) return ;

            match(input,67,FOLLOW_67_in_beyond_the_fringe_body957); if (state.failed) return ;

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



    // $ANTLR start "creatures"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:396:1: creatures : ( creature )+ ;
    public final void creatures() throws RecognitionException {
        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:397:5: ( ( creature )+ )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:397:7: ( creature )+
            {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:397:7: ( creature )+
            int cnt14=0;
            loop14:
            do {
                int alt14=2;
                int LA14_0 = input.LA(1);

                if ( (LA14_0==ID) ) {
                    alt14=1;
                }


                switch (alt14) {
            	case 1 :
            	    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:397:8: creature
            	    {
            	    pushFollow(FOLLOW_creature_in_creatures975);
            	    creature();

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
    // $ANTLR end "creatures"



    // $ANTLR start "creature"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:400:1: creature : ID 'is' string ;
    public final void creature() throws RecognitionException {
        Token ID11=null;
        VirtualWorldModelingLanguageParser.string_return string12 =null;


        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:412:5: ( ID 'is' string )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:412:7: ID 'is' string
            {
            ID11=(Token)match(input,ID,FOLLOW_ID_in_creature1003); if (state.failed) return ;

            if ( state.backtracking==0 ) {
                		addLastDeclaredCreature(ID11.getText());
                	 }

            match(input,58,FOLLOW_58_in_creature1007); if (state.failed) return ;

            pushFollow(FOLLOW_string_in_creature1009);
            string12=string();

            state._fsp--;
            if (state.failed) return ;

            if ( state.backtracking==0 ) {
                	 			addLastDeclaredCreatureProps(GeneralUtils.trimQuotes((string12!=null?input.toString(string12.start,string12.stop):null)));
                	 		}

            }

            if ( state.backtracking==0 ) {
            	if (codeGenerator != null) {
            		try {
            			codeGenerator.declareCreature(getLastDeclaredCreature(), getLastDeclaredCreatureProps(), null);
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
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:419:1: module : 'module' ID body ;
    public final void module() throws RecognitionException {
        Token ID13=null;

        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:420:5: ( 'module' ID body )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:420:7: 'module' ID body
            {
            match(input,60,FOLLOW_60_in_module1029); if (state.failed) return ;

            ID13=(Token)match(input,ID,FOLLOW_ID_in_module1031); if (state.failed) return ;

            if ( state.backtracking==0 ) { 
                			modName = ID13.getText();
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

            pushFollow(FOLLOW_body_in_module1035);
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
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:448:1: body : '{' ( expression ( expression )* )? '}' ;
    public final void body() throws RecognitionException {
        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:449:4: ( '{' ( expression ( expression )* )? '}' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:449:6: '{' ( expression ( expression )* )? '}'
            {
            match(input,66,FOLLOW_66_in_body1055); if (state.failed) return ;

            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:449:10: ( expression ( expression )* )?
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==ID||LA16_0==LIFETERM||LA16_0==46||LA16_0==49) ) {
                alt16=1;
            }
            switch (alt16) {
                case 1 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:449:11: expression ( expression )*
                    {
                    pushFollow(FOLLOW_expression_in_body1058);
                    expression();

                    state._fsp--;
                    if (state.failed) return ;

                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:449:22: ( expression )*
                    loop15:
                    do {
                        int alt15=2;
                        int LA15_0 = input.LA(1);

                        if ( (LA15_0==ID||LA15_0==LIFETERM||LA15_0==46||LA15_0==49) ) {
                            alt15=1;
                        }


                        switch (alt15) {
                    	case 1 :
                    	    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:449:23: expression
                    	    {
                    	    pushFollow(FOLLOW_expression_in_body1061);
                    	    expression();

                    	    state._fsp--;
                    	    if (state.failed) return ;

                    	    }
                    	    break;

                    	default :
                    	    break loop15;
                        }
                    } while (true);


                    }
                    break;

            }


            match(input,67,FOLLOW_67_in_body1067); if (state.failed) return ;

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
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:453:1: expression : ( ( entity_decl IAS )=> entity_def | check_term_def );
    public final void expression() throws RecognitionException {
        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:454:5: ( ( entity_decl IAS )=> entity_def | check_term_def )
            int alt17=2;
            switch ( input.LA(1) ) {
            case ID:
                {
                int LA17_1 = input.LA(2);

                if ( (synpred2_VirtualWorldModelingLanguage()) ) {
                    alt17=1;
                }
                else if ( (true) ) {
                    alt17=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return ;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 17, 1, input);

                    throw nvae;

                }
                }
                break;
            case 46:
                {
                int LA17_2 = input.LA(2);

                if ( (synpred2_VirtualWorldModelingLanguage()) ) {
                    alt17=1;
                }
                else if ( (true) ) {
                    alt17=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return ;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 17, 2, input);

                    throw nvae;

                }
                }
                break;
            case LIFETERM:
            case 49:
                {
                alt17=2;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 17, 0, input);

                throw nvae;

            }

            switch (alt17) {
                case 1 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:454:7: ( entity_decl IAS )=> entity_def
                    {
                    pushFollow(FOLLOW_entity_def_in_expression1093);
                    entity_def();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:455:7: check_term_def
                    {
                    pushFollow(FOLLOW_check_term_def_in_expression1101);
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
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:458:1: entity_def : entity_decl IAS term ( SEMICOLON )? ;
    public final void entity_def() throws RecognitionException {
        String entity_decl14 =null;


        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:459:5: ( entity_decl IAS term ( SEMICOLON )? )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:459:7: entity_decl IAS term ( SEMICOLON )?
            {
            pushFollow(FOLLOW_entity_decl_in_entity_def1118);
            entity_decl14=entity_decl();

            state._fsp--;
            if (state.failed) return ;

            match(input,IAS,FOLLOW_IAS_in_entity_def1120); if (state.failed) return ;

            if ( state.backtracking==0 ) {
                			// adds entity id to context stack
                			vwmlContextBuilder.push(entity_decl14);
                			entityWalker.markFutureEntityAsIAS(entity_decl14);
                			if (codeGenerator != null) {
                				codeGenerator.declareContext(vwmlContextBuilder.buildContext());
                			}
                			// we should link this entity with parent, if exists
                			 buildLinkingAssociation(entity_decl14);
                		      }

            pushFollow(FOLLOW_term_in_entity_def1124);
            term();

            state._fsp--;
            if (state.failed) return ;

            if ( state.backtracking==0 ) {
                		      	// removes top entity from stack
                		      	vwmlContextBuilder.pop();
                		      }

            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:473:13: ( SEMICOLON )?
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( (LA18_0==SEMICOLON) ) {
                alt18=1;
            }
            switch (alt18) {
                case 1 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:473:14: SEMICOLON
                    {
                    match(input,SEMICOLON,FOLLOW_SEMICOLON_in_entity_def1154); if (state.failed) return ;

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
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:476:1: check_term_def : ( LIFETERM '=' lifeterm_def | term_def );
    public final void check_term_def() throws RecognitionException {
        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:477:5: ( LIFETERM '=' lifeterm_def | term_def )
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( (LA19_0==LIFETERM) ) {
                alt19=1;
            }
            else if ( (LA19_0==ID||LA19_0==46||LA19_0==49) ) {
                alt19=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 19, 0, input);

                throw nvae;

            }
            switch (alt19) {
                case 1 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:477:7: LIFETERM '=' lifeterm_def
                    {
                    match(input,LIFETERM,FOLLOW_LIFETERM_in_check_term_def1173); if (state.failed) return ;

                    match(input,48,FOLLOW_48_in_check_term_def1175); if (state.failed) return ;

                    pushFollow(FOLLOW_lifeterm_def_in_check_term_def1177);
                    lifeterm_def();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:478:7: term_def
                    {
                    pushFollow(FOLLOW_term_def_in_check_term_def1185);
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
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:481:1: lifeterm_def : term_def ;
    public final void lifeterm_def() throws RecognitionException {
        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:482:5: ( term_def )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:482:8: term_def
            {
            pushFollow(FOLLOW_term_def_in_lifeterm_def1207);
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
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:500:1: term_def : entity ( oplist )* ;
    public final void term_def() throws RecognitionException {
        EntityWalker.Relation entity15 =null;


        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:501:5: ( entity ( oplist )* )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:501:7: entity ( oplist )*
            {
            pushFollow(FOLLOW_entity_in_term_def1226);
            entity15=entity();

            state._fsp--;
            if (state.failed) return ;

            if ( state.backtracking==0 ) {
                		lastProcessedEntity = entity15;
                		lastProcessedEntityAsTerm = false;
                	     }

            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:504:13: ( oplist )*
            loop20:
            do {
                int alt20=2;
                int LA20_0 = input.LA(1);

                if ( ((LA20_0 >= OPBEGIN && LA20_0 <= OPSUBSTRUCT)) ) {
                    alt20=1;
                }


                switch (alt20) {
            	case 1 :
            	    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:504:14: oplist
            	    {
            	    pushFollow(FOLLOW_oplist_in_term_def1231);
            	    oplist();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop20;
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
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:520:1: entity_decl returns [String id] : ( simple_entity_decl | '(' complex_entity_decl ')' );
    public final String entity_decl() throws RecognitionException {
        String id = null;


        String simple_entity_decl16 =null;


        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:521:5: ( simple_entity_decl | '(' complex_entity_decl ')' )
            int alt21=2;
            int LA21_0 = input.LA(1);

            if ( (LA21_0==ID) ) {
                alt21=1;
            }
            else if ( (LA21_0==46) ) {
                alt21=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return id;}
                NoViableAltException nvae =
                    new NoViableAltException("", 21, 0, input);

                throw nvae;

            }
            switch (alt21) {
                case 1 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:521:7: simple_entity_decl
                    {
                    pushFollow(FOLLOW_simple_entity_decl_in_entity_decl1265);
                    simple_entity_decl16=simple_entity_decl();

                    state._fsp--;
                    if (state.failed) return id;

                    if ( state.backtracking==0 ) {id = simple_entity_decl16;}

                    }
                    break;
                case 2 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:522:7: '(' complex_entity_decl ')'
                    {
                    match(input,46,FOLLOW_46_in_entity_decl1276); if (state.failed) return id;

                    if ( state.backtracking==0 ) {
                        	    	complexEntityNameBuilderDecl.startProgress();
                    	    	if (logger.isDebugEnabled()) {
                    	    		logger.debug("complex entity declaration process - started");
                    	    	}
                              }

                    pushFollow(FOLLOW_complex_entity_decl_in_entity_decl1281);
                    complex_entity_decl();

                    state._fsp--;
                    if (state.failed) return id;

                    match(input,47,FOLLOW_47_in_entity_decl1283); if (state.failed) return id;

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
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:546:1: compound_entity_decl : entity_decl ;
    public final void compound_entity_decl() throws RecognitionException {
        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:547:5: ( entity_decl )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:547:7: entity_decl
            {
            pushFollow(FOLLOW_entity_decl_in_compound_entity_decl1302);
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
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:550:1: simple_entity_decl returns [String id] : ID ;
    public final String simple_entity_decl() throws RecognitionException {
        String id = null;


        Token ID17=null;

        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:551:5: ( ID )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:551:7: ID
            {
            ID17=(Token)match(input,ID,FOLLOW_ID_in_simple_entity_decl1327); if (state.failed) return id;

            if ( state.backtracking==0 ) {
                		id = ID17.getText();
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
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:577:1: complex_entity_decl : ( compound_entity_decl )+ ;
    public final void complex_entity_decl() throws RecognitionException {
        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:581:5: ( ( compound_entity_decl )+ )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:581:7: ( compound_entity_decl )+
            {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:581:7: ( compound_entity_decl )+
            int cnt22=0;
            loop22:
            do {
                int alt22=2;
                int LA22_0 = input.LA(1);

                if ( (LA22_0==ID||LA22_0==46) ) {
                    alt22=1;
                }


                switch (alt22) {
            	case 1 :
            	    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:581:8: compound_entity_decl
            	    {
            	    pushFollow(FOLLOW_compound_entity_decl_in_complex_entity_decl1360);
            	    compound_entity_decl();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    if ( cnt22 >= 1 ) break loop22;
            	    if (state.backtracking>0) {state.failed=true; return ;}
                        EarlyExitException eee =
                            new EarlyExitException(22, input);
                        throw eee;
                }
                cnt22++;
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
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:584:1: term : expression ;
    public final void term() throws RecognitionException {
        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:585:5: ( expression )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:585:7: expression
            {
            pushFollow(FOLLOW_expression_in_term1379);
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
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:588:1: entity returns [EntityWalker.Relation rel] : ( simple_entity | complex_entity | syncronization_entity );
    public final EntityWalker.Relation entity() throws RecognitionException {
        EntityWalker.Relation rel = null;


        EntityWalker.Relation simple_entity18 =null;

        EntityWalker.Relation complex_entity19 =null;

        EntityWalker.Relation syncronization_entity20 =null;


        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:589:5: ( simple_entity | complex_entity | syncronization_entity )
            int alt23=3;
            switch ( input.LA(1) ) {
            case ID:
                {
                alt23=1;
                }
                break;
            case 46:
                {
                alt23=2;
                }
                break;
            case 49:
                {
                alt23=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return rel;}
                NoViableAltException nvae =
                    new NoViableAltException("", 23, 0, input);

                throw nvae;

            }

            switch (alt23) {
                case 1 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:589:7: simple_entity
                    {
                    pushFollow(FOLLOW_simple_entity_in_entity1402);
                    simple_entity18=simple_entity();

                    state._fsp--;
                    if (state.failed) return rel;

                    if ( state.backtracking==0 ) {rel = simple_entity18; }

                    }
                    break;
                case 2 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:590:7: complex_entity
                    {
                    pushFollow(FOLLOW_complex_entity_in_entity1420);
                    complex_entity19=complex_entity();

                    state._fsp--;
                    if (state.failed) return rel;

                    if ( state.backtracking==0 ) {rel = complex_entity19;}

                    }
                    break;
                case 3 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:591:7: syncronization_entity
                    {
                    pushFollow(FOLLOW_syncronization_entity_in_entity1437);
                    syncronization_entity20=syncronization_entity();

                    state._fsp--;
                    if (state.failed) return rel;

                    if ( state.backtracking==0 ) {rel = syncronization_entity20;}

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
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:595:1: syncronization_entity returns [EntityWalker.Relation rel] : '[' entity ']' ;
    public final EntityWalker.Relation syncronization_entity() throws RecognitionException {
        EntityWalker.Relation rel = null;


        EntityWalker.Relation entity21 =null;


        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:596:5: ( '[' entity ']' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:596:7: '[' entity ']'
            {
            match(input,49,FOLLOW_49_in_syncronization_entity1461); if (state.failed) return rel;

            pushFollow(FOLLOW_entity_in_syncronization_entity1463);
            entity21=entity();

            state._fsp--;
            if (state.failed) return rel;

            if ( state.backtracking==0 ) {rel = entity21; }

            match(input,50,FOLLOW_50_in_syncronization_entity1467); if (state.failed) return rel;

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
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:599:1: simple_entity returns [EntityWalker.Relation rel] : ID ;
    public final EntityWalker.Relation simple_entity() throws RecognitionException {
        EntityWalker.Relation rel = null;


        Token ID22=null;

        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:600:5: ( ID )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:600:7: ID
            {
            ID22=(Token)match(input,ID,FOLLOW_ID_in_simple_entity1489); if (state.failed) return rel;

            if ( state.backtracking==0 ) {
                		// since entity's id may include '.' we should check correctness of name
                		// if name ends with '.' we can suppouse that this name can be considered as complex entity's effective context
                		if (!vwmlContextBuilder.isEffectiveContext((ID22!=null?ID22.getText():null))) {
                			if (complexEntityNameBuilderDef.isInProgress()) {
                				complexEntityNameBuilderDef.addObjectId((ID22!=null?ID22.getText():null));
                			}
               			rel = buildRelation((ID22!=null?ID22.getText():null));
                			if (logger.isDebugEnabled()) {
                				logger.debug("processed simple entity '" + rel + "'");
                			}
                		}
                		else {
                			// so entity is considered as effective context
                			vwmlContextBuilder.setEffectiveContext((ID22!=null?ID22.getText():null));
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
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:622:1: complex_entity returns [EntityWalker.Relation rel] : '(' ( term )* ')' ;
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
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:665:5: ( '(' ( term )* ')' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:665:7: '(' ( term )* ')'
            {
            match(input,46,FOLLOW_46_in_complex_entity1530); if (state.failed) return rel;

            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:665:11: ( term )*
            loop24:
            do {
                int alt24=2;
                int LA24_0 = input.LA(1);

                if ( (LA24_0==ID||LA24_0==LIFETERM||LA24_0==46||LA24_0==49) ) {
                    alt24=1;
                }


                switch (alt24) {
            	case 1 :
            	    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:665:12: term
            	    {
            	    pushFollow(FOLLOW_term_in_complex_entity1533);
            	    term();

            	    state._fsp--;
            	    if (state.failed) return rel;

            	    }
            	    break;

            	default :
            	    break loop24;
                }
            } while (true);


            match(input,47,FOLLOW_47_in_complex_entity1537); if (state.failed) return rel;

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
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:676:1: oplist : ( opclist | opprojection );
    public final void oplist() throws RecognitionException {
        VirtualWorldModelingLanguageParser.opclist_return opclist23 =null;

        VirtualWorldModelingLanguageParser.opprojection_return opprojection24 =null;


        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:678:5: ( opclist | opprojection )
            int alt25=2;
            int LA25_0 = input.LA(1);

            if ( ((LA25_0 >= OPBEGIN && LA25_0 <= OPLAST)||(LA25_0 >= OPRANDOM && LA25_0 <= OPSUBSTRUCT)) ) {
                alt25=1;
            }
            else if ( ((LA25_0 >= OPPROJECTION_1 && LA25_0 <= OPPROJECTION_9)) ) {
                alt25=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 25, 0, input);

                throw nvae;

            }
            switch (alt25) {
                case 1 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:678:7: opclist
                    {
                    pushFollow(FOLLOW_opclist_in_oplist1622);
                    opclist23=opclist();

                    state._fsp--;
                    if (state.failed) return ;

                    if ( state.backtracking==0 ) {
                        			if (lastProcessedEntity != null && codeGenerator != null) { 
                        				lastProcessedEntityAsTerm = true;
                        				codeGenerator.associateOperation(lastProcessedEntity, (opclist23!=null?input.toString(opclist23.start,opclist23.stop):null), vwmlContextBuilder.buildContext());
                        			} 
                        		    }

                    }
                    break;
                case 2 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:684:7: opprojection
                    {
                    pushFollow(FOLLOW_opprojection_in_oplist1638);
                    opprojection24=opprojection();

                    state._fsp--;
                    if (state.failed) return ;

                    if ( state.backtracking==0 ) {
                        			if (lastProcessedEntity != null && codeGenerator != null) {
                        				lastProcessedEntityAsTerm = true;
                        				codeGenerator.associateOperation(lastProcessedEntity, (opprojection24!=null?input.toString(opprojection24.start,opprojection24.stop):null), vwmlContextBuilder.buildContext());
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
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:692:1: opclist : ( OPJOIN | OPINTERSECT | OPSUBSTRUCT | OPFIRST | OPLAST | OPBEGIN | OPREST | OPCARTESIAN | OPIN | OPINCL | OPEQ | OPIDENT | OPSQU | OPINTERPRET | OPCREATEEXPR | OPEXECUTE | OPRANDOM );
    public final VirtualWorldModelingLanguageParser.opclist_return opclist() throws RecognitionException {
        VirtualWorldModelingLanguageParser.opclist_return retval = new VirtualWorldModelingLanguageParser.opclist_return();
        retval.start = input.LT(1);


        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:693:5: ( OPJOIN | OPINTERSECT | OPSUBSTRUCT | OPFIRST | OPLAST | OPBEGIN | OPREST | OPCARTESIAN | OPIN | OPINCL | OPEQ | OPIDENT | OPSQU | OPINTERPRET | OPCREATEEXPR | OPEXECUTE | OPRANDOM )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:
            {
            if ( (input.LA(1) >= OPBEGIN && input.LA(1) <= OPLAST)||(input.LA(1) >= OPRANDOM && input.LA(1) <= OPSUBSTRUCT) ) {
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
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:712:1: opprojection : ( OPPROJECTION_1 | OPPROJECTION_2 | OPPROJECTION_3 | OPPROJECTION_4 | OPPROJECTION_5 | OPPROJECTION_6 | OPPROJECTION_7 | OPPROJECTION_8 | OPPROJECTION_9 );
    public final VirtualWorldModelingLanguageParser.opprojection_return opprojection() throws RecognitionException {
        VirtualWorldModelingLanguageParser.opprojection_return retval = new VirtualWorldModelingLanguageParser.opprojection_return();
        retval.start = input.LT(1);


        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:713:5: ( OPPROJECTION_1 | OPPROJECTION_2 | OPPROJECTION_3 | OPPROJECTION_4 | OPPROJECTION_5 | OPPROJECTION_6 | OPPROJECTION_7 | OPPROJECTION_8 | OPPROJECTION_9 )
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
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:724:1: termLanguages : ( JAVA | C | CPP | OBJECTIVEC );
    public final void termLanguages() throws RecognitionException {
        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:725:5: ( JAVA | C | CPP | OBJECTIVEC )
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
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:731:1: string : STRING_LITERAL ;
    public final VirtualWorldModelingLanguageParser.string_return string() throws RecognitionException {
        VirtualWorldModelingLanguageParser.string_return retval = new VirtualWorldModelingLanguageParser.string_return();
        retval.start = input.LT(1);


        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:732:5: ( STRING_LITERAL )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:732:7: STRING_LITERAL
            {
            match(input,STRING_LITERAL,FOLLOW_STRING_LITERAL_in_string1925); if (state.failed) return retval;

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
        // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:271:7: ( 'language' '=' JAVA )
        // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:271:8: 'language' '=' JAVA
        {
        match(input,59,FOLLOW_59_in_synpred1_VirtualWorldModelingLanguage487); if (state.failed) return ;

        match(input,48,FOLLOW_48_in_synpred1_VirtualWorldModelingLanguage489); if (state.failed) return ;

        match(input,JAVA,FOLLOW_JAVA_in_synpred1_VirtualWorldModelingLanguage491); if (state.failed) return ;

        }

    }
    // $ANTLR end synpred1_VirtualWorldModelingLanguage

    // $ANTLR start synpred2_VirtualWorldModelingLanguage
    public final void synpred2_VirtualWorldModelingLanguage_fragment() throws RecognitionException {
        // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:454:7: ( entity_decl IAS )
        // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:454:8: entity_decl IAS
        {
        pushFollow(FOLLOW_entity_decl_in_synpred2_VirtualWorldModelingLanguage1086);
        entity_decl();

        state._fsp--;
        if (state.failed) return ;

        match(input,IAS,FOLLOW_IAS_in_synpred2_VirtualWorldModelingLanguage1088); if (state.failed) return ;

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


 

    public static final BitSet FOLLOW_props_in_filedef356 = new BitSet(new long[]{0x1200000000000000L});
    public static final BitSet FOLLOW_include_in_filedef360 = new BitSet(new long[]{0x1200000000000000L});
    public static final BitSet FOLLOW_include_in_filedef363 = new BitSet(new long[]{0x1200000000000000L});
    public static final BitSet FOLLOW_module_in_filedef369 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_filedef372 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_include_vwml_in_include393 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_57_in_include_vwml421 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_STRING_LITERAL_in_include_vwml423 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_61_in_props442 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000004L});
    public static final BitSet FOLLOW_66_in_props444 = new BitSet(new long[]{0x0800000000000000L});
    public static final BitSet FOLLOW_optionsList_in_props446 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_67_in_props448 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_lang_in_optionsList469 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_langJava_in_lang496 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_otherLanguages_in_lang504 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_59_in_langJava547 = new BitSet(new long[]{0x0001000000000000L});
    public static final BitSet FOLLOW_48_in_langJava549 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_JAVA_in_langJava551 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000004L});
    public static final BitSet FOLLOW_66_in_langJava553 = new BitSet(new long[]{0x4000000000000000L});
    public static final BitSet FOLLOW_javaProps_in_langJava555 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_67_in_langJava557 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_propPackage_in_javaProps583 = new BitSet(new long[]{0x80C8000000000000L,0x0000000000000007L});
    public static final BitSet FOLLOW_generatedFileLocation_in_javaProps585 = new BitSet(new long[]{0x00C8000000000000L,0x0000000000000007L});
    public static final BitSet FOLLOW_optionalProps_in_javaProps588 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_62_in_propPackage610 = new BitSet(new long[]{0x0001000000000000L});
    public static final BitSet FOLLOW_48_in_propPackage612 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_packageName_in_propPackage614 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_LITERAL_in_packageName633 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_63_in_generatedFileLocation650 = new BitSet(new long[]{0x0001000000000000L});
    public static final BitSet FOLLOW_48_in_generatedFileLocation652 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_path_in_generatedFileLocation654 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_author_in_optionalProps674 = new BitSet(new long[]{0x00C0000000000002L,0x0000000000000007L});
    public static final BitSet FOLLOW_projname_in_optionalProps677 = new BitSet(new long[]{0x00C0000000000002L,0x0000000000000006L});
    public static final BitSet FOLLOW_description_in_optionalProps680 = new BitSet(new long[]{0x0080000000000002L,0x0000000000000006L});
    public static final BitSet FOLLOW_entity_history_size_in_optionalProps683 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000006L});
    public static final BitSet FOLLOW_visualizer_in_optionalProps686 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000004L});
    public static final BitSet FOLLOW_beyond_the_fringe_in_optionalProps689 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_51_in_author707 = new BitSet(new long[]{0x0001000000000000L});
    public static final BitSet FOLLOW_48_in_author709 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_string_in_author711 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_64_in_projname730 = new BitSet(new long[]{0x0001000000000000L});
    public static final BitSet FOLLOW_48_in_projname732 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_string_in_projname734 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_54_in_description757 = new BitSet(new long[]{0x0001000000000000L});
    public static final BitSet FOLLOW_48_in_description759 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_string_in_description761 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_55_in_entity_history_size780 = new BitSet(new long[]{0x0001000000000000L});
    public static final BitSet FOLLOW_48_in_entity_history_size782 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_string_in_entity_history_size784 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_65_in_visualizer804 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000004L});
    public static final BitSet FOLLOW_66_in_visualizer806 = new BitSet(new long[]{0x0010000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_visualizer_body_in_visualizer808 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_67_in_visualizer810 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_visualizer_class_in_visualizer_body828 = new BitSet(new long[]{0x0020000000000000L});
    public static final BitSet FOLLOW_visualizer_datapath_in_visualizer_body830 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_52_in_visualizer_class853 = new BitSet(new long[]{0x0001000000000000L});
    public static final BitSet FOLLOW_48_in_visualizer_class855 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_string_in_visualizer_class857 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_53_in_visualizer_datapath880 = new BitSet(new long[]{0x0001000000000000L});
    public static final BitSet FOLLOW_48_in_visualizer_datapath882 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_string_in_visualizer_datapath884 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_LITERAL_in_path907 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_66_in_beyond_the_fringe925 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_beyond_the_fringe_body_in_beyond_the_fringe927 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_67_in_beyond_the_fringe929 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_56_in_beyond_the_fringe_body951 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000004L});
    public static final BitSet FOLLOW_66_in_beyond_the_fringe_body953 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_creatures_in_beyond_the_fringe_body955 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_67_in_beyond_the_fringe_body957 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_creature_in_creatures975 = new BitSet(new long[]{0x0000000000000402L});
    public static final BitSet FOLLOW_ID_in_creature1003 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_58_in_creature1007 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_string_in_creature1009 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_60_in_module1029 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_ID_in_module1031 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000004L});
    public static final BitSet FOLLOW_body_in_module1035 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_66_in_body1055 = new BitSet(new long[]{0x0002400000002400L,0x0000000000000008L});
    public static final BitSet FOLLOW_expression_in_body1058 = new BitSet(new long[]{0x0002400000002400L,0x0000000000000008L});
    public static final BitSet FOLLOW_expression_in_body1061 = new BitSet(new long[]{0x0002400000002400L,0x0000000000000008L});
    public static final BitSet FOLLOW_67_in_body1067 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_entity_def_in_expression1093 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_check_term_def_in_expression1101 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_entity_decl_in_entity_def1118 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_IAS_in_entity_def1120 = new BitSet(new long[]{0x0002400000002400L});
    public static final BitSet FOLLOW_term_in_entity_def1124 = new BitSet(new long[]{0x0000080000000002L});
    public static final BitSet FOLLOW_SEMICOLON_in_entity_def1154 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LIFETERM_in_check_term_def1173 = new BitSet(new long[]{0x0001000000000000L});
    public static final BitSet FOLLOW_48_in_check_term_def1175 = new BitSet(new long[]{0x0002400000000400L});
    public static final BitSet FOLLOW_lifeterm_def_in_check_term_def1177 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_term_def_in_check_term_def1185 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_term_def_in_lifeterm_def1207 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_entity_in_term_def1226 = new BitSet(new long[]{0x000007FFFFFE0002L});
    public static final BitSet FOLLOW_oplist_in_term_def1231 = new BitSet(new long[]{0x000007FFFFFE0002L});
    public static final BitSet FOLLOW_simple_entity_decl_in_entity_decl1265 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_46_in_entity_decl1276 = new BitSet(new long[]{0x0000400000000400L});
    public static final BitSet FOLLOW_complex_entity_decl_in_entity_decl1281 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_47_in_entity_decl1283 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_entity_decl_in_compound_entity_decl1302 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_simple_entity_decl1327 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_compound_entity_decl_in_complex_entity_decl1360 = new BitSet(new long[]{0x0000400000000402L});
    public static final BitSet FOLLOW_expression_in_term1379 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simple_entity_in_entity1402 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_complex_entity_in_entity1420 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_syncronization_entity_in_entity1437 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_49_in_syncronization_entity1461 = new BitSet(new long[]{0x0002400000000400L});
    public static final BitSet FOLLOW_entity_in_syncronization_entity1463 = new BitSet(new long[]{0x0004000000000000L});
    public static final BitSet FOLLOW_50_in_syncronization_entity1467 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_simple_entity1489 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_46_in_complex_entity1530 = new BitSet(new long[]{0x0002C00000002400L});
    public static final BitSet FOLLOW_term_in_complex_entity1533 = new BitSet(new long[]{0x0002C00000002400L});
    public static final BitSet FOLLOW_47_in_complex_entity1537 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_opclist_in_oplist1622 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_opprojection_in_oplist1638 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_LITERAL_in_string1925 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_59_in_synpred1_VirtualWorldModelingLanguage487 = new BitSet(new long[]{0x0001000000000000L});
    public static final BitSet FOLLOW_48_in_synpred1_VirtualWorldModelingLanguage489 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_JAVA_in_synpred1_VirtualWorldModelingLanguage491 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_entity_decl_in_synpred2_VirtualWorldModelingLanguage1086 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_IAS_in_synpred2_VirtualWorldModelingLanguage1088 = new BitSet(new long[]{0x0000000000000002L});

}