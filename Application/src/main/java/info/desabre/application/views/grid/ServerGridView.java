package info.desabre.application.views.grid;

import info.desabre.database.models.server.Server;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by MikaSez on 08/06/2015.
 * This class is used to map data from a server object to a view object witch will be transmitted to the view and shown
 * Security / And bandwidth purposes
 */
public class ServerGridView {

    private String name;
    private int cores;
    private int memory;
    private String path;
    private static List<String> headers;
    
    static {
    	headers = new ArrayList<>();
        headers.addAll(Arrays.asList("Nom", "Nombre de coeurs", "Memoire", "Visualiser"));
    }


    public ServerGridView(String name, int cores, int memory) {
        this.name = name;
        this.cores = cores;
        this.memory = memory;
    }


    public static ServerGridView map(Server server) {
        ServerGridView view = new ServerGridView(server.getName(), server.getCoreCount(), server.getMemory());
        view.path = "/server/update/" + server.getId();

        return view;
    }

    public static List<ServerGridView> map(List<Server> servers) {
        return servers.stream().map(ServerGridView::map).collect(Collectors.toList());
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCores() {
        return cores;
    }

    public void setCores(int cores) {
        this.cores = cores;
    }

    public int getMemory() {
        return memory;
    }

    public void setMemory(int memory) {
        this.memory = memory;
    }

    public static void setHeaders(List<String> headers) {
        ServerGridView.headers = headers;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }


    public List<String> getHeaders(){
		return headers;
	}
}
