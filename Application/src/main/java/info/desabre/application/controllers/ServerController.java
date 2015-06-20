package info.desabre.application.controllers;

import info.desabre.application.views.grid.ServerGridView;
import info.desabre.database.models.server.Server;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Naiirod on 07/06/2015.
 * Controleur responsable de la gestion des serveurs.
 */
@Controller
@RequestMapping("/server/**")
public class ServerController {

    private static final Logger log = Logger.getLogger(ServerController.class.getName());

    @RequestMapping("list")
    public String list(Model model) {

        return "server/serverList";
    }

    @RequestMapping("/create")
    public String create(Model model) {

        return "server/serverCreate";
    }
    
    @RequestMapping("data")
    public
    @ResponseBody
    List<ServerGridView> data(Model model) {
        log.info("data requested");
        List<Server> servers = new ArrayList();
        servers.add(new Server("lzefze", "Un pettit serveur", 1, 5));
        return Collections.unmodifiableList(ServerGridView.map(servers));
    }

}
