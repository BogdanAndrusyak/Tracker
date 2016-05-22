package ru.parsentev.models;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;

/**
 * TODO: comment.
 */
public class User extends Base {

    private Role role;
    private String name;
    private String login;
    private String password;
    private String email;
    private Calendar createDate;
    private Collection<Item> items; // todo can do this like one method from base?

    //todo id must be assigned in the base? how create user without id?
    //for create user
    public User(String name, String login, String password, String email) {
        this.role = new Role(2, "user");
        this.name = name;
        this.login = login;
        this.password = password;
        this.email = email;
        this.createDate = new GregorianCalendar();
    }

    // for edit user
    public User(int id, Role role, String name, String login, String password, String email) {
        this.id = id;
        this.role = role;
        this.name = name;
        this.login = login;
        this.password = password;
        this.email = email;
        this.createDate = new GregorianCalendar();
    }

    // from base
    public User(int id, Role role, String name, String login, String password, String email, Calendar createDate, Collection<Item> items) {
        this.id = id;
        this.role = role;
        this.name = name;
        this.login = login;
        this.password = password;
        this.email = email;
        this.createDate = createDate;
        this.items = items;
    }

    public Role getRole() {
        return this.role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getName() {
        return this.name;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Calendar getCreateDate() {
        return createDate;
    }

    public String getSimpleCreateDate() {
        return new SimpleDateFormat("dd/M/yyyy hh:mm:ss a").format(getCreateDate().getTime());
    }

    public Collection<Item> getItems() {
        return items;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", login='" + login + '\'' +
                ", email='" + email + '\'' +
                ", createDate=" + createDate.getTime() +
                '}';
    }
}