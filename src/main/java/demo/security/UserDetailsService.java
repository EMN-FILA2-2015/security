package demo.security;

import demo.domain.User;
import demo.mongodb.UserDao;
import demo.domain.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component(value="userDetailsService")
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {

        UserDetails userDetails = new UserDetails();

        // Get user data from database
        User user = userDao.findByLogin(login);

        if(user == null) {
            return null;
        }

        userDetails.setUsername(user.getLogin());
        userDetails.setPassword(user.getPassword());

        for(UserRole role : user.getRoles()) {
            userDetails.addAuthorities(new GrantedAuthority(role));
        }

        userDetails.setEnabled(true);
        userDetails.setAccountNonExpired(true);
        userDetails.setAccountNonLocked(true);
        userDetails.setCredentialsNonExpired(true);

        return userDetails;
    }
}
