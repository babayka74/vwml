package com.vw.lang.processor.model.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import com.vw.lang.processor.model.builder.VWMLModelBuilder;

/**
 * VWML's processor main class
 * @author ogibayev
 *
 */
public final class VWML {
	
	public static abstract class Operation {
		
		public enum ARGS {
			VWMLFILE
		}
		
		public void process(VWMLArgs args) throws Exception {
			run(args);
			finalProcedure();
		}
		
		public void run(VWMLArgs args) throws Exception {
			String filePath = args.getArguments().get(Operation.ARGS.VWMLFILE.ordinal());
			VWMLModelBuilder.instance().compile(filePath, null);
		}
		
		/**
		 * Called upon operation's final step
		 */
		protected void finalProcedure() throws Exception {
			VWMLModelBuilder.instance().finalProcedure(VWMLModelBuilder.instance().getModProps());
		}
	}
	
	/**
	 * Compiles VWML sources into given language only
	 * @author ogibayev
	 *
	 */
	public static class Sources extends Operation {

		private Logger logger = Logger.getLogger(Sources.class);
		
		@Override
		public void run(VWMLArgs args) throws Exception {
			VWMLModelBuilder.instance().setBuildSteps(VWMLModelBuilder.BUILD_STEPS.SOURCE);
			super.run(args);
		}
		
	}

	/**
	 * Builds project from generated sources
	 * @author ogibayev
	 *
	 */
	public static class Project extends Operation {

		private Logger logger = Logger.getLogger(Project.class);
		
		@Override
		public void run(VWMLArgs args) throws Exception {
			VWMLModelBuilder.instance().setBuildSteps(VWMLModelBuilder.BUILD_STEPS.POM);
			super.run(args);
		}
	}

	/**
	 * Compiles VWML sources into given language and builds test executable project; resut of running project
	 * is starting static picture of virtual world which was described by VWML project; for debug purposes only
	 * @author ogibayev
	 *
	 */
	public static class Compile extends Operation {

		private Logger logger = Logger.getLogger(Compile.class);
		
		@Override
		public void run(VWMLArgs args) throws Exception {
			VWMLModelBuilder.instance().setBuildSteps(VWMLModelBuilder.BUILD_STEPS.COMPILE);
			super.run(args);
		}
	}

	/**
	 * Runs compiled project
	 * @author ogibayev
	 *
	 */
	public static class Test extends Operation {

		private Logger logger = Logger.getLogger(Test.class);
		
		@Override
		public void run(VWMLArgs args) throws Exception {
			VWMLModelBuilder.instance().setBuildSteps(VWMLModelBuilder.BUILD_STEPS.TEST);
			super.run(args);
		}
	}
	
	/**
	 * Command line arguments
	 * @author ogibayev
	 *
	 */
	public static class VWMLArgs {
		@Option(name="-m", usage="execution mode {source | project | compile | test};\r\nsource - generates source code from VWML only;\r\nproject - generates test executable project; used in order to test VW's (virtual world) start state - effective in case if visualizer is used;\r\ncompile - compiles project;\r\ntest - runs compiled project")
		private String mode;
		
		 // receives other command line parameters than options
	    @Argument
	    private List<String> arguments = new ArrayList<String>();

		public String getMode() {
			return mode;
		}

		public void setMode(String mode) {
			this.mode = mode;
		}

		public List<String> getArguments() {
			return arguments;
		}

		public void setArguments(List<String> arguments) {
			this.arguments = arguments;
		}

		@Override
		public String toString() {
			return "VWMLArgs [mode=" + mode + ", arguments=" + arguments + "]";
		}
	}
	
	private static Map<String, Operation> s_opCodes = new HashMap<String, Operation>() {
		{put("source",  new Sources());}
		{put("project", new Project());}
		{put("compile", new Compile());}
		{put("test",    new Test());   }
	};
	
	private static Logger logger = Logger.getLogger(VWML.class);
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		VWMLArgs vwmlArgs = new VWMLArgs();
		CmdLineParser cmdParser = new CmdLineParser(vwmlArgs);
		cmdParser.setUsageWidth(80);
		try {
			cmdParser.parseArgument(args);
			if (logger.isInfoEnabled()) {
				logger.info("builder started; actual arguments are '" + vwmlArgs + "'");
			}
			Operation op = s_opCodes.get(vwmlArgs.getMode());
			if (op != null) {
				op.process(vwmlArgs);
			}
			else {
				logger.error("invalid mode '" + vwmlArgs.getMode() + "'; valid are 'source | test'");
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

}
