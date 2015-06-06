package info.desabre;

import info.desabre.database.information.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan("info.desabre.repositories")
public class DatabaseInitApplication implements CommandLineRunner {


    @Autowired
    private NewsRepository news;

    public static void main(String[] args) {
        SpringApplication.run(DatabaseInitApplication.class, args);
    }


    @Override
    public void run(String... strings) throws Exception {
        news.deleteAll();
    }


}
