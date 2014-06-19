package com.vw.lang.beyond.java.fringe.gate.math;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.vw.lang.beyond.java.fringe.entity.EWEntity;
import com.vw.lang.beyond.java.fringe.entity.EWEntityBuilder;
import com.vw.lang.beyond.java.fringe.entity.EWObject;
import com.vw.lang.beyond.java.fringe.gate.GateCommandHandler;
import com.vw.lang.beyond.java.fringe.gate.IEW2VWMLGate;
import com.vw.lang.beyond.java.fringe.gate.IVWMLGate;

/**
 * Implements math integration package
 * @author Oleg
 *
 */
public class Math implements IVWMLGate {
	
	private static class SumHandler extends GateCommandHandler {

		@Override
		public EWEntity handler(EWEntity commandArgs) {
			int i = 0;
			for(EWObject o : commandArgs.getLink().getLinkedObjects()) {
				EWEntity e = (EWEntity)o;
				i += Math.convertString2Int((String)e.getId());
			}
			return EWEntityBuilder.buildSimpleEntity(String.valueOf(i), null);
		}
		
	}

	private static class SubstrHandler extends GateCommandHandler {

		@Override
		public EWEntity handler(EWEntity commandArgs) {
			int i = 0;
			boolean ft = true;
			for(EWObject o : commandArgs.getLink().getLinkedObjects()) {
				EWEntity e = (EWEntity)o;
				if (ft) {
					i = Math.convertString2Int((String)e.getId());
					ft = false;
					continue;
				}
				i -= Math.convertString2Int((String)e.getId());
			}
			return EWEntityBuilder.buildSimpleEntity(String.valueOf(i), null);
		}
		
	}
	
	private static class MultHandler extends GateCommandHandler {

		@Override
		public EWEntity handler(EWEntity commandArgs) {
			int i = 1;
			for(EWObject o : commandArgs.getLink().getLinkedObjects()) {
				EWEntity e = (EWEntity)o;
				i *= Math.convertString2Int((String)e.getId());
			}
			return EWEntityBuilder.buildSimpleEntity(String.valueOf(i), null);
		}
		
	}

	private static class DivHandler extends GateCommandHandler {

		@Override
		public EWEntity handler(EWEntity commandArgs) {
			int i = 0;
			boolean ft = true;
			for(EWObject o : commandArgs.getLink().getLinkedObjects()) {
				EWEntity e = (EWEntity)o;
				if (ft) {
					i = Math.convertString2Int((String)e.getId());
					ft = false;
					continue;
				}
				int d = Math.convertString2Int((String)e.getId());
				if (d != 0) {
					i /= d;
				}
			}
			return EWEntityBuilder.buildSimpleEntity(String.valueOf(i), null);
		}
		
	}

	private static class IncHandler extends GateCommandHandler {

		@Override
		public EWEntity handler(EWEntity commandArgs) {
			int i = 0;
			EWEntity e = null;
			if (commandArgs.isMarkedAsComplexEntity()) {
				e = (EWEntity)commandArgs.getLink().getConcreteLinkedEntity(0);
			}
			else {
				e = commandArgs;
			}
			if (e != null) {
				i = Math.convertString2Int((String)e.getId()) + 1;
			}
			return EWEntityBuilder.buildSimpleEntity(String.valueOf(i), null);
		}
		
	}
	
	private static class DecHandler extends GateCommandHandler {

		@Override
		public EWEntity handler(EWEntity commandArgs) {
			int i = 0;
			EWEntity e = null;
			if (commandArgs.isMarkedAsComplexEntity()) {
				e = (EWEntity)commandArgs.getLink().getConcreteLinkedEntity(0);
			}
			else {
				e = commandArgs;
			}
			if (e != null) {
				i = Math.convertString2Int((String)e.getId()) - 1;
			}
			return EWEntityBuilder.buildSimpleEntity(String.valueOf(i), null);
		}
	}
	
	public static class CompareHandler extends GateCommandHandler {
		
		@Override
		public EWEntity handler(EWEntity commandArgs) {
			EWEntity e = EWEntityBuilder.buildSimpleEntity("error", null);
			if (commandArgs.isMarkedAsComplexEntity() && commandArgs.getLink().getLinkedObjectsOnThisTime() == 2) {
				EWEntity e1 = (EWEntity)commandArgs.getLink().getConcreteLinkedEntity(0);
				EWEntity e2 = (EWEntity)commandArgs.getLink().getConcreteLinkedEntity(1);
				int ie1 = Math.convertString2Int((String)e1.getId());
				int ie2 = Math.convertString2Int((String)e2.getId());
				if (ie1 > ie2) {
					e = EWEntityBuilder.buildSimpleEntity("1", null);
				}
				else
				if (ie1 < ie2) {
					e = EWEntityBuilder.buildSimpleEntity("-1", null);
				}
				else
				if (ie1 == ie2) {
					e = EWEntityBuilder.buildSimpleEntity("0", null);
				}
			}
			return e;
		}		
	}
	
	public static class VSumHandler extends GateCommandHandler {

