package cs131.pa1.filter.concurrent;

import java.util.concurrent.BlockingQueue;

import cs131.pa1.filter.Message;

public class KillFilter extends ConcurrentFilter {

	public KillFilter() {
		super();
	}

	@Override
	protected String processLine(String line) {
		try {
			String command = line.split(" ")[1];
			input.add(command);
			Object[] commands = ConcurrentREPL.runningCommands.toArray();
			int index = 0;
			for (int i = 0; i < commands.length - 1; i++) {
				String curr = (String) commands[i];
				if (command.equals(curr)) {
					index = i;
				}				
			}
			ConcurrentFilter killedCommand = (ConcurrentFilter) commands[index];
			killedCommand.terminate(killedCommand);
		} catch (Exception e) {
			System.out.print(Message.INVALID_PARAMETER.with_parameter(line.split(" ")[1]));
		}
		return null;
	}
	
	public void run(){
		super.run();
	}

}
