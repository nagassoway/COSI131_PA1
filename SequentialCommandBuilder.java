package cs131.pa1.filter.sequential;

import java.util.List;

public class SequentialCommandBuilder {
	
	public static List<SequentialFilter> createFiltersFromCommand(String command){
		
		String[] splits = command.split("|", -1);
		for(int i = 0; i < splits.length; i++) {
			
			if(command.substring(0, 2).equals("cd")) {
			
			
			}
			else if(command.substring(0,3).equals("cat")) {
			
			
			}
			else if(command.substring(0, 2).equals("ls")) {
			
			
			}
			else if(command.substring(0, 3).equals("pwd")) {
			
			
			}
			else if(command.substring(0,4).equals("grep")) {
			
			
			}
			else if(command.substring(0, 2).equals("wc")) {
			
			
			}
			else if(command.substring(0, 4).equals("uniq")) {
			
			
			}
			else {
			
				System.out.println("The command " + splits[i] + " was not recognized.");
			}
		}
		return null;
	}
	
	private static SequentialFilter determineFinalFilter(String command){
		return null;
	}
	
	private static String adjustCommandToRemoveFinalFilter(String command){
		return null;
	}
	
	private static SequentialFilter constructFilterFromSubCommand(String subCommand){
		return null;
	}

	private static boolean linkFilters(List<SequentialFilter> filters){
		return false;
	}
}
