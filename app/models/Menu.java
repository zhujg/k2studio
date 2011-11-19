package models;

import java.util.List;

import javax.persistence.Entity;

import play.mvc.Scope.Session;
import utils.MyUtils;

@Entity(name="menus")
public class Menu extends TreeModel{

	public String url;
	public Integer sort;
	public String icon;
	
	public static String loadMenus(){
		User user = User.find("username",Session.current().get("username")).first();
		if(user==null) return "";
		String menuid = user.menu == null ? "" : user.menu;
		List<Role> roles = Role.find("id in (" + user.role + ")").fetch();
		for(Role role :roles){
			if(!MyUtils.isEmpty(role.nodes)){
				if(MyUtils.isEmpty(menuid)) menuid = role.nodes;
				else menuid = menuid + "," +role.nodes;
			}
		}
		StringBuffer sb = new StringBuffer("{\"menus\":[");
		List<Menu> list = Menu.find("pid=0 and id in (" + menuid +") order by sort").fetch();
		for(int i=0;i<list.size();i++){
			Menu menu = list.get(i);
			sb.append("{\"menuid\":\""+menu.id+"\",\"icon\":\""+menu.icon+"\",\"menuname\":\""+menu.name+"\",\"menus\":[");
			List<Menu> subMenus = Menu.find("pid=? and id in ("+ menuid +") order by sort", menu.id).fetch();
			for(int j=0;j<subMenus.size();j++){
				Menu sub = subMenus.get(j);
				sb.append("{\"menuid\":\""+sub.id+"\",\"menuname\":\""+sub.name+"\",\"icon\":\""+sub.icon+"\",\"url\":\""+sub.url+"\"}");
				if(subMenus.size() - j != 1) sb.append(",");
			}
			sb.append("]}");
			if(list.size()-i != 1) sb.append(",");
		}
		sb.append("]}");
		return sb.toString();
	}
}

