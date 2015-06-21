package info.desabre.application.views.inputs.form;

import info.desabre.application.views.inputs.Input;
import info.desabre.application.views.inputs.SpecialInput;
import info.desabre.application.views.inputs.impl.SpecialField;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by MikaSez on 20/06/2015.
 * this form is used to represent the formWidget in java and make easy to construct UI from beans
 */
public class Form<T> {
    public static final Logger log = Logger.getLogger(Form.class.getName());

    private String id;
    private int nbColumn;
    private List<SpecialInput> specialInputs;
    private List<Input> inputs;
    private boolean readOnly = true;
    private String path;

    public String getPath() {
        return path;
    }

    public Form(String id) {
        this.id = id;
        specialInputs = new ArrayList<>();
        inputs = new ArrayList<>();

    }

    public static Form oneColumnForm(String id) {
        Form form = new Form(id);
        form.nbColumn = 1;
        return form;
    }

    public static Form twoColumnForm(String id) {
        Form form = new Form(id);
        form.nbColumn = 2;
        return form;
    }

    public static Form threeColumnForm(String id) {
        Form form = new Form(id);
        form.nbColumn = 3;
        return form;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getNbColumn() {
        return nbColumn;
    }

    public void setNbColumn(int nbColumn) {
        this.nbColumn = nbColumn;
    }

    public List<SpecialInput> getSpecialInputs() {
        return specialInputs;
    }


    public List<Input> getInputs() {
        return inputs;
    }


    public boolean isReadonly() {
        return readOnly;
    }

    public void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
    }

    public void addInput(Input input, int column) {
        input.setColumn(column);
        this.inputs.add(input);
    }

    public void addSpecialInput(SpecialInput specialInput, int column) {
        Input input = new SpecialField(specialInput.getId());
        this.specialInputs.add(specialInput);
        this.addInput(input, column);

    }

    public void setSpecialInputs(List<SpecialInput> specialInputs) {
        this.specialInputs = specialInputs;
    }

    public void setInputs(List<Input> inputs) {
        this.inputs = inputs;
    }

    public void setPath(String path) {
        this.path = path;
    }

    protected static String capitalise(String word) {
        if (word == null) {
            return "";
        }
        return word.substring(0, 1).toUpperCase() + word.substring(1);
    }


    public T mapToObject(Map<String, String> map, T object) {

        for (Map.Entry<String, String> entry : map.entrySet()) {
            Method[] methods = object.getClass().getMethods();
            for (Method m : methods) {
                try {
                    if (m.getName().substring(0, 2).equals("is")) {
                        if (m.getName().substring(2).compareToIgnoreCase(entry.getKey()) == 0) {
                            m.invoke(object, true);
                        } else {
                            m.invoke(object, false);
                        }
                    }
                    if (m.getName().substring(0, 3).equals("set") && m.getName().substring(3).compareToIgnoreCase(entry.getKey()) == 0) {

                        Class t = m.getParameterTypes()[0];

                        if (t.equals(Boolean.class)) {
                            m.invoke(object, true);

                        } else if (t.equals(Integer.class)) {
                            m.invoke(object, Integer.parseInt(entry.getValue()));
                        } else {
                            m.invoke(object, entry.getValue());
                        }

                    } else if (m.getName().substring(0, 3).equals("set") && m.getParameterTypes()[0].equals(Boolean.class)) {
                        m.invoke(object, false);

                    }

                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
        log.info("final object : " + object);
        return object;
    }
}
