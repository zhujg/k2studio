package controllers;

import java.util.ArrayList;
import java.util.List;

import models.Depart;
import models.Menu;
import utils.MyUtils;
import utils.Page;
import utils.TreeUtils;

public class Departs extends Application{

	public static void index(){
		render();
	}
	
	public static void getTree(){
		TreeUtils tree = new TreeUtils(Depart.class,null);
		renderJSON(tree.getTree());
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
		long total = Depart.count(sql, args.toArray());
		List rows = Depart.find(sql + "order by sort",args.toArray()).fetch(getPage(),getRows());
		Page page = new Page(total,rows);
		renderJSON(page.get());
	}
	
	public static void save(){
		Depart depart = new Depart();
		depart.edit("",params.all());
		depart.save();
		renderJSON(depart);
	}
	
	public static void delete(Long id){
		Depart depart = Depart.findById(id);
		if(depart!=null) depart.delete();
		renderJSON(depart);
	}
	
	public static void update(Long id){
		Depart depart = Depart.findById(id);
		depart.edit("",params.all());
		depart.save();
		renderJSON(depart);
	}
}
