package info.desabre.application.views.inputs.impl;

/**
 * Created by MikaSez on 18/06/2015.
 */
public class SubmitField extends Field {
    public SubmitField(String name) {
        super(name);
    }

    public SubmitField(String name, String label) {
        super(name, label);
    }

    public SubmitField(String name, String label, String placeholder) {
        super(name, label, placeholder);
    }

    @Override
    public String getType() {
        return "submit";
    }
}
