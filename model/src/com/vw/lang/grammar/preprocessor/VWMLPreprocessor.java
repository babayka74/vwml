package com.vw.lang.grammar.preprocessor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.antlr.runtime.Token;

/**
 * Performs pre-processing tasks (#if_debug, etc)
 * @author Oleg
 *
 */
public class VWMLPreprocessor {
	
	public static class VWMLProcessorDirective {
		private String name;
		private String value;
		
		public VWMLProcessorDirective(String name, String value) {
			super();
			this.name = name;
			this.value = value;
		}
		
		public String getName() {
			return name;
		}
		
		public String getValue() {
			return value;
		}
	}
	
	protected static class VWMLPRocessorToken {
		private Token token;
		private boolean skipModeInitiator = false;

		public VWMLPRocessorToken(Token token) {
			super();
			this.token = token;
		}

		public boolean isSkipModeInitiator() {
			return skipModeInitiator;
		}
		
		public void setSkipModeInitiator(boolean skipModeInitiator) {
			this.skipModeInitiator = skipModeInitiator;
		}

		public Token getToken() {
			return token;
		}
	}
	
	protected static abstract class VWMLDirectiveProcessor {
		protected abstract Token process(VWMLPreprocessor preprocessor, Token token);
	}
	
	@SuppressWarnings("serial")
	private static final Map<String, VWMLDirectiveProcessor> s_preprocessorDispatcher = new HashMap<String, VWMLDirectiveProcessor>() {
		{
		}
	};

	private List<VWMLPRocessorToken> directivesStack = new ArrayList<VWMLPRocessorToken>();
	private static Map<String, VWMLProcessorDirective> activeDirectives = new HashMap<String, VWMLProcessorDirective>();
	
	public static void addDirective(String name, String value) {
		if (name != null) {
			VWMLProcessorDirective activeDirective = new VWMLProcessorDirective(name, value);
			activeDirectives.put(name, activeDirective);
		}
	}

	public static boolean isDebugDirectiveOn() {
		return activeDirectives.get("debug") != null;
	}
	
	public Token process(Token token) {
		Token t = token;
		if (t != null) {
			VWMLDirectiveProcessor preprocessor = s_preprocessorDispatcher.get(t.getText());
			if (preprocessor != null) {
				t = preprocessor.process(this, t);
			}
		}
		return t;
	}
	
	protected void pushDirectiveToken(VWMLPRocessorToken token) {
		directivesStack.add(token);
	}
	
	protected VWMLPRocessorToken popDirectiveToken() {
		VWMLPRocessorToken t = null;
		if (directivesStack.size() != 0) {
			t = directivesStack.get(directivesStack.size() - 1);
			directivesStack.remove(directivesStack.size() - 1);
		}
		return t;
	}
}
