package ru.parsentev.start;

import ru.parsentev.models.*;

/**
 * This class runs the program.
 */
class StartUI {
    private static int[] ranges;
	private final Input input;
    private final MenuTracker menu;
    private final Tracker tracker;

    /**
     * Default constructor.
     * @param input Input.
     * @param tracker Tracker.
     * @param menu Menu.
     */
	public StartUI(final Input input, final Tracker tracker, final MenuTracker menu) {
		this.input = input;
        this.menu = menu;
        this.tracker = tracker;
    }

    /**
     * Init main loop.
     */
	public void init() {
		do {
			menu.show();
			menu.select(input.ask("Select: ", ranges));
		} while(!"y".equals(this.input.ask("Exit?(y): ")));
	}

	public static void main(String[] args) {
		Tracker tracker = new Tracker();
        Input input = new ValidateInput();
		MenuTracker menu = new BeautyMenu(input, tracker, 6);
		menu.fillActions();

		/**
		 * Anonymous class. Implements deleting item in the menu.
		 */
		UserAction deleteAction = new BaseAction("Delete item.") {

			public int key() {
				return 5;
			}

			public void execute(Input input, Tracker tracker) {
				if (tracker.getAll().length != 0) {
					String id;
					boolean correctId = false;
					do {
						id = input.ask("Please enter the task's id: ");
						for (Item item : tracker.getAll()) {
							if (id.equals(item.getId())) {
								correctId = true;
								break;
							} else {
								throw new InputMismatchException("ID doesn't correct.");
							}
						}
					} while (!correctId);
					tracker.delete(id);
				} else {
					throw new NoItemsException();
				}
			}
		};
		menu.addAction(deleteAction);
		ranges = menu.getRanges();

		new StartUI(input, tracker, menu).init();
	}
}