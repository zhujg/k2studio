package controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.Dictionary;
import utils.Configure;
import utils.MyUtils;
import utils.Page;

public class Dictionaries extends Application{

	public static void index(){ render(); }
	
	public static void getItems(String q){
		List<Object> args = new ArrayList<Object>();
		String sql = "1=1 ";
		if(!MyUtils.isEmpty(q)){
			sql = sql + "and (field like ? or name like ? or note like ?)";
			args.add("%" + q + "%");
			args.add("%" + q + "%");
			args.add("%" + q + "%");
		}
		long total = Dictionary.count(sql, args.toArray());
		List rows = Dictionary.find(sql, args.toArray()).fetch(getPage(),getRows());
		Page page = new Page(total,rows);
		renderJSON(page.get());
	}
	
	public static void save(){
		Dictionary dictionary = new Dictionary();
		dictionary.edit("", params.all());
		dictionary.save();
		renderJSON(dictionary);
	}
	
	public static void delete(Long id){
		Dictionary dictionary = Dictionary.findById(id);
		if(dictionary!=null) dictionary.delete();
		renderJSON(dictionary);
	}
	
	public static void update(Long id){
		Dictionary dictionary = Dictionary.findById(id);
		dictionary.edit("", params.all());
		dictionary.save();
		renderJSON(dictionary);
	}
	
	public static void init(){
		Configure.initDictionary();
		renderJSON("\"ok\"");
	}
	
	public static void getCombobox(String field){
		List<Map<String,Object>> rows = new ArrayList<Map<String,Object>>();
		List<Dictionary> list = Configure.getDictionary(field);
		for(Dictionary dic : list){
			if(dic.enable==0) continue;
			Map<String,Object> row = new HashMap<String,Object>();
			row.put("id", dic.code);
			row.put("text", dic.codedesc);
			rows.add(row);
		}
		renderJSON(rows);
	}
}
