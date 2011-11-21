package controllers;

import java.util.ArrayList;
import java.util.List;

import models.Employee;
import models.Menu;
import utils.MyUtils;
import utils.Page;
import utils.Result;

public class Employees extends Application{

	public static void index(){ render(); }
	
	public static void getItems(Long pid,String q){
		List<Object> args = new ArrayList<Object>();
		String sql = "1=1 ";
		if(pid != null && pid > 0){
			sql = sql + "and dep = ? ";
			args.add(pid);
		}
		if(!MyUtils.isEmpty(q)){
			sql = sql + "and (name like ? or code like ?)";
			args.add("%" + q + "%");
			args.add("%" + q + "%");
		}
		if(!MyUtils.isEmpty(params.get("code"))){
			sql = sql + " and code = ?";
			args.add(params.get("code"));
		}
		if(!MyUtils.isEmpty(params.get("name"))){
			sql = sql + " and name like ?";
			args.add("%" + params.get("name") + "%");
		}
		if(!MyUtils.isEmpty(params.get("sex"))){
			sql = sql + " and sex = ?";
			args.add(params.get("sex"));
		}
		if(!MyUtils.isEmpty(params.get("jg"))){
			sql = sql + " and jg = ?";
			args.add(params.get("jg"));
		}
		if(!MyUtils.isEmpty(params.get("flag"))){
			sql = sql + " and flag = ?";
			args.add(Integer.parseInt(params.get("flag")));
		}
		if(!MyUtils.isEmpty(params.get("dep"))){
			sql = sql + " and dep = ?";
			args.add(Long.parseLong(params.get("dep")));
		}
		long total = Employee.count(sql, args.toArray());
		List rows = Employee.find(sql,args.toArray()).fetch(getPage(),getRows());
		Page page = new Page(total,rows);
		renderJSON(page.get());
	}

	public static void create(){
		render();
	}
	
	public static void save(Employee emp){
		emp.save();
		Result result = new Result(true,emp.id);
		renderJSON(result);
	}
	
	public static void edit(Long id){
		Employee emp = Employee.findById(id);
		if(emp==null) {
			Result result = new Result();
			result.message = "数据不存在";
			renderJSON(result);
		}
		render(emp);
	}
	
	public static void update(Employee emp){
		emp.save();
		Result result = new Result(true,emp.id);
		renderJSON(result);
	}
	
	public static void delete(Long id){
		Employee emp = Employee.findById(id);
		if(emp!=null) emp.delete();
		Result result = new Result(true,emp.id);
		renderJSON(result);
	}
	
}
