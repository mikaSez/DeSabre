package info.desabre.repositories.job;

import info.desabre.database.models.job.Job;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by Naiirod on 17/06/2015.
 */
public interface JobRepository extends MongoRepository<Job, String> {
    Job findByName(String name);

    Job findById(String id);

    List<Job> findAll();

}