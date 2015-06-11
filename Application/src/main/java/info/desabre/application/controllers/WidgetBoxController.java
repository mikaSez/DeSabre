package info.desabre.application.controllers;

import info.desabre.application.services.UserService;
import info.desabre.database.models.information.WidgetBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by MikaSez on 04/06/2015.
 */
@Controller
public class WidgetBoxController {
    Logger log = Logger.getLogger(NewsController.class.getName());

    @Autowired
    private UserService user;


    @RequestMapping("/widgetBox")
    public
    @ResponseBody
    List<WidgetBox> allBox() {

        log.info("Widget box information is updating");
        List<WidgetBox> widgets = new ArrayList<>();
        if (user.isAdmin()) {

            widgets.add(new WidgetBox(1, "primary", "bell", 5, "/notification/list", "Notification(s)."));
            widgets.add(new WidgetBox(2, "red", "database", 3, "#", "Job(s) en cours."));
            widgets.add(new WidgetBox(3, "green", "tasks", 124, "#", "Messages."));
            widgets.add(new WidgetBox(4, "primary", "users", 4000, "#", "Utilisateurs."));
            widgets.add(new WidgetBox(5, "red", "database", 3, "#", "Serveurs."));

        } else {

            widgets.add(new WidgetBox(1, "primary", "bell", 5, "#", "Notification(s)."));
            widgets.add(new WidgetBox(2, "red", "database", 3, "#", "Job(s) en cours."));
            widgets.add(new WidgetBox(3, "green", "tasks", 124, "#", "Messages."));
        }

        return Collections.unmodifiableList(widgets);
    }



}
