import java.io.*;
package cs131.pa1.filter.sequential;
import java.io.*;
import java.util.Scanner;
import cs131.pa1.filter.Message;

public class CommandImplement extends SequentialFilter {

	public String commandType;
	public String line;
	
	public CommandImplement() {
		
		
	}
	
	public CommandImplement(String type, String line) {
		
		this.commandType = type;
		this.line = line;
	}
	public File processFile(String file) {
		
		File f = new File(file);
		return f;
	
	}
	
		//Detect type of command
	protected void processCommand(String value) {
		
		if(value.substring(0, 2).equals("cd")) {
			
			addType("cd", value);
		}
		else if(value.substring(0,3).equals("cat")) {
		
			addType("cat", value);
		}
		else if(value.substring(0, 2).equals("ls")) {
		
			addType("ls", value);
		}
		else if(value.substring(0, 3).equals("pwd")) {
		
			addType("pwd", value);
		}
		else if(value.substring(0,4).equals("grep")) {
		
			addType("grep", value);
		}
		else if(value.substring(0, 2).equals("wc")) {
		
			addType("wc", value);
		}
		else if(value.substring(0, 4).equals("uniq")) {
		
			addType("uniq", value);
		}
		else {
		
			System.out.println(Message.COMMAND_NOT_FOUND);
		}
	}
	
	public String cd(String newPath) {
	    if (isAbsolutePath(newPath)) {
	        path = normalizePath(newPath);
	        return this;
	    }

	    path = normalizePath(path + SEPARATOR + newPath);
	    return this;
	}
	
    @SuppressWarnings("resource")
    public void cat() {
      		//All files given go into an array
		String[] segments = line.split(" ");
		File f = processFile(segments[0]);
		String fileLines = "";
		//If there are no files to cat
		try {
			Scanner sc = new Scanner(f);
			if(segments.length < 2) {
			
				System.out.println(Message.REQUIRES_PARAMETER.with_parameter(line));
			}
			else {
			
				for (String i: segments) {
				
					while(sc.hasNextLine()) {
					
						fileLines = fileLines + "\n" + sc.nextLine();
					}
				}
			}
		}
	    catch (FileNotFoundException e) {
	        e.printStackTrace();
	        System.out.println(Message.FILE_NOT_FOUND.with_parameter(line));
	    }
		
		return fileLines;
    }
  
    public void ls() {
      
    }
  
    public void cd() {
      
    }
  
    public void pwd() {
      
    }
  
    public void grep() {
      
    }
  
    public void wc() {
      
    }
  
    public void uniq() {
      
    }
  
    public void greaterThan() {

    }
  
    @Overide
    public void processLine() {
      
    }
	
   public void addType(String type, String subCommand) {
	
	this.type = type;
   }
	
@Override
public boolean isDone() {
	return input.size() == 0;
}
}
