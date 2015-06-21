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
    private String date;


    private static List<String> headers;
    
    static {
    	headers = new ArrayList<>();
    	headers.addAll(Arrays.asList("id", "Nom", "Date", "Visualiser"));
    }
    
    
    
    
    public JobGridView(String id, String name, String date) {
        this.name = name;
        this.id = id;
        this.date = date;
    }


    public static JobGridView map(Job job) {
        return new JobGridView(job.getId(), job.getName(), job.getDate());
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

    
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    


    public List<String> getHeaders(){
		return headers;
	}
}
