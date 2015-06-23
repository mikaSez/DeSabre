package info.desabre.application.services;

/**
 * Created by MikaSez on 23/06/2015.
 */
public enum ScriptTypes {
    CLOJURE("clojure"), CMAKE("cmake"), COBOL("cobol"), COMMONLISP("commonlisp"), D("d"), ERLANG("erlang"),
    HASKELL("haskell"), JAVASCRIPT("javascript"), JULIA("julia"), MATHEMATICA("mathematica"), MODELICA("modelica"), PERL("perl"), PROPERTIES("properties"),
    PYTHON("python"), SCHEME("scheme"), SHELL("shell"), XML("xml");

    private final String name;

    ScriptTypes(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
