package com.vw.lang.sink.java.entity.undefined.strategy;

import com.vw.lang.sink.java.VWMLObject;
import com.vw.lang.sink.java.link.IVWMLLinkVisitor;
import com.vw.lang.sink.java.link.VWMLLinkage;

/**
 * Root class which defines abstraction for processing logic when undefined entity is encountered
 * during linkage operation
 * @author ogibayev
 *
 */
public abstract class UndefinedEntityInterpretationStrategy {
	
	/**
	 * Defines concrete logic how to process undefined entity
	 * @param id
	 * @param visitor
	 */
	public abstract VWMLObject process(Object id, IVWMLLinkVisitor visitor, VWMLLinkage linkage) throws Exception;
}
