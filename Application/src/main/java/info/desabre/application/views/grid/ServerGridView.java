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
    private String path;
    private static List<String> headers;
    
    static {
    	headers = new ArrayList<>();
    	headers.addAll(Arrays.asList("Nom", "Nombre de coeurs", "Visualiser"));
    }
    
    
    public ServerGridView(String name, int cores) {
        this.name = name;
        this.cores = cores;
    }


    public static ServerGridView map(Server server) {
        ServerGridView view = new ServerGridView(server.getName(), server.getCoreCount());
        view.path = "#";

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
