package info.desabre.application.controllers;

import info.desabre.application.views.UserGridView;
import info.desabre.database.models.user.User;
import info.desabre.repositories.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by MikaSez on 11/06/2015.
 * <p>
 * Cette classe s'occupe de la gestion des utilisateurs
 */

@Controller
public class UserController {


    @Autowired
    private UserRepository repository;

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


    @RequestMapping("users/all")
    public
    @ResponseBody
    List<UserGridView> allUsers() {

        List<User> users = repository.findAll();
        if (users == null) {
            users = new ArrayList();
        }
        return Collections.unmodifiableList(UserGridView.map(users));
    }

    private boolean validateEmail() {
        return false;
    }


}
