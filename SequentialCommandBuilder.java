package cs131.pa1.filter.sequential;

import java.util.List;

public class SequentialCommandBuilder {
	
	public static List<SequentialFilter> createFiltersFromCommand(String command){
		List<SequentialFilter> filters = new LinkedList<SequentialFilter>();
		String[] segments = command.split("[|]");
		for (String i: segments) {
			i = i.trim();
			filters.add(constructFilterFromSubCommand(i));
		}
		return filters;
	}
	
	//Proposal for createFiltersFromCommand, based on adding setNextFilter commands while parsing each filter
		public static List<SequentialFilter> createFiltersFromCommand(String command){
		
		LinkedList<SequentialFilter> filters = new LinkedList<SequentialFilter>();
		String[] segments = command.split("[|]");
		int j = 0;
		SequentialFilter next = null;
		SequentialFilter prev = null;
		for (String i: segments) {
			i = i.trim();
			//filters.add(constructFilterFromSubCommand(i));
			//setNextFilter method to allow filters to refer to each other
			if(prev != null) {
				
				next = constructFilterFromSubCommand(i);
				prev.setNextFilter(next);
				filters.add(constructFilterFromSubCommand(i));
			}
			else {
				
				prev = constructFilterFromSubCommand(i);
				filters.add(prev);
			}
			j++;
		}
		
		return filters;
	}
	
	private static SequentialFilter determineFinalFilter(String command){
		return null;
	}
	
	private static String adjustCommandToRemoveFinalFilter(String command){
		return null;
	}
	
	// Nick's version
	private static SequentialFilter constructFilterFromSubCommand(String subCommand){
		String[] segments = subCommand.split(" ");
		if (checkCommands(segments[0]) == false) {
			return null;			
		} else {
			SequentialFilter command = new CommandImplement();
			command.input = new LinkedList<String>();
			command.input.add(subCommand);
			return command;
		}
	}

	private static boolean checkCommands(String subCommand) {
		switch (subCommand) {
		case "pwd" : return true;
		case "ls" : return true;
		case "cd" : return true;
		case "cat" : return true;
		case "grep" : return true;
		case "wc" : return true;
		case "uniq" : return true;
		case "exit": return true;
		default: System.out.println(Message.COMMAND_NOT_FOUND); return false;
		}
	}

	private static boolean linkFilters(List<SequentialFilter> filters){
		return false;
	}
}
