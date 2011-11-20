package utils;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import play.db.DB;
import play.exceptions.DatabaseException;

public class DBUtils {

	public long count(String sql,List<Object> args){
		try{
			Connection conn = DB.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			for(int i = 0;i<args.size();i++){
				ps.setObject(i+1, args.get(i));
			}
			ResultSet rs = ps.executeQuery();
			if(rs.next()) return rs.getLong(1);
		}catch (Exception e) {
			throw new DatabaseException(e.getMessage());
		}
		return 0l;
	}
	
	
	public static List<Map<String,Object>> getTables2Tree(){
		List<String> tables = new ArrayList<String>();
		List<Map<String,Object>> nodes = new ArrayList<Map<String,Object>>();
		try {
			Connection conn = DB.getConnection();
			DatabaseMetaData dbmd = conn.getMetaData();
			String[] types = { "TABLE" };
			ResultSet rs = dbmd.getTables(null, null, "%", types);
			String schema = "";
			while (rs.next()) {
				String name = rs.getString(3);
				schema = rs.getString(1);
				tables.add(name);
			}
			
			Map<String,Object> root = new HashMap<String,Object>();
			root.put("id", 0);
			root.put("text",schema);
			nodes.add(root);
			
			List<Map<String,Object>> parent = new ArrayList<Map<String,Object>>();
			for(int i = 0;i<tables.size();i++){
				String name = tables.get(i);
				Map<String,Object> node = new HashMap<String,Object>();
				node.put("id", 0);
				node.put("text", name);
				parent.add(node);
			}
			
			root.put("children", parent.toArray(new Object[parent.size()]));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return nodes;
	}
	
	public static List<Map<String,Object>> getColumns(String table){
		List<Map<String,Object>> columns = new ArrayList<Map<String,Object>>();
		if(MyUtils.isEmpty(table)) return columns;
		try {
			Connection conn = DB.getConnection();
			DatabaseMetaData dbmd = conn.getMetaData();
			ResultSet rs = dbmd.getColumns(null,null, table, null);
			int i = 0;
			while(rs.next()){
				String name = rs.getString("COLUMN_NAME");
				String type = rs.getString("TYPE_NAME");
				int size = rs.getInt("COLUMN_SIZE");
			    int nullable = rs.getInt("NULLABLE");
			    Map<String,Object> column = new HashMap<String,Object>();
			    column.put("name", name);
			    column.put("type", type);
			    column.put("size", size);
			    column.put("nullable", nullable);
			    column.put("index", ++i);
			    if(name.equals("id")) column.put("key", 1);
			    else column.put("key", 0);
			    columns.add(column);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return columns;
	}
}
