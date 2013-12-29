package com.win.game.puzzleR1.controllers.activity.main;

import java.util.ArrayList;
import java.util.List;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.FillResolutionPolicy;
import org.andengine.entity.scene.IOnAreaTouchListener;
import org.andengine.entity.scene.ITouchArea;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.ui.activity.SimpleBaseGameActivity;

import android.util.DisplayMetrics;
import android.util.Log;
import android.util.SparseArray;
import android.widget.Toast;

import com.win.game.puzzleR1.configurator.PuzzleR1Configurator;
import com.win.game.puzzleR1.controllers.activity.utils.ActivityUtils;
import com.win.game.puzzleR1.model.activity.PuzzleR1ModelActivity;
import com.win.game.puzzleR1.model.activity.communication.GameMessage;
import com.win.game.puzzleR1.model.activity.communication.GameMessageNotificationChannel;

/**
 * Game's main activity
 * @author Anton, Oleg
 *
 */
public class PuzzleR1MainScreenUIActivity extends SimpleBaseGameActivity implements IOnAreaTouchListener, IUICommandProcessor {

	/**
	 * Abstract UI command handler
	 * @author Oleg
	 *
	 */
	public static abstract class UICommandGenerated {
		private GameMessage deferredMessage;
		private TouchEvent pSceneTouchEvent;
		private ITouchArea pTouchArea;
		private float pTouchAreaLocalX;
		private float pTouchAreaLocalY;
		
		public GameMessage getDeferredMessage() {
			return deferredMessage;
		}

		public void setDeferredMessage(GameMessage deferredMessage) {
			this.deferredMessage = deferredMessage;
		}

		public void setTouchInfo(TouchEvent pSceneTouchEvent, ITouchArea pTouchArea, float pTouchAreaLocalX, float pTouchAreaLocalY) {
			setpSceneTouchEvent(pSceneTouchEvent);
			setpTouchArea(pTouchArea);
			setpTouchAreaLocalX(pTouchAreaLocalX);
			setpTouchAreaLocalY(pTouchAreaLocalY);
		}
		
		public TouchEvent getpSceneTouchEvent() {
			return pSceneTouchEvent;
		}

		public ITouchArea getpTouchArea() {
			return pTouchArea;
		}

		public float getpTouchAreaLocalX() {
			return pTouchAreaLocalX;
		}

		public float getpTouchAreaLocalY() {
			return pTouchAreaLocalY;
		}

		public abstract void handle(PuzzleR1MainScreenUIActivity activity, GameMessage msg);

		protected void setpSceneTouchEvent(TouchEvent pSceneTouchEvent) {
			this.pSceneTouchEvent = pSceneTouchEvent;
		}

		protected void setpTouchArea(ITouchArea pTouchArea) {
			this.pTouchArea = pTouchArea;
		}

		protected void setpTouchAreaLocalX(float pTouchAreaLocalX) {
			this.pTouchAreaLocalX = pTouchAreaLocalX;
		}

		protected void setpTouchAreaLocalY(float pTouchAreaLocalY) {
			this.pTouchAreaLocalY = pTouchAreaLocalY;
		}
	}

	/**
	 * Reaction on 'MSG_START_SELECTION' (UI's thread)
	 * @author Oleg
	 *
	 */
	public class ModelUICommandPrepareForSelection extends UICommandGenerated {

		@Override
		public void handle(PuzzleR1MainScreenUIActivity activity, GameMessage msg) {
			activity.setState(PuzzleR1MainScreenUIActivity.State.PREPARE_FOR_SELECTION);
			msg.setData(null);
		}
	}

	/**
	 * Reaction on 'MSG_ASK_TO_SELECT_IMAGE' (UI's thread)
	 * @author Oleg
	 *
	 */
	public class ModelUICommandSelectImage extends UICommandGenerated {

		@Override
		public void handle(PuzzleR1MainScreenUIActivity activity, GameMessage msg) {
			activity.displayMessage("Select image");
			activity.setState(PuzzleR1MainScreenUIActivity.State.SELECT_IMAGE);
			activity.deferrMessageProcessing(msg);
		}
	}

	/**
	 * Reaction on 'MSG_ASK_TO_SELECT_PLACE' (UI's thread)
	 * @author Oleg
	 *
	 */
	public class ModelUICommandSelectPlace extends UICommandGenerated {

