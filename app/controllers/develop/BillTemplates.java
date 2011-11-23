package controllers.develop;

import java.util.ArrayList;
import java.util.List;

import models.BillTemplate;
import models.BillTemplateH;
import utils.DBUtils;
import utils.MyUtils;
import controllers.Application;

public class BillTemplates extends Application{

	public static void index(){
		render();
	}
	
	public static void filterTable(boolean filter,String name){
		List<String> tables = DBUtils.getTables();
		List<String> list = new ArrayList<String>();
		if(MyUtils.isEmpty(name)){
			renderJSON(tables);
		}
		for(String table : tables){
			if(filter){
				if(table.startsWith(name)) list.add(table);
			}else{
				if(table.indexOf(name) > -1) list.add(table);
			}
		}
		renderJSON(list);
	}
	
	public static void save(String nodecode,String list1,String list2){
		BillTemplate  bt = new BillTemplate();
		bt.nodecode = nodecode;
		bt.save();
		
		BillTemplateH bth = new BillTemplateH(list1);
		bth.bt = bt;
		bth.pos = 1;
		
		bth.save();
	}
	
	public static void query(String nodecode){
		List<BillTemplate> list = BillTemplate.find("nodecode like ? ","%" + nodecode + "%").fetch();
		renderJSON(list);
	}
	
	public static void edit(Long id){
		BillTemplate  bt = BillTemplate.findById(id);
		BillTemplateH bth_head = BillTemplateH.find("bt =? and pos = 1",bt).first();
		render(bt,bth_head);
	}
}
