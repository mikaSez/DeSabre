package desabre.models.information;

import java.sql.Timestamp;

public class News {
    private int id;
    private String title;
    private Timestamp time;
    private String icon;
    private String path;

    public News(int id, String title, Timestamp time, String icon, String path) {
        this.id = id;
        this.title = title;
        this.time = time;
        this.icon = icon;
        this.path = path;
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
}