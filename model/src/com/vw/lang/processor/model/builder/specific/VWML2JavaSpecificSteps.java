package com.vw.lang.processor.model.builder.specific;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.apache.log4j.Logger;

import com.vw.lang.processor.model.builder.VWML2TargetSpecificSteps;
import com.vw.lang.processor.model.builder.VWMLModelBuilder;
import com.vw.lang.processor.model.builder.specific.main.VWML2JavaModulesMainStateBuilder;
import com.vw.lang.processor.model.builder.specific.tests.VWML2JavaModulesTestDynamicStateBuilder;
import com.vw.lang.processor.model.builder.specific.tests.VWML2JavaModulesTestInitialStateBuilder;
import com.vw.lang.sink.ICodeGenerator.StartModuleProps;
import com.vw.lang.sink.java.code.JavaCodeGenerator.JavaModuleStartProps;

public class VWML2JavaSpecificSteps extends VWML2TargetSpecificSteps {

	private Logger logger = Logger.getLogger(VWML2JavaSpecificSteps.class);
	
	public static abstract class JavaStep extends Step {
		public void correctPoperties(StartModuleProps props) {
			if (((JavaModuleStartProps)props).getDate() == null) {
				DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				Date date = new Date();				
				((JavaModuleStartProps)props).setDate(dateFormat.format(date));
			}
		}
	}

	/**
	 * Nothing to do, since sources are not generated. The scan is used by IDE in for run-time updates
	 * @param codeGeneratorName
	 * @param props
	 * @throws Exception
	 */
	public static class ScanStep extends JavaStep {
		public void step(VWML2TargetSpecificSteps stepProcessor, String codeGeneratorName, StartModuleProps props) throws Exception {
		}
	}
	
	/**
	 * Integrates sink's sources with generated sources (VWML -> targetLanguage)
	 * @param codeGeneratorName
	 * @param props
	 * @throws Exception
	 */
	public static class SourceStep extends JavaStep {
		public void step(VWML2TargetSpecificSteps stepProcessor, String codeGeneratorName, StartModuleProps props) throws Exception {
			// copies and unpacks sink's jar, generates necessary source code, etc...
			((VWML2JavaSpecificSteps)stepProcessor).setupSinkSources(codeGeneratorName, props);
		}
	}

	/**
	 * Adds POM to sources; allows to compile
	 * @param codeGeneratorName
	 * @param props
	 * @throws Exception
	 */
	public static class PomStep extends JavaStep {
		public void step(VWML2TargetSpecificSteps stepProcessor, String codeGeneratorName, StartModuleProps props) throws Exception {
			new SourceStep().step(stepProcessor, codeGeneratorName, props);
			((VWML2JavaSpecificSteps)stepProcessor).processSinkPom(codeGeneratorName, props);
		}
	}

	/**
	 * Compiles generated code
	 * @param codeGeneratorName
	 * @param props
	 * @throws Exception
	 */
	public static class CompileStep extends JavaStep {
		public void step(VWML2TargetSpecificSteps stepProcessor, String codeGeneratorName, StartModuleProps props) throws Exception {
			new PomStep().step(stepProcessor, codeGeneratorName, props);
			((VWML2JavaSpecificSteps)stepProcessor).runMaven(codeGeneratorName, props);
		}
	}

	/**
	 * Tests compiled code
	 * @param codeGeneratorName
	 * @param props
	 * @throws Exception
	 */
	public static class TestStep extends JavaStep {
		public void step(VWML2TargetSpecificSteps stepProcessor, String codeGeneratorName, StartModuleProps props) throws Exception {
			correctPoperties(props);
			// builds {unit, functional}-test source files
			new VWML2JavaModulesTestInitialStateBuilder().build((JavaModuleStartProps)props);
			new VWML2JavaModulesTestDynamicStateBuilder().build((JavaModuleStartProps)props);
			// compiles and runs maven as test
			new CompileStep().step(stepProcessor, codeGeneratorName, props);
			((VWML2JavaSpecificSteps)stepProcessor).runMavenAsTest(codeGeneratorName, props);
		}
	}

