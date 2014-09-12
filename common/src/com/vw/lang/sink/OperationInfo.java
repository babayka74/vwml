package com.vw.lang.sink;

/**
 * Contains information about VWML operation; (line, file, etc)
 * @author Oleg
 *
 */
public class OperationInfo {
	private String fileName;
	private String nextToken;
	private int line;
	private int position;
	
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
}
