package com.sematree.elastic.utils;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

public class MainConfig {
	private static MainConfig instance = null;
	
	private static Configuration config;
	
	public MainConfig() {
		try {
			config = new PropertiesConfiguration("esclient-config.properties");
		}catch (ConfigurationException e) {
			System.err.println("Error reading configuration file:");
			e.printStackTrace();
		}
	}
	
	public static MainConfig getInstance() {
		if (instance == null) {
			instance = new MainConfig();
		}
		
		return instance;
	}
	
	public String getString(String configName) {
		return config.getString(configName);
	}
	
	public int getInt(String configName) {
		return config.getInt(configName);
	}
	
	public String[] getStringArray(String configName) {
		return config.getStringArray(configName);
	}
}
