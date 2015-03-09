package com.vw.lang.sink.java;

import java.util.HashMap;
import java.util.Map;

import com.vw.lang.sink.java.beyond.fringe.creature.VWMLCreature;
import com.vw.lang.sink.java.entity.VWMLComplexEntity;
import com.vw.lang.sink.java.entity.VWMLEntity;
import com.vw.lang.sink.java.entity.VWMLSimpleEntity;
import com.vw.lang.sink.java.entity.VWMLTerm;
import com.vw.lang.sink.java.interpreter.datastructure.VWMLContext;
import com.vw.lang.sink.java.link.AbstractVWMLLinkVisitor;

/**
 * Simple VWML's object builder
 * @author ogibayev
 *
 */
public class VWMLObjectBuilder {
	
	public static enum VWMLObjectType {
		OBJECT("OBJECT"),
		SIMPLE_ENTITY("SIMPLE_ENTITY"),
		COMPLEX_ENTITY("COMPLEX_ENTITY"),
		TERM("TERM"),
		CONTEXT("CONTEXT"),
		CREATURE("CREATURE");
		
		private final String value;
		
		VWMLObjectType(String value) {
			this.value = value;
		}
		
		public static VWMLObjectType fromValue(String value) {
			if (value != null) {
				for(VWMLObjectType m : values()) {
					if (m.value.equals(value)) {
						return m;
					}
				}
			}
			return getDefault();
		}
		
		public String toValue() {
			return value;
		}
		
		public static VWMLObjectType getDefault() {
			return OBJECT;
		}
		
	}
	
	public static abstract class Builder {
		
		public VWMLObject objectBuilder(VWMLEntity obj, Object hashId, Object id, VWMLContext context, Integer entityHistorySize, AbstractVWMLLinkVisitor visitor) {
			if (obj == null) {
				obj = (VWMLEntity)build(hashId, id);
			}
			else {
				obj.setId(id);
				obj.setHashId(hashId);
			}
			obj.setContext(context);
			setInterpretationHistorySize(obj, entityHistorySize);
			if (visitor != null) {			
				addVisitor(obj, visitor);
			}
			return obj;
		}
		
		protected abstract VWMLObject build(Object hashId, Object id);
		protected abstract void setInterpretationHistorySize(VWMLObject o, int size);
		
		protected void addVisitor(VWMLObject obj, AbstractVWMLLinkVisitor visitor) {
			obj.getLink().setLinkOperationVisitor(visitor);
		}
	}
	
	public static class ObjectBuilder extends Builder {

		@Override
		public VWMLObject build(Object hashId, Object id) {
			return new VWMLObject(hashId, id, null);
		}

		@Override
		protected void setInterpretationHistorySize(VWMLObject o, int size) {
		}
	}
	
	public static class SimpleEntityBuilder extends Builder {

		@Override
		public VWMLObject build(Object hashId, Object id) {
			return new VWMLSimpleEntity(hashId, id, null);
		}

		@Override
		protected void setInterpretationHistorySize(VWMLObject o, int size) {
			((VWMLSimpleEntity)o).setInterpretationHistorySize(size);
		}
	}
	
	public static class ComplexEntityBuilder extends Builder {

		@Override
		public VWMLObject build(Object hashId, Object id) {
			return new VWMLComplexEntity(hashId, id, null);
		}
		
		@Override
		protected void setInterpretationHistorySize(VWMLObject o, int size) {
			((VWMLComplexEntity)o).setInterpretationHistorySize(size);
		}
	}

	public static class TermBuilder extends Builder {

		@Override
		public VWMLObject build(Object hashId, Object id) {
			return new VWMLTerm(hashId, id, null);
		}
		
		@Override
		protected void setInterpretationHistorySize(VWMLObject o, int size) {
			((VWMLTerm)o).setInterpretationHistorySize(size);
		}
	}

	public static class CreatureBuilder extends Builder {

		@Override
		public VWMLObject build(Object hashId, Object id) {
			return new VWMLCreature(hashId, id, null);
		}
		
		@Override
		protected void setInterpretationHistorySize(VWMLObject o, int size) {
			((VWMLCreature)o).setInterpretationHistorySize(size);
		}
	}
	
	/**
	 * Builders' map
	 */
	@SuppressWarnings("serial")
	private static Map<VWMLObjectType, Builder> s_builders = new HashMap<VWMLObjectType, Builder>() {
		{
			put(VWMLObjectType.OBJECT, new ObjectBuilder());
			put(VWMLObjectType.SIMPLE_ENTITY, new SimpleEntityBuilder());
			put(VWMLObjectType.COMPLEX_ENTITY, new ComplexEntityBuilder());
			put(VWMLObjectType.TERM, new TermBuilder());
			put(VWMLObjectType.CREATURE, new CreatureBuilder());			
		}
	};
	
	/**
	 * Builds builder according to its type; the visitor is used for debug purposes only - when
	 * we need to visualize object's structure
	 * @param builderType
	 * @param id
	 * @param context
	 * @param historyEntitySize
	 * @param visitor
	 * @return
	 */
	public static VWMLObject build(VWMLObjectBuilder.VWMLObjectType builderType, Object hashId, Object id, VWMLContext context, Integer entityHistorySize, AbstractVWMLLinkVisitor visitor) {
		VWMLObject obj = null;
		if (VWMLObjectsRepository.instance().getGarbageManager() != null) {
			obj = VWMLObjectsRepository.instance().getGarbageManager().requestForEntity(builderType);
		}
		obj = s_builders.get(builderType).objectBuilder((VWMLEntity)obj, hashId, id, context, entityHistorySize, visitor);
		return obj;
	}
}
