package demo.mongodb;

import demo.domain.User;
import demo.domain.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * DAO : Users
 */
@Repository
public class UserDao  {

    public static String USERS = "users";

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void save(User user) {
        mongoTemplate.save(user, USERS);
    }

    public void remove(User user) {
        mongoTemplate.remove(user, USERS);
    }

    public List<User> findAll() {
        return mongoTemplate.findAll(User.class, USERS);
    }

    public User findByLogin(String login) {
        /*
        return new User()
                .setLogin("admin")
                .setMail("admin@localhost")
                .setPassword(passwordEncoder.encode("password"))
                .addRole(UserRole.ADMIN);
        */
        return mongoTemplate.findById(login, User.class, USERS);
    }

}
