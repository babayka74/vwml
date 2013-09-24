package com.vw.lang.sink.java.entity.undefined.strategy;

import com.vw.lang.sink.java.VWMLObject;
import com.vw.lang.sink.java.VWMLObjectBuilder;
import com.vw.lang.sink.java.VWMLObjectsRepository;
import com.vw.lang.sink.java.entity.VWMLEntity;
import com.vw.lang.sink.java.link.AbstractVWMLLinkVisitor;
import com.vw.lang.sink.java.link.VWMLLinkage;

/**
 * Defines 'ue_im2' processing logic - means that undefined entity is interpreted as simple entity 'nil'
 * @author ogibayev
 *
 */
public class UndefinedEntityAsNilEntityInterpretationStrategy extends UndefinedEntityInterpretationStrategy {

	@Override
	public VWMLObject process(String context, Object id, AbstractVWMLLinkVisitor visitor, VWMLLinkage linkage) throws Exception {
		// adds undefined entity to repository 
		VWMLObjectsRepository.acquire(VWMLObjectBuilder.VWMLObjectType.SIMPLE_ENTITY, id,
									  context, linkage.getEntityHistorySize(), visitor);
		VWMLObjectsRepository.acquire(VWMLObjectBuilder.VWMLObjectType.SIMPLE_ENTITY, VWMLEntity.s_NilEntityId,
									  context, linkage.getEntityHistorySize(), visitor);
		linkage.interpretAs(id, VWMLEntity.s_NilEntityId, context);
		return VWMLObjectsRepository.instance().get(id, context);
	}

}
