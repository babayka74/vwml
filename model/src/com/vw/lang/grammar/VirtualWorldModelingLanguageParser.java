// $ANTLR 3.4 C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g 2013-08-08 10:46:10

package com.vw.lang.grammar;

import com.vw.lang.processor.model.builder.VWMLModelBuilder;
import com.vw.lang.sink.ICodeGenerator;
import com.vw.lang.sink.ICodeGenerator.StartModuleProps;
import com.vw.lang.sink.utils.ComplexEntityNameBuilder;
import com.vw.lang.sink.utils.EntityWalker;
import com.vw.lang.sink.utils.GeneralUtils;

import com.vw.lang.sink.java.link.IVWMLLinkVisitor;
import com.vw.lang.sink.java.code.JavaCodeGenerator;
import com.vw.lang.sink.java.code.JavaCodeGenerator.JavaModuleStartProps;

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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "C", "COMMA", "COMMENT", "CPP", "DQUOTE", "IAS", "ID", "JAVA", "LETTER", "LINE_COMMENT", "NATIVE_CODE", "NIL", "OBJECTIVEC", "OPBEGIN", "OPCARTESIAN", "OPCREATEEXPR", "OPEQ", "OPEXECUTE", "OPFIRST", "OPIDENT", "OPIN", "OPINCL", "OPINTERPRET", "OPINTERSECT", "OPJOIN", "OPLAST", "OPPROJECTION_1", "OPPROJECTION_2", "OPPROJECTION_3", "OPPROJECTION_4", "OPPROJECTION_5", "OPPROJECTION_6", "OPPROJECTION_7", "OPPROJECTION_8", "OPPROJECTION_9", "OPRANDOM", "OPREST", "OPSQU", "OPSUBSTRUCT", "SEMICOLON", "STRING_LITERAL", "WS", "'('", "')'", "'='", "'author'", "'class'", "'data'", "'description'", "'language'", "'module'", "'options'", "'package'", "'path'", "'visualizer'", "'{'", "'}'"
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
    	private String lastProcessedEntityId = null;
    	private String modName = null;
     	private boolean inDebug = false;
     	
     	private Logger logger = Logger.getLogger(this.getClass());
    	
    	public StartModuleProps getModuleProps() {
    		return modProps;
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
        			codeGenerator.interpretObjects(objLinkingId, objLinkedId);
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
        		if (linkingObjId != null) {
        			try {
        				codeGenerator.linkObjects(linkingObjId, linkedObj);
        				if (logger.isDebugEnabled()) {
        					logger.debug("Linked objects '" + linkingObjId + "' -> '" + linkedObj + "'");
        				}
        			}
        			catch(Exception e) {
        				rethrowVWMLExceptionAsRecognitionException(e);
        			}
        		}
    	}
    	
    	protected void rethrowVWMLExceptionAsRecognitionException(Exception e) throws RecognitionException {
    		throw new RecognitionException(new ANTLRStringStream(e.getMessage()));
    	}



    // $ANTLR start "filedef"
    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:140:1: filedef : ( props module | module );
    public final void filedef() throws RecognitionException {
        try {
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:141:5: ( props module | module )
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0==55) ) {
                alt1=1;
            }
            else if ( (LA1_0==54) ) {
                alt1=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 1, 0, input);

                throw nvae;

            }
            switch (alt1) {
                case 1 :
                    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:141:7: props module
                    {
                    pushFollow(FOLLOW_props_in_filedef356);
                    props();

                    state._fsp--;
                    if (state.failed) return ;

                    pushFollow(FOLLOW_module_in_filedef358);
                    module();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:142:7: module
                    {
                    pushFollow(FOLLOW_module_in_filedef366);
                    module();

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
    // $ANTLR end "filedef"



    // $ANTLR start "props"
    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:146:1: props : 'options' '{' optionsList '}' ;
    public final void props() throws RecognitionException {
        try {
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:147:5: ( 'options' '{' optionsList '}' )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:147:7: 'options' '{' optionsList '}'
            {
            match(input,55,FOLLOW_55_in_props386); if (state.failed) return ;

            match(input,59,FOLLOW_59_in_props388); if (state.failed) return ;

            pushFollow(FOLLOW_optionsList_in_props390);
            optionsList();

            state._fsp--;
            if (state.failed) return ;

            match(input,60,FOLLOW_60_in_props392); if (state.failed) return ;

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
    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:150:1: optionsList : lang ;
    public final void optionsList() throws RecognitionException {
        try {
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:151:5: ( lang )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:151:7: lang
            {
            pushFollow(FOLLOW_lang_in_optionsList413);
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
    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:154:1: lang : ( ( 'language' '=' JAVA )=> langJava | otherLanguages );
    public final void lang() throws RecognitionException {
        try {
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:155:5: ( ( 'language' '=' JAVA )=> langJava | otherLanguages )
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==53) && (synpred1_VirtualWorldModelingLanguage())) {
                alt2=1;
            }
            else if ( (LA2_0==60) ) {
                alt2=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;

            }
            switch (alt2) {
                case 1 :
                    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:155:7: ( 'language' '=' JAVA )=> langJava
                    {
                    pushFollow(FOLLOW_langJava_in_lang440);
                    langJava();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:156:7: otherLanguages
                    {
                    pushFollow(FOLLOW_otherLanguages_in_lang448);
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
    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:159:1: otherLanguages :;
    public final void otherLanguages() throws RecognitionException {
        try {
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:160:5: ()
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:161:5: 
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
    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:163:1: langJava : 'language' '=' JAVA '{' javaProps '}' ;
    public final void langJava() throws RecognitionException {

               codeGenerator = vwmlModelBuilder.getCodeGenerator(VWMLModelBuilder.SINK_TYPE.JAVA);
               if (logger.isDebugEnabled()) {
               		logger.debug("Code generator '" + codeGenerator + "'");
               }
            
        try {
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:170:5: ( 'language' '=' JAVA '{' javaProps '}' )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:170:7: 'language' '=' JAVA '{' javaProps '}'
            {
            match(input,53,FOLLOW_53_in_langJava491); if (state.failed) return ;

            match(input,48,FOLLOW_48_in_langJava493); if (state.failed) return ;

            match(input,JAVA,FOLLOW_JAVA_in_langJava495); if (state.failed) return ;

            match(input,59,FOLLOW_59_in_langJava497); if (state.failed) return ;

            pushFollow(FOLLOW_javaProps_in_langJava499);
            javaProps();

            state._fsp--;
            if (state.failed) return ;

            match(input,60,FOLLOW_60_in_langJava501); if (state.failed) return ;

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
    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:173:1: javaProps : propPackage generatedFileLocation optionalProps ;
    public final void javaProps() throws RecognitionException {

        	// instantiating module's properties which will be filled later
        	modProps = (codeGenerator != null) ? codeGenerator.buildProps() : null;
            
        try {
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:178:5: ( propPackage generatedFileLocation optionalProps )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:178:7: propPackage generatedFileLocation optionalProps
            {
            pushFollow(FOLLOW_propPackage_in_javaProps527);
            propPackage();

            state._fsp--;
            if (state.failed) return ;

            pushFollow(FOLLOW_generatedFileLocation_in_javaProps529);
            generatedFileLocation();

            state._fsp--;
            if (state.failed) return ;

            pushFollow(FOLLOW_optionalProps_in_javaProps531);
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
    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:181:1: propPackage : 'package' '=' packageName ;
    public final void propPackage() throws RecognitionException {
        VirtualWorldModelingLanguageParser.packageName_return packageName1 =null;


        try {
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:182:5: ( 'package' '=' packageName )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:182:7: 'package' '=' packageName
            {
            match(input,56,FOLLOW_56_in_propPackage553); if (state.failed) return ;

            match(input,48,FOLLOW_48_in_propPackage555); if (state.failed) return ;

            pushFollow(FOLLOW_packageName_in_propPackage557);
            packageName1=packageName();

            state._fsp--;
            if (state.failed) return ;

            if ( state.backtracking==0 ) {
            	    			  if (modProps != null) {
            	    				((JavaCodeGenerator.JavaModuleStartProps)modProps).setModulePackage(GeneralUtils.trimQuotes((packageName1!=null?input.toString(packageName1.start,packageName1.stop):null)));
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
    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:189:1: packageName : STRING_LITERAL ;
    public final VirtualWorldModelingLanguageParser.packageName_return packageName() throws RecognitionException {
        VirtualWorldModelingLanguageParser.packageName_return retval = new VirtualWorldModelingLanguageParser.packageName_return();
        retval.start = input.LT(1);


        try {
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:190:5: ( STRING_LITERAL )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:190:7: STRING_LITERAL
            {
            match(input,STRING_LITERAL,FOLLOW_STRING_LITERAL_in_packageName576); if (state.failed) return retval;

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
    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:193:1: generatedFileLocation : 'path' '=' path ;
    public final void generatedFileLocation() throws RecognitionException {
        VirtualWorldModelingLanguageParser.path_return path2 =null;


        try {
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:194:5: ( 'path' '=' path )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:194:7: 'path' '=' path
            {
            match(input,57,FOLLOW_57_in_generatedFileLocation593); if (state.failed) return ;

            match(input,48,FOLLOW_48_in_generatedFileLocation595); if (state.failed) return ;

            pushFollow(FOLLOW_path_in_generatedFileLocation597);
            path2=path();

            state._fsp--;
            if (state.failed) return ;

            if ( state.backtracking==0 ) {
                			if (modProps != null) {
                				((JavaCodeGenerator.JavaModuleStartProps)modProps).setSrcPath(GeneralUtils.trimQuotes((path2!=null?input.toString(path2.start,path2.stop):null)));
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
    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:201:1: optionalProps : ( author )? ( description )? ( visualizer )? ;
    public final void optionalProps() throws RecognitionException {
        try {
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:202:5: ( ( author )? ( description )? ( visualizer )? )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:202:7: ( author )? ( description )? ( visualizer )?
            {
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:202:7: ( author )?
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==49) ) {
                alt3=1;
            }
            switch (alt3) {
                case 1 :
                    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:202:7: author
                    {
                    pushFollow(FOLLOW_author_in_optionalProps617);
                    author();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }


            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:202:15: ( description )?
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==52) ) {
                alt4=1;
            }
            switch (alt4) {
                case 1 :
                    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:202:15: description
                    {
                    pushFollow(FOLLOW_description_in_optionalProps620);
                    description();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }


            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:202:28: ( visualizer )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==58) ) {
                alt5=1;
            }
            switch (alt5) {
                case 1 :
                    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:202:28: visualizer
                    {
                    pushFollow(FOLLOW_visualizer_in_optionalProps623);
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
    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:205:1: author : 'author' '=' string ;
    public final void author() throws RecognitionException {
        VirtualWorldModelingLanguageParser.string_return string3 =null;


        try {
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:206:5: ( 'author' '=' string )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:206:7: 'author' '=' string
            {
            match(input,49,FOLLOW_49_in_author641); if (state.failed) return ;

            match(input,48,FOLLOW_48_in_author643); if (state.failed) return ;

            pushFollow(FOLLOW_string_in_author645);
            string3=string();

            state._fsp--;
            if (state.failed) return ;

            if ( state.backtracking==0 ) {
            	    			if (modProps != null) {
            	    				((JavaCodeGenerator.JavaModuleStartProps)modProps).setAuthor(GeneralUtils.trimQuotes((string3!=null?input.toString(string3.start,string3.stop):null)));
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



    // $ANTLR start "description"
    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:213:1: description : 'description' '=' string ;
    public final void description() throws RecognitionException {
        VirtualWorldModelingLanguageParser.string_return string4 =null;


        try {
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:214:5: ( 'description' '=' string )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:214:7: 'description' '=' string
            {
            match(input,52,FOLLOW_52_in_description668); if (state.failed) return ;

            match(input,48,FOLLOW_48_in_description670); if (state.failed) return ;

            pushFollow(FOLLOW_string_in_description672);
            string4=string();

            state._fsp--;
            if (state.failed) return ;

            if ( state.backtracking==0 ) { 
                				if (modProps != null) {
                					((JavaCodeGenerator.JavaModuleStartProps)modProps).setDescription(GeneralUtils.trimQuotes((string4!=null?input.toString(string4.start,string4.stop):null)));
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
    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:221:1: visualizer : 'visualizer' '{' visualizer_body '}' ;
    public final void visualizer() throws RecognitionException {
        try {
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:222:5: ( 'visualizer' '{' visualizer_body '}' )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:222:7: 'visualizer' '{' visualizer_body '}'
            {
            match(input,58,FOLLOW_58_in_visualizer691); if (state.failed) return ;

            match(input,59,FOLLOW_59_in_visualizer693); if (state.failed) return ;

            pushFollow(FOLLOW_visualizer_body_in_visualizer695);
            visualizer_body();

            state._fsp--;
            if (state.failed) return ;

            match(input,60,FOLLOW_60_in_visualizer697); if (state.failed) return ;

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
    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:225:1: visualizer_body : ( visualizer_class visualizer_datapath |);
    public final void visualizer_body() throws RecognitionException {
        try {
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:226:5: ( visualizer_class visualizer_datapath |)
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==50) ) {
                alt6=1;
            }
            else if ( (LA6_0==60) ) {
                alt6=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 6, 0, input);

                throw nvae;

            }
            switch (alt6) {
                case 1 :
                    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:226:7: visualizer_class visualizer_datapath
                    {
                    pushFollow(FOLLOW_visualizer_class_in_visualizer_body715);
                    visualizer_class();

                    state._fsp--;
                    if (state.failed) return ;

                    pushFollow(FOLLOW_visualizer_datapath_in_visualizer_body717);
                    visualizer_datapath();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:228:5: 
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
    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:230:1: visualizer_class : 'class' '=' string ;
    public final void visualizer_class() throws RecognitionException {
        VirtualWorldModelingLanguageParser.string_return string5 =null;


        try {
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:231:5: ( 'class' '=' string )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:231:7: 'class' '=' string
            {
            match(input,50,FOLLOW_50_in_visualizer_class740); if (state.failed) return ;

            match(input,48,FOLLOW_48_in_visualizer_class742); if (state.failed) return ;

            pushFollow(FOLLOW_string_in_visualizer_class744);
            string5=string();

            state._fsp--;
            if (state.failed) return ;

            if ( state.backtracking==0 ) { 
                				if (modProps != null) {
                					((JavaCodeGenerator.JavaModuleStartProps)modProps).setVisitor((IVWMLLinkVisitor)GeneralUtils.instantiateClassThroughStaticMethod(GeneralUtils.trimQuotes((string5!=null?input.toString(string5.start,string5.stop):null)), "instance"));
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
    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:238:1: visualizer_datapath : 'data' '=' string ;
    public final void visualizer_datapath() throws RecognitionException {
        VirtualWorldModelingLanguageParser.string_return string6 =null;


        try {
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:239:5: ( 'data' '=' string )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:239:7: 'data' '=' string
            {
            match(input,51,FOLLOW_51_in_visualizer_datapath767); if (state.failed) return ;

            match(input,48,FOLLOW_48_in_visualizer_datapath769); if (state.failed) return ;

            pushFollow(FOLLOW_string_in_visualizer_datapath771);
            string6=string();

            state._fsp--;
            if (state.failed) return ;

            if ( state.backtracking==0 ) { 
                				if (modProps != null) {
                					((JavaCodeGenerator.JavaModuleStartProps)modProps).setVisitorDataPath(GeneralUtils.trimQuotes((string6!=null?input.toString(string6.start,string6.stop):null)));
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
    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:246:1: path : STRING_LITERAL ;
    public final VirtualWorldModelingLanguageParser.path_return path() throws RecognitionException {
        VirtualWorldModelingLanguageParser.path_return retval = new VirtualWorldModelingLanguageParser.path_return();
        retval.start = input.LT(1);


        try {
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:247:5: ( STRING_LITERAL )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:247:7: STRING_LITERAL
            {
            match(input,STRING_LITERAL,FOLLOW_STRING_LITERAL_in_path794); if (state.failed) return retval;

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
    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:250:1: module : 'module' ID body EOF ;
    public final void module() throws RecognitionException {
        Token ID7=null;

        try {
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:251:5: ( 'module' ID body EOF )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:251:7: 'module' ID body EOF
            {
            match(input,54,FOLLOW_54_in_module811); if (state.failed) return ;

            ID7=(Token)match(input,ID,FOLLOW_ID_in_module813); if (state.failed) return ;

            if ( state.backtracking==0 ) { 
                			modName = ID7.getText(); 
                			if (modProps != null) {
                				((JavaCodeGenerator.JavaModuleStartProps)modProps).setModuleName(modName);
            	    			try {
            	    				codeGenerator.startModule(modProps);
            	    			}
            	    			catch(Exception e) {
            	    				logger.error("Caught exception '" + e + "'");
            	    				rethrowVWMLExceptionAsRecognitionException(e);
            	    			}
                			}
                			// starts module's definition
                              }

            pushFollow(FOLLOW_body_in_module817);
            body();

            state._fsp--;
            if (state.failed) return ;

            match(input,EOF,FOLLOW_EOF_in_module819); if (state.failed) return ;

            if ( state.backtracking==0 ) {
                                         	if (modProps != null) {
                                         		try {
                                         			// actually generates source code
                                         			codeGenerator.generate(modProps);
                                         			// finalizes source generation phase for this module
                                         			codeGenerator.finishModule(modProps);
                                         			// tells to builder about last steps
                                         			vwmlModelBuilder.finalProcedure(modProps);
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
    // $ANTLR end "module"



    // $ANTLR start "body"
    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:282:1: body : '{' ( expression ( expression )* )? '}' ;
    public final void body() throws RecognitionException {
        try {
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:283:4: ( '{' ( expression ( expression )* )? '}' )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:283:6: '{' ( expression ( expression )* )? '}'
            {
            match(input,59,FOLLOW_59_in_body841); if (state.failed) return ;

            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:283:10: ( expression ( expression )* )?
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( ((LA8_0 >= IAS && LA8_0 <= ID)||LA8_0==46) ) {
                alt8=1;
            }
            switch (alt8) {
                case 1 :
                    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:283:11: expression ( expression )*
                    {
                    pushFollow(FOLLOW_expression_in_body844);
                    expression();

                    state._fsp--;
                    if (state.failed) return ;

                    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:283:22: ( expression )*
                    loop7:
                    do {
                        int alt7=2;
                        int LA7_0 = input.LA(1);

                        if ( ((LA7_0 >= IAS && LA7_0 <= ID)||LA7_0==46) ) {
                            alt7=1;
                        }


                        switch (alt7) {
                    	case 1 :
                    	    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:283:23: expression
                    	    {
                    	    pushFollow(FOLLOW_expression_in_body847);
                    	    expression();

                    	    state._fsp--;
                    	    if (state.failed) return ;

                    	    }
                    	    break;

                    	default :
                    	    break loop7;
                        }
                    } while (true);


                    }
                    break;

            }


            match(input,60,FOLLOW_60_in_body853); if (state.failed) return ;

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
    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:287:1: expression : ( ( entity_decl IAS )=> entity_def | term_def ( SEMICOLON )? );
    public final void expression() throws RecognitionException {
        try {
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:288:5: ( ( entity_decl IAS )=> entity_def | term_def ( SEMICOLON )? )
            int alt10=2;
            alt10 = dfa10.predict(input);
            switch (alt10) {
                case 1 :
                    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:288:7: ( entity_decl IAS )=> entity_def
                    {
                    pushFollow(FOLLOW_entity_def_in_expression879);
                    entity_def();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:289:7: term_def ( SEMICOLON )?
                    {
                    pushFollow(FOLLOW_term_def_in_expression887);
                    term_def();

                    state._fsp--;
                    if (state.failed) return ;

                    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:289:16: ( SEMICOLON )?
                    int alt9=2;
                    int LA9_0 = input.LA(1);

                    if ( (LA9_0==SEMICOLON) ) {
                        alt9=1;
                    }
                    switch (alt9) {
                        case 1 :
                            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:289:17: SEMICOLON
                            {
                            match(input,SEMICOLON,FOLLOW_SEMICOLON_in_expression890); if (state.failed) return ;

                            }
                            break;

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
    // $ANTLR end "expression"



    // $ANTLR start "entity_def"
    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:292:1: entity_def : entity_decl IAS term ( SEMICOLON )? ;
    public final void entity_def() throws RecognitionException {
        String entity_decl8 =null;


        try {
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:293:5: ( entity_decl IAS term ( SEMICOLON )? )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:293:7: entity_decl IAS term ( SEMICOLON )?
            {
            pushFollow(FOLLOW_entity_decl_in_entity_def909);
            entity_decl8=entity_decl();

            state._fsp--;
            if (state.failed) return ;

            match(input,IAS,FOLLOW_IAS_in_entity_def911); if (state.failed) return ;

            if ( state.backtracking==0 ) {
                			if (logger.isDebugEnabled()) {
                				logger.debug("declared entity '" + entity_decl8 + "'");
                			}
                			entityWalker.markFutureEntityAsIAS(entity_decl8);
                			// we should link this entity with parent, if exists
                			 buildLinkingAssociation(entity_decl8);
                		      }

            pushFollow(FOLLOW_term_in_entity_def915);
            term();

            state._fsp--;
            if (state.failed) return ;

            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:300:20: ( SEMICOLON )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==SEMICOLON) ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:300:21: SEMICOLON
                    {
                    match(input,SEMICOLON,FOLLOW_SEMICOLON_in_entity_def918); if (state.failed) return ;

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
    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:303:1: term_def : entity ( oplist )* ;
    public final void term_def() throws RecognitionException {
        String entity9 =null;


        try {
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:304:5: ( entity ( oplist )* )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:304:7: entity ( oplist )*
            {
            pushFollow(FOLLOW_entity_in_term_def937);
            entity9=entity();

            state._fsp--;
            if (state.failed) return ;

            if ( state.backtracking==0 ) { lastProcessedEntityId = entity9; }

            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:304:54: ( oplist )*
            loop12:
            do {
                int alt12=2;
                int LA12_0 = input.LA(1);

                if ( ((LA12_0 >= OPBEGIN && LA12_0 <= OPSUBSTRUCT)) ) {
                    alt12=1;
                }


                switch (alt12) {
            	case 1 :
            	    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:304:55: oplist
            	    {
            	    pushFollow(FOLLOW_oplist_in_term_def942);
            	    oplist();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop12;
                }
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
    // $ANTLR end "term_def"



    // $ANTLR start "entity_decl"
    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:307:1: entity_decl returns [String id] : ( simple_entity_decl | complex_entity_decl );
    public final String entity_decl() throws RecognitionException {
        String id = null;


        String simple_entity_decl10 =null;

        String complex_entity_decl11 =null;


        try {
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:308:5: ( simple_entity_decl | complex_entity_decl )
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==ID) ) {
                alt13=1;
            }
            else if ( (LA13_0==IAS||LA13_0==46) ) {
                alt13=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return id;}
                NoViableAltException nvae =
                    new NoViableAltException("", 13, 0, input);

                throw nvae;

            }
            switch (alt13) {
                case 1 :
                    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:308:7: simple_entity_decl
                    {
                    pushFollow(FOLLOW_simple_entity_decl_in_entity_decl965);
                    simple_entity_decl10=simple_entity_decl();

                    state._fsp--;
                    if (state.failed) return id;

                    if ( state.backtracking==0 ) {id = simple_entity_decl10;}

                    }
                    break;
                case 2 :
                    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:309:7: complex_entity_decl
                    {
                    pushFollow(FOLLOW_complex_entity_decl_in_entity_decl976);
                    complex_entity_decl11=complex_entity_decl();

                    state._fsp--;
                    if (state.failed) return id;

                    if ( state.backtracking==0 ) {id = complex_entity_decl11;}

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



    // $ANTLR start "simple_entity_decl"
    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:312:1: simple_entity_decl returns [String id] : ID ;
    public final String simple_entity_decl() throws RecognitionException {
        String id = null;


        Token ID12=null;

        try {
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:313:5: ( ID )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:313:7: ID
            {
            ID12=(Token)match(input,ID,FOLLOW_ID_in_simple_entity_decl1003); if (state.failed) return id;

            if ( state.backtracking==0 ) {
                		id = ID12.getText();
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
    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:330:1: complex_entity_decl returns [String id] : ( '(' ( simple_entity_decl )+ ')' )? ;
    public final String complex_entity_decl() throws RecognitionException {
        String id = null;



            	complexEntityNameBuilder.startProgress();
            
        try {
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:344:5: ( ( '(' ( simple_entity_decl )+ ')' )? )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:344:7: ( '(' ( simple_entity_decl )+ ')' )?
            {
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:344:7: ( '(' ( simple_entity_decl )+ ')' )?
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==46) ) {
                alt15=1;
            }
            switch (alt15) {
                case 1 :
                    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:344:8: '(' ( simple_entity_decl )+ ')'
                    {
                    match(input,46,FOLLOW_46_in_complex_entity_decl1049); if (state.failed) return id;

                    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:344:12: ( simple_entity_decl )+
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
                    	    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:344:13: simple_entity_decl
                    	    {
                    	    pushFollow(FOLLOW_simple_entity_decl_in_complex_entity_decl1052);
                    	    simple_entity_decl();

                    	    state._fsp--;
                    	    if (state.failed) return id;

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt14 >= 1 ) break loop14;
                    	    if (state.backtracking>0) {state.failed=true; return id;}
                                EarlyExitException eee =
                                    new EarlyExitException(14, input);
                                throw eee;
                        }
                        cnt14++;
                    } while (true);


                    match(input,47,FOLLOW_47_in_complex_entity_decl1056); if (state.failed) return id;

                    }
                    break;

            }


            }

            if ( state.backtracking==0 ) {
                	complexEntityNameBuilder.stopProgress();
                	id = complexEntityNameBuilder.build();
                	try {
                		codeGenerator.declareComplexEntity(id);
                	}
                	catch(Exception e) {
                		rethrowVWMLExceptionAsRecognitionException(e);
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
    // $ANTLR end "complex_entity_decl"



    // $ANTLR start "term"
    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:347:1: term : expression ;
    public final void term() throws RecognitionException {
        try {
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:348:5: ( expression )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:348:7: expression
            {
            pushFollow(FOLLOW_expression_in_term1075);
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
    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:351:1: entity returns [String id] : ( simple_entity | complex_entity );
    public final String entity() throws RecognitionException {
        String id = null;


        String simple_entity13 =null;

        String complex_entity14 =null;


        try {
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:352:5: ( simple_entity | complex_entity )
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==ID) ) {
                alt16=1;
            }
            else if ( (LA16_0==46) ) {
                alt16=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return id;}
                NoViableAltException nvae =
                    new NoViableAltException("", 16, 0, input);

                throw nvae;

            }
            switch (alt16) {
                case 1 :
                    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:352:7: simple_entity
                    {
                    pushFollow(FOLLOW_simple_entity_in_entity1098);
                    simple_entity13=simple_entity();

                    state._fsp--;
                    if (state.failed) return id;

                    if ( state.backtracking==0 ) {id = simple_entity13; }

                    }
                    break;
                case 2 :
                    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:353:7: complex_entity
                    {
                    pushFollow(FOLLOW_complex_entity_in_entity1110);
                    complex_entity14=complex_entity();

                    state._fsp--;
                    if (state.failed) return id;

                    if ( state.backtracking==0 ) {id = complex_entity14;}

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
    // $ANTLR end "entity"



    // $ANTLR start "simple_entity"
    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:357:1: simple_entity returns [String id] : ID ;
    public final String simple_entity() throws RecognitionException {
        String id = null;


        Token ID15=null;

        try {
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:358:5: ( ID )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:358:7: ID
            {
            ID15=(Token)match(input,ID,FOLLOW_ID_in_simple_entity1135); if (state.failed) return id;

            if ( state.backtracking==0 ) {
                	    	if (entityWalker.getEntityMarkedAsIAS() != null) {
                			buildIASAssociation((ID15!=null?ID15.getText():null));
                		}
                		else {
               			buildLinkingAssociation((ID15!=null?ID15.getText():null));
                		}
                		id = (ID15!=null?ID15.getText():null);
                		if (logger.isDebugEnabled()) {
                			logger.debug("processed simple entity '" + id + "'");
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
    // $ANTLR end "simple_entity"



    // $ANTLR start "complex_entity"
    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:372:1: complex_entity returns [String id] : '(' ( term )* ')' ;
    public final String complex_entity() throws RecognitionException {
        String id = null;



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
            		// ... otherwise linkage
          		buildLinkingAssociation(ceId);
          	}
                // the complex enity (name/id is generated) is pushed to stack (here complex entity is part of expression)
            	entityWalker.push(ceId);
            
        try {
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:400:5: ( '(' ( term )* ')' )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:400:7: '(' ( term )* ')'
            {
            match(input,46,FOLLOW_46_in_complex_entity1176); if (state.failed) return id;

            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:400:11: ( term )*
            loop17:
            do {
                int alt17=2;
                int LA17_0 = input.LA(1);

                if ( ((LA17_0 >= IAS && LA17_0 <= ID)||LA17_0==46) ) {
                    alt17=1;
                }


                switch (alt17) {
            	case 1 :
            	    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:400:12: term
            	    {
            	    pushFollow(FOLLOW_term_in_complex_entity1179);
            	    term();

            	    state._fsp--;
            	    if (state.failed) return id;

            	    }
            	    break;

            	default :
            	    break loop17;
                }
            } while (true);


            match(input,47,FOLLOW_47_in_complex_entity1183); if (state.failed) return id;

            }

            if ( state.backtracking==0 ) {
                    // remove it from stack
                	id = (String)entityWalker.pop();
                	if (logger.isDebugEnabled()) {
                		logger.debug("processed complex entity '" + id + "'");
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
    // $ANTLR end "complex_entity"



    // $ANTLR start "oplist"
    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:411:1: oplist : ( opclist | opprojection );
    public final void oplist() throws RecognitionException {
        VirtualWorldModelingLanguageParser.opclist_return opclist16 =null;

        VirtualWorldModelingLanguageParser.opprojection_return opprojection17 =null;


        try {
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:413:5: ( opclist | opprojection )
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( ((LA18_0 >= OPBEGIN && LA18_0 <= OPLAST)||(LA18_0 >= OPRANDOM && LA18_0 <= OPSUBSTRUCT)) ) {
                alt18=1;
            }
            else if ( ((LA18_0 >= OPPROJECTION_1 && LA18_0 <= OPPROJECTION_9)) ) {
                alt18=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 18, 0, input);

                throw nvae;

            }
            switch (alt18) {
                case 1 :
                    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:413:7: opclist
                    {
                    pushFollow(FOLLOW_opclist_in_oplist1266);
                    opclist16=opclist();

                    state._fsp--;
                    if (state.failed) return ;

                    if ( state.backtracking==0 ) { if (lastProcessedEntityId != null) { codeGenerator.associateOperation(lastProcessedEntityId, (opclist16!=null?input.toString(opclist16.start,opclist16.stop):null)); } }

                    }
                    break;
                case 2 :
                    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:414:7: opprojection
                    {
                    pushFollow(FOLLOW_opprojection_in_oplist1282);
                    opprojection17=opprojection();

                    state._fsp--;
                    if (state.failed) return ;

                    if ( state.backtracking==0 ) { if (lastProcessedEntityId != null) { codeGenerator.associateOperation(lastProcessedEntityId, (opprojection17!=null?input.toString(opprojection17.start,opprojection17.stop):null)); } }

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
    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:417:1: opclist : ( OPJOIN | OPINTERSECT | OPSUBSTRUCT | OPFIRST | OPLAST | OPBEGIN | OPREST | OPCARTESIAN | OPIN | OPINCL | OPEQ | OPIDENT | OPSQU | OPINTERPRET | OPCREATEEXPR | OPEXECUTE | OPRANDOM );
    public final VirtualWorldModelingLanguageParser.opclist_return opclist() throws RecognitionException {
        VirtualWorldModelingLanguageParser.opclist_return retval = new VirtualWorldModelingLanguageParser.opclist_return();
        retval.start = input.LT(1);


        try {
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:418:5: ( OPJOIN | OPINTERSECT | OPSUBSTRUCT | OPFIRST | OPLAST | OPBEGIN | OPREST | OPCARTESIAN | OPIN | OPINCL | OPEQ | OPIDENT | OPSQU | OPINTERPRET | OPCREATEEXPR | OPEXECUTE | OPRANDOM )
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
    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:437:1: opprojection : ( OPPROJECTION_1 | OPPROJECTION_2 | OPPROJECTION_3 | OPPROJECTION_4 | OPPROJECTION_5 | OPPROJECTION_6 | OPPROJECTION_7 | OPPROJECTION_8 | OPPROJECTION_9 );
    public final VirtualWorldModelingLanguageParser.opprojection_return opprojection() throws RecognitionException {
        VirtualWorldModelingLanguageParser.opprojection_return retval = new VirtualWorldModelingLanguageParser.opprojection_return();
        retval.start = input.LT(1);


        try {
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:438:5: ( OPPROJECTION_1 | OPPROJECTION_2 | OPPROJECTION_3 | OPPROJECTION_4 | OPPROJECTION_5 | OPPROJECTION_6 | OPPROJECTION_7 | OPPROJECTION_8 | OPPROJECTION_9 )
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
    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:449:1: termLanguages : ( JAVA | C | CPP | OBJECTIVEC );
    public final void termLanguages() throws RecognitionException {
        try {
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:450:5: ( JAVA | C | CPP | OBJECTIVEC )
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
    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:456:1: string : STRING_LITERAL ;
    public final VirtualWorldModelingLanguageParser.string_return string() throws RecognitionException {
        VirtualWorldModelingLanguageParser.string_return retval = new VirtualWorldModelingLanguageParser.string_return();
        retval.start = input.LT(1);


        try {
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:457:5: ( STRING_LITERAL )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:457:7: STRING_LITERAL
            {
            match(input,STRING_LITERAL,FOLLOW_STRING_LITERAL_in_string1569); if (state.failed) return retval;

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
        // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:155:7: ( 'language' '=' JAVA )
        // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:155:8: 'language' '=' JAVA
        {
        match(input,53,FOLLOW_53_in_synpred1_VirtualWorldModelingLanguage431); if (state.failed) return ;

        match(input,48,FOLLOW_48_in_synpred1_VirtualWorldModelingLanguage433); if (state.failed) return ;

        match(input,JAVA,FOLLOW_JAVA_in_synpred1_VirtualWorldModelingLanguage435); if (state.failed) return ;

        }

    }
    // $ANTLR end synpred1_VirtualWorldModelingLanguage

    // $ANTLR start synpred2_VirtualWorldModelingLanguage
    public final void synpred2_VirtualWorldModelingLanguage_fragment() throws RecognitionException {
        // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:288:7: ( entity_decl IAS )
        // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:288:8: entity_decl IAS
        {
        pushFollow(FOLLOW_entity_decl_in_synpred2_VirtualWorldModelingLanguage872);
        entity_decl();

        state._fsp--;
        if (state.failed) return ;

        match(input,IAS,FOLLOW_IAS_in_synpred2_VirtualWorldModelingLanguage874); if (state.failed) return ;

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


    protected DFA10 dfa10 = new DFA10(this);
    static final String DFA10_eotS =
        "\7\uffff";
    static final String DFA10_eofS =
        "\7\uffff";
    static final String DFA10_minS =
        "\1\11\1\0\1\11\2\uffff\1\11\1\0";
    static final String DFA10_maxS =
        "\1\56\1\0\1\57\2\uffff\1\57\1\0";
    static final String DFA10_acceptS =
        "\3\uffff\1\1\1\2\2\uffff";
    static final String DFA10_specialS =
        "\1\2\1\1\4\uffff\1\0}>";
    static final String[] DFA10_transitionS = {
            "\1\3\1\1\43\uffff\1\2",
            "\1\uffff",
            "\1\4\1\5\43\uffff\2\4",
            "",
            "",
            "\1\4\1\5\6\uffff\33\4\2\uffff\1\4\1\6",
            "\1\uffff"
    };

    static final short[] DFA10_eot = DFA.unpackEncodedString(DFA10_eotS);
    static final short[] DFA10_eof = DFA.unpackEncodedString(DFA10_eofS);
    static final char[] DFA10_min = DFA.unpackEncodedStringToUnsignedChars(DFA10_minS);
    static final char[] DFA10_max = DFA.unpackEncodedStringToUnsignedChars(DFA10_maxS);
    static final short[] DFA10_accept = DFA.unpackEncodedString(DFA10_acceptS);
    static final short[] DFA10_special = DFA.unpackEncodedString(DFA10_specialS);
    static final short[][] DFA10_transition;

    static {
        int numStates = DFA10_transitionS.length;
        DFA10_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA10_transition[i] = DFA.unpackEncodedString(DFA10_transitionS[i]);
        }
    }

    class DFA10 extends DFA {

        public DFA10(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 10;
            this.eot = DFA10_eot;
            this.eof = DFA10_eof;
            this.min = DFA10_min;
            this.max = DFA10_max;
            this.accept = DFA10_accept;
            this.special = DFA10_special;
            this.transition = DFA10_transition;
        }
        public String getDescription() {
            return "287:1: expression : ( ( entity_decl IAS )=> entity_def | term_def ( SEMICOLON )? );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA10_6 = input.LA(1);

                         
                        int index10_6 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred2_VirtualWorldModelingLanguage()) ) {s = 3;}

                        else if ( (true) ) {s = 4;}

                         
                        input.seek(index10_6);

                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA10_1 = input.LA(1);

                         
                        int index10_1 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred2_VirtualWorldModelingLanguage()) ) {s = 3;}

                        else if ( (true) ) {s = 4;}

                         
                        input.seek(index10_1);

                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA10_0 = input.LA(1);

                         
                        int index10_0 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (LA10_0==ID) ) {s = 1;}

                        else if ( (LA10_0==46) ) {s = 2;}

                        else if ( (LA10_0==IAS) && (synpred2_VirtualWorldModelingLanguage())) {s = 3;}

                         
                        input.seek(index10_0);

                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}

            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 10, _s, input);
            error(nvae);
            throw nvae;
        }

    }
 

    public static final BitSet FOLLOW_props_in_filedef356 = new BitSet(new long[]{0x0040000000000000L});
    public static final BitSet FOLLOW_module_in_filedef358 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_module_in_filedef366 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_55_in_props386 = new BitSet(new long[]{0x0800000000000000L});
    public static final BitSet FOLLOW_59_in_props388 = new BitSet(new long[]{0x0020000000000000L});
    public static final BitSet FOLLOW_optionsList_in_props390 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_60_in_props392 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_lang_in_optionsList413 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_langJava_in_lang440 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_otherLanguages_in_lang448 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_53_in_langJava491 = new BitSet(new long[]{0x0001000000000000L});
    public static final BitSet FOLLOW_48_in_langJava493 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_JAVA_in_langJava495 = new BitSet(new long[]{0x0800000000000000L});
    public static final BitSet FOLLOW_59_in_langJava497 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_javaProps_in_langJava499 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_60_in_langJava501 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_propPackage_in_javaProps527 = new BitSet(new long[]{0x0200000000000000L});
    public static final BitSet FOLLOW_generatedFileLocation_in_javaProps529 = new BitSet(new long[]{0x0412000000000000L});
    public static final BitSet FOLLOW_optionalProps_in_javaProps531 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_56_in_propPackage553 = new BitSet(new long[]{0x0001000000000000L});
    public static final BitSet FOLLOW_48_in_propPackage555 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_packageName_in_propPackage557 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_LITERAL_in_packageName576 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_57_in_generatedFileLocation593 = new BitSet(new long[]{0x0001000000000000L});
    public static final BitSet FOLLOW_48_in_generatedFileLocation595 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_path_in_generatedFileLocation597 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_author_in_optionalProps617 = new BitSet(new long[]{0x0410000000000002L});
    public static final BitSet FOLLOW_description_in_optionalProps620 = new BitSet(new long[]{0x0400000000000002L});
    public static final BitSet FOLLOW_visualizer_in_optionalProps623 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_49_in_author641 = new BitSet(new long[]{0x0001000000000000L});
    public static final BitSet FOLLOW_48_in_author643 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_string_in_author645 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_52_in_description668 = new BitSet(new long[]{0x0001000000000000L});
    public static final BitSet FOLLOW_48_in_description670 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_string_in_description672 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_58_in_visualizer691 = new BitSet(new long[]{0x0800000000000000L});
    public static final BitSet FOLLOW_59_in_visualizer693 = new BitSet(new long[]{0x1004000000000000L});
    public static final BitSet FOLLOW_visualizer_body_in_visualizer695 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_60_in_visualizer697 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_visualizer_class_in_visualizer_body715 = new BitSet(new long[]{0x0008000000000000L});
    public static final BitSet FOLLOW_visualizer_datapath_in_visualizer_body717 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_50_in_visualizer_class740 = new BitSet(new long[]{0x0001000000000000L});
    public static final BitSet FOLLOW_48_in_visualizer_class742 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_string_in_visualizer_class744 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_51_in_visualizer_datapath767 = new BitSet(new long[]{0x0001000000000000L});
    public static final BitSet FOLLOW_48_in_visualizer_datapath769 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_string_in_visualizer_datapath771 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_LITERAL_in_path794 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_54_in_module811 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_ID_in_module813 = new BitSet(new long[]{0x0800000000000000L});
    public static final BitSet FOLLOW_body_in_module817 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_module819 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_59_in_body841 = new BitSet(new long[]{0x1000400000000400L});
    public static final BitSet FOLLOW_expression_in_body844 = new BitSet(new long[]{0x1000400000000400L});
    public static final BitSet FOLLOW_expression_in_body847 = new BitSet(new long[]{0x1000400000000400L});
    public static final BitSet FOLLOW_60_in_body853 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_entity_def_in_expression879 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_term_def_in_expression887 = new BitSet(new long[]{0x0000080000000002L});
    public static final BitSet FOLLOW_SEMICOLON_in_expression890 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_entity_decl_in_entity_def909 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_IAS_in_entity_def911 = new BitSet(new long[]{0x0000400000000400L});
    public static final BitSet FOLLOW_term_in_entity_def915 = new BitSet(new long[]{0x0000080000000002L});
    public static final BitSet FOLLOW_SEMICOLON_in_entity_def918 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_entity_in_term_def937 = new BitSet(new long[]{0x000007FFFFFE0002L});
    public static final BitSet FOLLOW_oplist_in_term_def942 = new BitSet(new long[]{0x000007FFFFFE0002L});
    public static final BitSet FOLLOW_simple_entity_decl_in_entity_decl965 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_complex_entity_decl_in_entity_decl976 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_simple_entity_decl1003 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_46_in_complex_entity_decl1049 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_simple_entity_decl_in_complex_entity_decl1052 = new BitSet(new long[]{0x0000800000000400L});
    public static final BitSet FOLLOW_47_in_complex_entity_decl1056 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expression_in_term1075 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simple_entity_in_entity1098 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_complex_entity_in_entity1110 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_simple_entity1135 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_46_in_complex_entity1176 = new BitSet(new long[]{0x0000C00000000400L});
    public static final BitSet FOLLOW_term_in_complex_entity1179 = new BitSet(new long[]{0x0000C00000000400L});
    public static final BitSet FOLLOW_47_in_complex_entity1183 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_opclist_in_oplist1266 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_opprojection_in_oplist1282 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_LITERAL_in_string1569 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_53_in_synpred1_VirtualWorldModelingLanguage431 = new BitSet(new long[]{0x0001000000000000L});
    public static final BitSet FOLLOW_48_in_synpred1_VirtualWorldModelingLanguage433 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_JAVA_in_synpred1_VirtualWorldModelingLanguage435 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_entity_decl_in_synpred2_VirtualWorldModelingLanguage872 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_IAS_in_synpred2_VirtualWorldModelingLanguage874 = new BitSet(new long[]{0x0000000000000002L});

}