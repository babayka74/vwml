package com.vw.lang.sink.java.operations.processor;

import java.util.ArrayList;
import java.util.List;

import com.vw.lang.sink.java.VWMLContextsRepository;
import com.vw.lang.sink.java.VWMLObjectsRepository;
import com.vw.lang.sink.java.entity.VWMLEntity;
import com.vw.lang.sink.java.interpreter.VWMLInterpreterImpl;
import com.vw.lang.sink.java.interpreter.datastructure.VWMLContext;
import com.vw.lang.sink.java.interpreter.datastructure.VWMLStack;
import com.vw.lang.sink.java.operations.VWMLOperationUtils;
import com.vw.lang.utils.Stack;

public class VWMLOperationStackInspector extends VWMLStack.VWMLStackInspector {
	private List<VWMLEntity> reversedStack = new ArrayList<VWMLEntity>();
	private List<VWMLEntity> collector = new ArrayList<VWMLEntity>();
	private String dynContext = null;
	private VWMLEntity dynamicAddressedEntity = null;
	private boolean inspectMustReturn = false;
	private VWMLContext operationalContext = null;
	private VWMLInterpreterImpl interpreter = null;
	private boolean assemblyEntity = false;
	
	public VWMLOperationStackInspector(VWMLInterpreterImpl interpreter, VWMLContext context) {
		setInterpreter(interpreter);
		setOperationalContext(context);
	}
	
	public VWMLInterpreterImpl getInterpreter() {
		return interpreter;
	}

	public void setInterpreter(VWMLInterpreterImpl interpreter) {
		this.interpreter = interpreter;
	}

	public VWMLContext getOperationalContext() {
		return operationalContext;
	}

	public void setOperationalContext(VWMLContext operationalContext) {
		this.operationalContext = operationalContext;
	}

	@Override
	public boolean inspected(Object obj) throws Exception {
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
			e.setPartOfDynamicContext(false);
			reversedStack.remove(dynamicAddressedEntity);
			return true;
		}
		else {
			processDynamicAddressedEntity();
		}
		if (assemblyEntity) {
			collector.add(e);
		}
		dynamicAddressedEntity = e;
		pushEntityToReversedStack(e);
		return true;
	}

	@Override
	public boolean future(Stack stack, Object obj) throws Exception {
		boolean f = false;
		VWMLEntity e = (VWMLEntity)obj;
		if (e != null && e.isPartOfDynamicContext()) {
			inspectMustReturn = true;
			dynContext = VWMLContext.constructContextNameInRunTime(dynContext, e);
			e.setPartOfDynamicContext(false);
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
		collector.clear();
		collector = null;
	}
	
	public boolean isAssemblyEntity() {
		return assemblyEntity;
	}

	public void setAssemblyEntity(boolean assemblyEntity) {
		this.assemblyEntity = assemblyEntity;
	}

	protected void processDynamicAddressedEntity() throws Exception {
		VWMLEntity e = null;
		if (dynContext != null) {
			// lookup for entity
			if (VWMLContext.isDynamicContextPointsToSelf(dynContext) && operationalContext != null) {
				dynContext = VWMLContext.changeSelfAddressedDynamicContextNameTo(dynContext, operationalContext.getContext());					
			}
			if (assemblyEntity && collector.size() > 1) {
				VWMLContext c = VWMLContextsRepository.instance().get(dynContext);
				if (c == null) {
					throw new Exception("coudln't find context '" + dynContext + "'");
				}
				dynamicAddressedEntity = VWMLOperationUtils.generateComplexEntityFromEntitiesReversedStack(
															  collector,
															  collector.size() - 1,
															  c,
															  c,
															  0,
															  null,
															  VWMLOperationUtils.s_dontAddIfUnknown);
				for(VWMLEntity entity : collector) {
					reversedStack.remove(entity);
				}
				collector.clear();
			}
			e = (VWMLEntity)VWMLObjectsRepository.findObject(dynContext, dynamicAddressedEntity.buildReadableId());
			if (e == null) {
				throw new Exception("Couldn't find entity '" + dynamicAddressedEntity.getReadableId() + "' on context '" + dynContext + "'");
			}
			if (e.getReadableId() == null && ((e.isMarkedAsComplexEntity() && e.getLink().getLinkedObjectsOnThisTime() == 0) || !e.isMarkedAsComplexEntity())) {
				// looks like context which built during compilation time
				e.setReadableId((String)e.getId());
			}
			e.setDynamicAddressedInRunTime(true);
			pushEntityToReversedStack(e);
			dynContext = null;
		}
	}
	
	private void pushEntityToReversedStack(VWMLEntity entity) throws Exception {
		VWMLEntity asArgRef = VWMLOperationUtils.getRelatedEntityByArgument(interpreter, entity);
		if (asArgRef != null) {
			entity = asArgRef;
		}
		reversedStack.add(entity);
	}
}
