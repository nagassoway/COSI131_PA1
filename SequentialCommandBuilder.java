package cs131.pa1.filter.sequential;

import java.util.List;

public class SequentialCommandBuilder {
	private static List<SequentialFilter> filters;
	protected static SequentialBuilder commands;
	
	public static List<SequentialFilter> createFiltersFromCommand(String command){
		commands = new CommandInterpreter();
		commands.input = new LinkedList();
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
	
	// Nick's version
	private static SequentialFilter constructFilterFromSubCommand(String subCommand){
		String[] segments = subCommand.split(" ");
		if (checkCommands(segments[0]) == false) {
			return null;			
		} else {
			commands.input.add(subCommand);
			return null;
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
