package info.desabre;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DistantServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DistantServerApplication.class, args);
    }
}
