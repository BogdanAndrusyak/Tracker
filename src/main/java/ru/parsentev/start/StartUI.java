package ru.parsentev.start;

import ru.parsentev.models.*;

public class StartUI {

	public static void main(String[] args) {
		Tracker tracker = new Tracker();
		Task task = new Task("first task", "first desc");

		tracker.add(task);

		for (Item item : tracker.getAll()) {
			System.out.println(item.getName());
		}

		tracker.edit(new Task("second task", "second desc"), task);

		for (Item item : tracker.getAll()) {
			System.out.println(item.getName());
		}
	}
}