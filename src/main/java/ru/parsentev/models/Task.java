package ru.parsentev.models;

public class Task extends Item{

	public Task(String name, String desc) {
		this.name = name;
		this.description = desc;
	}

	@Override
	public String toString() {
		return "Task " + super.toString();
	}

	public String calculatePrice() {
		return "100%";
	}
}