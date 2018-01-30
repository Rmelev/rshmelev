/*package ru.job4j;

import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class TrackerTest {

    @Test
    public void whenComplexThenComplexResult() {
        Tracker tracker = new Tracker();
        Item previous = new Item("test1", "testDescription", System.currentTimeMillis());
        tracker.add(previous); // добавили мы только одну запись
        Item next = new Item("test2", "testDescription2", System.currentTimeMillis()); // эту просто создали
        next.setId(previous.getId()); // изменили записи next id на test1
        tracker.update(next); // нашли в массиве id test1 и переписали ему поля name, desc  и created
        //assertThat(tracker.findById(previous.getId()).getName(), is("test2"));
    }


    @Test
    public void whenAddFourItemsThenAddedFourItems() {
        Tracker tracker = new Tracker();
        Item item0 = new Item("test0", "testDescription0", System.currentTimeMillis());
        Item item1 = new Item("test1", "testDescription1", System.currentTimeMillis());
        Item item2 = new Item("test2", "testDescription2", System.currentTimeMillis());
        Item item3 = new Item("test3", "testDescription3", System.currentTimeMillis());
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
*/