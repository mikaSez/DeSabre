package info.desabre.application.views.inputs.impl;

/**
 * Created by MikaSez on 18/06/2015.
 */
public class ResetField extends Field {

    public ResetField(String name) {
        super(name);
    }

    public ResetField(String name, String label) {
        super(name, label);
    }

    public ResetField(String name, String label, String placeholder) {
        super(name, label, placeholder);
    }

    @Override
    public String getType() {
        return "reset";
    }
}
