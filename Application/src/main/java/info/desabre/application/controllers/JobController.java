package info.desabre.application.controllers;

import info.desabre.application.views.forms.views.JobCreateView;
import info.desabre.application.views.forms.views.JobLaunchView;
import info.desabre.application.views.grid.JobGridView;
import info.desabre.database.models.job.Job;
import info.desabre.database.models.job.Script;
import info.desabre.repositories.job.JobRepository;
import info.desabre.repositories.job.ScriptRepository;
import info.desabre.repositories.licence.LicenceRepository;
import info.desabre.repositories.server.ServerRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by MikaSez on 01/06/2015.
 * Controleur responsable de la gestion des jobs.
 */
@Controller
@RequestMapping("/job/**")
public class JobController {

    private static final Logger log = Logger.getLogger(JobController.class.getName());
    @Autowired
    private JobRepository repositoryJ;
    @Autowired
    private LicenceRepository repositoryL;
    @Autowired
    private ScriptRepository repositoryS;
    @Autowired
    private ServerRepository repositoryServ;
    
    private int index = 0;


    @RequestMapping("/list")
    public String list(Model model) {

        return "job/jobList";
    }
    
    @RequestMapping(value="/create", method=RequestMethod.GET)
    public String create(@ModelAttribute JobCreateView job, Model model) {
    	job.setScripts(repositoryS.findAll());
    	job.setLicences(repositoryL.findAll());
    	model.addAttribute("job", job);
    	model.addAttribute("scripts", job.getScripts());
    	if(job.getScripts().size() != 0) // si vide le html teste si scripts est declaré
    		model.addAttribute("scripts", job.getScripts());
        return "job/jobCreate";
    }
    
    @RequestMapping(value="/create", method=RequestMethod.POST)
    public String create(@ModelAttribute("job") @Valid JobCreateView job, BindingResult bindingResult, Model model) {
    	if(job.getName() == "") {
            model.addAttribute("name", true);
    		bindingResult.addError(new ObjectError("job", "le nom n'est pas valide"));
            log.info("'"+job.getName()+"' n'est pas enregistrable");
    	}
    	
    	if (!bindingResult.hasErrors()) {
        	model.addAttribute("saved", true);
        	repositoryJ.save(job.mapToJob());
            log.info("Vous avez ajouté le job '"+job.getName()+"'");
        }

    	job.setLicences(repositoryL.findAll());
    	job.setScripts(repositoryS.findAll());
    	model.addAttribute("job", job);
    	model.addAttribute("scripts", job.getScripts());
    	
        return "job/jobCreate";
    }
    
    @RequestMapping(value="/create", params={"addRow"})
    public String addRow(@ModelAttribute("job") JobCreateView job, BindingResult bindingResult, Model model) {
    	this.index++;
    	job.setLicences(repositoryL.findAll());
    	model.addAttribute("job", job);
    	model.addAttribute("licences", job.getLicences());
    	model.addAttribute("scripts", job.getScripts());
    	
        return "job/jobCreate";
    }

    @RequestMapping(value="/create", params={"removeRow"})
    public String removeRow(@ModelAttribute JobCreateView job, BindingResult bindingResult, final HttpServletRequest req, Model model) {
        final Integer rowId = Integer.valueOf(req.getParameter("removeRow"));
        job.getScripts().remove(rowId.intValue());
    	model.addAttribute("job", job);
    	model.addAttribute("licences", job.getLicences());
    	
        return "job/jobCreate";
    }
    
    
    @RequestMapping("dataLaunch")
    public
    @ResponseBody
    List<JobGridView> dataL(Model model) {
        log.info("data requested");
        List<Job> jobs = new ArrayList();
        jobs.addAll(repositoryJ.findAll());
        return Collections.unmodifiableList(JobGridView.mapL(jobs));
    }
    
    @RequestMapping("dataView")
    public
    @ResponseBody
    List<JobGridView> dataV(Model model) {
        log.info("data requested");
        List<Job> jobs = new ArrayList();
        jobs.addAll(repositoryJ.findAll());
        return Collections.unmodifiableList(JobGridView.mapV(jobs));
    }
    
}

