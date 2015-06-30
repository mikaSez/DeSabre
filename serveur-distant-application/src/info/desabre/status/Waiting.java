package info.desabre.status;

import java.io.File;

public class Waiting {

	int index;
	String file;
	
	public Waiting(int i, String s) {
		this.index = i;
		this.file = s;
	}
	
	public Running waitingToRunning(){
		File source = new File("../Application/distant/waiting/"+file);
		File destination = new File("../Application/distant/running/"+file);
		source.renameTo(destination);
		return new Running(this.index,  this.file);
	}
	
	public String getName(){
		return file;
	}
	
	public void setName(String name){
		this.file = name;
	}

}
