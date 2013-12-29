package com.win.game.puzzleR1.model.integration;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.vw.lang.beyond.java.fringe.entity.EWEntity;
import com.vw.lang.beyond.java.fringe.entity.EWEntityBuilder;
import com.vw.lang.beyond.java.fringe.gate.IEW2VWMLGate;
import com.vw.lang.beyond.java.fringe.gate.IVWMLGate;

/**
 * PuzzleR1CommandSink implements game's command sink (fringe in term of VWML). 
 * Object is instantiated and used by VWML model during integration phase (in run-time)
 * @author Oleg
 *
 */
public class PuzzleR1CommandSink implements IVWMLGate {

	protected static abstract class CommandSink {
	
		private PuzzleR1CommandSinkRTConfiguration configurator = PuzzleR1CommandSinkRTConfiguration.instance();
		
		public abstract EWEntity command(EWEntity commandArgs);

		public PuzzleR1CommandSinkRTConfiguration getConfigurator() {
			return configurator;
		}
		
		protected EWEntity answerToEWEntity(Object answer) {
			EWEntity e = null;
			if (answer != null) {
				try {
					e = EWEntityBuilder.buildFromString((String)answer);
				} catch (Exception ex) {
					// simple swallow it
				}
			}
			return e;
		}
	}

	/**
	 * Reaction on 'prepareForSelection'
	 * @author Oleg
	 *
	 */
	protected static class PrepareForSelectionCommand extends CommandSink {

		@Override
		public EWEntity command(EWEntity commandArgs) {
			// called in model's thread
			getConfigurator().getGameModelActivity().prepareForSelection();
			return null;
		}
	}

	/**
	 * Reaction on 'select'
	 * @author Oleg
	 *
	 */
	protected static class SelectCommand extends CommandSink {

		static long ts1 = 0;
		
		@Override
		public EWEntity command(EWEntity commandArgs) {
			// called in model's thread
			if (ts1 != 0) {
				long d = System.currentTimeMillis() - ts1;
				System.out.println("-> " + d);
			}
			Object answer = null;
			EWEntity entityAskForCard = (EWEntity)commandArgs.getLink().getConcreteLinkedEntity(0);
			EWEntity entityAskForPlace = (EWEntity)commandArgs.getLink().getConcreteLinkedEntity(1);
			if (entityAskForCard != null && entityAskForPlace != null) {
				if (EWEntity.isNilEntity(entityAskForCard)) {
					answer = getConfigurator().getGameModelActivity().askForSelectedImage();
				}
				else {
					answer = getConfigurator().getGameModelActivity().askForSelectedPlace();
				}
			}
			ts1 = System.currentTimeMillis();
			return answerToEWEntity(answer);
		}
	}

	/**
	 * Reaction on 'rightChoice'
	 * @author Oleg
	 *
	 */
	protected static class RightChoiceCommand extends CommandSink {

		@Override
		public EWEntity command(EWEntity commandArgs) {
			getConfigurator().getGameModelActivity().reportAboutRightChoice();
			return null;
		}
	}

	/**
	 * Reaction on 'wrongChoice'
	 * @author Oleg
	 *
	 */
	protected static class WrongChoiceCommand extends CommandSink {

		@Override
		public EWEntity command(EWEntity commandArgs) {
			getConfigurator().getGameModelActivity().reportAboutWrongChoice();
			return null;
		}
	}

	/**
	 * Reaction on 'endOfGame'
	 * @author Oleg
	 *
	 */
	protected static class EndOfGameCommand extends CommandSink {

		@Override
		public EWEntity command(EWEntity commandArgs) {
			getConfigurator().getGameModelActivity().reportAboutEndOfGame();
			return null;
		}
	}
	
	/**
	 * Reaction on 'unknown command'
	 * @author Oleg
	 *
	 */
	protected static class UnknownCommand extends CommandSink {

		@Override
		public EWEntity command(EWEntity commandArgs) {
			// TODO Auto-generated method stub
			return null;
		}
	}
	
	@SuppressWarnings("serial")
	private Map<String, CommandSink> availableCommands = new HashMap<String, CommandSink>() {
		{
			put("prepareForSelection", new PrepareForSelectionCommand());
			put("select",              new SelectCommand());
			put("rightChoice",         new RightChoiceCommand());
			put("wrongChoice",         new WrongChoiceCommand());
			put("endOfGame",           new EndOfGameCommand());
		}
	};
	
	private CommandSink unknownCommand = new UnknownCommand();
	
	private static PuzzleR1CommandSink s_instance = null;
	
	private PuzzleR1CommandSink() {
		
	}
	
	public static PuzzleR1CommandSink instance() {
		if (s_instance != null) {
			return s_instance;
		}
		s_instance = new PuzzleR1CommandSink();
		try {
			s_instance.init();
		} catch (Exception e) {
			s_instance = null;
		}
		return s_instance;
		
	}
	
	@Override
	public void activateVWCallback(IEW2VWMLGate arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public EWEntity invokeVW(Object commandId, EWEntity commandArgs) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EWEntity invokeEW(Object commandId, EWEntity commandArgs) {
		CommandSink command = availableCommands.get(((String)commandId));
		if (command == null) {
			command = unknownCommand;
		}
		return command.command(commandArgs);
	}

	@Override
	public void activateConfiguration(Properties arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void done() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init() throws Exception {
		// TODO Auto-generated method stub
		
	}
}
