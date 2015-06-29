package info.desabre;
import info.desabre.bdd.ListJobs;
import info.desabre.status.Waiting;
import info.desabre.view.Window;

import java.io.File;
import java.io.IOException;


public class Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Window ihm = new Window();
	}

	public void init (){
		ListJobs listjob = new ListJobs();
		String directory_en_cours = "../Application/distant/waiting";
		String directory_en_traitement = "../Application/distant/running";
		String directory_termine = "../Application/distant/terminated";
		
		File file;
		int i, index = 0;
			
		file = new File(directory_en_cours);
		if(file.isDirectory()){
			final String[] files = file.list();
			
			for(i=0;i<files.length;i++){
				if(files[i].contains(".ser")){
					System.out.println(files[i]);
					
					listjob.addWaiting(new Waiting(files[i]));
				}
				index++;
			}
			
		}
	}
}
