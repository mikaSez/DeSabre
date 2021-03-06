package info.desabre.application.controllers;

import info.desabre.application.views.forms.views.JobCreateView;
import info.desabre.application.views.forms.views.JobLaunchView;
import info.desabre.application.views.grid.JobGridView;
import info.desabre.database.models.job.Job;
import info.desabre.repositories.job.JobRepository;
import info.desabre.repositories.job.ScriptRepository;
import info.desabre.repositories.licence.LicenceRepository;
import info.desabre.repositories.server.ServerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
        //job.setScripts(repositoryS.findAll());
        job.setLicences(repositoryL.findAll());
    	model.addAttribute("job", job);
        //model.addAttribute("scripts", job.getScripts());
        //if(job.getScripts().size() != 0) // si vide le html teste si scripts est declaré
        //	model.addAttribute("scripts", job.getScripts());
        return "job/jobCreate";
    }
    
    @RequestMapping(value="/create", method=RequestMethod.POST)
    public String create(@ModelAttribute("job") @Valid JobCreateView job, BindingResult bindingResult, Model model) throws IOException {
        if(job.getName() == "") {
            model.addAttribute("name", true);
    		bindingResult.addError(new ObjectError("job", "le nom n'est pas valide"));
            log.info("'"+job.getName()+"' n'est pas enregistrable");
    	}
    	
    	if (!bindingResult.hasErrors()) {
        	model.addAttribute("saved", true);
        	repositoryJ.save(job.mapToJob());
            log.info("Vous avez ajouté le job '"+job.getName()+"'");
            Path file = Files.createFile(Paths.get("./Application/distant/waiting/job" + repositoryJ.findByName(job.getName()).getId() + ".ser"));
            System.out.println(file);
        }

    	job.setLicences(repositoryL.findAll());
        //job.setScripts(repositoryS.findAll());
        model.addAttribute("job", job);
        //model.addAttribute("scripts", job.getScripts());

        return "job/jobCreate";
    }
    
    @RequestMapping(value="/create", params={"addRow"})
    public String addRow(@ModelAttribute("job") JobCreateView job, BindingResult bindingResult, Model model) {
    	this.index++;
    	job.setLicences(repositoryL.findAll());
        // job.getScripts().add(new Script(this.index));
    	model.addAttribute("job", job);
    	model.addAttribute("licences", job.getLicences());
        //model.addAttribute("scripts", job.getScripts());

        return "job/jobCreate";
    }

    @RequestMapping(value="/create", params={"removeRow"})
    public String removeRow(@ModelAttribute JobCreateView job, BindingResult bindingResult, final HttpServletRequest req, Model model) {
        final Integer rowId = Integer.valueOf(req.getParameter("removeRow"));
        //job.getScripts().remove(rowId.intValue());
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
    
    
    @RequestMapping(value="/job/launch/config", method=RequestMethod.GET)
    public String lauchConfig(@RequestParam("id") String id, Model model) {
        Job job = repositoryJ.findById(id);

        model.addAttribute("job", job);
        model.addAttribute("servers", repositoryServ.findAll());
        return "job/jobLaunchConfig";
    }
    
    @RequestMapping(value="/job/launch/config", method=RequestMethod.POST)
    public String lauchConfig(@ModelAttribute("job") @Valid JobLaunchView job, Model model) {
    	Job j = repositoryJ.findByName(job.getName());
    	
    	ObjectOutputStream oos = null;
    	
        try {
            final FileOutputStream fichier = new FileOutputStream("./Application/distant/waiting/job" + j.getId() + ".ser");

          oos = new ObjectOutputStream(fichier);
          oos.writeObject(j);
          oos.flush();
        } catch (final java.io.IOException e) {
          e.printStackTrace();
      		model.addAttribute("errlaunched", true);
        } finally {
          try {
            if (oos != null) {
              oos.flush();
              oos.close();
            }
        	model.addAttribute("launched", true);
        	System.out.println("Job "+j.getId()+" bien lancé !");
          } catch (final IOException ex) {
            ex.printStackTrace();
          }
        }
    	
        return "job/jobLaunch";
    }

    @RequestMapping("/job/view/{id}")
    public String jobStatus(@PathVariable("id") String id, Model model) {
        Job job = repositoryJ.findById(id);
        model.addAttribute("jobName", job.getName());
        model.addAttribute("jobStatus", getJobStatus(id));
        return "/job/jobView";
    }

    private Object getJobStatus(String id) {
        ResponseEntity<String> response = null;
        try {
            RestTemplate restTemplate = new RestTemplate();
            response = restTemplate.getForEntity(
                    "http://localhost:8090/jobStatus/" + id,
                    String.class);
        } catch (Exception e) {

        }
        if (response == null) {
            return "pas de connexion au serveur";
        }

        return response.getBody();
    }
}

