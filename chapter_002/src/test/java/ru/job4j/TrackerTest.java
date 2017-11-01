package ru.job4j;

import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * class for test methods of class Tracker.
 */
public class TrackerTest {
    /**
     * test1: add() 1 item, setId(), getId(), update(), findById(), getAll().
     */
    @Test
    public void whenComplexThenComplexResult() {
        Tracker tracker = new Tracker();
        Item previous = new Item("test1", "testDescription", 123L);
        tracker.getItems().add(previous); // добавили мы только одну запись
        Item next = new Item("test2", "testDescription2", 1234L); // эту просто создали
        next.setId(previous.getId()); // изменили записи next id на test1
        tracker.update(next); // нашли в массиве id test1 и переписали ему поля name, desc  и created
        assertThat(tracker.findById(previous.getId()).getName(), is("test2"));
    }

    /**
     * test2, add() 4 items, delete() 2 items, getAll().
     */
    @Test
    public void whenAddFourItemsThenAddedFourItems() {
        Tracker tracker = new Tracker();
        Item item0 = new Item("test0", "testDescription0", 1230L);
        Item item1 = new Item("test1", "testDescription1", 1231L);
        Item item2 = new Item("test2", "testDescription2", 1232L);
        Item item3 = new Item("test3", "testDescription3", 1233L);
        tracker.getItems().add(item0);
        tracker.getItems().add(item1);
        tracker.getItems().add(item2);
        tracker.getItems().add(item3);
        tracker.getItems().remove(item1);
        tracker.getItems().remove(item2);
        ArrayList<Item> result = new ArrayList<>();
        result.add(item0);
        result.add(item3);
        assertThat(tracker.getItems(), is(result));
    }

    /**
     * test3, findByName(), getAll().
     */
    @Test
    public void whenDeleteThenDeleted() {
        Tracker tracker = new Tracker();
        Item item0 = new Item("test0", "testDescription0", 1230L);
        Item item1 = new Item("test1", "testDescription1", 1231L);
        Item item2 = new Item("test1", "testDescription2", 1232L);
        Item item3 = new Item("test1", "testDescription3", 1233L);
        Item item4 = new Item("test4", "testDescription3", 1234L);
        tracker.getItems().add(item0);
        tracker.getItems().add(item1);
        tracker.getItems().add(item2);
        tracker.getItems().add(item3);
        tracker.getItems().add(item4);
        ArrayList<Item> result = new ArrayList<>();
        result.add(item1);
        result.add(item2);
        result.add(item3);
        assertThat(tracker.findByName(item1.getName()), is(result));
    }
}