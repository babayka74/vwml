package com.win.game.model.fringe.gate.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.vw.lang.beyond.java.fringe.entity.EWEntity;
import com.vw.lang.beyond.java.fringe.entity.EWEntityBuilder;

public class Utils {
	/**
	 * Reads text file and produces ready EWEntity
	 * @param fileName
	 * @return
	 */
	public static EWEntity constructEntityFromTextFile(String fileFullPathName) throws Exception {
		EWEntity e = null;
		BufferedReader br = new BufferedReader(new FileReader(fileFullPathName));
	    try {
	        StringBuilder sb = new StringBuilder();
	        String line = br.readLine();

	        while (line != null) {
	            sb.append(line);
	            line = br.readLine();
	        }
	        e = EWEntityBuilder.buildFromString(sb.toString());
	    } finally {
	        br.close();
	    }				
	    return e;
	}
	
	/**
	 * Reads text file
	 * @param fileName
	 * @return
	 */
	public static String readTextFile(String fileFullPathName) {
        StringBuilder sb = new StringBuilder();
        try {
			BufferedReader br = new BufferedReader(new FileReader(fileFullPathName));
	        String line = br.readLine();
	        while (line != null) {
	            sb.append(line);
	            line = br.readLine();
	        }
        }
        catch (IOException e) {
        	sb = new StringBuilder();
        }
	    return sb.toString();
	}
	
}
