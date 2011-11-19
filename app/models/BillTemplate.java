package models;

import javax.persistence.Entity;

import play.db.jpa.Model;

@Entity(name="bill_template")
public class BillTemplate extends Model{

	public String billtemplate_name;
	public String nodecode;
	
}
