package info.desabre.application.controllers;

import info.desabre.application.services.MailService;
import info.desabre.application.services.UserService;
import info.desabre.application.views.UserInscriptionView;
import info.desabre.application.views.forms.UserAdminProfilForm;
import info.desabre.application.views.forms.views.UserAdminProfilView;
import info.desabre.application.views.forms.views.UserProfilView;
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

import javax.mail.MessagingException;
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


    @Autowired
    private MailService smtpMailSender;

    UserAdminProfilForm form;

    @Autowired
    private UserService service;

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
            try {
                sendInscriptionMail(user.getMail());
            } catch (MessagingException e) {
                log.severe(e.getMessage());
            }
            repository.save(user.mapToUser());
            return "login";
        }
        return "user/inscription";


    }

    private boolean validateMail(String mail) {
        return repository.findByMail(mail) == null;
    }


    @RequestMapping(value = {"/user/profil", "/profil"}, method = RequestMethod.GET)
    public String userProfil(Model model) {
        User user = service.getUser();
        Form<UserProfilView> f = FormProcessor.getInstance().processView(UserProfilView.fromUser(user));
        model.addAttribute("form", f);
        return "user/profil";
    }

    @RequestMapping(value = {"/user/profil", "/profil"}, method = RequestMethod.POST)
    public String userProfilChanged(@RequestParam Map<String, String> view, Model model) {
        User user = service.getUser();
        Form<UserProfilView> f = FormProcessor.getInstance().processView(new UserProfilView());
        UserProfilView vv = f.mapToObject(view, UserProfilView.fromUser(user));
        repository.save(vv.mergeIntoUser(user));
        model.addAttribute("form", FormProcessor.getInstance().processView(vv));
        return "user/profil";
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
        User user = repository.findByMail(mail);
        Form<UserAdminProfilView> f = FormProcessor.getInstance().processView(UserAdminProfilView.fromUser(user));

        model.addAttribute("form", f);
        return "user/profil";
    }

    @RequestMapping(value = "/admin/users/detail/update", method = RequestMethod.POST)
    public String userUpdate(@RequestParam Map<String, String> view, Model model) {
        User u = repository.findByMail(view.get("mail"));
        boolean isValidated = u.getValidated();
        form = new UserAdminProfilForm("userProfil");


        User temp = form.mapToObject(view, u);

        if (temp.getValidated() && !isValidated) {
            try {
                sendValidationMail(u.getMail());
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }
        repository.save(temp);
        return "admin/userList";
    }

    private void sendValidationMail(String to) throws MessagingException {
        smtpMailSender.send(to, "Inscription à Desabre.info", "<h1> Amazing ! </h1> " +
                "<p> Votre inscription à notre service est désormais terminée.</p>" +
                "<p> Vous pouvez dés maintenant utiliser notre service avec les données que vous avez utilisé à l'inscription. </p>");
    }

    public void sendInscriptionMail(String to) throws MessagingException {
        smtpMailSender.send(to, "Inscription à Desabre.info", "<h1> Amazing ! </h1> " +
                "<p> Première étape de l'inscription à desabre.info a été accomplie...</p>" +
                "<p> Vous recevrez un mail confirmant votre inscription, une fois que l'administrateur aura validé votre compte </p>");
    }
}
