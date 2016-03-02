package ru.parsentev.start;

/**
 * TODO: comment.
 */
public interface InputRange {
    /**
     * Checking for correct answer. Using in menu.
     * @param question Question.
     * @param range The range of correct values.
     * @return Answer.
     */
    int ask(String question, int[] range);
}
