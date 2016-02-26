package ru.parsentev.start;

/**
 * TODO: comment.
 */
public class BeautyMenu extends MenuTracker {

    /**
     * Default constructor.
     *
     * @param input   Input system.
     * @param tracker Tracker.
     * @param size Menu size.
     */
    public BeautyMenu(Input input, Tracker tracker, int size) {
        super(input, tracker, size);
    }

    @Override
    public void showItem(UserAction action) {
        System.out.println("-=" + action.info());
    }
}
