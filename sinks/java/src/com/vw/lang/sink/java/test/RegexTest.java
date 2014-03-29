package com.vw.lang.sink.java.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class RegexTest {
	
	@Test
	public void regexOnConflictRing() {
		String conflictDefinitionName = "{Tom.RobbyProject}.BallToBasket.CommonBasket";
		Pattern pattern = Pattern.compile("\\{([^\"]*)\\}");
		Matcher matcher = pattern.matcher(conflictDefinitionName);
        while (matcher.find()) {
            System.out.println(matcher.group(1));
        }		
	}
}
