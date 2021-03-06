package com.vw.lang.sink.java.link;

import java.util.ArrayList;
import java.util.List;

import com.vw.lang.sink.OperationInfo;
import com.vw.lang.sink.entity.InterpretationOfUndefinedEntityStrategyId;
import com.vw.lang.sink.java.VWMLObject;
import com.vw.lang.sink.java.VWMLObjectsRepository;
import com.vw.lang.sink.java.entity.VWMLEntity;
import com.vw.lang.sink.java.entity.undefined.strategy.UndefinedEntityAsEmptyComplexEntityInterpretationStrategy;
import com.vw.lang.sink.java.entity.undefined.strategy.UndefinedEntityAsEntityInterpretationStrategy;
import com.vw.lang.sink.java.entity.undefined.strategy.UndefinedEntityAsNilEntityInterpretationStrategy;
import com.vw.lang.sink.java.entity.undefined.strategy.UndefinedEntityInterpretationStrategy;
import com.vw.lang.sink.java.entity.undefined.strategy.UndefinedEntityStrictInterpretationStrategy;
import com.vw.lang.sink.java.interpreter.datastructure.VWMLContext;

/**
 * Each module's linking component (generated by processor) implements this interface
 * @author ogibayev
 *
 */
public class VWMLLinkage {

	private static List<VWMLEntity> modLifeTerms = new ArrayList<VWMLEntity>();
	
	/**
	 * Adds life term
	 * @param entityAsTerm
	 */
	public static void addLifeTerm(VWMLEntity entityAsTerm) {
		modLifeTerms.add(entityAsTerm);
	}
	
	/**
	 * Returns list of all lifeterms
	 * @return
	 */
	public static List<VWMLEntity> getLifeTerms() {
		return modLifeTerms;
	}

	public static void resetAllLifeTerms() {
		modLifeTerms.clear();
	}
	
	/**
	 * Returns list of all source lifeterms
	 * @return
	 */
	public static List<VWMLEntity> getSourceLifeTerms() {
		List<VWMLEntity> sourceLifeTerms = new ArrayList<VWMLEntity>();
		for(VWMLEntity e : getLifeTerms()) {
			if (e.isLifeTermAsSource()) {
				sourceLifeTerms.add(e);
			}
		}
		return sourceLifeTerms;
	}
	
	/**
	 * Overridden by module's linkage
	 * @return
	 */
	public InterpretationOfUndefinedEntityStrategyId getUndefinedEntityInterpretationStrategyId() {
		return InterpretationOfUndefinedEntityStrategyId.STRICT;
	}
	
	/**
	 * Overridden by module's linkage
	 * @return
	 */
	public AbstractVWMLLinkVisitor getPreprocessorStructureVisualizer() {
		return null;
	}
	
	/**
	 * Overridden by module's linkage
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public VWMLObject interpretUndefinedEntity(Object id) throws Exception {
		throw new Exception("must be implemented by module's linkage");
	}
	
	/**
	 * Overridden by module's linkage
	 * @return
	 */
	public int getEntityHistorySize() {
		return 0;
	}
	
	/**
	 * Interprets object identified by id as object identified by interpretingId
	 * @param id
	 * @param interpretingId
	 * @param context
	 * @throws Exception
	 */
	public void interpretAs(Object id, Object interpretingId, VWMLContext context) throws Exception {
		VWMLEntity obj = (VWMLEntity)VWMLObjectsRepository.instance().get(id, context);
		VWMLEntity interpretingObj = (VWMLEntity)VWMLObjectsRepository.instance().get(interpretingId, context);
		obj.setInterpreting(interpretingObj);
	}
	
	public void processUndefinedEntitiesOnPostLink() throws Exception {
		InterpretationOfUndefinedEntityStrategyId interpretationOfUndefinedEntityStrategyId = getUndefinedEntityInterpretationStrategyId();
		UndefinedEntityInterpretationStrategy strategy = null;
		if (interpretationOfUndefinedEntityStrategyId == InterpretationOfUndefinedEntityStrategyId.STRICT) {
			strategy = new UndefinedEntityStrictInterpretationStrategy();
		}
		else
		if (interpretationOfUndefinedEntityStrategyId == InterpretationOfUndefinedEntityStrategyId.UE_IM1) {
			strategy = new UndefinedEntityAsEmptyComplexEntityInterpretationStrategy();
		}
		else
		if (interpretationOfUndefinedEntityStrategyId == InterpretationOfUndefinedEntityStrategyId.UE_IM2) {
			strategy = new UndefinedEntityAsNilEntityInterpretationStrategy();
		}
		else
		if (interpretationOfUndefinedEntityStrategyId == InterpretationOfUndefinedEntityStrategyId.UE_IM3) {
			strategy = new UndefinedEntityAsEntityInterpretationStrategy();
		}
		strategy.postLinkProcessUndefinedEntities();
	}
	
	public OperationInfo[] getOperationDebugInfo(Object uniqId) {
		return null;
	}
}
