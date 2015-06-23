package info.desabre;

import info.desabre.fillers.DatabaseLicenceFiller;
import info.desabre.fillers.DatabaseNewsFiller;
import info.desabre.fillers.DatabaseUserFiller;
import info.desabre.fillers.DatabaseWidgetBoxFiller;
import info.desabre.repositories.information.NewsRepository;
import info.desabre.repositories.information.WidgetBoxRepository;
import info.desabre.repositories.job.JobRepository;
import info.desabre.repositories.licence.LicenceRepository;
import info.desabre.repositories.server.ServerRepository;
import info.desabre.repositories.user.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan("info.desabre")
public class DatabaseInitApplication implements CommandLineRunner {

    @Autowired
    private  DatabaseNewsFiller databaseNewsFiller;
    @Autowired
    private  DatabaseUserFiller databaseUserFiller;
    @Autowired
    private  DatabaseWidgetBoxFiller databaseWidgetBoxFiller;
    @Autowired
    private  DatabaseLicenceFiller databaseLicenceFiller;
    @Autowired
    UserRepository usersRepository;
    @Autowired
    WidgetBoxRepository widgetBoxRepository;
    @Autowired
    NewsRepository newsRepository;
    @Autowired
    LicenceRepository licenceRepository;
    @Autowired
    JobRepository jobRepository;
    @Autowired
    ServerRepository serverRepository;

    
    public static void main(String[] args) {
        SpringApplication.run(DatabaseInitApplication.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {


        System.out.println("Encore du travail ?");
        databaseUserFiller.userMockData();
        databaseNewsFiller.newsMockData();
        databaseWidgetBoxFiller.widgetBoxMockData();
        databaseLicenceFiller.licenceMockData();
        System.out.println("My work here is done");


    }

   
    

}
