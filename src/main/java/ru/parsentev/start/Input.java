package ru.parsentev.start;

import ru.parsentev.models.*;

public interface Input {
	
	String ask(String question);

	int ask(String question, int[] range);

}