package com.vw.lang.processor.model.main;

import java.util.ArrayList;
import java.util.List;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.Option;

/**
 * VWML's processor main class
 * @author ogibayev
 *
 */
public final class VWML {

	/**
	 * Command line arguments
	 * @author ogibayev
	 *
	 */
	public static class VWMLArgs {
		@Option(name="-m", usage="execution mode {source | exe};\r\nsource - generates source code from VWML only;\r\nexe - generates test executable binary; used in order to test VW's (virtual world) start state - effective in case if visualizer is used")
		private String mode;
		
		 // receives other command line parameters than options
	    @Argument
	    private List<String> arguments = new ArrayList<String>();
		
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
