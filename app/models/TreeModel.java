package models;

import javax.persistence.MappedSuperclass;

import play.db.jpa.Model;

@MappedSuperclass
public class TreeModel extends Model{

	public Long pid;
	public String name;
	
}
