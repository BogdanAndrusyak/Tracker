package ru.parsentev.store;

import ru.parsentev.models.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * TODO: comment.
 * Created by Bogdan on 5/14/2016.
 */
public class StorageCache implements Storage {

    private static final StorageCache INSTANCE = new StorageCache();

    private Storage storage = new JdbcStorage();

    public static StorageCache getInstance() {
        return INSTANCE;
    }

    @Override
    public List<User> getUsers() {
        return this.storage.getUsers();
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
    public User getUserById(int id) {
        return this.storage.getUserById(id);
    }

    @Override
    public int addItem(Item item) {
        return this.storage.addItem(item);
    }

    @Override
    public List<Item> getAllItems() {
        return this.storage.getAllItems();
    }

    @Override
    public List<Item> getItemsByUserId(int userId) {
        return this.storage.getItemsByUserId(userId);
    }

    //todo only for test, maybe do refactor?
    public List<Integer> getUsersIds() {
        List<Integer> ids = new ArrayList<>();
        for (User user : storage.getUsers()) {
            ids.add(user.getId());
        }

        return ids;
    }

    @Override
    public void close() {
        this.storage.close();
    }

    public boolean isCredential(String login, String password) {
        boolean exists = false;
        for (User user : this.storage.getUsers()) {
            if (login.equals(user.getLogin()) && password.equals(user.getPassword())) {
                exists = true;
                break;
            }
        }
        return exists;
    }

    @Override
    public void deleteItem(int id) {
        this.storage.deleteItem(id);
    }

    public User findByLogin(String login) {
        User resultUser = null;
        for (User user : this.storage.getUsers()) {
            if (login.equals(user.getLogin())) {
                resultUser = user;
                break;
            }
        }
        return resultUser;
    }

    @Override
    public void addCommentToItem(int id, Comment comment) {
        this.storage.addCommentToItem(id, comment);
    }

    @Override
    public void addCategoryToUser(int id, Category category) {
        this.storage.addCategoryToUser(id, category);
    }
}
