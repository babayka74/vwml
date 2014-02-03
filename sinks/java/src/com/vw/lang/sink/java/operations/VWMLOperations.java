package com.vw.lang.sink.java.operations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.vw.lang.sink.java.VWMLObject;
import com.vw.lang.sink.java.link.VWMLLinkIncrementalIterator;

/**
 * Encapsulates operations with VWML's operations on storage
 * @author ogibayev
 *
 */
public class VWMLOperations extends VWMLObject {
	private List<VWMLOperation> operations = Collections.synchronizedList(new ArrayList<VWMLOperation>());

	public VWMLOperations(Object hashId) {
		super(hashId);
	}
	
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
	 * Returns number of active operations
	 * @return
	 */
	public int operations() {
		return operations.size();
	}
	
	/**
	 * Returns current operation and moves pointer to next operation
	 * @param it - iterator
	 * @return
	 */
	public VWMLOperation peekOperation(VWMLLinkIncrementalIterator it) {
		VWMLOperation op = null;
		if (it.isCorrect()) {
			op = operations.get(it.getIt());
			it.next();
		}
		return op;
	}

	/**
	 * Clears operations storage
	 */
	public void clear() {
		operations.clear();
	}
	
	/**
	 * Returns instance of iterator of container of operations
	 * @return
	 */
	public VWMLLinkIncrementalIterator acquireLinkedObjectsIterator() {
		return new VWMLLinkIncrementalIterator(operations.size());
	}
}
