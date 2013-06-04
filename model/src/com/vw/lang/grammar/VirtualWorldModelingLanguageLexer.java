// $ANTLR 3.4 C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g 2013-06-03 05:50:45
 
package com.vw.lang.grammar;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked"})
public class VirtualWorldModelingLanguageLexer extends Lexer {
    public static final int EOF=-1;
    public static final int T__37=37;
    public static final int T__38=38;
    public static final int T__39=39;
    public static final int T__40=40;
    public static final int T__41=41;
    public static final int COMMA=4;
    public static final int DQUOTE=5;
    public static final int IAS=6;
    public static final int ID=7;
    public static final int NIL=8;
    public static final int OPBEGIN=9;
    public static final int OPCARTESIAN=10;
    public static final int OPCREATEEXPR=11;
    public static final int OPEQ=12;
    public static final int OPEXECUTE=13;
    public static final int OPFIRST=14;
    public static final int OPIDENT=15;
    public static final int OPIN=16;
    public static final int OPINCL=17;
    public static final int OPINTERPRET=18;
    public static final int OPINTERSECT=19;
    public static final int OPJOIN=20;
    public static final int OPLAST=21;
    public static final int OPPROJECTION_1=22;
    public static final int OPPROJECTION_2=23;
    public static final int OPPROJECTION_3=24;
    public static final int OPPROJECTION_4=25;
    public static final int OPPROJECTION_5=26;
    public static final int OPPROJECTION_6=27;
    public static final int OPPROJECTION_7=28;
    public static final int OPPROJECTION_8=29;
    public static final int OPPROJECTION_9=30;
    public static final int OPRANDOM=31;
    public static final int OPREST=32;
    public static final int OPSQU=33;
    public static final int OPSUBSTRUCT=34;
    public static final int SEMICOLON=35;
    public static final int WS=36;

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

