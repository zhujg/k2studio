package models;

import javax.persistence.Entity;

import play.db.jpa.Model;

@Entity(name="dictionarys")
public class Dictionary extends Model{

	public String field;
	public String name;
	public String code;
	public String codedesc;
	public Integer enable;
	public Integer editmode;
	public String note;
	
}
