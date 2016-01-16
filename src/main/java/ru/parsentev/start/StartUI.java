package ru.parsentev.start;

import ru.parsentev.models.*;

public class StartUI {
	
	private Input input;

	public StartUI(Input input) {
		this.input = input;
	}

	public void init() {
		String name = input.ask("Please enter the task`s name: ");

		Tracker tracker = new Tracker();
		Task task = new Task(name, "first desc");

		tracker.add(task);

		for (Item item : tracker.getAll()) {
			System.out.println(item.getName());
		}

		tracker.edit(new Task("second task", "second desc"), task);

		for (Item item : tracker.getAll()) {
			System.out.println(item.getName());
		}

		tracker.addComment(task, new Comment("desc", 10l));

		for(Comment comment : task.getAllComments()) {
			System.out.println(comment.desc + " " + comment.createDate);
		}
	}

	public static void main(String[] args) {
		Input input = new ConsoleInput();
		new StartUI(input).init();
	}
}