package info.desabre.application.views.forms.views;

import info.desabre.application.views.generator.annotations.Field;
import info.desabre.application.views.generator.annotations.Form;
import info.desabre.application.views.generator.annotations.Label;
import info.desabre.application.views.generator.annotations.Placeholder;
import info.desabre.application.views.generator.annotations.types.Number;
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


    @Field(name = "nbCores", column = 1)
    @Placeholder(text = "Nombre de coeurs disponibles")
    @Label(text = "Nombre de coeurs")
    @Number
    public String nbCores;


    @Field(name = "memory", column = 2)
    @Placeholder(text = "Entrez la mémoire disponible en MO")
    @Label(text = "Mémoire en MO")
    @Number
    public String memory;

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
        view.setNbCores(String.valueOf(server.getCoreCount()));
        view.setMemory(String.valueOf(server.getMemory()));

        return view;
    }


    public String getNbCores() {
        return nbCores;
    }

    public void setNbCores(String nbCores) {
        this.nbCores = nbCores;
    }

    public String getMemory() {
        return memory;
    }

    public void setMemory(String memory) {
        this.memory = memory;
    }

    public Server mergeIntoServer(Server server) {
        return server;
    }

    public Server toServer() {
        Server server = new Server();
        server.setAddress(address);
        server.setName(name);
        server.setMemory(Integer.parseInt(memory));
        server.setCoreCount(Integer.parseInt(nbCores));
        return server;
    }
}
