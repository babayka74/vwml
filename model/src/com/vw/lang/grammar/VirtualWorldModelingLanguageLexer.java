// $ANTLR 3.4 C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g 2013-09-03 16:09:30
 
package com.vw.lang.grammar;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked"})
public class VirtualWorldModelingLanguageLexer extends Lexer {
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
    public String getGrammarFileName() { return "C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g"; }

    // $ANTLR start "C"
    public final void mC() throws RecognitionException {
        try {
            int _type = C;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:9:3: ( '__c__' )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:9:5: '__c__'
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
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:10:5: ( '__cpp__' )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:10:7: '__cpp__'
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
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:11:5: ( 'ias' )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:11:7: 'ias'
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
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:12:6: ( '__java__' )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:12:8: '__java__'
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

    // $ANTLR start "NIL"
    public final void mNIL() throws RecognitionException {
        try {
            int _type = NIL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:13:5: ( 'nil' )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:13:7: 'nil'
            {
            match("nil"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "NIL"

    // $ANTLR start "OBJECTIVEC"
    public final void mOBJECTIVEC() throws RecognitionException {
        try {
            int _type = OBJECTIVEC;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:14:12: ( '__objective_c__' )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:14:14: '__objective_c__'
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

    // $ANTLR start "OPBEGIN"
    public final void mOPBEGIN() throws RecognitionException {
        try {
            int _type = OPBEGIN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:15:9: ( 'Begin' )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:15:11: 'Begin'
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

    // $ANTLR start "OPCARTESIAN"
    public final void mOPCARTESIAN() throws RecognitionException {
        try {
            int _type = OPCARTESIAN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:16:13: ( 'Cartesian' )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:16:15: 'Cartesian'
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

    // $ANTLR start "OPCREATEEXPR"
    public final void mOPCREATEEXPR() throws RecognitionException {
        try {
            int _type = OPCREATEEXPR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:17:14: ( '^' )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:17:16: '^'
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

    // $ANTLR start "OPEQ"
    public final void mOPEQ() throws RecognitionException {
        try {
            int _type = OPEQ;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:18:6: ( 'Eq' )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:18:8: 'Eq'
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
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:19:11: ( 'Exe' )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:19:13: 'Exe'
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
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:20:9: ( 'First' )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:20:11: 'First'
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
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:21:9: ( 'Ident' )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:21:11: 'Ident'
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
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:22:6: ( 'In' )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:22:8: 'In'
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
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:23:8: ( 'Include' )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:23:10: 'Include'
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
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:24:13: ( '~' )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:24:15: '~'
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
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:25:13: ( 'Intersect' )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:25:15: 'Intersect'
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
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:26:8: ( 'Join' )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:26:10: 'Join'
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
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:27:8: ( 'Last' )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:27:10: 'Last'
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
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:28:16: ( 'Projection_1' )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:28:18: 'Projection_1'
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
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:29:16: ( 'Projection_2' )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:29:18: 'Projection_2'
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
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:30:16: ( 'Projection_3' )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:30:18: 'Projection_3'
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
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:31:16: ( 'Projection_4' )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:31:18: 'Projection_4'
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
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:32:16: ( 'Projection_5' )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:32:18: 'Projection_5'
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
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:33:16: ( 'Projection_6' )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:33:18: 'Projection_6'
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
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:34:16: ( 'Projection_7' )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:34:18: 'Projection_7'
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
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:35:16: ( 'Projection_8' )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:35:18: 'Projection_8'
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
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:36:16: ( 'Projection_9' )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:36:18: 'Projection_9'
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
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:37:10: ( 'Random' )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:37:12: 'Random'
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

    // $ANTLR start "OPREST"
    public final void mOPREST() throws RecognitionException {
        try {
            int _type = OPREST;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:38:8: ( 'Rest' )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:38:10: 'Rest'
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
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:39:7: ( 'Squeeze' )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:39:9: 'Squeeze'
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

    // $ANTLR start "OPSUBSTRUCT"
    public final void mOPSUBSTRUCT() throws RecognitionException {
        try {
            int _type = OPSUBSTRUCT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:40:13: ( 'Substruct' )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:40:15: 'Substruct'
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

    // $ANTLR start "T__46"
    public final void mT__46() throws RecognitionException {
        try {
            int _type = T__46;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:41:7: ( '(' )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:41:9: '('
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
    // $ANTLR end "T__46"

    // $ANTLR start "T__47"
    public final void mT__47() throws RecognitionException {
        try {
            int _type = T__47;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:42:7: ( ')' )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:42:9: ')'
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
    // $ANTLR end "T__47"

    // $ANTLR start "T__48"
    public final void mT__48() throws RecognitionException {
        try {
            int _type = T__48;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:43:7: ( '=' )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:43:9: '='
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
    // $ANTLR end "T__48"

    // $ANTLR start "T__49"
    public final void mT__49() throws RecognitionException {
        try {
            int _type = T__49;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:44:7: ( '[' )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:44:9: '['
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
    // $ANTLR end "T__49"

    // $ANTLR start "T__50"
    public final void mT__50() throws RecognitionException {
        try {
            int _type = T__50;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:45:7: ( ']' )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:45:9: ']'
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
    // $ANTLR end "T__50"

    // $ANTLR start "T__51"
    public final void mT__51() throws RecognitionException {
        try {
            int _type = T__51;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:46:7: ( 'author' )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:46:9: 'author'
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
    // $ANTLR end "T__51"

    // $ANTLR start "T__52"
    public final void mT__52() throws RecognitionException {
        try {
            int _type = T__52;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:47:7: ( 'class' )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:47:9: 'class'
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
    // $ANTLR end "T__52"

    // $ANTLR start "T__53"
    public final void mT__53() throws RecognitionException {
        try {
            int _type = T__53;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:48:7: ( 'data' )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:48:9: 'data'
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
    // $ANTLR end "T__53"

    // $ANTLR start "T__54"
    public final void mT__54() throws RecognitionException {
        try {
            int _type = T__54;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:49:7: ( 'description' )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:49:9: 'description'
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
    // $ANTLR end "T__54"

    // $ANTLR start "T__55"
    public final void mT__55() throws RecognitionException {
        try {
            int _type = T__55;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:50:7: ( 'include' )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:50:9: 'include'
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
    // $ANTLR end "T__55"

    // $ANTLR start "T__56"
    public final void mT__56() throws RecognitionException {
        try {
            int _type = T__56;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:51:7: ( 'language' )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:51:9: 'language'
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
    // $ANTLR end "T__56"

    // $ANTLR start "T__57"
    public final void mT__57() throws RecognitionException {
        try {
            int _type = T__57;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:52:7: ( 'module' )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:52:9: 'module'
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
    // $ANTLR end "T__57"

    // $ANTLR start "T__58"
    public final void mT__58() throws RecognitionException {
        try {
            int _type = T__58;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:53:7: ( 'options' )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:53:9: 'options'
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
    // $ANTLR end "T__58"

    // $ANTLR start "T__59"
    public final void mT__59() throws RecognitionException {
        try {
            int _type = T__59;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:54:7: ( 'package' )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:54:9: 'package'
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
    // $ANTLR end "T__59"

    // $ANTLR start "T__60"
    public final void mT__60() throws RecognitionException {
        try {
            int _type = T__60;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:55:7: ( 'path' )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:55:9: 'path'
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
    // $ANTLR end "T__60"

    // $ANTLR start "T__61"
    public final void mT__61() throws RecognitionException {
        try {
            int _type = T__61;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:56:7: ( 'project_name' )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:56:9: 'project_name'
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
    // $ANTLR end "T__61"

    // $ANTLR start "T__62"
    public final void mT__62() throws RecognitionException {
        try {
            int _type = T__62;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:57:7: ( 'visualizer' )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:57:9: 'visualizer'
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
    // $ANTLR end "T__62"

    // $ANTLR start "T__63"
    public final void mT__63() throws RecognitionException {
        try {
            int _type = T__63;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:58:7: ( '{' )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:58:9: '{'
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
    // $ANTLR end "T__63"

    // $ANTLR start "T__64"
    public final void mT__64() throws RecognitionException {
        try {
            int _type = T__64;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:59:7: ( '}' )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:59:9: '}'
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
    // $ANTLR end "T__64"

    // $ANTLR start "ID"
    public final void mID() throws RecognitionException {
        try {
            int _type = ID;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:513:5: ( LETTER ( LETTER | '0' .. '9' )* )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:513:7: LETTER ( LETTER | '0' .. '9' )*
            {
            mLETTER(); 


            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:513:14: ( LETTER | '0' .. '9' )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( ((LA1_0 >= '0' && LA1_0 <= '9')||(LA1_0 >= 'A' && LA1_0 <= 'Z')||LA1_0=='_'||(LA1_0 >= 'a' && LA1_0 <= 'z')) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:
            	    {
            	    if ( (input.LA(1) >= '0' && input.LA(1) <= '9')||(input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
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
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:517:5: ( '\"' (~ ( '\"' ) )* '\"' )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:517:8: '\"' (~ ( '\"' ) )* '\"'
            {
            match('\"'); 

            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:517:12: (~ ( '\"' ) )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( ((LA2_0 >= '\u0000' && LA2_0 <= '!')||(LA2_0 >= '#' && LA2_0 <= '\uFFFF')) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:
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
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:570:5: ( ',' )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:570:7: ','
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
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:574:5: ( '\"' )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:574:7: '\"'
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
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:578:5: ( ';' )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:578:7: ';'
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
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:582:5: ( ( ' ' | '\\t' | '\\n' | '\\r' ) )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:582:7: ( ' ' | '\\t' | '\\n' | '\\r' )
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
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:587:5: ( '<*' ( . )* '*>' )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:587:7: '<*' ( . )* '*>'
            {
            match("<*"); 



            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:587:12: ( . )*
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
            	    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:587:12: .
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
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:591:5: ( '/*' ( options {greedy=false; } : . )* '*/' )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:591:7: '/*' ( options {greedy=false; } : . )* '*/'
            {
            match("/*"); 



            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:591:12: ( options {greedy=false; } : . )*
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
            	    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:591:40: .
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
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:595:5: ( '//' (~ ( '\\n' | '\\r' ) )* ( '\\r' )? '\\n' )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:595:7: '//' (~ ( '\\n' | '\\r' ) )* ( '\\r' )? '\\n'
            {
            match("//"); 



            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:595:12: (~ ( '\\n' | '\\r' ) )*
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( ((LA5_0 >= '\u0000' && LA5_0 <= '\t')||(LA5_0 >= '\u000B' && LA5_0 <= '\f')||(LA5_0 >= '\u000E' && LA5_0 <= '\uFFFF')) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:
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


            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:595:26: ( '\\r' )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0=='\r') ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:595:26: '\\r'
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
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:602:2: ( 'A' .. 'Z' | 'a' .. 'z' | '_' )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:
            {
            if ( (input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
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
        // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:8: ( C | CPP | IAS | JAVA | NIL | OBJECTIVEC | OPBEGIN | OPCARTESIAN | OPCREATEEXPR | OPEQ | OPEXECUTE | OPFIRST | OPIDENT | OPIN | OPINCL | OPINTERPRET | OPINTERSECT | OPJOIN | OPLAST | OPPROJECTION_1 | OPPROJECTION_2 | OPPROJECTION_3 | OPPROJECTION_4 | OPPROJECTION_5 | OPPROJECTION_6 | OPPROJECTION_7 | OPPROJECTION_8 | OPPROJECTION_9 | OPRANDOM | OPREST | OPSQU | OPSUBSTRUCT | T__46 | T__47 | T__48 | T__49 | T__50 | T__51 | T__52 | T__53 | T__54 | T__55 | T__56 | T__57 | T__58 | T__59 | T__60 | T__61 | T__62 | T__63 | T__64 | ID | STRING_LITERAL | COMMA | DQUOTE | SEMICOLON | WS | NATIVE_CODE | COMMENT | LINE_COMMENT )
        int alt7=60;
        alt7 = dfa7.predict(input);
        switch (alt7) {
            case 1 :
                // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:10: C
                {
                mC(); 


                }
                break;
            case 2 :
                // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:12: CPP
                {
                mCPP(); 


                }
                break;
            case 3 :
                // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:16: IAS
                {
                mIAS(); 


                }
                break;
            case 4 :
                // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:20: JAVA
                {
                mJAVA(); 


                }
                break;
            case 5 :
                // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:25: NIL
                {
                mNIL(); 


                }
                break;
            case 6 :
                // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:29: OBJECTIVEC
                {
                mOBJECTIVEC(); 


                }
                break;
            case 7 :
                // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:40: OPBEGIN
                {
                mOPBEGIN(); 


                }
                break;
            case 8 :
                // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:48: OPCARTESIAN
                {
                mOPCARTESIAN(); 


                }
                break;
            case 9 :
                // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:60: OPCREATEEXPR
                {
                mOPCREATEEXPR(); 


                }
                break;
            case 10 :
                // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:73: OPEQ
                {
                mOPEQ(); 


                }
                break;
            case 11 :
                // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:78: OPEXECUTE
                {
                mOPEXECUTE(); 


                }
                break;
            case 12 :
                // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:88: OPFIRST
                {
                mOPFIRST(); 


                }
                break;
            case 13 :
                // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:96: OPIDENT
                {
                mOPIDENT(); 


                }
                break;
            case 14 :
                // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:104: OPIN
                {
                mOPIN(); 


                }
                break;
            case 15 :
                // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:109: OPINCL
                {
                mOPINCL(); 


                }
                break;
            case 16 :
                // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:116: OPINTERPRET
                {
                mOPINTERPRET(); 


                }
                break;
            case 17 :
                // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:128: OPINTERSECT
                {
                mOPINTERSECT(); 


                }
                break;
            case 18 :
                // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:140: OPJOIN
                {
                mOPJOIN(); 


                }
                break;
            case 19 :
                // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:147: OPLAST
                {
                mOPLAST(); 


                }
                break;
            case 20 :
                // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:154: OPPROJECTION_1
                {
                mOPPROJECTION_1(); 


                }
                break;
            case 21 :
                // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:169: OPPROJECTION_2
                {
                mOPPROJECTION_2(); 


                }
                break;
            case 22 :
                // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:184: OPPROJECTION_3
                {
                mOPPROJECTION_3(); 


                }
                break;
            case 23 :
                // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:199: OPPROJECTION_4
                {
                mOPPROJECTION_4(); 


                }
                break;
            case 24 :
                // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:214: OPPROJECTION_5
                {
                mOPPROJECTION_5(); 


                }
                break;
            case 25 :
                // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:229: OPPROJECTION_6
                {
                mOPPROJECTION_6(); 


                }
                break;
            case 26 :
                // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:244: OPPROJECTION_7
                {
                mOPPROJECTION_7(); 


                }
                break;
            case 27 :
                // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:259: OPPROJECTION_8
                {
                mOPPROJECTION_8(); 


                }
                break;
            case 28 :
                // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:274: OPPROJECTION_9
                {
                mOPPROJECTION_9(); 


                }
                break;
            case 29 :
                // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:289: OPRANDOM
                {
                mOPRANDOM(); 


                }
                break;
            case 30 :
                // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:298: OPREST
                {
                mOPREST(); 


                }
                break;
            case 31 :
                // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:305: OPSQU
                {
                mOPSQU(); 


                }
                break;
            case 32 :
                // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:311: OPSUBSTRUCT
                {
                mOPSUBSTRUCT(); 


                }
                break;
            case 33 :
                // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:323: T__46
                {
                mT__46(); 


                }
                break;
            case 34 :
                // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:329: T__47
                {
                mT__47(); 


                }
                break;
            case 35 :
                // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:335: T__48
                {
                mT__48(); 


                }
                break;
            case 36 :
                // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:341: T__49
                {
                mT__49(); 


                }
                break;
            case 37 :
                // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:347: T__50
                {
                mT__50(); 


                }
                break;
            case 38 :
                // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:353: T__51
                {
                mT__51(); 


                }
                break;
            case 39 :
                // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:359: T__52
                {
                mT__52(); 


                }
                break;
            case 40 :
                // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:365: T__53
                {
                mT__53(); 


                }
                break;
            case 41 :
                // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:371: T__54
                {
                mT__54(); 


                }
                break;
            case 42 :
                // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:377: T__55
                {
                mT__55(); 


                }
                break;
            case 43 :
                // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:383: T__56
                {
                mT__56(); 


                }
                break;
            case 44 :
                // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:389: T__57
                {
                mT__57(); 


                }
                break;
            case 45 :
                // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:395: T__58
                {
                mT__58(); 


                }
                break;
            case 46 :
                // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:401: T__59
                {
                mT__59(); 


                }
                break;
            case 47 :
                // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:407: T__60
                {
                mT__60(); 


                }
                break;
            case 48 :
                // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:413: T__61
                {
                mT__61(); 


                }
                break;
            case 49 :
                // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:419: T__62
                {
                mT__62(); 


                }
                break;
            case 50 :
                // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:425: T__63
                {
                mT__63(); 


                }
                break;
            case 51 :
                // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:431: T__64
                {
                mT__64(); 


                }
                break;
            case 52 :
                // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:437: ID
                {
                mID(); 


                }
                break;
            case 53 :
                // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:440: STRING_LITERAL
                {
                mSTRING_LITERAL(); 


                }
                break;
            case 54 :
                // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:455: COMMA
                {
                mCOMMA(); 


                }
                break;
            case 55 :
                // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:461: DQUOTE
                {
                mDQUOTE(); 


                }
                break;
            case 56 :
                // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:468: SEMICOLON
                {
                mSEMICOLON(); 


                }
                break;
            case 57 :
                // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:478: WS
                {
                mWS(); 


                }
                break;
            case 58 :
                // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:481: NATIVE_CODE
                {
                mNATIVE_CODE(); 


                }
                break;
            case 59 :
                // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:493: COMMENT
                {
                mCOMMENT(); 


                }
                break;
            case 60 :
                // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:501: LINE_COMMENT
                {
                mLINE_COMMENT(); 


                }
                break;

        }

    }


    protected DFA7 dfa7 = new DFA7(this);
    static final String DFA7_eotS =
        "\1\uffff\5\37\1\uffff\3\37\1\uffff\5\37\5\uffff\10\37\3\uffff\1"+
        "\103\5\uffff\6\37\1\116\3\37\1\124\21\37\4\uffff\3\37\1\153\1\37"+
        "\1\155\2\37\1\uffff\1\160\4\37\1\uffff\26\37\1\uffff\1\37\1\uffff"+
        "\2\37\1\uffff\4\37\1\u0092\1\u0093\2\37\1\u0096\4\37\1\u009b\5\37"+
        "\1\u00a1\2\37\1\u00a4\4\37\1\u00a9\1\37\1\u00ab\1\u00ac\2\37\2\uffff"+
        "\2\37\1\uffff\3\37\1\u00b4\1\uffff\5\37\1\uffff\2\37\1\uffff\4\37"+
        "\1\uffff\1\37\2\uffff\3\37\1\u00c4\2\37\1\u00c7\1\uffff\2\37\1\u00ca"+
        "\4\37\1\u00cf\2\37\1\u00d2\1\37\1\u00d4\2\37\1\uffff\1\u00d7\1\37"+
        "\1\uffff\2\37\1\uffff\1\u00db\1\u00dc\2\37\1\uffff\1\u00df\1\37"+
        "\1\uffff\1\37\1\uffff\2\37\1\uffff\2\37\1\u00e6\2\uffff\2\37\1\uffff"+
        "\1\37\1\u00ea\1\u00eb\1\37\1\u00ed\1\37\1\uffff\3\37\2\uffff\1\37"+
        "\1\uffff\2\37\1\u00f5\2\37\1\u0100\1\37\1\uffff\1\37\1\u0103\1\u0104"+
        "\1\u0105\1\u0106\1\u0107\1\u0108\1\u0109\1\u010a\1\u010b\1\uffff"+
        "\1\u010c\1\37\12\uffff\1\37\1\u010f\1\uffff";
    static final String DFA7_eofS =
        "\u0110\uffff";
    static final String DFA7_minS =
        "\1\11\1\137\1\141\1\151\1\145\1\141\1\uffff\1\161\1\151\1\144\1"+
        "\uffff\1\157\1\141\1\162\1\141\1\161\5\uffff\1\165\1\154\2\141\1"+
        "\157\1\160\1\141\1\151\3\uffff\1\0\4\uffff\1\52\1\143\1\163\1\143"+
        "\1\154\1\147\1\162\1\60\1\145\1\162\1\145\1\60\1\151\1\163\1\157"+
        "\1\156\1\163\1\165\1\142\1\164\1\141\1\164\1\163\1\156\1\144\1\164"+
        "\1\143\1\157\1\163\4\uffff\1\137\1\141\1\142\1\60\1\154\1\60\1\151"+
        "\1\164\1\uffff\1\60\1\163\1\156\1\154\1\145\1\uffff\1\156\1\164"+
        "\1\152\1\144\1\164\1\145\1\163\1\150\1\163\1\141\1\143\1\147\1\165"+
        "\1\151\1\153\1\150\1\152\1\165\1\137\1\160\1\166\1\152\1\uffff\1"+
        "\165\1\uffff\1\156\1\145\1\uffff\2\164\1\165\1\162\2\60\1\145\1"+
        "\157\1\60\1\145\1\164\1\157\1\163\1\60\1\162\1\165\1\154\1\157\1"+
        "\141\1\60\1\145\1\141\1\60\1\137\1\141\1\145\1\144\1\60\1\163\2"+
        "\60\1\144\1\163\2\uffff\1\143\1\155\1\uffff\1\172\2\162\1\60\1\uffff"+
        "\1\151\1\141\1\145\1\156\1\147\1\uffff\1\143\1\154\1\uffff\2\137"+
        "\1\143\1\145\1\uffff\1\151\2\uffff\2\145\1\164\1\60\1\145\1\165"+
        "\1\60\1\uffff\1\160\1\147\1\60\1\163\1\145\1\164\1\151\1\60\1\137"+
        "\1\164\1\60\1\141\1\60\1\143\1\151\1\uffff\1\60\1\143\1\uffff\1"+
        "\164\1\145\1\uffff\2\60\1\137\1\172\1\uffff\1\60\1\151\1\uffff\1"+
        "\156\1\uffff\1\164\1\157\1\uffff\1\164\1\151\1\60\2\uffff\1\156"+
        "\1\145\1\uffff\1\166\2\60\1\156\1\60\1\157\1\uffff\1\141\1\162\1"+
        "\145\2\uffff\1\137\1\uffff\1\156\1\155\1\60\1\137\1\61\1\60\1\145"+
        "\1\uffff\1\143\11\60\1\uffff\1\60\1\137\12\uffff\1\137\1\60\1\uffff";
    static final String DFA7_maxS =
        "\1\176\1\137\1\156\1\151\1\145\1\141\1\uffff\1\170\1\151\1\156\1"+
        "\uffff\1\157\1\141\1\162\1\145\1\165\5\uffff\1\165\1\154\1\145\1"+
        "\141\1\157\1\160\1\162\1\151\3\uffff\1\uffff\4\uffff\1\57\1\157"+
        "\1\163\1\143\1\154\1\147\1\162\1\172\1\145\1\162\1\145\1\172\1\151"+
        "\1\163\1\157\1\156\1\163\1\165\1\142\1\164\1\141\1\164\1\163\1\156"+
        "\1\144\2\164\1\157\1\163\4\uffff\1\160\1\141\1\142\1\172\1\154\1"+
        "\172\1\151\1\164\1\uffff\1\172\1\163\1\156\1\154\1\145\1\uffff\1"+
        "\156\1\164\1\152\1\144\1\164\1\145\1\163\1\150\1\163\1\141\1\143"+
        "\1\147\1\165\1\151\1\153\1\150\1\152\1\165\1\137\1\160\1\166\1\152"+
        "\1\uffff\1\165\1\uffff\1\156\1\145\1\uffff\2\164\1\165\1\162\2\172"+
        "\1\145\1\157\1\172\1\145\1\164\1\157\1\163\1\172\1\162\1\165\1\154"+
        "\1\157\1\141\1\172\1\145\1\141\1\172\1\137\1\141\1\145\1\144\1\172"+
        "\1\163\2\172\1\144\1\163\2\uffff\1\143\1\155\1\uffff\1\172\2\162"+
        "\1\172\1\uffff\1\151\1\141\1\145\1\156\1\147\1\uffff\1\143\1\154"+
        "\1\uffff\2\137\1\143\1\145\1\uffff\1\151\2\uffff\2\145\1\164\1\172"+
        "\1\145\1\165\1\172\1\uffff\1\160\1\147\1\172\1\163\1\145\1\164\1"+
        "\151\1\172\1\137\1\164\1\172\1\141\1\172\1\143\1\151\1\uffff\1\172"+
        "\1\143\1\uffff\1\164\1\145\1\uffff\2\172\1\137\1\172\1\uffff\1\172"+
        "\1\151\1\uffff\1\156\1\uffff\1\164\1\157\1\uffff\1\164\1\151\1\172"+
        "\2\uffff\1\156\1\145\1\uffff\1\166\2\172\1\156\1\172\1\157\1\uffff"+
        "\1\141\1\162\1\145\2\uffff\1\137\1\uffff\1\156\1\155\1\172\1\137"+
        "\1\71\1\172\1\145\1\uffff\1\143\11\172\1\uffff\1\172\1\137\12\uffff"+
        "\1\137\1\172\1\uffff";
    static final String DFA7_acceptS =
        "\6\uffff\1\11\3\uffff\1\20\5\uffff\1\41\1\42\1\43\1\44\1\45\10\uffff"+
        "\1\62\1\63\1\64\1\uffff\1\66\1\70\1\71\1\72\35\uffff\1\65\1\67\1"+
        "\73\1\74\10\uffff\1\12\5\uffff\1\16\26\uffff\1\3\1\uffff\1\5\2\uffff"+
        "\1\13\41\uffff\1\22\1\23\2\uffff\1\36\4\uffff\1\50\5\uffff\1\57"+
        "\2\uffff\1\1\4\uffff\1\7\1\uffff\1\14\1\15\7\uffff\1\47\17\uffff"+
        "\1\35\2\uffff\1\46\2\uffff\1\54\4\uffff\1\2\2\uffff\1\52\1\uffff"+
        "\1\17\2\uffff\1\37\3\uffff\1\55\1\56\2\uffff\1\4\6\uffff\1\53\3"+
        "\uffff\1\10\1\21\1\uffff\1\40\7\uffff\1\61\12\uffff\1\51\2\uffff"+
        "\1\24\1\25\1\26\1\27\1\30\1\31\1\32\1\33\1\34\1\60\2\uffff\1\6";
    static final String DFA7_specialS =
        "\40\uffff\1\0\u00ef\uffff}>";
    static final String[] DFA7_transitionS = {
            "\2\43\2\uffff\1\43\22\uffff\1\43\1\uffff\1\40\5\uffff\1\20\1"+
            "\21\2\uffff\1\41\2\uffff\1\45\13\uffff\1\42\1\44\1\22\3\uffff"+
            "\1\37\1\4\1\5\1\37\1\7\1\10\2\37\1\11\1\13\1\37\1\14\3\37\1"+
            "\15\1\37\1\16\1\17\7\37\1\23\1\uffff\1\24\1\6\1\1\1\uffff\1"+
            "\25\1\37\1\26\1\27\4\37\1\2\2\37\1\30\1\31\1\3\1\32\1\33\5\37"+
            "\1\34\4\37\1\35\1\uffff\1\36\1\12",
            "\1\46",
            "\1\47\14\uffff\1\50",
            "\1\51",
            "\1\52",
            "\1\53",
            "",
            "\1\54\6\uffff\1\55",
            "\1\56",
            "\1\57\11\uffff\1\60",
            "",
            "\1\61",
            "\1\62",
            "\1\63",
            "\1\64\3\uffff\1\65",
            "\1\66\3\uffff\1\67",
            "",
            "",
            "",
            "",
            "",
            "\1\70",
            "\1\71",
            "\1\72\3\uffff\1\73",
            "\1\74",
            "\1\75",
            "\1\76",
            "\1\77\20\uffff\1\100",
            "\1\101",
            "",
            "",
            "",
            "\0\102",
            "",
            "",
            "",
            "",
            "\1\104\4\uffff\1\105",
            "\1\106\6\uffff\1\107\4\uffff\1\110",
            "\1\111",
            "\1\112",
            "\1\113",
            "\1\114",
            "\1\115",
            "\12\37\7\uffff\32\37\4\uffff\1\37\1\uffff\32\37",
            "\1\117",
            "\1\120",
            "\1\121",
            "\12\37\7\uffff\32\37\4\uffff\1\37\1\uffff\2\37\1\122\20\37"+
            "\1\123\6\37",
            "\1\125",
            "\1\126",
            "\1\127",
            "\1\130",
            "\1\131",
            "\1\132",
            "\1\133",
            "\1\134",
            "\1\135",
            "\1\136",
            "\1\137",
            "\1\140",
            "\1\141",
            "\1\142",
            "\1\143\20\uffff\1\144",
            "\1\145",
            "\1\146",
            "",
            "",
            "",
            "",
            "\1\147\20\uffff\1\150",
            "\1\151",
            "\1\152",
            "\12\37\7\uffff\32\37\4\uffff\1\37\1\uffff\32\37",
            "\1\154",
            "\12\37\7\uffff\32\37\4\uffff\1\37\1\uffff\32\37",
            "\1\156",
            "\1\157",
            "",
            "\12\37\7\uffff\32\37\4\uffff\1\37\1\uffff\32\37",
            "\1\161",
            "\1\162",
            "\1\163",
            "\1\164",
            "",
            "\1\165",
            "\1\166",
            "\1\167",
            "\1\170",
            "\1\171",
            "\1\172",
            "\1\173",
            "\1\174",
            "\1\175",
            "\1\176",
            "\1\177",
            "\1\u0080",
            "\1\u0081",
            "\1\u0082",
            "\1\u0083",
            "\1\u0084",
            "\1\u0085",
            "\1\u0086",
            "\1\u0087",
            "\1\u0088",
            "\1\u0089",
            "\1\u008a",
            "",
            "\1\u008b",
            "",
            "\1\u008c",
            "\1\u008d",
            "",
            "\1\u008e",
            "\1\u008f",
            "\1\u0090",
            "\1\u0091",
            "\12\37\7\uffff\32\37\4\uffff\1\37\1\uffff\32\37",
            "\12\37\7\uffff\32\37\4\uffff\1\37\1\uffff\32\37",
            "\1\u0094",
            "\1\u0095",
            "\12\37\7\uffff\32\37\4\uffff\1\37\1\uffff\32\37",
            "\1\u0097",
            "\1\u0098",
            "\1\u0099",
            "\1\u009a",
            "\12\37\7\uffff\32\37\4\uffff\1\37\1\uffff\32\37",
            "\1\u009c",
            "\1\u009d",
            "\1\u009e",
            "\1\u009f",
            "\1\u00a0",
            "\12\37\7\uffff\32\37\4\uffff\1\37\1\uffff\32\37",
            "\1\u00a2",
            "\1\u00a3",
            "\12\37\7\uffff\32\37\4\uffff\1\37\1\uffff\32\37",
            "\1\u00a5",
            "\1\u00a6",
            "\1\u00a7",
            "\1\u00a8",
            "\12\37\7\uffff\32\37\4\uffff\1\37\1\uffff\32\37",
            "\1\u00aa",
            "\12\37\7\uffff\32\37\4\uffff\1\37\1\uffff\32\37",
            "\12\37\7\uffff\32\37\4\uffff\1\37\1\uffff\32\37",
            "\1\u00ad",
            "\1\u00ae",
            "",
            "",
            "\1\u00af",
            "\1\u00b0",
            "",
            "\1\u00b1",
            "\1\u00b2",
            "\1\u00b3",
            "\12\37\7\uffff\32\37\4\uffff\1\37\1\uffff\32\37",
            "",
            "\1\u00b5",
            "\1\u00b6",
            "\1\u00b7",
            "\1\u00b8",
            "\1\u00b9",
            "",
            "\1\u00ba",
            "\1\u00bb",
            "",
            "\1\u00bc",
            "\1\u00bd",
            "\1\u00be",
            "\1\u00bf",
            "",
            "\1\u00c0",
            "",
            "",
            "\1\u00c1",
            "\1\u00c2",
            "\1\u00c3",
            "\12\37\7\uffff\32\37\4\uffff\1\37\1\uffff\32\37",
            "\1\u00c5",
            "\1\u00c6",
            "\12\37\7\uffff\32\37\4\uffff\1\37\1\uffff\32\37",
            "",
            "\1\u00c8",
            "\1\u00c9",
            "\12\37\7\uffff\32\37\4\uffff\1\37\1\uffff\32\37",
            "\1\u00cb",
            "\1\u00cc",
            "\1\u00cd",
            "\1\u00ce",
            "\12\37\7\uffff\32\37\4\uffff\1\37\1\uffff\32\37",
            "\1\u00d0",
            "\1\u00d1",
            "\12\37\7\uffff\32\37\4\uffff\1\37\1\uffff\32\37",
            "\1\u00d3",
            "\12\37\7\uffff\32\37\4\uffff\1\37\1\uffff\32\37",
            "\1\u00d5",
            "\1\u00d6",
            "",
            "\12\37\7\uffff\32\37\4\uffff\1\37\1\uffff\32\37",
            "\1\u00d8",
            "",
            "\1\u00d9",
            "\1\u00da",
            "",
            "\12\37\7\uffff\32\37\4\uffff\1\37\1\uffff\32\37",
            "\12\37\7\uffff\32\37\4\uffff\1\37\1\uffff\32\37",
            "\1\u00dd",
            "\1\u00de",
            "",
            "\12\37\7\uffff\32\37\4\uffff\1\37\1\uffff\32\37",
            "\1\u00e0",
            "",
            "\1\u00e1",
            "",
            "\1\u00e2",
            "\1\u00e3",
            "",
            "\1\u00e4",
            "\1\u00e5",
            "\12\37\7\uffff\32\37\4\uffff\1\37\1\uffff\32\37",
            "",
            "",
            "\1\u00e7",
            "\1\u00e8",
            "",
            "\1\u00e9",
            "\12\37\7\uffff\32\37\4\uffff\1\37\1\uffff\32\37",
            "\12\37\7\uffff\32\37\4\uffff\1\37\1\uffff\32\37",
            "\1\u00ec",
            "\12\37\7\uffff\32\37\4\uffff\1\37\1\uffff\32\37",
            "\1\u00ee",
            "",
            "\1\u00ef",
            "\1\u00f0",
            "\1\u00f1",
            "",
            "",
            "\1\u00f2",
            "",
            "\1\u00f3",
            "\1\u00f4",
            "\12\37\7\uffff\32\37\4\uffff\1\37\1\uffff\32\37",
            "\1\u00f6",
            "\1\u00f7\1\u00f8\1\u00f9\1\u00fa\1\u00fb\1\u00fc\1\u00fd\1"+
            "\u00fe\1\u00ff",
            "\12\37\7\uffff\32\37\4\uffff\1\37\1\uffff\32\37",
            "\1\u0101",
            "",
            "\1\u0102",
            "\12\37\7\uffff\32\37\4\uffff\1\37\1\uffff\32\37",
            "\12\37\7\uffff\32\37\4\uffff\1\37\1\uffff\32\37",
            "\12\37\7\uffff\32\37\4\uffff\1\37\1\uffff\32\37",
            "\12\37\7\uffff\32\37\4\uffff\1\37\1\uffff\32\37",
            "\12\37\7\uffff\32\37\4\uffff\1\37\1\uffff\32\37",
            "\12\37\7\uffff\32\37\4\uffff\1\37\1\uffff\32\37",
            "\12\37\7\uffff\32\37\4\uffff\1\37\1\uffff\32\37",
            "\12\37\7\uffff\32\37\4\uffff\1\37\1\uffff\32\37",
            "\12\37\7\uffff\32\37\4\uffff\1\37\1\uffff\32\37",
            "",
            "\12\37\7\uffff\32\37\4\uffff\1\37\1\uffff\32\37",
            "\1\u010d",
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
            "\1\u010e",
            "\12\37\7\uffff\32\37\4\uffff\1\37\1\uffff\32\37",
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
            return "1:1: Tokens : ( C | CPP | IAS | JAVA | NIL | OBJECTIVEC | OPBEGIN | OPCARTESIAN | OPCREATEEXPR | OPEQ | OPEXECUTE | OPFIRST | OPIDENT | OPIN | OPINCL | OPINTERPRET | OPINTERSECT | OPJOIN | OPLAST | OPPROJECTION_1 | OPPROJECTION_2 | OPPROJECTION_3 | OPPROJECTION_4 | OPPROJECTION_5 | OPPROJECTION_6 | OPPROJECTION_7 | OPPROJECTION_8 | OPPROJECTION_9 | OPRANDOM | OPREST | OPSQU | OPSUBSTRUCT | T__46 | T__47 | T__48 | T__49 | T__50 | T__51 | T__52 | T__53 | T__54 | T__55 | T__56 | T__57 | T__58 | T__59 | T__60 | T__61 | T__62 | T__63 | T__64 | ID | STRING_LITERAL | COMMA | DQUOTE | SEMICOLON | WS | NATIVE_CODE | COMMENT | LINE_COMMENT );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            IntStream input = _input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA7_32 = input.LA(1);

                        s = -1;
                        if ( ((LA7_32 >= '\u0000' && LA7_32 <= '\uFFFF')) ) {s = 66;}

                        else s = 67;

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