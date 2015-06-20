package info.desabre.application.views.forms;

import info.desabre.application.views.inputs.Input;
import info.desabre.application.views.inputs.form.Form;
import info.desabre.application.views.inputs.impl.CheckBoxField;
import info.desabre.application.views.inputs.impl.MailField;
import info.desabre.application.views.inputs.impl.TextField;
import info.desabre.database.models.user.User;

/**
 * Created by MikaSez on 20/06/2015.
 */
public class UserAdminProfilForm extends Form<User> {


    public UserAdminProfilForm(String id) {
        super(id);
        this.setNbColumn(3);
    }


    public void parseUser(User user) {
        Input in = new TextField("firstName", "Prénom de l'utilisateur", "Entrez un prénom");
        in.setValue(user.getFirstName());
        this.addInput(in, 1);

        in = new TextField("lastName", "Nom de l'utilisateur", "Entrez un nom");
        in.setValue(user.getLastName());
        this.addInput(in, 2);

        in = new TextField("login", "Login de l'utilisateur");
        in.setValue(user.getLogin());
        in.optional();
        this.addInput(in, 1);

        in = new MailField("mail", "Email de 'utilisateur", "Entrez un e-mail");
        in.setValue(user.getMail());
        this.addInput(in, 2);

        in = new CheckBoxField("admin", "L'utilisateur est admin?");
        in.setValue(user.getAdmin().toString());
        in.optional();
        this.addInput(in, 3);

        in = new CheckBoxField("deleted", "L'utilisateur est supprimé?");
        in.setValue(user.getDeleted().toString());
        in.optional();
        this.addInput(in, 3);
        in = new CheckBoxField("validated", "L'utilisateur est validé ?");
        in.setValue(user.getValidated().toString());
        in.optional();
        this.addInput(in, 3);
    }


}
