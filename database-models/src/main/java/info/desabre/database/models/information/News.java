package info.desabre.database.models.information;

import info.desabre.UserConstants;
import org.springframework.data.annotation.Id;

import java.sql.Timestamp;
import java.util.Date;

public class News {
    @Id
    private String id;
    private String title;
    private Date time;
    private String icon;
    private String path;
    private int groupId;

    public News() {

    }

    public News(String id, String title, Date time, String icon, String path) {
        this.id = id;
        this.title = title;
        this.time = time;
        this.icon = icon;
        this.path = path;
        this.groupId = UserConstants.GLOBAL_GROUPEID.getGroupeId();
    }

    public News(String id, String title, Date time, String icon, String path, int groupeId) {
        this.id = id;
        this.title = title;
        this.time = time;
        this.icon = icon;
        this.path = path;
        this.groupId = groupeId;
    }

    public News(String title, Date time, String icon, String path, int groupeId) {
        this.title = title;
        this.time = time;
        this.icon = icon;
        this.path = path;
        this.groupId = groupeId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupeId) {
        this.groupId = groupeId;
    }
}