package com.vw.lang.sink.java.link.debug.visitor.dot.phase.runtime;

import com.vw.lang.sink.java.VWMLObject;
import com.vw.lang.sink.java.link.AbstractVWMLLinkVisitor;
import com.vw.lang.sink.java.operations.VWMLOperation;

public class VWMLLinkDebugPreprocessorDotVisitorPhaseRuntime extends AbstractVWMLLinkVisitor {

	private VWMLLinkDebugPreprocessorDotVisitorPhaseRuntime() {
		
	}
	
	public static AbstractVWMLLinkVisitor instance() {
		return new VWMLLinkDebugPreprocessorDotVisitorPhaseRuntime();
	}
	
	@Override
	public void init(String schemaName, String schemaPath) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void done(String schemaName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void link(VWMLObject obj, VWMLObject objLinked) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void unlink(VWMLObject obj, VWMLObject objUnlinked) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void interpretObjectAs(VWMLObject obj, VWMLObject interpreting) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void associateOperation(VWMLObject obj, VWMLOperation op) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeOperationFromAssociation(VWMLObject obj, VWMLOperation op) {
		// TODO Auto-generated method stub
		
	}

}
