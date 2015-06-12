package info.desabre;

import info.desabre.database.models.information.News;
import info.desabre.database.models.information.WidgetBox;
import info.desabre.database.models.user.User;
import info.desabre.fillers.DatabaseNewsFiller;
import info.desabre.fillers.DatabaseUserFiller;
import info.desabre.fillers.DatabaseWidgetBoxFiller;
import info.desabre.repositories.information.NewsRepository;
import info.desabre.repositories.information.WidgetBoxRepository;
import info.desabre.repositories.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


@SpringBootApplication
@ComponentScan("info.desabre.repositories")
public class DatabaseInitApplication implements CommandLineRunner {


    static PasswordEncoder encoder = new BCryptPasswordEncoder();
    private final DatabaseNewsFiller databaseNewsFiller = new DatabaseNewsFiller();
    private final DatabaseUserFiller databaseUserFiller = new DatabaseUserFiller();
    private final DatabaseWidgetBoxFiller databaseWidgetBoxFiller = new DatabaseWidgetBoxFiller();
    @Autowired
    UserRepository usersRepository;
    @Autowired
    WidgetBoxRepository widgetBoxRepository;
    @Autowired
    NewsRepository newsRepository;

    public static void main(String[] args) {
        SpringApplication.run(DatabaseInitApplication.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {


        System.out.println("Encore du travail ?");
        userMockData();
        newsMockData();
        widgetBoxMockData();
        System.out.println("My work here is done");


    }

    public void userMockData() {
        System.out.println("Ajout d'utilisateurs :)");

        usersRepository.deleteAll();

        List<User> users = new ArrayList();
        users.add(new User("User", "WithLastName", encoder.encode("user"), "user", "user@desabre.info", false, 100));
        users.add(new User("Admin", "WithLastName", encoder.encode("admin"), "admin", "admin@desabre.info", true, UserConstants.ADMIN_GROUPEID.getGroupeId()));
        System.out.println(users.size() + " utilisateurs créés ");
        users.forEach(s -> System.out.println("leurs mails sont : " + s.getMail()));
        usersRepository.save(users);

    }

    public void widgetBoxMockData() {
        System.out.println("Création des données des widgetbox");
        List<WidgetBox> widgets = new ArrayList();
        widgetBoxRepository.deleteAll();

        initAdminWidget(widgets);


        initGlobalWidgetBox(widgets);

        System.out.println("Les données sont mis dans la widgetbox");
        widgetBoxRepository.save(widgets);
    }

    void initGlobalWidgetBox(List<WidgetBox> widgets) {
        widgets.add(new WidgetBox("primary", "bell", 5, "#", "Notification(s).", UserConstants.GLOBAL_GROUPEID.getGroupeId()));
        widgets.add(new WidgetBox("red", "database", 3, "#", "Job(s) en cours.", UserConstants.GLOBAL_GROUPEID.getGroupeId()));
        widgets.add(new WidgetBox("green", "tasks", 124, "#", "Messages.", UserConstants.GLOBAL_GROUPEID.getGroupeId()));
    }

    void initAdminWidget(List<WidgetBox> widgets) {
        widgets.add(new WidgetBox("primary", "bell", 5, "/notification/list", "Notification(s).", UserConstants.ADMIN_GROUPEID.getGroupeId()));
        widgets.add(new WidgetBox("red", "database", 3, "#", "Job(s) en cours.", UserConstants.ADMIN_GROUPEID.getGroupeId()));
        widgets.add(new WidgetBox("green", "tasks", 124, "#", "Messages.", UserConstants.ADMIN_GROUPEID.getGroupeId()));
        widgets.add(new WidgetBox("primary", "users", 4000, "#", "Utilisateurs.", UserConstants.ADMIN_GROUPEID.getGroupeId()));
        widgets.add(new WidgetBox("red", "database", 3, "#", "Serveurs.", UserConstants.ADMIN_GROUPEID.getGroupeId()));
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
