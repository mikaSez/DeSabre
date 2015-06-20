package info.desabre.application.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebMvcSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    private static PasswordEncoder encoder = new BCryptPasswordEncoder();

    @Autowired
    private UserDetailsService customUserDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/user/*").hasAnyRole(Role.USER.getRoleName())
                .antMatchers("/job/*").hasAnyRole(Role.USER.getRoleName(), Role.ADMIN.getRoleName())
                .antMatchers("/admin/*").hasAnyRole(Role.ADMIN.getRoleName())
                .antMatchers("/CSS/*").permitAll()
                .antMatchers("/javascript/*").permitAll()
                .antMatchers("/inscription").permitAll()
                .antMatchers("/inscription/*").permitAll()
                .anyRequest().authenticated()

                .and()
                .formLogin().loginPage("/login").permitAll()
                .and()
                        //it's not secure, never use it in a real application. Mika.
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login")
                .and()
                .logout().permitAll();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    public synchronized PasswordEncoder passwordEncoder() {
        return encoder;
    }

}