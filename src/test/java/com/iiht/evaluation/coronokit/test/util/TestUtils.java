package com.iiht.evaluation.coronokit.test.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

// boiler-plate code
public class TestUtils {
		
		public static File businessTestFile;
		public static File boundaryTestFile;
		public static File exceptionTestFile;
		
		public static final String jdbcURL= "jdbc:mysql://localhost/coronakittestdb";
		public static final String jdbcUsername= "root";
		public static final String jdbcPassword= "";
		static {
			businessTestFile = new File("./output_revised.txt");
			businessTestFile.delete();
			
			boundaryTestFile = new File("./output_boundary_revised.txt");
			boundaryTestFile.delete();
			
			exceptionTestFile = new File("./output_exception_revised.txt");
			exceptionTestFile.delete();
		}
		
		
		public static void yakshaAssert(String testName, Object result, File file) throws IOException {
			
			System.out.println("\n" + testName + "=" + result);
			FileWriter writer = new FileWriter(file,true);
			writer.append("\n" + testName + "=" + result);
			writer.flush();
			writer.close();
		}
				
				
		
		public static String currentTest() {
			return Thread.currentThread().getStackTrace()[2].getMethodName();
		}
}
