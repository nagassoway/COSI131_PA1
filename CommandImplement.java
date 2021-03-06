package cs131.pa1.filter.sequential;
import java.io.*;
import java.nio.file.Path;
import java.util.*;
import cs131.pa1.filter.Message;
import cs131.pa1.filter.Filter;

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
	
	@Override
	protected String processLine(String line) {

		processCommand(line);
		String[] commandSegments = line.split(" ");
		if(commandType.equals("cd")) {
			
			cd(commandSegments);
		}
		else if(commandType.equals("pwd")) {
			
			return pwd();
		}
		else if(commandType.equals("cat")) {
			
			cat();
		}
		else if(commandType.equals("ls")) {
			
			ls();
		}
		else if(commandType.equals("grep")) {
			
			return grep(commandSegments);
		}
		else if(commandType.equals("wc")) {
			
			return wc(commandSegments);
		}
		else if(commandType.equals("uniq")) {
			
			return uniq(commandSegments);
		}
		return null;
	}
	
	@SuppressWarnings("resource")
	private String cat() {
	      
		if(line.equals("cat")) {
			
			System.out.print(Message.REQUIRES_PARAMETER.with_parameter(line));
		}
		//All files given go into an array
		String[] segments = line.split(" ");
		File f = processFile(segments[1]);
		String fileLines = "";
		//If there are files to cat
		try {
			Scanner sc = new Scanner(f);
			if(segments.length < 2) {
			
				System.out.print(Message.REQUIRES_PARAMETER.with_parameter(line));
			}
			else {
				for (int i = 1; i < segments.length; i++) {
					
					sc = new Scanner(new File(segments[i]));
					int j = 1;
					while(sc.hasNextLine()) {
					
						if(j == 1) {
							
							fileLines = fileLines + sc.nextLine();
							
						}
						else {
							
							fileLines = fileLines + "\n" + sc.nextLine();
						}
						j++;
					}
				}
			}
		}
	    catch (FileNotFoundException e) {
	        
	        System.out.print(Message.FILE_NOT_FOUND.with_parameter(line));
	    }
		System.out.println(fileLines);
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
  
    private String cd (String[] line) {   	
    	
		if (line.length == 1) {
			System.out.print(Message.REQUIRES_PARAMETER.with_parameter(line[0]));
			return null;
		} else {
			try {
				String[] dir = line[1].split(Filter.FILE_SEPARATOR);
				File r = new File("");
				if (dir[0].equals("..")) {
					String path = r.getAbsolutePath();
					String[] spl = path.split(Filter.FILE_SEPARATOR);
					int i = 1;
					String output = "";
					for (String s: spl) {
						if (i != spl.length) {
							output = output + Filter.FILE_SEPARATOR + s;
						}
					}
					System.out.println(output);
					return output;
				} else {
					String path = r.getAbsolutePath();
					path = path + Filter.FILE_SEPARATOR + line[1]; 
					System.out.println(path);
					return path;
				}
			} catch (Exception e) {
				System.out.print(Message.DIRECTORY_NOT_FOUND.with_parameter(line[1]));
				return null;
			}
		}
    }
  
   private String pwd() {
	   
	   File r = new File("");
	   String path = r.getAbsolutePath();
	   System.out.print(path);
	   System.out.println("");
	   return path;
   }
  
    private String grep (String[] line) {
    		if (line.length == 1) {
    			System.out.print(Message.REQUIRES_PARAMETER.with_parameter(line[0]));
    		} else {
    			try {
    				Scanner sc = new Scanner (this.input.peek());
    				try {
    					while (sc.hasNext()) {
    						String fileContents = sc.next();
    						if (fileContents.contains(line[1])) {
    							return fileContents;
    						}
    					}
    				} catch (Exception e) {
    					
    				}
    			} catch (Exception e) {
    				System.out.print(Message.REQUIRES_INPUT.with_parameter(line[0]));
    			}
    		}
    		return null;
    }
  
    private String wc (String[] line) {
    		int lines = 0;
    		int words = 0;
    		int chars = 0;
    		try {
    			Scanner sc = new Scanner (this.input.peek());
    			while (sc.hasNext()) {
    				String inputString = sc.next();
    				lines++;
    				String[] wordCount = inputString.split(" ");
    				words = words + wordCount.length;
    				String[] charCount = inputString.split("");
    				chars = chars + charCount.length;
    			}
    			sc.close();
    		} catch (Exception e) {
    			System.out.print(Message.REQUIRES_INPUT.with_parameter(line[0]));
    		}
    		return "\t" + lines + "\t" + words + "\t" + chars;
    }
  
    private String uniq (String[] line) {
    		HashSet<String> uniqLines = new HashSet<String>();
    		Scanner sc = new Scanner (this.input.peek());
    		while (sc.hasNext()) {
    			String currLine = sc.next();
    			if (!uniqLines.contains(currLine)) {
    				uniqLines.add(currLine);
    				this.output.add(currLine);
    			}
    		}
    		sc.close();
    		return null;
    }
  
    private void greaterThan(String line) {
		String[] commandSegments = line.split(">");
		String[] subCommand = commandSegments[0].split(" ");
		switch (subCommand[0]) {
		case "pwd" : commandSegments[0] =pwd (commandSegments); break;
		case "ls" : commandSegments[0] =ls (commandSegments); break;
		case "cd" : commandSegments[0] =cd (commandSegments); break;
		case "cat" : commandSegments[0] =cat (commandSegments); break;
		case "grep" : commandSegments[0] =grep (commandSegments); break;
		case "wc" :commandSegments[0] =wc (commandSegments); break;
		case "uniq" : commandSegments[0] =uniq (commandSegments); break;
		}
		File f = new File(commandSegments[1]);
	    try {
	    		BufferedWriter writer = new BufferedWriter(new FileWriter(f));
			writer.write(commandSegments[0]);
			writer.close();
		} catch (IOException e) {
			System.out.print(Message.REQUIRES_INPUT.with_parameter(">"));
		}
	}
  
  /*  @Override
    public void processLine() {
      		String[] commandSegments = line.split(" ");
		switch (commandSegments[0]) {
		case "pwd" : return pwd(commandSegments); 
		case "ls" : return ls(commandSegments); 
		case "cd" : return cd(commandSegments);
		case "cat" : return cat(commandSegments); 
		case "grep" : return grep(commandSegments); 
		case "wc" : return wc(commandSegments); 
		case "uniq" : return uniq(commandSegments); 
		default: return null;
		}
    }*/
	
   public void addType(String type, String subCommand) {
	
	this.type = type;
   }
	
@Override
public boolean isDone() {
	return input.size() == 0;
}
}
