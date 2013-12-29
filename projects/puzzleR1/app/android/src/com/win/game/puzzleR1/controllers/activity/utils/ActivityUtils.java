package com.win.game.puzzleR1.controllers.activity.utils;

import android.app.Activity;
import android.widget.Toast;

/**
 * Some simple methods
 * @author Oleg
 *
 */
public class ActivityUtils {
	
	protected static class DefferedToast implements Runnable {

		private Activity activity;
		private String msg;
		
		private DefferedToast(Activity activity, String msg) {
			this.activity = activity;
			this.msg = msg;
		}
		
		public static DefferedToast build(Activity activity, String msg) {
			return new DefferedToast(activity, msg);
		}
		
		@Override
		public void run() {
			Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show();
		}
	}

	public static void defferToast(Activity activity, String msg) {
		activity.runOnUiThread(DefferedToast.build(activity, msg));
	}
}
