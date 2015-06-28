package info.desabre.repositories.job;

import info.desabre.database.models.job.Script;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by Naiirod on 22/06/2015.
 */
public interface ScriptRepository extends MongoRepository<Script, String> {
    Script findById(String id);

    List<Script> findAll();
}
