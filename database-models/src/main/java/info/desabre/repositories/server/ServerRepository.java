package info.desabre.repositories.server;

import info.desabre.database.models.server.Server;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ServerRepository extends MongoRepository<Server, String>{
	Server findByName(String name);

	List<Server> findAll();

	Server findByAddress(String address);

	Server findById(String id);
}
