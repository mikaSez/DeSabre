package info.desabre.repositories.user;

import info.desabre.database.models.user.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by MikaSez on 11/06/2015.
 */
public interface UserRepository extends MongoRepository<User, String> {
    User findByMail(String mail);

    List<User> findAll();

}