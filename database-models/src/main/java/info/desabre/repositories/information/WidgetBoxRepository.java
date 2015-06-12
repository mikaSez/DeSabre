package info.desabre.repositories.information;

import info.desabre.database.models.information.WidgetBox;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by MikaSez on 11/06/2015.
 */
public interface WidgetBoxRepository extends MongoRepository<WidgetBox, String> {

    List<WidgetBox> findByGroupeId(int groupeId);
}
