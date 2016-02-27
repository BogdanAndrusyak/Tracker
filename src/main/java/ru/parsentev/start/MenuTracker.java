package ru.parsentev.start;

import ru.parsentev.models.*;
import java.util.*;

/**
 * Outer class. Edits an item.
 */
class EditItem extends BaseAction {

	public EditItem() {
		super("Edit the item.");
	}

	public int key() {
		return 2;
	}

	public void execute(Input input, Tracker tracker) {
		
		if (tracker.getAll().length != 0) {
			String id;
			boolean correctId;
			do {
				id = input.ask("Please enter the task's id: ");

				Item item = tracker.findById(id);
				if (item == null) {
					throw new InputMismatchException("ID doesn't correct.");
				}

				correctId = true;
			} while (!correctId);

			String name = input.ask("Please enter the new task's name: ");
			String desc = input.ask("Please enter the new task's desc: ");
			Task task = new Task(name, desc);
			task.setId(id);
			tracker.edit(task);
		} else {
			throw new NoItemsException();
		}
	}
}

/**
 * Contains all of the action.
 * Responsible for the showing menu, and choose an action from the menu.
 */
public class MenuTracker {
	private final Input input;
	private final Tracker tracker;

	/** The array of actions. */
	protected final UserAction[] actions;

	/** Position when adding a new action. */
	private int position = 0;

	/**
	 * Default constructor.
	 * @param input Input system.
	 * @param tracker Tracker.
     */
	public MenuTracker(Input input, Tracker tracker, int size) {
		this.input = input;
		this.tracker = tracker;
		this.actions = new UserAction[size];
	}

	/**
	 * Filling array of actions.
	 */
	public void fillActions() { 
		this.actions[position++] = this.new AddItem();
		this.actions[position++] = new MenuTracker.ShowItems();
		this.actions[position++] = new EditItem();
		this.actions[position++] = new MenuTracker.FindByName();
		this.actions[position++] = new MenuTracker.AddComment();
	}

	/**
	 * Add action to the array from outside.
	 * @param action Action.
     */
	public void addAction(UserAction action) {
		this.actions[position++] = action;
	}

	/**
	 * Return the range of the menu choices.
	 * @return Range.
     */
	public int[] getRanges() {
		return new int[] {0, 1, 2, 3, 4, 5};
	}

	/**
	 * Execute the selected action by key from the menu.
	 * @param key Key.
     */
	public void select(int key) {
		boolean invalid = true;
		do {
			try {
				this.actions[key].execute(this.input, this.tracker);
				invalid = false;
			} catch (NoItemsException nie) {
				System.out.println("Please first add the item.");
				invalid = false;
			} catch (InputMismatchException ime) {
				System.out.println("Please enter the correct data.");	
			}
		} while(invalid);	
	}

	/**
	 * Print menu.
	 */
	public void show() {
		System.out.println("\nMenu:");

		for (UserAction action : this.actions) {
			if (action != null) {
				this.showItem(action);
			}
		}
	}

	public void showItem(UserAction action) {
		System.out.println(action.info());
	}

	/**
	 * Non-Static inner class. Adds an item.
	 */
	private class AddItem extends BaseAction {

		public AddItem() {
			super("Add the new item.");
		}

		public int key() {
			return 0;
		}

		public void execute(Input input, Tracker tracker) {
			String name = input.ask("Please enter the task's name: ");
			String desc = input.ask("Please enter the task's desc: ");
			tracker.add(new Task(name, desc));
		}
	}

	/**
	 * Static inner class. Shows all the items.
	 */
	private static class ShowItems extends BaseAction {

		public ShowItems() {
			super("Show all items.");
		}

		public int key() {
			return 1;
		}

		public void execute(Input input, Tracker tracker) {
			if(tracker.getAll().length != 0) {
				for (Item item : tracker.getAll()) {
					System.out.println(
						String.format("ID: %s, Name: %s.", item.getId(), item.getName())
					);
				}
			} else {
				throw new NoItemsException();
			}
		}
	}

	/**
	 * Find item by name.
	 */
	private class FindByName extends BaseAction {

		public FindByName() {
			super("Find item by name.");
		}

		public int key() {
			return 3;
		}

		public void execute(Input input, Tracker tracker) {
			if (tracker.getAll().length != 0) {
				Item item;
				String name;
				boolean correctName;
				do {
					name = input.ask("Please enter the task's name: ");
					item = tracker.findByName(name);

					if (item == null) {
						throw new InputMismatchException("Name doesn't correct.");
					}
					correctName = true;
				} while (!correctName);

				System.out.println(
						String.format("ID: %s, Name: %s", item.getId(), item.getName())
				);
			} else {
				throw new NoItemsException();
			}
		}
	}

	/**
	 * Add new comment to item.
	 */
	private class AddComment extends BaseAction {

		public AddComment() {
			super("Add comment.");
		}

		public int key() {
			return 4;
		}

		public void execute(Input input, Tracker tracker) {
			if (tracker.getAll().length != 0) {
				String id;
				boolean correctId = false;
				do {
					id = input.ask("Please enter the task's id: ");
					Item item = tracker.findById(id);
					if(item == null) {
						throw new InputMismatchException("ID doesn't correct.");
					}

					correctId = true;
				} while (!correctId);
				String comment = input.ask("Please enter the task's comment: ");
				tracker.addComment(id, new Comment(comment, new Date().getTime()));
			} else {
				throw new NoItemsException();
			}
		}
	}
}