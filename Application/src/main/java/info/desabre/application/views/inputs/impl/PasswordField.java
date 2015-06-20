package info.desabre.application.views.inputs.impl;

/**
 * Created by MikaSez on 18/06/2015.
 */
public class PasswordField extends Field {
    public PasswordField(String name) {
        super(name);
    }

    public PasswordField(String name, String label) {
        super(name, label);
    }

    public PasswordField(String name, String label, String placeholder) {
        super(name, label, placeholder);
    }

    @Override
    public String getType() {
        return "password";
    }
}
