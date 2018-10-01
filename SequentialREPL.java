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
		while(!cmd.equals("exit")) {
			
			//Create linkedlist of commands into String lines of CommandImplements.
			cmds = SequentialCommandBuilder.createFiltersFromCommand(cmd);
			
			System.out.println();
			System.out.print(Message.NEWCOMMAND);
			cmd = input.nextLine();
		}
		System.out.print(Message.GOODBYE);
	}

}

