package info.desabre;

/**
 * Created by MikaSez on 01/07/2015.
 */
public enum STATUS {
    WAITING("waiting"), TRAITEMENT("traitement"), FINAL("final"), ERROR("error");

    final String name;

    STATUS(String name) {
        this.name = name;
    }
}
