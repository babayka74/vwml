package com.vw.lang.sink.java.code.utils;

public class JavaCodeGeneratorUtils {
	
	public static String capitalizeFirstLetter(String original){
	    if(original.length() == 0)
	        return original;
	    return original.substring(0, 1).toUpperCase() + original.substring(1);
	}
}
