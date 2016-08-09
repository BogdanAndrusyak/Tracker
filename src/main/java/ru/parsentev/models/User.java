package ru.parsentev.models;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * TODO: comment.
 */
public class User extends Base {

    private Role role;
    private String name;
    private String login;
    private String password;
    private String email;
    private String country;
    private String city;
    private Calendar createDate;
    private Collection<Item> items; // todo can do this like one method from base?
    private List<Category> categories = new ArrayList<>();


    //todo id must be assigned in the base? how create user without id?
    //for create user
    public User(String name, String login, String password, String email, String country, String city) {
        this.role = new Role(2, "user");
        this.name = name;
        this.login = login;
        this.password = password;
        this.email = email;
        this.country = country;
        this.city = city;
        this.createDate = new GregorianCalendar();
        this.categories.add(new Category("No project")); // doesn't work yet
    }

    // for edit user
    public User(int id, Role role, String name, String login, String password, String email, String country, String city) {
        this.id = id;
        this.role = role;
        this.name = name;
        this.login = login;
        this.password = password;
        this.email = email;
        this.country = country;
        this.city = city;
        this.createDate = new GregorianCalendar();
        this.categories.add(new Category("No project"));
    }

    // from base
    public User(int id, Role role, String name, String login, String password, String email, String country, String city, Calendar createDate, Collection<Item> items) {
        this.id = id;
        this.role = role;
        this.name = name;
        this.login = login;
        this.password = password;
        this.email = email;
        this.country = country;
        this.city = city;
        this.createDate = createDate;
        this.items = items;
        this.categories.add(new Category("No project"));
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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

    public List<Category> getCategories() {
        return categories;
    }

    public void addCategory(Category category) {
        this.categories.add(category);
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