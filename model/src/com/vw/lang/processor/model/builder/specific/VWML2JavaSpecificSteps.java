package com.vw.lang.processor.model.builder.specific;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.apache.log4j.Logger;

import com.vw.lang.processor.model.builder.VWML2TargetSpecificSteps;
import com.vw.lang.processor.model.builder.specific.tests.VWML2JavaModulesTestDynamicStateBuilder;
import com.vw.lang.processor.model.builder.specific.tests.VWML2JavaModulesTestInitialStateBuilder;
import com.vw.lang.sink.ICodeGenerator.StartModuleProps;
import com.vw.lang.sink.java.code.JavaCodeGenerator.JavaModuleStartProps;

public class VWML2JavaSpecificSteps extends VWML2TargetSpecificSteps {

	private Logger logger = Logger.getLogger(VWML2JavaSpecificSteps.class);
	
	/**
	 * Integrates sink's sources with generated sources (VWML -> targetLanguage)
	 * @param codeGeneratorName
	 * @param props
	 * @throws Exception
	 */
	public static class SourceStep implements IStep {
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
	public static class PomStep implements IStep {
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
	public static class CompileStep implements IStep {
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
	public static class TestStep implements IStep {
		public void step(VWML2TargetSpecificSteps stepProcessor, String codeGeneratorName, StartModuleProps props) throws Exception {
			// builds {unit, functional}-test source files
			new VWML2JavaModulesTestInitialStateBuilder().build((JavaModuleStartProps)props);
			new VWML2JavaModulesTestDynamicStateBuilder().build((JavaModuleStartProps)props);
			// compiles and runs maven as test
			new CompileStep().step(stepProcessor, codeGeneratorName, props);
			((VWML2JavaSpecificSteps)stepProcessor).runMavenAsTest(codeGeneratorName, props);
		}
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
		new VWML2JavaInterpreterBridge().build();
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
		replace("-x-", logFilePath, fLog, new File(jprops.getSrcPath() + "/../res/log4j.xml"));
		fLog.delete();		
		File fPom = new File(resourceDestPath);
		replace("-x-", getProjectName(jprops), fPom, new File(jprops.getSrcPath() + "/../pom.xml"));
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
			runMaven = "cmd /c start cmd.exe /K \"cd " + pomFullPath + " && mvn clean package && exit\"";
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
		JavaModuleStartProps jprops = (JavaModuleStartProps)props;
		String pomFullPath = jprops.getSrcPath() + "/..";
		String runMaven = null;
		if (isWindows()) {
			runMaven = "cmd /c start cmd.exe /K \"cd " + pomFullPath + " && mvn -Dtest=VWML2JavaTestInitialState test && exit\"";
		}
		else
		if (isLinux() || isMac()) {
			runMaven = "bash -c \"cd " + pomFullPath + " && mvn -Dtest=VWML2JavaTestInitialState test && exit\"";
		}
		else {
			throw new Exception("unsupported os '" + getOsName() + "'");
		}
		runMavenCommand(runMaven);
	}
	
	private void runMavenCommand(String command) throws Exception {
		Process p = Runtime.getRuntime().exec(command);
		InputStream isErr = p.getErrorStream();
		if (isErr != null) {
			String err = inputStreamToString(isErr);
			if (err != null && err.length() > 0) {
				logger.error(err);
			}
		}
		InputStream os = p.getInputStream();
		if (os != null && logger.isInfoEnabled()) {
			String output = inputStreamToString(os);
			if (output != null && output.length() > 0) {
				logger.info(output);
			}
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

	public static void replace(String oldstring, String newstring, File in, File out) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(in));
		PrintWriter writer = new PrintWriter(new FileWriter(out));
		String line = null;
		while ((line = reader.readLine()) != null) {
		    writer.println(line.replaceAll(oldstring, newstring));
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
	  
	  private String inputStreamToString(InputStream in) throws Exception {
		  InputStreamReader is = new InputStreamReader(in);
		  StringBuilder sb = new StringBuilder();
		  BufferedReader br = new BufferedReader(is);
		  String read = br.readLine();
		  while(read != null) {
		      sb.append(read);
		      read = br.readLine();
		  }
		  return sb.toString();
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
}
