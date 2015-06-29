package info.desabre.repositories.licence;

import info.desabre.database.models.server.Licence;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by Naiirod on 17/06/2015.
 */
public interface LicenceRepository  extends MongoRepository<Licence, String> {
    Licence findById(String id);

    List<Licence> findAll();

    Licence findByName(String licence);
}
