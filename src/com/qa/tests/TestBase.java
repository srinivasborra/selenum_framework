package com.qa.tests;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Properties;


public class TestBase {
	public TestBase(){
		Properties prop = new Properties();

		try {
			Path path = FileSystems.getDefault().getPath("").toAbsolutePath();
			prop.load(new FileInputStream(new File(path + "/com/qa/tests/config.properties")));
			
			
			System.out.println("Initial Properties are loaded");
		}catch(Exception ex){}
		//initProperties();
	}
	
	public Properties initProperties() {
		Properties prop = new Properties();

		try {
			Path path = FileSystems.getDefault().getPath("").toAbsolutePath();
			prop.load(new FileInputStream(new File(path + "/src/com/qa/tests/config.properties")));
			
			
			System.out.println("Initial Properties are loaded");
	
	} catch (Exception e) {
		System.out.println(e.getMessage());
	}
	return prop;
}
}
