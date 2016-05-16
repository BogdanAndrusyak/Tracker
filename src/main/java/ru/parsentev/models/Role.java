package ru.parsentev.models;

import ru.parsentev.store.Base;

/**
 * TODO: comment.
 */
public class Role extends Base {

    private String name;

    //for edit
    public Role(int id) {
        this.id = id;
    }

    public Role(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
