package com.vw.lang.sink.java.entity.undefined.strategy;

import com.vw.lang.sink.java.VWMLObject;
import com.vw.lang.sink.java.link.AbstractVWMLLinkVisitor;
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
	 * @param context
	 * @param id
	 * @param visitor
	 */
	public abstract VWMLObject process(String context, Object id, AbstractVWMLLinkVisitor visitor, VWMLLinkage linkage) throws Exception;
}
