package com.win.game.puzzleR1.model.activity.communication;

import com.win.game.puzzleR1.controllers.activity.main.IUICommandProcessor;

import android.app.Activity;

/**
 * Notifies about job completion on given message
 * @author Oleg
 *
 */
public class GameMessageNotificationChannel {
	
	protected static class ChannelBridge implements Runnable {

		private GameMessage msg;
		private IUICommandProcessor uiCommandProcessor;
		
		private ChannelBridge(IUICommandProcessor uiCommandProcessor, GameMessage msg) {
			this.uiCommandProcessor = uiCommandProcessor;
			this.msg = msg;
		}
		
		public static ChannelBridge build(IUICommandProcessor uiCommandProcessor, GameMessage msg) {
			return new ChannelBridge(uiCommandProcessor, msg);
		}
		
		@Override
		public void run() {
			uiCommandProcessor.uiCommand(msg);
		}
	}
	
	
	private static GameMessageNotificationChannel s_instance = new GameMessageNotificationChannel();
	
	private GameMessageNotificationChannel() {
		
	}
	
	public static GameMessageNotificationChannel instance() {
		return s_instance;
	}
	
	/**
	 * Notifies waiter about finishing job on given message
	 * @param msg
	 */
	public static void notifyOn(GameMessage msg, int status) {
		if (msg != null) {
			synchronized(msg) {
				msg.setStatus(status);
				msg.notify();
			}
		}
	}
	
	/**
	 * Waits for finishing job on given message
	 * @param msg
	 */
	public static void waitForOn(GameMessage msg, int status) {
		if (msg != null) {
			synchronized(msg) {
				while(msg.getStatus() != status) {
					try {
						msg.wait();
					} catch (InterruptedException e) {
					}
				}
			}
		}
	}
	
	/**
	 * Sends message to activity's UI thread
	 * @param activity
	 * @param msg
	 */
	public static GameMessage sendMsgToActivity(Activity activity, IUICommandProcessor uiCommandProcessor, GameMessage msg) {
		activity.runOnUiThread(ChannelBridge.build(uiCommandProcessor, msg));
		waitForOn(msg, GameMessage.STATUS_COMPLETED);
		return msg;
	}
}
