package info.desabre.application.views.grid;

import info.desabre.application.views.inputs.InputWithType;
import info.desabre.database.models.job.Script;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class ScriptGridView {

    private String name;

    public InputWithType<Boolean> getMainScript() {
        return mainScript;
    }

    private InputWithType<Boolean> mainScript;
    private String path;


    private static List<String> headers;

    static {
        headers = new ArrayList<>();
        headers.addAll(Arrays.asList("name", "est principal ?", "Visualiser"));
    }


    public ScriptGridView(String name, boolean isMain) {
        this.name = name;
        this.mainScript = new InputWithType<>(isMain, InputWithType.BOOLEAN);
    }

    public static ScriptGridView map(Script script) {
        ScriptGridView view = new ScriptGridView(script.getName(), script.getMainScript());

        view.path = "/script/detail?id=" + script.getId();

        return view;
    }

    public static List<ScriptGridView> map(List<Script> scripts) {
        return scripts.stream().map(ScriptGridView::map).collect(Collectors.toList());
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }



    public List<String> getHeaders() {
        return headers;
    }


}
