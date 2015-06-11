package info.desabre.database.models.notification;

import org.springframework.data.annotation.Id;

public class Notification {

    @Id
    private String id;

    private String name;
    private boolean deleted;


    public Notification() {
    }

    public Notification(String id, String name) {
        this.id = id;
        this.name = name;
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

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
