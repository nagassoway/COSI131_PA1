package cs131.pa1.filter.sequential;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.*;

public class SequentialREPL {

	static String currentWorkingDirectory;
	
	@SuppressWarnings("resource")
	@SuppressWarnings("resource")
	public static void main(String[] args){
		
		System.out.println(Message.WELCOME);
		System.out.print(Message.NEWCOMMAND);
		Scanner input = new Scanner(System.in);
		String cmd = input.nextLine();
		List<SequentialFilter> cmds = new LinkedList<SequentialFilter>();
		String[] splits = new String[10];
		while(!cmd.equals("exit")) {
			
			
			System.out.print(Message.NEWCOMMAND);
			cmd = input.nextLine();
			//Create linkedlist of commands into String lines of CommandImplements.
			cmds = SequentialCommandBuilder.createFiltersFromCommand(cmd);
			Iterator<SequentialFilter> it = cmds.iterator();
			CommandImplement next = (CommandImplement) it.next();
			CommandImplement prev = next;
			next.process();
			while(it.hasNext()) {
				
				next = (CommandImplement) it.next();
				next.process();
				prev.setNextFilter(next);
				prev = (CommandImplement) it.next();
				
			}
			System.out.println(next.output.poll());
			
			
		}
		System.out.print(Message.GOODBYE);
	}

}
