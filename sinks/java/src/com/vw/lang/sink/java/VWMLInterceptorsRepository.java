package com.vw.lang.sink.java;

import java.util.Map;

import com.vw.lang.sink.java.entity.VWMLEntity;
import com.vw.lang.sink.java.interceptor.VWMLInterceptor;
import com.vw.lang.sink.java.interpreter.datastructure.resource.manager.VWMLResourceHostManagerFactory;

/**
 * VWML interceptors' manager
 * @author Oleg
 *
 */
public class VWMLInterceptorsRepository {

	private Map<String, VWMLInterceptor> repo = null;
	
	public static VWMLInterceptorsRepository instance() {
		return VWMLResourceHostManagerFactory.hostManagerInstance().requestInterceptorsRepo();
	}

	/**
	 * Repository's initialization steps
	 */
	public void init() {
		repo = VWMLResourceHostManagerFactory.hostManagerInstance().requestInterceptorsRepoContainer();
	}
	
	/**
	 * Repository's clearing steps
	 */
	public void done() {
		repo.clear();
		repo = null;
	}

	/**
	 * Adds interceptor
	 * @param interceptor
	 */
	public void addInterceptor(VWMLInterceptor interceptor) {
		if (interceptor != null && repo != null) {
			repo.put(interceptor.key(), interceptor);
		}
	}
	
	/**
	 * Removes interceptor
	 * @param interceptor
	 */
	public void removeInterceptor(VWMLInterceptor interceptor) {
		if (interceptor != null && repo != null) {
			repo.remove(interceptor.key());
		}
	}
	
	/**
	 * Gets interceptor
	 * @param interceptor
	 */
	public VWMLInterceptor getInterceptor(VWMLEntity intercept, VWMLEntity trigger) {
		VWMLInterceptor askedInterceptor = null;
		if (intercept != null && repo != null) {
			VWMLInterceptor i = new VWMLInterceptor();
			i.setIntercept(intercept);
			i.setTrigger(trigger);
			askedInterceptor = repo.get(i.key());
		}
		return askedInterceptor;
	}
}
