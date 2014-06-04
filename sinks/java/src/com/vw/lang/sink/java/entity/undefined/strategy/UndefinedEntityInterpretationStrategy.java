package com.vw.lang.sink.java.entity.undefined.strategy;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.vw.lang.sink.java.VWMLObject;
import com.vw.lang.sink.java.entity.ArgPair;
import com.vw.lang.sink.java.link.AbstractVWMLLinkVisitor;
import com.vw.lang.sink.java.link.VWMLLinkage;

/**
 * Root class which defines abstraction for processing logic when undefined entity is encountered
 * during linkage operation
 * @author ogibayev
 *
 */
public abstract class UndefinedEntityInterpretationStrategy {
		
	private static String s_syntheticEntity = "$";
	
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
				if (matcher.find()) {
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
