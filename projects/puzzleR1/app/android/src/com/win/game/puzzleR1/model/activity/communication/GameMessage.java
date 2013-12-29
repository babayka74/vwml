package com.win.game.puzzleR1.model.activity.communication;

public class GameMessage {
	public static final int MSG_START_SELECTION = 1;
	public static final int MSG_ASK_TO_SELECT_IMAGE = MSG_START_SELECTION + 1;
	public static final int MSG_ASK_TO_SELECT_PLACE = MSG_ASK_TO_SELECT_IMAGE + 1;
	public static final int MSG_RIGHT_CHOICE = MSG_ASK_TO_SELECT_PLACE + 1;	
	public static final int MSG_WRONG_CHOICE = MSG_RIGHT_CHOICE + 1;	
	public static final int MSG_END_OF_GAME = MSG_WRONG_CHOICE + 1;
	public static final int MSG_MODEL_STOPPED = MSG_END_OF_GAME + 1;	
	public static final int MSG_UI_MODEL_SYNC = MSG_MODEL_STOPPED + 1;
	
	public static final int STATUS_INITIATED = 0x00;
	public static final int STATUS_COMPLETED = 0x01;
	
	private int mType;
	private int status = STATUS_INITIATED;
	private Object mData;
	
	public GameMessage(int type, Object data) {
		this.mType = type;
		this.mData = data;
	}
	
	public int getType() {
		return this.mType;
	}
	
	public Object getData() {
		return this.mData;
	}
	
	public void dispose() {
		mData = null;
	}
	
	public void setType(int type) {
		this.mType = type;
	}
	
	public void setData(Object data) {
		this.mData = data;
	}

	protected int getStatus() {
		return status;
	}

	protected void setStatus(int status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + mType;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		GameMessage other = (GameMessage) obj;
		if (mType != other.mType) {
			return false;
		}
		return true;
	}
}
