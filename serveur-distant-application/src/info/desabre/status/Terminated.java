package info.desabre.status;

public class Terminated {

	int index;
	String file;

	public Terminated(int i, String s) {
		this.index = i;
		this.file = s;
	}
	
	public Terminated() {
		// TODO Auto-generated constructor stub
	}

	public String getName(){
		return file;
	}
	
	public void setName(String name){
		this.file = name;
	}

}
