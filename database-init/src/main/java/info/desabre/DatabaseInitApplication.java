package info.desabre;

import info.desabre.database.information.NewsRepository;
import info.desabre.database.models.information.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


@SpringBootApplication
@ComponentScan("info.desabre.repositories")
public class DatabaseInitApplication implements CommandLineRunner {


    @Autowired
    private NewsRepository newsRepository;

    public static void main(String[] args) {
        SpringApplication.run(DatabaseInitApplication.class, args);
    }


    @Override
    public void run(String... strings) throws Exception {

        for (News customer : newsRepository.findAll()) {
            System.out.println(customer.getTitle());
        }

        System.out.println("Encore du travail ?");

        System.out.println("Saving news");
        newsRepository.deleteAll();
        initNewsForUser();
        System.out.println("User news saved");
        initNewsForAdministration();
        System.out.println("Admin news saved");
        System.out.println("My work here is done");


    }

    private void initNewsForAdministration() {
        List<News> news = new ArrayList();

        news.add(new News("Job en erreur", new Timestamp(new Calendar.Builder().setDate(2015, 10, 20).build().getTimeInMillis()), "tasks", "#", UserConstants.ADMIN_GROUPEID.getGroupeId()));
        news.add(new News("Serveur redemarre", new Timestamp(new Calendar.Builder().setDate(2015, 10, 20).build().getTimeInMillis()), "database", "#", UserConstants.ADMIN_GROUPEID.getGroupeId()));
        news.add(new News("Tentative de réparation", new Timestamp(new Calendar.Builder().setDate(2015, 10, 20).build().getTimeInMillis()), "twitter", "#", UserConstants.ADMIN_GROUPEID.getGroupeId()));
        news.add(new News("Raté...", new Timestamp(new Calendar.Builder().setDate(2015, 10, 20).build().getTimeInMillis()), "facebook", "#", UserConstants.ADMIN_GROUPEID.getGroupeId()));
        news.add(new News("Tu es viré", new Timestamp(new Calendar.Builder().setDate(2015, 10, 20).build().getTimeInMillis()), "facebook", "#", UserConstants.ADMIN_GROUPEID.getGroupeId()));
        newsRepository.save(news);
    }

    private void initNewsForUser() {
        List<News> news = new ArrayList();
        news.add(new News("Job en erreur", new Timestamp(new Calendar.Builder().setDate(2015, 10, 20).build().getTimeInMillis()), "tasks", "#", 100));
        news.add(new News("Serveur redemarre", new Timestamp(new Calendar.Builder().setDate(2015, 10, 20).build().getTimeInMillis()), "database", "#", 100));
        news.add(new News("Le monde a été informé", new Timestamp(new Calendar.Builder().setDate(2015, 10, 20).build().getTimeInMillis()), "twitter", "#", 100));
        news.add(new News("Le statut a été posté", new Timestamp(new Calendar.Builder().setDate(2015, 10, 20).build().getTimeInMillis()), "facebook", "#", 100));
        news.add(new News("La vie sociale a été foutue", new Timestamp(new Calendar.Builder().setDate(2015, 10, 20).build().getTimeInMillis()), "facebook", "#", 100));
        newsRepository.save(news);


    }


}
