package info.desabre.application.views.generator;

import info.desabre.application.views.generator.annotations.*;
import info.desabre.application.views.generator.annotations.Field;
import info.desabre.application.views.generator.annotations.types.CheckBox;
import info.desabre.application.views.generator.annotations.types.Mail;
import info.desabre.application.views.generator.annotations.types.Password;
import info.desabre.application.views.inputs.Input;
import info.desabre.application.views.inputs.form.Form;
import info.desabre.application.views.inputs.impl.*;

import java.lang.annotation.Annotation;

/**
 * Created by MikaSez on 21/06/2015.
 */
public class FormProcessor {


    private static final FormProcessor processor = new FormProcessor();

    public static FormProcessor getInstance() {
        return processor;
    }


    /**
     * Process a view annotated with @link{Form} and return a generated form
     */
    public <T> Form<T> processView(T view) {
        System.out.println("begins");
        check(view);
        Form<T> form = createForm(view);
        return form;
    }

    private <T> Form<T> createForm(T view) {

        Form<T> form;

        info.desabre.application.views.generator.annotations.Form a = view.getClass().getAnnotationsByType(info.desabre.application.views.generator.annotations.Form.class)[0];

        form = new Form<>(a.id());
        form.setNbColumn(a.nbColumn());
        form.setReadOnly(a.readOnly());
        form.setPath(a.path());


        for (java.lang.reflect.Field f : view.getClass().getDeclaredFields()) {
            if (isField(f)) {
                Field fieldAn = f.getAnnotationsByType(Field.class)[0];

                Input i = createField(f, fieldAn.name(), view);
                i.setColumn(fieldAn.column());
                for (Annotation an : f.getDeclaredAnnotations()) {
                    if (an.annotationType().equals(Autocomplete.class)) {
                        i.autoComplete();
                    }
                    if (an.annotationType().equals(Autosave.class)) {
                        i.autoSave();
                    }
                    if (an.annotationType().equals(Readonly.class)) {
                        i.lock();
                    }
                    if (an.annotationType().equals(Hidden.class)) {
                        i.hide();
                    }
                    if (an.annotationType().equals(Label.class)) {
                        Label l = (Label) an;
                        i.setLabel(l.text());
                    }
                    if (an.annotationType().equals(Placeholder.class)) {
                        Placeholder p = (Placeholder) an;
                        i.setPlaceholder(p.text());
                    }
                    if (an.annotationType().equals(Optional.class)) {
                        i.optional();
                    }
                }
                form.addInput(i, fieldAn.column());
            }
        }

        return form;
    }

    private <T> Input createField(java.lang.reflect.Field f, String name, T view) {
        Input i;

        if (hasAnnotation(f, Password.class)) {
            i = new PasswordField(name);

        } else if (hasAnnotation(f, Mail.class)) {
            i = new MailField(name);
        } else if (hasAnnotation(f, info.desabre.application.views.generator.annotations.types.Number.class)) {
            i = new NumberField(name);
        } else if (hasAnnotation(f, CheckBox.class)) {
            i = new CheckBoxField(name);
        } else {
            i = new TextField(name);
        }

        try {
            if (f.get(view) != null && f.get(view) instanceof String)
                i.setValue((String) f.get(view));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return i;
    }

    private boolean hasAnnotation(java.lang.reflect.Field f, Class<? extends Annotation> c) {
        return f.getAnnotationsByType(c).length != 0;
    }

    private boolean isField(java.lang.reflect.Field f) {
        return hasAnnotation(f, Field.class);
    }

    private <T> void check(T view) {
        if (view.getClass().getAnnotationsByType(info.desabre.application.views.generator.annotations.Form.class).length == 0) {
            throw new IllegalArgumentException("View should be annotated with @Form");
        }
        boolean fieldsAnnotated = false;
        for (java.lang.reflect.Field f : view.getClass().getDeclaredFields()) {

            if (f.getAnnotationsByType(Field.class).length != 0) {
                fieldsAnnotated = true;
            }
        }
        if (!fieldsAnnotated) {
            throw new IllegalArgumentException("Atleast one field should be annotated with @Field");
        }

    }

}
