package cs131.pa1.filter.concurrent;

import cs131.pa1.filter.Message;

import java.util.ArrayList;
import java.util.Scanner;

public class ConcurrentREPL {

	static String currentWorkingDirectory;
	public static ArrayList<ConcurrentFilter> runningCommands = new ArrayList<>();
	
	public static void main(String[] args){
		currentWorkingDirectory = System.getProperty("user.dir");
		Scanner s = new Scanner(System.in);
		System.out.print(Message.WELCOME);
		String command;
		//Must keep a running list of all concurrentfilter objects created per each command line
		ArrayList<ConcurrentFilter> jobs = new ArrayList<ConcurrentFilter>();
		ArrayList<ConcurrentFilter> last = new ArrayList<>();
		ConcurrentFilter lastMan = null;
		boolean multipleCmds = false;
		while(true) {
			//obtaining the command from the user
			System.out.print(Message.NEWCOMMAND);
			command = s.nextLine();
			if(command.equals("exit")) {
				break;		
			}
			//Just add another else if statement since it can't be added as a concurrentfilter
			else if(command.contains("repl_jobs")){
					
//				int k = 1;
//				for (int i = 0; i < jobs.size(); i++) {
//				//Check whether thread has started and is not yet terminated
//				if(last.get(i).isAlive()){						
//				System.out.println(k + ". " + jobs.get(i));				
//				k++;
//				}		
//			}				
			} if(!command.trim().equals("")) {
				//Must create all filters before starting any of them
				String[] spl = command.split("\\|", -1);
				String first = spl[spl.length - 1];
				if(first.contains("&")) {
					//Then must run REPL loop while parsing current command
					multipleCmds = true;
					command = command.substring(0, command.indexOf('&')-1);
				}
				//building the filters list from the command
				ConcurrentFilter filterlist = ConcurrentCommandBuilder.createFiltersFromCommand(command);
				while(filterlist != null) {
					filterlist.process();
					jobs.add(filterlist);
					lastMan = filterlist;
					filterlist = (ConcurrentFilter) filterlist.getNext();
				}
			}
		}
		s.close();
		System.out.print(Message.GOODBYE);
	}
}
