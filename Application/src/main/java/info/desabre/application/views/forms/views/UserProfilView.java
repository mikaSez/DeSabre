package info.desabre.application.views.forms.views;

import info.desabre.application.views.generator.annotations.*;
import info.desabre.application.views.generator.annotations.types.Mail;
import info.desabre.application.views.generator.annotations.types.Password;
import info.desabre.application.views.generator.annotations.types.Text;
import info.desabre.database.models.user.User;

/**
 * Created by MikaSez on 21/06/2015.
 */
@Form(id = "UserProfilView", nbColumn = 2, readOnly = true, path = "/profil")
public class UserProfilView {

    @Field(name = "firstName", column = 1)
    @Placeholder(text = "Entrez votre prénom")
    @Label(text = "Votre prénom")
    public String firstName;

    @Field(name = "lastName", column = 2)
    @Placeholder(text = "Entrez votre nom")
    @Label(text = "Votre nom")
    public String lastName;

    @Field(name = "login", column = 1)
    @Label(text = "Votre login")
    @Optional
    @Placeholder(text = "Non obligatoire")
    public String login;

    @Field(name = "mail", column = 2)
    @Label(text = "Votre mail")
    @Mail
    public String mail;

    @Field(name = "admin", column = 2)
    @Text
    @Optional
    @Readonly
    @Label(text = "Etes-vous admin ?")
    public String admin;


    @Field(name = "password", column = 1)
    @Password
    @Optional
    @Label(text = "Votre mot de passe")
    public String password;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return "";
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static UserProfilView fromUser(User user) {
        UserProfilView view = new UserProfilView();
        view.firstName = user.getFirstName();
        view.admin = user.getAdmin().toString();
        view.mail = user.getMail();
        view.login = user.getLogin();
        view.lastName = user.getLastName();
        return view;
    }

    public User mergeIntoUser(User u) {
        u.setFirstName(firstName);
        u.setMail(mail);
        u.setLogin(login);
        u.setLastName(lastName);
        u.setPassword(password);
        password = "";
        return u;
    }
}
