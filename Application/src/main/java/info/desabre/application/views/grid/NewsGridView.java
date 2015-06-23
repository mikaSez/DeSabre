package info.desabre.application.views.grid;

import info.desabre.application.views.inputs.InputWithType;
import info.desabre.database.models.information.News;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


public class NewsGridView {
    private String title;
    private InputWithType<Date> time;

    private static List<String> headers;


    static {
        headers = new ArrayList<>();
        headers.addAll(Arrays.asList("Message", "Temps"));
    }

    public NewsGridView(String title, Date time) {
        this.title = title;
        this.time = new InputWithType<Date>(time, InputWithType.DATE);
    }

    public static NewsGridView map(News news) {
        return new NewsGridView(news.getTitle(), news.getTime());
    }

    public static List<NewsGridView> map(List<News> news) {
        return news.stream().map(NewsGridView::map).collect(Collectors.toList());
    }

    public String getTitle() {
        return title;
    }

    public InputWithType<Date> getTime() {
        return time;
    }

    public List<String> getHeaders() {
        return headers;
    }

}
