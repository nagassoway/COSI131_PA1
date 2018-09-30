package cs131.pa1.filter.sequential;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.*;

public class SequentialREPL {

	static String currentWorkingDirectory;
	
	@SuppressWarnings("resource")
	public static void main(String[] args){
		
		//LinkedList<String> pipes = new LinkedList<String>();
		Message msg;
		System.out.println(">Welcome to the Unix-ish command line.");
		System.out.print(">");
		Scanner input = new Scanner(System.in);
		String cmd = input.nextLine();
		List<SequentialFilter> cmds = new LinkedList<SequentialFilter>();
		String[] splits = new String[10];
		while(!cmd.equals("exit")) {
			
			
			System.out.print(">");
			cmd = input.nextLine();
			cmds = SequentialCommandBuilder.createFiltersFromCommand(cmd);
			
			
		}
		System.out.print("Thank you for using the Unix-ish command line. Goodbye!\n");
	}

}
