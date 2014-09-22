package com.vw.lang.grammar.preprocessor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Performs pre-processing tasks (#if_debug, etc)
 * @author Oleg
 *
 */
public class VWMLPreprocessor {
	
	public static class VWMLProcessorDirective {
		private String name;
		private String value;
		
		public VWMLProcessorDirective(String name, String value) {
			super();
			this.name = name;
			this.value = value;
		}
		
		public String getName() {
			return name;
		}
		
		public String getValue() {
			return value;
		}
	}
	
	
	
	public static class VWMLPreprocessorExpression {

		public static abstract class VWMLPreprocessorItem {
			static enum TYPE {
				REGULAR,
				EXPRESSION
			}
			
			private TYPE type;
			private String value;

			public TYPE getType() {
				return type;
			}

			public void setType(TYPE type) {
				this.type = type;
			}
			
			public String getValue() {
				return value;
			}

			public void setValue(String value) {
				this.value = value;
			}
			
			public abstract VWMLPreprocessorItem process(VWMLPreprocessorExpression expression) throws Exception;
		}
		
		public static class VWMLPreprocessorExpressionAsItem extends VWMLPreprocessorItem {
			
			private VWMLPreprocessorExpression expression;
			
			private VWMLPreprocessorExpressionAsItem(VWMLPreprocessorExpression expression) {
				this.expression = expression;
			}

			public static VWMLPreprocessorExpressionAsItem build(VWMLPreprocessorExpression expression) {
				return new VWMLPreprocessorExpressionAsItem(expression);
			}
			
			public VWMLPreprocessorExpression getExpression() {
				return expression;
			}

			@Override
			public VWMLPreprocessorItem process(VWMLPreprocessorExpression parent) throws Exception {
				VWMLPreprocessorItem v = expression.execute();
				parent.pushToItemStack(v);
				return v;
			}
		}
		
		public static class VWMLPreprocessorRegularItem extends VWMLPreprocessorItem {

			private VWMLPreprocessorRegularItem(String value) {
				setValue(value);
			}
			
			public static VWMLPreprocessorRegularItem build(String value) {
				return new VWMLPreprocessorRegularItem(value);
			}

			@Override
			public VWMLPreprocessorItem process(VWMLPreprocessorExpression parent) throws Exception  {
				String v = VWMLPreprocessor.getDefinitionOf(getValue());
				if (v != null) {
					setValue(v);
				}
				parent.pushToItemStack(this);
				return this;
			}
		}
		
		protected static abstract class VWMLPreprocessorOperation {
			public abstract void handle(VWMLPreprocessorExpression expression) throws Exception;
		}
		
		protected static abstract class VWMLPreprocessorBinaryOperation extends VWMLPreprocessorOperation {
			private VWMLPreprocessorItem v1;
			private VWMLPreprocessorItem v2;
			
			public VWMLPreprocessorItem getV1() {
				return v1;
			}

			public VWMLPreprocessorItem getV2() {
				return v2;
			}

			protected void prepare(VWMLPreprocessorExpression expression) throws Exception {
				v1 = expression.popFromItemStack();
				v2 = expression.popFromItemStack();
				v1.process(expression);
				v2.process(expression);
				v1 = expression.popFromItemStack();
				v2 = expression.popFromItemStack();
			}
		}
		
		protected static class VWMLPreprocessorAndOperation extends VWMLPreprocessorBinaryOperation {
			public void handle(VWMLPreprocessorExpression expression) throws Exception {
				if (expression.itemsStackSize() < 2) {
					throw new Exception("Preprocessor's operation '&' requires 2 arguments");
				}
				prepare(expression);
				String r = String.valueOf(Boolean.valueOf(getV1().getValue()).booleanValue() && Boolean.valueOf(getV2().getValue()).booleanValue());
				expression.pushToItemStack(VWMLPreprocessorRegularItem.build(r));
			}
		}

		protected static class VWMLPreprocessorOrOperation extends VWMLPreprocessorBinaryOperation {
			public void handle(VWMLPreprocessorExpression expression) throws Exception {
				if (expression.itemsStackSize() < 2) {
					throw new Exception("Preprocessor's operation '|' requires 2 arguments");
				}
				prepare(expression);
				String r = String.valueOf(Boolean.valueOf(getV1().getValue()).booleanValue() || Boolean.valueOf(getV2().getValue()).booleanValue());
				expression.pushToItemStack(VWMLPreprocessorRegularItem.build(r));
			}
		}
		
		private List<VWMLPreprocessorItem> itemsStack = new ArrayList<VWMLPreprocessorItem>();
		private List<String> operationsStack = new ArrayList<String>();
		
		@SuppressWarnings("serial")
		private static final Map<String, VWMLPreprocessorOperation> s_operationsProcessors = new HashMap<String, VWMLPreprocessorOperation>(){
	        {
	            put("&", new VWMLPreprocessorAndOperation());
	            put("|", new VWMLPreprocessorOrOperation());
	        }
	    };		
	
	    private VWMLPreprocessorExpression() {
	    	
	    }
	    
	    public static VWMLPreprocessorExpression build() {
	    	return new VWMLPreprocessorExpression();
	    }
	    
		public void pushToItemStack(VWMLPreprocessorItem item) {
			itemsStack.add(item);
		}
		
		public VWMLPreprocessorItem popFromItemStack() {
			VWMLPreprocessorItem v = null;
			if (itemsStack.size() != 0) {
				v = itemsStack.get(itemsStack.size() - 1);
				itemsStack.remove(itemsStack.size() - 1);
			}
			return v;
		}

		public int itemsStackSize() {
			return itemsStack.size();
		}
		
		public void pushToOperationStack(String item) {
			operationsStack.add(item);
		}
		
		public String popFromOperationsStack() {
			String v = null;
			if (operationsStack.size() != 0) {
				v = operationsStack.get(operationsStack.size() - 1);
				operationsStack.remove(operationsStack.size() - 1);
			}
			return v;
		}
		
		public VWMLPreprocessorItem execute() throws Exception {
			String v = null;
			if (operationsStack.size() == 0 && itemsStackSize() == 1) {
				pushToOperationStack("&");
				pushToItemStack(VWMLPreprocessorRegularItem.build("true"));
			}
			while ((v = popFromOperationsStack()) != null) {
				VWMLPreprocessorOperation p = s_operationsProcessors.get(v);
				if (p == null) {
					throw new Exception("Preprocessor reports about unsupported operation '" + v + "'");
				}
				p.handle(this);
			}
			return popFromItemStack();
		}
	}
	
	public static class VWMLPreprocessorIfDirective {
		private boolean result = false;
		private List<VWMLPreprocessorExpression> workingStack = new ArrayList<VWMLPreprocessorExpression>();
		private VWMLPreprocessor preprocessor = null;
		
		public VWMLPreprocessorIfDirective(VWMLPreprocessor preprocessor) {
			this.preprocessor = preprocessor;
		}
		
		public void addRegularItem(String value) {
			createIfParentNotExist();
			preprocessor.addRegularItemToExpression(getTop(), value);
		}

		public void addExpressionItem() {
			createIfParentNotExist();
			VWMLPreprocessor.VWMLPreprocessorExpression e = preprocessor.addExpressionItemToExpression(getTop());
			workingStack.add(e);
		}

		public void addOperation(String operation) {
			createIfParentNotExist();
			preprocessor.addOperationToExpression(getTop(), operation);
		}

		public void removeTop() {
			if (workingStack.size() != 0) {
				workingStack.remove(workingStack.size() - 1);
			}
		}

		public boolean process() throws Exception {
			boolean r = false;
			VWMLPreprocessorExpression parent = workingStack.get(0);
			if (parent != null) {
				VWMLPreprocessorExpression.VWMLPreprocessorItem v = parent.execute();
				if (v != null && v.getValue() != null) {
					r = Boolean.valueOf(v.getValue());
				}
			}
			else {
				throw new Exception("The stack of directive '#If' is empty");
			}
			result = r;
			return r;
		}
		
		public boolean getResult() {
			return result;
		}

		public void setResult(boolean result) {
			this.result = result;
		}
		
		public List<VWMLPreprocessorExpression> getWorkingStack() {
			return workingStack;
		}

		public VWMLPreprocessor getPreprocessor() {
			return preprocessor;
		}

		private void createIfParentNotExist() {
			if (workingStack.size() == 0) {
				VWMLPreprocessorExpression e = VWMLPreprocessorExpression.build();
				workingStack.add(e);
			}
		}
		
		private VWMLPreprocessorExpression getTop() {
			if (workingStack.size() == 0) {
				return null;
			}
			return workingStack.get(workingStack.size() - 1);
		}
	}
	
	private static Map<String, VWMLProcessorDirective> activeDirectives = new HashMap<String, VWMLProcessorDirective>();
	private List<VWMLPreprocessorIfDirective> ifBlockStack = new ArrayList<VWMLPreprocessorIfDirective>();
	
	private VWMLPreprocessor() {
		
	}
	
	public static VWMLPreprocessor instance() {
		return new VWMLPreprocessor();
	}
	
	public static void addDirective(String name, String value) {
		if (name != null) {
			VWMLProcessorDirective activeDirective = new VWMLProcessorDirective(name, value);
			activeDirectives.put(name, activeDirective);
		}
	}

	public static boolean isDebugDirectiveOn() {
		return activeDirectives.get("debug") != null;
	}

	public static String getDefinitionOf(String item) {
		VWMLProcessorDirective directive = activeDirectives.get(item);
		if (directive != null) {
			return directive.getValue();
		}
		return null;
	}
	
	public static VWMLPreprocessorExpression buildExpression() {
		return VWMLPreprocessorExpression.build();
	}
	
	public VWMLPreprocessorIfDirective startDirectiveIf() {
		VWMLPreprocessorIfDirective d = new VWMLPreprocessorIfDirective(this);
		ifBlockStack.add(d);
		return d;
	}

	public VWMLPreprocessorIfDirective getTopDirectiveIf() throws Exception {
		if (ifBlockStack.size() == 0) {
			throw new Exception("The stack of directive '#If' is empty");
		}
		return ifBlockStack.get(ifBlockStack.size() - 1);
	}
	
	public void processDirectiveIf() throws Exception {
		VWMLPreprocessorIfDirective d = getTopDirectiveIf();
		if (d == null) {
			throw new Exception("The stack of directive '#If' is empty");
		}
		d.process();
	}
	
	public boolean getResultOfProcessingDirectiveIf() throws Exception {
		if (ifBlockStack.size() == 0) {
			return true;
		}
		VWMLPreprocessorIfDirective d = getTopDirectiveIf();
		if (d == null) {
			throw new Exception("The stack of directive '#If' is empty");
		}
		return d.getResult();
	}
	
	public void reverseResultOfProcessingDirectiveIf() throws Exception {
		VWMLPreprocessorIfDirective d = getTopDirectiveIf();
		if (d == null) {
			throw new Exception("The stack of directive '#If' is empty");
		}
		d.setResult(!d.getResult());
	}
	
	public VWMLPreprocessorIfDirective endDirectiveIf() {
		VWMLPreprocessorIfDirective e = null;
		if (ifBlockStack.size() != 0) {
			e = ifBlockStack.get(ifBlockStack.size() - 1);
			ifBlockStack.remove(ifBlockStack.size() - 1);
		}
		return e;
	}
	
	protected void addRegularItemToExpression(VWMLPreprocessorExpression parent, String value) {
		VWMLPreprocessorExpression.VWMLPreprocessorRegularItem item = VWMLPreprocessorExpression.VWMLPreprocessorRegularItem.build(value);
		parent.pushToItemStack(item);
	}
	
	protected VWMLPreprocessor.VWMLPreprocessorExpression addExpressionItemToExpression(VWMLPreprocessorExpression parent) {
		VWMLPreprocessorExpression expression = VWMLPreprocessorExpression.build();
		VWMLPreprocessorExpression.VWMLPreprocessorExpressionAsItem item = VWMLPreprocessorExpression.VWMLPreprocessorExpressionAsItem.build(expression);
		parent.pushToItemStack(item);
		return expression;
	}
	
	protected void addOperationToExpression(VWMLPreprocessorExpression parent, String operation) {
		parent.pushToOperationStack(operation);
	}
}
