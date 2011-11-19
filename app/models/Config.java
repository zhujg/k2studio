package models;

import javax.persistence.Entity;
import javax.persistence.Column;

import play.db.jpa.Model;

@Entity(name="configs")
public class Config extends Model{

	@Column(name="`key`")
	public String key;
	@Column(name="`value`")
	public String value;
	public String note;
	
}
