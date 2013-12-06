package com.vw.lang.sink.java;

import java.util.HashMap;
import java.util.Map;

import com.vw.lang.sink.java.entity.VWMLEntity;
import com.vw.lang.sink.java.interpreter.datastructure.VWMLContext;
import com.vw.lang.sink.java.link.VWMLLinkIncrementalIterator;
import com.vw.lang.sink.java.repository.VWMLRepository;

/**
 * Global repository of contexts. Data structure is 'trie'
 * @author ogibayev
 *
 */
public class VWMLContextsRepository extends VWMLRepository {
	
	private static String s_default_context = "__vwml_root_context__";
	
	private VWMLContextsRepository() {
		createContextIfNotExists(s_default_context);
	}
	
	private Map<Object, VWMLContext> contextsMap = new HashMap<Object, VWMLContext>();
	
	private static final VWMLContextsRepository s_contextsRepository = new VWMLContextsRepository();
	
	public static VWMLContextsRepository instance() {
		return s_contextsRepository;
	}
	
	/**
	 * Returns id of default context
	 * @return
	 */
	public static String getDefaultContextId() {
		return s_default_context;
	}
	
	/**
	 * Clones context identified by id
	 * @param asContext
	 * @param newContextId
	 * @return
	 */
	public static VWMLContext clone(Object newContextId, VWMLContext context, VWMLCloneAuxCache auxCache) throws Exception {
		context = VWMLContextsRepository.instance().get(VWMLContextsRepository.instance().normalizeContext(context.getContext()));
		String[] clonedContextFullPath = context.getContextPath().clone();
		clonedContextFullPath[context.getContextPath().length - 1] = (String)newContextId;
		VWMLContext newContext = VWMLContextsRepository.instance().createFromContextPath(clonedContextFullPath);
		copyFrom(context.getContextName(), newContext.getContextName(), context, newContext, auxCache);
		return newContext;
	}
	
	/**
	 * Releases context's resources and related entities
	 * @param clonedContext
	 * @throws Exception
	 */
	public static void releaseCloned(VWMLContext clonedContext) throws Exception {
		VWMLContextsRepository.instance().release(clonedContext);
	}

	/**
	 * Removes entity which was associated with context from context itslef
	 * @param entity
	 * @param context
	 */
	public static void removeAssociatedEntityFromContext(VWMLEntity entity, VWMLContext context) {
		if (entity != null && context != null) {
			VWMLContextsRepository.instance().removeAssociatedEntity(entity, context);
			entity.getLink().unlinkFromAll();
		}
	}
	
	/**
	 * Returns default context which can be root if VWML program doesn't contain any contexts
	 * @return
	 */
	public VWMLContext getDefaultContext() {
		VWMLContext defaultContext = get(s_default_context);
		if (defaultContext == null) {
			defaultContext = createContextIfNotExists(s_default_context);
		}
		return defaultContext;
	}	
	
	/**
	 * Creates and registers in repository in case if context doesn't exist, otherwise reference to existed context is returned
	 * @param contextId
	 * @return
	 */
	public VWMLContext createContextIfNotExists(Object contextId) {
		contextId = normalizeContext((String)contextId);
		String[] contextPath = VWMLJavaExportUtils.parseContext((String)contextId);
		return createFromContextPath(contextPath);
	}

	/**
	 * Allows to create context (if not exists) by context path
	 * @param contextPath
	 * @return
	 */
	public VWMLContext createFromContextPath(String[] contextPath) {
		String rootContext = null;
		int startCtxIndex = 1;
		if (contextPath == null || contextPath.length == 0 || contextPath[0].length() == 0) {
			rootContext = s_default_context;
		}
		else {
			rootContext = contextPath[0];
			if (!rootContext.equals(getDefaultContextId())) {
				rootContext = getDefaultContextId();
				startCtxIndex = 0;
			}
		}
		VWMLContext root = contextsMap.get(rootContext);
		if (root == null) {
			root = VWMLContext.instance();
			root.setContext(rootContext);
			contextsMap.put(rootContext, root);
		}
		return create(root, contextPath, startCtxIndex);
	}
	
