package ru.parsentev.start;

/**
 * Base abstract class of all actions.
 */
abstract class BaseAction implements UserAction {
	/** Name of action. */
	private final String name;

	/**
	 * Default constructor.
	 * @param name Name.
     */
	public BaseAction(String name) {
		this.name = name;
	}

	/**
	 * Print information about what action should to do.
	 * @return Information.
     */
	public String info() {
		return String.format("%s - %s", this.key(), name);
	}
}