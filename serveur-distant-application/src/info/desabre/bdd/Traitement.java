package info.desabre.bdd;

import info.desabre.status.Running;
import info.desabre.status.Terminated;
import info.desabre.status.Waiting;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

public class Traitement {
	
	private ListJobs listjobs;

	public Traitement(ListJobs lj) {
		this.listjobs = lj;
	}
	
	public void execution() throws InterruptedException{
		System.out.println("------Debut d'execution------");
		
		//Parcours du repertoire wainting
		//Deplace chaque fichier de waiting à running à terminated
		System.out.println("------Execution Waiting------");
		ArrayList<Waiting> waitings = listjobs.getWaiting();
		Iterator<Waiting> iteratorW = waitings.iterator();
		
		while(iteratorW.hasNext()){
			Waiting w = iteratorW.next();
			Running r = new Running();
			Terminated t = new Terminated();
			
			System.out.print("Waiting/"+w.getName());
			if(new File("../Application/distant/waiting/"+w.getName()).exists()){
				r = w.waitingToRunning();
				iteratorW.remove();
				listjobs.addRunning(r);
			}
			System.out.println(" -> Running/");
			
			Thread.sleep(30000);
			
			System.out.print("Running/"+w.getName());
			if(new File("../Application/distant/running/"+r.getName()).exists()){
				t = r.runningToTerminated();
				listjobs.removeRunning(r);
				listjobs.addTerminated(t);
			}
			System.out.println(" -> Terminated/");
		}
		System.out.println("------/Execution Waiting------");
		
		
		System.out.println("------Execution Running------");
		ArrayList<Running> runnings = listjobs.getRunning();
		Iterator<Running> iteratorR = runnings.iterator();
		
		while(iteratorR.hasNext()){
			Running r = iteratorR.next();
			Terminated t = new Terminated();
			
			System.out.print("Running/"+r.getName());
			if(new File("../Application/distant/running/"+r.getName()).exists()){
				t = r.runningToTerminated();
				iteratorR.remove();
				listjobs.addTerminated(t);
			}
			System.out.println(" -> Terminated/");
		}
		System.out.println("------/Execution Running------");


		System.out.println("------/Fin d'execution------");
		
	}

}
