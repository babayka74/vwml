// $ANTLR 3.4 C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g 2013-12-29 13:16:47
 
package com.vw.lang.grammar;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked"})
public class VirtualWorldModelingLanguageLexer extends Lexer {
    public static final int EOF=-1;
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
    public static final int T__71=71;
    public static final int T__72=72;
    public static final int T__73=73;
    public static final int T__74=74;
    public static final int T__75=75;
    public static final int T__76=76;
    public static final int T__77=77;
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
    public static final int OPAPPLYTOCONTEXT=19;
    public static final int OPBEGIN=20;
    public static final int OPBREAKPOINT=21;
    public static final int OPCARTESIAN=22;
    public static final int OPCLONE=23;
    public static final int OPCREATEEXPR=24;
    public static final int OPENDCONFLICTGROUP=25;
    public static final int OPEQ=26;
    public static final int OPEXECUTE=27;
    public static final int OPFIRST=28;
    public static final int OPIDENT=29;
    public static final int OPIN=30;
    public static final int OPINCL=31;
    public static final int OPINTERPRET=32;
    public static final int OPINTERSECT=33;
    public static final int OPJOIN=34;
    public static final int OPLAST=35;
    public static final int OPPROJECTION_1=36;
    public static final int OPPROJECTION_2=37;
    public static final int OPPROJECTION_3=38;
    public static final int OPPROJECTION_4=39;
    public static final int OPPROJECTION_5=40;
    public static final int OPPROJECTION_6=41;
    public static final int OPPROJECTION_7=42;
    public static final int OPPROJECTION_8=43;
    public static final int OPPROJECTION_9=44;
    public static final int OPRANDOM=45;
    public static final int OPRELAX=46;
    public static final int OPREST=47;
    public static final int OPSQU=48;
    public static final int OPSTARTCONFLICTGROUP=49;
    public static final int OPSUBSTRUCT=50;
    public static final int SEMICOLON=51;
    public static final int STRING_LITERAL=52;
    public static final int WS=53;

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

