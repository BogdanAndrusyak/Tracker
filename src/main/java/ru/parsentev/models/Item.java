package ru.parsentev.models;

import java.util.Calendar;
import java.util.List;

public class Item {
	
	String id;

	String name;

	String description;

	private Calendar createDate;

	public User author;

	public List<Comment> comments;

	public List<File> files;

	@Override
	public String toString() {
		return "Item{" +
				"id='" + id + '\'' +
				", name='" + name + '\'' +
				", description='" + description + '\'' +
				", create=" + createDate +
				", author='" + author + '\'' +
				", comments=" + comments +
				'}';
	}

	Item() {

	}

	public Item(String name, String description, Calendar createDate) {
		this.name = name;
		this.description = description;
		this.createDate = createDate;
	}

	public String getName() {
		return this.name;
	}

	public String getDescription() {
		return this.description;
	}

	public Calendar getCreate() {
		return this.createDate;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void addComment(Comment comment) {
		this.comments.add(comment);
	}
	public List<Comment> getAllComments() {
		return this.comments;
	}
}