		@Override
		public void handle(PuzzleR1MainScreenUIActivity activity, GameMessage msg) {
			activity.displayMessage("Select place");
			activity.setState(PuzzleR1MainScreenUIActivity.State.SELECT_PLACE);
			activity.deferrMessageProcessing(msg);
		}
	}

	/**
	 * Reaction on 'MSG_RIGHT_CHOICE' (UI's thread)
	 * @author Oleg
	 *
	 */
	public class ModelUICommandRightChoice extends UICommandGenerated {

		@Override
		public void handle(PuzzleR1MainScreenUIActivity activity, GameMessage msg) {
			activity.deferrMessageProcessing(null);
			activity.rightChoice();
			GameMessageNotificationChannel.notifyOn(msg, GameMessage.STATUS_COMPLETED);
		}
	}

	/**
	 * Reaction on 'MSG_WRONG_CHOICE' (UI's thread)
	 * @author Oleg
	 *
	 */
	public class ModelUICommandWrongChoice extends UICommandGenerated {

		@Override
		public void handle(PuzzleR1MainScreenUIActivity activity, GameMessage msg) {
			activity.deferrMessageProcessing(null);
			activity.wrongChoice();
			GameMessageNotificationChannel.notifyOn(msg, GameMessage.STATUS_COMPLETED);
		}
	}

	/**
	 * Reaction on 'MSG_END_OF_GAME' (UI's thread)
	 * @author Oleg
	 *
	 */
	public class ModelUICommandEndOfGame extends UICommandGenerated {

		@Override
		public void handle(PuzzleR1MainScreenUIActivity activity, GameMessage msg) {
			activity.deferrMessageProcessing(null);
			activity.endOfGame();
			GameMessageNotificationChannel.notifyOn(msg, GameMessage.STATUS_COMPLETED);
		}
	}

	/**
	 * Reaction on 'MSG_MODEL_STOPPED' (UI's thread)
	 * @author Oleg
	 *
	 */
	public class ModelUIModelStopped extends UICommandGenerated {

		@Override
		public void handle(PuzzleR1MainScreenUIActivity activity, GameMessage msg) {
			GameMessageNotificationChannel.notifyOn(msg, GameMessage.STATUS_COMPLETED);
			activity.deferrMessageProcessing(null);
			activity.modelStopped();
		}
	}
	
	/**
	 * Reaction on 'SELECT_IMAGE' (source is touch event, UI thread)
	 * @author Oleg
	 *
	 */
	public class UICommandSelectImage extends UICommandGenerated {

		@Override
		public void handle(PuzzleR1MainScreenUIActivity activity, GameMessage msg) {
			Sprite img = (Sprite)this.getpTouchArea();
			String id = (String)img.getUserData();
			msg = getDeferredMessage();
			if (activity.isImg(id)) {
				if (msg != null) {
					msg.setData(id);
					setDeferredMessage(null);
					activity.selectImg(img);
				}
			}
			else {
				msg.setData(null);
				ActivityUtils.defferToast(activity, "You touched non-image area");
			}
			GameMessageNotificationChannel.notifyOn(msg, GameMessage.STATUS_COMPLETED);
		}
	}

	/**
	 * Reaction on 'SELECT_PLACE' (source is touch event, UI thread)
	 * @author Oleg
	 *
	 */
	public class UICommandSelectPlace extends UICommandGenerated {

		@Override
		public void handle(PuzzleR1MainScreenUIActivity activity, GameMessage msg) {
			Sprite img = (Sprite)this.getpTouchArea();
			String id = (String)img.getUserData();
			msg = getDeferredMessage();
			if (activity.isPlace(id)) {
				if (msg != null) {
					msg.setData(id);
					setDeferredMessage(null);
					activity.selectPlace(img);
				}
			}
			else {
				msg.setData(null);
				ActivityUtils.defferToast(activity, "Select place where image should be placed");
			}
			GameMessageNotificationChannel.notifyOn(msg, GameMessage.STATUS_COMPLETED);
		}
	}
	
	private SparseArray<UICommandGenerated> uiCommandsDispatcher = new SparseArray<UICommandGenerated>() {
		{
			put(GameMessage.MSG_START_SELECTION,     new ModelUICommandPrepareForSelection());
			put(GameMessage.MSG_ASK_TO_SELECT_IMAGE, new ModelUICommandSelectImage());
			put(GameMessage.MSG_ASK_TO_SELECT_PLACE, new ModelUICommandSelectPlace());
			put(GameMessage.MSG_RIGHT_CHOICE,        new ModelUICommandRightChoice());
			put(GameMessage.MSG_WRONG_CHOICE,        new ModelUICommandWrongChoice());
			put(GameMessage.MSG_END_OF_GAME,         new ModelUICommandEndOfGame());
			put(GameMessage.MSG_MODEL_STOPPED,       new ModelUIModelStopped());
		}
	};

