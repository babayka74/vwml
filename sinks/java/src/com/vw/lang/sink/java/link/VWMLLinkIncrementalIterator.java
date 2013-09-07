package com.vw.lang.sink.java.link;

/**
 * Implements simple interator with range control mechanism; the iterator uses 'int' type for pointer
 * and not thread safe.
 * @author ogibayev
 *
 */
public class VWMLLinkIncrementalIterator {
	private int it;
	private int size;
	
	private static int s_not_correct_mark_value = -1;
	
	public VWMLLinkIncrementalIterator() {
		
	}
	
	public VWMLLinkIncrementalIterator(int size) {
		this.size = size;
	}
	
	public int next() {
		it++;
		checkPointer();
		return it;
	}

	public int prev() {
		it--;
		checkPointer();
		return it;
	}
	
	public int getIt() {
		return it;
	}

	public void setIt(int it) {
		this.it = it;
		checkPointer();
	}
	
	public boolean isCorrect() {
		checkPointer();
		return (it == s_not_correct_mark_value) ? false : true;
	}
	
	protected void checkPointer() {
		if (size != 0 && it >= size) {
			it = -1;
		}
		else
		if (size != 0 && it < 0) {
			it = -1;
		}
	}
}
