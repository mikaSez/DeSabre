package info.desabre;
import info.desabre.bdd.ListJobs;
import info.desabre.bdd.Traitement;
import info.desabre.status.Running;
import info.desabre.status.Terminated;
import info.desabre.status.Waiting;
import info.desabre.view.Window;

import java.io.File;


public class Application {
	
	static ListJobs listjob = new ListJobs();
	static Window ihm;

	public static void main(String[] args) throws InterruptedException {
		init();
		ihm = new Window();
		ihm.jobsToString("");
		Traitement t = new Traitement(listjob);
		ihm.jobsToString(ihm.getTextArea()+"\n\r=> Après traitement\n\r");
		t.execution();
	}

	public static void init (){
		String directory_en_cours = "../Application/distant/waiting";
		String directory_en_traitement = "../Application/distant/running";
		String directory_termine = "../Application/distant/terminated";
		
		File file;
		int i, index = 0;
		
		System.out.println("----Waiting Jobs loading----");
		
		file = new File(directory_en_cours);
		if(file.isDirectory()){
			final String[] files = file.list();
			
			for(i=0;i<files.length;i++){
				if(files[i].contains(".ser")){
					System.out.println(files[i]);
					
					listjob.addWaiting(new Waiting(index, files[i]));
				}
				index++;
			}
			
		}

		System.out.println("----Waiting Jobs loaded----");
		
		System.out.println("----Running Jobs loading----");
		
		file = new File(directory_en_traitement);
		if(file.isDirectory()){
			final String[] files = file.list();
			
			for(i=0;i<files.length;i++){
				if(files[i].contains(".ser")){
					System.out.println(files[i]);
					
					listjob.addRunning(new Running(index, files[i]));
				}
				index++;
			}
			
		}

		System.out.println("----Running Jobs loaded----");
		
		System.out.println("----Terminated Jobs loading----");
		
		file = new File(directory_termine);
		if(file.isDirectory()){
			final String[] files = file.list();
			
			for(i=0;i<files.length;i++){
				if(files[i].contains(".ser")){
					System.out.println(files[i]);
					
					listjob.addTerminated(new Terminated(index, files[i]));
				}
				index++;
			}
			
		}

		System.out.println("----Terminated Jobs loaded----");
	}
}
