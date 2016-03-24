package ru.parsentev.containers.MyTree;

/**
 * TODO: comment.
 */
public class Entry<E> {
    E value; //also and key
    Entry<E> left;
    Entry<E> right;
    Entry<E> parent;

    public Entry(E value, Entry<E> parent) {
        this.value = value;
        this.parent = parent;
    }

    public E getValue() {
        return this.value;
    }

    public void setValue(E value) {
        this.value = value;
    }
}
