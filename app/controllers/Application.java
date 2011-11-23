package controllers;

import models.Menu;
import play.mvc.Before;
import play.mvc.Controller;
import play.mvc.Finally;
import play.mvc.With;

import com.fdsapi.ResultSetConverter;
import com.fdsapi.ResultSetConverter.Row;
import com.fdsapi.arrays.ArraySQL;
import com.jamonapi.Monitor;
import com.jamonapi.MonitorFactory;
import com.jamonapi.proxy.MonProxyFactory;

@With(Secure.class)
public class Application extends Controller {

	private static Monitor monitor;
	
	@Before
	static void monitor_start(){
		monitor = MonitorFactory.start(request.url);
	}
	
	@Finally
	static void monitor_end(){
		monitor.stop();
	}
    public static void index() {
    	String menus = Menu.loadMenus();
        render(menus);
    }
    
    public static int getPage(){
    	int page = params.get("page",Integer.class);
    	return page == 0 ? 1 : page;
    }
    
    public static int getRows(){
    	int rows = params.get("rows",Integer.class);
    	return rows == 0 ? 10 : rows;
    }

	public static void query(){ render(); }
	
}