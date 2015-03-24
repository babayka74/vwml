package com.vw.lang.sink.java.operations.processor;

import java.util.ArrayList;
import java.util.List;

import com.vw.lang.sink.java.VWMLContextsRepository;
import com.vw.lang.sink.java.VWMLContextsRepository.ContextIdPair;
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
	private boolean assemblyEntity = true;
	
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
			if (dynContext == null) {
				dynContext = VWMLContext.constructContextNameInRunTime(dynContext, e);
			}
			else {
				dynContext = VWMLContext.constructContextNameFromParts(dynContext, e.getReadableId());
			}
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
			if (dynContext == null) {
				dynContext = VWMLContext.constructContextNameInRunTime(dynContext, e);
			}
			else {
				dynContext = VWMLContext.constructContextNameFromParts(dynContext, e.getReadableId());
			}
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
		for(int i = 0; i < reversedStack.size(); i++) {
			VWMLEntity e = reversedStack.get(i);
			if (!e.isDynamicAddressedInRunTime()) {
				boolean cloneDetected = false;
				VWMLContext ctx = null;
				// entity created in runtime
				if (e.getLink().getParent() == null) {
					ctx = e.getContext();
				}
				else {
					VWMLEntity p = e;
					cloneDetected = (p.getClonedFrom() != null);
					ctx = p.getContext();
					// if entity belongs to cloned context, but not cloned, since it belongs to entity which doesn't belong to cloned context
					// then this case must be checked
					if (!cloneDetected && operationalContext.getClonedFrom() != null) {
						ContextIdPair firstClonedCtxPair = VWMLContextsRepository.instance().wellFormedContext(operationalContext.getContext());
						// whether prototype belongs to 'current operational' context or no - searching for model's context
						if (VWMLContext.isContextChildOf(firstClonedCtxPair, p.getContext().getContext())) {
							ctx = operationalContext;
							cloneDetected = true;
						}
					}
				}
				if (cloneDetected) {
					try {
						ContextIdPair cPair = VWMLContextsRepository.instance().wellFormedContext(ctx.getContext());
						if (cPair == null) {
							throw new Exception("non-wellformed context '" + ctx.getContext() + "'");
						}
						VWMLEntity e1 = (VWMLEntity)VWMLObjectsRepository.getAndCreateInCaseOfCloneOnStackInspector(cPair, e);
						if (e1 != null && e1 != e) {
							reversedStack.set(i, e1);
						}
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			}
		}
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
			VWMLContext c = null;
			// re-process 'dyncontext' on separating parts in order to check well-forming
			ContextIdPair cPair = VWMLContextsRepository.instance().wellFormedContext(dynContext);
			if (cPair != null) {
				c = VWMLContextsRepository.instance().createContextIfNotExists(cPair.getEffectiveContextId());
			}
			else {
				c = VWMLContextsRepository.instance().createContextIfNotExists(dynContext);
				cPair = VWMLContextsRepository.instance().wellFormedContext(dynContext);
				if (cPair == null) {
					throw new Exception("the original context doesn't contain some parts '" + dynContext + "'; see VWML code");
				}
			}
			if (assemblyEntity && collector.size() > 1) {
				if (c == null) {
					c = VWMLContextsRepository.instance().get(dynContext);
					if (c == null) {
						throw new Exception("coudln't find context '" + dynContext + "'");
					}
				}
				dynamicAddressedEntity = VWMLOperationUtils.generateComplexEntityFromEntitiesReversedStack(
															  collector,
															  collector.size() - 1,
															  c,
															  c,
															  0,
															  null,
															  VWMLOperationUtils.s_addIfUnknown);
				for(VWMLEntity entity : collector) {
					reversedStack.remove(entity);
				}
				collector.clear();
				dynamicAddressedEntity.setDynamicAddressedInRunTime(true);
				e = dynamicAddressedEntity;
			}
			else {
				dynamicAddressedEntity.setDynamicAddressedInRunTime(true);
				e = (VWMLEntity)VWMLObjectsRepository.getAndCreateInCaseOfClone(cPair, dynamicAddressedEntity);
			}
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
