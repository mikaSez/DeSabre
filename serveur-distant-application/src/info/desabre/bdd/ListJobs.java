package info.desabre.bdd;

import info.desabre.status.Running;
import info.desabre.status.Terminated;
import info.desabre.status.Waiting;

import java.util.ArrayList;

public class ListJobs {

	private ArrayList<Waiting> waiting;
	private ArrayList<Running> running;
	private ArrayList<Terminated> terminated;

	public ListJobs() {
		
	}
	
	public void setWaiting(ArrayList<Waiting> waiting){
		this.waiting = waiting;
	}
	
	public ArrayList<Waiting> getWaiting(){
		return this.waiting;
	}
	
	public void addWaiting(Waiting w){
		waiting.add(w);
	}
	
	public void setRunning(ArrayList<Running> running){
		this.running = running;
	}
	
	public ArrayList<Running> getRunning(){
		return this.running;
	}
	
	public void addRunning(Running r){
		running.add(r);
	}
	
	public void setTerminated(ArrayList<Terminated> terminated){
		this.terminated = terminated;
	}
	
	public ArrayList<Terminated> getTerminated(){
		return this.terminated;
	}
	
	public void addTerminated(Terminated t){
		terminated.add(t);
	}

}
