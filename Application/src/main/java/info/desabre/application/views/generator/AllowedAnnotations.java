package info.desabre.application.views.generator;

import info.desabre.application.views.generator.annotations.*;

/**
 * Created by MikaSez on 21/06/2015.
 */

public enum AllowedAnnotations {
    AUTOCOMPLETE(Autocomplete.class.getName()),
    AUTOSAVE(Autosave.class.getName()),
    FIELD(Field.class.getName()),
    FORM(Form.class.getName()),
    HIDDEN(Hidden.class.getName()),
    LABEL(Label.class.getName()),
    PLACEHOLDER(Placeholder.class.getName()),
    READONLY(Readonly.class.getName()),
    REQUIRED(Optional.class.getName()),
    NOPE("nope");

    private final String name;

    AllowedAnnotations(String name) {
        this.name = name;
    }

    public boolean isAllowed(String name) {
        for (AllowedAnnotations a : AllowedAnnotations.values()) {
            if (a.name.equals(name)) {
                return true;
            }
        }
        return false;
    }

    public String getName() {
        return name;
    }
}
