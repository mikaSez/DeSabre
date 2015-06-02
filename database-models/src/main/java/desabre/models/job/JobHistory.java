package desabre.models.job;


import org.springframework.data.annotation.Id;

import java.sql.Timestamp;

/**
 * Created by MikaSez on 02/06/2015.
 */
public class JobHistory {

    @Id
    private int id;
    private Timestamp date;
    private String groupeId;
    private Job job;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getGroupeId() {
        return groupeId;
    }

    public void setGroupeId(String groupeId) {
        this.groupeId = groupeId;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }
}
