package ru.parsentev.containers.MyTree;

/**
 * TODO: comment.
 */
public class Leaf<E> {
    private Entry<E> root;

    public Leaf(Entry<E> root) {
        this.root = root;
    }

    public Entry<E> getRoot() {
        return this.root;
    }

    public void setRoot(Entry<E> root) {
        this.root = root;
    }
}
