package info.desabre.application.views.forms.views;

import info.desabre.application.views.generator.annotations.*;
import info.desabre.application.views.generator.annotations.types.CheckBox;
import info.desabre.application.views.generator.annotations.types.Mail;
import info.desabre.database.models.user.User;

/**
 * Created by MikaSez on 21/06/2015.
 * Strictly equivalent to UserAdminProfilForm
 */
@Form(id = "UserAdminProfile", nbColumn = 3, readOnly = true, path = "/admin/users/detail/update")
public class UserAdminProfilView {

    @Field(name = "firstName", column = 1)
    @Placeholder(text = "Entrez un prénom")
    @Label(text = "Prénom de l'utilisateur")
    public String firstName;

    @Field(name = "lastName", column = 2)
    @Placeholder(text = "Entrez un nom")
    @Label(text = "Nom de l'utilisateur")
    public String lastName;

    @Field(name = "login", column = 1)
    @Optional
    @Label(text = "Login de l'utilisateur")
    public String login;

    @Field(name = "mail", column = 2)
    @Placeholder(text = "Entrez un e-mail")
    @Mail
    @Label(text = "Email de l'utilisateur")
    public String mail;

    @Field(name = "admin", column = 3)
    @CheckBox
    @Optional
    @Label(text = "L'utilisateur est admin ? ")
    public Boolean admin;

    @Field(name = "deleted", column = 3)
    @CheckBox
    @Optional
    @Label(text = "L'utilisateur est supprimé ? ")
    public Boolean deleted;

    @Field(name = "validated", column = 3)
    @CheckBox
    @Optional
    @Label(text = "L'utilisateur est validé ? ")
    public Boolean validated;


    public static UserAdminProfilView fromUser(User user) {
        UserAdminProfilView view = new UserAdminProfilView();
        view.firstName = user.getFirstName();
        view.validated = user.getValidated();
        view.deleted = user.getDeleted();
        view.admin = user.getAdmin();
        view.mail = user.getMail();
        view.login = user.getLogin();
        view.lastName = user.getLastName();
        return view;
    }
}
