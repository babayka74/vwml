package com.vw.lang.sink.java.operations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import com.vw.lang.sink.java.VWMLObject;

/**
 * Encapsulates operations with VWML's operations on storage
 * @author ogibayev
 *
 */
public class VWMLOperations extends VWMLObject {
	private AtomicInteger currOp = new AtomicInteger(0);
	private List<VWMLOperation> operations = Collections.synchronizedList(new ArrayList<VWMLOperation>());

	/**
	 * Associates VWML's operation with current entity; so entity is considered as term
	 * @param op
	 */
	public void addOperation(VWMLOperation op) {
		operations.add(op);
	}

	/**
	 * Removes VWML's operation; so if term doesn't have any operation then term is considered as entity
	 * @param op
	 */
	public void removeOperation(VWMLOperation op) {
		operations.remove(op);
	}

	/**
	 * Returns current operation and moves pointer to next operation
	 * @return
	 */
	public VWMLOperation peekOperation() {
		VWMLOperation op = null;
		if (hasNextOperation()) {
			op = operations.get(currOp.get());
			currOp.getAndIncrement();
		}
		return op;
	}
	
	/**
	 * Returns true in case if operation's pointer points to right place in operations storage
	 * @return
	 */
	public boolean hasNextOperation() {
		return (currOp.get() >= operations.size()) ? false : true;
	}
	
	/**
	 * Resets pointer of operations storage (moves it to beginning)
	 */
	public void reset() {
		currOp.set(0);
	}
	
	/**
	 * Clears operations storage
	 */
	public void clear() {
		reset();
		operations.clear();
	}
}
