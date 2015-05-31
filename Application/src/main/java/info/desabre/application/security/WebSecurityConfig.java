package info.desabre.application.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;

@Configuration
@EnableWebMvcSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/user/*").hasAnyRole(Role.USER.getRoleName())
                .antMatchers("/admin/*").hasAnyRole(Role.ADMIN.getRoleName())
                .antMatchers("/CSS/*").permitAll()
                .antMatchers("/javascript/*").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login").permitAll()
                .and()
                .logout().permitAll();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser(Role.USER.getRoleName()).password("password").roles(Role.USER.getRoleName()).and()
                .withUser(Role.ADMIN.getRoleName()).password("password").roles(Role.ADMIN.getRoleName()).and()
                .withUser("dorian").password("toto").roles(Role.USER.getRoleName(), Role.ADMIN.getRoleName()).and()
                .withUser("maxime").password("toto").roles(Role.USER.getRoleName(), Role.ADMIN.getRoleName());
    }
}