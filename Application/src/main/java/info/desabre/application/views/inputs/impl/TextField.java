package info.desabre.application.views.inputs.impl;

import java.util.logging.Logger;

import static java.util.logging.Logger.getLogger;

/**
 * Created by MikaSez on 18/06/2015.
 */
public class TextField extends Field {
    public static final Logger log = getLogger(TextField.class.getName());


    public TextField(String name) {
        super(name);
    }

    public TextField(String name, String label) {
        super(name, label);
    }

    public TextField(String name, String label, String placeholder) {
        super(name, label, placeholder);
    }


    @Override
    public String getType() {
        return "text";
    }
}
