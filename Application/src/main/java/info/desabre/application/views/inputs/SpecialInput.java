package info.desabre.application.views.inputs;

import java.util.List;

/**
 * Created by MikaSez on 20/06/2015.
 */
public abstract class SpecialInput {
    String id;
    Header header;
    List<Data> data;

    public String getId() {
        return this.id;

    }

    public Header getHeader() {
        return this.header;
    }

    public List<Data> getData() {
        return this.data;
    }

}

class Header {
    String title;
    String key;
    String value;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

class Data {

    String id;
    String key;
    String value;

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