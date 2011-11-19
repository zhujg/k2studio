package controllers;

import java.util.ArrayList;
import java.util.List;

import models.Menu;
import models.Role;
import models.User;
import utils.MyUtils;
import utils.Page;
import utils.TreeUtils;

public class Menus extends Application{

	public static void index(){
		render();
	}
	
	public static void getTree(Long rid,Long uid){
		String nodes = null;
		if(rid!=null){ 
			Role role = Role.findById(rid);
			if(role!=null) nodes = role.nodes;
		}
		if(uid!=null){
			User user = User.findById(uid);
			if(user!=null) nodes = user.menu;
		}
		TreeUtils tree = new TreeUtils(Menu.class,"sort",nodes);
		renderJSON(tree.getTree());
	}
	
	public static void getItems(Long pid,String q){
		List<Object> args = new ArrayList<Object>();
		String sql = "1=1 ";
		if(pid != null){
			sql = sql + "and pid = ? ";
			args.add(pid);
		}
		if(!MyUtils.isEmpty(q)){
			sql = sql + "and name like ? ";
			args.add("%" + q + "%");
		}
		long total = Menu.count(sql, args.toArray());
		List rows = Menu.find(sql + "order by sort",args.toArray()).fetch(getPage(),getRows());
		Page page = new Page(total,rows);
		renderJSON(page.get());
	}
	
	public static void save(){
		Menu menu = new Menu();
		menu.edit("", params.all());
		menu.save();
		renderJSON(menu);
	}
	
	public static void delete(Long id){
		Menu menu = Menu.findById(id);
		if(menu!=null) menu.delete();
		renderJSON(menu);
	}
	
	public static void update(Long id){
		Menu menu = Menu.findById(id);
		menu.edit("", params.all());
		menu.save();
		renderJSON(menu);
	}
}
