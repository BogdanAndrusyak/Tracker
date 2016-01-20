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
		Tracker tracker = new Tracker();
		MenuTracker menu = new MenuTracker(this.input, tracker);
		menu.fillActions();
		do {
			menu.show();
			int key = Integer.valueOf(input.ask("Select: "));
			menu.select(key);
		} while(!"y".equals(this.input.ask("Exit?(y): ")));
		
		// Old menu...
		/*Menu menu = new Menu(input);

		menu.addEntry(new MenuEntry("Add Task") {
			public void run() {
				tracker.add(add());
			}
		});

		menu.addEntry(new MenuEntry("Find by name Task") {
			public void run() {
				Item item = findByName();
			}
		});

		menu.addEntry(new MenuEntry("Add comment to Task") {
			public void run() {
				addComment();
			}
		});

		menu.addEntry(new MenuEntry("Edit Task") {
			public void run() {
				edit();
			}
		});

		menu.addEntry(new MenuEntry("Show all tasks") {
			public void run() {
				showAllItems();
			}
		});

		menu.addEntry(new MenuEntry("Delete Task") {
			public void run() {
				delete();
			}
		});
		
		menu.run();*/
	}

	public static void main(String[] args) {
		Input input = new ConsoleInput();
		new StartUI(input).init();
	}
	// Old methods...
	/*public Item add() {
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

		tracker.edit();
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
	}*/
}