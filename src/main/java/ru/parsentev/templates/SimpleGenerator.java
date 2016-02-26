package ru.parsentev.templates;

/**
 * TODO: comment.
 */
public class SimpleGenerator implements Template {
    public String generate(String template, Object[] data) {
        return "Hello, Petr.";
    }
}
