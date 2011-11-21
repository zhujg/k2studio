package controllers.develop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.TreeModel;

import utils.Configure;
import utils.DBUtils;
import utils.MyUtils;
import controllers.Application;

public class BillTemplates extends Application{

	public static void index(){
		render();
	}
	
	public static void filterTable(boolean filter,String name){
		List<String> tables = DBUtils.getTables();
		List<String> list = new ArrayList<String>();
		if(MyUtils.isEmpty(name)){
			renderJSON(tables);
		}
		for(String table : tables){
			if(filter){
				if(table.startsWith(name)) list.add(table);
			}else{
				if(table.indexOf(name) > -1) list.add(table);
			}
		}
		renderJSON(list);
	}
	
}
