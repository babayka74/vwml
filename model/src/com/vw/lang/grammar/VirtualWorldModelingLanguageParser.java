// $ANTLR 3.4 C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g 2013-09-03 16:09:30

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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "C", "COMMA", "COMMENT", "CPP", "DQUOTE", "IAS", "ID", "JAVA", "LETTER", "LINE_COMMENT", "NATIVE_CODE", "NIL", "OBJECTIVEC", "OPBEGIN", "OPCARTESIAN", "OPCREATEEXPR", "OPEQ", "OPEXECUTE", "OPFIRST", "OPIDENT", "OPIN", "OPINCL", "OPINTERPRET", "OPINTERSECT", "OPJOIN", "OPLAST", "OPPROJECTION_1", "OPPROJECTION_2", "OPPROJECTION_3", "OPPROJECTION_4", "OPPROJECTION_5", "OPPROJECTION_6", "OPPROJECTION_7", "OPPROJECTION_8", "OPPROJECTION_9", "OPRANDOM", "OPREST", "OPSQU", "OPSUBSTRUCT", "SEMICOLON", "STRING_LITERAL", "WS", "'('", "')'", "'='", "'['", "']'", "'author'", "'class'", "'data'", "'description'", "'include'", "'language'", "'module'", "'options'", "'package'", "'path'", "'project_name'", "'visualizer'", "'{'", "'}'"
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
    public static final int C=4;
    public static final int COMMA=5;
    public static final int COMMENT=6;
    public static final int CPP=7;
    public static final int DQUOTE=8;
    public static final int IAS=9;
    public static final int ID=10;
    public static final int JAVA=11;
    public static final int LETTER=12;
    public static final int LINE_COMMENT=13;
    public static final int NATIVE_CODE=14;
    public static final int NIL=15;
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
    public String getGrammarFileName() { return "C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g"; }


    	private VWMLModelBuilder vwmlModelBuilder = VWMLModelBuilder.instance();
    	private ICodeGenerator codeGenerator = null;
    	private StartModuleProps modProps = null;
    	private ComplexEntityNameBuilder complexEntityNameBuilder = ComplexEntityNameBuilder.instance();
    	private EntityWalker entityWalker = EntityWalker.instance();
    	private EntityWalker.Relation lastProcessedEntity = null;
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
    	
    	protected Object buildIASAssociation(Object id) throws RecognitionException {
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
        		return (codeGenerator != null) ? codeGenerator.getLastLink() : null;
    	}
    	
    	protected Object buildLinkingAssociation(Object linkedObj) throws RecognitionException {
      		Object rel = entityWalker.peek();
      		if (rel == null) {
      			rel = EntityWalker.Relation.build(ComplexEntityNameBuilder.generateRootId(modName), EntityWalker.REL.NONE, null);
      		}
        		if (rel != null) {
        			try {
        				Object linkingObjId = ((EntityWalker.Relation)rel).getObj();
        				if (codeGenerator != null) codeGenerator.linkObjects(linkingObjId, linkedObj);
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
    		throw new RecognitionException(new ANTLRStringStream(e.getMessage()));
    	}
    		



    // $ANTLR start "filedef"
    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:184:1: filedef : ( props )? ( include ( include )* )? ( module )? EOF ;
    public final void filedef() throws RecognitionException {
        try {
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:185:5: ( ( props )? ( include ( include )* )? ( module )? EOF )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:185:7: ( props )? ( include ( include )* )? ( module )? EOF
            {
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:185:7: ( props )?
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0==58) ) {
                alt1=1;
            }
            switch (alt1) {
                case 1 :
                    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:185:7: props
                    {
                    pushFollow(FOLLOW_props_in_filedef356);
                    props();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }


            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:185:14: ( include ( include )* )?
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==55) ) {
                alt3=1;
            }
            switch (alt3) {
                case 1 :
                    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:185:15: include ( include )*
                    {
                    pushFollow(FOLLOW_include_in_filedef360);
                    include();

                    state._fsp--;
                    if (state.failed) return ;

                    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:185:23: ( include )*
                    loop2:
                    do {
                        int alt2=2;
                        int LA2_0 = input.LA(1);

                        if ( (LA2_0==55) ) {
                            alt2=1;
                        }


                        switch (alt2) {
                    	case 1 :
                    	    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:185:24: include
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


            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:185:36: ( module )?
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==57) ) {
                alt4=1;
            }
            switch (alt4) {
                case 1 :
                    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:185:36: module
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
    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:206:1: include : include_vwml ;
    public final void include() throws RecognitionException {
        String include_vwml1 =null;


        try {
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:207:5: ( include_vwml )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:207:7: include_vwml
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
    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:215:1: include_vwml returns [String id] : 'include' STRING_LITERAL ;
    public final String include_vwml() throws RecognitionException {
        String id = null;


        Token STRING_LITERAL2=null;

        try {
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:216:5: ( 'include' STRING_LITERAL )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:216:8: 'include' STRING_LITERAL
            {
            match(input,55,FOLLOW_55_in_include_vwml421); if (state.failed) return id;

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
    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:219:1: props : 'options' '{' optionsList '}' ;
    public final void props() throws RecognitionException {
        try {
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:220:5: ( 'options' '{' optionsList '}' )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:220:7: 'options' '{' optionsList '}'
            {
            match(input,58,FOLLOW_58_in_props442); if (state.failed) return ;

            match(input,63,FOLLOW_63_in_props444); if (state.failed) return ;

            pushFollow(FOLLOW_optionsList_in_props446);
            optionsList();

            state._fsp--;
            if (state.failed) return ;

            match(input,64,FOLLOW_64_in_props448); if (state.failed) return ;

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
    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:223:1: optionsList : lang ;
    public final void optionsList() throws RecognitionException {
        try {
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:224:5: ( lang )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:224:7: lang
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
    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:227:1: lang : ( ( 'language' '=' JAVA )=> langJava | otherLanguages );
    public final void lang() throws RecognitionException {
        try {
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:228:5: ( ( 'language' '=' JAVA )=> langJava | otherLanguages )
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==56) && (synpred1_VirtualWorldModelingLanguage())) {
                alt5=1;
            }
            else if ( (LA5_0==64) ) {
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
                    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:228:7: ( 'language' '=' JAVA )=> langJava
                    {
                    pushFollow(FOLLOW_langJava_in_lang496);
                    langJava();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:229:7: otherLanguages
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
    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:232:1: otherLanguages :;
    public final void otherLanguages() throws RecognitionException {
        try {
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:233:5: ()
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:234:5: 
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
    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:236:1: langJava : 'language' '=' JAVA '{' javaProps '}' ;
    public final void langJava() throws RecognitionException {

               codeGenerator = vwmlModelBuilder.getCodeGenerator(VWMLModelBuilder.SINK_TYPE.JAVA);
               if (logger.isDebugEnabled()) {
               		logger.debug("Code generator '" + codeGenerator + "'");
               }
            
        try {
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:243:5: ( 'language' '=' JAVA '{' javaProps '}' )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:243:7: 'language' '=' JAVA '{' javaProps '}'
            {
            match(input,56,FOLLOW_56_in_langJava547); if (state.failed) return ;

            match(input,48,FOLLOW_48_in_langJava549); if (state.failed) return ;

            match(input,JAVA,FOLLOW_JAVA_in_langJava551); if (state.failed) return ;

            match(input,63,FOLLOW_63_in_langJava553); if (state.failed) return ;

            pushFollow(FOLLOW_javaProps_in_langJava555);
            javaProps();

            state._fsp--;
            if (state.failed) return ;

            match(input,64,FOLLOW_64_in_langJava557); if (state.failed) return ;

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
    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:246:1: javaProps : propPackage ( generatedFileLocation )? optionalProps ;
    public final void javaProps() throws RecognitionException {

        	// instantiating module's properties which will be filled later
        	modProps = (codeGenerator != null) ? codeGenerator.buildProps() : null;
        	// tell to builder reference to module's properties
        	if (vwmlModelBuilder.getProjectProps() == null) {
        		vwmlModelBuilder.setProjectProps(modProps);
        	}
            
        try {
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:255:5: ( propPackage ( generatedFileLocation )? optionalProps )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:255:7: propPackage ( generatedFileLocation )? optionalProps
            {
            pushFollow(FOLLOW_propPackage_in_javaProps583);
            propPackage();

            state._fsp--;
            if (state.failed) return ;

            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:255:19: ( generatedFileLocation )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==60) ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:255:19: generatedFileLocation
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
    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:258:1: propPackage : 'package' '=' packageName ;
    public final void propPackage() throws RecognitionException {
        VirtualWorldModelingLanguageParser.packageName_return packageName3 =null;


        try {
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:259:5: ( 'package' '=' packageName )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:259:7: 'package' '=' packageName
            {
            match(input,59,FOLLOW_59_in_propPackage610); if (state.failed) return ;

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
    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:266:1: packageName : STRING_LITERAL ;
    public final VirtualWorldModelingLanguageParser.packageName_return packageName() throws RecognitionException {
        VirtualWorldModelingLanguageParser.packageName_return retval = new VirtualWorldModelingLanguageParser.packageName_return();
        retval.start = input.LT(1);


        try {
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:267:5: ( STRING_LITERAL )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:267:7: STRING_LITERAL
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
    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:270:1: generatedFileLocation : 'path' '=' path ;
    public final void generatedFileLocation() throws RecognitionException {
        VirtualWorldModelingLanguageParser.path_return path4 =null;


        try {
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:271:5: ( 'path' '=' path )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:271:7: 'path' '=' path
            {
            match(input,60,FOLLOW_60_in_generatedFileLocation650); if (state.failed) return ;

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
    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:278:1: optionalProps : ( author )? ( projname )? ( description )? ( visualizer )? ;
    public final void optionalProps() throws RecognitionException {
        try {
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:279:5: ( ( author )? ( projname )? ( description )? ( visualizer )? )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:279:7: ( author )? ( projname )? ( description )? ( visualizer )?
            {
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:279:7: ( author )?
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==51) ) {
                alt7=1;
            }
            switch (alt7) {
                case 1 :
                    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:279:7: author
                    {
                    pushFollow(FOLLOW_author_in_optionalProps674);
                    author();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }


            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:279:15: ( projname )?
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==61) ) {
                alt8=1;
            }
            switch (alt8) {
                case 1 :
                    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:279:15: projname
                    {
                    pushFollow(FOLLOW_projname_in_optionalProps677);
                    projname();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }


            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:279:25: ( description )?
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==54) ) {
                alt9=1;
            }
            switch (alt9) {
                case 1 :
                    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:279:25: description
                    {
                    pushFollow(FOLLOW_description_in_optionalProps680);
                    description();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }


            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:279:38: ( visualizer )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==62) ) {
                alt10=1;
            }
            switch (alt10) {
                case 1 :
                    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:279:38: visualizer
                    {
                    pushFollow(FOLLOW_visualizer_in_optionalProps683);
                    visualizer();

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
    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:282:1: author : 'author' '=' string ;
    public final void author() throws RecognitionException {
        VirtualWorldModelingLanguageParser.string_return string5 =null;


        try {
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:283:5: ( 'author' '=' string )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:283:7: 'author' '=' string
            {
            match(input,51,FOLLOW_51_in_author701); if (state.failed) return ;

            match(input,48,FOLLOW_48_in_author703); if (state.failed) return ;

            pushFollow(FOLLOW_string_in_author705);
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
    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:290:1: projname : 'project_name' '=' string ;
    public final void projname() throws RecognitionException {
        VirtualWorldModelingLanguageParser.string_return string6 =null;


        try {
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:291:5: ( 'project_name' '=' string )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:291:7: 'project_name' '=' string
            {
            match(input,61,FOLLOW_61_in_projname724); if (state.failed) return ;

            match(input,48,FOLLOW_48_in_projname726); if (state.failed) return ;

            pushFollow(FOLLOW_string_in_projname728);
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
    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:298:1: description : 'description' '=' string ;
    public final void description() throws RecognitionException {
        VirtualWorldModelingLanguageParser.string_return string7 =null;


        try {
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:299:5: ( 'description' '=' string )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:299:7: 'description' '=' string
            {
            match(input,54,FOLLOW_54_in_description751); if (state.failed) return ;

            match(input,48,FOLLOW_48_in_description753); if (state.failed) return ;

            pushFollow(FOLLOW_string_in_description755);
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



    // $ANTLR start "visualizer"
    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:307:1: visualizer : 'visualizer' '{' visualizer_body '}' ;
    public final void visualizer() throws RecognitionException {
        try {
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:308:5: ( 'visualizer' '{' visualizer_body '}' )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:308:7: 'visualizer' '{' visualizer_body '}'
            {
            match(input,62,FOLLOW_62_in_visualizer775); if (state.failed) return ;

            match(input,63,FOLLOW_63_in_visualizer777); if (state.failed) return ;

            pushFollow(FOLLOW_visualizer_body_in_visualizer779);
            visualizer_body();

            state._fsp--;
            if (state.failed) return ;

            match(input,64,FOLLOW_64_in_visualizer781); if (state.failed) return ;

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
    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:311:1: visualizer_body : ( visualizer_class visualizer_datapath |);
    public final void visualizer_body() throws RecognitionException {
        try {
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:312:5: ( visualizer_class visualizer_datapath |)
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==52) ) {
                alt11=1;
            }
            else if ( (LA11_0==64) ) {
                alt11=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 11, 0, input);

                throw nvae;

            }
            switch (alt11) {
                case 1 :
                    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:312:7: visualizer_class visualizer_datapath
                    {
                    pushFollow(FOLLOW_visualizer_class_in_visualizer_body799);
                    visualizer_class();

                    state._fsp--;
                    if (state.failed) return ;

                    pushFollow(FOLLOW_visualizer_datapath_in_visualizer_body801);
                    visualizer_datapath();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:314:5: 
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
    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:316:1: visualizer_class : 'class' '=' string ;
    public final void visualizer_class() throws RecognitionException {
        VirtualWorldModelingLanguageParser.string_return string8 =null;


        try {
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:317:5: ( 'class' '=' string )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:317:7: 'class' '=' string
            {
            match(input,52,FOLLOW_52_in_visualizer_class824); if (state.failed) return ;

            match(input,48,FOLLOW_48_in_visualizer_class826); if (state.failed) return ;

            pushFollow(FOLLOW_string_in_visualizer_class828);
            string8=string();

            state._fsp--;
            if (state.failed) return ;

            if ( state.backtracking==0 ) { 
                				if (modProps != null) {
                					((JavaCodeGenerator.JavaModuleStartProps)modProps).setVisitor((IVWMLLinkVisitor)GeneralUtils.instantiateClassThroughStaticMethod(GeneralUtils.trimQuotes((string8!=null?input.toString(string8.start,string8.stop):null)), "instance"));
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
    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:324:1: visualizer_datapath : 'data' '=' string ;
    public final void visualizer_datapath() throws RecognitionException {
        VirtualWorldModelingLanguageParser.string_return string9 =null;


        try {
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:325:5: ( 'data' '=' string )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:325:7: 'data' '=' string
            {
            match(input,53,FOLLOW_53_in_visualizer_datapath851); if (state.failed) return ;

            match(input,48,FOLLOW_48_in_visualizer_datapath853); if (state.failed) return ;

            pushFollow(FOLLOW_string_in_visualizer_datapath855);
            string9=string();

            state._fsp--;
            if (state.failed) return ;

            if ( state.backtracking==0 ) { 
                				if (modProps != null) {
                					((JavaCodeGenerator.JavaModuleStartProps)modProps).setVisitorDataPath(GeneralUtils.trimQuotes((string9!=null?input.toString(string9.start,string9.stop):null)));
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
    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:332:1: path : STRING_LITERAL ;
    public final VirtualWorldModelingLanguageParser.path_return path() throws RecognitionException {
        VirtualWorldModelingLanguageParser.path_return retval = new VirtualWorldModelingLanguageParser.path_return();
        retval.start = input.LT(1);


        try {
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:333:5: ( STRING_LITERAL )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:333:7: STRING_LITERAL
            {
            match(input,STRING_LITERAL,FOLLOW_STRING_LITERAL_in_path878); if (state.failed) return retval;

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



    // $ANTLR start "module"
    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:336:1: module : 'module' ID body ;
    public final void module() throws RecognitionException {
        Token ID10=null;

        try {
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:337:5: ( 'module' ID body )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:337:7: 'module' ID body
            {
            match(input,57,FOLLOW_57_in_module895); if (state.failed) return ;

            ID10=(Token)match(input,ID,FOLLOW_ID_in_module897); if (state.failed) return ;

            if ( state.backtracking==0 ) { 
                			modName = ID10.getText();
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

            pushFollow(FOLLOW_body_in_module901);
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
    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:365:1: body : '{' ( expression ( expression )* )? '}' ;
    public final void body() throws RecognitionException {
        try {
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:366:4: ( '{' ( expression ( expression )* )? '}' )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:366:6: '{' ( expression ( expression )* )? '}'
            {
            match(input,63,FOLLOW_63_in_body921); if (state.failed) return ;

            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:366:10: ( expression ( expression )* )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==ID||LA13_0==46||LA13_0==49) ) {
                alt13=1;
            }
            switch (alt13) {
                case 1 :
                    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:366:11: expression ( expression )*
                    {
                    pushFollow(FOLLOW_expression_in_body924);
                    expression();

                    state._fsp--;
                    if (state.failed) return ;

                    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:366:22: ( expression )*
                    loop12:
                    do {
                        int alt12=2;
                        int LA12_0 = input.LA(1);

                        if ( (LA12_0==ID||LA12_0==46||LA12_0==49) ) {
                            alt12=1;
                        }


                        switch (alt12) {
                    	case 1 :
                    	    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:366:23: expression
                    	    {
                    	    pushFollow(FOLLOW_expression_in_body927);
                    	    expression();

                    	    state._fsp--;
                    	    if (state.failed) return ;

                    	    }
                    	    break;

                    	default :
                    	    break loop12;
                        }
                    } while (true);


                    }
                    break;

            }


            match(input,64,FOLLOW_64_in_body933); if (state.failed) return ;

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
    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:370:1: expression : ( ( entity_decl IAS )=> entity_def | term_def );
    public final void expression() throws RecognitionException {
        try {
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:371:5: ( ( entity_decl IAS )=> entity_def | term_def )
            int alt14=2;
            switch ( input.LA(1) ) {
            case ID:
                {
                int LA14_1 = input.LA(2);

                if ( (synpred2_VirtualWorldModelingLanguage()) ) {
                    alt14=1;
                }
                else if ( (true) ) {
                    alt14=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return ;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 14, 1, input);

                    throw nvae;

                }
                }
                break;
            case 46:
                {
                int LA14_2 = input.LA(2);

                if ( (synpred2_VirtualWorldModelingLanguage()) ) {
                    alt14=1;
                }
                else if ( (true) ) {
                    alt14=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return ;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 14, 2, input);

                    throw nvae;

                }
                }
                break;
            case 49:
                {
                alt14=2;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 14, 0, input);

                throw nvae;

            }

            switch (alt14) {
                case 1 :
                    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:371:7: ( entity_decl IAS )=> entity_def
                    {
                    pushFollow(FOLLOW_entity_def_in_expression959);
                    entity_def();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:372:7: term_def
                    {
                    pushFollow(FOLLOW_term_def_in_expression967);
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
    // $ANTLR end "expression"



    // $ANTLR start "entity_def"
    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:375:1: entity_def : entity_decl IAS term ( SEMICOLON )? ;
    public final void entity_def() throws RecognitionException {
        String entity_decl11 =null;


        try {
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:376:5: ( entity_decl IAS term ( SEMICOLON )? )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:376:7: entity_decl IAS term ( SEMICOLON )?
            {
            pushFollow(FOLLOW_entity_decl_in_entity_def984);
            entity_decl11=entity_decl();

            state._fsp--;
            if (state.failed) return ;

            match(input,IAS,FOLLOW_IAS_in_entity_def986); if (state.failed) return ;

            if ( state.backtracking==0 ) {
                			entityWalker.markFutureEntityAsIAS(entity_decl11);
                			// we should link this entity with parent, if exists
                			 buildLinkingAssociation(entity_decl11);
                		      }

            pushFollow(FOLLOW_term_in_entity_def990);
            term();

            state._fsp--;
            if (state.failed) return ;

            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:380:20: ( SEMICOLON )?
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==SEMICOLON) ) {
                alt15=1;
            }
            switch (alt15) {
                case 1 :
                    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:380:21: SEMICOLON
                    {
                    match(input,SEMICOLON,FOLLOW_SEMICOLON_in_entity_def993); if (state.failed) return ;

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



    // $ANTLR start "term_def"
    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:383:1: term_def : entity ( oplist )* ;
    public final void term_def() throws RecognitionException {
        EntityWalker.Relation entity12 =null;


        try {
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:384:5: ( entity ( oplist )* )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:384:7: entity ( oplist )*
            {
            pushFollow(FOLLOW_entity_in_term_def1012);
            entity12=entity();

            state._fsp--;
            if (state.failed) return ;

            if ( state.backtracking==0 ) {
                		lastProcessedEntity = entity12;
                		lastProcessedEntityAsTerm = false;
                	     }

            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:387:13: ( oplist )*
            loop16:
            do {
                int alt16=2;
                int LA16_0 = input.LA(1);

                if ( ((LA16_0 >= OPBEGIN && LA16_0 <= OPSUBSTRUCT)) ) {
                    alt16=1;
                }


                switch (alt16) {
            	case 1 :
            	    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:387:14: oplist
            	    {
            	    pushFollow(FOLLOW_oplist_in_term_def1017);
            	    oplist();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop16;
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
    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:403:1: entity_decl returns [String id] : ( simple_entity_decl | '(' complex_entity_decl ')' );
    public final String entity_decl() throws RecognitionException {
        String id = null;


        String simple_entity_decl13 =null;


        try {
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:404:5: ( simple_entity_decl | '(' complex_entity_decl ')' )
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==ID) ) {
                alt17=1;
            }
            else if ( (LA17_0==46) ) {
                alt17=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return id;}
                NoViableAltException nvae =
                    new NoViableAltException("", 17, 0, input);

                throw nvae;

            }
            switch (alt17) {
                case 1 :
                    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:404:7: simple_entity_decl
                    {
                    pushFollow(FOLLOW_simple_entity_decl_in_entity_decl1051);
                    simple_entity_decl13=simple_entity_decl();

                    state._fsp--;
                    if (state.failed) return id;

                    if ( state.backtracking==0 ) {id = simple_entity_decl13;}

                    }
                    break;
                case 2 :
                    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:405:7: '(' complex_entity_decl ')'
                    {
                    match(input,46,FOLLOW_46_in_entity_decl1062); if (state.failed) return id;

                    if ( state.backtracking==0 ) {
                        	    	complexEntityNameBuilder.startProgress();
                    	    	if (logger.isDebugEnabled()) {
                    	    		logger.debug("complex entity declaration process - started");
                    	    	}
                              }

                    pushFollow(FOLLOW_complex_entity_decl_in_entity_decl1067);
                    complex_entity_decl();

                    state._fsp--;
                    if (state.failed) return id;

                    match(input,47,FOLLOW_47_in_entity_decl1069); if (state.failed) return id;

                    if ( state.backtracking==0 ) {
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
    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:426:1: compound_entity_decl : entity_decl ;
    public final void compound_entity_decl() throws RecognitionException {
        try {
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:427:5: ( entity_decl )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:427:7: entity_decl
            {
            pushFollow(FOLLOW_entity_decl_in_compound_entity_decl1088);
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
    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:430:1: simple_entity_decl returns [String id] : ID ;
    public final String simple_entity_decl() throws RecognitionException {
        String id = null;


        Token ID14=null;

        try {
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:431:5: ( ID )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:431:7: ID
            {
            ID14=(Token)match(input,ID,FOLLOW_ID_in_simple_entity_decl1113); if (state.failed) return id;

            if ( state.backtracking==0 ) {
                		id = ID14.getText();
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
    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:454:1: complex_entity_decl : ( compound_entity_decl )+ ;
    public final void complex_entity_decl() throws RecognitionException {
        try {
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:458:5: ( ( compound_entity_decl )+ )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:458:7: ( compound_entity_decl )+
            {
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:458:7: ( compound_entity_decl )+
            int cnt18=0;
            loop18:
            do {
                int alt18=2;
                int LA18_0 = input.LA(1);

                if ( (LA18_0==ID||LA18_0==46) ) {
                    alt18=1;
                }


                switch (alt18) {
            	case 1 :
            	    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:458:8: compound_entity_decl
            	    {
            	    pushFollow(FOLLOW_compound_entity_decl_in_complex_entity_decl1146);
            	    compound_entity_decl();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    if ( cnt18 >= 1 ) break loop18;
            	    if (state.backtracking>0) {state.failed=true; return ;}
                        EarlyExitException eee =
                            new EarlyExitException(18, input);
                        throw eee;
                }
                cnt18++;
            } while (true);


            }

            if ( state.backtracking==0 ) {
                	complexEntityNameBuilder.stopProgress();
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
    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:461:1: term : expression ;
    public final void term() throws RecognitionException {
        try {
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:462:5: ( expression )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:462:7: expression
            {
            pushFollow(FOLLOW_expression_in_term1165);
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
    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:465:1: entity returns [EntityWalker.Relation rel] : ( simple_entity | complex_entity | syncronization_entity );
    public final EntityWalker.Relation entity() throws RecognitionException {
        EntityWalker.Relation rel = null;


        EntityWalker.Relation simple_entity15 =null;

        EntityWalker.Relation complex_entity16 =null;

        EntityWalker.Relation syncronization_entity17 =null;


        try {
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:466:5: ( simple_entity | complex_entity | syncronization_entity )
            int alt19=3;
            switch ( input.LA(1) ) {
            case ID:
                {
                alt19=1;
                }
                break;
            case 46:
                {
                alt19=2;
                }
                break;
            case 49:
                {
                alt19=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return rel;}
                NoViableAltException nvae =
                    new NoViableAltException("", 19, 0, input);

                throw nvae;

            }

            switch (alt19) {
                case 1 :
                    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:466:7: simple_entity
                    {
                    pushFollow(FOLLOW_simple_entity_in_entity1188);
                    simple_entity15=simple_entity();

                    state._fsp--;
                    if (state.failed) return rel;

                    if ( state.backtracking==0 ) {rel = simple_entity15; }

                    }
                    break;
                case 2 :
                    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:467:7: complex_entity
                    {
                    pushFollow(FOLLOW_complex_entity_in_entity1206);
                    complex_entity16=complex_entity();

                    state._fsp--;
                    if (state.failed) return rel;

                    if ( state.backtracking==0 ) {rel = complex_entity16;}

                    }
                    break;
                case 3 :
                    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:468:7: syncronization_entity
                    {
                    pushFollow(FOLLOW_syncronization_entity_in_entity1223);
                    syncronization_entity17=syncronization_entity();

                    state._fsp--;
                    if (state.failed) return rel;

                    if ( state.backtracking==0 ) {rel = syncronization_entity17;}

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
    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:472:1: syncronization_entity returns [EntityWalker.Relation rel] : '[' entity ']' ;
    public final EntityWalker.Relation syncronization_entity() throws RecognitionException {
        EntityWalker.Relation rel = null;


        EntityWalker.Relation entity18 =null;


        try {
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:473:5: ( '[' entity ']' )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:473:7: '[' entity ']'
            {
            match(input,49,FOLLOW_49_in_syncronization_entity1247); if (state.failed) return rel;

            pushFollow(FOLLOW_entity_in_syncronization_entity1249);
            entity18=entity();

            state._fsp--;
            if (state.failed) return rel;

            if ( state.backtracking==0 ) {rel = entity18; }

            match(input,50,FOLLOW_50_in_syncronization_entity1253); if (state.failed) return rel;

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
    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:476:1: simple_entity returns [EntityWalker.Relation rel] : ID ;
    public final EntityWalker.Relation simple_entity() throws RecognitionException {
        EntityWalker.Relation rel = null;


        Token ID19=null;

        try {
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:477:5: ( ID )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:477:7: ID
            {
            ID19=(Token)match(input,ID,FOLLOW_ID_in_simple_entity1275); if (state.failed) return rel;

            if ( state.backtracking==0 ) {
               		rel = buildRelation((ID19!=null?ID19.getText():null));
                		if (logger.isDebugEnabled()) {
                			logger.debug("processed simple entity '" + rel + "'");
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
    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:485:1: complex_entity returns [EntityWalker.Relation rel] : '(' ( term )* ')' ;
    public final EntityWalker.Relation complex_entity() throws RecognitionException {
        EntityWalker.Relation rel = null;



            	// id and name is the same
            	String ceId = ComplexEntityNameBuilder.generateRandomName();
            	try {
            		if (codeGenerator != null) codeGenerator.declareComplexEntity(ceId);
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
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:509:5: ( '(' ( term )* ')' )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:509:7: '(' ( term )* ')'
            {
            match(input,46,FOLLOW_46_in_complex_entity1316); if (state.failed) return rel;

            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:509:11: ( term )*
            loop20:
            do {
                int alt20=2;
                int LA20_0 = input.LA(1);

                if ( (LA20_0==ID||LA20_0==46||LA20_0==49) ) {
                    alt20=1;
                }


                switch (alt20) {
            	case 1 :
            	    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:509:12: term
            	    {
            	    pushFollow(FOLLOW_term_in_complex_entity1319);
            	    term();

            	    state._fsp--;
            	    if (state.failed) return rel;

            	    }
            	    break;

            	default :
            	    break loop20;
                }
            } while (true);


            match(input,47,FOLLOW_47_in_complex_entity1323); if (state.failed) return rel;

            }

            if ( state.backtracking==0 ) {
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
    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:520:1: oplist : ( opclist | opprojection );
    public final void oplist() throws RecognitionException {
        VirtualWorldModelingLanguageParser.opclist_return opclist20 =null;

        VirtualWorldModelingLanguageParser.opprojection_return opprojection21 =null;


        try {
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:522:5: ( opclist | opprojection )
            int alt21=2;
            int LA21_0 = input.LA(1);

            if ( ((LA21_0 >= OPBEGIN && LA21_0 <= OPLAST)||(LA21_0 >= OPRANDOM && LA21_0 <= OPSUBSTRUCT)) ) {
                alt21=1;
            }
            else if ( ((LA21_0 >= OPPROJECTION_1 && LA21_0 <= OPPROJECTION_9)) ) {
                alt21=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 21, 0, input);

                throw nvae;

            }
            switch (alt21) {
                case 1 :
                    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:522:7: opclist
                    {
                    pushFollow(FOLLOW_opclist_in_oplist1406);
                    opclist20=opclist();

                    state._fsp--;
                    if (state.failed) return ;

                    if ( state.backtracking==0 ) { if (lastProcessedEntity != null && codeGenerator != null) { lastProcessedEntityAsTerm = true; codeGenerator.associateOperation(lastProcessedEntity, (opclist20!=null?input.toString(opclist20.start,opclist20.stop):null)); } }

                    }
                    break;
                case 2 :
                    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:523:7: opprojection
                    {
                    pushFollow(FOLLOW_opprojection_in_oplist1422);
                    opprojection21=opprojection();

                    state._fsp--;
                    if (state.failed) return ;

                    if ( state.backtracking==0 ) { if (lastProcessedEntity != null && codeGenerator != null) { lastProcessedEntityAsTerm = true; codeGenerator.associateOperation(lastProcessedEntity, (opprojection21!=null?input.toString(opprojection21.start,opprojection21.stop):null)); } }

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
    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:526:1: opclist : ( OPJOIN | OPINTERSECT | OPSUBSTRUCT | OPFIRST | OPLAST | OPBEGIN | OPREST | OPCARTESIAN | OPIN | OPINCL | OPEQ | OPIDENT | OPSQU | OPINTERPRET | OPCREATEEXPR | OPEXECUTE | OPRANDOM );
    public final VirtualWorldModelingLanguageParser.opclist_return opclist() throws RecognitionException {
        VirtualWorldModelingLanguageParser.opclist_return retval = new VirtualWorldModelingLanguageParser.opclist_return();
        retval.start = input.LT(1);


        try {
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:527:5: ( OPJOIN | OPINTERSECT | OPSUBSTRUCT | OPFIRST | OPLAST | OPBEGIN | OPREST | OPCARTESIAN | OPIN | OPINCL | OPEQ | OPIDENT | OPSQU | OPINTERPRET | OPCREATEEXPR | OPEXECUTE | OPRANDOM )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:
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
    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:546:1: opprojection : ( OPPROJECTION_1 | OPPROJECTION_2 | OPPROJECTION_3 | OPPROJECTION_4 | OPPROJECTION_5 | OPPROJECTION_6 | OPPROJECTION_7 | OPPROJECTION_8 | OPPROJECTION_9 );
    public final VirtualWorldModelingLanguageParser.opprojection_return opprojection() throws RecognitionException {
        VirtualWorldModelingLanguageParser.opprojection_return retval = new VirtualWorldModelingLanguageParser.opprojection_return();
        retval.start = input.LT(1);


        try {
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:547:5: ( OPPROJECTION_1 | OPPROJECTION_2 | OPPROJECTION_3 | OPPROJECTION_4 | OPPROJECTION_5 | OPPROJECTION_6 | OPPROJECTION_7 | OPPROJECTION_8 | OPPROJECTION_9 )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:
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
    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:558:1: termLanguages : ( JAVA | C | CPP | OBJECTIVEC );
    public final void termLanguages() throws RecognitionException {
        try {
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:559:5: ( JAVA | C | CPP | OBJECTIVEC )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:
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
    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:565:1: string : STRING_LITERAL ;
    public final VirtualWorldModelingLanguageParser.string_return string() throws RecognitionException {
        VirtualWorldModelingLanguageParser.string_return retval = new VirtualWorldModelingLanguageParser.string_return();
        retval.start = input.LT(1);


        try {
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:566:5: ( STRING_LITERAL )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:566:7: STRING_LITERAL
            {
            match(input,STRING_LITERAL,FOLLOW_STRING_LITERAL_in_string1709); if (state.failed) return retval;

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
        // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:228:7: ( 'language' '=' JAVA )
        // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:228:8: 'language' '=' JAVA
        {
        match(input,56,FOLLOW_56_in_synpred1_VirtualWorldModelingLanguage487); if (state.failed) return ;

        match(input,48,FOLLOW_48_in_synpred1_VirtualWorldModelingLanguage489); if (state.failed) return ;

        match(input,JAVA,FOLLOW_JAVA_in_synpred1_VirtualWorldModelingLanguage491); if (state.failed) return ;

        }

    }
    // $ANTLR end synpred1_VirtualWorldModelingLanguage

    // $ANTLR start synpred2_VirtualWorldModelingLanguage
    public final void synpred2_VirtualWorldModelingLanguage_fragment() throws RecognitionException {
        // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:371:7: ( entity_decl IAS )
        // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:371:8: entity_decl IAS
        {
        pushFollow(FOLLOW_entity_decl_in_synpred2_VirtualWorldModelingLanguage952);
        entity_decl();

        state._fsp--;
        if (state.failed) return ;

        match(input,IAS,FOLLOW_IAS_in_synpred2_VirtualWorldModelingLanguage954); if (state.failed) return ;

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


 

    public static final BitSet FOLLOW_props_in_filedef356 = new BitSet(new long[]{0x0280000000000000L});
    public static final BitSet FOLLOW_include_in_filedef360 = new BitSet(new long[]{0x0280000000000000L});
    public static final BitSet FOLLOW_include_in_filedef363 = new BitSet(new long[]{0x0280000000000000L});
    public static final BitSet FOLLOW_module_in_filedef369 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_filedef372 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_include_vwml_in_include393 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_55_in_include_vwml421 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_STRING_LITERAL_in_include_vwml423 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_58_in_props442 = new BitSet(new long[]{0x8000000000000000L});
    public static final BitSet FOLLOW_63_in_props444 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_optionsList_in_props446 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_64_in_props448 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_lang_in_optionsList469 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_langJava_in_lang496 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_otherLanguages_in_lang504 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_56_in_langJava547 = new BitSet(new long[]{0x0001000000000000L});
    public static final BitSet FOLLOW_48_in_langJava549 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_JAVA_in_langJava551 = new BitSet(new long[]{0x8000000000000000L});
    public static final BitSet FOLLOW_63_in_langJava553 = new BitSet(new long[]{0x0800000000000000L});
    public static final BitSet FOLLOW_javaProps_in_langJava555 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_64_in_langJava557 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_propPackage_in_javaProps583 = new BitSet(new long[]{0x7048000000000000L});
    public static final BitSet FOLLOW_generatedFileLocation_in_javaProps585 = new BitSet(new long[]{0x6048000000000000L});
    public static final BitSet FOLLOW_optionalProps_in_javaProps588 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_59_in_propPackage610 = new BitSet(new long[]{0x0001000000000000L});
    public static final BitSet FOLLOW_48_in_propPackage612 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_packageName_in_propPackage614 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_LITERAL_in_packageName633 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_60_in_generatedFileLocation650 = new BitSet(new long[]{0x0001000000000000L});
    public static final BitSet FOLLOW_48_in_generatedFileLocation652 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_path_in_generatedFileLocation654 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_author_in_optionalProps674 = new BitSet(new long[]{0x6040000000000002L});
    public static final BitSet FOLLOW_projname_in_optionalProps677 = new BitSet(new long[]{0x4040000000000002L});
    public static final BitSet FOLLOW_description_in_optionalProps680 = new BitSet(new long[]{0x4000000000000002L});
    public static final BitSet FOLLOW_visualizer_in_optionalProps683 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_51_in_author701 = new BitSet(new long[]{0x0001000000000000L});
    public static final BitSet FOLLOW_48_in_author703 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_string_in_author705 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_61_in_projname724 = new BitSet(new long[]{0x0001000000000000L});
    public static final BitSet FOLLOW_48_in_projname726 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_string_in_projname728 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_54_in_description751 = new BitSet(new long[]{0x0001000000000000L});
    public static final BitSet FOLLOW_48_in_description753 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_string_in_description755 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_62_in_visualizer775 = new BitSet(new long[]{0x8000000000000000L});
    public static final BitSet FOLLOW_63_in_visualizer777 = new BitSet(new long[]{0x0010000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_visualizer_body_in_visualizer779 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_64_in_visualizer781 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_visualizer_class_in_visualizer_body799 = new BitSet(new long[]{0x0020000000000000L});
    public static final BitSet FOLLOW_visualizer_datapath_in_visualizer_body801 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_52_in_visualizer_class824 = new BitSet(new long[]{0x0001000000000000L});
    public static final BitSet FOLLOW_48_in_visualizer_class826 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_string_in_visualizer_class828 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_53_in_visualizer_datapath851 = new BitSet(new long[]{0x0001000000000000L});
    public static final BitSet FOLLOW_48_in_visualizer_datapath853 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_string_in_visualizer_datapath855 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_LITERAL_in_path878 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_57_in_module895 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_ID_in_module897 = new BitSet(new long[]{0x8000000000000000L});
    public static final BitSet FOLLOW_body_in_module901 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_63_in_body921 = new BitSet(new long[]{0x0002400000000400L,0x0000000000000001L});
    public static final BitSet FOLLOW_expression_in_body924 = new BitSet(new long[]{0x0002400000000400L,0x0000000000000001L});
    public static final BitSet FOLLOW_expression_in_body927 = new BitSet(new long[]{0x0002400000000400L,0x0000000000000001L});
    public static final BitSet FOLLOW_64_in_body933 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_entity_def_in_expression959 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_term_def_in_expression967 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_entity_decl_in_entity_def984 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_IAS_in_entity_def986 = new BitSet(new long[]{0x0002400000000400L});
    public static final BitSet FOLLOW_term_in_entity_def990 = new BitSet(new long[]{0x0000080000000002L});
    public static final BitSet FOLLOW_SEMICOLON_in_entity_def993 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_entity_in_term_def1012 = new BitSet(new long[]{0x000007FFFFFE0002L});
    public static final BitSet FOLLOW_oplist_in_term_def1017 = new BitSet(new long[]{0x000007FFFFFE0002L});
    public static final BitSet FOLLOW_simple_entity_decl_in_entity_decl1051 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_46_in_entity_decl1062 = new BitSet(new long[]{0x0000400000000400L});
    public static final BitSet FOLLOW_complex_entity_decl_in_entity_decl1067 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_47_in_entity_decl1069 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_entity_decl_in_compound_entity_decl1088 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_simple_entity_decl1113 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_compound_entity_decl_in_complex_entity_decl1146 = new BitSet(new long[]{0x0000400000000402L});
    public static final BitSet FOLLOW_expression_in_term1165 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simple_entity_in_entity1188 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_complex_entity_in_entity1206 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_syncronization_entity_in_entity1223 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_49_in_syncronization_entity1247 = new BitSet(new long[]{0x0002400000000400L});
    public static final BitSet FOLLOW_entity_in_syncronization_entity1249 = new BitSet(new long[]{0x0004000000000000L});
    public static final BitSet FOLLOW_50_in_syncronization_entity1253 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_simple_entity1275 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_46_in_complex_entity1316 = new BitSet(new long[]{0x0002C00000000400L});
    public static final BitSet FOLLOW_term_in_complex_entity1319 = new BitSet(new long[]{0x0002C00000000400L});
    public static final BitSet FOLLOW_47_in_complex_entity1323 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_opclist_in_oplist1406 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_opprojection_in_oplist1422 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_LITERAL_in_string1709 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_56_in_synpred1_VirtualWorldModelingLanguage487 = new BitSet(new long[]{0x0001000000000000L});
    public static final BitSet FOLLOW_48_in_synpred1_VirtualWorldModelingLanguage489 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_JAVA_in_synpred1_VirtualWorldModelingLanguage491 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_entity_decl_in_synpred2_VirtualWorldModelingLanguage952 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_IAS_in_synpred2_VirtualWorldModelingLanguage954 = new BitSet(new long[]{0x0000000000000002L});

}