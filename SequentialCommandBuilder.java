package cs131.pa1.filter.sequential;

import java.util.List;
import cs131.pa1.filter.Filter;

public class SequentialCommandBuilder {
	protected static List<SequentialFilter> filters;
	
	public static List<SequentialFilter> createFiltersFromCommand(String command){
		filters = new LinkedList<SequentialFilter>();
		String[] segments = command.split("[|]");
		for (String i: segments) {
			i = i.trim();
			filters.add(constructFilterFromSubCommand(i));
		}
		linkFilters(filters);
		for (SequentialFilter f: filters) {
			if (f.output != null) {
				f.process();		
			}
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
		default: System.out.print(Message.COMMAND_NOT_FOUND); return false;
		}
	}

	private static boolean linkFilters(List<SequentialFilter> filters){
		Object[] filterlist = filters.toArray();
		try {
			int i = 0;
			for (Object f: filterlist) {
				try {
					if (i>0) {
						((Filter) f).setPrevFilter((Filter) filterlist[i-1]);
					}
					((Filter) f).setNextFilter((Filter) filterlist[i+1]);
				} catch(Exception e) {
					return false;
				}
				i++;
			}
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
