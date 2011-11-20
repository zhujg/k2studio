package jobs;

import play.jobs.Job;
import play.jobs.OnApplicationStart;
import utils.Configure;

@OnApplicationStart
public class Bootstrap extends Job{

	@Override
	public void doJob() throws Exception {
		Configure.init();
		Configure.initDictionary();
	}
	
}
