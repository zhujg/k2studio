package utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

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
}
