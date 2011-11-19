package utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Page<E> {

	public long total;
	public List<E> rows;
	
	public Page(long total,List<E> rows) {
		this.total = total;
		this.rows = rows;
	}
	
	public Map<String,Object> get(){
		Map<String,Object> result = new HashMap<String,Object>();
    	result.put("total", total);
    	result.put("rows", rows);
    	return result;
	}
}
