package info.desabre.application.controllers;

import info.desabre.application.views.grid.ScriptGridView;
import info.desabre.database.models.job.Script;
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
@RequestMapping("/script/**")
public class ScriptController {

    private static final Logger log = Logger.getLogger(ScriptController.class.getName());

    @RequestMapping("list")
    public String list(Model model) {
        log.info("Asked for list");
        return "script/scriptList";
    }

    @RequestMapping("/create")
    public String create(Model model) {

        return "server/serverCreate";
    }

    @RequestMapping("data")
    public
    @ResponseBody
    List<ScriptGridView> data(Model model) {
        log.info("data requested");
        List<Script> scripts = new ArrayList();
        return Collections.unmodifiableList(ScriptGridView.map(scripts));
    }

}
