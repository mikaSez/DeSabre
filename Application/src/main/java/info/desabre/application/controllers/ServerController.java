package info.desabre.application.controllers;

import info.desabre.application.views.forms.ClusterCreationForm;
import info.desabre.application.views.forms.views.ClusterCreationView;
import info.desabre.application.views.forms.views.ClusterUpdateView;
import info.desabre.application.views.forms.views.ServerCreateView;
import info.desabre.application.views.generator.FormProcessor;
import info.desabre.application.views.grid.ServerGridView;
import info.desabre.application.views.inputs.form.Form;
import info.desabre.database.models.server.Licence;
import info.desabre.database.models.server.Server;
import info.desabre.repositories.licence.LicenceRepository;
import info.desabre.repositories.server.ServerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * Created by DeSaBre on 07/06/2015.
 * Controleur responsable de la gestion des serveurs.
 */
@Controller
@RequestMapping("/server/**")
public class ServerController {

    private static final Logger log = Logger.getLogger(ServerController.class.getName());

    @Autowired
    private ServerRepository serverRepository;
    @Autowired
    private LicenceRepository licenceRepository;

    Server server;
    @RequestMapping("list")
    public String list() {
        return "server/serverList";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(@ModelAttribute ServerCreateView server, Model model) {

        Form<ClusterCreationView> f = FormProcessor.getInstance().processView(new ClusterCreationView());
        model.addAttribute("form", f);
        return "server/serverCreate";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@RequestParam Map<String, String> data, Model model) {
        log.info("Checking server validity");


        Form<ClusterCreationView> f = new ClusterCreationForm("clusterCreation");
        ClusterCreationView view = new ClusterCreationView();
        f.mapToObject(data, view);

        if (clusterCreationHasErrors(view, model)) {
            f = FormProcessor.getInstance().processView(view);
            model.addAttribute("form", f);
            return "server/serverCreate";
        }
        serverRepository.save(view.toServer());
        return "redirect:/server/list";
    }

    private boolean clusterCreationHasErrors(ClusterCreationView view, Model model) {
        boolean hasErrors = false;
        List<String> errors = new ArrayList<>();
        if (view.getName().trim().isEmpty()) {
            errors.add("Le nom ne doit pas être vide");
        } else if (serverRepository.findByName(view.getName().trim()) != null) {
            errors.add("Un serveur avec ce nom existe déjà");
        }
        if (view.getAddress().trim().isEmpty()) {
            errors.add("L'addresse ne doit pas être vide");
        } else if (serverRepository.findByAddress(view.getAddress().trim()) != null) {
            errors.add("L'adresse spécifiée est déjà associée à un serveur");
        }
        model.addAttribute("errors", errors);
        return !errors.isEmpty();
    }

    @RequestMapping(value = "saveLicences", method = RequestMethod.POST)
    public
    @ResponseBody
    String create(@RequestBody String licences) {
        String[] l = licences.split(",");

        Arrays.asList(l).forEach(licence -> {
            server.getLicences().add(licenceRepository.findByName(licence));
        });
        serverRepository.save(server);
        return "success";
    }

    @RequestMapping(value = "update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable("id") String id, Model model) {
        server = serverRepository.findById(id);

        Form<ClusterUpdateView> f = FormProcessor.getInstance().processView(ClusterUpdateView.fromServer(server));

        model.addAttribute("form", f);
        List<String> allLicences = licenceRepository.findAll().stream().map(Licence::getName).collect(Collectors.toList());

        List<String> serverLicences = server.getLicences().stream().map(Licence::getName).collect(Collectors.toList());

        allLicences = allLicences.stream().filter(l -> !serverLicences.contains(l)).collect(Collectors.toList());

        model.addAttribute("licences", allLicences);
        model.addAttribute("serverLicences", serverLicences);
        return "/server/serverUpdate";
    }

    @RequestMapping("data")
    public
    @ResponseBody
    List<ServerGridView> data(Model model) {
        log.info("data server requested");
        List<Server> servers = new ArrayList();
        servers.addAll(serverRepository.findAll());
        return Collections.unmodifiableList(ServerGridView.map(servers));
    }

    private boolean validateServerName(String name) {
        return serverRepository.findByName(name) == null;
    }
}
