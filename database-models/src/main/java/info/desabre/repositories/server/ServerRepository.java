package info.desabre.repositories.server;

import info.desabre.database.models.server.Server;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ServerRepository extends MongoRepository<Server, String>{
	Server findByName(String name);

	List<Server> findAll();
}
