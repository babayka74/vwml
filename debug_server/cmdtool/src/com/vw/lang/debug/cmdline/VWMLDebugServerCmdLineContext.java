package com.vw.lang.debug.cmdline;

import java.io.File;

import net.dharwin.common.tools.cli.api.CLIContext;

import com.vw.lang.debug.devices.VWMLDebugServerRedirectDevice;
import com.vw.lang.debug.devices.console.VWMLDebugServerConsoleDevice;

/**
 * Command line's context
 * @author Oleg
 *
 */
public class VWMLDebugServerCmdLineContext extends CLIContext {

	/**
	 * All data received from debugged project is redirected to this device
	 */
	private VWMLDebugServerRedirectDevice redirectDevice = new VWMLDebugServerConsoleDevice();
	
	public VWMLDebugServerCmdLineContext(VWMLDebugServerCmdLine app) {
		super(app);
	}

	public VWMLDebugServerRedirectDevice getRedirectDevice() {
		return redirectDevice;
	}

	public void setRedirectDevice(VWMLDebugServerRedirectDevice redirectDevice) {
		this.redirectDevice = redirectDevice;
	}
	
	 @Override
     protected String getEmbeddedPropertiesFilename() {
		return "/embedded_vwmlcmdtool.properties";
     }
	 
	 protected File getExternalPropertiesFile() {
	 	return null;
	 }
 }
