package info.desabre.application.views.grid;

import info.desabre.database.models.job.Job;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Naiirod on 15/06/2015.
 * This class is used to map data from a job object to a view object witch will be transmitted to the view and shown
 * Security / And bandwidth purposes
 */
public class JobGridView {

    private String id;
    private String name;

    private static List<String> headers;
    
    static {
    	headers = new ArrayList<>();
    	headers.addAll(Arrays.asList("Id", "Nom", "Visualiser"));
    }
    
    
    
    
    public JobGridView(String id, String name) {
        this.id = id;
        this.name = name;
    }


    public static JobGridView map(Job job) {
        return new JobGridView(job.getId(), job.getName());
    }

    public static List<JobGridView> map(List<Job> jobs) {
        return jobs.stream().map(JobGridView::map).collect(Collectors.toList());
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public List<String> getHeaders(){
		return headers;
	}
}
