package controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.Menu;

public class Menus extends Application{

	public static void index(){
		render();
	}
	
	public static void getTree(){
		List<Map<String,Object>> nodes = new ArrayList<Map<String,Object>>();
		
		Map<String,Object> root = new HashMap<String,Object>();
		root.put("id", 0);
		root.put("text","K2Studio系统集成与开发平台");
		nodes.add(root);
		
		List<Menu> list = Menu.find("pid=0").fetch();
		List<Map<String,Object>> nodes1 = new ArrayList<Map<String,Object>>();
		for(Menu menu : list){
			Map<String,Object> node = new HashMap<String,Object>();
			node.put("id", menu.id);
			node.put("text", menu.name);
			nodes1.add(node);
		}
		
		root.put("children", nodes1.toArray(new Object[nodes1.size()]));
		
		List<Map<String,Object>> doing = new ArrayList<Map<String,Object>>();
		doing.addAll(nodes1);
		
		while(!doing.isEmpty()){
			List<Map<String,Object>> todo = new ArrayList<Map<String,Object>>();
			for(Map<String,Object> item: doing){
				list = Menu.find("pid",item.get("id")).fetch();
				if(list.isEmpty()) continue;
				
				List<Object> children = new ArrayList<Object>();
				for(Menu menu : list){
					Map<String,Object> node = new HashMap<String,Object>();
					node.put("id", menu.id);
					node.put("text", menu.name);
					children.add(node);
					todo.add(node);
				}
				item.put("children", children.toArray(new Object[children.size()]));
			}
			doing = todo;
		}
		

		renderJSON(nodes);
	}
	
	public static void getItems(Long pid){
		Map<String,Object> result = new HashMap<String,Object>();
		List<Menu> list = null;
		if(pid == null) list = Menu.findAll();
		else list = Menu.find("pid=?",pid).fetch();
		result.put("total", list.size());
		result.put("rows", list);
		renderJSON(result);
	}
	
	public static void save(Menu menu){
		menu.save();
		renderJSON(menu);
	}
	
	public static void delete(Long id){
		Menu menu = Menu.findById(id);
		if(menu!=null) menu.delete();
		renderJSON(menu);
	}
}
