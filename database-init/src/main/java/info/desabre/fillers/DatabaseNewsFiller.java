package info.desabre.fillers;

import info.desabre.UserConstants;
import info.desabre.database.models.information.News;
import info.desabre.repositories.information.NewsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
@Service
public class DatabaseNewsFiller {
    @Autowired
    NewsRepository newsRepository;

    public DatabaseNewsFiller() {
    }

    public void newsMockData() {
        System.out.println("Saving news");
        newsRepository.deleteAll();
        initNewsForUser();
        System.out.println("User news saved");
        initNewsForAdministration();
        System.out.println("Admin news saved");
    }

    void initNewsForAdministration() {
        List<News> news = new ArrayList();

        news.add(new News("Job en erreur", new Timestamp(new Calendar.Builder().setDate(2015, 10, 20).build().getTimeInMillis()), "tasks", "#", UserConstants.ADMIN_GROUPEID.getGroupeId()));
        news.add(new News("Serveur redemarre", new Timestamp(new Calendar.Builder().setDate(2015, 10, 20).build().getTimeInMillis()), "database", "#", UserConstants.ADMIN_GROUPEID.getGroupeId()));
        news.add(new News("Tentative de réparation", new Timestamp(new Calendar.Builder().setDate(2015, 10, 20).build().getTimeInMillis()), "twitter", "#", UserConstants.ADMIN_GROUPEID.getGroupeId()));
        news.add(new News("Raté...", new Timestamp(new Calendar.Builder().setDate(2015, 10, 20).build().getTimeInMillis()), "facebook", "#", UserConstants.ADMIN_GROUPEID.getGroupeId()));
        news.add(new News("Tu es viré", new Timestamp(new Calendar.Builder().setDate(2015, 10, 20).build().getTimeInMillis()), "facebook", "#", UserConstants.ADMIN_GROUPEID.getGroupeId()));
        newsRepository.save(news);
    }

    void initNewsForUser() {
        List<News> news = new ArrayList();
        news.add(new News("Job en erreur", new Timestamp(new Calendar.Builder().setDate(2015, 10, 20).build().getTimeInMillis()), "tasks", "#", 100));
        news.add(new News("Serveur redemarre", new Timestamp(new Calendar.Builder().setDate(2015, 10, 20).build().getTimeInMillis()), "database", "#", 100));
        news.add(new News("Le monde a été informé", new Timestamp(new Calendar.Builder().setDate(2015, 10, 20).build().getTimeInMillis()), "twitter", "#", 100));
        news.add(new News("Le statut a été posté", new Timestamp(new Calendar.Builder().setDate(2015, 10, 20).build().getTimeInMillis()), "facebook", "#", 100));
        news.add(new News("La vie sociale a été foutue", new Timestamp(new Calendar.Builder().setDate(2015, 10, 20).build().getTimeInMillis()), "facebook", "#", 100));
        newsRepository.save(news);


    }
}