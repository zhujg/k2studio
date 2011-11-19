package tags;

import groovy.lang.Closure;

import java.io.PrintWriter;
import java.util.Map;

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
}
