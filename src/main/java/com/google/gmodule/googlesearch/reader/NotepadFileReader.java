package com.google.gmodule.googlesearch.reader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 * NotepadReader class to read from note pad 
 * @author Sunil kumar
 *
 */
public class NotepadFileReader implements Reader{
	private int i;
	private int recordCount;
	InputStream is;
	private String filePath;
	private String sep;
	private String baseProjectPath;
	private StringBuffer buff;
	private final String DATA_FOLDER = "data";
	private final String DATA_FILE_EXT = ".txt";
	private Object[][] tabArray = null;
	ExcelFileReader excelFileReader;
	String DATA_SPLIT_REGEX = "\\|";
	String[] columnHeaderData;
	String DATA_SET_PREFIX = "$";
	String line;
	Scanner scanner;
	String class1;
	private Object[][] hashMapObj;
	HashMap<String, String> hashMapItems;
	private String[] columnHeaderArray;
	private List<HashMap<String, String>> arrayMapList;

	/**
	 * constructor to initialize elements of note pad reader objects
	 */
	public NotepadFileReader() {
		excelFileReader = new ExcelFileReader();
	}

	/**
	 * method to read excel table and put it into a two dimensional table array
	 * 
	 * @param FilePath
	 * @param SheetName
	 * @return
	 */
	public int getNotapadRecordCount(Class<? extends Object> testClass, String datasetName) {
		filePath = getFilePath(testClass);
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(filePath));
		} catch (FileNotFoundException e1) {
		}
		try {
			String line;
			while ((line = reader.readLine()) != null) {
				if (line.trim().startsWith(DATA_SET_PREFIX) && line.trim().contains(datasetName)) {
					{
						line = reader.readLine();
						recordCount = 0;
						while ((line = reader.readLine()) != null) {
							columnHeaderData = getDataSet(line);
							recordCount++;
						}
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return recordCount;
	}

	/**
	 * method to read excel table and put it into a two dimensional table array
	 * 
	 * @param FilePath
	 * @param SheetName
	 * @return
	 */
	public Object[][] getTableArrayFromNotePad(Class<? extends Object> testClass, String datasetName) {
		filePath = getFilePath(testClass);
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(filePath));
		} catch (FileNotFoundException e1) {
		}
		try {
			String line;
			while ((line = reader.readLine()) != null) {
				if (line.trim().startsWith(DATA_SET_PREFIX) && line.trim().contains(datasetName)) {
					{
						line = reader.readLine();
						tabArray = new String[getNotapadRecordCount(testClass, datasetName)][columnHeaderData.length];
						i = 0;
						while ((line = reader.readLine()) != null) {
							columnHeaderData = getDataSet(line);
							for (int j = 0; j < columnHeaderData.length; j++) {
								tabArray[i][j] = columnHeaderData[j];
							}
							i++;
						}
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return tabArray;
	}

	/**
	 * method to create hash map obj
	 * 
	 * @param testClass
	 * @param datasetName
	 * @return
	 */
	public Object[][] createHashMapObj(Class<? extends Object> testClass, String datasetName) {
		arrayMapList = new ArrayList<HashMap<String, String>>();
		columnHeaderArray = getColumnHeaderFromTestData(testClass, datasetName);
		try {
			tabArray = getTableArrayFromNotePad(testClass, datasetName);
		} catch (Exception e) {
		}
		for (int i = 0; i < tabArray.length; i++) {
			hashMapItems = new HashMap<String, String>();
			for (int j = 0; j < columnHeaderArray.length; j++) {
				hashMapItems.put(columnHeaderArray[j], tabArray[i][j].toString());
			}
			arrayMapList.add(hashMapItems);
		}
		hashMapObj = new Object[arrayMapList.size()][1];
		for (int i = 0; i < arrayMapList.size(); i++) {
			hashMapObj[i][0] = arrayMapList.get(i);
		}
		return hashMapObj;
	}

	/**
	 * method to get column header from test data
	 * 
	 * @param testClass
	 * @param datasetName
	 * @return
	 */
	public String[] getColumnHeaderFromTestData(Class<? extends Object> testClass, String datasetName) {
		class1 = getDataFilePathForClass(testClass);
		InputStream is = null;
		try {
			is = ClassLoader.getSystemResourceAsStream(class1);
		} catch (IllegalArgumentException e) {
		}
		scanner = new Scanner(is);
		while (scanner.hasNextLine()) {
			line = scanner.nextLine();
			if (line.trim().startsWith(DATA_SET_PREFIX) && line.trim().contains(datasetName)) {
				line = scanner.nextLine();
				columnHeaderData = getDataSet(line);
				break;
			}
		}
		scanner.close();
		return columnHeaderData;
	}

	/**
	 * method to get data file path for class
	 * 
	 * @param testClass
	 * @return
	 */
	public String getDataFilePathForClass(Class<? extends Object> testClass) {
		String clazz = testClass.getName();
		String sep = System.getProperty("file.separator");
		StringBuffer buff = new StringBuffer();
		buff.append(DATA_FOLDER);
		buff.append(sep);
		buff.append(clazz.replace(".", sep));
		buff.append(DATA_FILE_EXT);
		return buff.toString();
	}

	/**
	 * method to get data set
	 * 
	 * @param line
	 * @return
	 */
	private String[] getDataSet(String line) {
		return line.trim().split(DATA_SPLIT_REGEX);
	}

	/**
	 * method to get file path
	 * 
	 * @return String
	 */
	public String getFilePath(Class<? extends Object> testClass) {
		sep = System.getProperty("file.separator");
		baseProjectPath = System.getProperty("user.dir") + "\\";
		buff = new StringBuffer();
		buff.append(baseProjectPath);
		buff.append("src\\test\\resources\\");
		buff.append(DATA_FOLDER);
		buff.append(sep);
		buff.append(testClass.getName().replace(".", sep));
		buff.append(DATA_FILE_EXT);
		return buff.toString();
	}

	/**
	 * method to read file 
	 */
	@Override
	public String read() {
		// TODO Auto-generated method stub
		return null;
	}

}
