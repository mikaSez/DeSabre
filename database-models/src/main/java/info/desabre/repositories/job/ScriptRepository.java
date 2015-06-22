package info.desabre.repositories.job;

import info.desabre.database.models.job.Script;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by Naiirod on 22/06/2015.
 */
public interface ScriptRepository extends MongoRepository<Script, String> {
    Script findByName(String name);

    List<Script> findAll();
}
