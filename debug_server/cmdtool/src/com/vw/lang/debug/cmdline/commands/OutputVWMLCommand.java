package com.vw.lang.debug.cmdline.commands;

import java.util.HashMap;
import java.util.Map;

import net.dharwin.common.tools.cli.api.Command;
import net.dharwin.common.tools.cli.api.CommandResult;
import net.dharwin.common.tools.cli.api.annotations.CLICommand;

import com.beust.jcommander.Parameter;
import com.vw.lang.debug.cmdline.VWMLDebugServerCmdLineContext;
import com.vw.lang.debug.devices.VWMLDebugServerRedirectDevice;
import com.vw.lang.debug.devices.console.VWMLDebugServerConsoleDevice;

/**
 * Redirects output to given descriptor
 * @author Oleg
 *
 */
@CLICommand(name="output", description="redirects output received from VWML project to specified descriptor")
public class OutputVWMLCommand extends Command<VWMLDebugServerCmdLineContext>{
	
	@Parameter(names={"-d", "--descriptor"}, description="file, net, console")
	private String descriptorName;
	@Parameter(names={"-url"}, description="URL, not used for console")
	private String descriptorUrl;

	@SuppressWarnings("serial")
	private static Map<String, VWMLDebugServerRedirectDevice> s_availableDevices = new HashMap<String, VWMLDebugServerRedirectDevice>() {
		{
			put("file", null);
			put("net", null);
			put("console", new VWMLDebugServerConsoleDevice());
		}
		
	};
	
	public String getDescriptorName() {
		return descriptorName;
	}

	public void setDescriptorName(String descriptorName) {
		this.descriptorName = descriptorName;
	}

	public String getDescriptorUrl() {
		return descriptorUrl;
	}

	public void setDescriptorUrl(String descriptorUrl) {
		this.descriptorUrl = descriptorUrl;
	}

	@Override
	protected CommandResult innerExecute(VWMLDebugServerCmdLineContext ctx) {
		CommandResult r = CommandResult.OK;
		VWMLDebugServerRedirectDevice dev = s_availableDevices.get(getDescriptorName());
		if (dev == null) {
			r = CommandResult.BAD_ARGS;
			System.err.println("Device identified by descriptor '" + getDescriptorName() + "' isn't supported");
		}
		else {
			dev.setUrl(getDescriptorUrl());
			if (!dev.checkParams()) {
				r = CommandResult.BAD_ARGS;
				System.err.println("Device identified by descriptor '" + getDescriptorName() + "' got invalid parameters");
			}
		}
		return r;
	}
}
