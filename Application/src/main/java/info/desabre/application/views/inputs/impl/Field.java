package info.desabre.application.views.inputs.impl;

import info.desabre.application.views.inputs.Input;

/**
 * Created by MikaSez on 18/06/2015.
 */
public abstract class Field implements Input {

    private String label;
    private String value;
    private String placeholder;
    private String name;
    private int column = 1;
    private boolean readOnly = false;
    private boolean required = true;
    private boolean autocomplete = true;
    private boolean hidden;
    private boolean autosave = true;


    public Field(String name) {
        this.name = name;
    }

    public Field(String name, String label) {
        this.name = name;
        this.label = label;
    }

    public Field(String name, String label, String placeholder) {
        this.name = name;
        this.label = label;
        this.placeholder = placeholder;
    }

    @Override
    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
    }

    @Override
    public void lock() {
        this.readOnly = true;
    }

    @Override
    public void unlock() {
        this.readOnly = false;
    }

    @Override
    public void mandatory() {
        this.required = true;
    }

    @Override
    public void optional() {
        this.required = false;
    }

    @Override
    public void autoComplete() {
        this.autocomplete = true;
    }

    @Override
    public void disableAutoComplete() {
        this.autocomplete = false;
    }

    @Override
    public void autoSave() {
        this.autosave = true;
    }

    @Override
    public void disableSave() {
        this.autosave = false;
    }

    @Override
    public void hide() {
        this.hidden = true;
    }

    @Override
    public void show() {
        this.hidden = false;
    }

    @Override
    public String getValue() {
        return this.value;
    }

    @Override
    public String getLabel() {
        return this.label;
    }

    @Override
    public String getPlaceholder() {
        return this.placeholder;
    }

    @Override
    public boolean getReadonly() {
        return this.readOnly;
    }

    @Override
    public boolean getHidden() {
        return this.hidden;
    }

    @Override
    public boolean getAutocomplete() {
        return this.autocomplete;
    }

    @Override
    public boolean getAutosave() {
        return this.autosave;
    }

    @Override
    public boolean getRequired() {
        return this.required;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setColumn(int column) {
        this.column = column;
    }

    @Override
    public int getColumn() {
        return this.column;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
