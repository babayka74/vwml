package com.vw.lang.sink.java.operations.processor;

import java.util.ArrayList;
import java.util.List;

import com.vw.lang.sink.java.entity.VWMLEntity;
import com.vw.lang.sink.java.interpreter.datastructure.VWMLStack;

public class VWMLOperationStackInspector extends VWMLStack.VWMLStackInspector {
	private List<VWMLEntity> reversedStack = new ArrayList<VWMLEntity>(); 
	private VWMLEntity specialLinkedEntity = null;
	
	@Override
	public boolean inspected(Object obj) {
		if (((VWMLEntity)obj).getId() == VWMLStack.s_specialMark) {
			specialLinkedEntity = ((VWMLEntity)obj).getSpecialLinkedEntity();
			return false;
		}
		reversedStack.add((VWMLEntity)obj);
		return true;
	}
	
	public List<VWMLEntity> getReversedStack() {
		return reversedStack;
	}
	
	public VWMLEntity getSpecialLinkedEntity() {
		return specialLinkedEntity;
	}

	public void clear() {
		reversedStack.clear();
		specialLinkedEntity = null;
	}
}
