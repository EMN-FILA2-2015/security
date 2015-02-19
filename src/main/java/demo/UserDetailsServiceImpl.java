package demo;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;

@Component(value="userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        UserDetailsBean userDetails = new UserDetailsBean();

        // TODO Get user data from database

        userDetails.setEnabled(true);
        userDetails.setUsername("admin");
        userDetails.setPassword("password");
        userDetails.setAuthorities(Arrays.asList(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return "ADMIN";
            }
        }));
        userDetails.setAccountNonExpired(true);
        userDetails.setAccountNonLocked(true);
        userDetails.setCredentialsNonExpired(true);

        return userDetails;
    }
}
