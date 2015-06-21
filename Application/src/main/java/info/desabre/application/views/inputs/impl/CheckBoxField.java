package info.desabre.application.views.inputs.impl;

/**
 * Created by MikaSez on 18/06/2015.
 */
public class CheckBoxField extends Field {
    public CheckBoxField(String name) {
        super(name);
    }

    public CheckBoxField(String name, String label) {
        super(name, label);
    }

    public CheckBoxField(String name, String label, String placeholder) {
        super(name, label, placeholder);
    }


    @Override
    public String getType() {
        return "checkbox";
    }
}
