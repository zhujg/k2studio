package utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.Config;


public class Configure {

	public static Map<String,String> configures = new HashMap<String,String>();
	
	public static void init(){
		configures.clear();
		List<Config> configs = Config.findAll();
		for(Config config : configs){
			configures.put(config.key, config.value);
		}
	}
	
	public static String get(String key){
		return configures.get(key);
	}
}
