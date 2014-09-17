package com.vw.lang.sink;

/**
 * Contains information about VWML operation; (line, file, etc)
 * @author Oleg
 *
 */
public class OperationInfo {
	private String opCode;
	private String fileName;
	private String nextToken;
	private int line;
	private int position;
	
	public OperationInfo() {
		super();
	}

	public OperationInfo(String opCode) {
		super();
		this.opCode = opCode;
	}

	public OperationInfo(String opCode, String fileName, String nextToken, int line, int position) {
		super();
		this.opCode = opCode;
		this.fileName = fileName;
		this.nextToken = nextToken;
		this.line = line;
		this.position = position;
	}

	public String getOpCode() {
		return opCode;
	}

	public void setOpCode(String opCode) {
		this.opCode = opCode;
	}

	public String getFileName() {
		return fileName;
	}
	
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public String getNextToken() {
		return nextToken;
	}
	
	public void setNextToken(String nextToken) {
		this.nextToken = nextToken;
	}
	
	public int getLine() {
		return line;
	}
	
	public void setLine(int line) {
		this.line = line;
	}
	
	public int getPosition() {
		return position;
	}
	
	public void setPosition(int position) {
		this.position = position;
	}

	@Override
	public String toString() {
		return "OperationInfo [opCode=" + opCode + ", fileName=" + fileName
				+ ", nextToken=" + nextToken + ", line=" + line + ", position="
				+ position + "]";
	}
}
