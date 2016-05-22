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
        String name = "name";
        Item checked = new Task(name, "desc");

        // act
        tracker.add(checked);

        // action
        assertThat(tracker.findByName(name) != null, is(true));
    }

    @Test
    public void whenDeleteItemFromTracker() {
        String name = "name";
        Item checked = new Task(name, "desc");
        tracker.add(checked);

        tracker.delete(checked.getId());

        assertThat(tracker.findByName(name) == null, is(true));
    }

    @Test
    public void whenFindById() {
        Item checked = new Task("name", "desc");
        tracker.add(checked);

        assertEquals(checked, tracker.findById(checked.getId()));
    }

    @Test
    public void whenFindByWrongIdResultShouldBeNull() {
        int wrongId = 1000000;

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
        int checkedId = item1.getId();
        item2.setId(checkedId);

        tracker.edit(item2);

        assertEquals(item2, tracker.findById(checkedId));
    }

    @Test
    public void whenEditItemWithWrongId() {
        Item item1 = new Task("name1", "desc");
        Item item2 = new Task("name2", "desc");
        tracker.add(item1);
        int wrongId = 10000002;
        item2.setId(wrongId);

        tracker.edit(item2);

        assertNotEquals(item2, tracker.findById(wrongId));
    }


}