package models;

import javax.persistence.Entity;

import play.db.jpa.Model;

@Entity(name="employees")
public class Employee extends Model{

	public String code;
	public String name;
	public String sex;
	public String birthday;
	public String card;
	public String marry;
	public String mz;		//名族
	public String jg;		//籍贯
	public String zzmm;		//政治面貌
	public String email;
	public String tel;
	public String addr;
	
	public Long dep;
	public String zw;
	public String pyxs;		//聘用形式
	public String xl;		//学历
	public String zy;		//专业
	public String school;	//毕业学校
	public String rzrq;		//入职日期
	public Integer flag;	//在职状态
	
}