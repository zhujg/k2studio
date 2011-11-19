package utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import models.Menu;
import models.TreeModel;
import play.db.jpa.JPA;
import sun.reflect.generics.tree.Tree;

public class TreeUtils {

	private String className;
	private String sort;
	private String nodes;
	
	public TreeUtils(Class clazz,String sort) {
		this(clazz,sort,null);
	}
	
	public TreeUtils(Class clazz,String sort,String nodes) {
		this.className = clazz.getName();
		this.sort = sort;
		this.nodes = nodes;
	}
	
	public List<Map<String,Object>> getTree(){
		List<Map<String,Object>> nodes = new ArrayList<Map<String,Object>>();
		
		Map<String,Object> root = new HashMap<String,Object>();
		root.put("id", 0);
		root.put("text",Configure.get("SYS_COMPANY_NAME"));
		nodes.add(root);
		
		String sql = "from " + className + " where pid =0";
		if(!MyUtils.isEmpty(sort)) sql = sql + " order by " + sort;
		
		Query query = JPA.em().createQuery(sql);
		List<TreeModel> rows = query.getResultList();
		List<Map<String,Object>> parent = new ArrayList<Map<String,Object>>();
		for(int i = 0;i<rows.size();i++){
			TreeModel tree = rows.get(i);
			Map<String,Object> node = new HashMap<String,Object>();
			node.put("id", tree.id);
			node.put("text", tree.name);
			
			parent.add(node);
		}
		
		root.put("children", parent.toArray(new Object[parent.size()]));
		List<Map<String,Object>> doing = new ArrayList<Map<String,Object>>();
		doing.addAll(parent);
		
		while(!doing.isEmpty()){
			List<Map<String,Object>> todo = new ArrayList<Map<String,Object>>();
			for(Map<String,Object> item: doing){
				sql = "from " + className + " where pid =" + item.get("id");
				if(!MyUtils.isEmpty(sort)) sql = sql + " order by " + sort;
				query = JPA.em().createQuery(sql);
				rows = query.getResultList();
				if(rows.isEmpty()) continue;
				
				List<Object> children = new ArrayList<Object>();
				for(int i = 0;i<rows.size();i++){
					TreeModel tree = rows.get(i);
					Map<String,Object> node = new HashMap<String,Object>();
					node.put("id", tree.id);
					node.put("text", tree.name);
					if(this.nodes!=null && ("," + this.nodes + ",").indexOf("," + tree.id + ",")>-1 ){
						node.put("checked", true);
					}else{
						node.put("checked",false);
					}
					children.add(node);
					todo.add(node);
				}
				item.put("children", children.toArray(new Object[children.size()]));
			}
			doing = todo;
		}
		
		return nodes;
	}
}