    // $ANTLR start "IAS"
    public final void mIAS() throws RecognitionException {
        try {
            int _type = IAS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:6:5: ( 'ias' )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:6:7: 'ias'
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

    // $ANTLR start "NIL"
    public final void mNIL() throws RecognitionException {
        try {
            int _type = NIL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:7:5: ( 'nil' )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:7:7: 'nil'
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

    // $ANTLR start "OPBEGIN"
    public final void mOPBEGIN() throws RecognitionException {
        try {
            int _type = OPBEGIN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:8:9: ( 'Begin' )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:8:11: 'Begin'
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
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:9:13: ( 'Cartesian' )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:9:15: 'Cartesian'
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
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:10:14: ( '^' )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:10:16: '^'
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
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:11:6: ( 'Eq' )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:11:8: 'Eq'
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
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:12:11: ( 'Exe' )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:12:13: 'Exe'
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
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:13:9: ( 'First' )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:13:11: 'First'
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
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:14:9: ( 'Ident' )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:14:11: 'Ident'
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
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:15:6: ( 'In' )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:15:8: 'In'
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
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:16:8: ( 'Include' )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:16:10: 'Include'
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
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:17:13: ( '~' )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:17:15: '~'
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
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:18:13: ( 'Intersect' )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:18:15: 'Intersect'
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
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:19:8: ( 'Join' )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:19:10: 'Join'
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
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:20:8: ( 'Last' )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:20:10: 'Last'
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
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:21:16: ( 'Projection_1' )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:21:18: 'Projection_1'
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
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:22:16: ( 'Projection_2' )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:22:18: 'Projection_2'
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
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:23:16: ( 'Projection_3' )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:23:18: 'Projection_3'
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
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:24:16: ( 'Projection_4' )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:24:18: 'Projection_4'
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
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:25:16: ( 'Projection_5' )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:25:18: 'Projection_5'
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
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:26:16: ( 'Projection_6' )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:26:18: 'Projection_6'
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
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:27:16: ( 'Projection_7' )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:27:18: 'Projection_7'
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
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:28:16: ( 'Projection_8' )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:28:18: 'Projection_8'
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
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:29:16: ( 'Projection_9' )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:29:18: 'Projection_9'
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
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:30:10: ( 'Random' )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:30:12: 'Random'
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
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:31:8: ( 'Rest' )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:31:10: 'Rest'
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
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:32:7: ( 'Squeeze' )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:32:9: 'Squeeze'
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
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:33:13: ( 'Substruct' )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:33:15: 'Substruct'
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

    // $ANTLR start "T__37"
    public final void mT__37() throws RecognitionException {
        try {
            int _type = T__37;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:34:7: ( '(' )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:34:9: '('
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
    // $ANTLR end "T__37"

    // $ANTLR start "T__38"
    public final void mT__38() throws RecognitionException {
        try {
            int _type = T__38;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:35:7: ( ')' )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:35:9: ')'
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
    // $ANTLR end "T__38"

    // $ANTLR start "T__39"
    public final void mT__39() throws RecognitionException {
        try {
            int _type = T__39;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:36:7: ( 'module' )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:36:9: 'module'
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
    // $ANTLR end "T__39"

    // $ANTLR start "T__40"
    public final void mT__40() throws RecognitionException {
        try {
            int _type = T__40;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:37:7: ( '{' )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:37:9: '{'
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
    // $ANTLR end "T__40"

    // $ANTLR start "T__41"
    public final void mT__41() throws RecognitionException {
        try {
            int _type = T__41;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:38:7: ( '}' )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:38:9: '}'
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
    // $ANTLR end "T__41"

    // $ANTLR start "ID"
    public final void mID() throws RecognitionException {
        try {
            int _type = ID;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:127:5: ( ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '.' | '_' | '*' | '-' )+ )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:127:7: ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '.' | '_' | '*' | '-' )+
            {
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:127:7: ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '.' | '_' | '*' | '-' )+
            int cnt1=0;
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0=='*'||(LA1_0 >= '-' && LA1_0 <= '.')||(LA1_0 >= '0' && LA1_0 <= '9')||(LA1_0 >= 'A' && LA1_0 <= 'Z')||LA1_0=='_'||(LA1_0 >= 'a' && LA1_0 <= 'z')) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:
            	    {
            	    if ( input.LA(1)=='*'||(input.LA(1) >= '-' && input.LA(1) <= '.')||(input.LA(1) >= '0' && input.LA(1) <= '9')||(input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
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
            	    if ( cnt1 >= 1 ) break loop1;
                        EarlyExitException eee =
                            new EarlyExitException(1, input);
                        throw eee;
                }
                cnt1++;
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

    // $ANTLR start "COMMA"
    public final void mCOMMA() throws RecognitionException {
        try {
            int _type = COMMA;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:169:5: ( ',' )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:169:7: ','
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
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:173:5: ( '\"' )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:173:7: '\"'
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
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:177:5: ( ';' )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:177:7: ';'
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
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:181:5: ( ( ' ' | '\\t' | '\\n' | '\\r' ) )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:181:7: ( ' ' | '\\t' | '\\n' | '\\r' )
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

    public void mTokens() throws RecognitionException {
        // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:8: ( IAS | NIL | OPBEGIN | OPCARTESIAN | OPCREATEEXPR | OPEQ | OPEXECUTE | OPFIRST | OPIDENT | OPIN | OPINCL | OPINTERPRET | OPINTERSECT | OPJOIN | OPLAST | OPPROJECTION_1 | OPPROJECTION_2 | OPPROJECTION_3 | OPPROJECTION_4 | OPPROJECTION_5 | OPPROJECTION_6 | OPPROJECTION_7 | OPPROJECTION_8 | OPPROJECTION_9 | OPRANDOM | OPREST | OPSQU | OPSUBSTRUCT | T__37 | T__38 | T__39 | T__40 | T__41 | ID | COMMA | DQUOTE | SEMICOLON | WS )
        int alt2=38;
        alt2 = dfa2.predict(input);
        switch (alt2) {
            case 1 :
                // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:10: IAS
                {
                mIAS(); 


                }
                break;
            case 2 :
                // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:14: NIL
                {
                mNIL(); 


                }
                break;
            case 3 :
                // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:18: OPBEGIN
                {
                mOPBEGIN(); 


                }
                break;
            case 4 :
                // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:26: OPCARTESIAN
                {
                mOPCARTESIAN(); 


                }
                break;
            case 5 :
                // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:38: OPCREATEEXPR
                {
                mOPCREATEEXPR(); 


                }
                break;
            case 6 :
                // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:51: OPEQ
                {
                mOPEQ(); 


                }
                break;
            case 7 :
                // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:56: OPEXECUTE
                {
                mOPEXECUTE(); 


                }
                break;
            case 8 :
                // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:66: OPFIRST
                {
                mOPFIRST(); 


                }
                break;
            case 9 :
                // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:74: OPIDENT
                {
                mOPIDENT(); 


                }
                break;
            case 10 :
                // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:82: OPIN
                {
                mOPIN(); 


                }
                break;
            case 11 :
                // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:87: OPINCL
                {
                mOPINCL(); 


                }
                break;
            case 12 :
                // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:94: OPINTERPRET
                {
                mOPINTERPRET(); 


                }
                break;
            case 13 :
                // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:106: OPINTERSECT
                {
                mOPINTERSECT(); 


                }
                break;
            case 14 :
                // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:118: OPJOIN
                {
                mOPJOIN(); 


                }
                break;
            case 15 :
                // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:125: OPLAST
                {
                mOPLAST(); 


                }
                break;
            case 16 :
                // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:132: OPPROJECTION_1
                {
                mOPPROJECTION_1(); 


                }
                break;
            case 17 :
                // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:147: OPPROJECTION_2
                {
                mOPPROJECTION_2(); 


                }
                break;
            case 18 :
                // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:162: OPPROJECTION_3
                {
                mOPPROJECTION_3(); 


                }
                break;
            case 19 :
                // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:177: OPPROJECTION_4
                {
                mOPPROJECTION_4(); 


                }
                break;
            case 20 :
                // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:192: OPPROJECTION_5
                {
                mOPPROJECTION_5(); 


                }
                break;
            case 21 :
                // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:207: OPPROJECTION_6
                {
                mOPPROJECTION_6(); 


                }
                break;
            case 22 :
                // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:222: OPPROJECTION_7
                {
                mOPPROJECTION_7(); 


                }
                break;
            case 23 :
                // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:237: OPPROJECTION_8
                {
                mOPPROJECTION_8(); 


                }
                break;
            case 24 :
                // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:252: OPPROJECTION_9
                {
                mOPPROJECTION_9(); 


                }
                break;
            case 25 :
                // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:267: OPRANDOM
                {
                mOPRANDOM(); 


                }
                break;
            case 26 :
                // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:276: OPREST
                {
                mOPREST(); 


                }
                break;
            case 27 :
                // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:283: OPSQU
                {
                mOPSQU(); 


                }
                break;
            case 28 :
                // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:289: OPSUBSTRUCT
                {
                mOPSUBSTRUCT(); 


                }
                break;
            case 29 :
                // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:301: T__37
                {
                mT__37(); 


                }
                break;
            case 30 :
                // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:307: T__38
                {
                mT__38(); 


                }
                break;
            case 31 :
                // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:313: T__39
                {
                mT__39(); 


                }
                break;
            case 32 :
                // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:319: T__40
                {
                mT__40(); 


                }
                break;
            case 33 :
                // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:325: T__41
                {
                mT__41(); 


                }
                break;
            case 34 :
                // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:331: ID
                {
                mID(); 


                }
                break;
            case 35 :
                // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:334: COMMA
                {
                mCOMMA(); 


                }
                break;
            case 36 :
                // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:340: DQUOTE
                {
                mDQUOTE(); 


                }
                break;
            case 37 :
                // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:347: SEMICOLON
                {
                mSEMICOLON(); 


                }
                break;
            case 38 :
                // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1:357: WS
                {
                mWS(); 


                }
                break;

        }

    }


    protected DFA2 dfa2 = new DFA2(this);
    static final String DFA2_eotS =
        "\1\uffff\4\24\1\uffff\3\24\1\uffff\5\24\2\uffff\1\24\7\uffff\4\24"+
        "\1\56\3\24\1\64\10\24\1\75\1\76\2\24\1\uffff\1\101\4\24\1\uffff"+
        "\10\24\2\uffff\2\24\1\uffff\4\24\1\124\1\125\2\24\1\130\3\24\1\134"+
        "\1\24\1\136\1\137\2\24\2\uffff\2\24\1\uffff\3\24\1\uffff\1\24\2"+
        "\uffff\3\24\1\153\2\24\1\156\1\24\1\160\2\24\1\uffff\1\163\1\24"+
        "\1\uffff\1\24\1\uffff\2\24\1\uffff\1\24\1\171\1\172\1\24\1\174\2"+
        "\uffff\1\24\1\uffff\1\24\1\u0087\1\u0088\1\u0089\1\u008a\1\u008b"+
        "\1\u008c\1\u008d\1\u008e\1\u008f\11\uffff";
    static final String DFA2_eofS =
        "\u0090\uffff";
    static final String DFA2_minS =
        "\1\11\1\141\1\151\1\145\1\141\1\uffff\1\161\1\151\1\144\1\uffff"+
        "\1\157\1\141\1\162\1\141\1\161\2\uffff\1\157\7\uffff\1\163\1\154"+
        "\1\147\1\162\1\52\1\145\1\162\1\145\1\52\1\151\1\163\1\157\1\156"+
        "\1\163\1\165\1\142\1\144\2\52\1\151\1\164\1\uffff\1\52\1\163\1\156"+
        "\1\154\1\145\1\uffff\1\156\1\164\1\152\1\144\1\164\1\145\1\163\1"+
        "\165\2\uffff\1\156\1\145\1\uffff\2\164\1\165\1\162\2\52\1\145\1"+
        "\157\1\52\1\145\1\164\1\154\1\52\1\163\2\52\1\144\1\163\2\uffff"+
        "\1\143\1\155\1\uffff\1\172\1\162\1\145\1\uffff\1\151\2\uffff\2\145"+
        "\1\164\1\52\1\145\1\165\1\52\1\141\1\52\1\143\1\151\1\uffff\1\52"+
        "\1\143\1\uffff\1\156\1\uffff\1\164\1\157\1\uffff\1\164\2\52\1\156"+
        "\1\52\2\uffff\1\137\1\uffff\1\61\11\52\11\uffff";
    static final String DFA2_maxS =
        "\1\176\1\141\1\151\1\145\1\141\1\uffff\1\170\1\151\1\156\1\uffff"+
        "\1\157\1\141\1\162\1\145\1\165\2\uffff\1\157\7\uffff\1\163\1\154"+
        "\1\147\1\162\1\172\1\145\1\162\1\145\1\172\1\151\1\163\1\157\1\156"+
        "\1\163\1\165\1\142\1\144\2\172\1\151\1\164\1\uffff\1\172\1\163\1"+
        "\156\1\154\1\145\1\uffff\1\156\1\164\1\152\1\144\1\164\1\145\1\163"+
        "\1\165\2\uffff\1\156\1\145\1\uffff\2\164\1\165\1\162\2\172\1\145"+
        "\1\157\1\172\1\145\1\164\1\154\1\172\1\163\2\172\1\144\1\163\2\uffff"+
        "\1\143\1\155\1\uffff\1\172\1\162\1\145\1\uffff\1\151\2\uffff\2\145"+
        "\1\164\1\172\1\145\1\165\1\172\1\141\1\172\1\143\1\151\1\uffff\1"+
        "\172\1\143\1\uffff\1\156\1\uffff\1\164\1\157\1\uffff\1\164\2\172"+
        "\1\156\1\172\2\uffff\1\137\1\uffff\1\71\11\172\11\uffff";
    static final String DFA2_acceptS =
        "\5\uffff\1\5\3\uffff\1\14\5\uffff\1\35\1\36\1\uffff\1\40\1\41\1"+
        "\42\1\43\1\44\1\45\1\46\25\uffff\1\6\5\uffff\1\12\10\uffff\1\1\1"+
        "\2\2\uffff\1\7\22\uffff\1\16\1\17\2\uffff\1\32\3\uffff\1\3\1\uffff"+
        "\1\10\1\11\13\uffff\1\31\2\uffff\1\37\1\uffff\1\13\2\uffff\1\33"+
        "\5\uffff\1\4\1\15\1\uffff\1\34\12\uffff\1\20\1\21\1\22\1\23\1\24"+
        "\1\25\1\26\1\27\1\30";
    static final String DFA2_specialS =
        "\u0090\uffff}>";
    static final String[] DFA2_transitionS = {
            "\2\30\2\uffff\1\30\22\uffff\1\30\1\uffff\1\26\5\uffff\1\17\1"+
            "\20\1\24\1\uffff\1\25\2\24\1\uffff\12\24\1\uffff\1\27\5\uffff"+
            "\1\24\1\3\1\4\1\24\1\6\1\7\2\24\1\10\1\12\1\24\1\13\3\24\1\14"+
            "\1\24\1\15\1\16\7\24\3\uffff\1\5\1\24\1\uffff\10\24\1\1\3\24"+
            "\1\21\1\2\14\24\1\22\1\uffff\1\23\1\11",
            "\1\31",
            "\1\32",
            "\1\33",
            "\1\34",
            "",
            "\1\35\6\uffff\1\36",
            "\1\37",
            "\1\40\11\uffff\1\41",
            "",
            "\1\42",
            "\1\43",
            "\1\44",
            "\1\45\3\uffff\1\46",
            "\1\47\3\uffff\1\50",
            "",
            "",
            "\1\51",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\52",
            "\1\53",
            "\1\54",
            "\1\55",
            "\1\24\2\uffff\2\24\1\uffff\12\24\7\uffff\32\24\4\uffff\1\24"+
            "\1\uffff\32\24",
            "\1\57",
            "\1\60",
            "\1\61",
            "\1\24\2\uffff\2\24\1\uffff\12\24\7\uffff\32\24\4\uffff\1\24"+
            "\1\uffff\2\24\1\62\20\24\1\63\6\24",
            "\1\65",
            "\1\66",
            "\1\67",
            "\1\70",
            "\1\71",
            "\1\72",
            "\1\73",
            "\1\74",
            "\1\24\2\uffff\2\24\1\uffff\12\24\7\uffff\32\24\4\uffff\1\24"+
            "\1\uffff\32\24",
            "\1\24\2\uffff\2\24\1\uffff\12\24\7\uffff\32\24\4\uffff\1\24"+
            "\1\uffff\32\24",
            "\1\77",
            "\1\100",
            "",
            "\1\24\2\uffff\2\24\1\uffff\12\24\7\uffff\32\24\4\uffff\1\24"+
            "\1\uffff\32\24",
            "\1\102",
            "\1\103",
            "\1\104",
            "\1\105",
            "",
            "\1\106",
            "\1\107",
            "\1\110",
            "\1\111",
            "\1\112",
            "\1\113",
            "\1\114",
            "\1\115",
            "",
            "",
            "\1\116",
            "\1\117",
            "",
            "\1\120",
            "\1\121",
            "\1\122",
            "\1\123",
            "\1\24\2\uffff\2\24\1\uffff\12\24\7\uffff\32\24\4\uffff\1\24"+
            "\1\uffff\32\24",
            "\1\24\2\uffff\2\24\1\uffff\12\24\7\uffff\32\24\4\uffff\1\24"+
            "\1\uffff\32\24",
            "\1\126",
            "\1\127",
            "\1\24\2\uffff\2\24\1\uffff\12\24\7\uffff\32\24\4\uffff\1\24"+
            "\1\uffff\32\24",
            "\1\131",
            "\1\132",
            "\1\133",
            "\1\24\2\uffff\2\24\1\uffff\12\24\7\uffff\32\24\4\uffff\1\24"+
            "\1\uffff\32\24",
            "\1\135",
            "\1\24\2\uffff\2\24\1\uffff\12\24\7\uffff\32\24\4\uffff\1\24"+
            "\1\uffff\32\24",
            "\1\24\2\uffff\2\24\1\uffff\12\24\7\uffff\32\24\4\uffff\1\24"+
            "\1\uffff\32\24",
            "\1\140",
            "\1\141",
            "",
            "",
            "\1\142",
            "\1\143",
            "",
            "\1\144",
            "\1\145",
            "\1\146",
            "",
            "\1\147",
            "",
            "",
            "\1\150",
            "\1\151",
            "\1\152",
            "\1\24\2\uffff\2\24\1\uffff\12\24\7\uffff\32\24\4\uffff\1\24"+
            "\1\uffff\32\24",
            "\1\154",
            "\1\155",
            "\1\24\2\uffff\2\24\1\uffff\12\24\7\uffff\32\24\4\uffff\1\24"+
            "\1\uffff\32\24",
            "\1\157",
            "\1\24\2\uffff\2\24\1\uffff\12\24\7\uffff\32\24\4\uffff\1\24"+
            "\1\uffff\32\24",
            "\1\161",
            "\1\162",
            "",
            "\1\24\2\uffff\2\24\1\uffff\12\24\7\uffff\32\24\4\uffff\1\24"+
            "\1\uffff\32\24",
            "\1\164",
            "",
            "\1\165",
            "",
            "\1\166",
            "\1\167",
            "",
            "\1\170",
            "\1\24\2\uffff\2\24\1\uffff\12\24\7\uffff\32\24\4\uffff\1\24"+
            "\1\uffff\32\24",
            "\1\24\2\uffff\2\24\1\uffff\12\24\7\uffff\32\24\4\uffff\1\24"+
            "\1\uffff\32\24",
            "\1\173",
            "\1\24\2\uffff\2\24\1\uffff\12\24\7\uffff\32\24\4\uffff\1\24"+
            "\1\uffff\32\24",
            "",
            "",
            "\1\175",
            "",
            "\1\176\1\177\1\u0080\1\u0081\1\u0082\1\u0083\1\u0084\1\u0085"+
            "\1\u0086",
            "\1\24\2\uffff\2\24\1\uffff\12\24\7\uffff\32\24\4\uffff\1\24"+
            "\1\uffff\32\24",
            "\1\24\2\uffff\2\24\1\uffff\12\24\7\uffff\32\24\4\uffff\1\24"+
            "\1\uffff\32\24",
            "\1\24\2\uffff\2\24\1\uffff\12\24\7\uffff\32\24\4\uffff\1\24"+
            "\1\uffff\32\24",
            "\1\24\2\uffff\2\24\1\uffff\12\24\7\uffff\32\24\4\uffff\1\24"+
            "\1\uffff\32\24",
            "\1\24\2\uffff\2\24\1\uffff\12\24\7\uffff\32\24\4\uffff\1\24"+
            "\1\uffff\32\24",
            "\1\24\2\uffff\2\24\1\uffff\12\24\7\uffff\32\24\4\uffff\1\24"+
            "\1\uffff\32\24",
            "\1\24\2\uffff\2\24\1\uffff\12\24\7\uffff\32\24\4\uffff\1\24"+
            "\1\uffff\32\24",
            "\1\24\2\uffff\2\24\1\uffff\12\24\7\uffff\32\24\4\uffff\1\24"+
            "\1\uffff\32\24",
            "\1\24\2\uffff\2\24\1\uffff\12\24\7\uffff\32\24\4\uffff\1\24"+
            "\1\uffff\32\24",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA2_eot = DFA.unpackEncodedString(DFA2_eotS);
    static final short[] DFA2_eof = DFA.unpackEncodedString(DFA2_eofS);
    static final char[] DFA2_min = DFA.unpackEncodedStringToUnsignedChars(DFA2_minS);
    static final char[] DFA2_max = DFA.unpackEncodedStringToUnsignedChars(DFA2_maxS);
    static final short[] DFA2_accept = DFA.unpackEncodedString(DFA2_acceptS);
    static final short[] DFA2_special = DFA.unpackEncodedString(DFA2_specialS);
    static final short[][] DFA2_transition;

    static {
        int numStates = DFA2_transitionS.length;
        DFA2_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA2_transition[i] = DFA.unpackEncodedString(DFA2_transitionS[i]);
        }
    }

    class DFA2 extends DFA {

        public DFA2(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 2;
            this.eot = DFA2_eot;
            this.eof = DFA2_eof;
            this.min = DFA2_min;
            this.max = DFA2_max;
            this.accept = DFA2_accept;
            this.special = DFA2_special;
            this.transition = DFA2_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( IAS | NIL | OPBEGIN | OPCARTESIAN | OPCREATEEXPR | OPEQ | OPEXECUTE | OPFIRST | OPIDENT | OPIN | OPINCL | OPINTERPRET | OPINTERSECT | OPJOIN | OPLAST | OPPROJECTION_1 | OPPROJECTION_2 | OPPROJECTION_3 | OPPROJECTION_4 | OPPROJECTION_5 | OPPROJECTION_6 | OPPROJECTION_7 | OPPROJECTION_8 | OPPROJECTION_9 | OPRANDOM | OPREST | OPSQU | OPSUBSTRUCT | T__37 | T__38 | T__39 | T__40 | T__41 | ID | COMMA | DQUOTE | SEMICOLON | WS );";
        }
    }
 

}