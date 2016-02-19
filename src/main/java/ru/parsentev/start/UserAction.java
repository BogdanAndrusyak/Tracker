package ru.parsentev.start;

/**
 * Common interface for actions.
 */
interface UserAction {
	/**
	 * Define a unique key.
	 * @return Key.
     */
	int key();

	/**
	 * Execute action.
	 * @param input Input system.
	 * @param tracker Tracker.
     */
	void execute(Input input, Tracker tracker);

	/**
	 * Print information about what action should to do.
	 * @return Information.
     */
	String info();
}