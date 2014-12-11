package com.vw.lang.sink.java.operations.processor.operations.handlers.ltt;

import java.util.ArrayList;
import java.util.List;

import com.vw.lang.sink.java.entity.VWMLEntity;
import com.vw.lang.sink.java.interpreter.VWMLInterpreterImpl;
import com.vw.lang.sink.java.operations.processor.operations.handlers.activate.VWMLOperationActivateHandler;

/**
 * Handler of 'OPLTT (Long Term Task)' operation
 * @author ogibayev
 *
 */
public class VWMLOperationLTTHandler extends VWMLOperationActivateHandler {

	@Override
	protected void activateTerm(VWMLInterpreterImpl interpreter, VWMLEntity context, VWMLEntity term) throws Exception {
		if (interpreter.isPushed()) {
			if (interpreter.getRtNode() == null) {
				throw new Exception("interpreter '" + interpreter + "' doesn't have associated RT node; context '" + context.getContext() + "'");
			}
			interpreter = interpreter.getRtNode().firstPushedInterpreter();
			if (interpreter == null) {
				throw new Exception("Didn't find first pushed interpreter; context '" + context.getContext() + "'");
			}
		}
		term.setActivated(true);
		// the LTT is run on separated ring
		while(interpreter.getMasterInterpreter() != null) {
			interpreter = interpreter.getMasterInterpreter();
		}
		List<VWMLEntity> tl = new ArrayList<VWMLEntity>();
		tl.add(term);
		interpreter.hiddenActivity(tl, context);
	}
}
