package ru.parsentev.models;

import java.util.List;

/**
 * TODO: comment.
 * Category for item - project in view
 * Created by Bogdan on 8/3/2016.
 */
public class Category {
    private String name;
    private List<Item> items;

    public Category(String name) {
        this.name = name;
    }

    public void addItem(Item item) {
        this.items.add(item);
    }
}
