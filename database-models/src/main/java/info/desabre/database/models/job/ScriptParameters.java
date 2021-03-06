package info.desabre.database.models.job;

import org.springframework.data.annotation.Id;

/**
 * Created by MikaSez on 02/06/2015.
 */
public class ScriptParameters {
    @Id
    private String id;
    public String key;
    private String value;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
