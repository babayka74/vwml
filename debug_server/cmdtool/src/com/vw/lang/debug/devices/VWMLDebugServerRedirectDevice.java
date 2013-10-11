package com.vw.lang.debug.devices;

/**
 * Root class for all supported devices. Device is used for redirecting incoming output from debugged application 
 * @author Oleg
 *
 */
public abstract class VWMLDebugServerRedirectDevice {
	private String url;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	/**
	 * Returns 'true' in case if device's parameters are well-formed
	 * @return
	 */
	public abstract boolean checkParams();
}
