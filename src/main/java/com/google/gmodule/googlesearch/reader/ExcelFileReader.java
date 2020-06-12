package com.google.gmodule.googlesearch.reader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelFileReader implements Reader {

	private final String DATA_FOLDER = "data";
	private final String DATA_FILE_EXT = ".xlsx";
	private String filePath;
	private String sep;
	private String baseProjectPath;
	private StringBuffer buff;
	private String cellData;
	private int totalCols;
	private int totalRows;
	private int startRow;
	private int startCol;
	private int ci, cj;
	private int rowCount;
	private Object[][] tabArray = null;
	private FileInputStream excelFile = null;
	private static XSSFSheet excelWSheet;
	private static XSSFWorkbook excelWBook;
	private static XSSFCell cell;

	/**
	 * constructor to initialize excel reader objects
	 */
	public ExcelFileReader() {
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
	 * method to read excel table and put it into a two dimensional table array
	 * 
	 * @param FilePath
	 * @param SheetName
	 * @return
	 */
	public Object[][] getTableArray(Class<? extends Object> testClass, String sheetName) {
		filePath = getFilePath(testClass);
		try {
			excelFile = new FileInputStream(filePath);
			excelWBook = new XSSFWorkbook(excelFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		excelWSheet = excelWBook.getSheet(sheetName);
		startRow = 1;
		startCol = 0;
		totalRows = excelWSheet.getLastRowNum();
		totalCols = excelWSheet.getRow(0).getLastCellNum();
		tabArray = new String[totalRows][totalCols];
		ci = 0;
		for (int i = startRow; i <= totalRows; i++, ci++) {
			cj = 0;
			for (int j = startCol; j < totalCols; j++, cj++) {
				tabArray[ci][cj] = getCellData(i, j);
			}
		}
		return (tabArray);
	}

	/**
	 * method to get individual cell data
	 * 
	 * @param rowNum
	 * @param colNum
	 * @return
	 */
	public String getCellData(int rowNum, int colNum) {
		try {
			cell = excelWSheet.getRow(rowNum).getCell(colNum);
			cellData = cell.getStringCellValue();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cellData;
	}

	/**
	 * method to get total rows in the excel sheet
	 * 
	 * @return
	 */
	public int getTotalRowsInTheExcelSheet() {
		try {
			rowCount = excelWSheet.getLastRowNum();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rowCount;
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