package ru.parsentev.store;

import ru.parsentev.models.Item;

import java.util.Collection;

/**
 * TODO: comment.
 * Created by Bogdan on 4/20/2016.
 */
public interface Storage {

    Collection<User> users();

    int addUser(final User user);

    void editUser(final User user);

    void deleteUser(final int id);

    int addItem(final Item item);

    void editItem(final Item item);

    void deleteItem(final int id);

    void close();
}
