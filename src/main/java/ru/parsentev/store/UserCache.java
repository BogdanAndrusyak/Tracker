package ru.parsentev.store;

import ru.parsentev.models.User;

import java.util.Collection;

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
    public User get(int id) {
        return this.storage.get(id);
    }

    @Override
    public void close() {
        this.storage.close();
    }
}
