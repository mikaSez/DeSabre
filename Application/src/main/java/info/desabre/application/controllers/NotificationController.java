package info.desabre.application.controllers;

import info.desabre.application.views.grid.NotificationGridView;
import info.desabre.database.models.notification.Notification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Naiirod on 07/06/2015.
 * Controleur responsable de la gestion des notifications.
 */
@Controller
@RequestMapping("/notification/**")
public class NotificationController {
	private static final Logger log = Logger.getLogger(NotificationController.class.getName());

    @RequestMapping("list")
    public String list(Model model) {

        return "notification/notificationList";
    }


    @RequestMapping("data")
    public
    @ResponseBody
    List<NotificationGridView> data(Model model) {
        log.info("data requested");
        List<Notification> notifications = new ArrayList();
        notifications.add(new Notification("lzefze", "Une pettite notif"));
        return Collections.unmodifiableList(NotificationGridView.map(notifications));
    }

}
