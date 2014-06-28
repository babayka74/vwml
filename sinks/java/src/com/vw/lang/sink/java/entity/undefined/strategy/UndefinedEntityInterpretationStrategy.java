package com.vw.lang.sink.java.entity.undefined.strategy;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.vw.lang.sink.java.VWMLObject;
import com.vw.lang.sink.java.entity.ArgPair;
import com.vw.lang.sink.java.interpreter.datastructure.VWMLContext;
import com.vw.lang.sink.java.link.AbstractVWMLLinkVisitor;
import com.vw.lang.sink.java.link.VWMLLinkage;

/**
 * Root class which defines abstraction for processing logic when undefined entity is encountered
 * during linkage operation
 * @author ogibayev
 *
 */
public abstract class UndefinedEntityInterpretationStrategy {
		
	protected static class UndefinedPair {
		private VWMLObject obj;
		private VWMLContext ctx;
		
		public UndefinedPair(VWMLObject obj, VWMLContext ctx) {
			super();
			this.obj = obj;
			this.ctx = ctx;
		}

		public VWMLObject getObj() {
			return obj;
		}

		public VWMLContext getCtx() {
			return ctx;
		}
	}
	
	private static String s_syntheticEntity = "$";
	private static List<UndefinedPair> undefinedEntities = new ArrayList<UndefinedPair>();
	
	
	public void addUndefined(VWMLObject obj, VWMLContext ctx) {
		undefinedEntities.add(new UndefinedPair(obj, ctx));
	}
	
	public void clearUndefined() {
		undefinedEntities.clear();
	}
	
	public void postLinkProcessUndefinedEntities() throws Exception {
		for(UndefinedPair pair : undefinedEntities) {
			postLinkProcessOfUndefinedEntity(pair.getObj(), pair.getCtx());
		}
		clearUndefined();
	}
	
	public void postLinkProcessOfUndefinedEntity(VWMLObject obj, VWMLContext ctx) throws Exception {
		throw new Exception("Should be implemented by concrete strategy");
	}
	
	/**
	 * Returns 'true' if entity is synthetic (used in operation ForEach)
	 * @param entityId
	 * @return
	 */
	public boolean isEntitySynthetic(String entityId) {
		return entityId.equals(s_syntheticEntity);
	}
	
	public ArgPair parseEntityAsArg(String entityId) {
		ArgPair argAsPair = null;
		if (entityId.startsWith("$") && entityId.length() > 1) {
			try {
				Pattern pattern = Pattern.compile("\\$([0-9]*)");
				Matcher matcher = pattern.matcher(entityId);
				if (matcher.find() && matcher.group(1).length() > 0) {
					argAsPair = new ArgPair();
					argAsPair.setPlaceNumber(matcher.group(1));
		        }
			}
			catch(Exception e) {
				// nothing to do
			}
		}
		return argAsPair;
	}
	
	/**
	 * Defines concrete logic how to process undefined entity
	 * @param context
	 * @param id
	 * @param visitor
	 */
	public abstract VWMLObject process(String context, Object id, AbstractVWMLLinkVisitor visitor, VWMLLinkage linkage) throws Exception;
}
