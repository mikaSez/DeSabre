package info.desabre.database.models.information;

import info.desabre.UserConstants;

import java.sql.Timestamp;

public class News {
    private int id;
    private String title;
    private Timestamp time;
    private String icon;
    private String path;
    private int groupId;

    public News(int id, String title, Timestamp time, String icon, String path) {
        this.id = id;
        this.title = title;
        this.time = time;
        this.icon = icon;
        this.path = path;
        this.groupId = UserConstants.GLOBAL_GROUPEID.getGroupeId();
    }

    public News(int id, String title, Timestamp time, String icon, String path, int groupeId) {
        this.id = id;
        this.title = title;
        this.time = time;
        this.icon = icon;
        this.path = path;
        this.groupId = groupeId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Timestamp getTime() {
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