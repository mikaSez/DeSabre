package info.desabre.application.services;

import info.desabre.application.security.Role;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by MikaSez on 31/05/2015.
 * Class used to store/manipulate data relative to the current user.
 */
@Component
public class UserService {

    private List<Role> userAuthorities;
    private UserDetails userDetails;

    public UserService() {
        userAuthorities = new ArrayList();
    }

    private void init() {
        userDetails = currentUserDetails();
        if (userDetails != null) {
            List<String> authorities = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
            for (String s : authorities) {
                userAuthorities.add(Role.fromName(s));
            }
        }
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
