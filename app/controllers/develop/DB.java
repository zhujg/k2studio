package controllers.develop;

import java.util.List;
import java.util.Map;

import utils.DBUtils;
import controllers.Application;

public class DB extends Application{

	public static void index(){
		render();
	}
	
	public static void getTableTree(){
		List<Map<String,Object>> nodes = DBUtils.getTables2Tree();
		renderJSON(nodes);
	}
	
	public static void getColumns(String table){
		List<Map<String,Object>> columns = DBUtils.getColumns(table);
		renderJSON(columns);
	}
	
}
