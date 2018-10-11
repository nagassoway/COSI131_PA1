package cs131.pa1.filter.concurrent;

public class REPLJobs extends ConcurrentFilter {

	public REPLJobs() {
		super();
	}

	@Override
	protected String processLine(String line) {
		String jobList = "";
		int i = 1;
		for (ConcurrentFilter s: ConcurrentREPL.runningCommands) {
			jobList = jobList + i + ". " + s.toString() + "\n";
		}
		return jobList;
	}
	
	public void run(){
		super.run();
		output.add("***");
	}

}
