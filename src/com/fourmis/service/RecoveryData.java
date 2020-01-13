package com.fourmis.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class RecoveryData {

	private Map <String, Integer> allData;
	
	public RecoveryData(String url) {
		this.allData = new HashMap<>();
		Properties properties = new Properties();
		try {
			FileInputStream fileInputStream = new FileInputStream("./src/" + url);
			properties.load(fileInputStream);
			
			Enumeration<?> enumeration = properties.keys();
			while (enumeration.hasMoreElements()) {
				String key = (String) enumeration.nextElement();
				Integer value = Integer.parseInt((String) properties.get(key));
			    
				allData.put(key, value);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Map<String, Integer> getAllData() {
		return allData;
	}

}
