package ohtu.services;

import ohtu.domain.User;
import java.util.ArrayList;
import java.util.List;
import ohtu.data_access.UserDao;

public class AuthenticationService {

    private UserDao userDao;

    public AuthenticationService(UserDao userDao) {
        this.userDao = userDao;
    }

    public boolean logIn(String username, String password) {
        for (User user : userDao.listAll()) {
            if (user.getUsername().equals(username)
                    && user.getPassword().equals(password)) {
                return true;
            }
        }

        return false;
    }

    public boolean createUser(String username, String password) {
        if (userExists(username) || invalid(username, password) ) {
            return false;
        }

        userDao.add(new User(username, password));

        return true;
    }

    public boolean userExists(String username) {
        return userDao.findByName(username) != null;
    }

    private boolean invalid(String username, String password) {
        if ( username.length()<3) {
            return true;
        }
        
        if ( passwordInvalid(password)) return true;
       
        return false;
    }

    public boolean passwordInvalid(String password) {
        if (password.length()<8) {
            return true;
        }
        if (!password.matches(".*(0|1|2|3|4|5|6|7|8|9).*")) {
            return true;
        }
        return false;
    }
}
