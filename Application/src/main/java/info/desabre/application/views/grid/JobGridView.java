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
    private String path;

    private static List<String> headers;
    
    static {
    	headers = new ArrayList<>();
    	headers.addAll(Arrays.asList("Id", "Nom", "Traitement"));
    }
    
    
    public JobGridView(String id, String name) {
        this.id = id;
        this.name = name;
    }


    public static JobGridView mapL(Job job) {
    	JobGridView view = new JobGridView(job.getId(), job.getName());
    	view.path = "/job/launch/config?id=" + job.getId();
    	
        return view;
    }

    public static List<JobGridView> mapL(List<Job> jobs) {
        return jobs.stream().map(JobGridView::mapL).collect(Collectors.toList());
    }

    public static JobGridView mapV(Job job) {
    	JobGridView view = new JobGridView(job.getId(), job.getName());
    	view.path = "/job/details?id=" + job.getId();
    	
        return view;
    }

    public static List<JobGridView> mapV(List<Job> jobs) {
        return jobs.stream().map(JobGridView::mapV).collect(Collectors.toList());
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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }


    public List<String> getHeaders(){
		return headers;
	}
}
