package info.desabre.database.models.job;

import java.util.List;

import org.springframework.data.annotation.Id;

/**
 * Created by MikaSez on 02/06/2015.
 */
public class Script {

    @Id
    private String id;
    private String name;
    private String content;
    private String groupeId;
    
    private int index;

    private List<ScriptParameters> parameters;

    public Script(int index){
    	this.index = index;
    }
    
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

    public String getGroupeId() {
        return groupeId;
    }

    public void setGroupeId(String groupeId) {
        this.groupeId = groupeId;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public List<ScriptParameters> getParameters() {
        return parameters;
    }

    public void setParameters(List<ScriptParameters> parameters) {
        this.parameters = parameters;
    }
}
