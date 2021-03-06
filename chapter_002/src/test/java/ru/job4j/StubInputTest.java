/*package ru.job4j;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class StubInputTest {

   @Test
    public void whenUserAddItemThenTrackerHasNewItemWithSameName() {
        Input input = new StubInput(new String[]{"1", "test name", "desc", "7"});
        Tracker tracker = new Tracker();
        StartUI start = new StartUI(input, tracker);
        start.init();
        assertThat(tracker.getItems().get(0).getName(), is("test name"));
    }

    @Test
    public void whenUpdateThenTrackerHasUpdatedValue() {
        Tracker tracker = new Tracker();
        //Item item = new Item();
        tracker.getItems().add(new Item());
        Input input = new StubInput(new String[]{"3", String.valueOf(tracker.getItems().get(0).getId()), "test name", "desc", "7"});
        StartUI start = new StartUI(input, tracker);
        start.init();
        assertThat(tracker.getItems().get(0).getName(), is("test name"));
    }

   @Test
    public void whenComplexTestThenComplexResult() {
        Tracker tracker = new Tracker();
        tracker.getItems().add(new Item());
        tracker.getItems().add(new Item());
        tracker.getItems().add(new Item());
        Input input = new StubInput(new String[]{
                "3", String.valueOf(tracker.getItems().get(0).getId()), "roma", "test1",
                "3", String.valueOf(tracker.getItems().get(1).getId()), "jora", "test2",
                "3", String.valueOf(tracker.getItems().get(2).getId()), "gena", "test3",
                "4", String.valueOf(tracker.getItems().get(1).getId()), "7"});
        new StartUI(input, tracker).init();
        assertThat(tracker.getItems().get(1).getName(), is("gena"));
    }
}*/