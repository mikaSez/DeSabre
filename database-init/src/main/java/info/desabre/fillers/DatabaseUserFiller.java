package info.desabre.fillers;

import info.desabre.UserConstants;
import info.desabre.database.models.user.User;
import info.desabre.repositories.user.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DatabaseUserFiller {
    @Autowired
    UserRepository usersRepository;

    public DatabaseUserFiller() {
    }

    public void userMockData() {
        System.out.println("Ajout d'utilisateurs :)");

        usersRepository.deleteAll();

        List<User> users = new ArrayList();
        users.add(new User("User", "WithLastName", "$2a$10$vk0FcVaLvmTyQ4YrkVt.S.utmqkk9t8jJpgsHv0w2hRq.gdFAg/3i", false, false, true, 100, "user@desabre.info"));
        users.add(new User("Admin", "WithLastName", "$2a$10$pQHctBMeOxseJCt3TVWLEuzfVD3yX5KFYfHRmH8a3wIQuo0dYxzwG", true, false, true, UserConstants.ADMIN_GROUPEID.getGroupeId(), "admin@desabre.info"));
        System.out.println(users.size() + " utilisateurs créés ");
        users.forEach(s -> System.out.println("leurs mails sont : " + s.getMail()));
        usersRepository.save(users);

    }
}