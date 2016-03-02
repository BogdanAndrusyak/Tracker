package ru.parsentev.start;

public class StubInput implements SimpleInput {

	private final String[] answers;
	private int position = 0;

	public StubInput(String[] answers) {
		this.answers = answers;
	}

	public String ask(String question) {
		return answers[position++];
	}
}