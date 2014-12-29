package com.win.game.model.fringe.gate.configuration.loader;

/**
 * Implemented by caller when configuration is supplied from another source then file
 * @author Oleg
 *
 */
public abstract class Supplier {
	private ConfigurationLoader cl;
	private String confName;
	
	public ConfigurationLoader getCl() {
		return cl;
	}

	public void setCl(ConfigurationLoader cl) {
		this.cl = cl;
	}

	public String getConfName() {
		return confName;
	}

	public void setConfName(String confName) {
		this.confName = confName;
	}
	
	/**
	 * Returns configuration as complex entity which is presented by String format
	 * @return
	 */
	public abstract String getRawConfigurationAsString();
}
