package com.vw.lang.sink.java;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

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

	public static class Pool {
		private Map<VWMLObjectType, ConcurrentLinkedQueue<VWMLObject>> entityPool = new ConcurrentHashMap<VWMLObjectBuilder.VWMLObjectType, ConcurrentLinkedQueue<VWMLObject>>();
		
		public VWMLObject get(VWMLObjectType type) {
			VWMLObject o = null;
			ConcurrentLinkedQueue<VWMLObject> s = entityPool.get(type);
			if (s != null) {
				o = s.poll();
//				System.out.println("Pool of '" + type + "' has elements '" + s.size() + "'");
			}
			return o;
		}
		
		public void put(VWMLObjectType type, VWMLObject o) {
			ConcurrentLinkedQueue<VWMLObject> s = entityPool.get(type);
			if (s == null) {
				s = new ConcurrentLinkedQueue<VWMLObject>();
				entityPool.put(type, s);
			}
			s.offer(o);
//			System.out.println("Pool of '" + type + "' has elements '" + s.size() + "'");
		}
	}
	
	public static abstract class Builder {
		
		public VWMLObject objectBuilder(Object hashId, Object id, VWMLContext context, Integer entityHistorySize, AbstractVWMLLinkVisitor visitor) {
			VWMLEntity obj = (VWMLEntity)build(hashId, id);
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
	
	private static Pool s_pool = new Pool();
	
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
		VWMLObject obj = s_pool.get(builderType);
		if (obj == null) {
			obj = s_builders.get(builderType).objectBuilder(hashId, id, context, entityHistorySize, visitor);
		}
		else {
			// reinitialize
			obj.restore(hashId, id);
			((VWMLEntity)obj).setContext(context);
			((VWMLEntity)obj).setInterpretationHistorySize(entityHistorySize);
			if (visitor != null) {
				obj.getLink().setLinkOperationVisitor(visitor);
			}
		}
		return obj;
	}
	
	public static void returnToPool(VWMLEntity o) {
		if (o.isEntityPooled()) {
			o.restore("__removed__", "__removed__");
			o.setReadableId("__removed__");
			s_pool.put(o.deductEntityType(), o);
		}
	}
}
