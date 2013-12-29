package com.win.game.puzzleR1.configurator;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.util.Log;

/**
 * Game's configuration service
 * @author Oleg
 *
 */
public class PuzzleR1Configurator {

	public static final String CFG_KEY_IMG_IDS    = "imgIDs";
	public static final String CFG_KEY_PLACES_IDS = "placesIDs";
	public static final String CFG_KEY_RELATIONS  = "relations";
	
	private List<String> imgIDsList;
	private List<String> placesIDsList;
	private HashMap<String, String> relationsMap;
	private HashMap<String, Object> mConfig;

	private static PuzzleR1Configurator s_instance = new PuzzleR1Configurator();
	
	private PuzzleR1Configurator() {
		
	}
	
	public static PuzzleR1Configurator instance() {
		return s_instance;
	}
	
	public List<String> getImgIDsList() {
		return imgIDsList;
	}

	public List<String> getPlacesIDsList() {
		return placesIDsList;
	}

	public HashMap<String, String> getRelationsMap() {
		return relationsMap;
	}

	public HashMap<String, Object> getmConfig() {
		return mConfig;
	}

	public void loadResources(Activity activity) throws Exception {
		String strJSON = null;
		try {
			InputStream file = null;
			file = activity.getResources().getAssets().open("config.json");
			byte [] data = new byte[file.available()];
			file.read(data);
			file.close();
			strJSON = new String(data);
		} catch (IOException e) {
			throw new Exception("Could not open file 'config.json'");
		}
		try {
			 JSONObject configJSON = new JSONObject(strJSON);
			 // Parse imgIDs array
			 JSONArray jsaImgIDs = (JSONArray)configJSON.get(CFG_KEY_IMG_IDS);
			 imgIDsList = new ArrayList<String>();
			 int len = jsaImgIDs.length();
			 for (int i = 0; i < len; i++) {
				 String id = jsaImgIDs.getString(i);
				 imgIDsList.add(id);
				 Log.d("CONFIG", "imgIDs[" + String.valueOf(i) + "] = " + id);
			 }
			 // Parse placesIDs array
			 JSONArray jsaPlacesIDs = (JSONArray)configJSON.get(CFG_KEY_PLACES_IDS);
			 placesIDsList = new ArrayList<String>();
			 len = jsaPlacesIDs.length();
			 for (int i = 0; i < len; i++) {
				 String id = jsaPlacesIDs.getString(i);
				 placesIDsList.add(id);
				 Log.d("CONFIG", "placesIDs[" + String.valueOf(i) + "] = " + id);
			 }
			 // Parse relations
			 relationsMap = new HashMap<String, String>();
			 JSONArray jsaRelations = (JSONArray)configJSON.get(CFG_KEY_RELATIONS);
			 len = jsaRelations.length();
			 for (int i = 0; i < len; i++) {
				 JSONArray jsaPair = jsaRelations.getJSONArray(i);
				 String iId = jsaPair.getString(0);
				 String pId = jsaPair.getString(1);
				 relationsMap.put(iId, pId);
			 }
			 Log.d("CONFIG", "Place for 'i1' -> " + relationsMap.get("i1"));
			 Log.d("CONFIG", "Parsing finished");
			 mConfig = new HashMap<String, Object>();
			 mConfig.put(CFG_KEY_IMG_IDS, imgIDsList);
			 mConfig.put(CFG_KEY_PLACES_IDS, placesIDsList);
			 mConfig.put(CFG_KEY_RELATIONS, relationsMap);
			 
		} catch (JSONException e) {
			throw new Exception("The configuration has invalid format");
		}
	}
}
