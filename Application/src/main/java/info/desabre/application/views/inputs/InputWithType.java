package info.desabre.application.views.inputs;

/**
 * Created by MikaSez on 23/06/2015.
 */
public class InputWithType<T> {
    public static final String DATE = "date";
    public static final String BOOLEAN = "boolean";
    private String type;
    private T value;

    public InputWithType(T value, String type) {
        this.type = type;
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
