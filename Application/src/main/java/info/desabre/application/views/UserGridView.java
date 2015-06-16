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
    private String status;


    public static UserGridView map(User user) {
    	
    	UserGridView view =new UserGridView(user.getFirstName(), user.getLastName(), user.getMail());
    	
    	if(user.getDeleted() && user.getValidated()){
    		view.status = "deleted";
    	} else if(user.getDeleted() && !user.getValidated()) {
    		view.status = "rejected";
    	} else if(!user.getValidated()){
    		view.status = "validation";
    	} else if(user.getAdmin()){
    		view.status = "admin";
    	} else {
    		view.status = "user";
    	}
    	
        return view;
    }

    public static List<UserGridView> map(List<User> users) {
        return users.stream().map(user -> map(user)).collect(Collectors.toList());
    }


    public UserGridView(String firstName, String lastName, String mail) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.mail = mail;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

   
}
