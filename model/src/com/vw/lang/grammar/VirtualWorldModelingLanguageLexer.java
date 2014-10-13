// $ANTLR 3.4 C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g 2014-10-13 16:22:28
 
package com.vw.lang.grammar;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked"})
public class VirtualWorldModelingLanguageLexer extends Lexer {
    public static final int EOF=-1;
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
    public static final int T__86=86;
    public static final int T__87=87;
    public static final int T__88=88;
    public static final int T__89=89;
    public static final int T__90=90;
    public static final int T__91=91;
    public static final int T__92=92;
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
    public static final int OPACTIVATE=17;
    public static final int OPACTIVATECTX=18;
    public static final int OPACTIVATEONFRINGE=19;
    public static final int OPAPPLYTOCONTEXT=20;
    public static final int OPBEGIN=21;
    public static final int OPBORN=22;
    public static final int OPBREAKPOINT=23;
    public static final int OPCALLP=24;
    public static final int OPCARTESIAN=25;
    public static final int OPCLONE=26;
    public static final int OPCREATEEXPR=27;
    public static final int OPDYNCONTEXT=28;
    public static final int OPENDCONFLICTGROUP=29;
    public static final int OPEQ=30;
    public static final int OPEXECUTE=31;
    public static final int OPFIND=32;
    public static final int OPFIRST=33;
    public static final int OPFOREACH=34;
    public static final int OPGATE=35;
    public static final int OPGET=36;
    public static final int OPIDENT=37;
    public static final int OPIN=38;
    public static final int OPINCL=39;
    public static final int OPINTERPRET=40;
    public static final int OPINTERRUPT=41;
    public static final int OPINTERSECT=42;
    public static final int OPJOIN=43;
    public static final int OPLAST=44;
    public static final int OPPROJECTION=45;
    public static final int OPRANDOM=46;
    public static final int OPRECALL=47;
    public static final int OPRELAX=48;
    public static final int OPREPEAT=49;
    public static final int OPREST=50;
    public static final int OPSIZE=51;
    public static final int OPSQU=52;
    public static final int OPSTARTCONFLICTGROUP=53;
    public static final int OPSUBSTRUCT=54;
    public static final int P_DEBUG=55;
    public static final int P_ELSE=56;
    public static final int P_ENDIF=57;
    public static final int P_IF=58;
    public static final int P_OP_AND=59;
    public static final int P_OP_B=60;
    public static final int P_OP_E=61;
    public static final int P_OP_L=62;
    public static final int P_OP_OR=63;
    public static final int SEMICOLON=64;
    public static final int STRING_LITERAL=65;
    public static final int WS=66;

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

