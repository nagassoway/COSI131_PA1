import java.io.*;

public class CommandImplement extends SequentialFilter {

	public String commandType;
	public String line;
	
	public CommandImplement() {
		
		
	}
	
	public CommandImplement(String type, String line) {
		
		this.commandType = type;
		this.line = line;
	}
    	public String processFile(String file) {
		
		File f = new File(file);
		
		try {
			Scanner sc = new Scanner(f);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.print("At least one of the files in the command" + "was not found.\n");
					
		}
	
		
		return null;
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
    
    public void cat() {
      
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
}
