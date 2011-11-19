package controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.Depart;
import models.Role;
import models.TreeModel;
import models.User;
import utils.MyUtils;
import utils.Page;

public class Roles extends Application{

	public static void index(){
		render();
	}
	
	public static void getTree(Long uid){
		String node_role = null;
		if(uid!=null){
			User user = User.findById(uid);
			if(user!=null) node_role = user.role;
		}
		List<Map<String,Object>> nodes = Role.getTree(node_role);
		renderJSON(nodes);
	}
	
	public static void getItems(Long pid,String q){
		List<Object> args = new ArrayList<Object>();
		String sql = "1=1 ";
		if(pid != null && pid != 0){
			sql = sql + "and pid = ? ";
			args.add(pid);
		}
		if(!MyUtils.isEmpty(q)){
			sql = sql + "and name like ? ";
			args.add("%" + q + "%");
		}
		long total = Role.count(sql, args.toArray());
		List<Role> rows = Role.find(sql, args.toArray()).fetch(getPage(),getRows());
		for(int i =0;i<rows.size();i++){
			Role role = rows.get(i);
			role.departName = Depart.findById(role.pid).toString();
		}
		Page<Role> page = new Page<Role>(total,rows);
		renderJSON(page.get());
	}
	
	public static void save(){
		Role role = new Role();
		role.edit("",params.all());
		role.save();
		renderJSON(role);
	}
	
	public static void delete(Long id){
		Role role = Role.findById(id);
		if(role!=null) role.delete();
		renderJSON(role);
	}
	
	public static void update(Long id){
		Role role = Role.findById(id);
		role.edit("",params.all());
		role.save();
		renderJSON(role);
	}
	
	public static void saveAuthorize(Long id,String nodes){
		Role role = Role.findById(id);
		if(role != null) role.nodes = nodes;
		role.save();
		renderJSON(role);
	}
}
