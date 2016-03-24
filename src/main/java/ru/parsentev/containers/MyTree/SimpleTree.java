package ru.parsentev.containers.MyTree;

import ru.parsentev.containers.MyTree.Leaf;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO: comment.
 */
public class SimpleTree<E extends Comparable<E>> {

    private Entry<E> root;

    public void addChild(Leaf<E> leaf, E e) {
        // probably need to write a function check, leaf belongs to tree or not???
        Entry<E> t = leaf.getRoot();

        if(t == null) {
            leaf.setRoot(new Entry<E>(e, null));
        } else {
            int cmp;
            Entry<E> parent;

            do {
                parent = t;
                cmp = e.compareTo(t.getValue());
                if (cmp < 0)
                    t = t.left;
                if (cmp > 0)
                    t = t.right;
                else
                    t.setValue(e);
            } while (t != null);

            Entry<E> eEntry = new Entry<>(e, parent);
            if(cmp < 0)
                parent.left = eEntry;
            else
                parent.right = eEntry;
        }

    }

    public List<E> getChildren() {
        List<E> children = new ArrayList<>();
        Entry<E> t = root;

        //todo

        return children;
    }
}
