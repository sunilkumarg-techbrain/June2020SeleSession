package com.google.gmodule.googlesearch.reader;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * PropertyFileReader class to read from property file
 * @author Sunil kumar
 *
 */
public class PropertyFileReader implements Reader {

	/**
	 * method to read
	 */
	@Override
	public String read() {
		return "Property file reading";

	}

	/**
	 * method to get property values
	 * @param key
	 * @param propFilePath
	 * @return
	 * @throws IOException
	 */
	public static String getPropValues(String key, String propFilePath){

		InputStream input = null;
		String result = "";
		try {
			Properties prop = new Properties();
			input = new FileInputStream(propFilePath);
			if (input != null) {
				prop.load(input);
			}

			result = prop.getProperty(key);

		} catch (IOException e) {
			System.out.println("Exception: " + e);
		} finally {
			try {
				input.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

}
