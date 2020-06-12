package com.google.gmodule.googlesearch.reader;

/**
 * ReaderFactory to getREader based on the file type
 * 
 * @author Sunil kumar
 *
 */
public class ReaderFactory {

	/**
	 * method to get reader based on the file type
	 * 
	 * @param readerType
	 * @return
	 */
	public Reader getReader(String readerType) {

		Reader reader = null;
		if (readerType.equalsIgnoreCase("CSV")) {
			reader = new CSVReader();
		} else if (readerType.equalsIgnoreCase("properties")) {
			reader = new PropertyFileReader();
		} else if (readerType.equalsIgnoreCase("xlsx")) {
			reader = new ExcelFileReader();
		} else if (readerType.equalsIgnoreCase("txt")) {
			reader = new NotepadFileReader();
		}

		return reader;
	}

}
