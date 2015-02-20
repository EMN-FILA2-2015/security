package demo;

import demo.domain.User;
import demo.domain.UserRole;
import demo.mongodb.UserDao;
import demo.security.PasswordEncoder;
import demo.security.UserDetails;
import demo.security.UserDetailsService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Test
 */
@RunWith(MockitoJUnitRunner.class)
public class UserDetailsServiceTest {

    @InjectMocks
    UserDetailsService userDetailsService;
    @Mock
    UserDao userDao;

    PasswordEncoder passwordEncoder = new PasswordEncoder();

    @Test
    public void testUserDetailsService() {
        // Given
        String login = "admin";
        String password = "password";

        User user = new User().setLogin(login).setPassword(password).addRole(UserRole.ADMIN);
        Mockito.when(userDao.findByLogin(login)).thenReturn(user);

        // When
        UserDetails userDetails = userDetailsService.loadUserByUsername("admin");

        // Then
        Assert.assertEquals("admin", userDetails.getUsername());
        Assert.assertEquals(password, password);
        Assert.assertEquals("ADMIN", userDetails.getAuthorities().iterator().next().getAuthority());
    }

}
