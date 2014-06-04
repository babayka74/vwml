package com.vw.lang.sink.java.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class RegexTest {
	
	@Test
	public void regexOnConflictRing() {
		String args = "$22";
		Pattern pattern = Pattern.compile("\\$([0-9]*)");
		Matcher matcher = pattern.matcher(args);
        while (matcher.find()) {
            System.out.println(matcher.group(1));
        }		
		String conflictDefinitionName = "{Tom.RobbyProject}.BallToBasket.CommonBasket";
		pattern = Pattern.compile("\\{([^\"]*)\\}");
		matcher = pattern.matcher(conflictDefinitionName);
        while (matcher.find()) {
            System.out.println(matcher.group(1));
        }		
	}
}
