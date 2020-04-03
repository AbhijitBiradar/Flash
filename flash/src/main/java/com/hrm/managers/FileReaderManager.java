package com.hrm.managers;

import com.hrm.readers.CSVFileReader;
import com.hrm.readers.ConfigFileReader;
import com.hrm.readers.ExcelFileReader;
import com.hrm.readers.JsonFileReader;
import com.hrm.readers.LogFileReader;
import com.hrm.readers.XMLFileReader;

public class FileReaderManager {
	private static FileReaderManager fileReaderManager = new FileReaderManager();
	private static ConfigFileReader configFileReader;
	private static JsonFileReader jsonFileReader;
	private static CSVFileReader csvFileReader;
	private static ExcelFileReader excelFileReader;
	private static LogFileReader logFileReader;
	private static XMLFileReader xmlFileReader;
	

	private FileReaderManager() {
	}

	public static FileReaderManager getInstance() {
		return fileReaderManager;
	}

	public ConfigFileReader getConfigReader() {
		return (configFileReader == null) ? new ConfigFileReader() : configFileReader;
	}

	public JsonFileReader getJsonReader() {
		return (jsonFileReader == null) ? new JsonFileReader() : jsonFileReader;
	}
	
	public CSVFileReader getCSVReader() {
		return (csvFileReader == null) ? new CSVFileReader() : csvFileReader;
	}
	
	public ExcelFileReader getExcelReader() {
		return (excelFileReader == null) ? new ExcelFileReader() : excelFileReader;
	}
	
	public LogFileReader getLogReader() {
		return (logFileReader == null) ? new LogFileReader() : logFileReader;
	}
	
	public XMLFileReader getXmlReader() {
		return (xmlFileReader == null) ? new XMLFileReader() : xmlFileReader;
	}
	//Log mechanish is pendng and need to write for every method
}
