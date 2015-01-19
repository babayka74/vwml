package com.vw.lang.processor.model.sink;

import java.io.InputStream;

import com.vw.lang.sink.OperationInfo;

public abstract class CompilationSink {
	
	public static enum Mode {
		SCAN_ONLY,
		FULL_BUILD
	}
	
	private Mode mode = Mode.SCAN_ONLY;
	
	public Mode getMode() {
		return mode;
	}

	public void setMode(Mode mode) {
		this.mode = mode;
	}

	/**
	 * Publishes skipped code
	 * @param opInfo
	 */
	public abstract void skippedCode(OperationInfo opInfo);

	/**
	 * Handles VWML's include directive
	 * @param fileName
	 */
	public abstract void handleInclude(String fileName);
	
	/**
	 * Publishes module's desired package (for Java only)
	 * @param packageName
	 */
	public abstract void publishModulePackage(String packageName);
	
	/**
	 * Publishes module's file location
	 * @param fileLocation
	 */
	public abstract void publishFileLocation(String fileLocation);

	/**
	 * Publishes module's author
	 * @param author
	 */
	public abstract void publishAuthor(String author);

	/**
	 * Publishes project name
	 * @param author
	 */
	public abstract void publishProjectName(String projectName);

	/**
	 * Publishes project description
	 * @param author
	 */
	public abstract void publishProjectDescription(String projectDescription);

	/**
	 * Publishes currently processed module name
	 * @param author
	 */
	public abstract void publishModuleName(String moduleName);
	
	/**
	 * Delegates error message info
	 * @param opInfo
	 */
	public abstract void delegateErrorCompilationMessage(OperationInfo opInfo);
	
	/**
	 * Delegates info that process is going to be started
	 * @param processName
	 */
	public abstract void delegateStartProcessExecution(String processName);
	
	/**
	 * Delegates input and output streams of running process
	 * @param processName
	 * @param is
	 * @param es
	 */
	public abstract void delegateRuntimeStreams(String processName, InputStream is, InputStream es);

	/**
	 * Delegates info that process finished execution
	 * @param processName
	 */
	public abstract void delegateFinishProcessExecution(String processName);
}
