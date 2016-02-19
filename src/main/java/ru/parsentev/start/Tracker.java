package ru.parsentev.start;

import ru.parsentev.models.*;

/**
 * The class stores the items and processes them.
 */
class Tracker {
	/** The field keeps all the items. */
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
	protected Item findById(String id) {
		Item result = null;
		for (Item item : items) {
			if (item != null && item.getId().equals(id)){
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
	private String generateId() {
		//return String.valueOf(System.currentTimeMillis() + RN.nextInt());
		return String.valueOf(idPosition++);
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
			if(items[i] != null && item.getId().equals(items[i].getId())) {
				items[i] = item;
				break;
			}
		}
	}

	/**
	 * Delete item by ID.
	 * @param id ID.
     */
	public void delete(String id) {
		for(int i = 0; i < items.length; i++) {
			if(items[i] != null && items[i].getId().equals(id)) {
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
	public Item findByCreate(long create) {
		Item result = null;
		for(Item item : items) {
			if(item != null && item.getCreate() == create) {
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
	public void addComment(String id, Comment comment) {
		for (int i = 0; i < items.length; i++) {
			if (items[i] != null && id.equals(items[i].getId())) {
				items[i].comments[items[i].positionComment++] = comment;
			}
		}
	}
}