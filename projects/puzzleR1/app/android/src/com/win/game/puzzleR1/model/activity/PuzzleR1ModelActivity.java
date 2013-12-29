package com.win.game.puzzleR1.model.activity;

import android.app.Activity;
import android.util.Log;

import com.win.game.puzzleR1.controllers.activity.main.IUICommandProcessor;
import com.win.game.puzzleR1.model.activity.communication.GameMessage;
import com.win.game.puzzleR1.model.activity.communication.GameMessageNotificationChannel;
import com.win.game.puzzleR1.model.bridge.VWML2JavaInterpreterBridge;
import com.win.game.puzzleR1.model.integration.PuzzleR1CommandSinkRTConfiguration;

public class PuzzleR1ModelActivity extends Thread {

	private final static String TAG = "GameThread"; 
	private GameMessage ewSyncMsg;
	private Activity activity;
	private IUICommandProcessor uiCommandProcessor;
	private boolean started = false;
	
	private PuzzleR1ModelActivity(GameMessage locker, Activity activity, IUICommandProcessor uiCommandProcessor) {
		this.activity = activity;
		this.ewSyncMsg = locker;
		this.uiCommandProcessor = uiCommandProcessor;
	}
	
	public static PuzzleR1ModelActivity build(GameMessage locker, Activity mainActivity, IUICommandProcessor uiCommandProcessor) {
		PuzzleR1ModelActivity m = new PuzzleR1ModelActivity(locker, mainActivity, uiCommandProcessor);
		// initializes internal integration service; used by fringes
		PuzzleR1CommandSinkRTConfiguration.instance().setGameModelActivity(m);
		return m;
	}
	
	/**
	 * Game's model is executing is separated thread. 
	 * The connectivity is provided by fringes defined inside VWML code
	 */
	public void run() {
		Log.d(TAG, "Game Thread waits for configuration...");
		// waiting till configuration and images have been loaded
		notifyUIAboutStartedModel();
		// starts game model
		Log.d(TAG, "Game Thread started...");
		try {
			// starts model's initialization process and interpretation process
			// pay attention: fringes are initialized and instantiated inside
			VWML2JavaInterpreterBridge.instance().startInterpretationProcess();
		} catch (Exception e) {
			// activity should be notified
		}
		reportAboutModelStopped();
		Log.d(TAG, "Game Thread finished...");
	}

	/**
	 * Notifies UI activity about that model has started
	 */
	public void notifyUIAboutStartedModel() {
		synchronized(ewSyncMsg) {
			started = true;
			ewSyncMsg.notify();
		}
	}

	/**
	 * UI activity calls this method in order to wait until model has started
	 */
	public void waitUntillStarted() {
		synchronized(ewSyncMsg) {
			if (!started) {
				try {
					ewSyncMsg.wait();
				} catch (InterruptedException e) {
				}
			}
		}
	}
	
	/**
	 * Commands to UI to allow to selection process (called by PuzzleR1CommandSink)
	 */
	public void prepareForSelection() {
		GameMessage msg = new GameMessage(GameMessage.MSG_START_SELECTION, true);
		GameMessageNotificationChannel.sendMsgToActivity(activity, uiCommandProcessor, msg);
	}

	/**
	 * Commands to UI to select image (called by PuzzleR1CommandSink)
	 */
	public Object askForSelectedImage() {
		Object answer = null;
		while(answer == null) {
			GameMessage msg = new GameMessage(GameMessage.MSG_ASK_TO_SELECT_IMAGE, true);
			msg = GameMessageNotificationChannel.sendMsgToActivity(activity, uiCommandProcessor, msg);
			answer = msg.getData();
		}
		return answer;
	}

	/**
	 * Commands to UI to select place (called by PuzzleR1CommandSink)
	 */
	public Object askForSelectedPlace() {
		Object answer = null;
		while(answer == null) {
			GameMessage msg = new GameMessage(GameMessage.MSG_ASK_TO_SELECT_PLACE, true);
			msg = GameMessageNotificationChannel.sendMsgToActivity(activity, uiCommandProcessor, msg);
			answer = msg.getData();
		}
		return answer;
	}

	/**
	 * Commands to UI to indicate right choice (called by PuzzleR1CommandSink)
	 */
	public Object reportAboutRightChoice() {
		GameMessage msg = new GameMessage(GameMessage.MSG_RIGHT_CHOICE, true);
		GameMessageNotificationChannel.sendMsgToActivity(activity, uiCommandProcessor, msg);
		return null;
	}
	
	/**
	 * Commands to UI to indicate wrong choice (called by PuzzleR1CommandSink)
	 */
	public Object reportAboutWrongChoice() {
		GameMessage msg = new GameMessage(GameMessage.MSG_WRONG_CHOICE, true);
		GameMessageNotificationChannel.sendMsgToActivity(activity, uiCommandProcessor, msg);
		return null;
	}

	/**
	 * Commands to UI to indicate that game has finished (called by PuzzleR1CommandSink)
	 */
	public Object reportAboutEndOfGame() {
		GameMessage msg = new GameMessage(GameMessage.MSG_END_OF_GAME, true);
		GameMessageNotificationChannel.sendMsgToActivity(activity, uiCommandProcessor, msg);
		return null;
	}
	
	/**
	 * Commands to UI to indicate that model has finished (called by PuzzleR1CommandSink)
	 */
	public Object reportAboutModelStopped() {
		GameMessage msg = new GameMessage(GameMessage.MSG_MODEL_STOPPED, true);
		GameMessageNotificationChannel.sendMsgToActivity(activity, uiCommandProcessor, msg);
		return null;
	}
}