	/**
	 * Generates main entry point of the project
	 * @param codeGeneratorName
	 * @param props
	 * @throws Exception
	 */
	public static class MainStep extends JavaStep {
		public void step(VWML2TargetSpecificSteps stepProcessor, String codeGeneratorName, StartModuleProps props) throws Exception {
			correctPoperties(props);
			// builds {unit, functional}-test source files
			new VWML2JavaModulesTestInitialStateBuilder().build((JavaModuleStartProps)props);
			new VWML2JavaModulesMainStateBuilder().build((JavaModuleStartProps)props);
			// compiles and runs maven as test
			new CompileStep().step(stepProcessor, codeGeneratorName, props);
			((VWML2JavaSpecificSteps)stepProcessor).runMavenAsTest(codeGeneratorName, props);
		}
	}

	private VWMLModelBuilder modelBuilder = null;
	
	public VWML2JavaSpecificSteps(VWMLModelBuilder modelBuilder) {
		this.modelBuilder = modelBuilder;
	}
	
	private void setupSinkSources(String codeGeneratorName, StartModuleProps props) throws Exception {
		String jars[] = {
				"VWMLSink-" + codeGeneratorName + "-sources.jar",
				"VWMLCommon-sources.jar"
				};
		for(String jar : jars) {
			processSinkJar(jar, props);
		}
		processInterpreterBridge();
	}
	
	private void processInterpreterBridge() throws Exception {
		VWML2JavaInterpreterBridge bridge = new VWML2JavaInterpreterBridge();
		bridge.setModelBuilder(modelBuilder);
		bridge.build();
	}
	
	private void processSinkJar(String jarName, StartModuleProps props) throws Exception {
		JavaModuleStartProps jprops = (JavaModuleStartProps)props;
		String resourceName = "sinks/" + jarName;
		String resourceDestPath = jprops.getSrcPath() + "/" + jarName;
		copyResourceTo(resourceName, resourceDestPath);
		if (logger.isInfoEnabled()) {
			logger.info("resource '" + resourceName + "'; unpacking...");
		}
		File jar = new File(resourceDestPath);
		unJar(jar, new File(jprops.getSrcPath()));
		jar.delete();
		if (logger.isInfoEnabled()) {
			logger.info("resource '" + resourceName + "' unpacked");
		}
	}
	
	private void processSinkPom(String codeGeneratorName, StartModuleProps props) throws Exception {
		JavaModuleStartProps jprops = (JavaModuleStartProps)props;
		String resourceDestPath = jprops.getSrcPath() + "/pom.orig";
		copyResourceTo("sinks/pom.xml", resourceDestPath);
		String relatedResources[] = {"/../res", "/../log"};
		for(String res : relatedResources) {
			String rPath = jprops.getSrcPath() + res;
			File f = new File(rPath);
			if (!f.exists()) {
				f.mkdir();
			}
		}
		copyResourceTo("sinks/log4j.project", jprops.getSrcPath() + "/../res/log4j.project");
		File fLog = new File(jprops.getSrcPath() + "/../res/log4j.project");
		String logFilePath = jprops.getSrcPath() + "/../log/" + getProjectName(jprops) + ".log";
		logFilePath = logFilePath.replaceAll("\\\\", "/");
		replace(new String[] {"-x-"}, new String[] {logFilePath}, fLog, new File(jprops.getSrcPath() + "/../res/log4j.xml"));
		fLog.delete();
		String pomAddonPath = props.getProperty(VWMLModelBuilder.ADDONS.INTEGRATIONPOM.toValue());
		String pomAddonContent = "";
		if (pomAddonPath != null) {
			pomAddonContent = file2Buf(pomAddonPath);
		}
		File fPom = new File(resourceDestPath);
		replace(new String[] {"-x-", "-y-"}, new String[] {getProjectName(jprops), pomAddonContent}, fPom, new File(jprops.getSrcPath() + "/../pom.xml"));
		fPom.delete();
		if (logger.isInfoEnabled()) {
			logger.info(jprops.getSrcPath() + "/pom.xml processed - OK");
		}
	}

