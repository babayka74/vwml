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
import com.vw.lang.sink.InterpretationProps;
import com.vw.lang.sink.entity.InterpretationOfUndefinedEntityStrategyId;
import com.vw.lang.sink.utils.GeneralUtils;

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
			// the module's props are set during compilation phase (see grammar file, term 'filedef')
			VWMLModelBuilder.instance().setInterpretationProps(buildInterpretationProps(args));
			VWMLModelBuilder.instance().compile(filePath);
		}
		
		/**
		 * Called upon operation's final step
		 */
		protected void finalProcedure() throws Exception {
			VWMLModelBuilder.instance().finalProcedure(VWMLModelBuilder.instance().getProjectProps());
		}
		
		private InterpretationProps buildInterpretationProps(VWMLArgs args) throws Exception {
			InterpretationProps ip = new InterpretationProps();
			ip.setInterpretationOfUndefinedEntityStrategyId(InterpretationOfUndefinedEntityStrategyId.fromValue(args.getEntityInterpretationStrategy()));
			if (args.getInterpreterProps() != null) {
				VWMLInterprterArgs interpreterArgs = new VWMLInterprterArgs();
				CmdLineParser cmdParser = new CmdLineParser(interpreterArgs);
				cmdParser.setUsageWidth(80);
				cmdParser.parseArgument(GeneralUtils.trimQuotes(args.getInterpreterProps()));
				if (interpreterArgs.getPkg() != null && interpreterArgs.getSrcPath() != null) {
					ip.setInterpretersPackage(interpreterArgs.getPkg());
					ip.setInterpretersSrcPath(interpreterArgs.getSrcPath());
					if (logger.isInfoEnabled()) {
						logger.info("Interpreter's properties '" + ip + "'");
					}
				}
			}
			return ip;
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
	 * Defines interpretator's properties, it isn't in usage now
	 * @author ogibayev
	 *
	 */
	public static class VWMLInterprterArgs {
		@Option(name = "-src", usage="absolute path, where interpreter's sources should be located; usually coincides with module's sources")
		private String srcPath;
		@Option(name = "-package", usage="interpreter's java package")
		private String pkg;
		
		public String getSrcPath() {
			return srcPath;
		}
		public void setSrcPath(String srcPath) {
			this.srcPath = srcPath;
		}
		public String getPkg() {
			return pkg;
		}
		public void setPkg(String pkg) {
			this.pkg = pkg;
		}
		@Override
		public String toString() {
			return "VWMLInterprterArgs [srcPath=" + srcPath + ", pkg=" + pkg
					+ "]";
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
		@Option(name="-entity", usage="entity generation/checking options {strict, ue_im1, ue_im2, ue_im3};\r\nstrict - if undefined simple entity found - exception is thrown;\r\nue_im1 - if undefined simple entity found - it is interpreted as empty complex entity ();\r\nue_im2 - if undefined simple entity found - it is interpreted as 'nil' entity\r\nue_im3 - if undefined simple entity found - it is interpreted as is")
		private String entityInterpretationStrategy = new String(InterpretationOfUndefinedEntityStrategyId.STRICT.toValue());
		@Option(name="-interpreter", usage="dquoted interpreter's options (not used now)")
		private String interpreterProps = null;
		
		 // receives other command line parameters than options
	    @Argument
	    private List<String> arguments = new ArrayList<String>();

		public String getMode() {
			return mode;
		}

		public void setMode(String mode) {
			this.mode = mode;
		}

		public String getInterpreterProps() {
			return interpreterProps;
		}

		public void setInterpreterProps(String interpreterProps) {
			this.interpreterProps = interpreterProps;
		}

		public String getEntityInterpretationStrategy() {
			return entityInterpretationStrategy;
		}

		public void setEntityInterpretationStrategy(String entityInterpretationStrategy) {
			this.entityInterpretationStrategy = entityInterpretationStrategy;
		}

		public List<String> getArguments() {
			return arguments;
		}

		public void setArguments(List<String> arguments) {
			this.arguments = arguments;
		}

		@Override
		public String toString() {
			return "VWMLArgs [mode=" + mode + ", entityInterpretationModes="
					+ entityInterpretationStrategy + ", arguments=" + arguments
					+ "]";
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
