package ru.job4j;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * class for stubinput testing.
 */
public class StubInputTest {
    /**
     * testing add and exit.
     */
    @Test
    public void whenUserAddItemThenTrackerHasNewItemWithSameName() {
        Tracker tracker = new Tracker();
        Input input = new StubInput(new String[]{"0", "test name", "desc", "6"});
        StartUI start = new StartUI(input, tracker);
        start.init();
        assertThat(tracker.getAll()[0].getName(), is("test name"));
    }

    /**
     * testing update.
     */
    @Test
    public void whenUpdateThenTrackerHasUpdatedValue() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item());
        Input input = new StubInput(new String[]{"2", item.getId(), "test name", "desc", "6"});
        StartUI start = new StartUI(input, tracker);
        start.init();
        assertThat(tracker.findById(item.getId()).getName(), is("test name"));
    }

    /**
     * testing delete.
     */
    @Test
    public void whenComplexTestThenComplexResult() {
        Tracker tracker = new Tracker();
        Item item1 = tracker.add(new Item());
        Item item2 = tracker.add(new Item());
        Item item3 = tracker.add(new Item());
        Input input = new StubInput(new String[]{
                "2", item1.getId(), "roma", "test1",
                "2", item2.getId(), "jora", "test2",
                "2", item3.getId(), "gena", "test3",
                "3", item2.getId(), "6"});
        new StartUI(input, tracker).init();
        //System.out.println(tracker.getAll()[2].getId());
        assertThat(tracker.getAll()[1].getName(), is("gena"));
    }
}