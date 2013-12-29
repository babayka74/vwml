package com.win.game.puzzleR1.model.integration;

import com.win.game.puzzleR1.model.activity.PuzzleR1ModelActivity;

/**
 * Integration service configurator is used in order to pass configuration to fringes
 * @author Oleg
 *
 */
public class PuzzleR1CommandSinkRTConfiguration {
	private PuzzleR1ModelActivity gameModelActivity;
	private static PuzzleR1CommandSinkRTConfiguration s_instance = new PuzzleR1CommandSinkRTConfiguration();
	
	private PuzzleR1CommandSinkRTConfiguration() {
		
	}
	
	public static PuzzleR1CommandSinkRTConfiguration instance() {
		return s_instance;
	}
	
	public PuzzleR1ModelActivity getGameModelActivity() {
		return gameModelActivity;
	}

	public void setGameModelActivity(PuzzleR1ModelActivity gameModelActivity) {
		this.gameModelActivity = gameModelActivity;
	}
}
