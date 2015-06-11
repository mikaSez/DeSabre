package info.desabre.application.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by MikaSez on 11/06/2015.
 * <p>
 * Cette classe s'occupe de la gestion des utilisateurs
 */

@Controller
public class UserController {


    @RequestMapping("/inscription/validate")
    public String validateInscriptionData(Model model) {

        boolean error = validateEmail();
        if (!error) {
            model.addAttribute("inscribed", true);
            return "login";
        }
        model.addAttribute("param.error", true);
        return "/inscription";


    }

    private boolean validateEmail() {
        return false;
    }


}
