package info.desabre.application.security;

import info.desabre.repositories.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;


/**
 * Converteur entre nos utilisateurs et le type utilisateur spring
 */
@Service
@Qualifier("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    private static final Logger log = Logger.getLogger(CustomUserDetailsService.class.getName());

    @Autowired
    private UserRepository userRepository;

    /**
     * Our username IS the email
     */
    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(final String mail)
            throws UsernameNotFoundException {


        log.info("Trying to connect : " + mail);
        info.desabre.database.models.user.User user = userRepository.findByMail(mail);
        if (user == null) {
            //FIXME enlever l'erreur
            return new User("Anonyme", "Anonyme", Collections.<GrantedAuthority>emptyList());
        }
        List<GrantedAuthority> authorities = getGrantedAuthorities(user);

        return buildUserForAuthentication(user, authorities);

    }


    /**
     * While we have only two roles we dont realy need any fancy systems with role in database.
     * But we shouldn't stick with it if roles > 2
     */
    private List<GrantedAuthority> getGrantedAuthorities(info.desabre.database.models.user.User user) {
        List<GrantedAuthority> authorities = new ArrayList();
        if (user.getAdmin()) {
            authorities.add(new SimpleGrantedAuthority(Role.ADMIN.toString()));
        } else {
            authorities.add(new SimpleGrantedAuthority(Role.USER.toString()));
        }
        return authorities;
    }

    private User buildUserForAuthentication(info.desabre.database.models.user.User user,
                                            List<GrantedAuthority> authorities) {
        User springUser = new User(user.getMail(), user.getPassword(), user.getValidated(), false, false, user.getDeleted(), authorities);

        return springUser;
    }


}