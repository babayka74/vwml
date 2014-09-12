package com.vw.lang.grammar.preprocessor;

import org.antlr.runtime.Token;
import org.antlr.runtime.TokenSource;
import org.antlr.runtime.TokenStream;

public class VWMLTokenStream implements TokenStream {

	private TokenStream impl;
	private VWMLPreprocessor preprocessor = new VWMLPreprocessor();
	
	public VWMLTokenStream(TokenStream impl) {
		super();
		this.impl = impl;
	}

	public TokenStream getImpl() {
		return impl;
	}

	public void setImpl(TokenStream impl) {
		this.impl = impl;
	}

	@Override
	public void consume() {
		impl.consume();
	}

	@Override
	public int LA(int i) {
		return impl.LA(i);
	}

	@Override
	public int mark() {
		return impl.mark();
	}

	@Override
	public int index() {
		return impl.index();
	}

	@Override
	public void rewind(int marker) {
		impl.rewind(marker);
	}

	@Override
	public void rewind() {
		impl.rewind();
	}

	@Override
	public void release(int marker) {
		impl.release(marker);
	}

	@Override
	public void seek(int index) {
		impl.seek(index);
	}

	@Override
	public int size() {
		return impl.size();
	}

	@Override
	public String getSourceName() {
		return impl.getSourceName();
	}

	@Override
	public Token LT(int k) {
		Token t = null;
		t = impl.LT(k);
		preprocessor.process(t);
		return t;
	}

	@Override
	public int range() {
		return impl.range();
	}

	@Override
	public Token get(int i) {
		Token t = impl.get(i);
		t = preprocessor.process(t);
		return t;
	}

	@Override
	public TokenSource getTokenSource() {
		return impl.getTokenSource();
	}

	@Override
	public String toString(int start, int stop) {
		return impl.toString();
	}

	@Override
	public String toString(Token start, Token stop) {
		return impl.toString(start, stop);
	}

	public VWMLPreprocessor getPreprocessor() {
		return preprocessor;
	}
}
