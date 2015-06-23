package info.desabre.application.controllers;

import info.desabre.UserConstants;
import info.desabre.application.services.UserService;
import info.desabre.database.models.information.WidgetBox;
import info.desabre.repositories.information.NewsRepository;
import info.desabre.repositories.information.WidgetBoxRepository;
import info.desabre.repositories.job.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by MikaSez on 04/06/2015.
 */
@Controller
public class WidgetBoxController {
    Logger log = Logger.getLogger(NewsController.class.getName());

    @Autowired
    private UserService user;
    @Autowired
    private NewsRepository newsRepository;
    @Autowired
    private NewsRepository userRepository;
    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private WidgetBoxRepository repository;


    @RequestMapping("/widgetBox")
    public
    @ResponseBody
    List<WidgetBox> allBox() {

        log.info("Widget box information is updating");
        List<WidgetBox> widgets = null;
        if (user.isAdmin()) {
            widgets = repository.findByGroupeId(UserConstants.ADMIN_GROUPEID.getGroupeId());
        } else {
            widgets = repository.findByGroupeId(user.getUser().getGroupeId());
            if (widgets == null || widgets.isEmpty()) {
                widgets = repository.findByGroupeId(UserConstants.GLOBAL_GROUPEID.getGroupeId());
            }
        }
        return Collections.unmodifiableList(widgets);
    }


    private long getServerCount() {
        return 0l;
    }

    private long getUserCount() {
        return userRepository.count();
    }

    private long getMessageCount() {
        return newsRepository.count();
    }


}
