package info.desabre.application.controllers;

import info.desabre.application.views.forms.views.ServerCreateView;
import info.desabre.application.views.grid.ServerGridView;
import info.desabre.database.models.server.Server;
import info.desabre.repositories.licence.LicenceRepository;
import info.desabre.repositories.server.ServerRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

import javax.validation.Valid;

/**
 * Created by DeSaBre on 07/06/2015.
 * Controleur responsable de la gestion des serveurs.
 */
@Controller
@RequestMapping("/server/**")
public class ServerController {

    private static final Logger log = Logger.getLogger(ServerController.class.getName());

    @Autowired
    private ServerRepository repositoryS;
    @Autowired
    private LicenceRepository repositoryL;
    
    
    @RequestMapping("list")
    public String list(Model model) {

        return "server/serverList";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(@ModelAttribute ServerCreateView server, Model model) {
        server.setLicences(repositoryL.findAll());
        model.addAttribute("server", server);
        model.addAttribute("licences", server.getLicences());
        return "server/serverCreate";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@ModelAttribute("server") @Valid ServerCreateView server, BindingResult bindingResult, Model model) {
        server.setLicences(repositoryL.findAll());
        model.addAttribute("server", server);
        model.addAttribute("licences", server.getLicences());

        if (!validateServerName(server.getName())) {
            model.addAttribute("errorName", true);
            bindingResult.addError(new ObjectError("server", "le nom n'est pas valide"));
            log.info("'" + server.getName() + "' n'est pas enregistrable");
        }

        if (!bindingResult.hasErrors()) {
            model.addAttribute("saved", true);
            repositoryS.save(server.mapToServer());
            log.info("Vous avez ajouté le serveur '" + server.getName() + "'");
        }

        return "server/serverCreate";
    }


    @RequestMapping("data")
    public
    @ResponseBody
    List<ServerGridView> data(Model model) {
        log.info("data server requested");
        List<Server> servers = new ArrayList();
        servers.addAll(repositoryS.findAll());
        return Collections.unmodifiableList(ServerGridView.map(servers));
    }

    private boolean validateServerName(String name) {
        return repositoryS.findByName(name) == null;
    }
}
