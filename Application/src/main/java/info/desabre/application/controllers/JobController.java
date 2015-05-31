package info.desabre.application.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by MikaSez on 01/06/2015.
 * Controlleur responsable de la gestion des jobs.
 */
@Controller
public class JobController {


    @RequestMapping("/job/list")
    public String jobList(Model model) {

        return "job/jobList";
    }

}