		private static int X = 0x0;
		private static int Y = 0x1;
		private static int XYCOORDINATES = 0x2;
		private static int FIRSTVECTOR = 0x0;
		private static int SECONDVECTOR = 0x1;
		private static int LRANGE = 0x2;
		private static int RRANGE = 0x3;
		
		protected static class VRange {
			int x;
			int y;
			
			public VRange(int x, int y) {
				super();
				this.x = x;
				this.y = y;
			}

			public int getX() {
				return x;
			}
			
			public void setX(int x) {
				this.x = x;
			}
			
			public int getY() {
				return y;
			}

			public void setY(int y) {
				this.y = y;
			}
		}
		
		@Override
		public EWEntity handler(EWEntity commandArgs) {
			boolean invalidVectorAndStop = false;
			VRange vRangeL = null, vRangeR = null;
			EWEntity e = EWEntityBuilder.buildComplexEntity("()", null);
			if (commandArgs.isMarkedAsComplexEntity() && commandArgs.getLink().getLinkedObjectsOnThisTime() >= 2) {
				EWEntity e1 = (EWEntity)commandArgs.getLink().getConcreteLinkedEntity(FIRSTVECTOR);
				EWEntity e2 = (EWEntity)commandArgs.getLink().getConcreteLinkedEntity(SECONDVECTOR);
				if (commandArgs.getLink().getLinkedObjectsOnThisTime() >= 3) {
					vRangeL = createRangeFromEWEntity((EWEntity)commandArgs.getLink().getConcreteLinkedEntity(LRANGE));
				}
				if (commandArgs.getLink().getLinkedObjectsOnThisTime() >= 4) {
					vRangeR = createRangeFromEWEntity((EWEntity)commandArgs.getLink().getConcreteLinkedEntity(RRANGE));
				}
				if (e1.isMarkedAsComplexEntity() &&
					e2.isMarkedAsComplexEntity() && 
					e1.getLink().getLinkedObjectsOnThisTime() == e2.getLink().getLinkedObjectsOnThisTime() &&
					e1.getLink().getLinkedObjectsOnThisTime() == XYCOORDINATES) {
					for(int i = X; i < XYCOORDINATES; i++) {
						int v1 = Math.convertString2Int((String)e1.getLink().getConcreteLinkedEntity(i).getId());
						int v2 = Math.convertString2Int((String)e2.getLink().getConcreteLinkedEntity(i).getId());
						if (vRangeL != null) {
							if (i == X && v1 + v2 < vRangeL.getX()) {
								invalidVectorAndStop = true;
								break;
							}
							else
							if (i == Y && v1 + v2 < vRangeL.getY()) {
								invalidVectorAndStop = true;
								break;
							}
						}
						if (vRangeR != null) {
							if (i == X && v1 + v2 > vRangeR.getX()) {
								invalidVectorAndStop = true;
								break;
							}
							else
							if (i == Y && v1 + v2 > vRangeR.getY()) {
								invalidVectorAndStop = true;
								break;
							}
						}
						EWEntity v = EWEntityBuilder.buildSimpleEntity(String.valueOf(v1 + v2), null);
						e.link(v);
					}
				}
			}
			if (invalidVectorAndStop) {
				try {
					e = EWEntityBuilder.buildFromString("(-1 -1)");
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
			return e;
		}
		
		private VRange createRangeFromEWEntity(EWEntity e) {
			int x = Math.convertString2Int((String)e.getLink().getConcreteLinkedEntity(X).getId());
			int y = Math.convertString2Int((String)e.getLink().getConcreteLinkedEntity(Y).getId());
			return new VRange(x, y);
		}
	}
	
	@SuppressWarnings("serial")
	private static Map<String, GateCommandHandler> s_methods = new HashMap<String, GateCommandHandler>() {
		{
			put("sum", new SumHandler());
			put("substr", new SubstrHandler());
			put("mult", new MultHandler());
			put("div", new DivHandler());
			put("inc", new IncHandler());
			put("dec", new DecHandler());
			put("compare", new CompareHandler());
			put("vsum", new VSumHandler());
		}
	};
	
	private static Math s_math = null;
	
	private Math() {
		
	}
	
	public static synchronized Math instance() {
		if (s_math != null) {
			return s_math;
		}
		s_math = new Math();
		try {
			s_math.init();
		} catch (Exception e) {
			s_math = null;
		}
		return s_math;
	}
	
	@Override
	public void init() throws Exception {
	}

	@Override
	public void done() throws Exception {
	}

	@Override
	public void activateVWCallback(IEW2VWMLGate gate) {
	}
	
	@Override
	public void activateConfiguration(Properties props) throws Exception{
	}
	
	@Override
	public EWEntity invokeEW(String commandId, EWEntity commandArgs) {
		EWEntity e = null;
		GateCommandHandler gh = s_methods.get(commandId);
		if (gh != null) {
			e = gh.handler(commandArgs);
		}
		return e;
	}

	@Override
	public EWEntity invokeVW(String commandId, EWEntity commandArgs) {
		return null;
	}
	
	private static int convertString2Int(String s) {
		int i = 0;
		try {
			i = Integer.parseInt(s);
		}
		catch(NumberFormatException ex) {
			// swallow it simple; depends on business logic
		}
		return i;
	}
}
