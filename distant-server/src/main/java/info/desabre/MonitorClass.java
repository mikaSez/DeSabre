package info.desabre;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by MikaSez on 01/07/2015.
 */
@Component
public class MonitorClass {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    private static final String waitingFolder = "./Application/distant/waiting";
    private static final String tratementFolder = "./Application/distant/running/";
    private static final String finalFolder = "./Application/distant/end/";
    private static final String errorFolder = "./Application/distant/error/";
    public static final Map<String, STATUS> status = new HashMap<>();

    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() throws IOException {
        System.out.println("The time is now " + dateFormat.format(new Date()));
        List<Path> files = Files.list(Paths.get(waitingFolder)).collect(Collectors.toList());
        if (!files.isEmpty()) {
            System.out.println("Found files waiting for treatement");
            files.forEach(p -> {
                new Thread(ServerTreatement(p)).start();
            });
        }
    }

    private Runnable ServerTreatement(Path p) {

        return () -> {
            try {
                String jobId = p.getFileName().toString().substring(3).split("\\.")[0];

                status.put(jobId, STATUS.WAITING);

                Path path = p;
                Files.move(path, Paths.get(tratementFolder + p.getFileName()));
                path = Paths.get(tratementFolder + p.getFileName());
                System.out.println("File : " + p.getFileName() + " moved for treatement");
                status.put(jobId, STATUS.TRAITEMENT);

                Thread.sleep(10000);
                if (new Random().nextBoolean()) {

                    status.put(jobId, STATUS.ERROR);
                    Files.move(path, Paths.get(errorFolder + p.getFileName()));
                    System.out.println("File : " + p.getFileName() + " in error");
                } else {

                    status.put(jobId, STATUS.FINAL);
                    Files.move(path, Paths.get(finalFolder + p.getFileName()));
                    System.out.println("Traitement ended well for file : " + p.getFileName());
                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        };
    }
}
