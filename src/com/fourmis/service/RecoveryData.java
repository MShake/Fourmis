package com.fourmis.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class RecoveryData {
	
	private Properties properties;
	private FileInputStream fileInputStream;
	private Map <String, Integer> allData;
	
	public RecoveryData(String url) {
		this.allData = new HashMap<String, Integer>();
		this.properties = new Properties();
		try {
			this.fileInputStream = new FileInputStream("./src/"+url);
			this.properties.load(this.fileInputStream);
			
			Enumeration<?> enumeration = this.properties.keys();
			while (enumeration.hasMoreElements()) {
				String key = (String) enumeration.nextElement();
				Integer value = Integer.parseInt((String) this.properties.get(key));
			    
				allData.put(key, value);
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void closeFile(){
		try {
			this.fileInputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	public FileInputStream getFileInputStream() {
		return fileInputStream;
	}

	public void setFileInputStream(FileInputStream fileInputStream) {
		this.fileInputStream = fileInputStream;
	}

	public Map<String, Integer> getAllData() {
		return allData;
	}

	public void setAllData(Map<String, Integer> allData) {
		this.allData = allData;
	}

}
