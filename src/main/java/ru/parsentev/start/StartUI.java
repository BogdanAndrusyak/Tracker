package ru.parsentev.start;

import ru.parsentev.models.*;
import java.util.*;

public class StartUI {
	
	private Input input;
	private Tracker tracker = new Tracker();

	public StartUI(Input input) {
		this.input = input;
	}

	public void init() {
		
		tracker.add(add());

		Item item = findByName();
		edit();
		addComment();
		showAllItems();
		delete();
		showAllItems();

		/*tracker.edit(new Task("second task", "second desc"), task);

		for (Item item : tracker.getAll()) {
			System.out.println(item.getName());
		}

		tracker.addComment(task, new Comment("desc", 10l));

		for(Comment comment : task.getAllComments()) {
			System.out.println(comment.desc + " " + comment.createDate);
		}*/
	}

	public static void main(String[] args) {
		Input input = new ConsoleInput();
		new StartUI(input).init();
	}

	public Item add() {
		String name = input.ask("Please enter the task's name: ");
		String desc = input.ask("Please enter the task's description: ");

		return new Task(name, desc);
	}

	public Item findByName() {
		String name = input.ask("Please enter the task's name: ");
		return tracker.findByName(name);
	}

	public void edit() {
		String oldName = input.ask("Please enter the old task's name: ");
		String newName = input.ask("Please enter the new task's name: ");
		String newDesc = input.ask("Please enter the new task's description: ");

		tracker.edit(tracker.findByName(oldName), new Task(newName, newDesc));
	}

	public void delete() {
		String name = input.ask("Please enter the task's name to delete: ");
		tracker.delete(tracker.findByName(name).getId());
	}

	public void addComment() {
		String name = input.ask("Please enter the task's name to add comment: ");
		String commentDesc = input.ask("Please enter the comment: ");

		tracker.addComment(tracker.findByName(name), new Comment(commentDesc, new Date().getTime()));
	}

	public void showAllItems() {
		String answer = input.ask("Show all tasks yes/no: ");
		if (answer.equals("yes")) {
			for (Item item : tracker.getAll()) {
				System.out.println(item.getName());
			}
		}
	}
}