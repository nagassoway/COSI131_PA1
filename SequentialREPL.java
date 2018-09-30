package cs131.pa1.filter.sequential;

import java.util.LinkedList;
import java.util.Scanner;
import java.util.*;
import cs131.pa1.filter.Message;

public class SequentialREPL {

	static String currentWorkingDirectory;
	
	@SuppressWarnings("resource")
	public static void main(String[] args){
		
		System.out.print(Message.WELCOME);
		System.out.print(Message.NEWCOMMAND);
		Scanner input = new Scanner(System.in);
		String cmd = input.nextLine();
		List<SequentialFilter> cmds = new LinkedList<SequentialFilter>();
		String[] splits = new String[10];
		while(!cmd.equals("exit")) {
			
			cmds.clear();
			System.out.print(Message.NEWCOMMAND);
			cmd = input.nextLine();
			//Create linkedlist of commands into String lines of CommandImplements.
			cmds = SequentialCommandBuilder.createFiltersFromCommand(cmd);
			//SequentialCommandBuilder.linkFilters(cmds);
			//Iterator<SequentialFilter> it = cmds.iterator();
			//CommandImplement next = (CommandImplement) it.next();
			System.out.println();
		}
		System.out.print(Message.GOODBYE);
	}

}
