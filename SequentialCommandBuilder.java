package cs131.pa1.filter.sequential;

import java.util.List;

public class SequentialCommandBuilder {
	private static List<SequentialFilter> filters;
	
	public static List<SequentialFilter> createFiltersFromCommand(String command){
		filters = new LinkedList<SequentialFilter>();
		String[] segments = command.split("[|]");
		for (String i: segments) {
			i = i.trim();
			filters.add(constructFilterFromSubCommand(i));
		}
		return filters;
	}
	
	private static SequentialFilter determineFinalFilter(String command){
		return null;
	}
	
	private static String adjustCommandToRemoveFinalFilter(String command){
		return null;
	}
	
	private static SequentialFilter constructFilterFromSubCommand(String subCommand){
				if(subCommand.substring(0, 2).equals("cd")) {
			
		}
		else if(subCommand.substring(0,3).equals("cat")) {
		
		
		}
		else if(subCommand.substring(0, 2).equals("ls")) {
		
		
		}
		else if(subCommand.substring(0, 3).equals("pwd")) {
		
		
		}
		else if(subCommand.substring(0,4).equals("grep")) {
		
		
		}
		else if(subCommand.substring(0, 2).equals("wc")) {
		
		
		}
		else if(subCommand.substring(0, 4).equals("uniq")) {
		
		
		}
		else {
		
			System.out.println("The command " + subCommand + " was not recognized.");
		}
		return null;
	}

	private static boolean linkFilters(List<SequentialFilter> filters){
		return false;
	}
}