	private SparseArray<UICommandGenerated> onTouchDispatcher = new SparseArray<UICommandGenerated>() {
		{
			put(PuzzleR1MainScreenUIActivity.State.SELECT_IMAGE.ordinal(), new UICommandSelectImage());
			put(PuzzleR1MainScreenUIActivity.State.SELECT_PLACE.ordinal(), new UICommandSelectPlace());
		}
	};
	
	private static final String TAG = "PuzzleR1MainScreenUIActivity";
	
	private static int CAMERA_WIDTH = 1024;
	private static int CAMERA_HEIGHT = 768;

	private Scene mScene;
	
	public static PuzzleR1MainScreenUIActivity instance;
	
	private PuzzleR1ModelActivity mPuzzleR1ModelActivity;
	
	private GameMessage synchronizationMessage = new GameMessage(GameMessage.MSG_UI_MODEL_SYNC, null);
	
	public static enum State {
		IDLE,
		PREPARE_FOR_SELECTION,
		SELECT_IMAGE,
		SELECT_PLACE
	};
	
	private State mState;
	private Sprite mCurImg;
	private Sprite mCurPlace;

	private BitmapTextureAtlas mBackgroundTexture;
	private ITextureRegion mBackgroundTextureRegion;
	
	private List<ITextureRegion> images;
	
	private BitmapTextureAtlas mPlaceholderTexture;
	private ITextureRegion mPlaceholderTextureRegion;
	
	private boolean forceExit = false;
	
	private static final int IMG_WIDTH  = 153;
	private static final int IMG_HEIGHT = 152;
	
	private static final float[][] PLACEHOLDERS_COORDS = {
		{293, 96},
		{449, 96},
		{605, 96},
		{293, 252},
		{605, 252},
		{293, 408},
		{449, 408},
		{605, 408}
	};
	
	public PuzzleR1MainScreenUIActivity() {
		instance = this;
	}
	
