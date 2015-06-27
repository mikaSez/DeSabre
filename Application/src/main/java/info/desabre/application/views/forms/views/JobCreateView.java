package info.desabre.application.views.forms.views;

import info.desabre.database.models.job.Job;
import info.desabre.database.models.job.Script;
import info.desabre.database.models.server.Licence;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by Naiirod on 17/06/2015.
 */

public class JobCreateView {

	@NotNull(message = "Le nom doit être renseigné")
    @Size(min = 2, message = "Le nom doit comporter au moins deux caractères")
	private String name;

	private List<Licence> licences;
	
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}

	public void addLicence(Licence licence){
		licences.add(licence);
	}
	
	
	public List<Licence> getLicences(){
		return licences;
	}
	
	public void setLicences(List<Licence> licences){
		this.licences = licences;
	}
	
    public Job mapToJob() {
        Job job =  new Job(name, licences);
        return job;
    }  
    
    @Override
    public String toString() {
        return "JobCreateView{" +
	                "name='" + name + "'" +
	                "licences={'" + licences.toString() + "'}" +
                "}";
    }
}
