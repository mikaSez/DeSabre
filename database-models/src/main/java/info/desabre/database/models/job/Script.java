package info.desabre.database.models.job;

import info.desabre.database.models.user.User;
import org.springframework.data.annotation.Id;

import java.util.List;

/**
 * Created by MikaSez on 02/06/2015.
 */
public class Script {

    @Id
    private String id;
    private String name;
    private Boolean mainScript;
    private String content;
    private User user;


    private List<ScriptParameters> parameters;


    public Script(String name, String content){
    	this.name = name;
    	this.content = content;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Boolean getMainScript() {
        return mainScript;
    }

    public void setMainScript(Boolean mainScript) {
        this.mainScript = mainScript;
    }

    public List<ScriptParameters> getParameters() {
        return parameters;
    }

    public void setParameters(List<ScriptParameters> parameters) {
        this.parameters = parameters;
    }
}
