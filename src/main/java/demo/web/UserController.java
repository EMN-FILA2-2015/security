package demo.web;

import demo.domain.User;
import demo.mongodb.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller : users
 */
@RestController
public class UserController {

    @Autowired
    private UserDao userDao;

    @RequestMapping(value="rest/users", method=RequestMethod.GET)
    public List<User> findAll() {
        List<User> users = userDao.findAll();
        for(User user : users) {
            user.setPassword(null);
        }
        return users;
    }

    @RequestMapping(value="rest/users/{login}", method=RequestMethod.GET)
    public User findByLogin(@PathVariable(value = "login") String login) {
        User user = userDao.findByLogin(login);
        user.setPassword(null);
        return user;
    }

    @RequestMapping(value="rest/users/{login}", method=RequestMethod.PUT)
    public void save(@PathVariable(value = "login") String login, @RequestBody User user) {
        User userOld = userDao.findByLogin(login);
        user.setPassword(userOld.getPassword());
        userDao.save(user);
    }

}
