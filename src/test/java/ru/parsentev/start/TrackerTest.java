package ru.parsentev.start;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.parsentev.models.Comment;
import ru.parsentev.models.Item;
import ru.parsentev.models.Task;

import java.util.Date;

import static org.hamcrest.core.CombinableMatcher.either;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * TODO: comment.
 */
public class TrackerTest {
    Tracker tracker;

    @Before
    public void initialize() {
        tracker = new Tracker();
    }

    @Test
    public void whenAddNewItemsShouldBeSavedInTracker() {
        // assign
        Item checked = new Task("name", "desc");
        Item checked2 = new Task("namfe", "desc");
        tracker.add(checked);
        tracker.add(checked2);
        boolean is = false;

        // act
        for (Item item : tracker.getAll()) {
            is = item.equals(checked) ? true : false;
        }
        for (Item item : tracker.getAll()) {
            is = item.equals(checked2) ? true : false;
        }

        // action
        assertTrue(is);
    }

    @Test
    public void whenDeleteItemFromTracker() {
        Item checked = new Task("name", "desc");
        tracker.add(checked);

        tracker.delete(checked.getId());

        for (Item item : tracker.getAll()) {
            assertNull(item);
        }
    }

    @Test
    public void whenFindById() {
        Item checked = new Task("name", "desc");
        tracker.add(checked);

        assertEquals(checked, tracker.findById(checked.getId()));
    }

    @Test
    public void whenFindByWrongIdResultShouldBeNull() {
        String wrongId = "1000000";

        assertNull(tracker.findById(wrongId));
    }

    @Test
    public void whenFindByName() {
        String itemName = "name";
        Item checked = new Task(itemName, "desc");
        tracker.add(checked);

        assertEquals(checked, tracker.findByName(itemName));
    }

    @Test
    public void whenFindByWrongNameResultShouldBeNull() {
        String itemName = "name";
        String wrongName = "namesdfsfdsfsfsdf";
        Item checked = new Task(itemName, "desc");
        tracker.add(checked);

        assertNull(tracker.findByName(wrongName));
    }

    @Test
    public void whenEditItem() {
        Item item1 = new Task("name1", "desc");
        Item item2 = new Task("name2", "desc");
        tracker.add(item1);
        String checkedId = item1.getId();
        item2.setId(checkedId);

        tracker.edit(item2);

        assertEquals(item2, tracker.findById(checkedId));
    }

    @Test
    public void whenEditItemWithWrongId() {
        Item item1 = new Task("name1", "desc");
        Item item2 = new Task("name2", "desc");
        tracker.add(item1);
        String wrongId = "10000002";
        item2.setId(wrongId);

        tracker.edit(item2);

        assertNotEquals(item2, tracker.findById(wrongId));
    }


}