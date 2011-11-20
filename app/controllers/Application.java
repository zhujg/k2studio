package controllers;

import java.util.HashMap;
import java.util.Map;

import models.Menu;
import play.mvc.Controller;
import play.mvc.With;
import utils.Page;

@With(Secure.class)
public class Application extends Controller {

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