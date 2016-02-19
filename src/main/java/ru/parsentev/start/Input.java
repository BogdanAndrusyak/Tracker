package ru.parsentev.start;
/**
 * Input system.
 */
interface Input {
	/**
	 * Ask question the user and get an answer.
	 * @param question Question.
	 * @return Answer.
     */
	String ask(String question);

	/**
	 * Checking for correct answer. Using in menu.
	 * @param question Question.
	 * @param range The range of correct values.
	 * @return Answer.
	 */
	int ask(String question, int[] range);

}