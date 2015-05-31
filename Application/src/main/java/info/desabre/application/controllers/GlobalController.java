package info.desabre.application.controllers;

import info.desabre.application.security.Role;
import info.desabre.application.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by MikaSez on 24/05/2015.
 * Controller used for first application access
 */
@Controller
public class GlobalController {


    @Autowired
    private UserService userService;
    @RequestMapping({"/"})
    public String greeting(Model model) {
        for (Role au : userService.getUserAuthorities()) {
            if (Role.ADMIN == au) {
                return "admin/home";
            }
        }
        return "user/home";
    }


}
