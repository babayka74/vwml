package com.vw.lang.sink.java.link.debug.visitor.dot;

import java.io.FileWriter;
import java.io.IOException;

import com.vw.lang.sink.java.VWMLObject;
import com.vw.lang.sink.java.link.IVWMLLinkVisitor;

/**
 * DOT language generator; used in order to visualize objects during preprocessor's stage
 * @author ogibayev
 *
 */
public class VWMLLinkDebugPreprocessorDotVisitor implements IVWMLLinkVisitor {

	private FileWriter fw = null;
	private static VWMLLinkDebugPreprocessorDotVisitor s_instance = new VWMLLinkDebugPreprocessorDotVisitor();
	
	private VWMLLinkDebugPreprocessorDotVisitor() {
		
	}
	
	public static IVWMLLinkVisitor instance() {
		return s_instance;
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
			fw.write("\"" + obj.getId().toString() + "\"" + " -- " + "\"" + objLinked.getId().toString() + "\"" + ";\r\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void unlink(VWMLObject obj, VWMLObject objUnlinked) {
	}
}
