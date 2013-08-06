package com.vw.lang.sink.java.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.vw.lang.sink.java.code.JavaCodeGenerator.ModuleFiles;

public class VWMLModuleEnumTest {

	@Test
	public void test() {
		assertTrue(ModuleFiles.fromValue("Repository").ordinal() == ModuleFiles.REPOSITORY.ordinal());
		assertTrue(ModuleFiles.numValues() == 3);
		assertTrue(ModuleFiles.index(1).ordinal() == ModuleFiles.REPOSITORY.ordinal());
		assertTrue(ModuleFiles.index(ModuleFiles.REPOSITORY.toValue()) == 1);
	}
}
