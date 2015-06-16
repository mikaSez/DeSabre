package info.desabre.database.models.user;

import org.springframework.data.annotation.Id;

import java.util.List;

/**
 * Created by MikaSez on 02/06/2015.
 */
public class User {


    @Id
    private String id;
    private String firstName;
    private String lastName;

    private String login;
    private String password;
    private String mail;
    private Boolean admin;
    private Boolean deleted;
    private Boolean validated;
    private Integer groupeId;

    public User(String firstName, String lastName, String password, String login, String mail, Boolean admin, Integer groupeId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.login = login;
        this.mail = mail;
        this.admin = admin;
        this.groupeId = groupeId;
    }

    private List<UserPreferences> preferences;

    public User(String firstName, String lastName, String password, Boolean admin, Boolean deleted, Boolean validated, Integer groupeId, String mail) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.admin = admin;
        this.deleted = deleted;
        this.validated = validated;
        this.groupeId = groupeId;
        this.mail = mail;
    }

    public User(String mail, String lastName, String firstName) {
        this.mail = mail;
        this.lastName = lastName;
        this.firstName = firstName;
        this.setValidated(true);
        this.setAdmin(false);
        this.setDeleted(true);
        //TODO maybe delete the groupe id ?
        // Doesn't make sense to keep it. We don't have or ever have a distributed architecture. And id is pretty much unique.
        this.groupeId = 100;

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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getValidated() {
        return validated;
    }

    public void setValidated(Boolean validated) {
        this.validated = validated;
    }

    public List<UserPreferences> getPreferences() {
        return preferences;
    }

    public void setPreferences(List<UserPreferences> preferences) {
        this.preferences = preferences;
    }

    public User() {
    }

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

}
