package info.desabre.application.views.forms.views;

import info.desabre.application.views.generator.annotations.Field;
import info.desabre.application.views.generator.annotations.Form;
import info.desabre.application.views.generator.annotations.Label;
import info.desabre.application.views.generator.annotations.Placeholder;
import info.desabre.database.models.server.Server;

/**
 * Created by MikaSez on 21/06/2015.
 */
@Form(id = "ClusterCreation", nbColumn = 2, readOnly = true, path = "/server/create")
public class ClusterUpdateView {
    @Field(name = "name", column = 1)
    @Placeholder(text = "Entrez le nom du serveur")
    @Label(text = "Nom du serveur")
    public String name;

    @Field(name = "address", column = 2)
    @Placeholder(text = "Entrez l'adresse du serveur")
    @Label(text = "Adresse du serveur")
    public String address;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public static ClusterUpdateView fromServer(Server server) {
        ClusterUpdateView view = new ClusterUpdateView();
        view.setAddress(server.getAddress());
        view.setName(server.getName());

        return view;
    }


    public Server mergeIntoServer(Server server) {
        return server;
    }

    public Server toServer() {
        Server server = new Server();
        server.setAddress(address);
        server.setName(name);
        return server;
    }
}
