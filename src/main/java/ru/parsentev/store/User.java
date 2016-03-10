package ru.parsentev.store;

/**
 * TODO: comment.
 */
public class User extends Base {
    private Role role;

    public User() {
    }

    public User(String id) {
        this.id = id;
    }

    public Role getRole() {
        return this.role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}