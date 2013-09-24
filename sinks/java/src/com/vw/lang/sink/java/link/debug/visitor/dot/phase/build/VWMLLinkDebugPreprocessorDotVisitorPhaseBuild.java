package com.vw.lang.sink.java.link.debug.visitor.dot.phase.build;

import java.io.FileWriter;
import java.io.IOException;

import com.vw.lang.sink.java.VWMLObject;
import com.vw.lang.sink.java.link.AbstractVWMLLinkVisitor;
import com.vw.lang.sink.java.operations.VWMLOperation;

public class VWMLLinkDebugPreprocessorDotVisitorPhaseBuild extends AbstractVWMLLinkVisitor {

	private FileWriter fw = null;

	private VWMLLinkDebugPreprocessorDotVisitorPhaseBuild() {
		
	}
	
	public static AbstractVWMLLinkVisitor instance() {
		return new VWMLLinkDebugPreprocessorDotVisitorPhaseBuild();
	}
	
	@Override
	public void init(String schemaName, String schemaPath) {
		try {
			fw = new FileWriter(schemaPath);
			fw.write("graph " + schemaName + " {\r\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void done(String schemaName) {
		if (fw != null) {
			try {
				fw.write("}\r\n");
				fw.flush();
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			finally {
				fw = null;
			}
		}
	}

	@Override
	public void link(VWMLObject obj, VWMLObject objLinked) {
		try {
			if (fw != null) fw.write("\"" + obj.getId().toString() + "\"" + " -- " + "\"" + objLinked.getId().toString() + "\"" + ";\r\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void unlink(VWMLObject obj, VWMLObject objUnlinked) {
	}

	@Override
	public void interpretObjectAs(VWMLObject obj, VWMLObject interpreting) {
		try {
			if (fw != null) fw.write("\"" + obj.getId() + "\" -- \"" + interpreting.getId() + "\"[style=dotted];\r\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void associateOperation(VWMLObject obj, VWMLOperation op) {
	/*	try {
			fw.write("\"" + obj.getId() + "::" + op.getOpCode().toValue() + "\" [shape=box];");
			fw.write("\"" + obj.getId() + "\" -- \"" + obj.getId() + "::" + op.getOpCode().toValue() + "\"[style=dotted];\r\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	*/	
	}

	@Override
	public void removeOperationFromAssociation(VWMLObject obj, VWMLOperation op) {
	}
}
