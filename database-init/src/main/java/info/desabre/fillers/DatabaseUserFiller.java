package info.desabre.fillers;

import info.desabre.UserConstants;
import info.desabre.database.models.user.User;
import info.desabre.repositories.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;


public class DatabaseUserFiller {
    @Autowired
    UserRepository usersRepository;
    static PasswordEncoder encoder = new BCryptPasswordEncoder();

    public DatabaseUserFiller() {
    }

    public void userMockData() {
        System.out.println("Ajout d'utilisateurs :)");

        usersRepository.deleteAll();

        List<User> users = new ArrayList();
        users.add(new User("User", "WithLastName", encoder.encode("user"), "user", "user@desabre.info", false, 100));
        users.add(new User("Admin", "WithLastName", encoder.encode("admin"), "admin", "admin@desabre.info", true, UserConstants.ADMIN_GROUPEID.getGroupeId()));
        System.out.println(users.size() + " utilisateurs créés ");
        users.forEach(s -> System.out.println("leurs mails sont : " + s.getMail()));
        usersRepository.save(users);

    }
}