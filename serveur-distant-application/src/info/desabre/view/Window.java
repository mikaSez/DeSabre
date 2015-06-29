package info.desabre.view;

import info.desabre.database.models.job.Job;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class Window  extends JFrame {
	  private JPanel container = new JPanel();
	  private JTextField jtf = new JTextField();

	  public Window(){
	    this.setTitle("Serveur distant - DaSabre");
	    this.setSize(600, 600);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setLocationRelativeTo(null);
	    container.setBackground(Color.white);
	    container.setLayout(new BorderLayout());
	    JPanel top = new JPanel();
	    Font police = new Font("Arial", Font.BOLD, 14);
	    jtf.setFont(police);
	    jtf.setPreferredSize(new Dimension(600, 600));
	    jtf.setForeground(Color.BLACK);
	    jtf.setText(jobsToString());
	    jtf.setEditable(false);
	    top.add(jtf);
	    container.add(top, BorderLayout.NORTH);
	    this.setContentPane(container);
	    this.setVisible(true);            
	  }
	  
	  public String jobsToString(){
		  
		String directory = "../../../GitHub/DeSaBre/Application/distant";
		ObjectInputStream ois = null;
		File file = new File(directory);
		int i;
		String texte = "";
	    	
		try {
			if(file.isDirectory()){
				final String[] files = file.list();
				
				for(i=0;i<files.length;i++){

					System.out.println(files[i]);
					final FileInputStream fichier = new FileInputStream(directory+"/"+files[i]);
					ois = new ObjectInputStream(fichier);
					final Job job = (Job) ois.readObject();
					
					texte += "Job"+i;
					texte += "\nId"+job.getId();
					texte += "\nNom"+job.getName();
					texte += "\nDate"+job.getDate();
					texte += "\n";
					texte += "\n-------------------------------\n";
					
				}
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
		  try {
		    if (ois != null) {
		    	ois.close();
		    }
		  } catch (final IOException ex) {
		    ex.printStackTrace();
		  }
		}
		  
		return texte;
	  }
	}