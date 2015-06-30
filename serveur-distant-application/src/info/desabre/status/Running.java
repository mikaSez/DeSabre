package info.desabre.status;

import java.io.File;

public class Running {

	int index;
	String file;

	public Running(int i, String s) {
		this.index = i;
		this.file = s;
	}
	
	public Running() {
		// TODO Auto-generated constructor stub
	}

	public Terminated runningToTerminated(){
		File source = new File("../Application/distant/running/"+file);
		File destination = new File("../Application/distant/terminated/"+file);
		source.renameTo(destination);
		return new Terminated(this.index,  this.file);
	}
	
	public String getName(){
		return file;
	}
	
	public void setName(String name){
		this.file = name;
	}

}
