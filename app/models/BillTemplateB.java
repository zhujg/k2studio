package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import play.db.jpa.Model;

@Entity(name="bill_template_b")
public class BillTemplateB extends Model{

	@ManyToOne public BillTemplateH bth;
	public String field;			//字段名
	public String showname;			//显示名称
	public Integer showorder;		//显示顺序
	public Integer userflag;		//用户可控
	public Integer nullflag = 1;	//是否为空
	public Integer width;			//宽度
	public String datatype;			//数据类型
	public String defaulvalue;		//默认值
	public Integer inputlength;		//输入字符长度
	public String table_name;		//表体名字
	public String table_code;		//表体代码
	public Integer cardflag=0;		//是否为卡片项目
	public Integer showflag=0;		//卡片是否显示
	public Integer listflag=0;		//列表显示
	public Integer editflag=0;		//是否编辑
	public Integer newlineflag=0;	//折行布局

	public List<Propertygrid> getPropertys(){
		List<Propertygrid> rows = new ArrayList<Propertygrid>();
		Propertygrid property = new Propertygrid("显示顺序",showorder);
		rows.add(property);
		property = new Propertygrid("用户可控",userflag);
		rows.add(property);
		property = new Propertygrid("是否为空",nullflag);
		rows.add(property);
		property = new Propertygrid("数据类型",datatype);
		rows.add(property);
		property = new Propertygrid("默认值",defaulvalue);
		rows.add(property);
		property = new Propertygrid("字符长度",inputlength);
		rows.add(property);
		return rows;
	}
}
