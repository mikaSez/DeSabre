package info.desabre.application.views.inputs.impl;

/**
 * Created by MikaSez on 18/06/2015.
 */
public class ButtonField extends Field {
    public ButtonField(String name) {
        super(name);
    }

    public ButtonField(String name, String label) {
        super(name, label);
    }

    public ButtonField(String name, String label, String placeholder) {
        super(name, label, placeholder);
    }

    @Override
    public String getType() {
        return "button";
    }
}
