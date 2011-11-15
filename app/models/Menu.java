package models;

import javax.persistence.Entity;

import play.db.jpa.Model;

@Entity(name="menus")
public class Menu extends Model{

	public Long pid;
	public String name;
	public String url;
	public Integer sort;
	public String icon;
	
}
