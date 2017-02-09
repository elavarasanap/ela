package com.java.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

public class ApplicationPropertyReader {
	
	public String readPropertyFile(String path ,String propertykey){
		Properties prop = new Properties();
		InputStream input = null;
		File currentDirFile = new File(".");
		try {
			input = new FileInputStream(path);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		try {
			prop.load(input);
		} catch (IOException e) {
			e.printStackTrace();
		}
	    String propertyValue = prop.getProperty(propertykey);
	    System.out.println(propertyValue);
		return propertyValue;
		
	}
	public static void main (String[] args){
		
		ApplicationPropertyReader reader = new ApplicationPropertyReader();
		reader.readPropertyFile("src/main/resources/config/application.properties","w_keystorefile");
		reader.readPropertyFile("D:/elavarasan/elagit/trunk/hethi/hethi.services/src/main/resources/config/application.properties", "w_keystorepass");
	}

}
