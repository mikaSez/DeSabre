package info.desabre.application.controllers;

import info.desabre.application.views.UserInscriptionView;
import info.desabre.application.views.forms.UserAdminProfilForm;
import info.desabre.application.views.forms.views.UserAdminProfilView;
import info.desabre.application.views.generator.FormProcessor;
import info.desabre.application.views.grid.UserGridView;
import info.desabre.application.views.inputs.form.Form;
import info.desabre.database.models.user.User;
import info.desabre.repositories.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by MikaSez on 11/06/2015.
 * <p>
 * Cette classe s'occupe de la gestion des utilisateurs
 */

@Controller
public class UserController {

    private final static Logger log = Logger.getLogger(UserController.class.getName());
    @Autowired
    private UserRepository repository;
    UserAdminProfilForm form;

    @RequestMapping("/inscription")
    public String inscription(@ModelAttribute UserInscriptionView user, Model model) {
        model.addAttribute("user", user);
        return "user/inscription";
    }


    @RequestMapping("/inscription/validate")
    public String validateInscriptionData(@ModelAttribute("user") @Valid UserInscriptionView user, BindingResult bindingResult, Model model) {

        model.addAttribute("user", user);
        if (!validateMail(user.getMail())) {
            //FIXME plus simple que de créer un objecterror ??
            model.addAttribute("errorMail", true);
            bindingResult.addError(new ObjectError("user", "le mail n'est pas valide"));
        }
        if (!bindingResult.hasErrors()) {
            model.addAttribute("inscribed", true);
            repository.save(user.mapToUser());
            return "login";
        }
        return "user/inscription";


    }

    private boolean validateMail(String mail) {
        return repository.findByMail(mail) == null;
    }


    @RequestMapping("users/all")
    public
    @ResponseBody
    List<UserGridView> allUsers() {

        List<User> users = repository.findAll();
        return Collections.unmodifiableList(UserGridView.map(users));
    }


    @RequestMapping("/admin/users/detail/user")
    public String userDetails(@RequestParam("mail") String mail, Model model) {
        System.err.println(mail);
        User user = repository.findByMail(mail);


        //form = new UserAdminProfilForm("userProfil");
        //form.parseUser(user);
        //form.setPath("/admin/users/detail/update");
        Form<UserAdminProfilView> f = FormProcessor.getInstance().processView(UserAdminProfilView.fromUser(user));


        model.addAttribute("form", f);
        return "user/profil";
    }

    @RequestMapping(value = "/admin/users/detail/update", method = RequestMethod.POST)
    public String userUpdate(@RequestParam Map<String, String> view, Model model) {
        User u = repository.findByMail(view.get("mail"));
        form = new UserAdminProfilForm("userProfil");

        //Form<UserAdminProfilView> f =  FormProcessor.getInstance().processView(UserAdminProfilView.fromUser(u));

        repository.save(form.mapToObject(view, u));
        return "admin/userList";
    }
}
