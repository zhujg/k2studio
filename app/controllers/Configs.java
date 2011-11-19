package controllers;

import java.util.ArrayList;
import java.util.List;

import models.Config;
import utils.Configure;
import utils.MyUtils;
import utils.Page;

public class Configs extends Application{

	public static void index(){
		render();
	}
	
	public static void getItems(String q){
		List<Object> args = new ArrayList<Object>();
		String sql = "1=1";
		if(!MyUtils.isEmpty(q)){
			sql = "key like ? or value like ?";
			q = "%" + q + "%";
			args.add(q);
			args.add(q);
		}
		long total = Config.count(sql,args.toArray());
		List list = Config.find(sql,args.toArray()).fetch(getPage(), getRows());
		Page page = new Page(total,list);
		renderJSON(page.get());
	}
	
	public static void save(){
		Config config = new Config();
		config.edit("", params.all());
		config.save();
		renderJSON(config);
	}
	
	public static void delete(Long id){
		Config config = Config.findById(id);
		if(config!=null) config.delete();
		renderJSON(config);
	}
	
	public static void edit(Long id){
		Config config = Config.findById(id);
		renderJSON(config);
	}
	
	public static void update(Long id){
		Config config = Config.findById(id);
		config.edit("", params.all());
		config.save();
		renderJSON(config);
	}
	
	public static void init(){
		Configure.init();
		renderJSON("\"ok\"");
	}
	
}
