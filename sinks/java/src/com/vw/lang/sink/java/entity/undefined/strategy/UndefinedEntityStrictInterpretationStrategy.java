package com.vw.lang.sink.java.entity.undefined.strategy;

import com.vw.lang.sink.java.VWMLObject;
import com.vw.lang.sink.java.link.IVWMLLinkVisitor;
import com.vw.lang.sink.java.link.VWMLLinkage;

/**
 * Defines 'strict' processing logic - means that exception is thrown when undefined entity is encountered
 * @author ogibayev
 *
 */
public class UndefinedEntityStrictInterpretationStrategy extends UndefinedEntityInterpretationStrategy {

	@Override
	public VWMLObject process(Object id, IVWMLLinkVisitor visitor, VWMLLinkage linkage) throws Exception {
		throw new Exception("undefined entity '" + id + "'");
	}
}
