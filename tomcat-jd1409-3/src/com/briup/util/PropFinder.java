package com.briup.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropFinder {
	private static Properties prop;
	static {
		prop = new Properties();
		try {
			prop.load(new FileInputStream(new File("src/com/briup/util/config.properties")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getProp(String name){
		String value = prop.getProperty(name);
		return value;
	}
}
