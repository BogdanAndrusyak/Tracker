package ru.parsentev.models;

/**
 * TODO: comment.
 * Created by Bogdan on 4/21/2016.
 */
public class File {

    private final byte[] array;

    public File(byte[] array) {
        this.array = array;
    }

    public byte[] read() {
        return this.array;
    }
}
