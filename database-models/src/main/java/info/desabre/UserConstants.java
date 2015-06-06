package info.desabre;

/**
 * Created by MikaSez on 06/06/2015.
 */
public enum UserConstants {
    //deplacer dans le parametrage ?
    STARTING_GROUPEID(100), GLOBAL_GROUPEID(1);

    private final int groupeId;

    UserConstants(int id) {
        this.groupeId = id;
    }

    public int getGroupeId() {
        return groupeId;
    }
}
