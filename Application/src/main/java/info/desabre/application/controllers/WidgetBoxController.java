package info.desabre.application.controllers;

import info.desabre.UserConstants;
import info.desabre.application.services.UserService;
import info.desabre.database.models.information.WidgetBox;
import info.desabre.repositories.information.NewsRepository;
import info.desabre.repositories.information.WidgetBoxRepository;
import info.desabre.repositories.job.JobRepository;
import info.desabre.repositories.server.ServerRepository;
import info.desabre.repositories.user.UserRepository;
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
    private UserRepository userRepository;
    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private ServerRepository serverRepository;

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

        widgets.forEach(s -> {
            switch (s.getType()) {
                case USER:
                    s.setNumber((int) getUserCount());
                    break;

                case NEWS:
                    s.setNumber(getMessageCount());
                    break;
                case SERVER:
                    s.setNumber((int) getServerCount());
                    break;
                case JOB:
                    s.setNumber((int) getJobCount());
                    break;
                case SCRIPT:
                    s.setNumber(user.getUser().getScripts().size());
            }
        });

        return Collections.unmodifiableList(widgets);
    }


    private long getServerCount() {
        return serverRepository.count();
    }

    private long getUserCount() {

        return userRepository.count();
    }

    private int getMessageCount() {
        return newsRepository.findByGroupId(user.getUser().getGroupeId()).size();
    }


    public long getJobCount() {
        return jobRepository.count();
    }


}
