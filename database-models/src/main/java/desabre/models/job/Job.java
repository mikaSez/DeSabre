package desabre.models.job;


import desabre.models.server.Licence;
import desabre.models.server.Server;
import org.springframework.data.annotation.Id;

import java.util.List;

/**
 * Created by MikaSez on 02/06/2015.
 */
public class Job {

    @Id
    private int id;
    private String name;
    private String groupeid;
    private Server executionServer;

    private Script mainScript;
    private List<Script> scripts;
    private List<Licence> requiredLicences;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public List<Licence> getRequiredLicences() {
        return requiredLicences;
    }

    public void setRequiredLicences(List<Licence> requiredLicences) {
        this.requiredLicences = requiredLicences;
    }
}
