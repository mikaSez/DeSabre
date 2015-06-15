package info.desabre.application.views;

import info.desabre.database.models.job.Job;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Naiirod on 15/06/2015.
 * This class is used to map data from a job object to a view object witch will be transmitted to the view and shown
 * Security / And bandwidth purposes
 */
public class JobGridView {

    private String name;
    private int id;

    public JobGridView(int id, String name) {
        this.name = name;
        this.id = id;
    }


    public static JobGridView map(Job job) {
        return new JobGridView(job.getId(), job.getName());
    }

    public static List<JobGridView> map(List<Job> jobs) {
        return jobs.stream().map(job -> map(job)).collect(Collectors.toList());
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
