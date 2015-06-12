package info.desabre.repositories.information;

import info.desabre.database.models.information.News;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by MikaSez on 06/06/2015.
 */
public interface NewsRepository extends MongoRepository<News, String> {
    News findById(int id);
    List<News> findByGroupId(int groupeId);

}
