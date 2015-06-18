package info.desabre.application.views;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import info.desabre.database.models.job.Job;

/**
 * Created by Naiirod on 17/06/2015.
 */
public class JobCreateView {

	@NotNull(message = "Le nom doit être renseigné")
    @Size(min = 2, message = "Le nom doit comporter au moins deux caractères")
	private String name;
	
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}

    public Job mapToJob() {
        Job job =  new Job(name);
        return job;
    }
    
    @Override
    public String toString() {
        return "JobCreateView{" +
                "name='" + name + '\'' +
                '}';
    }
}
