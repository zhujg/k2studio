package models;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;

import play.db.DB;
import play.db.jpa.Model;

@Entity(name="bill_template")
public class BillTemplate extends Model{

	public String billtemplate_name;
	public String nodecode;
	
}
