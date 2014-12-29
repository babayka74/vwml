package com.vw.lang.processor.model.main;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import com.vw.lang.grammar.preprocessor.VWMLPreprocessor;
import com.vw.lang.processor.model.builder.VWMLModelBuilder;
import com.vw.lang.processor.model.sink.CompilationSink;
import com.vw.lang.sink.InterpretationProps;
import com.vw.lang.sink.entity.InterpretationOfUndefinedEntityStrategyId;

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

		private CompilationSink compilationSink = null;
		private VWMLModelBuilder modelBuilder = null;
		
		public VWMLModelBuilder getModelBuilder() {
			return modelBuilder;
		}

		public void setModelBuilder(VWMLModelBuilder modelBuilder) {
			this.modelBuilder = modelBuilder;
		}

		public CompilationSink getCompilationSink() {
			return compilationSink;
		}

		public void setCompilationSink(CompilationSink compilationSink) {
			this.compilationSink = compilationSink;
		}

		public void process(VWMLArgs args) throws Exception {
			run(args);
			finalProcedure(args);
		}
		
		public void run(VWMLArgs args) throws Exception {
			String filePath = args.getArguments().get(Operation.ARGS.VWMLFILE.ordinal());
			// the module's props are set during compilation phase (see grammar file, term 'filedef')
			modelBuilder.setInterpretationProps(buildInterpretationProps(args));
			modelBuilder.setCompilationSink(compilationSink);
			modelBuilder.compile(filePath);
		}
		
		/**
		 * Called upon operation's final step
		 */
		protected void finalProcedure(VWMLArgs args) throws Exception {
			if (VWMLModelBuilder.BUILD_STEPS.TEST == modelBuilder.getBuildSteps()) {
				String testMode = VWMLModelBuilder.TEST_MODE.STATIC.toValue();
				if (args.getTestMode() != null) {
					testMode = args.getTestMode();
				}
				modelBuilder.getProjectProps().addProperty(VWMLModelBuilder.s_TestModeProp, testMode);
			}
			boolean processingAddons = true;
			if (compilationSink != null && compilationSink.getMode() == CompilationSink.Mode.SCAN_ONLY) {
				processingAddons = false;
			}
			if (processingAddons) {
				processAddons(args);
			}
			modelBuilder.finalProcedure(modelBuilder.getProjectProps());
		}
		
		private InterpretationProps buildInterpretationProps(VWMLArgs args) throws Exception {
			InterpretationProps ip = new InterpretationProps();
			if (args.getDebug() != null && args.getDebug().equals("active")) {
				ip.setActivateDebugger(true);
			}
			if (args.getIncludeDebugInfo() != null && args.getIncludeDebugInfo().equals("true")){
				ip.setIncludeDebugInfo(true);
			}
			if (args.getDirectives().size() != 0) {
				for(String directive : args.getDirectives()) {
					String[] directiveStruct = directive.split("=");
					if (directiveStruct != null) {
						if (directiveStruct.length > 1) {
							VWMLPreprocessor.addDirective(directiveStruct[0], directiveStruct[1]);
						}
						else {
							VWMLPreprocessor.addDirective(directiveStruct[0], null);
						}
					}
				}
			}
			ip.setInterpretationOfUndefinedEntityStrategyId(InterpretationOfUndefinedEntityStrategyId.fromValue(args.getEntityInterpretationStrategy()));
			if (args.getInterpreterProps() != null) {
				Properties props = new Properties();
				props.load(new FileInputStream(args.getInterpreterProps()));
				ip.setDynamicProps(props);
				if (logger.isInfoEnabled()) {
					logger.info("Interpreter's properties '" + ip + "'");
				}
			}
			return ip;
		}
		
		private void processAddons(VWMLArgs args) {
			if (args.getAddons() != null) {
				String[] addons = args.getAddons().split(",");
				if (addons != null) {
					for(String addon : addons) {
						String[] prop = addon.split("=");
						if (prop != null && prop.length > 1 && VWMLModelBuilder.ADDONS.fromValue(prop[0]) != VWMLModelBuilder.ADDONS.NONE) {
							modelBuilder.getProjectProps().addProperty(prop[0], prop[1]);
						}
					}
				}
			}
		}
	}

	/**
	 * Scans VWML sources and publishes events according to logic of compilation sink
	 * @author ogibayev
	 *
	 */
	public static class Scan extends Operation {

		@Override
		public void run(VWMLArgs args) throws Exception {
			getModelBuilder().setBuildSteps(VWMLModelBuilder.BUILD_STEPS.SOURCE);
			super.run(args);
		}
	}
	
	/**
	 * Compiles VWML sources into given language only
	 * @author ogibayev
	 *
	 */
	public static class Sources extends Operation {

		@Override
		public void run(VWMLArgs args) throws Exception {
			getModelBuilder().setBuildSteps(VWMLModelBuilder.BUILD_STEPS.SOURCE);
			super.run(args);
		}
		
	}

	/**
	 * Builds project from generated sources
	 * @author ogibayev
	 *
	 */
	public static class Project extends Operation {

		@Override
		public void run(VWMLArgs args) throws Exception {
			getModelBuilder().setBuildSteps(VWMLModelBuilder.BUILD_STEPS.POM);
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

		@Override
		public void run(VWMLArgs args) throws Exception {
			getModelBuilder().setBuildSteps(VWMLModelBuilder.BUILD_STEPS.COMPILE);
			super.run(args);
		}
	}

	/**
	 * Runs compiled project
	 * @author ogibayev
	 *
	 */
	public static class Test extends Operation {

		@Override
		public void run(VWMLArgs args) throws Exception {
			getModelBuilder().setBuildSteps(VWMLModelBuilder.BUILD_STEPS.TEST);
			super.run(args);
		}
	}

	/**
	 * Generates main entry for the project
	 * @author ogibayev
	 *
	 */
	public static class Main extends Operation {

		@Override
		public void run(VWMLArgs args) throws Exception {
			getModelBuilder().setBuildSteps(VWMLModelBuilder.BUILD_STEPS.MAIN);
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
		@Option(name="-m", usage="execution mode {source | project | compile | test | main};\r\nsource - generates source code from VWML only;\r\nproject - generates test executable project; used in order to test VW's (virtual world) start state - effective in case if visualizer is used;\r\ncompile - compiles project;\r\ntest - runs compiled project;\r\nmain - generates project and main entry")
		private String mode;
		@Option(name="-t", usage="test mode {none | static | dynamic | all};\r\nnone - no tests are run;\r\nstatic test is run only (checks linkages only);\r\ndynamic - executes dynamic tests;\r\nall - static and dynamic tests are run")
		private String testMode;
		@Option(name="-d", usage="active; debug option activated")
		private String debug;
		@Option(name="-entity", usage="entity generation/checking options {strict, ue_im1, ue_im2, ue_im3};\r\nstrict - if undefined simple entity found - exception is thrown;\r\nue_im1 - if undefined simple entity found - it is interpreted as empty complex entity ();\r\nue_im2 - if undefined simple entity found - it is interpreted as 'nil' entity\r\nue_im3 - if undefined simple entity found - it is interpreted as is")
		private String entityInterpretationStrategy = new String(InterpretationOfUndefinedEntityStrategyId.STRICT.toValue());
		@Option(name="-interpreter", usage="path to property file")
		private String interpreterProps = null;
		@Option(name="-addons", usage="comma separated properties list")
		private String addons = null;
		@Option(name="-p", usage="preprocessor's directives in format \"<directive>{=<value>}, ...\"")
		private String preprocessorDirective = null;
		@Option(name="-debuginfo", usage="includes debug info in case if true specified")
		private String includeDebugInfo = null;
		
		 // receives other command line parameters than options
	    @Argument
	    private List<String> arguments = new ArrayList<String>();
	    private List<String> directives = new ArrayList<String>();

		public String getAddons() {
			return addons;
		}

		public void setAddons(String addons) {
			this.addons = addons;
		}

		public String getMode() {
			return mode;
		}

		public void setMode(String mode) {
			this.mode = mode;
		}

		public String getTestMode() {
			return testMode;
		}

		public void setTestMode(String testMode) {
			this.testMode = testMode;
		}

		public String getDebug() {
			return debug;
		}

		public void setDebug(String debug) {
			this.debug = debug;
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

		public String getPreprocessorDirective() {
			return preprocessorDirective;
		}

		public void setPreprocessorDirective(String preprocessorDirective) {
			this.preprocessorDirective = preprocessorDirective;
		}
		
		public List<String> getDirectives() {
			return directives;
		}

		public List<String> getArguments() {
			return arguments;
		}

		public void setArguments(List<String> arguments) {
			this.arguments = arguments;
		}

		public String getIncludeDebugInfo() {
			return includeDebugInfo;
		}

		public void setIncludeDebugInfo(String includeDebugInfo) {
			this.includeDebugInfo = includeDebugInfo;
		}

		@Override
		public String toString() {
			return "VWMLArgs [mode=" + mode + ", testMode=" + testMode
					+ ", debug=" + debug + ", entityInterpretationStrategy="
					+ entityInterpretationStrategy + ", interpreterProps="
					+ interpreterProps + ", addons=" + addons
					+ ", includeDebugInfo=" + includeDebugInfo
					+ ", arguments=" + arguments + ", directives=" + directives
					+ "]";
		}
	}
	
	@SuppressWarnings("serial")
	private Map<String, Operation> opCodes = new HashMap<String, Operation>() {
		{put("source",  new Sources());}
		{put("project", new Project());}
		{put("compile", new Compile());}
		{put("test",    new Test());   }
		{put("main",    new Main());   }
	};
	
	private static Logger logger = Logger.getLogger(VWML.class);
	private VWMLModelBuilder vwmlModelBuilder = new VWMLModelBuilder();
	private CompilationSink compilationSink = null;
	
	public Map<String, Operation> getOpCodes() {
		return opCodes;
	}
	
	public CompilationSink getCompilationSink() {
		return compilationSink;
	}

	public void setCompilationSink(CompilationSink compilationSink) {
		this.compilationSink = compilationSink;
	}

	public void init() throws Exception {
		vwmlModelBuilder.init();
	}
	
	/**
	 * Decodes and handles input arguments
	 * @param args
	 */
	public void handleArgs(String[] args) {
		VWMLArgs vwmlArgs = new VWMLArgs();
		CmdLineParser cmdParser = new CmdLineParser(vwmlArgs);
		cmdParser.setUsageWidth(80);
		try {
			cmdParser.parseArgument(args);
			if (vwmlArgs.getPreprocessorDirective() != null) {
				String[] list = vwmlArgs.getPreprocessorDirective().split(",");
				for(String directive : list) {
					vwmlArgs.getDirectives().add(directive);
				}
			}
			if (logger.isInfoEnabled()) {
				logger.info("builder started; actual arguments are '" + vwmlArgs + "'");
			}
			Operation op = getOpCodes().get(vwmlArgs.getMode());
			if (op != null) {
				op.setModelBuilder(vwmlModelBuilder);
				op.process(vwmlArgs);
			}
			else {
				logger.error("invalid mode '" + vwmlArgs.getMode() + "'; valid are 'source | test'");
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		VWML vwml = new VWML();
		try {
			vwml.init();
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		vwml.handleArgs(args);
	}
}
