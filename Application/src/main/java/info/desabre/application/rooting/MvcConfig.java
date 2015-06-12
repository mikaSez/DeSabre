package info.desabre.application.rooting;

import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.ErrorPage;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter implements EmbeddedServletContainerCustomizer{


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
        registry.addViewController("/job/list").setViewName("job/jobList");
        registry.addViewController("/job/create").setViewName("job/jobCreate");
        registry.addViewController("/server/list").setViewName("server/serverList");
        registry.addViewController("/notification/list").setViewName("notification/notificationList");
        registry.addViewController("/profil").setViewName("user/profil");
        registry.addViewController("/index").setViewName("index");

    }

	@Override
	public void customize(ConfigurableEmbeddedServletContainer container) {
		container.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/error/404"));
		container.addErrorPages(new ErrorPage(HttpStatus.FORBIDDEN, "/error/403"));
	}

	

}