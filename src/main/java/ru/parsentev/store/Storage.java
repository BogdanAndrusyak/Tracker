package ru.parsentev.store;

import ru.parsentev.models.*;

import java.util.Collection;
import java.util.List;

/**
 * TODO: comment.
 * Created by Bogdan on 4/20/2016.
 */
public interface Storage {

    Collection<User> users();

    int addUser(final User user);

    void editUser(final User user);

    void deleteUser(final int id);

    User getUserById(final int id);

    List<Role> getRoles();

    Collection<Item> getAllItems();

    Collection<Item> getItemsByUserId(int userId);
//
//    Collection<Comment> getCommentsByItemId(int id);
//
//    Collection<File> getFilesByItemId(int id);

    int addItem(Item item);

    void deleteItem(int id);

    void close();
}
