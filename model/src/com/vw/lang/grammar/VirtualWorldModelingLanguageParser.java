// $ANTLR 3.4 C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g 2013-05-24 08:37:09

package com.vw.lang.grammar;

import com.vw.lang.processor.model.builder.VWMLModelBuilder;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

/**
---------------------- Virtual World's language grammar ------------------------------------

*/
@SuppressWarnings({"all", "warnings", "unchecked"})
public class VirtualWorldModelingLanguageParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "COMMA", "DQUOTE", "IAS", "ID", "NIL", "OPBEGIN", "OPCARTESIAN", "OPCREATEEXPR", "OPEQ", "OPEXECUTE", "OPFIRST", "OPIDENT", "OPIN", "OPINCL", "OPINTERPRET", "OPINTERSECT", "OPJOIN", "OPLAST", "OPPROJECTION_1", "OPPROJECTION_2", "OPPROJECTION_3", "OPPROJECTION_4", "OPPROJECTION_5", "OPPROJECTION_6", "OPPROJECTION_7", "OPPROJECTION_8", "OPPROJECTION_9", "OPRANDOM", "OPREST", "OPSQU", "OPSUBSTRUCT", "WS", "'('", "')'", "'module'", "'{'", "'}'"
    };

    public static final int EOF=-1;
    public static final int T__36=36;
    public static final int T__37=37;
    public static final int T__38=38;
    public static final int T__39=39;
    public static final int T__40=40;
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
    public static final int WS=35;

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



    	private VWMLModelBuilder s_vwmlModelBuilder = null;
     	private boolean inDebug = false;
    	
    	protected void setInDebug(boolean inDebug) {
    		this.inDebug = inDebug;
    		s_vwmlModelBuilder.setDebug(inDebug);		
    	}
    	
    	protected boolean isInDebug() {
    		return this.inDebug;
    	}



    // $ANTLR start "module"
    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:72:1: module : 'module' ID body EOF ;
    public final void module() throws RecognitionException {
         s_vwmlModelBuilder = VWMLModelBuilder.instance(); setInDebug(true); 
        try {
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:74:5: ( 'module' ID body EOF )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:74:7: 'module' ID body EOF
            {
            match(input,38,FOLLOW_38_in_module310); 

            match(input,ID,FOLLOW_ID_in_module312); 

            pushFollow(FOLLOW_body_in_module314);
            body();

            state._fsp--;


            match(input,EOF,FOLLOW_EOF_in_module316); 

             

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
    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:77:1: body : '{' ( expression ( expression )* )? '}' ;
    public final void body() throws RecognitionException {
        try {
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:78:4: ( '{' ( expression ( expression )* )? '}' )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:78:6: '{' ( expression ( expression )* )? '}'
            {
            match(input,39,FOLLOW_39_in_body338); 

            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:78:10: ( expression ( expression )* )?
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==36) ) {
                alt2=1;
            }
            switch (alt2) {
                case 1 :
                    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:78:11: expression ( expression )*
                    {
                    pushFollow(FOLLOW_expression_in_body341);
                    expression();

                    state._fsp--;


                    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:78:22: ( expression )*
                    loop1:
                    do {
                        int alt1=2;
                        int LA1_0 = input.LA(1);

                        if ( (LA1_0==36) ) {
                            alt1=1;
                        }


                        switch (alt1) {
                    	case 1 :
                    	    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:78:23: expression
                    	    {
                    	    pushFollow(FOLLOW_expression_in_body344);
                    	    expression();

                    	    state._fsp--;


                    	    }
                    	    break;

                    	default :
                    	    break loop1;
                        }
                    } while (true);


                    }
                    break;

            }


            match(input,40,FOLLOW_40_in_body350); 

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
    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:82:1: expression : '(' entity_name IAS term ')' ;
    public final void expression() throws RecognitionException {
        try {
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:83:5: ( '(' entity_name IAS term ')' )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:83:7: '(' entity_name IAS term ')'
            {
            match(input,36,FOLLOW_36_in_expression368); 

            pushFollow(FOLLOW_entity_name_in_expression370);
            entity_name();

            state._fsp--;


            match(input,IAS,FOLLOW_IAS_in_expression372); 

            pushFollow(FOLLOW_term_in_expression374);
            term();

            state._fsp--;


            match(input,37,FOLLOW_37_in_expression376); 

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



    // $ANTLR start "entity_name"
    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:87:1: entity_name : ( ID | ( '(' ( ID )+ ')' )? );
    public final void entity_name() throws RecognitionException {
        try {
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:88:5: ( ID | ( '(' ( ID )+ ')' )? )
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==ID) ) {
                alt5=1;
            }
            else if ( (LA5_0==IAS||LA5_0==36) ) {
                alt5=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 5, 0, input);

                throw nvae;

            }
            switch (alt5) {
                case 1 :
                    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:88:7: ID
                    {
                    match(input,ID,FOLLOW_ID_in_entity_name394); 

                    }
                    break;
                case 2 :
                    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:89:7: ( '(' ( ID )+ ')' )?
                    {
                    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:89:7: ( '(' ( ID )+ ')' )?
                    int alt4=2;
                    int LA4_0 = input.LA(1);

                    if ( (LA4_0==36) ) {
                        alt4=1;
                    }
                    switch (alt4) {
                        case 1 :
                            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:89:8: '(' ( ID )+ ')'
                            {
                            match(input,36,FOLLOW_36_in_entity_name403); 

                            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:89:12: ( ID )+
                            int cnt3=0;
                            loop3:
                            do {
                                int alt3=2;
                                int LA3_0 = input.LA(1);

                                if ( (LA3_0==ID) ) {
                                    alt3=1;
                                }


                                switch (alt3) {
                            	case 1 :
                            	    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:89:12: ID
                            	    {
                            	    match(input,ID,FOLLOW_ID_in_entity_name405); 

                            	    }
                            	    break;

                            	default :
                            	    if ( cnt3 >= 1 ) break loop3;
                                        EarlyExitException eee =
                                            new EarlyExitException(3, input);
                                        throw eee;
                                }
                                cnt3++;
                            } while (true);


                            match(input,37,FOLLOW_37_in_entity_name408); 

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
    // $ANTLR end "entity_name"



    // $ANTLR start "term"
    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:93:1: term : ( entity ( oplist )* | expression );
    public final void term() throws RecognitionException {
        try {
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:94:5: ( entity ( oplist )* | expression )
            int alt7=2;
            alt7 = dfa7.predict(input);
            switch (alt7) {
                case 1 :
                    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:94:7: entity ( oplist )*
                    {
                    pushFollow(FOLLOW_entity_in_term428);
                    entity();

                    state._fsp--;


                    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:94:14: ( oplist )*
                    loop6:
                    do {
                        int alt6=2;
                        int LA6_0 = input.LA(1);

                        if ( ((LA6_0 >= OPBEGIN && LA6_0 <= OPSUBSTRUCT)) ) {
                            alt6=1;
                        }


                        switch (alt6) {
                    	case 1 :
                    	    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:94:15: oplist
                    	    {
                    	    pushFollow(FOLLOW_oplist_in_term431);
                    	    oplist();

                    	    state._fsp--;


                    	    }
                    	    break;

                    	default :
                    	    break loop6;
                        }
                    } while (true);


                    }
                    break;
                case 2 :
                    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:95:7: expression
                    {
                    pushFollow(FOLLOW_expression_in_term441);
                    expression();

                    state._fsp--;


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
    // $ANTLR end "term"



    // $ANTLR start "entity"
    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:98:1: entity : ( simple_entity | complex_entity );
    public final void entity() throws RecognitionException {
        try {
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:99:5: ( simple_entity | complex_entity )
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==ID) ) {
                alt8=1;
            }
            else if ( (LA8_0==36) ) {
                alt8=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 8, 0, input);

                throw nvae;

            }
            switch (alt8) {
                case 1 :
                    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:99:7: simple_entity
                    {
                    pushFollow(FOLLOW_simple_entity_in_entity460);
                    simple_entity();

                    state._fsp--;


                    }
                    break;
                case 2 :
                    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:100:7: complex_entity
                    {
                    pushFollow(FOLLOW_complex_entity_in_entity468);
                    complex_entity();

                    state._fsp--;


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
    // $ANTLR end "entity"



    // $ANTLR start "simple_entity"
    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:104:1: simple_entity : ID ;
    public final void simple_entity() throws RecognitionException {
        try {
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:105:5: ( ID )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:105:7: ID
            {
            match(input,ID,FOLLOW_ID_in_simple_entity487); 

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
    // $ANTLR end "simple_entity"



    // $ANTLR start "complex_entity"
    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:108:1: complex_entity : '(' ( term )* ')' ;
    public final void complex_entity() throws RecognitionException {
        try {
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:109:5: ( '(' ( term )* ')' )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:109:7: '(' ( term )* ')'
            {
            match(input,36,FOLLOW_36_in_complex_entity504); 

            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:109:11: ( term )*
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( (LA9_0==ID||LA9_0==36) ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:109:12: term
            	    {
            	    pushFollow(FOLLOW_term_in_complex_entity507);
            	    term();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop9;
                }
            } while (true);


            match(input,37,FOLLOW_37_in_complex_entity511); 

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
    // $ANTLR end "complex_entity"



    // $ANTLR start "oplist"
    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:116:1: oplist : ( opclist | opprojection );
    public final void oplist() throws RecognitionException {
        try {
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:117:5: ( opclist | opprojection )
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( ((LA10_0 >= OPBEGIN && LA10_0 <= OPLAST)||(LA10_0 >= OPRANDOM && LA10_0 <= OPSUBSTRUCT)) ) {
                alt10=1;
            }
            else if ( ((LA10_0 >= OPPROJECTION_1 && LA10_0 <= OPPROJECTION_9)) ) {
                alt10=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 10, 0, input);

                throw nvae;

            }
            switch (alt10) {
                case 1 :
                    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:117:7: opclist
                    {
                    pushFollow(FOLLOW_opclist_in_oplist566);
                    opclist();

                    state._fsp--;


                    }
                    break;
                case 2 :
                    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:118:7: opprojection
                    {
                    pushFollow(FOLLOW_opprojection_in_oplist574);
                    opprojection();

                    state._fsp--;


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



    // $ANTLR start "opclist"
    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:121:1: opclist : ( OPJOIN | OPINTERSECT | OPSUBSTRUCT | OPFIRST | OPLAST | OPBEGIN | OPREST | OPCARTESIAN | OPIN | OPINCL | OPEQ | OPIDENT | OPSQU | OPINTERPRET | OPCREATEEXPR | OPEXECUTE | OPRANDOM );
    public final void opclist() throws RecognitionException {
        try {
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:122:5: ( OPJOIN | OPINTERSECT | OPSUBSTRUCT | OPFIRST | OPLAST | OPBEGIN | OPREST | OPCARTESIAN | OPIN | OPINCL | OPEQ | OPIDENT | OPSQU | OPINTERPRET | OPCREATEEXPR | OPEXECUTE | OPRANDOM )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:
            {
            if ( (input.LA(1) >= OPBEGIN && input.LA(1) <= OPLAST)||(input.LA(1) >= OPRANDOM && input.LA(1) <= OPSUBSTRUCT) ) {
                input.consume();
                state.errorRecovery=false;
            }
            else {
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
    // $ANTLR end "opclist"



    // $ANTLR start "opprojection"
    // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:141:1: opprojection : ( OPPROJECTION_1 | OPPROJECTION_2 | OPPROJECTION_3 | OPPROJECTION_4 | OPPROJECTION_5 | OPPROJECTION_6 | OPPROJECTION_7 | OPPROJECTION_8 | OPPROJECTION_9 );
    public final void opprojection() throws RecognitionException {
        try {
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:142:5: ( OPPROJECTION_1 | OPPROJECTION_2 | OPPROJECTION_3 | OPPROJECTION_4 | OPPROJECTION_5 | OPPROJECTION_6 | OPPROJECTION_7 | OPPROJECTION_8 | OPPROJECTION_9 )
            // C:\\Users\\ogibayev\\projects\\vw\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:
            {
            if ( (input.LA(1) >= OPPROJECTION_1 && input.LA(1) <= OPPROJECTION_9) ) {
                input.consume();
                state.errorRecovery=false;
            }
            else {
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
    // $ANTLR end "opprojection"

    // Delegated rules


    protected DFA7 dfa7 = new DFA7(this);
    static final String DFA7_eotS =
        "\11\uffff";
    static final String DFA7_eofS =
        "\11\uffff";
    static final String DFA7_minS =
        "\1\7\1\uffff\3\6\1\uffff\2\6\1\7";
    static final String DFA7_maxS =
        "\1\44\1\uffff\3\45\1\uffff\3\45";
    static final String DFA7_acceptS =
        "\1\uffff\1\1\3\uffff\1\2\3\uffff";
    static final String DFA7_specialS =
        "\11\uffff}>";
    static final String[] DFA7_transitionS = {
            "\1\1\34\uffff\1\2",
            "",
            "\1\5\1\3\34\uffff\1\4\1\1",
            "\1\5\1\1\1\uffff\32\1\1\uffff\2\1",
            "\1\1\1\6\34\uffff\2\1",
            "",
            "\1\1\1\10\1\uffff\32\1\1\uffff\1\1\1\7",
            "\1\5\1\1\1\uffff\32\1\1\uffff\2\1",
            "\1\10\1\uffff\32\1\1\uffff\1\1\1\7"
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
            return "93:1: term : ( entity ( oplist )* | expression );";
        }
    }
 

    public static final BitSet FOLLOW_38_in_module310 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_ID_in_module312 = new BitSet(new long[]{0x0000008000000000L});
    public static final BitSet FOLLOW_body_in_module314 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_module316 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_39_in_body338 = new BitSet(new long[]{0x0000011000000000L});
    public static final BitSet FOLLOW_expression_in_body341 = new BitSet(new long[]{0x0000011000000000L});
    public static final BitSet FOLLOW_expression_in_body344 = new BitSet(new long[]{0x0000011000000000L});
    public static final BitSet FOLLOW_40_in_body350 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_36_in_expression368 = new BitSet(new long[]{0x00000010000000C0L});
    public static final BitSet FOLLOW_entity_name_in_expression370 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_IAS_in_expression372 = new BitSet(new long[]{0x0000001000000080L});
    public static final BitSet FOLLOW_term_in_expression374 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_37_in_expression376 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_entity_name394 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_36_in_entity_name403 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_ID_in_entity_name405 = new BitSet(new long[]{0x0000002000000080L});
    public static final BitSet FOLLOW_37_in_entity_name408 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_entity_in_term428 = new BitSet(new long[]{0x00000007FFFFFE02L});
    public static final BitSet FOLLOW_oplist_in_term431 = new BitSet(new long[]{0x00000007FFFFFE02L});
    public static final BitSet FOLLOW_expression_in_term441 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simple_entity_in_entity460 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_complex_entity_in_entity468 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_simple_entity487 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_36_in_complex_entity504 = new BitSet(new long[]{0x0000003000000080L});
    public static final BitSet FOLLOW_term_in_complex_entity507 = new BitSet(new long[]{0x0000003000000080L});
    public static final BitSet FOLLOW_37_in_complex_entity511 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_opclist_in_oplist566 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_opprojection_in_oplist574 = new BitSet(new long[]{0x0000000000000002L});

}