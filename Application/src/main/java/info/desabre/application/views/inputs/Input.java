package info.desabre.application.views.inputs;

/**
 * Created by MikaSez on 18/06/2015.
 */
public interface Input {
    /**
     * Défini le label qui sera affiché au dessus du input
     **/
    void setLabel(String label);

    /**
     * Defini la valeur à mettre à l'affichage de l'input
     */
    void setValue(String value);

    /**
     * Defini le message d'aide affiché dans l'input
     */
    void setPlaceholder(String placeholder);

    /**
     * Rend le champ non modifiable.
     */
    void lock();

    /**
     * Rend le champ modifiable
     */
    void unlock();

    /**
     * Rend le champ obligatoire
     */
    void mandatory();

    /**
     * Rend le champ optionnel
     */
    void optional();

    /**
     * Permet une autocompletion simple du champ
     */
    void autoComplete();

    /**
     * Désactive l'autocompletion simple du champ
     */
    void disableAutoComplete();

    /**
     * Permet de garder l'historique des valeurs saisies
     */
    void autoSave();

    /**
     * Permet de ne pas garder l'historique des valeurs saisiess
     */
    void disableSave();

    void setName(String name);

    void hide();

    void show();

    String getValue();

    String getLabel();

    String getPlaceholder();

    boolean getReadonly();

    boolean getHidden();

    boolean getAutocomplete();

    boolean getAutosave();

    boolean getRequired();

    String getType();

    int getColumn();

    String getName();

    void setColumn(int column);


}
