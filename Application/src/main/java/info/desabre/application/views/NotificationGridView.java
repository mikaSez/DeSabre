package info.desabre.application.views;

import info.desabre.database.models.notification.Notification;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Naiirod on 08/06/2015.
 * This class is used to map data from a server object to a view object witch will be transmitted to the view and shown
 * Security / And bandwidth purposes
 */
public class NotificationGridView {
    private String name;

    public NotificationGridView(String name) {
        this.name = name;
    }


    public static NotificationGridView map(Notification notification) {
        return new NotificationGridView(notification.getName());
    }

    public static List<NotificationGridView> map(List<Notification> notifications) {
        return notifications.stream().map(notification -> map(notification)).collect(Collectors.toList());
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