	@Override
	public EngineOptions onCreateEngineOptions() {
		Toast.makeText(this, "Touch the image to select it. Then touch the place to put the image", Toast.LENGTH_SHORT).show();
		final DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		final Camera camera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);		
		return new EngineOptions(true, ScreenOrientation.LANDSCAPE_SENSOR, new FillResolutionPolicy(), camera);
	}

	@Override
	protected void onCreateResources() {
		try {
			// loads game configuration
			PuzzleR1Configurator.instance().loadResources(this);
			// initializes UI resources
			initResources();
		}
		catch(Exception e) {
			displayMessage(e.getMessage());
		}
	}

	@Override
	protected Scene onCreateScene() {
		PuzzleR1Configurator conf = PuzzleR1Configurator.instance();
		this.mScene = new Scene();
		this.mScene.setBackground(new Background(0, 0, 0xFF));
		this.mScene.setBackgroundEnabled(false);
		Sprite sprBkg = new Sprite(0, 0, this.mBackgroundTextureRegion, this.getVertexBufferObjectManager());
		mScene.attachChild(sprBkg);
		this.mScene.setOnAreaTouchListener(this);
		// Create Placeholders
		for (int i = 0; i < images.size(); i++) {
			Sprite sprPlace = new Sprite(0, 0, this.mPlaceholderTextureRegion, this.getVertexBufferObjectManager());
			sprPlace.setX(PLACEHOLDERS_COORDS[i][0]);
			sprPlace.setY(PLACEHOLDERS_COORDS[i][1]);
			sprPlace.setUserData(conf.getPlacesIDsList().get(i));
			this.mScene.registerTouchArea(sprPlace);
			this.mScene.attachChild(sprPlace);
		}
		// Create Image parts
		for (int i = 0; i < images.size(); i++) {
			ITextureRegion tr = images.get(i);
			Sprite sprImg = new Sprite(0, 0, tr, this.getVertexBufferObjectManager());
			sprImg.setX((float)Math.floor(i / 4) * (CAMERA_WIDTH - IMG_WIDTH - 20) + 10);
			sprImg.setY((i % 4) * (IMG_HEIGHT + 20));
			sprImg.setUserData(conf.getImgIDsList().get(i));
			this.mScene.registerTouchArea(sprImg);
			this.mScene.attachChild(sprImg);
		}
		// actually instantiates, initializes and starts game model activity
		startModelActivity();
		return this.mScene;
	}

	@Override
	public boolean onAreaTouched(TouchEvent pSceneTouchEvent, ITouchArea pTouchArea, float pTouchAreaLocalX, float pTouchAreaLocalY) {
		if (pSceneTouchEvent.isActionDown()) {
			UICommandGenerated onTouchCommand = onTouchDispatcher.get(mState.ordinal());
			if (onTouchCommand == null) {
				return false;
			}
			onTouchCommand.setTouchInfo(pSceneTouchEvent, pTouchArea, pTouchAreaLocalX, pTouchAreaLocalY);
			onTouchCommand.handle(this, null);
			mState = State.IDLE;
			return true;
		}
		return false;
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		if (forceExit) {
			System.exit(0);
		}
	}

	public void uiCommand(GameMessage msg) {
		UICommandGenerated uiCommand = uiCommandsDispatcher.get(msg.getType());
		if (uiCommand != null) {
			uiCommand.handle(this, msg);
		}
		Log.d(TAG, String.valueOf(msg.getType()));
	}
	
	public State getState() {
		return mState;
	}

	public void setState(State mState) {
		this.mState = mState;
	}

	public void displayMessage(String message) {
		Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
	}
	
	public void deferrMessageProcessing(GameMessage msg) {
		UICommandGenerated onTouchCommand = onTouchDispatcher.get(this.mState.ordinal());
		if (onTouchCommand != null) {
			onTouchCommand.setDeferredMessage(msg); 
		}
	}
	
	protected void selectImg(Sprite img) {
		mCurImg = img;
		markCurrentImage();
	}

	protected void selectPlace(Sprite place) {
		mCurPlace = place;
	}

	protected boolean isImg(String id) {
		return (id.indexOf("c") == 0);
	}
	
	protected boolean isPlace(String id) {
		return (id.indexOf("p") == 0);
	}
	
	protected void rightChoice() {
		displayMessage("Right choice !");
		unmarkCurrentImage();
		mScene.unregisterTouchArea(mCurPlace);
		mScene.unregisterTouchArea(mCurImg);
		mCurImg.setPosition(mCurPlace);
	}
	
	protected void wrongChoice() {
		displayMessage("Wrong choice; try again");
		unmarkCurrentImage();
		mCurImg = null;
		mCurPlace = null;
	}

	protected void endOfGame() {
		mCurImg = null;
		mCurPlace = null;
		displayMessage("Congratulation ! Puzzle completed !");
		System.out.println("!!!");
	}
	
	protected void modelStopped() {
		finish();
		forceExit = true;
	}
	
	private void markCurrentImage() {
		if (mCurImg != null) {
			mCurImg.setRed(mCurImg.getRed() + 50);
		}
	}

	private void unmarkCurrentImage() {
		if (mCurImg != null) {
			mCurImg.setRed(mCurImg.getRed() - 50);
		}
	}
	
	private	void initResources() {
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
		this.mBackgroundTexture = new BitmapTextureAtlas(this.getTextureManager(), 1024, 768);
		this.mBackgroundTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mBackgroundTexture, this, "bkg.jpg", 0, 0);
		this.mBackgroundTexture.load();
		images = new ArrayList<ITextureRegion>();
		for (int i = 1; i <= 8; i++) {
			BitmapTextureAtlas bmp = new BitmapTextureAtlas(this.getTextureManager(), 153, 152);
			ITextureRegion tr = BitmapTextureAtlasTextureRegionFactory.createFromAsset(bmp, this, "img/image" + String.valueOf(i) + ".png", 0, 0);
			bmp.load();
			images.add(tr);
		}
		this.mPlaceholderTexture = new BitmapTextureAtlas(this.getTextureManager(), 154, 154);
		this.mPlaceholderTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mPlaceholderTexture, this, "placeholder.png", 0, 0);
		this.mPlaceholderTexture.load();
	}
	
	private void startModelActivity() {
		mState = State.IDLE;
		mPuzzleR1ModelActivity = PuzzleR1ModelActivity.build(synchronizationMessage, this, this);
		mPuzzleR1ModelActivity.start();
		// waits until game's model is started
		mPuzzleR1ModelActivity.waitUntillStarted();
	}
}
