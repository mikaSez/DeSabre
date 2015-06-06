package info.desabre;

import info.desabre.database.information.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DatabaseModelsApplication implements CommandLineRunner {
    @Autowired
    private NewsRepository news;

    public static void main(String[] args) {
        SpringApplication.run(DatabaseModelsApplication.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        news.deleteAll();
    }
}
