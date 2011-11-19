package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Transient;

import play.db.jpa.Model;
import utils.Configure;

@Entity(name="roles")
public class Role extends Model{

	public Long pid;
	public String name;
	public Integer flag;
	public String note;
	public String nodes;
	
	@Transient
	public String departName;
	
	public static List<Map<String,Object>> getTree(String node_role){
		List<Map<String,Object>> nodes = new ArrayList<Map<String,Object>>();
		Map<String,Object> root = new HashMap<String,Object>();
		root.put("id", 0);
		root.put("text",Configure.get("SYS_COMPANY_NAME"));
		nodes.add(root);
		
		List<Map<String,Object>> parent = new ArrayList<Map<String,Object>>();
		List<Role> roles = Role.findAll();
		for(Role role : roles){
			Map<String,Object> node = new HashMap<String,Object>();
			node.put("id", role.id);
			node.put("text", role.name);
			if(node_role!=null && ("," + node_role + ",").indexOf("," + role.id + ",")>-1 ){
				node.put("checked", true);
			}else{
				node.put("checked",false);
			}
			parent.add(node);
		}
		root.put("children", parent.toArray(new Object[parent.size()]));
		return nodes;
	}
}
