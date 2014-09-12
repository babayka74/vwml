// $ANTLR 3.4 C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g 2014-09-12 12:02:24
 
package com.vw.lang.grammar;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked"})
public class VirtualWorldModelingLanguageLexer extends Lexer {
    public static final int EOF=-1;
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
    public static final int T__71=71;
    public static final int T__72=72;
    public static final int T__73=73;
    public static final int T__74=74;
    public static final int T__75=75;
    public static final int T__76=76;
    public static final int T__77=77;
    public static final int T__78=78;
    public static final int T__79=79;
    public static final int T__80=80;
    public static final int T__81=81;
    public static final int T__82=82;
    public static final int T__83=83;
    public static final int T__84=84;
    public static final int T__85=85;
    public static final int C=4;
    public static final int COMMA=5;
    public static final int COMMENT=6;
    public static final int CPP=7;
    public static final int DIRECTIVE_DEBUG=8;
    public static final int DIRECTIVE_ENDIF=9;
    public static final int DQUOTE=10;
    public static final int IAS=11;
    public static final int ID=12;
    public static final int JAVA=13;
    public static final int LETTER=14;
    public static final int LIFETERM=15;
    public static final int LINE_COMMENT=16;
    public static final int NATIVE_CODE=17;
    public static final int OBJECTIVEC=18;
    public static final int OPACTIVATECTX=19;
    public static final int OPACTIVATEONFRINGE=20;
    public static final int OPAPPLYTOCONTEXT=21;
    public static final int OPBEGIN=22;
    public static final int OPBORN=23;
    public static final int OPBREAKPOINT=24;
    public static final int OPCALLP=25;
    public static final int OPCARTESIAN=26;
    public static final int OPCLONE=27;
    public static final int OPCREATEEXPR=28;
    public static final int OPDYNCONTEXT=29;
    public static final int OPENDCONFLICTGROUP=30;
    public static final int OPEQ=31;
    public static final int OPEXECUTE=32;
    public static final int OPFIND=33;
    public static final int OPFIRST=34;
    public static final int OPFOREACH=35;
    public static final int OPGATE=36;
    public static final int OPGET=37;
    public static final int OPIDENT=38;
    public static final int OPIN=39;
    public static final int OPINCL=40;
    public static final int OPINTERPRET=41;
    public static final int OPINTERRUPT=42;
    public static final int OPINTERSECT=43;
    public static final int OPJOIN=44;
    public static final int OPLAST=45;
    public static final int OPPROJECTION=46;
    public static final int OPRANDOM=47;
    public static final int OPRECALL=48;
    public static final int OPRELAX=49;
    public static final int OPREPEAT=50;
    public static final int OPREST=51;
    public static final int OPSIZE=52;
    public static final int OPSQU=53;
    public static final int OPSTARTCONFLICTGROUP=54;
    public static final int OPSUBSTRUCT=55;
    public static final int SEMICOLON=56;
    public static final int STRING_LITERAL=57;
    public static final int WS=58;

            private static final int NATIVE_CODE_CHANNEL = 199;


    // delegates
    // delegators
    public Lexer[] getDelegates() {
        return new Lexer[] {};
    }

