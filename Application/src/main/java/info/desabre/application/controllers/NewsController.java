package info.desabre.application.controllers;

import info.desabre.application.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by MikaSez on 04/06/2015.
 */

@Controller
public class NewsController {

    Logger log = Logger.getLogger(NewsController.class.getName());

    @Autowired
    private UserService user;

    @RequestMapping("/news")
    public
    @ResponseBody
    List<News> getNews() {
        log.info("news requested");
        List<News> news = new ArrayList<>();


        if (!user.isAdmin()) {
            news.add(new News(1, "Job en erreur", new Timestamp(new Calendar.Builder().setDate(2015, 10, 20).build().getTimeInMillis()), "tasks", "#"));
            news.add(new News(2, "Serveur redemarre", new Timestamp(new Calendar.Builder().setDate(2015, 10, 20).build().getTimeInMillis()), "database", "#"));
            news.add(new News(3, "Le monde a été informé", new Timestamp(new Calendar.Builder().setDate(2015, 10, 20).build().getTimeInMillis()), "twitter", "#"));
            news.add(new News(4, "Le statut a été posté", new Timestamp(new Calendar.Builder().setDate(2015, 10, 20).build().getTimeInMillis()), "facebook", "#"));
            news.add(new News(5, "La vie sociale a été foutue", new Timestamp(new Calendar.Builder().setDate(2015, 10, 20).build().getTimeInMillis()), "facebook", "#"));

        } else {
            news.add(new News(1, "Job en erreur", new Timestamp(new Calendar.Builder().setDate(2015, 10, 20).build().getTimeInMillis()), "tasks", "#"));
            news.add(new News(2, "Serveur redemarre", new Timestamp(new Calendar.Builder().setDate(2015, 10, 20).build().getTimeInMillis()), "database", "#"));
            news.add(new News(3, "Tentative de réparation", new Timestamp(new Calendar.Builder().setDate(2015, 10, 20).build().getTimeInMillis()), "twitter", "#"));
            news.add(new News(4, "Raté...", new Timestamp(new Calendar.Builder().setDate(2015, 10, 20).build().getTimeInMillis()), "facebook", "#"));
            news.add(new News(5, "Tu es viré", new Timestamp(new Calendar.Builder().setDate(2015, 10, 20).build().getTimeInMillis()), "facebook", "#"));

        }

        return Collections.unmodifiableList(news);

    }


    private class News {
        private int id;
        private String title;
        private Timestamp time;
        private String icon;
        private String path;

        public News(int id, String title, Timestamp time, String icon, String path) {
            this.id = id;
            this.title = title;
            this.time = time;
            this.icon = icon;
            this.path = path;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public Timestamp getTime() {
            return time;
        }

        public void setTime(Timestamp time) {
            this.time = time;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }
    }
}
