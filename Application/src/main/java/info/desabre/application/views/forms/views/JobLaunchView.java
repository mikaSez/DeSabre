package info.desabre.application.views.forms.views;

import info.desabre.database.models.server.Server;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by Naiirod on 17/06/2015.
 */

public class JobLaunchView {

	@NotNull(message = "Le nom doit être renseigné")
    @Size(min = 2, message = "Le nom doit comporter au moins deux caractères")
	private String name;

	private Server server;
	
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public Server getServer(){
		return server;
	}
	
	public void setServer(Server server){
		this.server = server;
	} 
    
    @Override
    public String toString() {
        return "JobLaunchView{" +
	                "name='" + name + "'" +
	                "server={'" + server.getName() + "'}" +
                "}";
    }
}
