package ru.parsentev.services;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * TODO: comment.
 */
public class SimpleListTest {

    public class A { }
    public class B extends A { }
    public class C extends B { }

    @Test
    public void whenCreateContainerShouldReturnTheSameType() {
        Stack simple = new Stack(4);

        simple.add("test");
        String result = simple.get(0);

        assertThat(result,  is("test"));
    }

    /*public void wildTest() {
        SimpleList<? extends A> list = new SimpleList<>(10);
        list.add(new A());
        list.add(new B());
        list.add(new C());

        print(list);
        printUpper(list);
        printLower(list);
    }

    public void print(SimpleList<?> list) {
        //todo print
    }

    public void printUpper(SimpleList<? extends B> list) {

    }

    public void printLower(SimpleList<? super B> list) {

    }*/
}