package info.desabre.application.services;

import info.desabre.application.security.Role;
import info.desabre.database.models.user.User;
import info.desabre.repositories.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * Created by MikaSez on 31/05/2015.
 * Class used to store/manipulate data relative to the current user.
 */
@Component
public class UserService {

    private List<Role> userAuthorities;
    private UserDetails userDetails;
    private Logger log = Logger.getLogger(UserService.class.getName());


    private User user;
    @Autowired
    private UserRepository repository;

    public UserService() {
        userAuthorities = new ArrayList();
    }



    public boolean isAdmin() {
        return userAuthorities.contains(Role.ADMIN);
    }

    private void init() {
        userDetails = currentUserDetails();
        userAuthorities.clear();
        user = null;


        if (userDetails != null) {

            List<String> authorities = userDetails.getAuthorities()
                    .stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());
            authorities.forEach(s1 -> userAuthorities.add(Role.fromName(s1)));
            user = repository.findByMail(userDetails.getUsername());
        }


        log.info(userDetails.getUsername() + " logged in");
    }


    public User getUser() {
        if (user == null) {
            init();
        }
        return user;
    }

    public List<Role> getUserAuthorities() {
        init();
        return Collections.unmodifiableList(userAuthorities);
    }

    private UserDetails currentUserDetails() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        if (authentication != null) {
            Object principal = authentication.getPrincipal();
            return principal instanceof UserDetails ? (UserDetails) principal : null;
        }
        return null;
    }
}
