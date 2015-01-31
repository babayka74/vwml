package com.vw.lang.sink.java;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.vw.lang.sink.java.entity.VWMLEntity;
import com.vw.lang.sink.java.interpreter.datastructure.VWMLContext;
import com.vw.lang.sink.java.interpreter.datastructure.resource.manager.VWMLResourceHostManagerFactory;
import com.vw.lang.sink.java.link.VWMLLinkIncrementalIterator;
import com.vw.lang.sink.java.repository.VWMLRepository;

/**
 * Global repository of contexts. Data structure is 'trie'
 * @author ogibayev
 *
 */
public class VWMLContextsRepository extends VWMLRepository {
	
	private static String s_default_context = "__vwml_root_context__";
	
	private Map<Object, VWMLContext> contextsMap = null;
	private Set<String> lookup = new HashSet<String>();
	
	public static VWMLContextsRepository instance() {
		return VWMLResourceHostManagerFactory.hostManagerInstance().requestContextsRepo();
	}
	
	public void init() {
		contextsMap = VWMLResourceHostManagerFactory.hostManagerInstance().requestContextsRepoContainer();
		createContextIfNotExists(s_default_context);
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
	 * @param bornMode
	 * @return
	 */
	public static synchronized VWMLContext clone(Object newContextId, VWMLContext context, VWMLCloneAuxCache auxCache, boolean bornMode) throws Exception {
		context = VWMLContextsRepository.instance().get(VWMLContextsRepository.instance().normalizeContext(context.getContext()));
		String[] clonedContextFullPath = context.getContextPath().clone();
		clonedContextFullPath[context.getContextPath().length - 1] = (String)newContextId;
		VWMLContext newContext = VWMLContextsRepository.instance().createFromContextPath(clonedContextFullPath);
		VWMLContextsRepository.instance().copyFrom(context.getContextName(), newContext.getContextName(), context, newContext, context, auxCache, bornMode);
		return newContext;
	}
	
	/**
	 * Clones context identified by id for 'Clone/Born' operation only
	 * @param asContext
	 * @param newContextId
	 * @return
	 */
	public static synchronized VWMLContext cloneLazy(Object newContextId, VWMLContext context) throws Exception {
		context = VWMLContextsRepository.instance().get(VWMLContextsRepository.instance().normalizeContext(context.getContext()));
		String[] clonedContextFullPath = context.getContextPath().clone();
		clonedContextFullPath[context.getContextPath().length - 1] = (String)newContextId;
		VWMLContext newContext = VWMLContextsRepository.instance().createFromContextPath(clonedContextFullPath);
		newContext.setClonedFrom(context);
		return newContext;
	}

	/**
	 * Releases context's resources and related entities
	 * @param clonedContext
	 * @throws Exception
	 */
	public static synchronized void releaseCloned(VWMLContext clonedContext) throws Exception {
		VWMLContextsRepository.instance().releaseClonedImpl(clonedContext);
	}
	
	/**
	 * Performs context migration process from 'from' to 'to' repository
	 * @param from
	 * @param to
	 * @param context
	 */
	public static void migrate(VWMLContextsRepository from, VWMLContextsRepository to, VWMLContext context) {
		
	}

	/**
	 * Releases all contexts
	 */
	public synchronized void removeAll() {
		VWMLContextsRepository.instance().release(VWMLContextsRepository.instance().getRootContext());
		contextsMap.clear();
		lookup.clear();
		markAsInvalid();
	}
	
	/**
	 * Removes entity which was associated with context from context itslef
	 * @param entity
	 * @param context
	 */
	public static synchronized void removeAssociatedEntityFromContext(VWMLEntity entity, VWMLContext context) {
		if (entity != null && context != null) {
			VWMLContextsRepository.instance().removeAssociatedEntity(entity, context);
			entity.getLink().unlinkFromAll();
		}
	}
	
	/**
	 * Returns true in case if context belongs to the storage
	 * @param context
	 * @return
	 */
	public synchronized boolean belong(String context) {
		return lookup.contains(context);
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
	public synchronized VWMLContext createContextIfNotExists(Object contextId) {
		contextId = normalizeContext((String)contextId);
		String[] contextPath = VWMLJavaExportUtils.parseContext((String)contextId);
		return createFromContextPath(contextPath);
	}

	/**
	 * Allows to create context (if not exists) by context path
	 * @param contextPath
	 * @return
	 */
	public synchronized VWMLContext createFromContextPath(String[] contextPath) {
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
			root = VWMLContext.instance(rootContext);
			root.setContext(rootContext);
			contextsMap.put(rootContext, root);
		}
		VWMLContext ctx = create(root, contextPath, startCtxIndex);
		return ctx;
	}
	
	/**
	 * Returns context identified by id
	 * @param contextId
	 * @return
	 */
	public synchronized VWMLContext get(Object contextId) {
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
		VWMLContext ctx = find(root, contextPath, null, 1, -1);
		return ctx;
	}

	/**
	 * Returns main root context
	 * @return
	 */
	public VWMLContext getRootContext() {
		String rootContext = s_default_context;
		VWMLContext root = contextsMap.get(rootContext);
		return root;
	}
	
	/**
	 * Returns context identified by context path and path's element
	 * @param root
	 * @param contextPath
	 * @param pos
	 * @param contextElement
	 * @return
	 */
	public synchronized VWMLContext getByParsedPath(VWMLContext root, String[] contextPath, int pos, String contextElement) {
		int len = contextPath.length;
		VWMLContext context = null;
		for(int i = len; i >= 0 && context == null; i--) {
			context = find(root, contextPath, contextElement, pos, i);
		}
		return context;
	}
	
	protected VWMLContext create(VWMLContext parent, String[] contextPath, int pos) {
		String actualContext = "";
		boolean created = false;
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
				next = VWMLContext.instance(actualContext);
				((VWMLContext)next).setContext(actualContext);
				((VWMLContext)next).setContextName(contextPath[i]);
				parent.getLink().link(next);
				created = true;
			}
			parent = (VWMLContext)next;
		}
		if (created) {
			lookup.add(actualContext);
		}
		return parent;
	}
		
	protected VWMLContext find(VWMLContext parent, String[] contextPath, String contextElement, int pos, int toPos) {
		VWMLObject next = null;
		int tillPos = (toPos == -1) ? contextPath.length : toPos;
		for(int i = pos; i < tillPos; i++) {
			next = null;
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
		if (contextElement != null && parent != null) {
			next = null;
			for(VWMLObject o : parent.getLink().getLinkedObjects()) {
				if (((VWMLContext)o).getContextName().equals(contextElement)) {
					next = o;
					break;
				}
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
	
	protected void copyFrom(String initialEntityId, String newEntityId, VWMLContext contextFrom, VWMLContext contextTo, VWMLContext initial, VWMLCloneAuxCache auxCache, boolean bornMode) throws Exception {
		if (initialEntityId != null && newEntityId != null) {
			for(VWMLEntity e : contextFrom.getAssociatedEntities()) {
				if (!e.isOriginal()) {
					continue; // passing entities which were created in runtime
				}
				//System.out.println("Copy '" + e.buildReadableId() + "' from '" + contextFrom + "' to '" + contextTo + "'");
				e.clone(null, initialEntityId, newEntityId, contextTo, initial, auxCache, false, bornMode);
			}
		}
		VWMLLinkIncrementalIterator it = contextFrom.getLink().acquireLinkedObjectsIterator();
		if (it != null) {
			for(; it.isCorrect(); it.next()) {
				VWMLContext c = (VWMLContext)contextFrom.getLink().getConcreteLinkedEntity(it.getIt());
				VWMLContext n = instance().createContextIfNotExists(contextTo.getContext() + "." + c.getId());
				copyFrom(initialEntityId, newEntityId, c, n, initial, auxCache, bornMode);
			}
		}
	}

	protected void releaseClonedImpl(VWMLContext context) {
		release(context);
		if (context.getLink().getParent() != null) {
			context.getLink().getParent().getLink().getLinkedObjects().remove(context);
			context.getLink().setParent(null);
		}
	}
	
	protected void release(VWMLContext context) {
		//System.out.println("release context '" + context.getContext() + "'");
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
		for(VWMLEntity e : context.getAssociatedEntities()) {
			e.release();
		}
		context.removeAllAssociatedEntities();
	}
	
	protected void removeAssociatedEntity(VWMLEntity entity, VWMLContext context) {
		context.getAssociatedEntities().remove(entity);
	}

	protected void markAsInvalid() {
		VWMLResourceHostManagerFactory.hostManagerInstance().markContextsRepoAsInvalid();
	}
}
