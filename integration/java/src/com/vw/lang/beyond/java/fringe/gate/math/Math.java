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
	public EWEntity invokeEW(Object commandId, EWEntity commandArgs) {
		EWEntity e = null;
		GateCommandHandler gh = s_methods.get(commandId);
		if (gh != null) {
			e = gh.handler(commandArgs);
		}
		return e;
	}

	@Override
	public EWEntity invokeVW(Object commandId, EWEntity commandArgs) {
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
