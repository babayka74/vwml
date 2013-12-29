package com.win.game.puzzleR1.controllers.activity.main;

import com.win.game.puzzleR1.model.activity.communication.GameMessage;

public interface IUICommandProcessor {
	/**
	 * Called by model by on UI thread
	 * @param msg
	 */
	public void uiCommand(GameMessage msg);
}