	private void runMaven(String codeGeneratorName, StartModuleProps props) throws Exception {
		JavaModuleStartProps jprops = (JavaModuleStartProps)props;
		String pomFullPath = jprops.getSrcPath() + "/..";
		String runMaven = null;
		if (isWindows()) {
			runMaven = "cmd /c start/wait cmd.exe /K \"cd " + pomFullPath + " && mvn clean package && exit\"";
		}
		else
		if (isLinux() || isMac()) {
			runMaven = "bash -c \"cd " + pomFullPath + " && mvn clean package && exit\"";
		}
		else {
			throw new Exception("unsupported os '" + getOsName() + "'");
		}
		runMavenCommand(runMaven);
	}

	private void runMavenAsTest(String codeGeneratorName, StartModuleProps props) throws Exception {
		List<String> tests = new ArrayList<String>();
		JavaModuleStartProps jprops = (JavaModuleStartProps)props;
		String testModeAsStr = props.getProperty(VWMLModelBuilder.s_TestModeProp);
		VWMLModelBuilder.TEST_MODE testMode = VWMLModelBuilder.TEST_MODE.getDefault();
		if (testModeAsStr != null) {
			testMode = VWMLModelBuilder.TEST_MODE.fromValue(testModeAsStr);
		}
		if (testMode == VWMLModelBuilder.TEST_MODE.STATIC) {
			tests.add(buildMavenTestCommand(jprops, "VWML2JavaTestInitialState"));
		}
		else
		if (testMode == VWMLModelBuilder.TEST_MODE.DYNAMIC) {
			tests.add(buildMavenTestCommand(jprops, "VWML2JavaTestDynamicState"));
		}
		else
		if (testMode == VWMLModelBuilder.TEST_MODE.ALL) {
			tests.add(buildMavenTestCommand(jprops, "VWML2JavaTestInitialState"));
			tests.add(buildMavenTestCommand(jprops, "VWML2JavaTestDynamicState"));
		}
		for(String testCommand : tests) {
			runMavenCommand(testCommand);
		}
	}

	private String buildMavenTestCommand(JavaModuleStartProps jprops, String testCommand) throws Exception {
		String pomFullPath = jprops.getSrcPath() + "/..";
		String runMaven = null;
		if (isWindows()) {
			runMaven = "cmd /c start/wait cmd.exe /K \"cd " + pomFullPath + " && mvn -Dtest=" + testCommand + " -DforkCount=0 test && exit\"";
		}
		else
		if (isLinux() || isMac()) {
			runMaven = "bash -c \"cd " + pomFullPath + " && mvn -Dtest=" + testCommand + " test && exit\"";
		}
		else {
			throw new Exception("unsupported os '" + getOsName() + "'");
		}
		if (logger.isInfoEnabled()) {
			logger.info("test to be run: " + runMaven);
		}
		return runMaven;
	}
	
	private void runMavenCommand(String command) throws Exception {
		if (logger.isInfoEnabled()) {
			logger.info("Executing: " + command);
		}
		Process p = Runtime.getRuntime().exec(command);
		p.waitFor();
		if (logger.isInfoEnabled()) {
			logger.info("Executed: " + command);
		}
	}
	
