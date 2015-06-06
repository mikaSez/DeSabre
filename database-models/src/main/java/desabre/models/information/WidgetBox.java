package desabre.models.information;

/**
 * Created by MikaSez on 06/06/2015.
 */
public class WidgetBox {
    private int id;
    private String color;
    private String icon;
    private int number;
    private String path;
    private String text;

    public WidgetBox(int id, String color, String icon, int number, String path, String text) {
        this.id = id;
        this.color = color;
        this.icon = icon;
        this.number = number;
        this.path = path;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}