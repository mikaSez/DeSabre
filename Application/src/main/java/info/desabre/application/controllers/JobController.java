package info.desabre.application.controllers;

import info.desabre.application.views.JobCreateView;
import info.desabre.application.views.JobGridView;
import info.desabre.database.models.job.Job;
import info.desabre.repositories.job.JobRepository;

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

/**
 * Created by MikaSez on 01/06/2015.
 * Controleur responsable de la gestion des jobs.
 */
@Controller
@RequestMapping("/job/**")
public class JobController {

    private static final Logger log = Logger.getLogger(JobController.class.getName());
    @Autowired
    private JobRepository repository;

    @RequestMapping("/list")
    public String list(Model model) {

        return "job/jobList";
    }

    @RequestMapping(value="/create", method=RequestMethod.GET)
    public String create(@ModelAttribute JobCreateView job, Model model) {
    	model.addAttribute("job", job);
        return "job/jobCreate";
    }
    
    @RequestMapping(value="/create", method=RequestMethod.POST)
    public String create(@ModelAttribute("job") @Valid JobCreateView job, BindingResult bindingResult, Model model) {
    	model.addAttribute("job", job);
    	if(job.getName() == "") {
            model.addAttribute("name", true);
    		bindingResult.addError(new ObjectError("job", "le nom n'est pas valide"));
            log.info("'"+job.getName()+"' n'est pas enregistrable");
    	}
    	
    	if (!bindingResult.hasErrors()) {
        	model.addAttribute("saved", true);
            repository.save(job.mapToJob());
            log.info("Vous avez ajouté le job '"+job.getName()+"'");
        }

        return "job/jobCreate";
    }
    
    @RequestMapping("data")
    public
    @ResponseBody
    List<JobGridView> data(Model model) {
        log.info("data requested");
        List<Job> jobs = new ArrayList();
        jobs.add(new Job("0", "Compter les poules", "10/02/2003"));
        jobs.add(new Job("1", "Vitesse moyenne de Superman", "10/02/2005"));
        jobs.add(new Job("2", "Nombre PI", "10/02/2003"));
        jobs.add(new Job("3", "Dechiffrage enigma", "10/02/1941"));
        jobs.add(new Job("4", "Chiffrage Titan", "10/02/1942"));
        jobs.add(new Job("5", "Compter les poules", "10/02/2003"));
        jobs.add(new Job("6", "Vitesse moyenne de Superman", "10/02/2003"));
//        {id: 9, title: "Vitesse moyenne de Superman", date: "10/02/2005", path: "#"},
//        {id: 21, title: "Production moyenne de toile par Spiderman", date: "10/02/2003", path: "#"},
//        {id: 32, title: "Nombre PI", date: "10/02/2003", path: "#"},
//        {id: 43, title: "Dechiffrage enigma ", date: "10/02/1941", path: "#"},
//        {id: 54, title: "Chiffrage Titan", date: "10/02/1942", path: "#"},
//        {id: 123, title: "Compter les poules", date: "10/02/2003", path: "#"},
//        {id: 15, title: "Vitesse moyenne de Superman", date: "10/02/2005", path: "#"},
//        {id: 23, title: "Production moyenne de toile par Spiderman", date: "10/02/2003", path: "#"},
//        {id: 323, title: "Nombre PI", date: "10/02/2003", path: "#"},
//        {id: 489, title: "Dechiffrage enigma ", date: "10/02/1941", path: "#"},
//        {id: 5435, title: "Chiffrage Titan", date: "10/02/1942", path: "#"},
//        {id: 612, title: "Nombre de naissance par seconde", date: "10/02/2011", path: "#"}
        return Collections.unmodifiableList(JobGridView.map(jobs));
    }
    
    
    @RequestMapping("paramdata")
    public
    @ResponseBody
    List<JobGridView> paramdata(Model model) {
        log.info("data requested");
        List<Job> jobs = new ArrayList();
        jobs.add(new Job("0", "Compter les poules", "10/02/2003"));
        jobs.add(new Job("1", "Vitesse moyenne de Superman", "10/02/2005"));
        jobs.add(new Job("2", "Nombre PI", "10/02/2003"));
        jobs.add(new Job("3", "Dechiffrage enigma", "10/02/1941"));
        jobs.add(new Job("4", "Chiffrage Titan", "10/02/1942"));
        jobs.add(new Job("5", "Compter les poules", "10/02/2003"));
        jobs.add(new Job("6", "Vitesse moyenne de Superman", "10/02/2003"));
        return Collections.unmodifiableList(JobGridView.map(jobs));
    }


}

