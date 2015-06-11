package info.desabre.application.controllers;

import java.util.logging.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorController {
	private static final Logger log = Logger.getLogger(ErrorController.class.getName());
    
	
    @RequestMapping("/error/404")
    public String pageNotFound(Model model) {
        return "error/404";
    }
	
    @RequestMapping("/error/403")
    public String forbiddenAccess(Model model) {
    	return "error/403";
    }

}
