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
		return null;
	}

	private static boolean linkFilters(List<SequentialFilter> filters){
		return false;
	}
}