    // $ANTLR start "IAS"
    public final void mIAS() throws RecognitionException {
        try {
            int _type = IAS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:11:5: ( 'ias' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:11:7: 'ias'
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
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:12:6: ( '__java__' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:12:8: '__java__'
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
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:13:10: ( 'lifeterm' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:13:12: 'lifeterm'
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
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:14:12: ( '__objective_c__' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:14:14: '__objective_c__'
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

    // $ANTLR start "OPACTIVATE"
    public final void mOPACTIVATE() throws RecognitionException {
        try {
            int _type = OPACTIVATE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:15:12: ( 'Activate' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:15:14: 'Activate'
            {
            match("Activate"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "OPACTIVATE"

    // $ANTLR start "OPACTIVATECTX"
    public final void mOPACTIVATECTX() throws RecognitionException {
        try {
            int _type = OPACTIVATECTX;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:16:15: ( ':' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:16:17: ':'
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
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:17:20: ( 'Do' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:17:22: 'Do'
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
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:18:18: ( 'Context' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:18:20: 'Context'
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
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:19:9: ( 'Begin' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:19:11: 'Begin'
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
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:20:8: ( 'Born' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:20:10: 'Born'
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
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:21:14: ( 'Bp' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:21:16: 'Bp'
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
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:22:9: ( 'CallP' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:22:11: 'CallP'
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
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:23:13: ( 'Cartesian' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:23:15: 'Cartesian'
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
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:24:9: ( 'Clone' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:24:11: 'Clone'
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
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:25:14: ( '^' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:25:16: '^'
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
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:26:14: ( '->' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:26:16: '->'
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
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:27:20: ( ']' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:27:22: ']'
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
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:28:6: ( 'Eq' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:28:8: 'Eq'
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
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:29:11: ( 'Exe' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:29:13: 'Exe'
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
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:30:8: ( 'Find' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:30:10: 'Find'
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
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:31:9: ( 'First' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:31:11: 'First'
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
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:32:11: ( 'ForEach' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:32:13: 'ForEach'
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
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:33:8: ( 'Gate' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:33:10: 'Gate'
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
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:34:7: ( 'Get' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:34:9: 'Get'
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
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:35:9: ( 'Ident' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:35:11: 'Ident'
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
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:36:6: ( 'In' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:36:8: 'In'
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
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:37:8: ( 'Include' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:37:10: 'Include'
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
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:38:13: ( '~' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:38:15: '~'
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
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:39:13: ( 'Interrupt' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:39:15: 'Interrupt'
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
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:40:13: ( 'Intersect' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:40:15: 'Intersect'
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
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:41:8: ( 'Join' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:41:10: 'Join'
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
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:42:8: ( 'Last' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:42:10: 'Last'
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
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:43:14: ( 'Projection' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:43:16: 'Projection'
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
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:44:10: ( 'Random' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:44:12: 'Random'
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
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:45:10: ( 'Recall' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:45:12: 'Recall'
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
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:46:9: ( 'Relax' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:46:11: 'Relax'
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
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:47:10: ( 'Repeat' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:47:12: 'Repeat'
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
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:48:8: ( 'Rest' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:48:10: 'Rest'
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
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:49:8: ( 'Size' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:49:10: 'Size'
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
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:50:7: ( 'Squeeze' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:50:9: 'Squeeze'
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
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:51:22: ( '[' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:51:24: '['
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
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:52:13: ( 'Substruct' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:52:15: 'Substruct'
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

    // $ANTLR start "P_DEBUG"
    public final void mP_DEBUG() throws RecognitionException {
        try {
            int _type = P_DEBUG;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:53:9: ( '#if_debug' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:53:11: '#if_debug'
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
    // $ANTLR end "P_DEBUG"

    // $ANTLR start "P_ELSE"
    public final void mP_ELSE() throws RecognitionException {
        try {
            int _type = P_ELSE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:54:8: ( '#else' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:54:10: '#else'
            {
            match("#else"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "P_ELSE"

    // $ANTLR start "P_ENDIF"
    public final void mP_ENDIF() throws RecognitionException {
        try {
            int _type = P_ENDIF;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:55:9: ( '#endif' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:55:11: '#endif'
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
    // $ANTLR end "P_ENDIF"

    // $ANTLR start "P_IF"
    public final void mP_IF() throws RecognitionException {
        try {
            int _type = P_IF;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:56:6: ( '#if' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:56:8: '#if'
            {
            match("#if"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "P_IF"

    // $ANTLR start "P_OP_AND"
    public final void mP_OP_AND() throws RecognitionException {
        try {
            int _type = P_OP_AND;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:57:10: ( '&' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:57:12: '&'
            {
            match('&'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "P_OP_AND"

    // $ANTLR start "P_OP_B"
    public final void mP_OP_B() throws RecognitionException {
        try {
            int _type = P_OP_B;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:58:8: ( '>' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:58:10: '>'
            {
            match('>'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "P_OP_B"

    // $ANTLR start "P_OP_E"
    public final void mP_OP_E() throws RecognitionException {
        try {
            int _type = P_OP_E;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:59:8: ( '=' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:59:10: '='
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
    // $ANTLR end "P_OP_E"

    // $ANTLR start "P_OP_L"
    public final void mP_OP_L() throws RecognitionException {
        try {
            int _type = P_OP_L;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:60:8: ( '<' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:60:10: '<'
            {
            match('<'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "P_OP_L"

    // $ANTLR start "P_OP_OR"
    public final void mP_OP_OR() throws RecognitionException {
        try {
            int _type = P_OP_OR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:61:9: ( '|' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:61:11: '|'
            {
            match('|'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "P_OP_OR"

    // $ANTLR start "T__67"
    public final void mT__67() throws RecognitionException {
        try {
            int _type = T__67;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:62:7: ( '(' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:62:9: '('
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
    // $ANTLR end "T__67"

    // $ANTLR start "T__68"
    public final void mT__68() throws RecognitionException {
        try {
            int _type = T__68;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:63:7: ( ')' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:63:9: ')'
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
    // $ANTLR end "T__68"

    // $ANTLR start "T__69"
    public final void mT__69() throws RecognitionException {
        try {
            int _type = T__69;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:64:7: ( '.' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:64:9: '.'
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
    // $ANTLR end "T__69"

    // $ANTLR start "T__70"
    public final void mT__70() throws RecognitionException {
        try {
            int _type = T__70;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:65:7: ( 'author' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:65:9: 'author'
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
    // $ANTLR end "T__70"

    // $ANTLR start "T__71"
    public final void mT__71() throws RecognitionException {
        try {
            int _type = T__71;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:66:7: ( 'beyond' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:66:9: 'beyond'
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
    // $ANTLR end "T__71"

    // $ANTLR start "T__72"
    public final void mT__72() throws RecognitionException {
        try {
            int _type = T__72;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:67:7: ( 'class' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:67:9: 'class'
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
    // $ANTLR end "T__72"

    // $ANTLR start "T__73"
    public final void mT__73() throws RecognitionException {
        try {
            int _type = T__73;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:68:7: ( 'conflictring' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:68:9: 'conflictring'
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
    // $ANTLR end "T__73"

    // $ANTLR start "T__74"
    public final void mT__74() throws RecognitionException {
        try {
            int _type = T__74;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:69:7: ( 'conflicts' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:69:9: 'conflicts'
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
    // $ANTLR end "T__74"

    // $ANTLR start "T__75"
    public final void mT__75() throws RecognitionException {
        try {
            int _type = T__75;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:70:7: ( 'contexts' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:70:9: 'contexts'
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
    // $ANTLR end "T__75"

    // $ANTLR start "T__76"
    public final void mT__76() throws RecognitionException {
        try {
            int _type = T__76;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:71:7: ( 'data' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:71:9: 'data'
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
    // $ANTLR end "T__76"

    // $ANTLR start "T__77"
    public final void mT__77() throws RecognitionException {
        try {
            int _type = T__77;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:72:7: ( 'description' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:72:9: 'description'
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
    // $ANTLR end "T__77"

    // $ANTLR start "T__78"
    public final void mT__78() throws RecognitionException {
        try {
            int _type = T__78;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:73:7: ( 'entities' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:73:9: 'entities'
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
    // $ANTLR end "T__78"

    // $ANTLR start "T__79"
    public final void mT__79() throws RecognitionException {
        try {
            int _type = T__79;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:74:7: ( 'entity_history_size' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:74:9: 'entity_history_size'
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
    // $ANTLR end "T__79"

    // $ANTLR start "T__80"
    public final void mT__80() throws RecognitionException {
        try {
            int _type = T__80;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:75:7: ( 'external' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:75:9: 'external'
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
    // $ANTLR end "T__80"

    // $ANTLR start "T__81"
    public final void mT__81() throws RecognitionException {
        try {
            int _type = T__81;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:76:7: ( 'fringe' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:76:9: 'fringe'
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
    // $ANTLR end "T__81"

    // $ANTLR start "T__82"
    public final void mT__82() throws RecognitionException {
        try {
            int _type = T__82;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:77:7: ( 'include' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:77:9: 'include'
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
    // $ANTLR end "T__82"

    // $ANTLR start "T__83"
    public final void mT__83() throws RecognitionException {
        try {
            int _type = T__83;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:78:7: ( 'language' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:78:9: 'language'
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
    // $ANTLR end "T__83"

    // $ANTLR start "T__84"
    public final void mT__84() throws RecognitionException {
        try {
            int _type = T__84;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:79:7: ( 'module' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:79:9: 'module'
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
    // $ANTLR end "T__84"

    // $ANTLR start "T__85"
    public final void mT__85() throws RecognitionException {
        try {
            int _type = T__85;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:80:7: ( 'options' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:80:9: 'options'
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
    // $ANTLR end "T__85"

    // $ANTLR start "T__86"
    public final void mT__86() throws RecognitionException {
        try {
            int _type = T__86;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:81:7: ( 'package' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:81:9: 'package'
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
    // $ANTLR end "T__86"

    // $ANTLR start "T__87"
    public final void mT__87() throws RecognitionException {
        try {
            int _type = T__87;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:82:7: ( 'path' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:82:9: 'path'
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
    // $ANTLR end "T__87"

    // $ANTLR start "T__88"
    public final void mT__88() throws RecognitionException {
        try {
            int _type = T__88;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:83:7: ( 'project_name' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:83:9: 'project_name'
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
    // $ANTLR end "T__88"

    // $ANTLR start "T__89"
    public final void mT__89() throws RecognitionException {
        try {
            int _type = T__89;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:84:7: ( 'source' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:84:9: 'source'
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
    // $ANTLR end "T__89"

    // $ANTLR start "T__90"
    public final void mT__90() throws RecognitionException {
        try {
            int _type = T__90;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:85:7: ( 'visualizer' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:85:9: 'visualizer'
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
    // $ANTLR end "T__90"

    // $ANTLR start "T__91"
    public final void mT__91() throws RecognitionException {
        try {
            int _type = T__91;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:86:7: ( '{' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:86:9: '{'
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
    // $ANTLR end "T__91"

    // $ANTLR start "T__92"
    public final void mT__92() throws RecognitionException {
        try {
            int _type = T__92;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:87:7: ( '}' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:87:9: '}'
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
    // $ANTLR end "T__92"

    // $ANTLR start "ID"
    public final void mID() throws RecognitionException {
        try {
            int _type = ID;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1194:5: ( LETTER ( LETTER | '.' )* )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1194:7: LETTER ( LETTER | '.' )*
            {
            mLETTER(); 


            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1194:14: ( LETTER | '.' )*
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
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1199:5: ( '\"' (~ ( '\"' ) )* '\"' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1199:8: '\"' (~ ( '\"' ) )* '\"'
            {
            match('\"'); 

            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1199:12: (~ ( '\"' ) )*
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
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1351:5: ( ',' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1351:7: ','
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
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1355:5: ( '\"' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1355:7: '\"'
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
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1359:5: ( ';' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1359:7: ';'
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
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1363:5: ( ( ' ' | '\\t' | '\\n' | '\\r' ) )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1363:7: ( ' ' | '\\t' | '\\n' | '\\r' )
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
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1368:5: ( '<*' ( . )* '*>' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1368:7: '<*' ( . )* '*>'
            {
            match("<*"); 



            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1368:12: ( . )*
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
            	    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1368:12: .
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
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1372:5: ( '/*' ( options {greedy=false; } : . )* '*/' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1372:7: '/*' ( options {greedy=false; } : . )* '*/'
            {
            match("/*"); 



            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1372:12: ( options {greedy=false; } : . )*
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
            	    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1372:40: .
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
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1376:5: ( '//' (~ ( '\\n' | '\\r' ) )* ( '\\r' )? '\\n' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1376:7: '//' (~ ( '\\n' | '\\r' ) )* ( '\\r' )? '\\n'
            {
            match("//"); 



            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1376:12: (~ ( '\\n' | '\\r' ) )*
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


            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1376:26: ( '\\r' )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0=='\r') ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1376:26: '\\r'
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
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1382:2: ( 'A' .. 'Z' | 'a' .. 'z' | '0' .. '9' | '_' | '-' | '!' | '?' | '$' )
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
        // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:8: ( C | CPP | IAS | JAVA | LIFETERM | OBJECTIVEC | OPACTIVATE | OPACTIVATECTX | OPACTIVATEONFRINGE | OPAPPLYTOCONTEXT | OPBEGIN | OPBORN | OPBREAKPOINT | OPCALLP | OPCARTESIAN | OPCLONE | OPCREATEEXPR | OPDYNCONTEXT | OPENDCONFLICTGROUP | OPEQ | OPEXECUTE | OPFIND | OPFIRST | OPFOREACH | OPGATE | OPGET | OPIDENT | OPIN | OPINCL | OPINTERPRET | OPINTERRUPT | OPINTERSECT | OPJOIN | OPLAST | OPPROJECTION | OPRANDOM | OPRECALL | OPRELAX | OPREPEAT | OPREST | OPSIZE | OPSQU | OPSTARTCONFLICTGROUP | OPSUBSTRUCT | P_DEBUG | P_ELSE | P_ENDIF | P_IF | P_OP_AND | P_OP_B | P_OP_E | P_OP_L | P_OP_OR | T__67 | T__68 | T__69 | T__70 | T__71 | T__72 | T__73 | T__74 | T__75 | T__76 | T__77 | T__78 | T__79 | T__80 | T__81 | T__82 | T__83 | T__84 | T__85 | T__86 | T__87 | T__88 | T__89 | T__90 | T__91 | T__92 | ID | STRING_LITERAL | COMMA | DQUOTE | SEMICOLON | WS | NATIVE_CODE | COMMENT | LINE_COMMENT )
        int alt7=88;
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
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:16: IAS
                {
                mIAS(); 


                }
                break;
            case 4 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:20: JAVA
                {
                mJAVA(); 


                }
                break;
            case 5 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:25: LIFETERM
                {
                mLIFETERM(); 


                }
                break;
            case 6 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:34: OBJECTIVEC
                {
                mOBJECTIVEC(); 


                }
                break;
            case 7 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:45: OPACTIVATE
                {
                mOPACTIVATE(); 


                }
                break;
            case 8 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:56: OPACTIVATECTX
                {
                mOPACTIVATECTX(); 


                }
                break;
            case 9 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:70: OPACTIVATEONFRINGE
                {
                mOPACTIVATEONFRINGE(); 


                }
                break;
            case 10 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:89: OPAPPLYTOCONTEXT
                {
                mOPAPPLYTOCONTEXT(); 


                }
                break;
            case 11 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:106: OPBEGIN
                {
                mOPBEGIN(); 


                }
                break;
            case 12 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:114: OPBORN
                {
                mOPBORN(); 


                }
                break;
            case 13 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:121: OPBREAKPOINT
                {
                mOPBREAKPOINT(); 


                }
                break;
            case 14 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:134: OPCALLP
                {
                mOPCALLP(); 


                }
                break;
            case 15 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:142: OPCARTESIAN
                {
                mOPCARTESIAN(); 


                }
                break;
            case 16 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:154: OPCLONE
                {
                mOPCLONE(); 


                }
                break;
            case 17 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:162: OPCREATEEXPR
                {
                mOPCREATEEXPR(); 


                }
                break;
            case 18 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:175: OPDYNCONTEXT
                {
                mOPDYNCONTEXT(); 


                }
                break;
            case 19 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:188: OPENDCONFLICTGROUP
                {
                mOPENDCONFLICTGROUP(); 


                }
                break;
            case 20 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:207: OPEQ
                {
                mOPEQ(); 


                }
                break;
            case 21 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:212: OPEXECUTE
                {
                mOPEXECUTE(); 


                }
                break;
            case 22 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:222: OPFIND
                {
                mOPFIND(); 


                }
                break;
            case 23 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:229: OPFIRST
                {
                mOPFIRST(); 


                }
                break;
            case 24 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:237: OPFOREACH
                {
                mOPFOREACH(); 


                }
                break;
            case 25 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:247: OPGATE
                {
                mOPGATE(); 


                }
                break;
            case 26 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:254: OPGET
                {
                mOPGET(); 


                }
                break;
            case 27 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:260: OPIDENT
                {
                mOPIDENT(); 


                }
                break;
            case 28 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:268: OPIN
                {
                mOPIN(); 


                }
                break;
            case 29 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:273: OPINCL
                {
                mOPINCL(); 


                }
                break;
            case 30 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:280: OPINTERPRET
                {
                mOPINTERPRET(); 


                }
                break;
            case 31 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:292: OPINTERRUPT
                {
                mOPINTERRUPT(); 


                }
                break;
            case 32 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:304: OPINTERSECT
                {
                mOPINTERSECT(); 


                }
                break;
            case 33 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:316: OPJOIN
                {
                mOPJOIN(); 


                }
                break;
            case 34 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:323: OPLAST
                {
                mOPLAST(); 


                }
                break;
            case 35 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:330: OPPROJECTION
                {
                mOPPROJECTION(); 


                }
                break;
            case 36 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:343: OPRANDOM
                {
                mOPRANDOM(); 


                }
                break;
            case 37 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:352: OPRECALL
                {
                mOPRECALL(); 


                }
                break;
            case 38 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:361: OPRELAX
                {
                mOPRELAX(); 


                }
                break;
            case 39 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:369: OPREPEAT
                {
                mOPREPEAT(); 


                }
                break;
            case 40 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:378: OPREST
                {
                mOPREST(); 


                }
                break;
            case 41 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:385: OPSIZE
                {
                mOPSIZE(); 


                }
                break;
            case 42 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:392: OPSQU
                {
                mOPSQU(); 


                }
                break;
            case 43 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:398: OPSTARTCONFLICTGROUP
                {
                mOPSTARTCONFLICTGROUP(); 


                }
                break;
            case 44 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:419: OPSUBSTRUCT
                {
                mOPSUBSTRUCT(); 


                }
                break;
            case 45 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:431: P_DEBUG
                {
                mP_DEBUG(); 


                }
                break;
            case 46 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:439: P_ELSE
                {
                mP_ELSE(); 


                }
                break;
            case 47 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:446: P_ENDIF
                {
                mP_ENDIF(); 


                }
                break;
            case 48 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:454: P_IF
                {
                mP_IF(); 


                }
                break;
            case 49 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:459: P_OP_AND
                {
                mP_OP_AND(); 


                }
                break;
            case 50 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:468: P_OP_B
                {
                mP_OP_B(); 


                }
                break;
            case 51 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:475: P_OP_E
                {
                mP_OP_E(); 


                }
                break;
            case 52 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:482: P_OP_L
                {
                mP_OP_L(); 


                }
                break;
            case 53 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:489: P_OP_OR
                {
                mP_OP_OR(); 


                }
                break;
            case 54 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:497: T__67
                {
                mT__67(); 


                }
                break;
            case 55 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:503: T__68
                {
                mT__68(); 


                }
                break;
            case 56 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:509: T__69
                {
                mT__69(); 


                }
                break;
            case 57 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:515: T__70
                {
                mT__70(); 


                }
                break;
            case 58 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:521: T__71
                {
                mT__71(); 


                }
                break;
            case 59 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:527: T__72
                {
                mT__72(); 


                }
                break;
            case 60 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:533: T__73
                {
                mT__73(); 


                }
                break;
            case 61 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:539: T__74
                {
                mT__74(); 


                }
                break;
            case 62 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:545: T__75
                {
                mT__75(); 


                }
                break;
            case 63 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:551: T__76
                {
                mT__76(); 


                }
                break;
            case 64 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:557: T__77
                {
                mT__77(); 


                }
                break;
            case 65 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:563: T__78
                {
                mT__78(); 


                }
                break;
            case 66 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:569: T__79
                {
                mT__79(); 


                }
                break;
            case 67 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:575: T__80
                {
                mT__80(); 


                }
                break;
            case 68 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:581: T__81
                {
                mT__81(); 


                }
                break;
            case 69 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:587: T__82
                {
                mT__82(); 


                }
                break;
            case 70 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:593: T__83
                {
                mT__83(); 


                }
                break;
            case 71 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:599: T__84
                {
                mT__84(); 


                }
                break;
            case 72 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:605: T__85
                {
                mT__85(); 


                }
                break;
            case 73 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:611: T__86
                {
                mT__86(); 


                }
                break;
            case 74 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:617: T__87
                {
                mT__87(); 


                }
                break;
            case 75 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:623: T__88
                {
                mT__88(); 


                }
                break;
            case 76 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:629: T__89
                {
                mT__89(); 


                }
                break;
            case 77 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:635: T__90
                {
                mT__90(); 


                }
                break;
            case 78 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:641: T__91
                {
                mT__91(); 


                }
                break;
            case 79 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:647: T__92
                {
                mT__92(); 


                }
                break;
            case 80 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:653: ID
                {
                mID(); 


                }
                break;
            case 81 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:656: STRING_LITERAL
                {
                mSTRING_LITERAL(); 


                }
                break;
            case 82 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:671: COMMA
                {
                mCOMMA(); 


                }
                break;
            case 83 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:677: DQUOTE
                {
                mDQUOTE(); 


                }
                break;
            case 84 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:684: SEMICOLON
                {
                mSEMICOLON(); 


                }
                break;
            case 85 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:694: WS
                {
                mWS(); 


                }
                break;
            case 86 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:697: NATIVE_CODE
                {
                mNATIVE_CODE(); 


                }
                break;
            case 87 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:709: COMMENT
                {
                mCOMMENT(); 


                }
                break;
            case 88 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:717: LINE_COMMENT
                {
                mLINE_COMMENT(); 


                }
                break;

        }

    }


    protected DFA7 dfa7 = new DFA7(this);
    static final String DFA7_eotS =
        "\1\uffff\4\55\1\uffff\3\55\1\uffff\1\55\1\uffff\4\55\1\uffff\5\55"+
        "\5\uffff\1\124\4\uffff\13\55\3\uffff\1\145\4\uffff\6\55\1\160\5"+
        "\55\1\167\1\uffff\1\170\6\55\1\u0082\10\55\4\uffff\17\55\4\uffff"+
        "\3\55\1\u00a5\4\55\1\uffff\6\55\2\uffff\1\u00b0\4\55\1\u00b5\3\55"+
        "\1\uffff\13\55\1\u00c5\2\uffff\24\55\1\uffff\11\55\1\u00e4\1\uffff"+
        "\1\u00e5\2\55\1\u00e8\1\uffff\3\55\1\u00ec\1\u00ed\5\55\1\u00f3"+
        "\1\u00f4\2\55\2\uffff\5\55\1\u00fc\7\55\1\u0104\3\55\1\u0108\10"+
        "\55\1\u0111\1\55\1\u0113\1\u0114\2\uffff\1\u0115\1\55\1\uffff\1"+
        "\u0117\2\55\2\uffff\3\55\1\u011e\1\55\2\uffff\4\55\1\u0124\2\55"+
        "\1\uffff\7\55\1\uffff\3\55\1\uffff\10\55\1\uffff\1\55\3\uffff\1"+
        "\55\1\uffff\4\55\1\u0140\1\u0141\1\uffff\1\u0142\2\55\1\u0145\1"+
        "\u0146\1\uffff\6\55\1\u014d\1\u014e\3\55\1\u0152\1\55\1\u0154\2"+
        "\55\1\u0157\3\55\1\u015b\1\55\1\u015d\1\u015e\3\55\3\uffff\1\u0162"+
        "\1\55\2\uffff\6\55\2\uffff\1\u016a\1\u016b\1\55\1\uffff\1\55\1\uffff"+
        "\1\u016e\1\55\1\uffff\1\u0170\1\u0171\1\u0172\1\uffff\1\55\2\uffff"+
        "\3\55\1\uffff\2\55\1\u017a\1\55\1\u017c\1\55\1\u017e\2\uffff\2\55"+
        "\1\uffff\1\55\3\uffff\1\u0182\1\u0183\1\u0184\1\55\1\u0186\1\55"+
        "\1\u0188\1\uffff\1\55\1\uffff\1\55\1\uffff\3\55\3\uffff\1\u018e"+
        "\1\uffff\1\55\1\uffff\3\55\1\u0193\1\55\1\uffff\1\55\1\u0196\2\55"+
        "\1\uffff\1\55\1\u019a\1\uffff\1\55\1\u019c\1\55\1\uffff\1\55\1\uffff"+
        "\2\55\1\u01a1\1\55\1\uffff\3\55\1\u01a6\1\uffff";
    static final String DFA7_eofS =
        "\u01a7\uffff";
    static final String DFA7_minS =
        "\1\11\1\137\2\141\1\143\1\uffff\1\157\1\141\1\145\1\uffff\1\76\1"+
        "\uffff\1\161\1\151\1\141\1\144\1\uffff\1\157\1\141\1\162\1\141\1"+
        "\151\1\uffff\1\145\3\uffff\1\52\4\uffff\1\165\1\145\1\154\1\141"+
        "\1\156\1\162\1\157\1\160\1\141\1\157\1\151\3\uffff\1\0\3\uffff\1"+
        "\52\1\143\1\163\1\143\1\146\1\156\1\164\1\41\1\156\1\154\1\157\1"+
        "\147\1\162\1\41\1\uffff\1\41\1\145\1\156\1\162\2\164\1\145\1\41"+
        "\1\151\1\163\1\157\1\156\1\143\1\172\1\165\1\142\1\146\1\154\2\uffff"+
        "\1\164\1\171\1\141\1\156\1\164\1\163\2\164\1\151\1\144\1\164\1\143"+
        "\1\157\1\165\1\163\4\uffff\1\137\1\141\1\142\1\41\1\154\1\145\1"+
        "\147\1\151\1\uffff\1\164\1\154\1\164\1\156\1\151\1\156\2\uffff\1"+
        "\41\1\144\1\163\1\105\1\145\1\41\1\156\1\154\1\145\1\uffff\1\156"+
        "\1\164\1\152\1\144\2\141\1\145\1\164\2\145\1\163\1\137\2\uffff\1"+
        "\150\1\157\1\163\1\146\1\141\1\143\1\151\1\145\1\156\1\165\1\151"+
        "\1\153\1\150\1\152\1\162\1\165\1\137\1\160\1\166\1\152\1\uffff\1"+
        "\165\1\164\1\165\1\166\1\145\1\120\2\145\1\156\1\41\1\uffff\1\41"+
        "\1\164\1\141\1\41\1\uffff\1\164\1\165\1\162\2\41\1\145\1\157\1\154"+
        "\1\170\1\141\2\41\1\145\1\164\2\uffff\1\157\1\156\1\163\1\154\1"+
        "\145\1\41\1\162\1\164\1\162\1\147\1\154\1\157\1\141\1\41\1\145\1"+
        "\143\1\141\1\41\1\137\1\141\1\145\1\144\1\145\2\141\1\170\1\41\1"+
        "\163\2\41\2\uffff\1\41\1\143\1\uffff\1\41\1\144\1\162\2\uffff\1"+
        "\143\1\155\1\154\1\41\1\164\2\uffff\1\172\2\162\1\144\1\41\1\151"+
        "\1\170\1\uffff\2\151\1\156\2\145\1\156\1\147\1\uffff\1\143\1\145"+
        "\1\154\1\uffff\2\137\1\143\1\145\1\162\1\147\2\164\1\uffff\1\151"+
        "\3\uffff\1\150\1\uffff\1\145\1\165\1\145\1\164\2\41\1\uffff\1\41"+
        "\1\145\1\165\2\41\1\uffff\1\143\1\164\1\160\1\145\1\137\1\141\2"+
        "\41\1\163\1\145\1\164\1\41\1\151\1\41\1\137\1\164\1\41\1\155\2\145"+
        "\1\41\1\141\2\41\1\160\1\143\1\151\3\uffff\1\41\1\143\2\uffff\1"+
        "\164\1\163\1\164\1\163\1\150\1\154\2\uffff\2\41\1\137\1\uffff\1"+
        "\172\1\uffff\1\41\1\151\1\uffff\3\41\1\uffff\1\156\2\uffff\2\164"+
        "\1\157\1\uffff\1\164\1\162\1\41\1\151\1\41\1\151\1\41\2\uffff\1"+
        "\156\1\145\1\uffff\1\166\3\uffff\3\41\1\156\1\41\1\151\1\41\1\uffff"+
        "\1\157\1\uffff\1\163\1\uffff\1\141\1\162\1\145\3\uffff\1\41\1\uffff"+
        "\1\156\1\uffff\1\156\1\164\1\155\1\41\1\137\1\uffff\1\147\1\41\1"+
        "\157\1\145\1\uffff\1\143\1\41\1\uffff\1\162\1\41\1\137\1\uffff\1"+
        "\171\1\uffff\2\137\1\41\1\163\1\uffff\1\151\1\172\1\145\1\41\1\uffff";
    static final String DFA7_maxS =
        "\1\176\1\137\1\156\1\151\1\143\1\uffff\2\157\1\160\1\uffff\1\76"+
        "\1\uffff\1\170\1\157\1\145\1\156\1\uffff\1\157\1\141\1\162\1\145"+
        "\1\165\1\uffff\1\151\3\uffff\1\52\4\uffff\1\165\1\145\1\157\1\145"+
        "\1\170\1\162\1\157\1\160\1\162\1\157\1\151\3\uffff\1\uffff\3\uffff"+
        "\1\57\1\157\1\163\1\143\1\146\1\156\1\164\1\172\1\156\1\162\1\157"+
        "\1\147\1\162\1\172\1\uffff\1\172\1\145\2\162\2\164\1\145\1\172\1"+
        "\151\1\163\1\157\1\156\1\163\1\172\1\165\1\142\1\146\1\156\2\uffff"+
        "\1\164\1\171\1\141\1\156\1\164\1\163\2\164\1\151\1\144\2\164\1\157"+
        "\1\165\1\163\4\uffff\1\160\1\141\1\142\1\172\1\154\1\145\1\147\1"+
        "\151\1\uffff\1\164\1\154\1\164\1\156\1\151\1\156\2\uffff\1\172\1"+
        "\144\1\163\1\105\1\145\1\172\1\156\1\154\1\145\1\uffff\1\156\1\164"+
        "\1\152\1\144\2\141\1\145\1\164\2\145\1\163\1\137\2\uffff\1\150\1"+
        "\157\1\163\1\164\1\141\1\143\1\151\1\145\1\156\1\165\1\151\1\153"+
        "\1\150\1\152\1\162\1\165\1\137\1\160\1\166\1\152\1\uffff\1\165\1"+
        "\164\1\165\1\166\1\145\1\120\2\145\1\156\1\172\1\uffff\1\172\1\164"+
        "\1\141\1\172\1\uffff\1\164\1\165\1\162\2\172\1\145\1\157\1\154\1"+
        "\170\1\141\2\172\1\145\1\164\2\uffff\1\157\1\156\1\163\1\154\1\145"+
        "\1\172\1\162\1\164\1\162\1\147\1\154\1\157\1\141\1\172\1\145\1\143"+
        "\1\141\1\172\1\137\1\141\1\145\1\144\1\145\2\141\1\170\1\172\1\163"+
        "\2\172\2\uffff\1\172\1\143\1\uffff\1\172\1\144\1\163\2\uffff\1\143"+
        "\1\155\1\154\1\172\1\164\2\uffff\1\172\2\162\1\144\1\172\1\151\1"+
        "\170\1\uffff\1\151\1\171\1\156\2\145\1\156\1\147\1\uffff\1\143\1"+
        "\145\1\154\1\uffff\2\137\1\143\1\145\1\162\1\147\2\164\1\uffff\1"+
        "\151\3\uffff\1\150\1\uffff\1\145\1\165\1\145\1\164\2\172\1\uffff"+
        "\1\172\1\145\1\165\2\172\1\uffff\1\143\1\164\1\160\1\145\1\137\1"+
        "\141\2\172\1\163\1\145\1\164\1\172\1\151\1\172\1\137\1\164\1\172"+
        "\1\155\2\145\1\172\1\141\2\172\1\160\1\143\1\151\3\uffff\1\172\1"+
        "\143\2\uffff\1\164\1\163\1\164\1\163\1\150\1\154\2\uffff\2\172\1"+
        "\137\1\uffff\1\172\1\uffff\1\172\1\151\1\uffff\3\172\1\uffff\1\156"+
        "\2\uffff\2\164\1\157\1\uffff\1\164\1\163\1\172\1\151\1\172\1\151"+
        "\1\172\2\uffff\1\156\1\145\1\uffff\1\166\3\uffff\3\172\1\156\1\172"+
        "\1\151\1\172\1\uffff\1\157\1\uffff\1\163\1\uffff\1\141\1\162\1\145"+
        "\3\uffff\1\172\1\uffff\1\156\1\uffff\1\156\1\164\1\155\1\172\1\137"+
        "\1\uffff\1\147\1\172\1\157\1\145\1\uffff\1\143\1\172\1\uffff\1\162"+
        "\1\172\1\137\1\uffff\1\171\1\uffff\2\137\1\172\1\163\1\uffff\1\151"+
        "\1\172\1\145\1\172\1\uffff";
    static final String DFA7_acceptS =
        "\5\uffff\1\10\3\uffff\1\21\1\uffff\1\23\4\uffff\1\36\5\uffff\1\53"+
        "\1\uffff\1\61\1\62\1\63\1\uffff\1\65\1\66\1\67\1\70\13\uffff\1\116"+
        "\1\117\1\120\1\uffff\1\122\1\124\1\125\16\uffff\1\22\22\uffff\1"+
        "\126\1\64\17\uffff\1\121\1\123\1\127\1\130\10\uffff\1\11\6\uffff"+
        "\1\15\1\24\11\uffff\1\34\14\uffff\1\56\1\57\24\uffff\1\3\12\uffff"+
        "\1\25\4\uffff\1\32\16\uffff\1\55\1\60\36\uffff\1\14\1\26\2\uffff"+
        "\1\31\3\uffff\1\41\1\42\5\uffff\1\50\1\51\7\uffff\1\77\7\uffff\1"+
        "\112\3\uffff\1\1\10\uffff\1\16\1\uffff\1\20\1\13\1\27\1\uffff\1"+
        "\33\6\uffff\1\46\5\uffff\1\73\33\uffff\1\44\1\45\1\47\2\uffff\1"+
        "\71\1\72\6\uffff\1\104\1\107\3\uffff\1\114\1\uffff\1\2\2\uffff\1"+
        "\105\3\uffff\1\12\1\uffff\1\30\1\35\3\uffff\1\52\7\uffff\1\110\1"+
        "\111\2\uffff\1\4\1\uffff\1\5\1\106\1\7\7\uffff\1\76\1\uffff\1\101"+
        "\1\uffff\1\103\3\uffff\1\17\1\37\1\40\1\uffff\1\54\1\uffff\1\75"+
        "\5\uffff\1\43\4\uffff\1\115\2\uffff\1\100\3\uffff\1\74\1\uffff\1"+
        "\113\4\uffff\1\6\4\uffff\1\102";
    static final String DFA7_specialS =
        "\56\uffff\1\0\u0178\uffff}>";
    static final String[] DFA7_transitionS = {
            "\2\61\2\uffff\1\61\22\uffff\1\61\1\55\1\56\1\27\1\55\1\uffff"+
            "\1\30\1\uffff\1\35\1\36\2\uffff\1\57\1\12\1\37\1\62\12\55\1"+
            "\5\1\60\1\33\1\32\1\31\1\55\1\uffff\1\4\1\10\1\7\1\6\1\14\1"+
            "\15\1\16\1\55\1\17\1\21\1\55\1\22\3\55\1\23\1\55\1\24\1\25\7"+
            "\55\1\26\1\uffff\1\13\1\11\1\1\1\uffff\1\40\1\41\1\42\1\43\1"+
            "\44\1\45\2\55\1\2\2\55\1\3\1\46\1\55\1\47\1\50\2\55\1\51\2\55"+
            "\1\52\4\55\1\53\1\34\1\54\1\20",
            "\1\63",
            "\1\64\14\uffff\1\65",
            "\1\67\7\uffff\1\66",
            "\1\70",
            "",
            "\1\71",
            "\1\73\12\uffff\1\74\2\uffff\1\72",
            "\1\75\11\uffff\1\76\1\77",
            "",
            "\1\100",
            "",
            "\1\101\6\uffff\1\102",
            "\1\103\5\uffff\1\104",
            "\1\105\3\uffff\1\106",
            "\1\107\11\uffff\1\110",
            "",
            "\1\111",
            "\1\112",
            "\1\113",
            "\1\114\3\uffff\1\115",
            "\1\116\7\uffff\1\117\3\uffff\1\120",
            "",
            "\1\122\3\uffff\1\121",
            "",
            "",
            "",
            "\1\123",
            "",
            "",
            "",
            "",
            "\1\125",
            "\1\126",
            "\1\127\2\uffff\1\130",
            "\1\131\3\uffff\1\132",
            "\1\133\11\uffff\1\134",
            "\1\135",
            "\1\136",
            "\1\137",
            "\1\140\20\uffff\1\141",
            "\1\142",
            "\1\143",
            "",
            "",
            "",
            "\0\144",
            "",
            "",
            "",
            "\1\146\4\uffff\1\147",
            "\1\150\6\uffff\1\151\4\uffff\1\152",
            "\1\153",
            "\1\154",
            "\1\155",
            "\1\156",
            "\1\157",
            "\1\55\2\uffff\1\55\10\uffff\2\55\1\uffff\12\55\5\uffff\1\55"+
            "\1\uffff\32\55\4\uffff\1\55\1\uffff\32\55",
            "\1\161",
            "\1\162\5\uffff\1\163",
            "\1\164",
            "\1\165",
            "\1\166",
            "\1\55\2\uffff\1\55\10\uffff\2\55\1\uffff\12\55\5\uffff\1\55"+
            "\1\uffff\32\55\4\uffff\1\55\1\uffff\32\55",
            "",
            "\1\55\2\uffff\1\55\10\uffff\2\55\1\uffff\12\55\5\uffff\1\55"+
            "\1\uffff\32\55\4\uffff\1\55\1\uffff\32\55",
            "\1\171",
            "\1\172\3\uffff\1\173",
            "\1\174",
            "\1\175",
            "\1\176",
            "\1\177",
            "\1\55\2\uffff\1\55\10\uffff\2\55\1\uffff\12\55\5\uffff\1\55"+
            "\1\uffff\32\55\4\uffff\1\55\1\uffff\2\55\1\u0080\20\55\1\u0081"+
            "\6\55",
            "\1\u0083",
            "\1\u0084",
            "\1\u0085",
            "\1\u0086",
            "\1\u0087\10\uffff\1\u0088\3\uffff\1\u0089\2\uffff\1\u008a",
            "\1\u008b",
            "\1\u008c",
            "\1\u008d",
            "\1\u008e",
            "\1\u008f\1\uffff\1\u0090",
            "",
            "",
            "\1\u0091",
            "\1\u0092",
            "\1\u0093",
            "\1\u0094",
            "\1\u0095",
            "\1\u0096",
            "\1\u0097",
            "\1\u0098",
            "\1\u0099",
            "\1\u009a",
            "\1\u009b",
            "\1\u009c\20\uffff\1\u009d",
            "\1\u009e",
            "\1\u009f",
            "\1\u00a0",
            "",
            "",
            "",
            "",
            "\1\u00a1\20\uffff\1\u00a2",
            "\1\u00a3",
            "\1\u00a4",
            "\1\55\2\uffff\1\55\10\uffff\2\55\1\uffff\12\55\5\uffff\1\55"+
            "\1\uffff\32\55\4\uffff\1\55\1\uffff\32\55",
            "\1\u00a6",
            "\1\u00a7",
            "\1\u00a8",
            "\1\u00a9",
            "",
            "\1\u00aa",
            "\1\u00ab",
            "\1\u00ac",
            "\1\u00ad",
            "\1\u00ae",
            "\1\u00af",
            "",
            "",
            "\1\55\2\uffff\1\55\10\uffff\2\55\1\uffff\12\55\5\uffff\1\55"+
            "\1\uffff\32\55\4\uffff\1\55\1\uffff\32\55",
            "\1\u00b1",
            "\1\u00b2",
            "\1\u00b3",
            "\1\u00b4",
            "\1\55\2\uffff\1\55\10\uffff\2\55\1\uffff\12\55\5\uffff\1\55"+
            "\1\uffff\32\55\4\uffff\1\55\1\uffff\32\55",
            "\1\u00b6",
            "\1\u00b7",
            "\1\u00b8",
            "",
            "\1\u00b9",
            "\1\u00ba",
            "\1\u00bb",
            "\1\u00bc",
            "\1\u00bd",
            "\1\u00be",
            "\1\u00bf",
            "\1\u00c0",
            "\1\u00c1",
            "\1\u00c2",
            "\1\u00c3",
            "\1\u00c4",
            "",
            "",
            "\1\u00c6",
            "\1\u00c7",
            "\1\u00c8",
            "\1\u00c9\15\uffff\1\u00ca",
            "\1\u00cb",
            "\1\u00cc",
            "\1\u00cd",
            "\1\u00ce",
            "\1\u00cf",
            "\1\u00d0",
            "\1\u00d1",
            "\1\u00d2",
            "\1\u00d3",
            "\1\u00d4",
            "\1\u00d5",
            "\1\u00d6",
            "\1\u00d7",
            "\1\u00d8",
            "\1\u00d9",
            "\1\u00da",
            "",
            "\1\u00db",
            "\1\u00dc",
            "\1\u00dd",
            "\1\u00de",
            "\1\u00df",
            "\1\u00e0",
            "\1\u00e1",
            "\1\u00e2",
            "\1\u00e3",
            "\1\55\2\uffff\1\55\10\uffff\2\55\1\uffff\12\55\5\uffff\1\55"+
            "\1\uffff\32\55\4\uffff\1\55\1\uffff\32\55",
            "",
            "\1\55\2\uffff\1\55\10\uffff\2\55\1\uffff\12\55\5\uffff\1\55"+
            "\1\uffff\32\55\4\uffff\1\55\1\uffff\32\55",
            "\1\u00e6",
            "\1\u00e7",
            "\1\55\2\uffff\1\55\10\uffff\2\55\1\uffff\12\55\5\uffff\1\55"+
            "\1\uffff\32\55\4\uffff\1\55\1\uffff\32\55",
            "",
            "\1\u00e9",
            "\1\u00ea",
            "\1\u00eb",
            "\1\55\2\uffff\1\55\10\uffff\2\55\1\uffff\12\55\5\uffff\1\55"+
            "\1\uffff\32\55\4\uffff\1\55\1\uffff\32\55",
            "\1\55\2\uffff\1\55\10\uffff\2\55\1\uffff\12\55\5\uffff\1\55"+
            "\1\uffff\32\55\4\uffff\1\55\1\uffff\32\55",
            "\1\u00ee",
            "\1\u00ef",
            "\1\u00f0",
            "\1\u00f1",
            "\1\u00f2",
            "\1\55\2\uffff\1\55\10\uffff\2\55\1\uffff\12\55\5\uffff\1\55"+
            "\1\uffff\32\55\4\uffff\1\55\1\uffff\32\55",
            "\1\55\2\uffff\1\55\10\uffff\2\55\1\uffff\12\55\5\uffff\1\55"+
            "\1\uffff\32\55\4\uffff\1\55\1\uffff\32\55",
            "\1\u00f5",
            "\1\u00f6",
            "",
            "",
            "\1\u00f7",
            "\1\u00f8",
            "\1\u00f9",
            "\1\u00fa",
            "\1\u00fb",
            "\1\55\2\uffff\1\55\10\uffff\2\55\1\uffff\12\55\5\uffff\1\55"+
            "\1\uffff\32\55\4\uffff\1\55\1\uffff\32\55",
            "\1\u00fd",
            "\1\u00fe",
            "\1\u00ff",
            "\1\u0100",
            "\1\u0101",
            "\1\u0102",
            "\1\u0103",
            "\1\55\2\uffff\1\55\10\uffff\2\55\1\uffff\12\55\5\uffff\1\55"+
            "\1\uffff\32\55\4\uffff\1\55\1\uffff\32\55",
            "\1\u0105",
            "\1\u0106",
            "\1\u0107",
            "\1\55\2\uffff\1\55\10\uffff\2\55\1\uffff\12\55\5\uffff\1\55"+
            "\1\uffff\32\55\4\uffff\1\55\1\uffff\32\55",
            "\1\u0109",
            "\1\u010a",
            "\1\u010b",
            "\1\u010c",
            "\1\u010d",
            "\1\u010e",
            "\1\u010f",
            "\1\u0110",
            "\1\55\2\uffff\1\55\10\uffff\2\55\1\uffff\12\55\5\uffff\1\55"+
            "\1\uffff\32\55\4\uffff\1\55\1\uffff\32\55",
            "\1\u0112",
            "\1\55\2\uffff\1\55\10\uffff\2\55\1\uffff\12\55\5\uffff\1\55"+
            "\1\uffff\32\55\4\uffff\1\55\1\uffff\32\55",
            "\1\55\2\uffff\1\55\10\uffff\2\55\1\uffff\12\55\5\uffff\1\55"+
            "\1\uffff\32\55\4\uffff\1\55\1\uffff\32\55",
            "",
            "",
            "\1\55\2\uffff\1\55\10\uffff\2\55\1\uffff\12\55\5\uffff\1\55"+
            "\1\uffff\32\55\4\uffff\1\55\1\uffff\32\55",
            "\1\u0116",
            "",
            "\1\55\2\uffff\1\55\10\uffff\2\55\1\uffff\12\55\5\uffff\1\55"+
            "\1\uffff\32\55\4\uffff\1\55\1\uffff\32\55",
            "\1\u0118",
            "\1\u0119\1\u011a",
            "",
            "",
            "\1\u011b",
            "\1\u011c",
            "\1\u011d",
            "\1\55\2\uffff\1\55\10\uffff\2\55\1\uffff\12\55\5\uffff\1\55"+
            "\1\uffff\32\55\4\uffff\1\55\1\uffff\32\55",
            "\1\u011f",
            "",
            "",
            "\1\u0120",
            "\1\u0121",
            "\1\u0122",
            "\1\u0123",
            "\1\55\2\uffff\1\55\10\uffff\2\55\1\uffff\12\55\5\uffff\1\55"+
            "\1\uffff\32\55\4\uffff\1\55\1\uffff\32\55",
            "\1\u0125",
            "\1\u0126",
            "",
            "\1\u0127",
            "\1\u0128\17\uffff\1\u0129",
            "\1\u012a",
            "\1\u012b",
            "\1\u012c",
            "\1\u012d",
            "\1\u012e",
            "",
            "\1\u012f",
            "\1\u0130",
            "\1\u0131",
            "",
            "\1\u0132",
            "\1\u0133",
            "\1\u0134",
            "\1\u0135",
            "\1\u0136",
            "\1\u0137",
            "\1\u0138",
            "\1\u0139",
            "",
            "\1\u013a",
            "",
            "",
            "",
            "\1\u013b",
            "",
            "\1\u013c",
            "\1\u013d",
            "\1\u013e",
            "\1\u013f",
            "\1\55\2\uffff\1\55\10\uffff\2\55\1\uffff\12\55\5\uffff\1\55"+
            "\1\uffff\32\55\4\uffff\1\55\1\uffff\32\55",
            "\1\55\2\uffff\1\55\10\uffff\2\55\1\uffff\12\55\5\uffff\1\55"+
            "\1\uffff\32\55\4\uffff\1\55\1\uffff\32\55",
            "",
            "\1\55\2\uffff\1\55\10\uffff\2\55\1\uffff\12\55\5\uffff\1\55"+
            "\1\uffff\32\55\4\uffff\1\55\1\uffff\32\55",
            "\1\u0143",
            "\1\u0144",
            "\1\55\2\uffff\1\55\10\uffff\2\55\1\uffff\12\55\5\uffff\1\55"+
            "\1\uffff\32\55\4\uffff\1\55\1\uffff\32\55",
            "\1\55\2\uffff\1\55\10\uffff\2\55\1\uffff\12\55\5\uffff\1\55"+
            "\1\uffff\32\55\4\uffff\1\55\1\uffff\32\55",
            "",
            "\1\u0147",
            "\1\u0148",
            "\1\u0149",
            "\1\u014a",
            "\1\u014b",
            "\1\u014c",
            "\1\55\2\uffff\1\55\10\uffff\2\55\1\uffff\12\55\5\uffff\1\55"+
            "\1\uffff\32\55\4\uffff\1\55\1\uffff\32\55",
            "\1\55\2\uffff\1\55\10\uffff\2\55\1\uffff\12\55\5\uffff\1\55"+
            "\1\uffff\32\55\4\uffff\1\55\1\uffff\32\55",
            "\1\u014f",
            "\1\u0150",
            "\1\u0151",
            "\1\55\2\uffff\1\55\10\uffff\2\55\1\uffff\12\55\5\uffff\1\55"+
            "\1\uffff\32\55\4\uffff\1\55\1\uffff\32\55",
            "\1\u0153",
            "\1\55\2\uffff\1\55\10\uffff\2\55\1\uffff\12\55\5\uffff\1\55"+
            "\1\uffff\32\55\4\uffff\1\55\1\uffff\32\55",
            "\1\u0155",
            "\1\u0156",
            "\1\55\2\uffff\1\55\10\uffff\2\55\1\uffff\12\55\5\uffff\1\55"+
            "\1\uffff\32\55\4\uffff\1\55\1\uffff\32\55",
            "\1\u0158",
            "\1\u0159",
            "\1\u015a",
            "\1\55\2\uffff\1\55\10\uffff\2\55\1\uffff\12\55\5\uffff\1\55"+
            "\1\uffff\32\55\4\uffff\1\55\1\uffff\32\55",
            "\1\u015c",
            "\1\55\2\uffff\1\55\10\uffff\2\55\1\uffff\12\55\5\uffff\1\55"+
            "\1\uffff\32\55\4\uffff\1\55\1\uffff\32\55",
            "\1\55\2\uffff\1\55\10\uffff\2\55\1\uffff\12\55\5\uffff\1\55"+
            "\1\uffff\32\55\4\uffff\1\55\1\uffff\32\55",
            "\1\u015f",
            "\1\u0160",
            "\1\u0161",
            "",
            "",
            "",
            "\1\55\2\uffff\1\55\10\uffff\2\55\1\uffff\12\55\5\uffff\1\55"+
            "\1\uffff\32\55\4\uffff\1\55\1\uffff\32\55",
            "\1\u0163",
            "",
            "",
            "\1\u0164",
            "\1\u0165",
            "\1\u0166",
            "\1\u0167",
            "\1\u0168",
            "\1\u0169",
            "",
            "",
            "\1\55\2\uffff\1\55\10\uffff\2\55\1\uffff\12\55\5\uffff\1\55"+
            "\1\uffff\32\55\4\uffff\1\55\1\uffff\32\55",
            "\1\55\2\uffff\1\55\10\uffff\2\55\1\uffff\12\55\5\uffff\1\55"+
            "\1\uffff\32\55\4\uffff\1\55\1\uffff\32\55",
            "\1\u016c",
            "",
            "\1\u016d",
            "",
            "\1\55\2\uffff\1\55\10\uffff\2\55\1\uffff\12\55\5\uffff\1\55"+
            "\1\uffff\32\55\4\uffff\1\55\1\uffff\32\55",
            "\1\u016f",
            "",
            "\1\55\2\uffff\1\55\10\uffff\2\55\1\uffff\12\55\5\uffff\1\55"+
            "\1\uffff\32\55\4\uffff\1\55\1\uffff\32\55",
            "\1\55\2\uffff\1\55\10\uffff\2\55\1\uffff\12\55\5\uffff\1\55"+
            "\1\uffff\32\55\4\uffff\1\55\1\uffff\32\55",
            "\1\55\2\uffff\1\55\10\uffff\2\55\1\uffff\12\55\5\uffff\1\55"+
            "\1\uffff\32\55\4\uffff\1\55\1\uffff\32\55",
            "",
            "\1\u0173",
            "",
            "",
            "\1\u0174",
            "\1\u0175",
            "\1\u0176",
            "",
            "\1\u0177",
            "\1\u0178\1\u0179",
            "\1\55\2\uffff\1\55\10\uffff\2\55\1\uffff\12\55\5\uffff\1\55"+
            "\1\uffff\32\55\4\uffff\1\55\1\uffff\32\55",
            "\1\u017b",
            "\1\55\2\uffff\1\55\10\uffff\2\55\1\uffff\12\55\5\uffff\1\55"+
            "\1\uffff\32\55\4\uffff\1\55\1\uffff\32\55",
            "\1\u017d",
            "\1\55\2\uffff\1\55\10\uffff\2\55\1\uffff\12\55\5\uffff\1\55"+
            "\1\uffff\32\55\4\uffff\1\55\1\uffff\32\55",
            "",
            "",
            "\1\u017f",
            "\1\u0180",
            "",
            "\1\u0181",
            "",
            "",
            "",
            "\1\55\2\uffff\1\55\10\uffff\2\55\1\uffff\12\55\5\uffff\1\55"+
            "\1\uffff\32\55\4\uffff\1\55\1\uffff\32\55",
            "\1\55\2\uffff\1\55\10\uffff\2\55\1\uffff\12\55\5\uffff\1\55"+
            "\1\uffff\32\55\4\uffff\1\55\1\uffff\32\55",
            "\1\55\2\uffff\1\55\10\uffff\2\55\1\uffff\12\55\5\uffff\1\55"+
            "\1\uffff\32\55\4\uffff\1\55\1\uffff\32\55",
            "\1\u0185",
            "\1\55\2\uffff\1\55\10\uffff\2\55\1\uffff\12\55\5\uffff\1\55"+
            "\1\uffff\32\55\4\uffff\1\55\1\uffff\32\55",
            "\1\u0187",
            "\1\55\2\uffff\1\55\10\uffff\2\55\1\uffff\12\55\5\uffff\1\55"+
            "\1\uffff\32\55\4\uffff\1\55\1\uffff\32\55",
            "",
            "\1\u0189",
            "",
            "\1\u018a",
            "",
            "\1\u018b",
            "\1\u018c",
            "\1\u018d",
            "",
            "",
            "",
            "\1\55\2\uffff\1\55\10\uffff\2\55\1\uffff\12\55\5\uffff\1\55"+
            "\1\uffff\32\55\4\uffff\1\55\1\uffff\32\55",
            "",
            "\1\u018f",
            "",
            "\1\u0190",
            "\1\u0191",
            "\1\u0192",
            "\1\55\2\uffff\1\55\10\uffff\2\55\1\uffff\12\55\5\uffff\1\55"+
            "\1\uffff\32\55\4\uffff\1\55\1\uffff\32\55",
            "\1\u0194",
            "",
            "\1\u0195",
            "\1\55\2\uffff\1\55\10\uffff\2\55\1\uffff\12\55\5\uffff\1\55"+
            "\1\uffff\32\55\4\uffff\1\55\1\uffff\32\55",
            "\1\u0197",
            "\1\u0198",
            "",
            "\1\u0199",
            "\1\55\2\uffff\1\55\10\uffff\2\55\1\uffff\12\55\5\uffff\1\55"+
            "\1\uffff\32\55\4\uffff\1\55\1\uffff\32\55",
            "",
            "\1\u019b",
            "\1\55\2\uffff\1\55\10\uffff\2\55\1\uffff\12\55\5\uffff\1\55"+
            "\1\uffff\32\55\4\uffff\1\55\1\uffff\32\55",
            "\1\u019d",
            "",
            "\1\u019e",
            "",
            "\1\u019f",
            "\1\u01a0",
            "\1\55\2\uffff\1\55\10\uffff\2\55\1\uffff\12\55\5\uffff\1\55"+
            "\1\uffff\32\55\4\uffff\1\55\1\uffff\32\55",
            "\1\u01a2",
            "",
            "\1\u01a3",
            "\1\u01a4",
            "\1\u01a5",
            "\1\55\2\uffff\1\55\10\uffff\2\55\1\uffff\12\55\5\uffff\1\55"+
            "\1\uffff\32\55\4\uffff\1\55\1\uffff\32\55",
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
            return "1:1: Tokens : ( C | CPP | IAS | JAVA | LIFETERM | OBJECTIVEC | OPACTIVATE | OPACTIVATECTX | OPACTIVATEONFRINGE | OPAPPLYTOCONTEXT | OPBEGIN | OPBORN | OPBREAKPOINT | OPCALLP | OPCARTESIAN | OPCLONE | OPCREATEEXPR | OPDYNCONTEXT | OPENDCONFLICTGROUP | OPEQ | OPEXECUTE | OPFIND | OPFIRST | OPFOREACH | OPGATE | OPGET | OPIDENT | OPIN | OPINCL | OPINTERPRET | OPINTERRUPT | OPINTERSECT | OPJOIN | OPLAST | OPPROJECTION | OPRANDOM | OPRECALL | OPRELAX | OPREPEAT | OPREST | OPSIZE | OPSQU | OPSTARTCONFLICTGROUP | OPSUBSTRUCT | P_DEBUG | P_ELSE | P_ENDIF | P_IF | P_OP_AND | P_OP_B | P_OP_E | P_OP_L | P_OP_OR | T__67 | T__68 | T__69 | T__70 | T__71 | T__72 | T__73 | T__74 | T__75 | T__76 | T__77 | T__78 | T__79 | T__80 | T__81 | T__82 | T__83 | T__84 | T__85 | T__86 | T__87 | T__88 | T__89 | T__90 | T__91 | T__92 | ID | STRING_LITERAL | COMMA | DQUOTE | SEMICOLON | WS | NATIVE_CODE | COMMENT | LINE_COMMENT );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            IntStream input = _input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA7_46 = input.LA(1);

                        s = -1;
                        if ( ((LA7_46 >= '\u0000' && LA7_46 <= '\uFFFF')) ) {s = 100;}

                        else s = 101;

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