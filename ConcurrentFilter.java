package cs131.pa1.filter.concurrent;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.BlockingQueue;

import cs131.pa1.filter.Filter;


public abstract class ConcurrentFilter extends Filter implements Runnable {
	
	protected BlockingQueue<String> input;
	protected BlockingQueue<String> output;
	
	@Override
	public void setPrevFilter(Filter prevFilter) {
		prevFilter.setNextFilter(this);
	}
	
	@Override
	public void setNextFilter(Filter nextFilter) {
		if (nextFilter instanceof ConcurrentFilter){
			ConcurrentFilter sequentialNext = (ConcurrentFilter) nextFilter;
			this.next = sequentialNext;
			sequentialNext.prev = this;
			if (this.output == null){
				this.output = new LinkedBlockingQueue<String>();
			}
			sequentialNext.input = this.output;
		} else {
			throw new RuntimeException("Should not attempt to link dissimilar filter types.");
		}
	}
	
	public Filter getNext() {
		return next;
	}
	
	public void process(){
		while (!input.isEmpty()){
			String line = input.poll();
			String processedLine = processLine(line);
			if (processedLine != null){
				output.add(processedLine);
			}
		}	
	}
	
	public void run(){   //Works for any filter that just uses process line
		String pill = "***";
		String line = "";
						
		while(true) { 
			String waitNsee = "";
			
			//Input queue isn't empty
			if(input.size() != 0){   
					waitNsee = input.poll();
					if(waitNsee.equals(pill))
						break;
					else   
						line = waitNsee;	
			}
			
			//Input queue empty
			else{  
				try {  
					waitNsee = input.take(); //Waiting until it isn't empty
				}
				catch (InterruptedException e) {
					e.printStackTrace();
				}
				if (waitNsee.equals(pill)) //Signifies the producing thread is done
					break;
				else{                    //Otherwise its an actual value
					line = waitNsee;
				}
			}
			//Processing line
			String processedLine = processLine(line);
			if (processedLine != null)
				output.add(processedLine);
		}
	}
	
	@Override
	public boolean isDone() {
		return input.size() == 0;
	}
	
	protected abstract String processLine(String line);

	public void terminate(ConcurrentFilter input) {
		ConcurrentCommandBuilder.filters.remove(input);
	}
	
}
