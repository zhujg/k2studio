package models;

import javax.persistence.Entity;

import play.db.jpa.Model;

@Entity(name="departs")
public class Depart extends TreeModel{

	public String code;
	public Integer sort;
	public String note;
	
	@Override
	public String toString() {
		return name;
	}
}