    public VirtualWorldModelingLanguageLexer() {} 
    public VirtualWorldModelingLanguageLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public VirtualWorldModelingLanguageLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);
    }
    public String getGrammarFileName() { return "C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g"; }

    // $ANTLR start "C"
    public final void mC() throws RecognitionException {
        try {
            int _type = C;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:9:3: ( '__c__' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:9:5: '__c__'
            {
            match("__c__"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "C"

    // $ANTLR start "CPP"
    public final void mCPP() throws RecognitionException {
        try {
            int _type = CPP;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:10:5: ( '__cpp__' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:10:7: '__cpp__'
            {
            match("__cpp__"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "CPP"

    // $ANTLR start "DIRECTIVE_DEBUG"
    public final void mDIRECTIVE_DEBUG() throws RecognitionException {
        try {
            int _type = DIRECTIVE_DEBUG;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:11:17: ( '#if_debug' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:11:19: '#if_debug'
            {
            match("#if_debug"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "DIRECTIVE_DEBUG"

    // $ANTLR start "DIRECTIVE_ENDIF"
    public final void mDIRECTIVE_ENDIF() throws RecognitionException {
        try {
            int _type = DIRECTIVE_ENDIF;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:12:17: ( '#endif' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:12:19: '#endif'
            {
            match("#endif"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "DIRECTIVE_ENDIF"

    // $ANTLR start "IAS"
    public final void mIAS() throws RecognitionException {
        try {
            int _type = IAS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:13:5: ( 'ias' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:13:7: 'ias'
            {
            match("ias"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "IAS"

    // $ANTLR start "JAVA"
    public final void mJAVA() throws RecognitionException {
        try {
            int _type = JAVA;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:14:6: ( '__java__' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:14:8: '__java__'
            {
            match("__java__"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "JAVA"

    // $ANTLR start "LIFETERM"
    public final void mLIFETERM() throws RecognitionException {
        try {
            int _type = LIFETERM;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:15:10: ( 'lifeterm' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:15:12: 'lifeterm'
            {
            match("lifeterm"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "LIFETERM"

    // $ANTLR start "OBJECTIVEC"
    public final void mOBJECTIVEC() throws RecognitionException {
        try {
            int _type = OBJECTIVEC;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:16:12: ( '__objective_c__' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:16:14: '__objective_c__'
            {
            match("__objective_c__"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "OBJECTIVEC"

    // $ANTLR start "OPACTIVATECTX"
    public final void mOPACTIVATECTX() throws RecognitionException {
        try {
            int _type = OPACTIVATECTX;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:17:15: ( ':' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:17:17: ':'
            {
            match(':'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "OPACTIVATECTX"

    // $ANTLR start "OPACTIVATEONFRINGE"
    public final void mOPACTIVATEONFRINGE() throws RecognitionException {
        try {
            int _type = OPACTIVATEONFRINGE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:18:20: ( 'Do' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:18:22: 'Do'
            {
            match("Do"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "OPACTIVATEONFRINGE"

    // $ANTLR start "OPAPPLYTOCONTEXT"
    public final void mOPAPPLYTOCONTEXT() throws RecognitionException {
        try {
            int _type = OPAPPLYTOCONTEXT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:19:18: ( 'Context' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:19:20: 'Context'
            {
            match("Context"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "OPAPPLYTOCONTEXT"

    // $ANTLR start "OPBEGIN"
    public final void mOPBEGIN() throws RecognitionException {
        try {
            int _type = OPBEGIN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:20:9: ( 'Begin' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:20:11: 'Begin'
            {
            match("Begin"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "OPBEGIN"

    // $ANTLR start "OPBORN"
    public final void mOPBORN() throws RecognitionException {
        try {
            int _type = OPBORN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:21:8: ( 'Born' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:21:10: 'Born'
            {
            match("Born"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "OPBORN"

    // $ANTLR start "OPBREAKPOINT"
    public final void mOPBREAKPOINT() throws RecognitionException {
        try {
            int _type = OPBREAKPOINT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:22:14: ( 'Bp' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:22:16: 'Bp'
            {
            match("Bp"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "OPBREAKPOINT"

    // $ANTLR start "OPCALLP"
    public final void mOPCALLP() throws RecognitionException {
        try {
            int _type = OPCALLP;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:23:9: ( 'CallP' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:23:11: 'CallP'
            {
            match("CallP"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "OPCALLP"

    // $ANTLR start "OPCARTESIAN"
    public final void mOPCARTESIAN() throws RecognitionException {
        try {
            int _type = OPCARTESIAN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:24:13: ( 'Cartesian' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:24:15: 'Cartesian'
            {
            match("Cartesian"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "OPCARTESIAN"

    // $ANTLR start "OPCLONE"
    public final void mOPCLONE() throws RecognitionException {
        try {
            int _type = OPCLONE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:25:9: ( 'Clone' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:25:11: 'Clone'
            {
            match("Clone"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "OPCLONE"

    // $ANTLR start "OPCREATEEXPR"
    public final void mOPCREATEEXPR() throws RecognitionException {
        try {
            int _type = OPCREATEEXPR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:26:14: ( '^' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:26:16: '^'
            {
            match('^'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "OPCREATEEXPR"

    // $ANTLR start "OPDYNCONTEXT"
    public final void mOPDYNCONTEXT() throws RecognitionException {
        try {
            int _type = OPDYNCONTEXT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:27:14: ( '->' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:27:16: '->'
            {
            match("->"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "OPDYNCONTEXT"

    // $ANTLR start "OPENDCONFLICTGROUP"
    public final void mOPENDCONFLICTGROUP() throws RecognitionException {
        try {
            int _type = OPENDCONFLICTGROUP;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:28:20: ( ']' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:28:22: ']'
            {
            match(']'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "OPENDCONFLICTGROUP"

    // $ANTLR start "OPEQ"
    public final void mOPEQ() throws RecognitionException {
        try {
            int _type = OPEQ;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:29:6: ( 'Eq' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:29:8: 'Eq'
            {
            match("Eq"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "OPEQ"

    // $ANTLR start "OPEXECUTE"
    public final void mOPEXECUTE() throws RecognitionException {
        try {
            int _type = OPEXECUTE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:30:11: ( 'Exe' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:30:13: 'Exe'
            {
            match("Exe"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "OPEXECUTE"

    // $ANTLR start "OPFIND"
    public final void mOPFIND() throws RecognitionException {
        try {
            int _type = OPFIND;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:31:8: ( 'Find' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:31:10: 'Find'
            {
            match("Find"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "OPFIND"

    // $ANTLR start "OPFIRST"
    public final void mOPFIRST() throws RecognitionException {
        try {
            int _type = OPFIRST;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:32:9: ( 'First' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:32:11: 'First'
            {
            match("First"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "OPFIRST"

    // $ANTLR start "OPFOREACH"
    public final void mOPFOREACH() throws RecognitionException {
        try {
            int _type = OPFOREACH;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:33:11: ( 'ForEach' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:33:13: 'ForEach'
            {
            match("ForEach"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "OPFOREACH"

    // $ANTLR start "OPGATE"
    public final void mOPGATE() throws RecognitionException {
        try {
            int _type = OPGATE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:34:8: ( 'Gate' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:34:10: 'Gate'
            {
            match("Gate"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "OPGATE"

    // $ANTLR start "OPGET"
    public final void mOPGET() throws RecognitionException {
        try {
            int _type = OPGET;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:35:7: ( 'Get' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:35:9: 'Get'
            {
            match("Get"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "OPGET"

    // $ANTLR start "OPIDENT"
    public final void mOPIDENT() throws RecognitionException {
        try {
            int _type = OPIDENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:36:9: ( 'Ident' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:36:11: 'Ident'
            {
            match("Ident"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "OPIDENT"

    // $ANTLR start "OPIN"
    public final void mOPIN() throws RecognitionException {
        try {
            int _type = OPIN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:37:6: ( 'In' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:37:8: 'In'
            {
            match("In"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "OPIN"

    // $ANTLR start "OPINCL"
    public final void mOPINCL() throws RecognitionException {
        try {
            int _type = OPINCL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:38:8: ( 'Include' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:38:10: 'Include'
            {
            match("Include"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "OPINCL"

    // $ANTLR start "OPINTERPRET"
    public final void mOPINTERPRET() throws RecognitionException {
        try {
            int _type = OPINTERPRET;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:39:13: ( '~' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:39:15: '~'
            {
            match('~'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "OPINTERPRET"

    // $ANTLR start "OPINTERRUPT"
    public final void mOPINTERRUPT() throws RecognitionException {
        try {
            int _type = OPINTERRUPT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:40:13: ( 'Interrupt' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:40:15: 'Interrupt'
            {
            match("Interrupt"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "OPINTERRUPT"

    // $ANTLR start "OPINTERSECT"
    public final void mOPINTERSECT() throws RecognitionException {
        try {
            int _type = OPINTERSECT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:41:13: ( 'Intersect' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:41:15: 'Intersect'
            {
            match("Intersect"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "OPINTERSECT"

    // $ANTLR start "OPJOIN"
    public final void mOPJOIN() throws RecognitionException {
        try {
            int _type = OPJOIN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:42:8: ( 'Join' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:42:10: 'Join'
            {
            match("Join"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "OPJOIN"

    // $ANTLR start "OPLAST"
    public final void mOPLAST() throws RecognitionException {
        try {
            int _type = OPLAST;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:43:8: ( 'Last' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:43:10: 'Last'
            {
            match("Last"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "OPLAST"

    // $ANTLR start "OPPROJECTION"
    public final void mOPPROJECTION() throws RecognitionException {
        try {
            int _type = OPPROJECTION;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:44:14: ( 'Projection' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:44:16: 'Projection'
            {
            match("Projection"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "OPPROJECTION"

    // $ANTLR start "OPRANDOM"
    public final void mOPRANDOM() throws RecognitionException {
        try {
            int _type = OPRANDOM;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:45:10: ( 'Random' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:45:12: 'Random'
            {
            match("Random"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "OPRANDOM"

    // $ANTLR start "OPRECALL"
    public final void mOPRECALL() throws RecognitionException {
        try {
            int _type = OPRECALL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:46:10: ( 'Recall' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:46:12: 'Recall'
            {
            match("Recall"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "OPRECALL"

    // $ANTLR start "OPRELAX"
    public final void mOPRELAX() throws RecognitionException {
        try {
            int _type = OPRELAX;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:47:9: ( 'Relax' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:47:11: 'Relax'
            {
            match("Relax"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "OPRELAX"

    // $ANTLR start "OPREPEAT"
    public final void mOPREPEAT() throws RecognitionException {
        try {
            int _type = OPREPEAT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:48:10: ( 'Repeat' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:48:12: 'Repeat'
            {
            match("Repeat"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "OPREPEAT"

    // $ANTLR start "OPREST"
    public final void mOPREST() throws RecognitionException {
        try {
            int _type = OPREST;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:49:8: ( 'Rest' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:49:10: 'Rest'
            {
            match("Rest"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "OPREST"

    // $ANTLR start "OPSIZE"
    public final void mOPSIZE() throws RecognitionException {
        try {
            int _type = OPSIZE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:50:8: ( 'Size' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:50:10: 'Size'
            {
            match("Size"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "OPSIZE"

    // $ANTLR start "OPSQU"
    public final void mOPSQU() throws RecognitionException {
        try {
            int _type = OPSQU;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:51:7: ( 'Squeeze' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:51:9: 'Squeeze'
            {
            match("Squeeze"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "OPSQU"

    // $ANTLR start "OPSTARTCONFLICTGROUP"
    public final void mOPSTARTCONFLICTGROUP() throws RecognitionException {
        try {
            int _type = OPSTARTCONFLICTGROUP;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:52:22: ( '[' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:52:24: '['
            {
            match('['); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "OPSTARTCONFLICTGROUP"

    // $ANTLR start "OPSUBSTRUCT"
    public final void mOPSUBSTRUCT() throws RecognitionException {
        try {
            int _type = OPSUBSTRUCT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:53:13: ( 'Substruct' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:53:15: 'Substruct'
            {
            match("Substruct"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "OPSUBSTRUCT"

    // $ANTLR start "T__59"
    public final void mT__59() throws RecognitionException {
        try {
            int _type = T__59;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:54:7: ( '(' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:54:9: '('
            {
            match('('); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__59"

    // $ANTLR start "T__60"
    public final void mT__60() throws RecognitionException {
        try {
            int _type = T__60;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:55:7: ( ')' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:55:9: ')'
            {
            match(')'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__60"

    // $ANTLR start "T__61"
    public final void mT__61() throws RecognitionException {
        try {
            int _type = T__61;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:56:7: ( '.' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:56:9: '.'
            {
            match('.'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__61"

    // $ANTLR start "T__62"
    public final void mT__62() throws RecognitionException {
        try {
            int _type = T__62;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:57:7: ( '=' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:57:9: '='
            {
            match('='); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__62"

    // $ANTLR start "T__63"
    public final void mT__63() throws RecognitionException {
        try {
            int _type = T__63;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:58:7: ( 'author' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:58:9: 'author'
            {
            match("author"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__63"

    // $ANTLR start "T__64"
    public final void mT__64() throws RecognitionException {
        try {
            int _type = T__64;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:59:7: ( 'beyond' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:59:9: 'beyond'
            {
            match("beyond"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__64"

    // $ANTLR start "T__65"
    public final void mT__65() throws RecognitionException {
        try {
            int _type = T__65;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:60:7: ( 'class' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:60:9: 'class'
            {
            match("class"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__65"

    // $ANTLR start "T__66"
    public final void mT__66() throws RecognitionException {
        try {
            int _type = T__66;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:61:7: ( 'conflictring' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:61:9: 'conflictring'
            {
            match("conflictring"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__66"

    // $ANTLR start "T__67"
    public final void mT__67() throws RecognitionException {
        try {
            int _type = T__67;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:62:7: ( 'conflicts' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:62:9: 'conflicts'
            {
            match("conflicts"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__67"

    // $ANTLR start "T__68"
    public final void mT__68() throws RecognitionException {
        try {
            int _type = T__68;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:63:7: ( 'contexts' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:63:9: 'contexts'
            {
            match("contexts"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__68"

    // $ANTLR start "T__69"
    public final void mT__69() throws RecognitionException {
        try {
            int _type = T__69;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:64:7: ( 'data' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:64:9: 'data'
            {
            match("data"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__69"

    // $ANTLR start "T__70"
    public final void mT__70() throws RecognitionException {
        try {
            int _type = T__70;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:65:7: ( 'description' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:65:9: 'description'
            {
            match("description"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__70"

    // $ANTLR start "T__71"
    public final void mT__71() throws RecognitionException {
        try {
            int _type = T__71;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:66:7: ( 'entities' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:66:9: 'entities'
            {
            match("entities"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__71"

    // $ANTLR start "T__72"
    public final void mT__72() throws RecognitionException {
        try {
            int _type = T__72;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:67:7: ( 'entity_history_size' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:67:9: 'entity_history_size'
            {
            match("entity_history_size"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__72"

    // $ANTLR start "T__73"
    public final void mT__73() throws RecognitionException {
        try {
            int _type = T__73;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:68:7: ( 'external' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:68:9: 'external'
            {
            match("external"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__73"

    // $ANTLR start "T__74"
    public final void mT__74() throws RecognitionException {
        try {
            int _type = T__74;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:69:7: ( 'fringe' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:69:9: 'fringe'
            {
            match("fringe"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__74"

    // $ANTLR start "T__75"
    public final void mT__75() throws RecognitionException {
        try {
            int _type = T__75;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:70:7: ( 'include' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:70:9: 'include'
            {
            match("include"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__75"

    // $ANTLR start "T__76"
    public final void mT__76() throws RecognitionException {
        try {
            int _type = T__76;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:71:7: ( 'language' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:71:9: 'language'
            {
            match("language"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__76"

    // $ANTLR start "T__77"
    public final void mT__77() throws RecognitionException {
        try {
            int _type = T__77;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:72:7: ( 'module' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:72:9: 'module'
            {
            match("module"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__77"

    // $ANTLR start "T__78"
    public final void mT__78() throws RecognitionException {
        try {
            int _type = T__78;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:73:7: ( 'options' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:73:9: 'options'
            {
            match("options"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__78"

    // $ANTLR start "T__79"
    public final void mT__79() throws RecognitionException {
        try {
            int _type = T__79;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:74:7: ( 'package' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:74:9: 'package'
            {
            match("package"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__79"

    // $ANTLR start "T__80"
    public final void mT__80() throws RecognitionException {
        try {
            int _type = T__80;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:75:7: ( 'path' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:75:9: 'path'
            {
            match("path"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__80"

    // $ANTLR start "T__81"
    public final void mT__81() throws RecognitionException {
        try {
            int _type = T__81;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:76:7: ( 'project_name' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:76:9: 'project_name'
            {
            match("project_name"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__81"

    // $ANTLR start "T__82"
    public final void mT__82() throws RecognitionException {
        try {
            int _type = T__82;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:77:7: ( 'source' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:77:9: 'source'
            {
            match("source"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__82"

    // $ANTLR start "T__83"
    public final void mT__83() throws RecognitionException {
        try {
            int _type = T__83;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:78:7: ( 'visualizer' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:78:9: 'visualizer'
            {
            match("visualizer"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__83"

    // $ANTLR start "T__84"
    public final void mT__84() throws RecognitionException {
        try {
            int _type = T__84;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:79:7: ( '{' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:79:9: '{'
            {
            match('{'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__84"

    // $ANTLR start "T__85"
    public final void mT__85() throws RecognitionException {
        try {
            int _type = T__85;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:80:7: ( '}' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:80:9: '}'
            {
            match('}'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__85"

    // $ANTLR start "ID"
    public final void mID() throws RecognitionException {
        try {
            int _type = ID;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1192:5: ( LETTER ( LETTER | '.' )* )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1192:7: LETTER ( LETTER | '.' )*
            {
            mLETTER(); 


            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1192:14: ( LETTER | '.' )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0=='!'||LA1_0=='$'||(LA1_0 >= '-' && LA1_0 <= '.')||(LA1_0 >= '0' && LA1_0 <= '9')||LA1_0=='?'||(LA1_0 >= 'A' && LA1_0 <= 'Z')||LA1_0=='_'||(LA1_0 >= 'a' && LA1_0 <= 'z')) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:
            	    {
            	    if ( input.LA(1)=='!'||input.LA(1)=='$'||(input.LA(1) >= '-' && input.LA(1) <= '.')||(input.LA(1) >= '0' && input.LA(1) <= '9')||input.LA(1)=='?'||(input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "ID"

    // $ANTLR start "STRING_LITERAL"
    public final void mSTRING_LITERAL() throws RecognitionException {
        try {
            int _type = STRING_LITERAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1197:5: ( '\"' (~ ( '\"' ) )* '\"' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1197:8: '\"' (~ ( '\"' ) )* '\"'
            {
            match('\"'); 

            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1197:12: (~ ( '\"' ) )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( ((LA2_0 >= '\u0000' && LA2_0 <= '!')||(LA2_0 >= '#' && LA2_0 <= '\uFFFF')) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:
            	    {
            	    if ( (input.LA(1) >= '\u0000' && input.LA(1) <= '!')||(input.LA(1) >= '#' && input.LA(1) <= '\uFFFF') ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);


            match('\"'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "STRING_LITERAL"

    // $ANTLR start "COMMA"
    public final void mCOMMA() throws RecognitionException {
        try {
            int _type = COMMA;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1282:5: ( ',' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1282:7: ','
            {
            match(','); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "COMMA"

    // $ANTLR start "DQUOTE"
    public final void mDQUOTE() throws RecognitionException {
        try {
            int _type = DQUOTE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1286:5: ( '\"' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1286:7: '\"'
            {
            match('\"'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "DQUOTE"

    // $ANTLR start "SEMICOLON"
    public final void mSEMICOLON() throws RecognitionException {
        try {
            int _type = SEMICOLON;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1290:5: ( ';' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1290:7: ';'
            {
            match(';'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "SEMICOLON"

    // $ANTLR start "WS"
    public final void mWS() throws RecognitionException {
        try {
            int _type = WS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1294:5: ( ( ' ' | '\\t' | '\\n' | '\\r' ) )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1294:7: ( ' ' | '\\t' | '\\n' | '\\r' )
            {
            if ( (input.LA(1) >= '\t' && input.LA(1) <= '\n')||input.LA(1)=='\r'||input.LA(1)==' ' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            _channel=HIDDEN;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "WS"

    // $ANTLR start "NATIVE_CODE"
    public final void mNATIVE_CODE() throws RecognitionException {
        try {
            int _type = NATIVE_CODE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1299:5: ( '<*' ( . )* '*>' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1299:7: '<*' ( . )* '*>'
            {
            match("<*"); 



            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1299:12: ( . )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0=='*') ) {
                    int LA3_1 = input.LA(2);

                    if ( (LA3_1=='>') ) {
                        alt3=2;
                    }
                    else if ( ((LA3_1 >= '\u0000' && LA3_1 <= '=')||(LA3_1 >= '?' && LA3_1 <= '\uFFFF')) ) {
                        alt3=1;
                    }


                }
                else if ( ((LA3_0 >= '\u0000' && LA3_0 <= ')')||(LA3_0 >= '+' && LA3_0 <= '\uFFFF')) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1299:12: .
            	    {
            	    matchAny(); 

            	    }
            	    break;

            	default :
            	    break loop3;
                }
            } while (true);


            match("*>"); 



            _channel=NATIVE_CODE_CHANNEL;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "NATIVE_CODE"

    // $ANTLR start "COMMENT"
    public final void mCOMMENT() throws RecognitionException {
        try {
            int _type = COMMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1303:5: ( '/*' ( options {greedy=false; } : . )* '*/' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1303:7: '/*' ( options {greedy=false; } : . )* '*/'
            {
            match("/*"); 



            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1303:12: ( options {greedy=false; } : . )*
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( (LA4_0=='*') ) {
                    int LA4_1 = input.LA(2);

                    if ( (LA4_1=='/') ) {
                        alt4=2;
                    }
                    else if ( ((LA4_1 >= '\u0000' && LA4_1 <= '.')||(LA4_1 >= '0' && LA4_1 <= '\uFFFF')) ) {
                        alt4=1;
                    }


                }
                else if ( ((LA4_0 >= '\u0000' && LA4_0 <= ')')||(LA4_0 >= '+' && LA4_0 <= '\uFFFF')) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1303:40: .
            	    {
            	    matchAny(); 

            	    }
            	    break;

            	default :
            	    break loop4;
                }
            } while (true);


            match("*/"); 



            _channel=HIDDEN;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "COMMENT"

    // $ANTLR start "LINE_COMMENT"
    public final void mLINE_COMMENT() throws RecognitionException {
        try {
            int _type = LINE_COMMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1307:5: ( '//' (~ ( '\\n' | '\\r' ) )* ( '\\r' )? '\\n' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1307:7: '//' (~ ( '\\n' | '\\r' ) )* ( '\\r' )? '\\n'
            {
            match("//"); 



            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1307:12: (~ ( '\\n' | '\\r' ) )*
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( ((LA5_0 >= '\u0000' && LA5_0 <= '\t')||(LA5_0 >= '\u000B' && LA5_0 <= '\f')||(LA5_0 >= '\u000E' && LA5_0 <= '\uFFFF')) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:
            	    {
            	    if ( (input.LA(1) >= '\u0000' && input.LA(1) <= '\t')||(input.LA(1) >= '\u000B' && input.LA(1) <= '\f')||(input.LA(1) >= '\u000E' && input.LA(1) <= '\uFFFF') ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    break loop5;
                }
            } while (true);


            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1307:26: ( '\\r' )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0=='\r') ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1307:26: '\\r'
                    {
                    match('\r'); 

                    }
                    break;

            }


            match('\n'); 

            _channel=HIDDEN;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "LINE_COMMENT"

    // $ANTLR start "LETTER"
    public final void mLETTER() throws RecognitionException {
        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1313:2: ( 'A' .. 'Z' | 'a' .. 'z' | '0' .. '9' | '_' | '-' | '!' | '?' | '$' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:
            {
            if ( input.LA(1)=='!'||input.LA(1)=='$'||input.LA(1)=='-'||(input.LA(1) >= '0' && input.LA(1) <= '9')||input.LA(1)=='?'||(input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "LETTER"

    public void mTokens() throws RecognitionException {
        // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:8: ( C | CPP | DIRECTIVE_DEBUG | DIRECTIVE_ENDIF | IAS | JAVA | LIFETERM | OBJECTIVEC | OPACTIVATECTX | OPACTIVATEONFRINGE | OPAPPLYTOCONTEXT | OPBEGIN | OPBORN | OPBREAKPOINT | OPCALLP | OPCARTESIAN | OPCLONE | OPCREATEEXPR | OPDYNCONTEXT | OPENDCONFLICTGROUP | OPEQ | OPEXECUTE | OPFIND | OPFIRST | OPFOREACH | OPGATE | OPGET | OPIDENT | OPIN | OPINCL | OPINTERPRET | OPINTERRUPT | OPINTERSECT | OPJOIN | OPLAST | OPPROJECTION | OPRANDOM | OPRECALL | OPRELAX | OPREPEAT | OPREST | OPSIZE | OPSQU | OPSTARTCONFLICTGROUP | OPSUBSTRUCT | T__59 | T__60 | T__61 | T__62 | T__63 | T__64 | T__65 | T__66 | T__67 | T__68 | T__69 | T__70 | T__71 | T__72 | T__73 | T__74 | T__75 | T__76 | T__77 | T__78 | T__79 | T__80 | T__81 | T__82 | T__83 | T__84 | T__85 | ID | STRING_LITERAL | COMMA | DQUOTE | SEMICOLON | WS | NATIVE_CODE | COMMENT | LINE_COMMENT )
        int alt7=81;
        alt7 = dfa7.predict(input);
        switch (alt7) {
            case 1 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:10: C
                {
                mC(); 


                }
                break;
            case 2 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:12: CPP
                {
                mCPP(); 


                }
                break;
            case 3 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:16: DIRECTIVE_DEBUG
                {
                mDIRECTIVE_DEBUG(); 


                }
                break;
            case 4 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:32: DIRECTIVE_ENDIF
                {
                mDIRECTIVE_ENDIF(); 


                }
                break;
            case 5 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:48: IAS
                {
                mIAS(); 


                }
                break;
            case 6 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:52: JAVA
                {
                mJAVA(); 


                }
                break;
            case 7 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:57: LIFETERM
                {
                mLIFETERM(); 


                }
                break;
            case 8 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:66: OBJECTIVEC
                {
                mOBJECTIVEC(); 


                }
                break;
            case 9 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:77: OPACTIVATECTX
                {
                mOPACTIVATECTX(); 


                }
                break;
            case 10 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:91: OPACTIVATEONFRINGE
                {
                mOPACTIVATEONFRINGE(); 


                }
                break;
            case 11 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:110: OPAPPLYTOCONTEXT
                {
                mOPAPPLYTOCONTEXT(); 


                }
                break;
            case 12 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:127: OPBEGIN
                {
                mOPBEGIN(); 


                }
                break;
            case 13 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:135: OPBORN
                {
                mOPBORN(); 


                }
                break;
            case 14 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:142: OPBREAKPOINT
                {
                mOPBREAKPOINT(); 


                }
                break;
            case 15 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:155: OPCALLP
                {
                mOPCALLP(); 


                }
                break;
            case 16 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:163: OPCARTESIAN
                {
                mOPCARTESIAN(); 


                }
                break;
            case 17 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:175: OPCLONE
                {
                mOPCLONE(); 


                }
                break;
            case 18 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:183: OPCREATEEXPR
                {
                mOPCREATEEXPR(); 


                }
                break;
            case 19 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:196: OPDYNCONTEXT
                {
                mOPDYNCONTEXT(); 


                }
                break;
            case 20 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:209: OPENDCONFLICTGROUP
                {
                mOPENDCONFLICTGROUP(); 


                }
                break;
            case 21 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:228: OPEQ
                {
                mOPEQ(); 


                }
                break;
            case 22 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:233: OPEXECUTE
                {
                mOPEXECUTE(); 


                }
                break;
            case 23 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:243: OPFIND
                {
                mOPFIND(); 


                }
                break;
            case 24 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:250: OPFIRST
                {
                mOPFIRST(); 


                }
                break;
            case 25 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:258: OPFOREACH
                {
                mOPFOREACH(); 


                }
                break;
            case 26 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:268: OPGATE
                {
                mOPGATE(); 


                }
                break;
            case 27 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:275: OPGET
                {
                mOPGET(); 


                }
                break;
            case 28 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:281: OPIDENT
                {
                mOPIDENT(); 


                }
                break;
            case 29 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:289: OPIN
                {
                mOPIN(); 


                }
                break;
            case 30 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:294: OPINCL
                {
                mOPINCL(); 


                }
                break;
            case 31 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:301: OPINTERPRET
                {
                mOPINTERPRET(); 


                }
                break;
            case 32 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:313: OPINTERRUPT
                {
                mOPINTERRUPT(); 


                }
                break;
            case 33 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:325: OPINTERSECT
                {
                mOPINTERSECT(); 


                }
                break;
            case 34 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:337: OPJOIN
                {
                mOPJOIN(); 


                }
                break;
            case 35 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:344: OPLAST
                {
                mOPLAST(); 


                }
                break;
            case 36 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:351: OPPROJECTION
                {
                mOPPROJECTION(); 


                }
                break;
            case 37 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:364: OPRANDOM
                {
                mOPRANDOM(); 


                }
                break;
            case 38 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:373: OPRECALL
                {
                mOPRECALL(); 


                }
                break;
            case 39 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:382: OPRELAX
                {
                mOPRELAX(); 


                }
                break;
            case 40 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:390: OPREPEAT
                {
                mOPREPEAT(); 


                }
                break;
            case 41 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:399: OPREST
                {
                mOPREST(); 


                }
                break;
            case 42 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:406: OPSIZE
                {
                mOPSIZE(); 


                }
                break;
            case 43 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:413: OPSQU
                {
                mOPSQU(); 


                }
                break;
            case 44 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:419: OPSTARTCONFLICTGROUP
                {
                mOPSTARTCONFLICTGROUP(); 


                }
                break;
            case 45 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:440: OPSUBSTRUCT
                {
                mOPSUBSTRUCT(); 


                }
                break;
            case 46 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:452: T__59
                {
                mT__59(); 


                }
                break;
            case 47 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:458: T__60
                {
                mT__60(); 


                }
                break;
            case 48 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:464: T__61
                {
                mT__61(); 


                }
                break;
            case 49 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:470: T__62
                {
                mT__62(); 


                }
                break;
            case 50 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:476: T__63
                {
                mT__63(); 


                }
                break;
            case 51 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:482: T__64
                {
                mT__64(); 


                }
                break;
            case 52 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:488: T__65
                {
                mT__65(); 


                }
                break;
            case 53 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:494: T__66
                {
                mT__66(); 


                }
                break;
            case 54 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:500: T__67
                {
                mT__67(); 


                }
                break;
            case 55 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:506: T__68
                {
                mT__68(); 


                }
                break;
            case 56 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:512: T__69
                {
                mT__69(); 


                }
                break;
            case 57 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:518: T__70
                {
                mT__70(); 


                }
                break;
            case 58 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:524: T__71
                {
                mT__71(); 


                }
                break;
            case 59 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:530: T__72
                {
                mT__72(); 


                }
                break;
            case 60 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:536: T__73
                {
                mT__73(); 


                }
                break;
            case 61 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:542: T__74
                {
                mT__74(); 


                }
                break;
            case 62 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:548: T__75
                {
                mT__75(); 


                }
                break;
            case 63 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:554: T__76
                {
                mT__76(); 


                }
                break;
            case 64 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:560: T__77
                {
                mT__77(); 


                }
                break;
            case 65 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:566: T__78
                {
                mT__78(); 


                }
                break;
            case 66 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:572: T__79
                {
                mT__79(); 


                }
                break;
            case 67 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:578: T__80
                {
                mT__80(); 


                }
                break;
            case 68 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:584: T__81
                {
                mT__81(); 


                }
                break;
            case 69 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:590: T__82
                {
                mT__82(); 


                }
                break;
            case 70 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:596: T__83
                {
                mT__83(); 


                }
                break;
            case 71 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:602: T__84
                {
                mT__84(); 


                }
                break;
            case 72 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:608: T__85
                {
                mT__85(); 


                }
                break;
            case 73 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:614: ID
                {
                mID(); 


                }
                break;
            case 74 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:617: STRING_LITERAL
                {
                mSTRING_LITERAL(); 


                }
                break;
            case 75 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:632: COMMA
                {
                mCOMMA(); 


                }
                break;
            case 76 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:638: DQUOTE
                {
                mDQUOTE(); 


                }
                break;
            case 77 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:645: SEMICOLON
                {
                mSEMICOLON(); 


                }
                break;
            case 78 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:655: WS
                {
                mWS(); 


                }
                break;
            case 79 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:658: NATIVE_CODE
                {
                mNATIVE_CODE(); 


                }
                break;
            case 80 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:670: COMMENT
                {
                mCOMMENT(); 


                }
                break;
            case 81 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:678: LINE_COMMENT
                {
                mLINE_COMMENT(); 


                }
                break;

        }

    }


    protected DFA7 dfa7 = new DFA7(this);
    static final String DFA7_eotS =
        "\1\uffff\1\50\1\uffff\2\50\1\uffff\3\50\1\uffff\1\50\1\uffff\4\50"+
        "\1\uffff\5\50\5\uffff\13\50\3\uffff\1\136\5\uffff\1\50\2\uffff\4"+
        "\50\1\150\5\50\1\157\1\uffff\1\160\6\50\1\172\27\50\4\uffff\3\50"+
        "\1\u009a\3\50\1\uffff\6\50\2\uffff\1\u00a4\4\50\1\u00a9\3\50\1\uffff"+
        "\37\50\1\uffff\10\50\1\u00d5\1\uffff\1\u00d6\2\50\1\u00d9\1\uffff"+
        "\3\50\1\u00dd\1\u00de\5\50\1\u00e4\1\u00e5\7\50\1\u00ed\7\50\1\u00f5"+
        "\3\50\1\u00f9\7\50\1\u0101\1\50\1\u0103\1\u0104\2\uffff\1\u0105"+
        "\1\50\1\uffff\1\u0107\2\50\2\uffff\3\50\1\u010e\1\50\2\uffff\4\50"+
        "\1\u0114\2\50\1\uffff\7\50\1\uffff\3\50\1\uffff\7\50\1\uffff\1\50"+
        "\3\uffff\1\50\1\uffff\4\50\1\u012f\1\u0130\1\uffff\1\u0131\2\50"+
        "\1\u0134\1\u0135\1\uffff\6\50\1\u013c\1\u013d\3\50\1\u0141\1\50"+
        "\1\u0143\2\50\1\u0146\2\50\1\u0149\1\50\1\u014b\1\u014c\3\50\3\uffff"+
        "\1\u0150\1\50\2\uffff\6\50\2\uffff\1\u0158\1\u0159\1\50\1\uffff"+
        "\1\50\1\uffff\1\u015c\1\50\1\uffff\1\u015e\1\u015f\1\uffff\1\50"+
        "\2\uffff\3\50\1\uffff\2\50\1\u0167\1\50\1\u0169\1\50\1\u016b\2\uffff"+
        "\2\50\1\uffff\1\50\2\uffff\1\u016f\1\u0170\1\u0171\1\50\1\u0173"+
        "\1\50\1\u0175\1\uffff\1\50\1\uffff\1\50\1\uffff\3\50\3\uffff\1\u017b"+
        "\1\uffff\1\50\1\uffff\3\50\1\u0180\1\50\1\uffff\1\50\1\u0183\2\50"+
        "\1\uffff\1\50\1\u0187\1\uffff\1\50\1\u0189\1\50\1\uffff\1\50\1\uffff"+
        "\2\50\1\u018e\1\50\1\uffff\3\50\1\u0193\1\uffff";
    static final String DFA7_eofS =
        "\u0194\uffff";
    static final String DFA7_minS =
        "\1\11\1\137\1\145\2\141\1\uffff\1\157\1\141\1\145\1\uffff\1\76\1"+
        "\uffff\1\161\1\151\1\141\1\144\1\uffff\1\157\1\141\1\162\1\141\1"+
        "\151\5\uffff\1\165\1\145\1\154\1\141\1\156\1\162\1\157\1\160\1\141"+
        "\1\157\1\151\3\uffff\1\0\4\uffff\1\52\1\143\2\uffff\1\163\1\143"+
        "\1\146\1\156\1\41\1\156\1\154\1\157\1\147\1\162\1\41\1\uffff\1\41"+
        "\1\145\1\156\1\162\2\164\1\145\1\41\1\151\1\163\1\157\1\156\1\143"+
        "\1\172\1\165\1\142\1\164\1\171\1\141\1\156\1\164\1\163\2\164\1\151"+
        "\1\144\1\164\1\143\1\157\1\165\1\163\4\uffff\1\137\1\141\1\142\1"+
        "\41\1\154\1\145\1\147\1\uffff\1\164\1\154\1\164\1\156\1\151\1\156"+
        "\2\uffff\1\41\1\144\1\163\1\105\1\145\1\41\1\156\1\154\1\145\1\uffff"+
        "\1\156\1\164\1\152\1\144\2\141\1\145\1\164\2\145\1\163\1\150\1\157"+
        "\1\163\1\146\1\141\1\143\1\151\1\145\1\156\1\165\1\151\1\153\1\150"+
        "\1\152\1\162\1\165\1\137\1\160\1\166\1\152\1\uffff\1\165\1\164\1"+
        "\165\1\145\1\120\2\145\1\156\1\41\1\uffff\1\41\1\164\1\141\1\41"+
        "\1\uffff\1\164\1\165\1\162\2\41\1\145\1\157\1\154\1\170\1\141\2"+
        "\41\1\145\1\164\1\157\1\156\1\163\1\154\1\145\1\41\1\162\1\164\1"+
        "\162\1\147\1\154\1\157\1\141\1\41\1\145\1\143\1\141\1\41\1\137\1"+
        "\141\1\145\1\144\1\145\1\141\1\170\1\41\1\163\2\41\2\uffff\1\41"+
        "\1\143\1\uffff\1\41\1\144\1\162\2\uffff\1\143\1\155\1\154\1\41\1"+
        "\164\2\uffff\1\172\2\162\1\144\1\41\1\151\1\170\1\uffff\2\151\1"+
        "\156\2\145\1\156\1\147\1\uffff\1\143\1\145\1\154\1\uffff\2\137\1"+
        "\143\1\145\1\162\1\147\1\164\1\uffff\1\151\3\uffff\1\150\1\uffff"+
        "\1\145\1\165\1\145\1\164\2\41\1\uffff\1\41\1\145\1\165\2\41\1\uffff"+
        "\1\143\1\164\1\160\1\145\1\137\1\141\2\41\1\163\1\145\1\164\1\41"+
        "\1\151\1\41\1\137\1\164\1\41\1\155\1\145\1\41\1\141\2\41\1\160\1"+
        "\143\1\151\3\uffff\1\41\1\143\2\uffff\1\164\1\163\1\164\1\163\1"+
        "\150\1\154\2\uffff\2\41\1\137\1\uffff\1\172\1\uffff\1\41\1\151\1"+
        "\uffff\2\41\1\uffff\1\156\2\uffff\2\164\1\157\1\uffff\1\164\1\162"+
        "\1\41\1\151\1\41\1\151\1\41\2\uffff\1\156\1\145\1\uffff\1\166\2"+
        "\uffff\3\41\1\156\1\41\1\151\1\41\1\uffff\1\157\1\uffff\1\163\1"+
        "\uffff\1\141\1\162\1\145\3\uffff\1\41\1\uffff\1\156\1\uffff\1\156"+
        "\1\164\1\155\1\41\1\137\1\uffff\1\147\1\41\1\157\1\145\1\uffff\1"+
        "\143\1\41\1\uffff\1\162\1\41\1\137\1\uffff\1\171\1\uffff\2\137\1"+
        "\41\1\163\1\uffff\1\151\1\172\1\145\1\41\1\uffff";
    static final String DFA7_maxS =
        "\1\176\1\137\1\151\1\156\1\151\1\uffff\2\157\1\160\1\uffff\1\76"+
        "\1\uffff\1\170\1\157\1\145\1\156\1\uffff\1\157\1\141\1\162\1\145"+
        "\1\165\5\uffff\1\165\1\145\1\157\1\145\1\170\1\162\1\157\1\160\1"+
        "\162\1\157\1\151\3\uffff\1\uffff\4\uffff\1\57\1\157\2\uffff\1\163"+
        "\1\143\1\146\1\156\1\172\1\156\1\162\1\157\1\147\1\162\1\172\1\uffff"+
        "\1\172\1\145\2\162\2\164\1\145\1\172\1\151\1\163\1\157\1\156\1\163"+
        "\1\172\1\165\1\142\1\164\1\171\1\141\1\156\1\164\1\163\2\164\1\151"+
        "\1\144\2\164\1\157\1\165\1\163\4\uffff\1\160\1\141\1\142\1\172\1"+
        "\154\1\145\1\147\1\uffff\1\164\1\154\1\164\1\156\1\151\1\156\2\uffff"+
        "\1\172\1\144\1\163\1\105\1\145\1\172\1\156\1\154\1\145\1\uffff\1"+
        "\156\1\164\1\152\1\144\2\141\1\145\1\164\2\145\1\163\1\150\1\157"+
        "\1\163\1\164\1\141\1\143\1\151\1\145\1\156\1\165\1\151\1\153\1\150"+
        "\1\152\1\162\1\165\1\137\1\160\1\166\1\152\1\uffff\1\165\1\164\1"+
        "\165\1\145\1\120\2\145\1\156\1\172\1\uffff\1\172\1\164\1\141\1\172"+
        "\1\uffff\1\164\1\165\1\162\2\172\1\145\1\157\1\154\1\170\1\141\2"+
        "\172\1\145\1\164\1\157\1\156\1\163\1\154\1\145\1\172\1\162\1\164"+
        "\1\162\1\147\1\154\1\157\1\141\1\172\1\145\1\143\1\141\1\172\1\137"+
        "\1\141\1\145\1\144\1\145\1\141\1\170\1\172\1\163\2\172\2\uffff\1"+
        "\172\1\143\1\uffff\1\172\1\144\1\163\2\uffff\1\143\1\155\1\154\1"+
        "\172\1\164\2\uffff\1\172\2\162\1\144\1\172\1\151\1\170\1\uffff\1"+
        "\151\1\171\1\156\2\145\1\156\1\147\1\uffff\1\143\1\145\1\154\1\uffff"+
        "\2\137\1\143\1\145\1\162\1\147\1\164\1\uffff\1\151\3\uffff\1\150"+
        "\1\uffff\1\145\1\165\1\145\1\164\2\172\1\uffff\1\172\1\145\1\165"+
        "\2\172\1\uffff\1\143\1\164\1\160\1\145\1\137\1\141\2\172\1\163\1"+
        "\145\1\164\1\172\1\151\1\172\1\137\1\164\1\172\1\155\1\145\1\172"+
        "\1\141\2\172\1\160\1\143\1\151\3\uffff\1\172\1\143\2\uffff\1\164"+
        "\1\163\1\164\1\163\1\150\1\154\2\uffff\2\172\1\137\1\uffff\1\172"+
        "\1\uffff\1\172\1\151\1\uffff\2\172\1\uffff\1\156\2\uffff\2\164\1"+
        "\157\1\uffff\1\164\1\163\1\172\1\151\1\172\1\151\1\172\2\uffff\1"+
        "\156\1\145\1\uffff\1\166\2\uffff\3\172\1\156\1\172\1\151\1\172\1"+
        "\uffff\1\157\1\uffff\1\163\1\uffff\1\141\1\162\1\145\3\uffff\1\172"+
        "\1\uffff\1\156\1\uffff\1\156\1\164\1\155\1\172\1\137\1\uffff\1\147"+
        "\1\172\1\157\1\145\1\uffff\1\143\1\172\1\uffff\1\162\1\172\1\137"+
        "\1\uffff\1\171\1\uffff\2\137\1\172\1\163\1\uffff\1\151\1\172\1\145"+
        "\1\172\1\uffff";
    static final String DFA7_acceptS =
        "\5\uffff\1\11\3\uffff\1\22\1\uffff\1\24\4\uffff\1\37\5\uffff\1\54"+
        "\1\56\1\57\1\60\1\61\13\uffff\1\107\1\110\1\111\1\uffff\1\113\1"+
        "\115\1\116\1\117\2\uffff\1\3\1\4\13\uffff\1\23\37\uffff\1\112\1"+
        "\114\1\120\1\121\7\uffff\1\12\6\uffff\1\16\1\25\11\uffff\1\35\37"+
        "\uffff\1\5\11\uffff\1\26\4\uffff\1\33\53\uffff\1\15\1\27\2\uffff"+
        "\1\32\3\uffff\1\42\1\43\5\uffff\1\51\1\52\7\uffff\1\70\7\uffff\1"+
        "\103\3\uffff\1\1\7\uffff\1\17\1\uffff\1\21\1\14\1\30\1\uffff\1\34"+
        "\6\uffff\1\47\5\uffff\1\64\32\uffff\1\45\1\46\1\50\2\uffff\1\62"+
        "\1\63\6\uffff\1\75\1\100\3\uffff\1\105\1\uffff\1\2\2\uffff\1\76"+
        "\2\uffff\1\13\1\uffff\1\31\1\36\3\uffff\1\53\7\uffff\1\101\1\102"+
        "\2\uffff\1\6\1\uffff\1\7\1\77\7\uffff\1\67\1\uffff\1\72\1\uffff"+
        "\1\74\3\uffff\1\20\1\40\1\41\1\uffff\1\55\1\uffff\1\66\5\uffff\1"+
        "\44\4\uffff\1\106\2\uffff\1\71\3\uffff\1\65\1\uffff\1\104\4\uffff"+
        "\1\10\4\uffff\1\73";
    static final String DFA7_specialS =
        "\51\uffff\1\0\u016a\uffff}>";
    static final String[] DFA7_transitionS = {
            "\2\54\2\uffff\1\54\22\uffff\1\54\1\50\1\51\1\2\1\50\3\uffff"+
            "\1\27\1\30\2\uffff\1\52\1\12\1\31\1\56\12\50\1\5\1\53\1\55\1"+
            "\32\1\uffff\1\50\1\uffff\1\50\1\10\1\7\1\6\1\14\1\15\1\16\1"+
            "\50\1\17\1\21\1\50\1\22\3\50\1\23\1\50\1\24\1\25\7\50\1\26\1"+
            "\uffff\1\13\1\11\1\1\1\uffff\1\33\1\34\1\35\1\36\1\37\1\40\2"+
            "\50\1\3\2\50\1\4\1\41\1\50\1\42\1\43\2\50\1\44\2\50\1\45\4\50"+
            "\1\46\1\uffff\1\47\1\20",
            "\1\57",
            "\1\61\3\uffff\1\60",
            "\1\62\14\uffff\1\63",
            "\1\65\7\uffff\1\64",
            "",
            "\1\66",
            "\1\70\12\uffff\1\71\2\uffff\1\67",
            "\1\72\11\uffff\1\73\1\74",
            "",
            "\1\75",
            "",
            "\1\76\6\uffff\1\77",
            "\1\100\5\uffff\1\101",
            "\1\102\3\uffff\1\103",
            "\1\104\11\uffff\1\105",
            "",
            "\1\106",
            "\1\107",
            "\1\110",
            "\1\111\3\uffff\1\112",
            "\1\113\7\uffff\1\114\3\uffff\1\115",
            "",
            "",
            "",
            "",
            "",
            "\1\116",
            "\1\117",
            "\1\120\2\uffff\1\121",
            "\1\122\3\uffff\1\123",
            "\1\124\11\uffff\1\125",
            "\1\126",
            "\1\127",
            "\1\130",
            "\1\131\20\uffff\1\132",
            "\1\133",
            "\1\134",
            "",
            "",
            "",
            "\0\135",
            "",
            "",
            "",
            "",
            "\1\137\4\uffff\1\140",
            "\1\141\6\uffff\1\142\4\uffff\1\143",
            "",
            "",
            "\1\144",
            "\1\145",
            "\1\146",
            "\1\147",
            "\1\50\2\uffff\1\50\10\uffff\2\50\1\uffff\12\50\5\uffff\1\50"+
            "\1\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\151",
            "\1\152\5\uffff\1\153",
            "\1\154",
            "\1\155",
            "\1\156",
            "\1\50\2\uffff\1\50\10\uffff\2\50\1\uffff\12\50\5\uffff\1\50"+
            "\1\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "",
            "\1\50\2\uffff\1\50\10\uffff\2\50\1\uffff\12\50\5\uffff\1\50"+
            "\1\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\161",
            "\1\162\3\uffff\1\163",
            "\1\164",
            "\1\165",
            "\1\166",
            "\1\167",
            "\1\50\2\uffff\1\50\10\uffff\2\50\1\uffff\12\50\5\uffff\1\50"+
            "\1\uffff\32\50\4\uffff\1\50\1\uffff\2\50\1\170\20\50\1\171\6"+
            "\50",
            "\1\173",
            "\1\174",
            "\1\175",
            "\1\176",
            "\1\177\10\uffff\1\u0080\3\uffff\1\u0081\2\uffff\1\u0082",
            "\1\u0083",
            "\1\u0084",
            "\1\u0085",
            "\1\u0086",
            "\1\u0087",
            "\1\u0088",
            "\1\u0089",
            "\1\u008a",
            "\1\u008b",
            "\1\u008c",
            "\1\u008d",
            "\1\u008e",
            "\1\u008f",
            "\1\u0090",
            "\1\u0091\20\uffff\1\u0092",
            "\1\u0093",
            "\1\u0094",
            "\1\u0095",
            "",
            "",
            "",
            "",
            "\1\u0096\20\uffff\1\u0097",
            "\1\u0098",
            "\1\u0099",
            "\1\50\2\uffff\1\50\10\uffff\2\50\1\uffff\12\50\5\uffff\1\50"+
            "\1\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\u009b",
            "\1\u009c",
            "\1\u009d",
            "",
            "\1\u009e",
            "\1\u009f",
            "\1\u00a0",
            "\1\u00a1",
            "\1\u00a2",
            "\1\u00a3",
            "",
            "",
            "\1\50\2\uffff\1\50\10\uffff\2\50\1\uffff\12\50\5\uffff\1\50"+
            "\1\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\u00a5",
            "\1\u00a6",
            "\1\u00a7",
            "\1\u00a8",
            "\1\50\2\uffff\1\50\10\uffff\2\50\1\uffff\12\50\5\uffff\1\50"+
            "\1\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\u00aa",
            "\1\u00ab",
            "\1\u00ac",
            "",
            "\1\u00ad",
            "\1\u00ae",
            "\1\u00af",
            "\1\u00b0",
            "\1\u00b1",
            "\1\u00b2",
            "\1\u00b3",
            "\1\u00b4",
            "\1\u00b5",
            "\1\u00b6",
            "\1\u00b7",
            "\1\u00b8",
            "\1\u00b9",
            "\1\u00ba",
            "\1\u00bb\15\uffff\1\u00bc",
            "\1\u00bd",
            "\1\u00be",
            "\1\u00bf",
            "\1\u00c0",
            "\1\u00c1",
            "\1\u00c2",
            "\1\u00c3",
            "\1\u00c4",
            "\1\u00c5",
            "\1\u00c6",
            "\1\u00c7",
            "\1\u00c8",
            "\1\u00c9",
            "\1\u00ca",
            "\1\u00cb",
            "\1\u00cc",
            "",
            "\1\u00cd",
            "\1\u00ce",
            "\1\u00cf",
            "\1\u00d0",
            "\1\u00d1",
            "\1\u00d2",
            "\1\u00d3",
            "\1\u00d4",
            "\1\50\2\uffff\1\50\10\uffff\2\50\1\uffff\12\50\5\uffff\1\50"+
            "\1\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "",
            "\1\50\2\uffff\1\50\10\uffff\2\50\1\uffff\12\50\5\uffff\1\50"+
            "\1\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\u00d7",
            "\1\u00d8",
            "\1\50\2\uffff\1\50\10\uffff\2\50\1\uffff\12\50\5\uffff\1\50"+
            "\1\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "",
            "\1\u00da",
            "\1\u00db",
            "\1\u00dc",
            "\1\50\2\uffff\1\50\10\uffff\2\50\1\uffff\12\50\5\uffff\1\50"+
            "\1\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\50\2\uffff\1\50\10\uffff\2\50\1\uffff\12\50\5\uffff\1\50"+
            "\1\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\u00df",
            "\1\u00e0",
            "\1\u00e1",
            "\1\u00e2",
            "\1\u00e3",
            "\1\50\2\uffff\1\50\10\uffff\2\50\1\uffff\12\50\5\uffff\1\50"+
            "\1\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\50\2\uffff\1\50\10\uffff\2\50\1\uffff\12\50\5\uffff\1\50"+
            "\1\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\u00e6",
            "\1\u00e7",
            "\1\u00e8",
            "\1\u00e9",
            "\1\u00ea",
            "\1\u00eb",
            "\1\u00ec",
            "\1\50\2\uffff\1\50\10\uffff\2\50\1\uffff\12\50\5\uffff\1\50"+
            "\1\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\u00ee",
            "\1\u00ef",
            "\1\u00f0",
            "\1\u00f1",
            "\1\u00f2",
            "\1\u00f3",
            "\1\u00f4",
            "\1\50\2\uffff\1\50\10\uffff\2\50\1\uffff\12\50\5\uffff\1\50"+
            "\1\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\u00f6",
            "\1\u00f7",
            "\1\u00f8",
            "\1\50\2\uffff\1\50\10\uffff\2\50\1\uffff\12\50\5\uffff\1\50"+
            "\1\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\u00fa",
            "\1\u00fb",
            "\1\u00fc",
            "\1\u00fd",
            "\1\u00fe",
            "\1\u00ff",
            "\1\u0100",
            "\1\50\2\uffff\1\50\10\uffff\2\50\1\uffff\12\50\5\uffff\1\50"+
            "\1\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\u0102",
            "\1\50\2\uffff\1\50\10\uffff\2\50\1\uffff\12\50\5\uffff\1\50"+
            "\1\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\50\2\uffff\1\50\10\uffff\2\50\1\uffff\12\50\5\uffff\1\50"+
            "\1\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "",
            "",
            "\1\50\2\uffff\1\50\10\uffff\2\50\1\uffff\12\50\5\uffff\1\50"+
            "\1\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\u0106",
            "",
            "\1\50\2\uffff\1\50\10\uffff\2\50\1\uffff\12\50\5\uffff\1\50"+
            "\1\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\u0108",
            "\1\u0109\1\u010a",
            "",
            "",
            "\1\u010b",
            "\1\u010c",
            "\1\u010d",
            "\1\50\2\uffff\1\50\10\uffff\2\50\1\uffff\12\50\5\uffff\1\50"+
            "\1\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\u010f",
            "",
            "",
            "\1\u0110",
            "\1\u0111",
            "\1\u0112",
            "\1\u0113",
            "\1\50\2\uffff\1\50\10\uffff\2\50\1\uffff\12\50\5\uffff\1\50"+
            "\1\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\u0115",
            "\1\u0116",
            "",
            "\1\u0117",
            "\1\u0118\17\uffff\1\u0119",
            "\1\u011a",
            "\1\u011b",
            "\1\u011c",
            "\1\u011d",
            "\1\u011e",
            "",
            "\1\u011f",
            "\1\u0120",
            "\1\u0121",
            "",
            "\1\u0122",
            "\1\u0123",
            "\1\u0124",
            "\1\u0125",
            "\1\u0126",
            "\1\u0127",
            "\1\u0128",
            "",
            "\1\u0129",
            "",
            "",
            "",
            "\1\u012a",
            "",
            "\1\u012b",
            "\1\u012c",
            "\1\u012d",
            "\1\u012e",
            "\1\50\2\uffff\1\50\10\uffff\2\50\1\uffff\12\50\5\uffff\1\50"+
            "\1\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\50\2\uffff\1\50\10\uffff\2\50\1\uffff\12\50\5\uffff\1\50"+
            "\1\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "",
            "\1\50\2\uffff\1\50\10\uffff\2\50\1\uffff\12\50\5\uffff\1\50"+
            "\1\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\u0132",
            "\1\u0133",
            "\1\50\2\uffff\1\50\10\uffff\2\50\1\uffff\12\50\5\uffff\1\50"+
            "\1\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\50\2\uffff\1\50\10\uffff\2\50\1\uffff\12\50\5\uffff\1\50"+
            "\1\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "",
            "\1\u0136",
            "\1\u0137",
            "\1\u0138",
            "\1\u0139",
            "\1\u013a",
            "\1\u013b",
            "\1\50\2\uffff\1\50\10\uffff\2\50\1\uffff\12\50\5\uffff\1\50"+
            "\1\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\50\2\uffff\1\50\10\uffff\2\50\1\uffff\12\50\5\uffff\1\50"+
            "\1\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\u013e",
            "\1\u013f",
            "\1\u0140",
            "\1\50\2\uffff\1\50\10\uffff\2\50\1\uffff\12\50\5\uffff\1\50"+
            "\1\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\u0142",
            "\1\50\2\uffff\1\50\10\uffff\2\50\1\uffff\12\50\5\uffff\1\50"+
            "\1\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\u0144",
            "\1\u0145",
            "\1\50\2\uffff\1\50\10\uffff\2\50\1\uffff\12\50\5\uffff\1\50"+
            "\1\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\u0147",
            "\1\u0148",
            "\1\50\2\uffff\1\50\10\uffff\2\50\1\uffff\12\50\5\uffff\1\50"+
            "\1\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\u014a",
            "\1\50\2\uffff\1\50\10\uffff\2\50\1\uffff\12\50\5\uffff\1\50"+
            "\1\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\50\2\uffff\1\50\10\uffff\2\50\1\uffff\12\50\5\uffff\1\50"+
            "\1\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\u014d",
            "\1\u014e",
            "\1\u014f",
            "",
            "",
            "",
            "\1\50\2\uffff\1\50\10\uffff\2\50\1\uffff\12\50\5\uffff\1\50"+
            "\1\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\u0151",
            "",
            "",
            "\1\u0152",
            "\1\u0153",
            "\1\u0154",
            "\1\u0155",
            "\1\u0156",
            "\1\u0157",
            "",
            "",
            "\1\50\2\uffff\1\50\10\uffff\2\50\1\uffff\12\50\5\uffff\1\50"+
            "\1\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\50\2\uffff\1\50\10\uffff\2\50\1\uffff\12\50\5\uffff\1\50"+
            "\1\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\u015a",
            "",
            "\1\u015b",
            "",
            "\1\50\2\uffff\1\50\10\uffff\2\50\1\uffff\12\50\5\uffff\1\50"+
            "\1\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\u015d",
            "",
            "\1\50\2\uffff\1\50\10\uffff\2\50\1\uffff\12\50\5\uffff\1\50"+
            "\1\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\50\2\uffff\1\50\10\uffff\2\50\1\uffff\12\50\5\uffff\1\50"+
            "\1\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "",
            "\1\u0160",
            "",
            "",
            "\1\u0161",
            "\1\u0162",
            "\1\u0163",
            "",
            "\1\u0164",
            "\1\u0165\1\u0166",
            "\1\50\2\uffff\1\50\10\uffff\2\50\1\uffff\12\50\5\uffff\1\50"+
            "\1\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\u0168",
            "\1\50\2\uffff\1\50\10\uffff\2\50\1\uffff\12\50\5\uffff\1\50"+
            "\1\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\u016a",
            "\1\50\2\uffff\1\50\10\uffff\2\50\1\uffff\12\50\5\uffff\1\50"+
            "\1\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "",
            "",
            "\1\u016c",
            "\1\u016d",
            "",
            "\1\u016e",
            "",
            "",
            "\1\50\2\uffff\1\50\10\uffff\2\50\1\uffff\12\50\5\uffff\1\50"+
            "\1\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\50\2\uffff\1\50\10\uffff\2\50\1\uffff\12\50\5\uffff\1\50"+
            "\1\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\50\2\uffff\1\50\10\uffff\2\50\1\uffff\12\50\5\uffff\1\50"+
            "\1\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\u0172",
            "\1\50\2\uffff\1\50\10\uffff\2\50\1\uffff\12\50\5\uffff\1\50"+
            "\1\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\u0174",
            "\1\50\2\uffff\1\50\10\uffff\2\50\1\uffff\12\50\5\uffff\1\50"+
            "\1\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "",
            "\1\u0176",
            "",
            "\1\u0177",
            "",
            "\1\u0178",
            "\1\u0179",
            "\1\u017a",
            "",
            "",
            "",
            "\1\50\2\uffff\1\50\10\uffff\2\50\1\uffff\12\50\5\uffff\1\50"+
            "\1\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "",
            "\1\u017c",
            "",
            "\1\u017d",
            "\1\u017e",
            "\1\u017f",
            "\1\50\2\uffff\1\50\10\uffff\2\50\1\uffff\12\50\5\uffff\1\50"+
            "\1\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\u0181",
            "",
            "\1\u0182",
            "\1\50\2\uffff\1\50\10\uffff\2\50\1\uffff\12\50\5\uffff\1\50"+
            "\1\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\u0184",
            "\1\u0185",
            "",
            "\1\u0186",
            "\1\50\2\uffff\1\50\10\uffff\2\50\1\uffff\12\50\5\uffff\1\50"+
            "\1\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "",
            "\1\u0188",
            "\1\50\2\uffff\1\50\10\uffff\2\50\1\uffff\12\50\5\uffff\1\50"+
            "\1\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\u018a",
            "",
            "\1\u018b",
            "",
            "\1\u018c",
            "\1\u018d",
            "\1\50\2\uffff\1\50\10\uffff\2\50\1\uffff\12\50\5\uffff\1\50"+
            "\1\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\u018f",
            "",
            "\1\u0190",
            "\1\u0191",
            "\1\u0192",
            "\1\50\2\uffff\1\50\10\uffff\2\50\1\uffff\12\50\5\uffff\1\50"+
            "\1\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            ""
    };

    static final short[] DFA7_eot = DFA.unpackEncodedString(DFA7_eotS);
    static final short[] DFA7_eof = DFA.unpackEncodedString(DFA7_eofS);
    static final char[] DFA7_min = DFA.unpackEncodedStringToUnsignedChars(DFA7_minS);
    static final char[] DFA7_max = DFA.unpackEncodedStringToUnsignedChars(DFA7_maxS);
    static final short[] DFA7_accept = DFA.unpackEncodedString(DFA7_acceptS);
    static final short[] DFA7_special = DFA.unpackEncodedString(DFA7_specialS);
    static final short[][] DFA7_transition;

    static {
        int numStates = DFA7_transitionS.length;
        DFA7_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA7_transition[i] = DFA.unpackEncodedString(DFA7_transitionS[i]);
        }
    }

    class DFA7 extends DFA {

        public DFA7(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 7;
            this.eot = DFA7_eot;
            this.eof = DFA7_eof;
            this.min = DFA7_min;
            this.max = DFA7_max;
            this.accept = DFA7_accept;
            this.special = DFA7_special;
            this.transition = DFA7_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( C | CPP | DIRECTIVE_DEBUG | DIRECTIVE_ENDIF | IAS | JAVA | LIFETERM | OBJECTIVEC | OPACTIVATECTX | OPACTIVATEONFRINGE | OPAPPLYTOCONTEXT | OPBEGIN | OPBORN | OPBREAKPOINT | OPCALLP | OPCARTESIAN | OPCLONE | OPCREATEEXPR | OPDYNCONTEXT | OPENDCONFLICTGROUP | OPEQ | OPEXECUTE | OPFIND | OPFIRST | OPFOREACH | OPGATE | OPGET | OPIDENT | OPIN | OPINCL | OPINTERPRET | OPINTERRUPT | OPINTERSECT | OPJOIN | OPLAST | OPPROJECTION | OPRANDOM | OPRECALL | OPRELAX | OPREPEAT | OPREST | OPSIZE | OPSQU | OPSTARTCONFLICTGROUP | OPSUBSTRUCT | T__59 | T__60 | T__61 | T__62 | T__63 | T__64 | T__65 | T__66 | T__67 | T__68 | T__69 | T__70 | T__71 | T__72 | T__73 | T__74 | T__75 | T__76 | T__77 | T__78 | T__79 | T__80 | T__81 | T__82 | T__83 | T__84 | T__85 | ID | STRING_LITERAL | COMMA | DQUOTE | SEMICOLON | WS | NATIVE_CODE | COMMENT | LINE_COMMENT );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            IntStream input = _input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA7_41 = input.LA(1);

                        s = -1;
                        if ( ((LA7_41 >= '\u0000' && LA7_41 <= '\uFFFF')) ) {s = 93;}

                        else s = 94;

                        if ( s>=0 ) return s;
                        break;
            }
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 7, _s, input);
            error(nvae);
            throw nvae;
        }

    }
 

}