package models;

import javax.persistence.Entity;
import javax.persistence.Transient;

import play.db.jpa.Model;

@Entity(name="users")
public class User extends Model{

	public String username;
	public String password;
	public String truename;
	public Integer flag;
	public Integer sex;
	public String note;
	public Long pid;
	@Transient
	public String departName;
	public String role;
	public String menu;
}
