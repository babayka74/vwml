package com.vw.lang.processor.tests;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.junit.Test;

import com.vw.lang.processor.model.main.VWML;
import com.vw.lang.processor.model.sink.CompilationSink;
import com.vw.lang.sink.OperationInfo;

public class VWMLInMemoryTest {
	
	public static class TCompilationSink extends CompilationSink {

		@Override
		public void skippedCode(OperationInfo opInfo) {
		}

		@Override
		public void handleInclude(String fileName) {
			System.out.println("Include '" + fileName + "'");
		}

		@Override
		public void publishModulePackage(String packageName) {
			System.out.println("Package '" + packageName + "'");
		}

		@Override
		public void publishFileLocation(String fileLocation) {
			System.out.println("Java src '" + fileLocation + "'");
		}

		@Override
		public void publishAuthor(String author) {
			System.out.println("Author '" + author + "'");
		}

		@Override
		public void publishProjectName(String projectName) {
			System.out.println("Project name '" + projectName + "'");
		}

		@Override
		public void publishProjectDescription(String projectDescription) {
			System.out.println("Project description '" + projectDescription + "'");
		}

		@Override
		public void publishModuleName(String moduleName) {
			System.out.println("Module name '" + moduleName + "'");
		}

		@Override
		public void delegateErrorCompilationMessage(OperationInfo opInfo) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void delegateStartProcessExecution(String processName) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void delegateRuntimeStreams(String processName, InputStream is,
				InputStream es) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void delegateFinishProcessExecution(String processName) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	private static final String VWMLINTERPRETER_DEF_SETTINGS = "-m scan -t static -entity ue_im3 -p verbose=true -debuginfo true";
	
	public void test1() {
		VWML vwml = new VWML();
		try {
			vwml.init();
		}
		catch (Exception e) {
			e.printStackTrace();
			return;
		}
		try {
			ff("C:\\Users\\Oleg\\projects\\vwml\\example\\msg.zip");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	
	@Test
	public void test2() {
		String buf = null;
		VWML vwml = new VWML();
		try {
			vwml.init();
			buf = readFile("C:\\Users\\Oleg\\projects\\vwml\\example\\msg\\msg.vwml");
		}
		catch (Exception e) {
			e.printStackTrace();
			return;
		}
		try {
			TCompilationSink compilationSink = new TCompilationSink();
			String args[] = VWMLINTERPRETER_DEF_SETTINGS.split(" ");
			vwml.setChunkVwmlCode(buf);
			vwml.setCompilationSink(compilationSink);
			vwml.handleArgs(args);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private String readFile(String fileName) throws Exception {
        StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new FileReader(fileName));
	    try {
	        String line = br.readLine();

	        while (line != null) {
	            sb.append(line);
	            sb.append("\r\n");
	            line = br.readLine();
	        }
	    } finally {
	        br.close();
	    }
	    return sb.toString();
	}
	
	private void ff(String archive) throws Exception {
		ZipInputStream zis = null;
		try {
			zis = new ZipInputStream(new FileInputStream(archive));
			Reader in = new InputStreamReader(zis, "UTF-8");
			ZipEntry ze = zis.getNextEntry();
			while(ze != null) {
				StringBuilder out = new StringBuilder();
				char[] buffer = new char[1024];
				for (;;) {
			        int rsz = in.read(buffer, 0, buffer.length);
			        if (rsz < 0)
			        	break;
			        out.append(buffer, 0, rsz);
			    }
				String content = out.toString();
				if (content.length() == 0) {
					ze = zis.getNextEntry();
					continue;
				}
				if (ze.getName().endsWith(".vwml")) {
					System.out.println(content);
					TCompilationSink sink = new TCompilationSink();
					VWML vwml = new VWML();
					vwml.setCompilationSink(sink);
					vwml.setChunkVwmlCode(content);
					String args[] = VWMLINTERPRETER_DEF_SETTINGS.split(" ");
					vwml.handleArgs(args);
				}
				ze = zis.getNextEntry();
			}
		}
		finally {
			if (zis != null) {
				zis.close();
			}
		}
	}
}
