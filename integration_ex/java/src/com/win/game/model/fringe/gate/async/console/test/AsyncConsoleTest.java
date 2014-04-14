package com.win.game.model.fringe.gate.async.console.test;

import org.junit.Before;
import org.junit.Test;

import com.vw.lang.beyond.java.fringe.entity.EWEntity;
import com.vw.lang.beyond.java.fringe.entity.EWEntityBuilder;
import com.vw.lang.beyond.java.fringe.gate.console.Console;
import com.win.game.model.fringe.gate.async.console.AsyncConsole;

public class AsyncConsoleTest {

	private AsyncConsole console = AsyncConsole.instance();

	@Before
	public void init() {
	}
	
	@Test
	public void test1() throws Exception {
		EWEntity eColor = EWEntityBuilder.buildFromString("colors");
		EWEntity eInit = EWEntityBuilder.buildFromString("(colors (red green blue))");
		console.invokeEW(AsyncConsole.getInitMethod(), eInit);
		while(true) {
			EWEntity e = console.invokeEW(Console.getInMethod(), eColor);
			if (e != null && !e.getId().equals(EWEntity.s_NilEntityId)) {
				console.invokeEW(Console.getOutMethod(), e);
			}
			try {
				Thread.sleep(500);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}
	}
}
