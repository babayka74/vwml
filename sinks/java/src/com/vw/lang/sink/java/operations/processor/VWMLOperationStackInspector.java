package com.vw.lang.sink.java.operations.processor;

import java.util.ArrayList;
import java.util.List;

import com.vw.lang.sink.java.VWMLObjectsRepository;
import com.vw.lang.sink.java.entity.VWMLEntity;
import com.vw.lang.sink.java.interpreter.datastructure.VWMLContext;
import com.vw.lang.sink.java.interpreter.datastructure.VWMLStack;

public class VWMLOperationStackInspector extends VWMLStack.VWMLStackInspector {
	private List<VWMLEntity> reversedStack = new ArrayList<VWMLEntity>();
	private String dynContext = null;
	private VWMLEntity dynamicAddressedEntity = null;
	
	@Override
	public boolean inspected(Object obj) {
		VWMLEntity e = (VWMLEntity)obj;
		if (e.getId() == VWMLStack.s_specialMark) {
			processDynamicAddressedEntity();
			return false;
		}
		if (e.isPartOfDynamicContext()) {
			dynContext = VWMLContext.constructContextNameInRunTime(dynContext, e.getReadableId());
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
