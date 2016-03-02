package ru.parsentev.models;

public class Bug extends Item {
    public Bug(String name, String description, long create) {
        super(name, description, create);
    }

    @Override
    public String toString() {
        return "Bug " + super.toString();
    }
}