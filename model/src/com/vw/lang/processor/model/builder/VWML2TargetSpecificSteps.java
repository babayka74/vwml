package com.vw.lang.processor.model.builder;

import com.vw.lang.processor.model.sink.CompilationSink;
import com.vw.lang.sink.ICodeGenerator.StartModuleProps;

/**
 * Abstract class which declares functionality which should be implemented by specific builder
 * @author ogibayev
 *
 */
public abstract class VWML2TargetSpecificSteps {
	
	public static abstract class Step {
		private VWMLModelBuilder modelBuilder = null;
		private CompilationSink compilationSink = null;
		
		public VWMLModelBuilder getModelBuilder() {
			return modelBuilder;
		}

		public void setModelBuilder(VWMLModelBuilder modelBuilder) {
			this.modelBuilder = modelBuilder;
		}

		public CompilationSink getCompilationSink() {
			return compilationSink;
		}

		public void setCompilationSink(CompilationSink compilationSink) {
			this.compilationSink = compilationSink;
		}
		
		public abstract void step(VWML2TargetSpecificSteps stepProcessor, String codeGeneratorName, StartModuleProps props) throws Exception;
		public abstract void correctPoperties(StartModuleProps props);
	}
	
}
