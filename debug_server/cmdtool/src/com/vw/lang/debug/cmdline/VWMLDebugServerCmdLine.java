package com.vw.lang.debug.cmdline;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import net.dharwin.common.tools.cli.api.CLIContext;
import net.dharwin.common.tools.cli.api.CommandLineApplication;
import net.dharwin.common.tools.cli.api.exceptions.CLIInitException;

/**
 * Debug server command line tool
 * @author Oleg
 *
 */
public class VWMLDebugServerCmdLine extends CommandLineApplication<VWMLDebugServerCmdLineContext> {
	
	public VWMLDebugServerCmdLine() throws CLIInitException {
		super();
	}

	@Override
	public void start() {
		displayPrompt();
		super.start();
	}
	
	@Override
	protected void shutdown() {
		System.out.println("Shutting down VWML Debug Server client.");
	}
	
	@Override
	protected void processInputLine(String inputLine) {
		super.processInputLine(inputLine);
		displayPrompt();
	}
	
	@Override
    protected CLIContext createContext() {
        return new VWMLDebugServerCmdLineContext(this);
    }
	
	protected void displayPrompt() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();		
		System.out.println("VWML Debug Server client; (C) Win-Interactive LLC; Local time > " + dateFormat.format(date));
	}
}
