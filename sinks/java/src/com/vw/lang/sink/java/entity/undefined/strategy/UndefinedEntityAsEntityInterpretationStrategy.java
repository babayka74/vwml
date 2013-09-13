package com.vw.lang.sink.java.entity.undefined.strategy;

import org.apache.log4j.Logger;

import com.vw.lang.sink.java.VWMLObject;
import com.vw.lang.sink.java.VWMLObjectBuilder;
import com.vw.lang.sink.java.VWMLObjectsRepository;
import com.vw.lang.sink.java.link.IVWMLLinkVisitor;
import com.vw.lang.sink.java.link.VWMLLinkage;

/**
 * Defines 'ue_im3' processing logic - means that undefined entity is interpreted as is (loop interpretation)
 * @author ogibayev
 *
 */
public class UndefinedEntityAsEntityInterpretationStrategy extends UndefinedEntityInterpretationStrategy {

	private Logger logger = Logger.getLogger(UndefinedEntityAsEntityInterpretationStrategy.class);
	
	@Override
	public VWMLObject process(String context, Object id, IVWMLLinkVisitor visitor, VWMLLinkage linkage) throws Exception {
		// adds undefined entity to repository 
		VWMLObjectsRepository.acquire(VWMLObjectBuilder.VWMLObjectType.SIMPLE_ENTITY, id,
									  context, linkage.getEntityHistorySize(), visitor);
		linkage.interpretAs(id, id, context);
		if (logger.isDebugEnabled()) {
			logger.debug("undefined entity '" + id + "' is interpreted as simple entity '" + id + "'; cyclic interpretation on effective context '" + context + "'");
		}
		return VWMLObjectsRepository.instance().get(id, context);
	}
}
