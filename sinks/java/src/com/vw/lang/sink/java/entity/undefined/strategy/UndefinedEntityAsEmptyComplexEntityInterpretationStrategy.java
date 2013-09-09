package com.vw.lang.sink.java.entity.undefined.strategy;

import org.apache.log4j.Logger;

import com.vw.lang.sink.java.VWMLObject;
import com.vw.lang.sink.java.VWMLObjectBuilder;
import com.vw.lang.sink.java.VWMLObjectsRepository;
import com.vw.lang.sink.java.link.IVWMLLinkVisitor;
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
	public VWMLObject process(Object id, IVWMLLinkVisitor visitor, VWMLLinkage linkage) throws Exception {
		// generating 'fake' complex entity
		String ceName = ComplexEntityNameBuilder.generateEmptyComplexEntity();
		// adds undefined entity to repository 
		VWMLObjectsRepository.acquire(VWMLObjectBuilder.VWMLObjectType.SIMPLE_ENTITY, id, visitor);
		VWMLObjectsRepository.acquire(VWMLObjectBuilder.VWMLObjectType.COMPLEX_ENTITY, ceName, visitor);
		// builds interpreting association
		linkage.interpretAs(id, ceName);
		if (logger.isDebugEnabled()) {
			logger.debug("undefined entity '" + id + "' is interpreted as complex entity '" + ceName + "'");
		}
		return VWMLObjectsRepository.instance().get(id);
	}
}
