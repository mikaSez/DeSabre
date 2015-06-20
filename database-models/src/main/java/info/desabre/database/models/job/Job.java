package info.desabre.database.models.job;


import info.desabre.database.models.server.Licence;
import info.desabre.database.models.server.Server;

import org.springframework.data.annotation.Id;

import java.util.List;

/**
 * Created by MikaSez on 02/06/2015.
 */
public class Job {

    @Id
    private String id;
    private String name;
    private String groupeid;
    private Server executionServer;
    private String date;

    private Script mainScript;
    private List<Script> scripts;
    private List<Licence> requiredLicences;
    
    public Job(String name, List<Script> scripts, List<Licence> licences){
    	this.name = name;
    	this.scripts = scripts;
    	this.requiredLicences = licences;
    }
    
    public Job(String id, String name, String date){
    	this.id = id;
    	this.name = name;
    	this.date = date;
    }

    public Script getMainScript() {
        return mainScript;
    }

    public void setMainScript(Script mainScript) {
        this.mainScript = mainScript;
    }

    public List<Script> getScripts() {
        return scripts;
    }

    public void setScripts(List<Script> scripts) {
        this.scripts = scripts;
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

    public String getGroupeid() {
        return groupeid;
    }

    public void setGroupeid(String groupeid) {
        this.groupeid = groupeid;
    }

    public Server getExecutionServer() {
        return executionServer;
    }

    public void setExecutionServer(Server executionServer) {
        this.executionServer = executionServer;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
    public List<Licence> getRequiredLicences() {
        return requiredLicences;
    }

    public void setRequiredLicences(List<Licence> requiredLicences) {
        this.requiredLicences = requiredLicences;
    }
}
