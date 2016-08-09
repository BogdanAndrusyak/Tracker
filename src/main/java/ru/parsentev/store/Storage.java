package ru.parsentev.store;

import ru.parsentev.models.*;

import java.util.Collection;
import java.util.List;

/**
 * TODO: comment.
 * Created by Bogdan on 4/20/2016.
 */
public interface Storage {

    List<User> getUsers();

    int addUser(final User user);

    void editUser(final User user);

    void deleteUser(final int id);

    User getUserById(final int id);

    List<Role> getRoles();

    List<Item> getAllItems();

    List<Item> getItemsByUserId(int userId);
//
//    Collection<Comment> getCommentsByItemId(int id);
//
//    Collection<File> getFilesByItemId(int id);

    int addItem(Item item);

    void deleteItem(int id);

    void addCommentToItem(int id, Comment comment);

    void addCategoryToUser(int id, Category category);

    void close();
}
