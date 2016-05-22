package ru.parsentev.start;

import ru.parsentev.models.*;

import java.util.Calendar;

/**
 * The class stores the getAllItems and processes them.
 */
class Tracker {
	/** The field keeps all the getAllItems. */
	private final Item[] items = new Item[10];

	/** Position when adding a new item. */
	private int position = 0;

	/** Position ID. */
	private int idPosition = 100;

	/**
	 * Add a new item to the array.
	 * @param item New item.
     */
	public void add(Item item) {
		item.setId(this.generateId());
		this.items[position++] = item;
	}

	/**
	 * Find the item by ID.
	 * @param id ID the requested item.
	 * @return The desired item.
     */
	protected Item findById(int id) {
		Item result = null;
		for (Item item : items) {
			if (item != null && item.getId() == id){
				result = item;
				break;
			}
		}
		return result;
	}

	/**
	 * Generate a unique id.
	 * @return New id.
     */
	private int generateId() {
		//return String.valueOf(System.currentTimeMillis() + RN.nextInt());
		return idPosition++;
	}

	/**
	 * Get the array of all elements.
	 * @return Array.
     */
	public Item[] getAll() {
		Item[] result = new Item[position];
		for (int index=0; index!=this.position; index++) {
			result[index] = this.items[index];
		}
		return result;
	}

	/**
	 * Edit the existing item (just replace with new).
	 * @param item New item.
     */
	public void edit(Item item) {
		for(int i = 0; i < items.length; i++) {
			if(items[i] != null && item.getId() == items[i].getId()) {
				items[i] = item;
				break;
			}
		}
	}

	/**
	 * Delete item by ID.
	 * @param id ID.
     */
	public void delete(int id) {
		for(int i = 0; i < items.length; i++) {
			if(items[i] != null && items[i].getId() == id) {
				items[i] = null;
				position--; 
				break;
			}
		}
	}

	/**
	 * Find item by name.
	 * @param name Name.
	 * @return The desired item.
     */
	public Item findByName(String name) {
		Item result = null;
		for (Item item : items) {
			if (item != null && item.getName().equals(name)){
				result = item;
				break;
			}
		}
		return result;
	}

	/**
	 * Find item by created date.
	 * @param create Create date.
	 * @return The desired item.
     */
	public Item findByCreate(Calendar create) {
		Item result = null;
		for(Item item : items) {
			if(item != null && item.getCreateDate() == create) {
				result = item;
				break;
			}
		}
		return result;
	}

	/**
	 * Add new comment to the existing item.
	 * @param id ID existing item.
	 * @param comment New comment.
     */
	public void addComment(int id, Comment comment) {
		for (int i = 0; i < items.length; i++) {
			if (items[i] != null && id == items[i].getId()) {
				items[i].addComment(comment);
			}
		}
	}
}