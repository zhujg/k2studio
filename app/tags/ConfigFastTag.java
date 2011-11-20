package tags;

import groovy.lang.Closure;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import models.Dictionary;
import play.templates.FastTags;
import play.templates.GroovyTemplate.ExecutableTemplate;
import utils.Configure;
import utils.MyUtils;

@FastTags.Namespace("config")
public class ConfigFastTag extends FastTags{

	public static void _show(Map<?, ?> args, Closure body, PrintWriter out, ExecutableTemplate template, int fromLine){
		String key = args.get("arg").toString();
		if(MyUtils.isEmpty(key)) out.print("");
		out.print(Configure.get(key));
	}
	
	public static void _format(Map<?, ?> args, Closure body, PrintWriter out, ExecutableTemplate template, int fromLine){
		String field = args.get("arg").toString();
		List<Dictionary> rows = Configure.getDictionary(field);
		out.println("function format"+field+"(val,row){");
		for(Dictionary row : rows){
			out.println("	if (val == "+row.code+"){"); 
			out.println("		return '"+row.codedesc+"';");
		    out.println("	}");
		}
		out.println("}");
	}

}
