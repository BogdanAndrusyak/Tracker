package ru.parsentev.start;

interface UserAction {
	
	int key();

	void execute(Input input, Tracker tracker);

	String info();
}