package info.desabre.application.views.inputs.impl;

/**
 * Created by MikaSez on 21/06/2015.
 */
public class NumberField extends Field {
    public NumberField(String name) {
        super(name);
    }

    public NumberField(String name, String label) {
        super(name, label);
    }

    public NumberField(String name, String label, String placeholder) {
        super(name, label, placeholder);
    }

    @Override
    public String getType() {
        return "number";
    }
}
