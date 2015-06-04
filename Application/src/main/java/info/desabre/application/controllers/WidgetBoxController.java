package info.desabre.application.controllers;

import info.desabre.application.services.UserService;
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
        List<WidgetBox> widgets = new ArrayList<>();
        if (user.isAdmin()) {

            widgets.add(new WidgetBox(1, "primary", "bell", 5, "#", "Notification(s)."));
            widgets.add(new WidgetBox(2, "red", "database", 3, "#", "Job(s) en cours."));
            widgets.add(new WidgetBox(3, "green", "tasks", 124, "#", "Messages."));
            widgets.add(new WidgetBox(4, "primary", "users", 4000, "#", "Utilisateurs."));
            widgets.add(new WidgetBox(5, "red", "database", 3, "#", "Serveurs."));

        } else {

            widgets.add(new WidgetBox(1, "primary", "bell", 5, "#", "Notification(s)."));
            widgets.add(new WidgetBox(2, "red", "database", 3, "#", "Job(s) en cours."));
            widgets.add(new WidgetBox(3, "green", "tasks", 124, "#", "Messages."));
            widgets.add(new WidgetBox(4, "blue", "users", 3, "#", "Totos."));
        }

        return Collections.unmodifiableList(widgets);
    }


    private class WidgetBox {
        private int id;
        private String color;
        private String icon;
        private int number;
        private String path;
        private String text;

        public WidgetBox(int id, String color, String icon, int number, String path, String text) {
            this.id = id;
            this.color = color;
            this.icon = icon;
            this.number = number;
            this.path = path;
            this.text = text;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }
}
