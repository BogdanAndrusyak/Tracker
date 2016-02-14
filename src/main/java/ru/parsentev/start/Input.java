package ru.parsentev.start;

interface Input {
	
	String ask(String question);

	int ask(String question, int[] range);

}