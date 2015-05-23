package info.desabre.application.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by MikaSez on 24/05/2015.
 */
@Controller
public class GlobalController {

    @RequestMapping({"/"})
    public String greeting(Model model) {
        System.out.println("nothing controller");
        return "user/home";
    }


}
