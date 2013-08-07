package com.vw.lang.processor.model.builder.specific;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.vw.lang.processor.model.builder.VWML2TargetSpecificSteps;
import com.vw.lang.sink.ICodeGenerator.StartModuleProps;
import com.vw.lang.sink.java.code.JavaCodeGenerator.JavaModuleStartProps;

public class VWML2JavaSpecificSteps extends VWML2TargetSpecificSteps {

	private Logger logger = Logger.getLogger(VWML2JavaSpecificSteps.class);

	public void setupSinkSources(String codeGeneratorName, StartModuleProps props) throws Exception {
		JavaModuleStartProps jprops = (JavaModuleStartProps)props;
		String fileName = "VWMLSink-" + codeGeneratorName + "-sources.jar";
		String resourceName = "sinks/" + fileName;
		if (logger.isInfoEnabled()) {
			logger.info("looking for for resource '" + resourceName + "'");
		}
		InputStream is = this.getClass().getClassLoader().getResourceAsStream(resourceName);
		if (logger.isDebugEnabled()) {
			logger.debug("resource '" + resourceName + "'; stream '" + is + "'");
		}
		if (is == null) {
			throw new Exception("resource '" + resourceName + "' was not found");
		}
		if (logger.isInfoEnabled()) {
			logger.info("resource '" + resourceName + "' found and is going to be copied to '" + jprops.getSrcPath() + "'");
		}
		String resourceDestPath = jprops.getSrcPath() + "/" + fileName;
		FileOutputStream fos = new FileOutputStream(resourceDestPath);
		try {
			copy(is, fos);
		} finally {
	        fos.flush();
	        fos.close();
	        is.close();
		}
		if (logger.isInfoEnabled()) {
			logger.info("resource '" + resourceName + "' copied to '" + resourceDestPath + "'; unpacking...");
		}
		File jar = new File(resourceDestPath);
		unJar(jar, new File(jprops.getSrcPath()));
		jar.delete();
		if (logger.isInfoEnabled()) {
			logger.info("resource '" + resourceName + "' unpacked");
		}
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
	      throw new IOException("Mkdirs failed to create " +
	                            dir.toString());
	    }
	  }
	
	  private static void copy(InputStream is, OutputStream os) throws Exception {
			int len = 0;
			byte[] buffer = new byte[2048];
	        while((len = is.read(buffer)) > 0) {
	            os.write(buffer, 0, len);   
	        }
	  }
}
