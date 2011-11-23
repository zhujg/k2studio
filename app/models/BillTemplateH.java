package models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import play.db.jpa.Model;

@Entity(name="bill_template_h")
public class BillTemplateH extends Model{
	
	@ManyToOne public BillTemplate bt;
	public Integer pos;
	public Integer tabindex;
	public String tabcode;
	public String tabname;
	public String tablename;
	public String tablecode;
	
	public BillTemplateH(String tablename) {
		this.tablename = tablename;
		this.tablecode = tablename;
		this.pos = 1;
		this.tabindex = 1;
	}
	
}
