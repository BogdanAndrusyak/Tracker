package ru.parsentev.start;

public class NoItemsException extends RuntimeException {
	public NoItemsException(String msg) {
		super(msg);
	}
}