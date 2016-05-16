package ru.parsentev.store;

import ru.parsentev.models.Item;
import ru.parsentev.models.Role;
import ru.parsentev.models.User;

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

    User get(final int id);

    List<Role> getRoles();

    void close();
}