	/**
	 * Returns context identified by id
	 * @param contextId
	 * @return
	 */
	public VWMLContext get(Object contextId) {
		contextId = normalizeContext((String)contextId);
		String[] contextPath = VWMLJavaExportUtils.parseContext((String)contextId);
		String rootContext = null;
		if (contextPath == null || contextPath.length == 0 || contextPath[0].length() == 0) {
			rootContext = s_default_context;
		}
		else {
			rootContext = contextPath[0];
		}
		VWMLContext root = contextsMap.get(rootContext);
		if (root == null) {
			return null;
		}		
		return find(root, contextPath, 1);
	}

	protected VWMLContext create(VWMLContext parent, String[] contextPath, int pos) {
		String actualContext = "";
		for(int i = pos; i < contextPath.length; i++) {
			VWMLObject next = null;
			for(VWMLObject o : parent.getLink().getLinkedObjects()) {
				if (o.getId().equals(contextPath[i])) {
					next = o;
					break;
				}
			}
			if (actualContext.length() > 0) {
				actualContext += ".";
			}
			actualContext += contextPath[i];
			if (next == null) {
				next = VWMLContext.instance();
				((VWMLContext)next).setContext(actualContext);
				((VWMLContext)next).setContextName(contextPath[i]);
				parent.getLink().link(next);
			}
			parent = (VWMLContext)next;
		}
		return parent;
	}
	
	protected VWMLContext find(VWMLContext parent, String[] contextPath, int pos) {
		for(int i = pos; i < contextPath.length; i++) {
			VWMLObject next = null;
			for(VWMLObject o : parent.getLink().getLinkedObjects()) {
				if (((VWMLContext)o).getContextName().equals(contextPath[i])) {
					next = o;
					break;
				}
			}
			if (next == null) {
				parent = null;
				break;
			}
			parent = (VWMLContext)next;
		}
		return parent;
	}
	
	protected String normalizeContext(String context) {
		if (context == null || context.length() == 0 || context.equals(s_default_context)) {
			return s_default_context;
		}
		if (context.startsWith(s_default_context)) {
			return context;
		}
		return s_default_context + "." + context; 
	}
	
	protected static void copyFrom(String initialEntityId, String newEntityId, VWMLContext contextFrom, VWMLContext contextTo, VWMLCloneAuxCache auxCache) throws Exception {
		if (initialEntityId != null && newEntityId != null) {
			for(VWMLEntity e : contextFrom.getAssociatedEntities()) {
				if (!e.isOriginal()) {
					continue; // passing entities which were created in runtime
				}
				System.out.println("entity '" + e.getId() + "' on context '" + contextFrom.getContext() + "'");
				e.clone(initialEntityId, newEntityId, contextTo, contextFrom, auxCache, false);
			}
		}
		VWMLLinkIncrementalIterator it = contextFrom.getLink().acquireLinkedObjectsIterator();
		if (it != null) {
			for(; it.isCorrect(); it.next()) {
				VWMLContext c = (VWMLContext)contextFrom.getLink().getConcreteLinkedEntity(it.getIt());
				VWMLContext n = instance().createContextIfNotExists(contextTo.getContext() + "." + c.getId());
				copyFrom(initialEntityId, newEntityId, c, n, auxCache);
			}
		}
	}
	
	protected void release(VWMLContext context) {
		System.out.println("release context '" + context.getContext() + "'");
		for(VWMLEntity e : context.getAssociatedEntities()) {
			// release entity's relations
			e.getLink().unlinkFromAll();
		}
		VWMLLinkIncrementalIterator it = context.getLink().acquireLinkedObjectsIterator();
		if (it != null) {
			for(; it.isCorrect(); it.next()) {
				VWMLContext c = (VWMLContext)context.getLink().getConcreteLinkedEntity(it.getIt());
				if (c != null) {
					release(c);
				}
			}
		}
		context.getLink().clear();
		context.removeAllAssociatedEntities();
	}
	
	protected void removeAssociatedEntity(VWMLEntity entity, VWMLContext context) {
		context.getAssociatedEntities().remove(entity);
	}

}