    // $ANTLR start "OPACTIVATECTX"
    public final void mOPACTIVATECTX() throws RecognitionException {
        try {
            int _type = OPACTIVATECTX;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:15:15: ( ':' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:15:17: ':'
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
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:16:20: ( 'Do' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:16:22: 'Do'
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
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:17:18: ( 'Context' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:17:20: 'Context'
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
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:18:9: ( 'Begin' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:18:11: 'Begin'
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

    // $ANTLR start "OPBREAKPOINT"
    public final void mOPBREAKPOINT() throws RecognitionException {
        try {
            int _type = OPBREAKPOINT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:19:14: ( 'Bp' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:19:16: 'Bp'
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

    // $ANTLR start "OPCARTESIAN"
    public final void mOPCARTESIAN() throws RecognitionException {
        try {
            int _type = OPCARTESIAN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:20:13: ( 'Cartesian' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:20:15: 'Cartesian'
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
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:21:9: ( 'Clone' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:21:11: 'Clone'
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
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:22:14: ( '^' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:22:16: '^'
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

    // $ANTLR start "OPENDCONFLICTGROUP"
    public final void mOPENDCONFLICTGROUP() throws RecognitionException {
        try {
            int _type = OPENDCONFLICTGROUP;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:23:20: ( ']' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:23:22: ']'
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
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:24:6: ( 'Eq' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:24:8: 'Eq'
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
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:25:11: ( 'Exe' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:25:13: 'Exe'
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

    // $ANTLR start "OPFIRST"
    public final void mOPFIRST() throws RecognitionException {
        try {
            int _type = OPFIRST;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:26:9: ( 'First' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:26:11: 'First'
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

    // $ANTLR start "OPIDENT"
    public final void mOPIDENT() throws RecognitionException {
        try {
            int _type = OPIDENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:27:9: ( 'Ident' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:27:11: 'Ident'
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
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:28:6: ( 'In' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:28:8: 'In'
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
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:29:8: ( 'Include' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:29:10: 'Include'
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
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:30:13: ( '~' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:30:15: '~'
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

    // $ANTLR start "OPINTERSECT"
    public final void mOPINTERSECT() throws RecognitionException {
        try {
            int _type = OPINTERSECT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:31:13: ( 'Intersect' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:31:15: 'Intersect'
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
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:32:8: ( 'Join' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:32:10: 'Join'
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
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:33:8: ( 'Last' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:33:10: 'Last'
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

    // $ANTLR start "OPPROJECTION_1"
    public final void mOPPROJECTION_1() throws RecognitionException {
        try {
            int _type = OPPROJECTION_1;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:34:16: ( 'Projection_1' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:34:18: 'Projection_1'
            {
            match("Projection_1"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "OPPROJECTION_1"

    // $ANTLR start "OPPROJECTION_2"
    public final void mOPPROJECTION_2() throws RecognitionException {
        try {
            int _type = OPPROJECTION_2;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:35:16: ( 'Projection_2' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:35:18: 'Projection_2'
            {
            match("Projection_2"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "OPPROJECTION_2"

    // $ANTLR start "OPPROJECTION_3"
    public final void mOPPROJECTION_3() throws RecognitionException {
        try {
            int _type = OPPROJECTION_3;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:36:16: ( 'Projection_3' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:36:18: 'Projection_3'
            {
            match("Projection_3"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "OPPROJECTION_3"

    // $ANTLR start "OPPROJECTION_4"
    public final void mOPPROJECTION_4() throws RecognitionException {
        try {
            int _type = OPPROJECTION_4;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:37:16: ( 'Projection_4' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:37:18: 'Projection_4'
            {
            match("Projection_4"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "OPPROJECTION_4"

    // $ANTLR start "OPPROJECTION_5"
    public final void mOPPROJECTION_5() throws RecognitionException {
        try {
            int _type = OPPROJECTION_5;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:38:16: ( 'Projection_5' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:38:18: 'Projection_5'
            {
            match("Projection_5"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "OPPROJECTION_5"

    // $ANTLR start "OPPROJECTION_6"
    public final void mOPPROJECTION_6() throws RecognitionException {
        try {
            int _type = OPPROJECTION_6;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:39:16: ( 'Projection_6' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:39:18: 'Projection_6'
            {
            match("Projection_6"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "OPPROJECTION_6"

    // $ANTLR start "OPPROJECTION_7"
    public final void mOPPROJECTION_7() throws RecognitionException {
        try {
            int _type = OPPROJECTION_7;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:40:16: ( 'Projection_7' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:40:18: 'Projection_7'
            {
            match("Projection_7"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "OPPROJECTION_7"

    // $ANTLR start "OPPROJECTION_8"
    public final void mOPPROJECTION_8() throws RecognitionException {
        try {
            int _type = OPPROJECTION_8;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:41:16: ( 'Projection_8' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:41:18: 'Projection_8'
            {
            match("Projection_8"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "OPPROJECTION_8"

    // $ANTLR start "OPPROJECTION_9"
    public final void mOPPROJECTION_9() throws RecognitionException {
        try {
            int _type = OPPROJECTION_9;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:42:16: ( 'Projection_9' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:42:18: 'Projection_9'
            {
            match("Projection_9"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "OPPROJECTION_9"

    // $ANTLR start "OPRANDOM"
    public final void mOPRANDOM() throws RecognitionException {
        try {
            int _type = OPRANDOM;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:43:10: ( 'Random' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:43:12: 'Random'
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

    // $ANTLR start "OPRELAX"
    public final void mOPRELAX() throws RecognitionException {
        try {
            int _type = OPRELAX;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:44:9: ( 'Relax' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:44:11: 'Relax'
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

    // $ANTLR start "OPREST"
    public final void mOPREST() throws RecognitionException {
        try {
            int _type = OPREST;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:45:8: ( 'Rest' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:45:10: 'Rest'
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

    // $ANTLR start "OPSQU"
    public final void mOPSQU() throws RecognitionException {
        try {
            int _type = OPSQU;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:46:7: ( 'Squeeze' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:46:9: 'Squeeze'
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
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:47:22: ( '[' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:47:24: '['
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
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:48:13: ( 'Substruct' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:48:15: 'Substruct'
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

    // $ANTLR start "T__54"
    public final void mT__54() throws RecognitionException {
        try {
            int _type = T__54;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:49:7: ( '(' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:49:9: '('
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
    // $ANTLR end "T__54"

    // $ANTLR start "T__55"
    public final void mT__55() throws RecognitionException {
        try {
            int _type = T__55;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:50:7: ( ')' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:50:9: ')'
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
    // $ANTLR end "T__55"

    // $ANTLR start "T__56"
    public final void mT__56() throws RecognitionException {
        try {
            int _type = T__56;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:51:7: ( '.' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:51:9: '.'
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
    // $ANTLR end "T__56"

    // $ANTLR start "T__57"
    public final void mT__57() throws RecognitionException {
        try {
            int _type = T__57;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:52:7: ( '=' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:52:9: '='
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
    // $ANTLR end "T__57"

    // $ANTLR start "T__58"
    public final void mT__58() throws RecognitionException {
        try {
            int _type = T__58;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:53:7: ( 'author' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:53:9: 'author'
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
    // $ANTLR end "T__58"

    // $ANTLR start "T__59"
    public final void mT__59() throws RecognitionException {
        try {
            int _type = T__59;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:54:7: ( 'beyond' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:54:9: 'beyond'
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
    // $ANTLR end "T__59"

    // $ANTLR start "T__60"
    public final void mT__60() throws RecognitionException {
        try {
            int _type = T__60;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:55:7: ( 'class' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:55:9: 'class'
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
    // $ANTLR end "T__60"

    // $ANTLR start "T__61"
    public final void mT__61() throws RecognitionException {
        try {
            int _type = T__61;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:56:7: ( 'conflictring' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:56:9: 'conflictring'
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
    // $ANTLR end "T__61"

    // $ANTLR start "T__62"
    public final void mT__62() throws RecognitionException {
        try {
            int _type = T__62;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:57:7: ( 'conflicts' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:57:9: 'conflicts'
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
    // $ANTLR end "T__62"

    // $ANTLR start "T__63"
    public final void mT__63() throws RecognitionException {
        try {
            int _type = T__63;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:58:7: ( 'data' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:58:9: 'data'
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
    // $ANTLR end "T__63"

    // $ANTLR start "T__64"
    public final void mT__64() throws RecognitionException {
        try {
            int _type = T__64;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:59:7: ( 'description' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:59:9: 'description'
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
    // $ANTLR end "T__64"

    // $ANTLR start "T__65"
    public final void mT__65() throws RecognitionException {
        try {
            int _type = T__65;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:60:7: ( 'entity_history_size' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:60:9: 'entity_history_size'
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
    // $ANTLR end "T__65"

    // $ANTLR start "T__66"
    public final void mT__66() throws RecognitionException {
        try {
            int _type = T__66;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:61:7: ( 'fringe' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:61:9: 'fringe'
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
    // $ANTLR end "T__66"

    // $ANTLR start "T__67"
    public final void mT__67() throws RecognitionException {
        try {
            int _type = T__67;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:62:7: ( 'include' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:62:9: 'include'
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
    // $ANTLR end "T__67"

    // $ANTLR start "T__68"
    public final void mT__68() throws RecognitionException {
        try {
            int _type = T__68;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:63:7: ( 'language' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:63:9: 'language'
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
    // $ANTLR end "T__68"

    // $ANTLR start "T__69"
    public final void mT__69() throws RecognitionException {
        try {
            int _type = T__69;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:64:7: ( 'module' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:64:9: 'module'
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
    // $ANTLR end "T__69"

    // $ANTLR start "T__70"
    public final void mT__70() throws RecognitionException {
        try {
            int _type = T__70;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:65:7: ( 'options' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:65:9: 'options'
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
    // $ANTLR end "T__70"

    // $ANTLR start "T__71"
    public final void mT__71() throws RecognitionException {
        try {
            int _type = T__71;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:66:7: ( 'package' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:66:9: 'package'
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
    // $ANTLR end "T__71"

    // $ANTLR start "T__72"
    public final void mT__72() throws RecognitionException {
        try {
            int _type = T__72;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:67:7: ( 'path' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:67:9: 'path'
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
    // $ANTLR end "T__72"

    // $ANTLR start "T__73"
    public final void mT__73() throws RecognitionException {
        try {
            int _type = T__73;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:68:7: ( 'project_name' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:68:9: 'project_name'
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
    // $ANTLR end "T__73"

    // $ANTLR start "T__74"
    public final void mT__74() throws RecognitionException {
        try {
            int _type = T__74;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:69:7: ( 'source' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:69:9: 'source'
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
    // $ANTLR end "T__74"

    // $ANTLR start "T__75"
    public final void mT__75() throws RecognitionException {
        try {
            int _type = T__75;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:70:7: ( 'visualizer' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:70:9: 'visualizer'
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
    // $ANTLR end "T__75"

    // $ANTLR start "T__76"
    public final void mT__76() throws RecognitionException {
        try {
            int _type = T__76;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:71:7: ( '{' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:71:9: '{'
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
    // $ANTLR end "T__76"

    // $ANTLR start "T__77"
    public final void mT__77() throws RecognitionException {
        try {
            int _type = T__77;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:72:7: ( '}' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:72:9: '}'
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
    // $ANTLR end "T__77"

    // $ANTLR start "ID"
    public final void mID() throws RecognitionException {
        try {
            int _type = ID;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:946:5: ( LETTER ( LETTER | '.' )* )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:946:7: LETTER ( LETTER | '.' )*
            {
            mLETTER(); 


            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:946:14: ( LETTER | '.' )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0=='!'||LA1_0=='.'||(LA1_0 >= '0' && LA1_0 <= '9')||LA1_0=='?'||(LA1_0 >= 'A' && LA1_0 <= 'Z')||LA1_0=='_'||(LA1_0 >= 'a' && LA1_0 <= 'z')) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:
            	    {
            	    if ( input.LA(1)=='!'||input.LA(1)=='.'||(input.LA(1) >= '0' && input.LA(1) <= '9')||input.LA(1)=='?'||(input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
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
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:950:5: ( '\"' (~ ( '\"' ) )* '\"' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:950:8: '\"' (~ ( '\"' ) )* '\"'
            {
            match('\"'); 

            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:950:12: (~ ( '\"' ) )*
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
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1021:5: ( ',' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1021:7: ','
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
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1025:5: ( '\"' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1025:7: '\"'
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
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1029:5: ( ';' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1029:7: ';'
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
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1033:5: ( ( ' ' | '\\t' | '\\n' | '\\r' ) )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1033:7: ( ' ' | '\\t' | '\\n' | '\\r' )
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
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1038:5: ( '<*' ( . )* '*>' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1038:7: '<*' ( . )* '*>'
            {
            match("<*"); 



            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1038:12: ( . )*
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
            	    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1038:12: .
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
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1042:5: ( '/*' ( options {greedy=false; } : . )* '*/' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1042:7: '/*' ( options {greedy=false; } : . )* '*/'
            {
            match("/*"); 



            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1042:12: ( options {greedy=false; } : . )*
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
            	    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1042:40: .
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
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1046:5: ( '//' (~ ( '\\n' | '\\r' ) )* ( '\\r' )? '\\n' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1046:7: '//' (~ ( '\\n' | '\\r' ) )* ( '\\r' )? '\\n'
            {
            match("//"); 



            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1046:12: (~ ( '\\n' | '\\r' ) )*
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


            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1046:26: ( '\\r' )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0=='\r') ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1046:26: '\\r'
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
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1053:2: ( 'A' .. 'Z' | 'a' .. 'z' | '0' .. '9' | '_' | '!' | '?' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:
            {
            if ( input.LA(1)=='!'||(input.LA(1) >= '0' && input.LA(1) <= '9')||input.LA(1)=='?'||(input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
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
        // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:8: ( C | CPP | IAS | JAVA | LIFETERM | OBJECTIVEC | OPACTIVATECTX | OPACTIVATEONFRINGE | OPAPPLYTOCONTEXT | OPBEGIN | OPBREAKPOINT | OPCARTESIAN | OPCLONE | OPCREATEEXPR | OPENDCONFLICTGROUP | OPEQ | OPEXECUTE | OPFIRST | OPIDENT | OPIN | OPINCL | OPINTERPRET | OPINTERSECT | OPJOIN | OPLAST | OPPROJECTION_1 | OPPROJECTION_2 | OPPROJECTION_3 | OPPROJECTION_4 | OPPROJECTION_5 | OPPROJECTION_6 | OPPROJECTION_7 | OPPROJECTION_8 | OPPROJECTION_9 | OPRANDOM | OPRELAX | OPREST | OPSQU | OPSTARTCONFLICTGROUP | OPSUBSTRUCT | T__54 | T__55 | T__56 | T__57 | T__58 | T__59 | T__60 | T__61 | T__62 | T__63 | T__64 | T__65 | T__66 | T__67 | T__68 | T__69 | T__70 | T__71 | T__72 | T__73 | T__74 | T__75 | T__76 | T__77 | ID | STRING_LITERAL | COMMA | DQUOTE | SEMICOLON | WS | NATIVE_CODE | COMMENT | LINE_COMMENT )
        int alt7=73;
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
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:45: OPACTIVATECTX
                {
                mOPACTIVATECTX(); 


                }
                break;
            case 8 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:59: OPACTIVATEONFRINGE
                {
                mOPACTIVATEONFRINGE(); 


                }
                break;
            case 9 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:78: OPAPPLYTOCONTEXT
                {
                mOPAPPLYTOCONTEXT(); 


                }
                break;
            case 10 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:95: OPBEGIN
                {
                mOPBEGIN(); 


                }
                break;
            case 11 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:103: OPBREAKPOINT
                {
                mOPBREAKPOINT(); 


                }
                break;
            case 12 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:116: OPCARTESIAN
                {
                mOPCARTESIAN(); 


                }
                break;
            case 13 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:128: OPCLONE
                {
                mOPCLONE(); 


                }
                break;
            case 14 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:136: OPCREATEEXPR
                {
                mOPCREATEEXPR(); 


                }
                break;
            case 15 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:149: OPENDCONFLICTGROUP
                {
                mOPENDCONFLICTGROUP(); 


                }
                break;
            case 16 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:168: OPEQ
                {
                mOPEQ(); 


                }
                break;
            case 17 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:173: OPEXECUTE
                {
                mOPEXECUTE(); 


                }
                break;
            case 18 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:183: OPFIRST
                {
                mOPFIRST(); 


                }
                break;
            case 19 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:191: OPIDENT
                {
                mOPIDENT(); 


                }
                break;
            case 20 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:199: OPIN
                {
                mOPIN(); 


                }
                break;
            case 21 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:204: OPINCL
                {
                mOPINCL(); 


                }
                break;
            case 22 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:211: OPINTERPRET
                {
                mOPINTERPRET(); 


                }
                break;
            case 23 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:223: OPINTERSECT
                {
                mOPINTERSECT(); 


                }
                break;
            case 24 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:235: OPJOIN
                {
                mOPJOIN(); 


                }
                break;
            case 25 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:242: OPLAST
                {
                mOPLAST(); 


                }
                break;
            case 26 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:249: OPPROJECTION_1
                {
                mOPPROJECTION_1(); 


                }
                break;
            case 27 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:264: OPPROJECTION_2
                {
                mOPPROJECTION_2(); 


                }
                break;
            case 28 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:279: OPPROJECTION_3
                {
                mOPPROJECTION_3(); 


                }
                break;
            case 29 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:294: OPPROJECTION_4
                {
                mOPPROJECTION_4(); 


                }
                break;
            case 30 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:309: OPPROJECTION_5
                {
                mOPPROJECTION_5(); 


                }
                break;
            case 31 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:324: OPPROJECTION_6
                {
                mOPPROJECTION_6(); 


                }
                break;
            case 32 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:339: OPPROJECTION_7
                {
                mOPPROJECTION_7(); 


                }
                break;
            case 33 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:354: OPPROJECTION_8
                {
                mOPPROJECTION_8(); 


                }
                break;
            case 34 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:369: OPPROJECTION_9
                {
                mOPPROJECTION_9(); 


                }
                break;
            case 35 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:384: OPRANDOM
                {
                mOPRANDOM(); 


                }
                break;
            case 36 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:393: OPRELAX
                {
                mOPRELAX(); 


                }
                break;
            case 37 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:401: OPREST
                {
                mOPREST(); 


                }
                break;
            case 38 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:408: OPSQU
                {
                mOPSQU(); 


                }
                break;
            case 39 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:414: OPSTARTCONFLICTGROUP
                {
                mOPSTARTCONFLICTGROUP(); 


                }
                break;
            case 40 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:435: OPSUBSTRUCT
                {
                mOPSUBSTRUCT(); 


                }
                break;
            case 41 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:447: T__54
                {
                mT__54(); 


                }
                break;
            case 42 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:453: T__55
                {
                mT__55(); 


                }
                break;
            case 43 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:459: T__56
                {
                mT__56(); 


                }
                break;
            case 44 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:465: T__57
                {
                mT__57(); 


                }
                break;
            case 45 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:471: T__58
                {
                mT__58(); 


                }
                break;
            case 46 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:477: T__59
                {
                mT__59(); 


                }
                break;
            case 47 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:483: T__60
                {
                mT__60(); 


                }
                break;
            case 48 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:489: T__61
                {
                mT__61(); 


                }
                break;
            case 49 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:495: T__62
                {
                mT__62(); 


                }
                break;
            case 50 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:501: T__63
                {
                mT__63(); 


                }
                break;
            case 51 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:507: T__64
                {
                mT__64(); 


                }
                break;
            case 52 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:513: T__65
                {
                mT__65(); 


                }
                break;
            case 53 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:519: T__66
                {
                mT__66(); 


                }
                break;
            case 54 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:525: T__67
                {
                mT__67(); 


                }
                break;
            case 55 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:531: T__68
                {
                mT__68(); 


                }
                break;
            case 56 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:537: T__69
                {
                mT__69(); 


                }
                break;
            case 57 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:543: T__70
                {
                mT__70(); 


                }
                break;
            case 58 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:549: T__71
                {
                mT__71(); 


                }
                break;
            case 59 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:555: T__72
                {
                mT__72(); 


                }
                break;
            case 60 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:561: T__73
                {
                mT__73(); 


                }
                break;
            case 61 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:567: T__74
                {
                mT__74(); 


                }
                break;
            case 62 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:573: T__75
                {
                mT__75(); 


                }
                break;
            case 63 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:579: T__76
                {
                mT__76(); 


                }
                break;
            case 64 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:585: T__77
                {
                mT__77(); 


                }
                break;
            case 65 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:591: ID
                {
                mID(); 


                }
                break;
            case 66 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:594: STRING_LITERAL
                {
                mSTRING_LITERAL(); 


                }
                break;
            case 67 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:609: COMMA
                {
                mCOMMA(); 


                }
                break;
            case 68 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:615: DQUOTE
                {
                mDQUOTE(); 


                }
                break;
            case 69 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:622: SEMICOLON
                {
                mSEMICOLON(); 


                }
                break;
            case 70 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:632: WS
                {
                mWS(); 


                }
                break;
            case 71 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:635: NATIVE_CODE
                {
                mNATIVE_CODE(); 


                }
                break;
            case 72 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:647: COMMENT
                {
                mCOMMENT(); 


                }
                break;
            case 73 :
                // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:655: LINE_COMMENT
                {
                mLINE_COMMENT(); 


                }
                break;

        }

    }


    protected DFA7 dfa7 = new DFA7(this);
    static final String DFA7_eotS =
        "\1\uffff\3\45\1\uffff\3\45\2\uffff\3\45\1\uffff\5\45\5\uffff\13"+
        "\45\3\uffff\1\122\5\uffff\5\45\1\134\4\45\1\141\1\142\3\45\1\150"+
        "\25\45\4\uffff\3\45\1\u0084\3\45\1\uffff\4\45\2\uffff\1\u008c\4"+
        "\45\1\uffff\33\45\1\uffff\7\45\1\uffff\4\45\1\u00b7\1\u00b8\3\45"+
        "\1\u00bc\6\45\1\u00c3\6\45\1\u00ca\3\45\1\u00ce\10\45\1\u00d7\1"+
        "\u00d8\1\u00d9\1\u00da\2\45\2\uffff\2\45\1\u00df\1\uffff\4\45\1"+
        "\u00e4\1\45\1\uffff\6\45\1\uffff\3\45\1\uffff\10\45\4\uffff\3\45"+
        "\1\u00fa\1\uffff\2\45\1\u00fd\1\u00fe\1\uffff\3\45\1\u0102\1\u0103"+
        "\3\45\1\u0107\1\45\1\u0109\2\45\1\u010c\2\45\1\u010f\1\45\1\u0111"+
        "\2\45\1\uffff\1\u0114\1\45\2\uffff\3\45\2\uffff\1\u0119\1\u011a"+
        "\1\45\1\uffff\1\45\1\uffff\1\u011d\1\45\1\uffff\1\u011f\1\u0120"+
        "\1\uffff\1\45\1\uffff\2\45\1\uffff\4\45\2\uffff\2\45\1\uffff\1\45"+
        "\2\uffff\1\u012c\1\u012d\1\45\1\u012f\1\45\1\u0131\5\45\2\uffff"+
        "\1\45\1\uffff\1\45\1\uffff\3\45\1\u013c\3\45\1\u0148\2\45\1\uffff"+
        "\1\45\1\u014c\1\u014d\1\u014e\1\u014f\1\u0150\1\u0151\1\u0152\1"+
        "\u0153\1\u0154\1\u0155\1\uffff\1\45\1\u0157\1\45\12\uffff\1\45\1"+
        "\uffff\2\45\1\u015c\1\45\1\uffff\3\45\1\u0161\1\uffff";
    static final String DFA7_eofS =
        "\u0162\uffff";
    static final String DFA7_minS =
        "\1\11\1\137\2\141\1\uffff\1\157\1\141\1\145\2\uffff\1\161\1\151"+
        "\1\144\1\uffff\1\157\1\141\1\162\1\141\1\161\5\uffff\1\165\1\145"+
        "\1\154\1\141\1\156\1\162\1\157\1\160\1\141\1\157\1\151\3\uffff\1"+
        "\0\4\uffff\1\52\1\143\1\163\1\143\1\146\1\156\1\41\1\156\1\162\1"+
        "\157\1\147\2\41\1\145\1\162\1\145\1\41\1\151\1\163\1\157\1\156\1"+
        "\154\1\165\1\142\1\164\1\171\1\141\1\156\1\164\1\163\1\164\1\151"+
        "\1\144\1\164\1\143\1\157\1\165\1\163\4\uffff\1\137\1\141\1\142\1"+
        "\41\1\154\1\145\1\147\1\uffff\2\164\1\156\1\151\2\uffff\1\41\1\163"+
        "\1\156\1\154\1\145\1\uffff\1\156\1\164\1\152\1\144\1\141\1\164\1"+
        "\145\1\163\1\150\1\157\1\163\1\146\1\141\1\143\1\151\1\156\1\165"+
        "\1\151\1\153\1\150\1\152\1\162\1\165\1\137\1\160\1\166\1\152\1\uffff"+
        "\1\165\1\164\1\165\3\145\1\156\1\uffff\2\164\1\165\1\162\2\41\1"+
        "\145\1\157\1\170\1\41\1\145\1\164\1\157\1\156\1\163\1\154\1\41\1"+
        "\162\1\164\1\147\1\154\1\157\1\141\1\41\1\145\1\143\1\141\1\41\1"+
        "\137\1\141\1\145\1\144\1\145\1\141\1\170\1\163\4\41\1\144\1\163"+
        "\2\uffff\1\143\1\155\1\41\1\uffff\1\172\2\162\1\144\1\41\1\151\1"+
        "\uffff\1\151\1\171\2\145\1\156\1\147\1\uffff\1\143\1\145\1\154\1"+
        "\uffff\2\137\1\143\1\145\1\162\1\147\1\164\1\151\4\uffff\2\145\1"+
        "\164\1\41\1\uffff\1\145\1\165\2\41\1\uffff\1\143\1\160\1\137\2\41"+
        "\1\163\1\145\1\164\1\41\1\151\1\41\1\137\1\164\1\41\1\155\1\145"+
        "\1\41\1\141\1\41\1\143\1\151\1\uffff\1\41\1\143\2\uffff\2\164\1"+
        "\150\2\uffff\2\41\1\137\1\uffff\1\172\1\uffff\1\41\1\151\1\uffff"+
        "\2\41\1\uffff\1\156\1\uffff\1\164\1\157\1\uffff\1\164\1\162\2\151"+
        "\2\uffff\1\156\1\145\1\uffff\1\166\2\uffff\2\41\1\156\1\41\1\151"+
        "\1\41\1\157\1\163\1\141\1\162\1\145\2\uffff\1\137\1\uffff\1\156"+
        "\1\uffff\1\156\1\164\1\155\1\41\1\137\1\61\1\147\1\41\1\157\1\145"+
        "\1\uffff\1\143\12\41\1\uffff\1\162\1\41\1\137\12\uffff\1\171\1\uffff"+
        "\2\137\1\41\1\163\1\uffff\1\151\1\172\1\145\1\41\1\uffff";
    static final String DFA7_maxS =
        "\1\176\1\137\1\156\1\151\1\uffff\2\157\1\160\2\uffff\1\170\1\151"+
        "\1\156\1\uffff\1\157\1\141\1\162\1\145\1\165\5\uffff\1\165\1\145"+
        "\1\157\1\145\1\156\1\162\1\157\1\160\1\162\1\157\1\151\3\uffff\1"+
        "\uffff\4\uffff\1\57\1\157\1\163\1\143\1\146\1\156\1\172\1\156\1"+
        "\162\1\157\1\147\2\172\1\145\1\162\1\145\1\172\1\151\1\163\1\157"+
        "\1\156\1\163\1\165\1\142\1\164\1\171\1\141\1\156\1\164\1\163\1\164"+
        "\1\151\1\144\2\164\1\157\1\165\1\163\4\uffff\1\160\1\141\1\142\1"+
        "\172\1\154\1\145\1\147\1\uffff\2\164\1\156\1\151\2\uffff\1\172\1"+
        "\163\1\156\1\154\1\145\1\uffff\1\156\1\164\1\152\1\144\1\141\1\164"+
        "\1\145\1\163\1\150\1\157\1\163\1\146\1\141\1\143\1\151\1\156\1\165"+
        "\1\151\1\153\1\150\1\152\1\162\1\165\1\137\1\160\1\166\1\152\1\uffff"+
        "\1\165\1\164\1\165\3\145\1\156\1\uffff\2\164\1\165\1\162\2\172\1"+
        "\145\1\157\1\170\1\172\1\145\1\164\1\157\1\156\1\163\1\154\1\172"+
        "\1\162\1\164\1\147\1\154\1\157\1\141\1\172\1\145\1\143\1\141\1\172"+
        "\1\137\1\141\1\145\1\144\1\145\1\141\1\170\1\163\4\172\1\144\1\163"+
        "\2\uffff\1\143\1\155\1\172\1\uffff\1\172\2\162\1\144\1\172\1\151"+
        "\1\uffff\1\151\1\171\2\145\1\156\1\147\1\uffff\1\143\1\145\1\154"+
        "\1\uffff\2\137\1\143\1\145\1\162\1\147\1\164\1\151\4\uffff\2\145"+
        "\1\164\1\172\1\uffff\1\145\1\165\2\172\1\uffff\1\143\1\160\1\137"+
        "\2\172\1\163\1\145\1\164\1\172\1\151\1\172\1\137\1\164\1\172\1\155"+
        "\1\145\1\172\1\141\1\172\1\143\1\151\1\uffff\1\172\1\143\2\uffff"+
        "\2\164\1\150\2\uffff\2\172\1\137\1\uffff\1\172\1\uffff\1\172\1\151"+
        "\1\uffff\2\172\1\uffff\1\156\1\uffff\1\164\1\157\1\uffff\1\164\1"+
        "\163\2\151\2\uffff\1\156\1\145\1\uffff\1\166\2\uffff\2\172\1\156"+
        "\1\172\1\151\1\172\1\157\1\163\1\141\1\162\1\145\2\uffff\1\137\1"+
        "\uffff\1\156\1\uffff\1\156\1\164\1\155\1\172\1\137\1\71\1\147\1"+
        "\172\1\157\1\145\1\uffff\1\143\12\172\1\uffff\1\162\1\172\1\137"+
        "\12\uffff\1\171\1\uffff\2\137\1\172\1\163\1\uffff\1\151\1\172\1"+
        "\145\1\172\1\uffff";
    static final String DFA7_acceptS =
        "\4\uffff\1\7\3\uffff\1\16\1\17\3\uffff\1\26\5\uffff\1\47\1\51\1"+
        "\52\1\53\1\54\13\uffff\1\77\1\100\1\101\1\uffff\1\103\1\105\1\106"+
        "\1\107\46\uffff\1\102\1\104\1\110\1\111\7\uffff\1\10\4\uffff\1\13"+
        "\1\20\5\uffff\1\24\33\uffff\1\3\7\uffff\1\21\52\uffff\1\30\1\31"+
        "\3\uffff\1\45\6\uffff\1\62\6\uffff\1\73\3\uffff\1\1\10\uffff\1\15"+
        "\1\12\1\22\1\23\4\uffff\1\44\4\uffff\1\57\25\uffff\1\43\2\uffff"+
        "\1\55\1\56\3\uffff\1\65\1\70\3\uffff\1\75\1\uffff\1\2\2\uffff\1"+
        "\66\2\uffff\1\11\1\uffff\1\25\2\uffff\1\46\4\uffff\1\71\1\72\2\uffff"+
        "\1\4\1\uffff\1\5\1\67\13\uffff\1\14\1\27\1\uffff\1\50\1\uffff\1"+
        "\61\12\uffff\1\76\13\uffff\1\63\3\uffff\1\32\1\33\1\34\1\35\1\36"+
        "\1\37\1\40\1\41\1\42\1\60\1\uffff\1\74\4\uffff\1\6\4\uffff\1\64";
    static final String DFA7_specialS =
        "\46\uffff\1\0\u013b\uffff}>";
    static final String[] DFA7_transitionS = {
            "\2\51\2\uffff\1\51\22\uffff\1\51\1\45\1\46\5\uffff\1\24\1\25"+
            "\2\uffff\1\47\1\uffff\1\26\1\53\12\45\1\4\1\50\1\52\1\27\1\uffff"+
            "\1\45\1\uffff\1\45\1\7\1\6\1\5\1\12\1\13\2\45\1\14\1\16\1\45"+
            "\1\17\3\45\1\20\1\45\1\21\1\22\7\45\1\23\1\uffff\1\11\1\10\1"+
            "\1\1\uffff\1\30\1\31\1\32\1\33\1\34\1\35\2\45\1\2\2\45\1\3\1"+
            "\36\1\45\1\37\1\40\2\45\1\41\2\45\1\42\4\45\1\43\1\uffff\1\44"+
            "\1\15",
            "\1\54",
            "\1\55\14\uffff\1\56",
            "\1\60\7\uffff\1\57",
            "",
            "\1\61",
            "\1\63\12\uffff\1\64\2\uffff\1\62",
            "\1\65\12\uffff\1\66",
            "",
            "",
            "\1\67\6\uffff\1\70",
            "\1\71",
            "\1\72\11\uffff\1\73",
            "",
            "\1\74",
            "\1\75",
            "\1\76",
            "\1\77\3\uffff\1\100",
            "\1\101\3\uffff\1\102",
            "",
            "",
            "",
            "",
            "",
            "\1\103",
            "\1\104",
            "\1\105\2\uffff\1\106",
            "\1\107\3\uffff\1\110",
            "\1\111",
            "\1\112",
            "\1\113",
            "\1\114",
            "\1\115\20\uffff\1\116",
            "\1\117",
            "\1\120",
            "",
            "",
            "",
            "\0\121",
            "",
            "",
            "",
            "",
            "\1\123\4\uffff\1\124",
            "\1\125\6\uffff\1\126\4\uffff\1\127",
            "\1\130",
            "\1\131",
            "\1\132",
            "\1\133",
            "\1\45\14\uffff\1\45\1\uffff\12\45\5\uffff\1\45\1\uffff\32\45"+
            "\4\uffff\1\45\1\uffff\32\45",
            "\1\135",
            "\1\136",
            "\1\137",
            "\1\140",
            "\1\45\14\uffff\1\45\1\uffff\12\45\5\uffff\1\45\1\uffff\32\45"+
            "\4\uffff\1\45\1\uffff\32\45",
            "\1\45\14\uffff\1\45\1\uffff\12\45\5\uffff\1\45\1\uffff\32\45"+
            "\4\uffff\1\45\1\uffff\32\45",
            "\1\143",
            "\1\144",
            "\1\145",
            "\1\45\14\uffff\1\45\1\uffff\12\45\5\uffff\1\45\1\uffff\32\45"+
            "\4\uffff\1\45\1\uffff\2\45\1\146\20\45\1\147\6\45",
            "\1\151",
            "\1\152",
            "\1\153",
            "\1\154",
            "\1\155\6\uffff\1\156",
            "\1\157",
            "\1\160",
            "\1\161",
            "\1\162",
            "\1\163",
            "\1\164",
            "\1\165",
            "\1\166",
            "\1\167",
            "\1\170",
            "\1\171",
            "\1\172",
            "\1\173\20\uffff\1\174",
            "\1\175",
            "\1\176",
            "\1\177",
            "",
            "",
            "",
            "",
            "\1\u0080\20\uffff\1\u0081",
            "\1\u0082",
            "\1\u0083",
            "\1\45\14\uffff\1\45\1\uffff\12\45\5\uffff\1\45\1\uffff\32\45"+
            "\4\uffff\1\45\1\uffff\32\45",
            "\1\u0085",
            "\1\u0086",
            "\1\u0087",
            "",
            "\1\u0088",
            "\1\u0089",
            "\1\u008a",
            "\1\u008b",
            "",
            "",
            "\1\45\14\uffff\1\45\1\uffff\12\45\5\uffff\1\45\1\uffff\32\45"+
            "\4\uffff\1\45\1\uffff\32\45",
            "\1\u008d",
            "\1\u008e",
            "\1\u008f",
            "\1\u0090",
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
            "\1\u009c",
            "\1\u009d",
            "\1\u009e",
            "\1\u009f",
            "\1\u00a0",
            "\1\u00a1",
            "\1\u00a2",
            "\1\u00a3",
            "\1\u00a4",
            "\1\u00a5",
            "\1\u00a6",
            "\1\u00a7",
            "\1\u00a8",
            "\1\u00a9",
            "\1\u00aa",
            "\1\u00ab",
            "",
            "\1\u00ac",
            "\1\u00ad",
            "\1\u00ae",
            "\1\u00af",
            "\1\u00b0",
            "\1\u00b1",
            "\1\u00b2",
            "",
            "\1\u00b3",
            "\1\u00b4",
            "\1\u00b5",
            "\1\u00b6",
            "\1\45\14\uffff\1\45\1\uffff\12\45\5\uffff\1\45\1\uffff\32\45"+
            "\4\uffff\1\45\1\uffff\32\45",
            "\1\45\14\uffff\1\45\1\uffff\12\45\5\uffff\1\45\1\uffff\32\45"+
            "\4\uffff\1\45\1\uffff\32\45",
            "\1\u00b9",
            "\1\u00ba",
            "\1\u00bb",
            "\1\45\14\uffff\1\45\1\uffff\12\45\5\uffff\1\45\1\uffff\32\45"+
            "\4\uffff\1\45\1\uffff\32\45",
            "\1\u00bd",
            "\1\u00be",
            "\1\u00bf",
            "\1\u00c0",
            "\1\u00c1",
            "\1\u00c2",
            "\1\45\14\uffff\1\45\1\uffff\12\45\5\uffff\1\45\1\uffff\32\45"+
            "\4\uffff\1\45\1\uffff\32\45",
            "\1\u00c4",
            "\1\u00c5",
            "\1\u00c6",
            "\1\u00c7",
            "\1\u00c8",
            "\1\u00c9",
            "\1\45\14\uffff\1\45\1\uffff\12\45\5\uffff\1\45\1\uffff\32\45"+
            "\4\uffff\1\45\1\uffff\32\45",
            "\1\u00cb",
            "\1\u00cc",
            "\1\u00cd",
            "\1\45\14\uffff\1\45\1\uffff\12\45\5\uffff\1\45\1\uffff\32\45"+
            "\4\uffff\1\45\1\uffff\32\45",
            "\1\u00cf",
            "\1\u00d0",
            "\1\u00d1",
            "\1\u00d2",
            "\1\u00d3",
            "\1\u00d4",
            "\1\u00d5",
            "\1\u00d6",
            "\1\45\14\uffff\1\45\1\uffff\12\45\5\uffff\1\45\1\uffff\32\45"+
            "\4\uffff\1\45\1\uffff\32\45",
            "\1\45\14\uffff\1\45\1\uffff\12\45\5\uffff\1\45\1\uffff\32\45"+
            "\4\uffff\1\45\1\uffff\32\45",
            "\1\45\14\uffff\1\45\1\uffff\12\45\5\uffff\1\45\1\uffff\32\45"+
            "\4\uffff\1\45\1\uffff\32\45",
            "\1\45\14\uffff\1\45\1\uffff\12\45\5\uffff\1\45\1\uffff\32\45"+
            "\4\uffff\1\45\1\uffff\32\45",
            "\1\u00db",
            "\1\u00dc",
            "",
            "",
            "\1\u00dd",
            "\1\u00de",
            "\1\45\14\uffff\1\45\1\uffff\12\45\5\uffff\1\45\1\uffff\32\45"+
            "\4\uffff\1\45\1\uffff\32\45",
            "",
            "\1\u00e0",
            "\1\u00e1",
            "\1\u00e2",
            "\1\u00e3",
            "\1\45\14\uffff\1\45\1\uffff\12\45\5\uffff\1\45\1\uffff\32\45"+
            "\4\uffff\1\45\1\uffff\32\45",
            "\1\u00e5",
            "",
            "\1\u00e6",
            "\1\u00e7",
            "\1\u00e8",
            "\1\u00e9",
            "\1\u00ea",
            "\1\u00eb",
            "",
            "\1\u00ec",
            "\1\u00ed",
            "\1\u00ee",
            "",
            "\1\u00ef",
            "\1\u00f0",
            "\1\u00f1",
            "\1\u00f2",
            "\1\u00f3",
            "\1\u00f4",
            "\1\u00f5",
            "\1\u00f6",
            "",
            "",
            "",
            "",
            "\1\u00f7",
            "\1\u00f8",
            "\1\u00f9",
            "\1\45\14\uffff\1\45\1\uffff\12\45\5\uffff\1\45\1\uffff\32\45"+
            "\4\uffff\1\45\1\uffff\32\45",
            "",
            "\1\u00fb",
            "\1\u00fc",
            "\1\45\14\uffff\1\45\1\uffff\12\45\5\uffff\1\45\1\uffff\32\45"+
            "\4\uffff\1\45\1\uffff\32\45",
            "\1\45\14\uffff\1\45\1\uffff\12\45\5\uffff\1\45\1\uffff\32\45"+
            "\4\uffff\1\45\1\uffff\32\45",
            "",
            "\1\u00ff",
            "\1\u0100",
            "\1\u0101",
            "\1\45\14\uffff\1\45\1\uffff\12\45\5\uffff\1\45\1\uffff\32\45"+
            "\4\uffff\1\45\1\uffff\32\45",
            "\1\45\14\uffff\1\45\1\uffff\12\45\5\uffff\1\45\1\uffff\32\45"+
            "\4\uffff\1\45\1\uffff\32\45",
            "\1\u0104",
            "\1\u0105",
            "\1\u0106",
            "\1\45\14\uffff\1\45\1\uffff\12\45\5\uffff\1\45\1\uffff\32\45"+
            "\4\uffff\1\45\1\uffff\32\45",
            "\1\u0108",
            "\1\45\14\uffff\1\45\1\uffff\12\45\5\uffff\1\45\1\uffff\32\45"+
            "\4\uffff\1\45\1\uffff\32\45",
            "\1\u010a",
            "\1\u010b",
            "\1\45\14\uffff\1\45\1\uffff\12\45\5\uffff\1\45\1\uffff\32\45"+
            "\4\uffff\1\45\1\uffff\32\45",
            "\1\u010d",
            "\1\u010e",
            "\1\45\14\uffff\1\45\1\uffff\12\45\5\uffff\1\45\1\uffff\32\45"+
            "\4\uffff\1\45\1\uffff\32\45",
            "\1\u0110",
            "\1\45\14\uffff\1\45\1\uffff\12\45\5\uffff\1\45\1\uffff\32\45"+
            "\4\uffff\1\45\1\uffff\32\45",
            "\1\u0112",
            "\1\u0113",
            "",
            "\1\45\14\uffff\1\45\1\uffff\12\45\5\uffff\1\45\1\uffff\32\45"+
            "\4\uffff\1\45\1\uffff\32\45",
            "\1\u0115",
            "",
            "",
            "\1\u0116",
            "\1\u0117",
            "\1\u0118",
            "",
            "",
            "\1\45\14\uffff\1\45\1\uffff\12\45\5\uffff\1\45\1\uffff\32\45"+
            "\4\uffff\1\45\1\uffff\32\45",
            "\1\45\14\uffff\1\45\1\uffff\12\45\5\uffff\1\45\1\uffff\32\45"+
            "\4\uffff\1\45\1\uffff\32\45",
            "\1\u011b",
            "",
            "\1\u011c",
            "",
            "\1\45\14\uffff\1\45\1\uffff\12\45\5\uffff\1\45\1\uffff\32\45"+
            "\4\uffff\1\45\1\uffff\32\45",
            "\1\u011e",
            "",
            "\1\45\14\uffff\1\45\1\uffff\12\45\5\uffff\1\45\1\uffff\32\45"+
            "\4\uffff\1\45\1\uffff\32\45",
            "\1\45\14\uffff\1\45\1\uffff\12\45\5\uffff\1\45\1\uffff\32\45"+
            "\4\uffff\1\45\1\uffff\32\45",
            "",
            "\1\u0121",
            "",
            "\1\u0122",
            "\1\u0123",
            "",
            "\1\u0124",
            "\1\u0125\1\u0126",
            "\1\u0127",
            "\1\u0128",
            "",
            "",
            "\1\u0129",
            "\1\u012a",
            "",
            "\1\u012b",
            "",
            "",
            "\1\45\14\uffff\1\45\1\uffff\12\45\5\uffff\1\45\1\uffff\32\45"+
            "\4\uffff\1\45\1\uffff\32\45",
            "\1\45\14\uffff\1\45\1\uffff\12\45\5\uffff\1\45\1\uffff\32\45"+
            "\4\uffff\1\45\1\uffff\32\45",
            "\1\u012e",
            "\1\45\14\uffff\1\45\1\uffff\12\45\5\uffff\1\45\1\uffff\32\45"+
            "\4\uffff\1\45\1\uffff\32\45",
            "\1\u0130",
            "\1\45\14\uffff\1\45\1\uffff\12\45\5\uffff\1\45\1\uffff\32\45"+
            "\4\uffff\1\45\1\uffff\32\45",
            "\1\u0132",
            "\1\u0133",
            "\1\u0134",
            "\1\u0135",
            "\1\u0136",
            "",
            "",
            "\1\u0137",
            "",
            "\1\u0138",
            "",
            "\1\u0139",
            "\1\u013a",
            "\1\u013b",
            "\1\45\14\uffff\1\45\1\uffff\12\45\5\uffff\1\45\1\uffff\32\45"+
            "\4\uffff\1\45\1\uffff\32\45",
            "\1\u013d",
            "\1\u013e\1\u013f\1\u0140\1\u0141\1\u0142\1\u0143\1\u0144\1"+
            "\u0145\1\u0146",
            "\1\u0147",
            "\1\45\14\uffff\1\45\1\uffff\12\45\5\uffff\1\45\1\uffff\32\45"+
            "\4\uffff\1\45\1\uffff\32\45",
            "\1\u0149",
            "\1\u014a",
            "",
            "\1\u014b",
            "\1\45\14\uffff\1\45\1\uffff\12\45\5\uffff\1\45\1\uffff\32\45"+
            "\4\uffff\1\45\1\uffff\32\45",
            "\1\45\14\uffff\1\45\1\uffff\12\45\5\uffff\1\45\1\uffff\32\45"+
            "\4\uffff\1\45\1\uffff\32\45",
            "\1\45\14\uffff\1\45\1\uffff\12\45\5\uffff\1\45\1\uffff\32\45"+
            "\4\uffff\1\45\1\uffff\32\45",
            "\1\45\14\uffff\1\45\1\uffff\12\45\5\uffff\1\45\1\uffff\32\45"+
            "\4\uffff\1\45\1\uffff\32\45",
            "\1\45\14\uffff\1\45\1\uffff\12\45\5\uffff\1\45\1\uffff\32\45"+
            "\4\uffff\1\45\1\uffff\32\45",
            "\1\45\14\uffff\1\45\1\uffff\12\45\5\uffff\1\45\1\uffff\32\45"+
            "\4\uffff\1\45\1\uffff\32\45",
            "\1\45\14\uffff\1\45\1\uffff\12\45\5\uffff\1\45\1\uffff\32\45"+
            "\4\uffff\1\45\1\uffff\32\45",
            "\1\45\14\uffff\1\45\1\uffff\12\45\5\uffff\1\45\1\uffff\32\45"+
            "\4\uffff\1\45\1\uffff\32\45",
            "\1\45\14\uffff\1\45\1\uffff\12\45\5\uffff\1\45\1\uffff\32\45"+
            "\4\uffff\1\45\1\uffff\32\45",
            "\1\45\14\uffff\1\45\1\uffff\12\45\5\uffff\1\45\1\uffff\32\45"+
            "\4\uffff\1\45\1\uffff\32\45",
            "",
            "\1\u0156",
            "\1\45\14\uffff\1\45\1\uffff\12\45\5\uffff\1\45\1\uffff\32\45"+
            "\4\uffff\1\45\1\uffff\32\45",
            "\1\u0158",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\u0159",
            "",
            "\1\u015a",
            "\1\u015b",
            "\1\45\14\uffff\1\45\1\uffff\12\45\5\uffff\1\45\1\uffff\32\45"+
            "\4\uffff\1\45\1\uffff\32\45",
            "\1\u015d",
            "",
            "\1\u015e",
            "\1\u015f",
            "\1\u0160",
            "\1\45\14\uffff\1\45\1\uffff\12\45\5\uffff\1\45\1\uffff\32\45"+
            "\4\uffff\1\45\1\uffff\32\45",
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
            return "1:1: Tokens : ( C | CPP | IAS | JAVA | LIFETERM | OBJECTIVEC | OPACTIVATECTX | OPACTIVATEONFRINGE | OPAPPLYTOCONTEXT | OPBEGIN | OPBREAKPOINT | OPCARTESIAN | OPCLONE | OPCREATEEXPR | OPENDCONFLICTGROUP | OPEQ | OPEXECUTE | OPFIRST | OPIDENT | OPIN | OPINCL | OPINTERPRET | OPINTERSECT | OPJOIN | OPLAST | OPPROJECTION_1 | OPPROJECTION_2 | OPPROJECTION_3 | OPPROJECTION_4 | OPPROJECTION_5 | OPPROJECTION_6 | OPPROJECTION_7 | OPPROJECTION_8 | OPPROJECTION_9 | OPRANDOM | OPRELAX | OPREST | OPSQU | OPSTARTCONFLICTGROUP | OPSUBSTRUCT | T__54 | T__55 | T__56 | T__57 | T__58 | T__59 | T__60 | T__61 | T__62 | T__63 | T__64 | T__65 | T__66 | T__67 | T__68 | T__69 | T__70 | T__71 | T__72 | T__73 | T__74 | T__75 | T__76 | T__77 | ID | STRING_LITERAL | COMMA | DQUOTE | SEMICOLON | WS | NATIVE_CODE | COMMENT | LINE_COMMENT );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            IntStream input = _input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA7_38 = input.LA(1);

                        s = -1;
                        if ( ((LA7_38 >= '\u0000' && LA7_38 <= '\uFFFF')) ) {s = 81;}

                        else s = 82;

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