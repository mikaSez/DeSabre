package info.desabre.application.controllers;

import info.desabre.UserConstants;
import info.desabre.application.services.UserService;
import info.desabre.application.views.grid.NewsGridView;
import info.desabre.database.models.information.News;
import info.desabre.repositories.information.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Comparator;
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
        List<News> news;
        if (!user.isAdmin()) {
            news = repository.findByGroupId(100);
        } else {
            news = repository.findByGroupId(UserConstants.ADMIN_GROUPEID.getGroupeId());
        }
        news.addAll(repository.findByGroupId(UserConstants.GLOBAL_GROUPEID.getGroupeId()));
        news.sort(Comparator.comparing(News::getTime));
        //will be really bad on large chunk of data, limit the query not the output
        return news.subList(0, 5);

    }

    @RequestMapping("/news/data")
    public
    @ResponseBody
    List<NewsGridView> data() {
        log.info("data requested");
        List<News> news;
        if (!user.isAdmin()) {
            news = repository.findByGroupId(100);
        } else {
            news = repository.findByGroupId(UserConstants.ADMIN_GROUPEID.getGroupeId());
        }
        news.addAll(repository.findByGroupId(UserConstants.GLOBAL_GROUPEID.getGroupeId()));
        news.sort(Comparator.comparing(News::getTime));

        return NewsGridView.map(news);
    }


    @RequestMapping("/news/send")
    public String send(@RequestParam("message") String message) {
        log.info("new message created for all users : " + message);
        News news = new News(message, new java.util.Date(), "refresh fa-spin", "#", UserConstants.GLOBAL_GROUPEID.getGroupeId());

        repository.save(news);
        return "notification/newsList";

    }

}
