package info.desabre.database.models.job;

import org.springframework.data.annotation.Id;

import java.util.List;

/**
 * Created by MikaSez on 02/06/2015.
 */
public class Script {

    @Id
    private int id;
    private String name;
    private String content;
    private String groupeId;

    private List<ScriptParameters> parameters;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getGroupeId() {
        return groupeId;
    }

    public void setGroupeId(String groupeId) {
        this.groupeId = groupeId;
    }

    public List<ScriptParameters> getParameters() {
        return parameters;
    }

    public void setParameters(List<ScriptParameters> parameters) {
        this.parameters = parameters;
    }
}
