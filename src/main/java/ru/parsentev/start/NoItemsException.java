package ru.parsentev.start;

class NoItemsException extends RuntimeException {
	public NoItemsException() {
		super("No items.");
	}
}