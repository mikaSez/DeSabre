package info.desabre.application.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Naiirod on 07/06/2015.
 * Controleur responsable de la gestion des serveurs.
 */
@Controller
public class ServerController {


    @RequestMapping("/server/list")
    public String serverList(Model model) {

        return "server/serverList";
    }
    
}
