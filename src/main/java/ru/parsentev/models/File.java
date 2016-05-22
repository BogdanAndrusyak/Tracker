package ru.parsentev.models;

/**
 * TODO: comment.
 * Created by Bogdan on 4/21/2016.
 */
public class File extends Base {

    private final byte[] array;

    public File(int id, byte[] array) {
        this.id = id;
        this.array = array;
    }

    public File(byte[] array) {
        this.array = array;
    }

    public byte[] read() {
        return this.array;
    }
}
