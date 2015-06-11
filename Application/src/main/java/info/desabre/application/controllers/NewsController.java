package info.desabre.application.controllers;

import info.desabre.UserConstants;
import info.desabre.application.services.UserService;
import info.desabre.database.models.information.News;
import info.desabre.repositories.information.NewsRepository;
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
public class NewsController {

    Logger log = Logger.getLogger(NewsController.class.getName());

    @Autowired
    private UserService user;

    @Autowired
    private NewsRepository repository;

    @RequestMapping("/news")
    public
    @ResponseBody
    List<News> getNews() {
        log.info("news requested");
        for (News customer : repository.findAll()) {
            System.out.println(customer.getTitle());
        }
        if (!user.isAdmin()) {
            return Collections.unmodifiableList(repository.findByGroupId(100));
        } else {
            return Collections.unmodifiableList(repository.findByGroupId(UserConstants.ADMIN_GROUPEID.getGroupeId()));
        }

    }


}
