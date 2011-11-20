package controllers;

import infoplay.JavaInfo;

import java.util.ArrayList;
import java.util.List;

import play.Play;
import play.templates.FastTags;
import play.templates.JavaExtensions;
import utils.Page;

import models.Property;
import models.Role;

public class ServerInfo extends Application{

	public static void index(){
		render();
	}
	
	public static void getItems(){
		JavaInfo javaInfo = new JavaInfo();
		List<Property> rows = new ArrayList<Property>();
		Property property = new Property("a.操作系统",javaInfo.osname);
		rows.add(property);
		property = new Property("b.主机域名",request.domain);
		rows.add(property);
		property = new Property("c.监听端口",request.port.toString());
		rows.add(property);
		property = new Property("d.Web根路径",Play.applicationPath.getAbsolutePath());
		rows.add(property);
		property = new Property("e.Play版本",Play.version);
		rows.add(property);
		property = new Property("f.JVM版本",javaInfo.javaversion);
		rows.add(property);
		property = new Property("g.JVM提供商",javaInfo.javavendor);
		rows.add(property);
		property = new Property("h.JVM安装路径",javaInfo.javahome);
		rows.add(property);
		property = new Property("i.JVM可用最大内存",JavaExtensions.formatSize(Long.parseLong(javaInfo.vmmax)));
		rows.add(property);
		Page<Property> page = new Page<Property>(9,rows);
		renderJSON(page.get());
	}
}
