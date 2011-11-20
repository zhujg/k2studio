package utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.Config;
import models.Dictionary;


public class Configure {

	public static Map<String,String> configures = new HashMap<String,String>();
	public static Map<String,List<Dictionary>> dictionaries = new HashMap<String,List<Dictionary>>(); 
	
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
	
	public static void initDictionary(){
		dictionaries.clear();
		List<String> list = Dictionary.find("select field from models.Dictionary group by field").fetch();
		for(String field : list ){
			List<Dictionary> rows = Dictionary.find("field = ?", field).fetch();
			dictionaries.put(field, rows);
		}
	}
	
	public static List<Dictionary> getDictionary(String field){
		return dictionaries.get(field);
	}
}
