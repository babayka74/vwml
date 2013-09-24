package com.vw.lang.sink.java.link.debug.visitor.dot;

import com.vw.lang.sink.java.VWMLObject;
import com.vw.lang.sink.java.link.AbstractVWMLLinkVisitor;
import com.vw.lang.sink.java.link.debug.visitor.dot.phase.build.VWMLLinkDebugPreprocessorDotVisitorPhaseBuild;
import com.vw.lang.sink.java.link.debug.visitor.dot.phase.runtime.VWMLLinkDebugPreprocessorDotVisitorPhaseRuntime;
import com.vw.lang.sink.java.operations.VWMLOperation;

/**
 * DOT language generator; used in order to visualize objects during preprocessor's stage
 * @author ogibayev
 *
 */
public class VWMLLinkDebugPreprocessorDotVisitor extends AbstractVWMLLinkVisitor {

	private AbstractVWMLLinkVisitor phases[] = 	{
													VWMLLinkDebugPreprocessorDotVisitorPhaseBuild.instance(),
													VWMLLinkDebugPreprocessorDotVisitorPhaseRuntime.instance()
												};
	
	private VWMLLinkDebugPreprocessorDotVisitor() {
		
	}
	
	public static AbstractVWMLLinkVisitor instance() {
		return new VWMLLinkDebugPreprocessorDotVisitor();
	}
	
	@Override
	public void init(String schemaName, String schemaPath) {
		phases[getPhase()].init(schemaName, schemaPath);
	}

	@Override
	public void done(String schemaName) {
		phases[getPhase()].done(schemaName);
	}

	@Override
	public void link(VWMLObject obj, VWMLObject objLinked) {
		phases[getPhase()].link(obj, objLinked);	
	}

	@Override
	public void unlink(VWMLObject obj, VWMLObject objUnlinked) {
		phases[getPhase()].unlink(obj, objUnlinked);
	}

	@Override
	public void interpretObjectAs(VWMLObject obj, VWMLObject interpreting) {
		phases[getPhase()].interpretObjectAs(obj, interpreting);
	}

	@Override
	public void associateOperation(VWMLObject obj, VWMLOperation op) {
		phases[getPhase()].associateOperation(obj, op);
	}

	@Override
	public void removeOperationFromAssociation(VWMLObject obj, VWMLOperation op) {
		phases[getPhase()].removeOperationFromAssociation(obj, op);
	}
}
