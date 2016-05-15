package ru.parsentev.models;

import ru.parsentev.store.Base;
import ru.parsentev.store.Role;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * TODO: comment.
 */
public class User extends Base {
    private Role role;
    private String name;
    private String login;
    private String email;
    private Calendar createDate;

    //todo id must be assigned in the base? how create user without id?
    public User(String name, String login, String email) {
        this.name = name;
        this.login = login;
        this.email = email;
        this.createDate = new GregorianCalendar();
    }

    public User(int id, String name, String login, String email) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.email = email;
        this.createDate = new GregorianCalendar();
    }

    // from base
    public User(int id, String name, String login, String email, Calendar createDate) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.email = email;
        this.createDate = createDate;
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
        return new SimpleDateFormat("dd/M/yyyy hh:mm:ss").format(getCreateDate().getTime());
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