package ru.parsentev.models;

import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.List;

public class Item extends Base {

	protected String name;
	protected String description;
	protected Calendar createDate;
	protected int authorId;
	protected Collection<Comment> comments;
	protected Collection<File> files;

	Item() {

	}

	// for add from view
	public Item(String name, int authorId) {
		this.name = name;
		this.authorId = authorId;
		this.createDate = new GregorianCalendar();
	}

	// for add from base
	public Item(int id, String name, String description, Calendar createDate, int authorId, Collection<Comment> comments, Collection<File> files) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.createDate = createDate;
		this.authorId = authorId;
		this.comments = comments;
		this.files = files;
	}

	public String getName() {
		return this.name;
	}

	public String getDescription() {
		return this.description;
	}

	public Calendar getCreateDate() {
		return this.createDate;
	}

	public int getAuthorId() {
		return authorId;
	}

	public void addComment(Comment comment) {
		this.comments.add(comment);
	}

	public Collection<Comment> getAllComments() {
		return this.comments;
	}
}