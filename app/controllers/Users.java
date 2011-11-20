package controllers;

import java.util.ArrayList;
import java.util.List;

import models.Depart;
import models.Role;
import models.User;
import utils.MyUtils;
import utils.Page;

public class Users extends Application{

	public static void index(){
		render();
	}
	
	public static void getItems(Long pid,String q){
		List<Object> args = new ArrayList<Object>();
		String sql = "1=1 ";
		if(pid != null && pid != 0){
			sql = sql + "and pid = ? ";
			args.add(pid);
		}
		if(!MyUtils.isEmpty(q)){
			sql = sql + "and (truename like ? or username like ?)";
			args.add("%" + q + "%");
			args.add("%" + q + "%");
		}
		long total = User.count(sql, args.toArray());
		List<User> rows = User.find(sql, args.toArray()).fetch(getPage(),getRows());
		for(int i =0;i<rows.size();i++){
			User user = rows.get(i);
			user.departName = Depart.findById(user.pid).toString();
		}
		Page<User> page = new Page<User>(total,rows);
		renderJSON(page.get());
	}
	
	public static void save(){
		User user = new User();
		user.edit("",params.all());
		user.save();
		renderJSON(user);
	}
	
	public static void delete(Long id){
		User user = User.findById(id);
		if(user!=null) user.delete();
		renderJSON(user);
	}
	
	public static void update(Long id){
		User user = User.findById(id);
		user.edit("",params.all());
		user.save();
		renderJSON(user);
	}

	public static void authorize(){
		render();
	}
	
	public static void saveAuthorize(Long id,String role,String menu){
		User user = User.findById(id);
		if(user != null) {
			user.role = role;
			user.menu = menu;
		}
		user.save();
		renderJSON(user);
	}
}
