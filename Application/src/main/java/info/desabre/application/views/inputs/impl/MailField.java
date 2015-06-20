package info.desabre.application.views.inputs.impl;

/**
 * Created by MikaSez on 18/06/2015.
 */
public class MailField extends Field {

    public MailField(String name) {
        super(name);
    }

    public MailField(String name, String label) {
        super(name, label);
    }

    public MailField(String name, String label, String placeholder) {
        super(name, label, placeholder);
    }

    @Override
    public String getType() {
        return "email";
    }
}
