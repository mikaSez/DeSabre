package info.desabre.application.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.logging.Logger;

/**
 * Created by MikaSez on 01/06/2015.
 * Controlleur responsable de la gestion des jobs.
 */
@Controller
public class JobController {

    private static final Logger log = Logger.getLogger(JobController.class.getName());

    @RequestMapping("/job/list")
    public String list(Model model) {

        return "job/jobList";
    }


    @RequestMapping("/job/create")
    public String create(Model model) {

        return "job/jobCreate";
    }

}

