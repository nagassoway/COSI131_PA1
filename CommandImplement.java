import java.io.*;

public class CommandImplement extends SequentialFilter {

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
}
