package ru.parsentev.models;

import java.util.Calendar;

public class Comment extends Base {

	private final String description;
	private final Calendar createDate;

	public Comment(String description, Calendar createDate) {
		this.description = description;
		this.createDate = createDate;
	}
	public Comment(int id, String description, Calendar createDate) {
		this.id = id;
		this.description = description;
		this.createDate = createDate;
	}

	public String getDescription() {
		return description;
	}

	public Calendar getCreateDate() {
		return createDate;
	}
}