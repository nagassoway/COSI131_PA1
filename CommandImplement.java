import java.io.*;
package cs131.pa1.filter.sequential;
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
		
			System.out.print(Message.COMMAND_NOT_FOUND);
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
	public String cat() {
	      
		//All files given go into an array
		String[] segments = line.split(" ");
		if(!output.isEmpty()) {
			segments[segments.length] = output.poll();
		}
		File f = processFile(segments[0]);
		String fileLines = "";
		//If there are no files to cat
		try {
			Scanner sc = new Scanner(f);
			if(segments.length < 2) {
			
				System.out.print(Message.REQUIRES_PARAMETER.with_parameter(line));
			}
			else {
			
				for (int i = 0; i < segments.length; i++) {
				
					while(sc.hasNextLine()) {
					
						fileLines = fileLines + "\n" + sc.nextLine();
					}
					sc = new Scanner(segments[i]);
				}
			}
		}
	    catch (FileNotFoundException e) {
	        e.printStackTrace();
	        System.out.print(Message.FILE_NOT_FOUND.with_parameter(line));
	    }
		
		return fileLines;
	}
	
	public void ls() {
	   
		File[] paths;
	    try {  
	          
	       // create new empty file
	       File f = new File(" ");
	       paths = f.listFiles();
	       for(File path:paths) {
	          
	         // prints file and directory paths
	         System.out.println(path);
	       }
	          
	       } catch(Exception e) {
	          // if any error occurs
	          e.printStackTrace();
	          System.out.print(Message.DIRECTORY_NOT_FOUND);
	       }
	}
  
    public void cd() {
      
    }
  
   public void pwd() {
	   
	   File r = new File("");
	   String path = r.getAbsolutePath();
	   String[] spl = path.split("\\\\");
	   System.out.print("/");
	   int j = 0;
	   for(String i: spl) {
		   
		   System.out.print(i);
		   if(j > 0) {
			   
			   System.out.print("/");
		   }
	   }
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
