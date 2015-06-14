package info.desabre.application.views;

import info.desabre.database.models.user.User;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by MikaSez on 14/06/2015.
 */
public class UserGridView {


    private String firstName;
    private String lastName;
    private String mail;
    private Boolean admin;
    private Boolean deleted;
    private Integer groupeId;


    public static UserGridView map(User user) {
        return new UserGridView(user.getFirstName(), user.getLastName(), user.getMail(), user.getAdmin(), user.getDeleted(), user.getGroupeId());
    }

    public static List<UserGridView> map(List<User> users) {
        return users.stream().map(user -> map(user)).collect(Collectors.toList());
    }


    public UserGridView(String firstName, String lastName, String mail, Boolean admin, Boolean deleted, Integer groupeId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.mail = mail;
        this.admin = admin;
        this.deleted = deleted;
        this.groupeId = groupeId;
    }


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

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Integer getGroupeId() {
        return groupeId;
    }

    public void setGroupeId(Integer groupeId) {
        this.groupeId = groupeId;
    }
}
