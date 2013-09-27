package com.vw.lang.sink.java;

import java.util.HashMap;
import java.util.Map;

import com.vw.lang.sink.java.interpreter.datastructure.VWMLContext;
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
		String[] contextPath = VWMLJavaExportUtils.parseContext((String)contextId);
		String rootContext = null;
		if (contextPath == null || contextPath[0].length() == 0) {
			rootContext = s_default_context;
		}
		else {
			rootContext = contextPath[0];
		}
		VWMLContext root = contextsMap.get(rootContext);
		if (root == null) {
			root = VWMLContext.instance();
			root.setContext(rootContext);
			contextsMap.put(rootContext, root);
		}
		return create(root, contextPath, 1);
	}
	
	/**
	 * Returns context identified by id
	 * @param contextId
	 * @return
	 */
	public VWMLContext get(Object contextId) {
		String[] contextPath = VWMLJavaExportUtils.parseContext((String)contextId);
		String rootContext = null;
		if (contextPath == null || contextPath[0].length() == 0) {
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
		for(int i = pos; i < contextPath.length; i++) {
			VWMLObject next = null;
			for(VWMLObject o : parent.getLink().getLinkedObjects()) {
				if (o.getId().equals(contextPath[i])) {
					next = o;
					break;
				}
			}
			if (next == null) {
				next = VWMLContext.instance();
				((VWMLContext)next).setContext(contextPath[i]);
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
				if (o.getId().equals(contextPath[i])) {
					next = o;
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
}
