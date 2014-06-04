package com.vw.lang.sink.java.entity.undefined.strategy;

import com.vw.lang.sink.java.VWMLContextsRepository;
import com.vw.lang.sink.java.VWMLObject;
import com.vw.lang.sink.java.VWMLObjectBuilder;
import com.vw.lang.sink.java.VWMLObjectsRepository;
import com.vw.lang.sink.java.entity.ArgPair;
import com.vw.lang.sink.java.entity.VWMLEntity;
import com.vw.lang.sink.java.interpreter.datastructure.VWMLContext;
import com.vw.lang.sink.java.link.AbstractVWMLLinkVisitor;
import com.vw.lang.sink.java.link.VWMLLinkage;

/**
 * Defines 'ue_im3' processing logic - means that undefined entity is interpreted as is (loop interpretation)
 * @author ogibayev
 *
 */
public class UndefinedEntityAsEntityInterpretationStrategy extends UndefinedEntityInterpretationStrategy {

	@Override
	public VWMLObject process(String context, Object id, AbstractVWMLLinkVisitor visitor, VWMLLinkage linkage) throws Exception {
		VWMLContext ctx = VWMLContextsRepository.instance().get(context);
		if (ctx == null) {
			throw new Exception("couldn't find context idetntified by '" + context + "'");
		}
		// adds undefined entity to repository 
		VWMLEntity e = (VWMLEntity)VWMLObjectsRepository.acquire(VWMLObjectBuilder.VWMLObjectType.SIMPLE_ENTITY, id,
									  				 			 context, linkage.getEntityHistorySize(), VWMLObjectsRepository.asOriginal, visitor);
		if (e != null) {
			if (isEntitySynthetic((String)id)) {
				e.setSynthetic(true);
			}
			else {
				ArgPair argPair = parseEntityAsArg((String)id);
				if (argPair != null) {
					e.setAsArgPair(argPair);
				}
			}
		}
		linkage.interpretAs(id, id, ctx);
		return VWMLObjectsRepository.instance().get(id, ctx);
	}
}
