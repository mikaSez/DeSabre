package info.desabre.application.views.inputs.impl;

/**
 * Created by MikaSez on 20/06/2015.
 */
public class SpecialField extends Field {
    public SpecialField(String id) {
        super(id);
    }

    public SpecialField(String name, String label) {
        super(name, label);
    }

    public SpecialField(String name, String label, String placeholder) {
        super(name, label, placeholder);
    }

    @Override
    public String getType() {
        return "special";
    }
}
