package info.desabre.application.views;

import info.desabre.application.security.PasswordEncrypt;
import info.desabre.database.models.user.User;
import org.hibernate.validator.constraints.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by MikaSez on 14/06/2015.
 */
public class UserInscriptionView {


    @Autowired
    private PasswordEncrypt userService;
    //FIXME hybernate seems to not mix with javax
    //replace with custom annotation ?
    @NotNull(message = "Le mail doit être obligatoire")
    @Email(message = "Le mail doit être valide")
    private String mail;

    /**
     * It's very bad assuming things about last/first names
     */
    @NotNull(message = "Un nom est obligatoire")
    @Size(min = 2, message = "Le nom de famille doit comporter au moins deux caractères")
    private String lastName;
    @NotNull(message = "Un prénom est obligatoire")
    @Size(min = 2, message = "Le prenom doit comporter au moins deux caractères")
    private String firstName;

    private String password;


    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    public User mapToUser() {
        User user =  new User(mail, lastName, firstName);
        user.setPassword(password);
        return user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserInscriptionView{" +
                "mail='" + mail + '\'' +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                '}';
    }
}

