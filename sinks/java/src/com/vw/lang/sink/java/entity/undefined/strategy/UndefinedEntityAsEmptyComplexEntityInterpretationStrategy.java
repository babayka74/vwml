package com.vw.lang.sink.java.entity.undefined.strategy;

import org.apache.log4j.Logger;

import com.vw.lang.sink.java.VWMLContextsRepository;
import com.vw.lang.sink.java.VWMLObject;
import com.vw.lang.sink.java.VWMLObjectBuilder;
import com.vw.lang.sink.java.VWMLObjectsRepository;
import com.vw.lang.sink.java.interpreter.datastructure.VWMLContext;
import com.vw.lang.sink.java.link.AbstractVWMLLinkVisitor;
import com.vw.lang.sink.java.link.VWMLLinkage;
import com.vw.lang.sink.utils.ComplexEntityNameBuilder;

/**
 * Defines 'ue_im1' processing logic - means that undefined entity is interpreted as empty complex entity ()
 * @author ogibayev
 *
 */
public class UndefinedEntityAsEmptyComplexEntityInterpretationStrategy extends UndefinedEntityInterpretationStrategy {

	private Logger logger = Logger.getLogger(UndefinedEntityAsEmptyComplexEntityInterpretationStrategy.class);
	
	@Override
	public VWMLObject process(String context, Object id, AbstractVWMLLinkVisitor visitor, VWMLLinkage linkage) throws Exception {
		VWMLContext ctx = VWMLContextsRepository.instance().get(context);
		if (ctx == null) {
			throw new Exception("couldn't find context idetntified by '" + context + "'");
		}
		// generating 'fake' complex entity
		String ceName = ComplexEntityNameBuilder.generateEmptyComplexEntity();
		// adds undefined entity to repository 
		VWMLObjectsRepository.acquire(VWMLObjectBuilder.VWMLObjectType.SIMPLE_ENTITY,
									  id, context, linkage.getEntityHistorySize(), visitor);
		VWMLObjectsRepository.acquire(VWMLObjectBuilder.VWMLObjectType.COMPLEX_ENTITY,
									  ceName, context, linkage.getEntityHistorySize(), visitor);
		// builds interpreting association
		linkage.interpretAs(id, ceName, ctx);
		if (logger.isDebugEnabled()) {
			logger.debug("undefined entity '" + id + "' is interpreted as complex entity '" + ceName + "'");
		}
		return VWMLObjectsRepository.instance().get(id, ctx);
	}
}
