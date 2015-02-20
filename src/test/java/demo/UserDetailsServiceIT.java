package demo;

import demo.domain.User;
import demo.domain.UserRole;
import demo.mongodb.UserDao;
import demo.security.PasswordEncoder;
import demo.security.UserDetails;
import demo.security.UserDetailsService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Test
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = UiApplication.class)
@WebAppConfiguration
public class UserDetailsServiceIT {

    @Autowired
    UserDetailsService userDetailsService;
    @Autowired
    UserDao userDao;
    @Autowired
    PasswordEncoder passwordEncoder;

    private final String login = this.getClass().getName();
    private String password;

    @Before
    public void before() {
        this.password = passwordEncoder.encode("password");

        User user = new User()
                .setLogin(login)
                .setMail("test@test.com")
                .setPassword(password)
                .addRole(UserRole.ADMIN);
        userDao.save(user);
    }

    @After
    public void after() {
        userDao.remove(userDao.findByLogin(login));
    }

    @Test
    public void testUserDetailsService() {
        // Given

        // When
        UserDetails userDetails = userDetailsService.loadUserByUsername(login);

        // Then
        Assert.assertEquals(login, userDetails.getUsername());
        Assert.assertEquals(password, userDetails.getPassword());
        Assert.assertEquals(UserRole.ADMIN.name(), userDetails.getAuthorities().iterator().next().getAuthority());
    }

}
