package ru.parsentev.store;

import ru.parsentev.models.Role;
import ru.parsentev.models.User;

import java.util.Collection;
import java.util.List;

/**
 * TODO: comment.
 * Created by Bogdan on 5/14/2016.
 */
public class UserCache implements Storage {

    private static final UserCache INSTANCE = new UserCache();

    private Storage storage = new JdbcStorage();

    public static UserCache getInstance() {
        return INSTANCE;
    }

    @Override
    public Collection<User> users() {
        return this.storage.users();
    }

    @Override
    public int addUser(User user) {
        return this.storage.addUser(user);
    }

    @Override
    public void editUser(User user) {
        this.storage.editUser(user);
    }

    @Override
    public void deleteUser(int id) {
        this.storage.deleteUser(id);
    }

    @Override
    public List<Role> getRoles() {
        return this.storage.getRoles();
    }

    @Override
    public User get(int id) {
        return this.storage.get(id);
    }

    @Override
    public void close() {
        this.storage.close();
    }

    public boolean isCredential(String login, String password) {
        boolean exists = false;
        for (User user : this.storage.users()) {
            if (login.equals(user.getLogin()) && password.equals(user.getPassword())) {
                exists = true;
                break;
            }
        }
        return exists;
    }

    public User findByLogin(String login) {
        User resultUser = null;
        for (User user : this.storage.users()) {
            if (login.equals(user.getLogin())) {
                resultUser = user;
                break;
            }
        }
        return resultUser;
    }
}
