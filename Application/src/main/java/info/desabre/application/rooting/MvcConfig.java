package info.desabre.application.rooting;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {


    /**
     * Indicate here the correspondaces between the view and the code.
     * The view controller is the intercepted path, which must be handled in controllers
     * The view name is the template file name who will be handled by this controller.
     * The view is typicaly under ressources templates and is *.html
     * The view name is stripped of .html when written here.
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/user/home").setViewName("user/home");
        registry.addViewController("/").setViewName("user/home");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/admin/home").setViewName("admin/home");

        /*
        registry.addViewController("/index").setViewName("index");
         */
    }

}