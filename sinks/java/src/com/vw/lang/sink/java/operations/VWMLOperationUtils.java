package com.vw.lang.sink.java.operations;

import java.util.List;

import com.vw.lang.sink.java.VWMLContextsRepository;
import com.vw.lang.sink.java.VWMLObjectBuilder;
import com.vw.lang.sink.java.VWMLObjectsRepository;
import com.vw.lang.sink.java.VWMLObjectBuilder.VWMLObjectType;
import com.vw.lang.sink.java.entity.VWMLEntity;
import com.vw.lang.sink.java.interpreter.datastructure.VWMLContext;
import com.vw.lang.sink.java.link.AbstractVWMLLinkVisitor;
import com.vw.lang.sink.utils.ComplexEntityNameBuilder;

/**
 * Some aux. methods related to VWML's operations
 * @author ogibayev
 *
 */
public class VWMLOperationUtils {

	public static boolean s_addIfUnknown = true;
	public static boolean s_dontAddIfUnknown = false;
	
	
	/**
	 * Generates complex entity from list by adding entities starting from the end of the list; name is random
	 * @param entities
	 * @param fromPos
	 * @param context
	 * @param effectiveContext
	 * @param interpretationHistorySize
	 * @param visitor
	 * @return
	 */
	public static VWMLEntity generateComplexEntityFromEntitiesReversedStack(List<VWMLEntity> entities,
			                                                                int fromPos,
			                                                                String context,
			                                                                String effectiveContext,
			                                                                int interpretationHistorySize,
			                                                                AbstractVWMLLinkVisitor visitor,
			                                                                boolean addIfUnknown) throws Exception {
		VWMLEntity newComplexEntity = null;
		// if effective context (lifeterm's context) isn't parent of entity's context it means that 
		// actual interpretation is being run on context identified by effectiveContext id
		if (!VWMLContext.isContextChildOf(effectiveContext, context)) {
			context = effectiveContext;
		}
		boolean remove = true;
		String cen = ComplexEntityNameBuilder.generateRandomName();
		if (entities.size() > 0) {
			newComplexEntity = (VWMLEntity)VWMLObjectsRepository.acquire(VWMLObjectType.COMPLEX_ENTITY,
																		 cen,
																		 context,
																		 interpretationHistorySize,
																		 visitor);
	
			for(int i = fromPos; i >= 0; i--) {
				newComplexEntity.getLink().link(entities.get(i));
			}
		}
		else {
			newComplexEntity = (VWMLEntity)VWMLObjectsRepository.instance().getEmptyEntity();
			remove = false;
		}
		if (addIfUnknown) {
			VWMLContext ctx = VWMLContextsRepository.instance().get(context);
			if (ctx == null) {
				throw new Exception("couldn't find context identified by '" + context + "'");
			}
			String id = newComplexEntity.buildReadableId();
			VWMLEntity lookedEntity = (VWMLEntity)VWMLObjectsRepository.instance().get(id, ctx);
			if (lookedEntity != null) {
				if (remove) {
					VWMLObjectsRepository.instance().remove(newComplexEntity);
					newComplexEntity.getLink().unlinkFromAll();
				}
				newComplexEntity = lookedEntity;
			}
			else {
				newComplexEntity.setId(id);
				VWMLObjectsRepository.instance().addConcrete(newComplexEntity, ctx);
			}
		}
		return newComplexEntity;
	}
	
	/**
	 * Complex entity which is generated from list by adding entities from the end of the list; the name is generated from names of entities
	 * @param entities
	 * @param fromPos
	 * @param context
	 * @param interpretationHistorySize
	 * @param visitor
	 * @return
	 */
	public static VWMLEntity generateComplexEntityFromEntitiesReversedStackEx(List<VWMLEntity> entities,
																	          int fromPos,
																	          String context,
																	          String effectiveContext,
																	          int interpretationHistorySize,
																	          AbstractVWMLLinkVisitor visitor) throws Exception {

		String cen = ComplexEntityNameBuilder.generateRandomName();
		String id = "";
		VWMLEntity newEntity = null;
		// if effective context (lifeterm's context) isn't parent of entity's context it means that 
		// actual interpretation is being run on context identified by effectiveContext id
		if (!VWMLContext.isContextChildOf(effectiveContext, context)) {
			context = effectiveContext;
		}
		VWMLContext ctx = VWMLContextsRepository.instance().get(context);
		if (ctx == null) {
			throw new Exception("couldn't find context identified by '" + context + "'");
		}
		// complex entity
		if (entities.size() > 0) {
			// acquire new complex entity with random name
			newEntity = (VWMLEntity)VWMLObjectBuilder.build(VWMLObjectType.COMPLEX_ENTITY,
															cen,
															ctx,
															interpretationHistorySize,
															visitor);
			// filling entity by entities from stack; the name consists from entities names 
			ComplexEntityNameBuilder ceb = ComplexEntityNameBuilder.instance();
			ceb.startProgress();
			for(int i = fromPos; i >= 0; i--) {
				newEntity.getLink().link(entities.get(i));
				ceb.addObjectId(entities.get(i).buildReadableId());
			}
			ceb.stopProgress();
			id = ceb.build();
			// sets entity's id to generated id
			newEntity.setId(id);
			newEntity = VWMLObjectsRepository.instance().addConcrete(newEntity, ctx);
		}
		else {
			// builds empty complex entity name (related to set of default entities)
			newEntity = (VWMLEntity)VWMLObjectsRepository.instance().getEmptyEntity();
		}
		return newEntity;
	}	
}
