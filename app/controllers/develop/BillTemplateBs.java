package controllers.develop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.BillTemplateB;
import models.BillTemplateH;
import models.Propertygrid;
import utils.Result;
import controllers.Application;

public class BillTemplateBs extends Application{

	public static void getItems(Long id){
		List<BillTemplateB> list = BillTemplateB.find("bth.id = ? order by showorder", id).fetch();
		renderJSON(list);
	}
	
	public static void save(Long bid) {
		BillTemplateH bth = BillTemplateH.findById(bid);
		BillTemplateB btb = new BillTemplateB();
		btb.edit("", params.all());
		btb.bth = bth;
		btb.save();
		Result result = new Result(true,null);
		renderJSON(result);
	}
	
	public static void getField(Long id){
		BillTemplateB bill = null;
		if(id==null || id ==0){
			bill = new BillTemplateB();
		}else{
			bill = BillTemplateB.findById(id);
		}
		renderJSON(bill.getPropertys());
	}
	
	
	public static void update() {};
	public static void delete() {};
}