	private void copyResourceTo(String resourceName, String resourceDestPath) throws Exception {
		InputStream is = this.getClass().getClassLoader().getResourceAsStream(resourceName);
		if (logger.isDebugEnabled()) {
			logger.debug("resource '" + resourceName + "'; stream '" + is + "'");
		}
		if (is == null) {
			throw new Exception("resource '" + resourceName + "' was not found");
		}
		if (logger.isInfoEnabled()) {
			logger.info("resource '" + resourceName + "' found and is copied to '" + resourceDestPath + "'");
		}
		FileOutputStream fos = new FileOutputStream(resourceDestPath);
		try {
			copy(is, fos);
		} finally {
	        fos.flush();
	        fos.close();
	        is.close();
		}
		if (logger.isInfoEnabled()) {
			logger.info("resource '" + resourceName + "' was copied to '" + resourceDestPath + "'");
		}
	}

	public static void replace(String[] oldstring, String[] newstring, File in, File out) throws IOException {
		if (oldstring.length != newstring.length) {
			throw new IOException("lookup and replacement string arrayes must have the same size");
		}
		BufferedReader reader = new BufferedReader(new FileReader(in));
		PrintWriter writer = new PrintWriter(new FileWriter(out));
		String line = null;
		while ((line = reader.readLine()) != null) {
			for(int i = 0; i < oldstring.length; i++) {
				line = line.replaceAll(oldstring[i], newstring[i]);
			}
			writer.println(line);
		}
	    reader.close();
	    writer.close();
	}	
	/**
	   * Unpack matching files from a jar. Entries inside the jar that do
	   * not match the given pattern will be skipped.
	   *
	   * @param jarFile the .jar file to unpack
	   * @param toDir the destination directory into which to unpack the jar
	   */	
	private static void unJar(File jarFile, File toDir) throws Exception {
		JarFile jar = new JarFile(jarFile);
		try {
			Enumeration<JarEntry> entries = jar.entries();
			while (entries.hasMoreElements()) {
				JarEntry entry = (JarEntry)entries.nextElement();
				if (!entry.isDirectory() && (!entry.getName().contains("META-INF"))) {
					InputStream in = jar.getInputStream(entry);
					try {
						File file = new File(toDir, entry.getName());
						ensureDirectory(file.getParentFile());
						OutputStream out = new FileOutputStream(file);
						try {
							copy(in, out);
						} finally {
							out.close();
						}
					} finally {
						in.close();
					}
				}
			}
		} finally {
			jar.close();
		}
	}
	
	/**
	   * Ensure the existence of a given directory.
	   *
	   * @throws IOException if it cannot be created and does not already exist
	   */
	  private static void ensureDirectory(File dir) throws IOException {
		  if (!dir.mkdirs() && !dir.isDirectory()) {
			  throw new IOException("Mkdirs failed to create " + dir.toString());
		  }
	  }
	
	  private static void copy(InputStream is, OutputStream os) throws Exception {
			int len = 0;
			byte[] buffer = new byte[2048];
			while((len = is.read(buffer)) > 0) {
			    os.write(buffer, 0, len);   
			}
	  }
	  
	  private String getOsName() {
		  return System.getProperty("os.name").toLowerCase();
	  }
	  
	  private boolean isWindows() {
		  return (getOsName().indexOf("win") >= 0);
	  }
	  
	  private boolean isLinux() {
		  String os = getOsName();
		  return (os.indexOf("nix") >= 0 || os.indexOf("nux") >= 0 || os.indexOf("aix") > 0);
	  }
	  
	  private boolean isMac() {
		  return (getOsName().indexOf("mac") >= 0);
	  }
	  
	  private String getProjectName(JavaModuleStartProps jprops) {
		  return (jprops.getProjectName() == null) ? jprops.getModuleName() : jprops.getProjectName();
	  }
	  
	  private String file2Buf(String path) {
		  try {
			  int l = (int)new File(path).length();
			  byte[] buf = new byte[l];
			  FileInputStream fis = new FileInputStream(path);
			  fis.read(buf);
			  return new String(buf);
		  } catch (Exception e) {
			  e.printStackTrace();
		  }
		  return null;
	  }
}
