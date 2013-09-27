package com.vw.lang.sink.java.entity.undefined.strategy;

import org.apache.log4j.Logger;

import com.vw.lang.sink.java.VWMLContextsRepository;
import com.vw.lang.sink.java.VWMLObject;
import com.vw.lang.sink.java.VWMLObjectBuilder;
import com.vw.lang.sink.java.VWMLObjectsRepository;
import com.vw.lang.sink.java.interpreter.datastructure.VWMLContext;
import com.vw.lang.sink.java.link.AbstractVWMLLinkVisitor;
import com.vw.lang.sink.java.link.VWMLLinkage;

/**
 * Defines 'ue_im3' processing logic - means that undefined entity is interpreted as is (loop interpretation)
 * @author ogibayev
 *
 */
public class UndefinedEntityAsEntityInterpretationStrategy extends UndefinedEntityInterpretationStrategy {

	private Logger logger = Logger.getLogger(UndefinedEntityAsEntityInterpretationStrategy.class);
	
	@Override
	public VWMLObject process(String context, Object id, AbstractVWMLLinkVisitor visitor, VWMLLinkage linkage) throws Exception {
		VWMLContext ctx = VWMLContextsRepository.instance().get(context);
		if (ctx == null) {
			throw new Exception("couldn't find context idetntified by '" + context + "'");
		}
		// adds undefined entity to repository 
		VWMLObjectsRepository.acquire(VWMLObjectBuilder.VWMLObjectType.SIMPLE_ENTITY, id,
									  context, linkage.getEntityHistorySize(), visitor);
		linkage.interpretAs(id, id, ctx);
		if (logger.isDebugEnabled()) {
			logger.debug("undefined entity '" + id + "' is interpreted as simple entity '" + id + "'; cyclic interpretation on effective context '" + context + "'");
		}
		return VWMLObjectsRepository.instance().get(id, ctx);
	}
}
