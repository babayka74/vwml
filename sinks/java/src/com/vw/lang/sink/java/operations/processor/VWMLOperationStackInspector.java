package com.vw.lang.sink.java.operations.processor;

import java.util.ArrayList;
import java.util.List;

import com.vw.lang.sink.java.VWMLObjectsRepository;
import com.vw.lang.sink.java.entity.VWMLEntity;
import com.vw.lang.sink.java.interpreter.datastructure.VWMLContext;
import com.vw.lang.sink.java.interpreter.datastructure.VWMLStack;
import com.vw.lang.utils.Stack;

public class VWMLOperationStackInspector extends VWMLStack.VWMLStackInspector {
	private List<VWMLEntity> reversedStack = new ArrayList<VWMLEntity>();
	private String dynContext = null;
	private VWMLEntity dynamicAddressedEntity = null;
	private boolean inspectMustReturn = false;
	
	@Override
	public boolean inspected(Object obj) {
		if (inspectMustReturn) {
			return false;
		}
		VWMLEntity e = (VWMLEntity)obj;
		if (e.getId() == VWMLStack.s_specialMark) {
			processDynamicAddressedEntity();
			return false;
		}
		if (e.isPartOfDynamicContext()) {
			dynContext = VWMLContext.constructContextNameInRunTime(dynContext, e);
			reversedStack.remove(dynamicAddressedEntity);
			return true;
		}
		else {
			processDynamicAddressedEntity();
		}
		dynamicAddressedEntity = e;
		reversedStack.add(e);
		return true;
	}

	@Override
	public boolean future(Stack stack, Object obj) {
		boolean f = false;
		VWMLEntity e = (VWMLEntity)obj;
		if (e != null && e.isPartOfDynamicContext()) {
			inspectMustReturn = true;
			dynContext = VWMLContext.constructContextNameInRunTime(dynContext, e);
			reversedStack.remove(dynamicAddressedEntity);
			f = true;
		}
		else {
			processDynamicAddressedEntity();
			if (inspectMustReturn) {
				VWMLEntity passToStack = new VWMLEntity(VWMLStack.s_specialMark, VWMLStack.s_specialMark, null);
				stack.pop();
				stack.push(passToStack);
			}
		}
		return f;
	}
	
	public Object toStack() {
		return null;
	}
	
	public boolean popInspectedNode(Object obj) {
		return true;
	}

	public List<VWMLEntity> getReversedStack() {
		return reversedStack;
	}
	
	public void clear() {
		reversedStack.clear();
		dynContext = null;
		dynamicAddressedEntity = null;
	}
	
	protected void processDynamicAddressedEntity() {
		VWMLEntity e = null;
		if (dynContext != null) {
			// lookup for entity
			try {
				e = (VWMLEntity)VWMLObjectsRepository.findObject(dynContext, dynamicAddressedEntity.buildReadableId());
			} catch (Exception ex) {
				// entity wasn't found on dynamic context
				e = VWMLObjectsRepository.instance().getNilEntity();
			}
			reversedStack.add(e);
			dynContext = null;
		}
	}
}
