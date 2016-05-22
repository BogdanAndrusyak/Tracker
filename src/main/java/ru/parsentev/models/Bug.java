package ru.parsentev.models;

import java.util.Calendar;

public class Bug extends Item {
    public Bug(String name, String description, Calendar create) {
//        super(name, description, create);
    }

    @Override
    public String toString() {
        return "Bug " + super.toString();
    }
}