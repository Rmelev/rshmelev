package ru.job4j;

import java.util.Arrays;

/**
 * class Tracker is user interface of items array.
 */
public class Tracker {
    /**
     * size of item array.
     */
    private int n = 100;
    /**
     * array of items.
     */
    private Item[] items = new Item[n];

    /**
     * count of added items.
     */
    private int count = 0;

    /**
     * add item to Tracker.
     * @param item - added to Tracker item.
     * @return - item, what we added.
     */
    public Item add(Item item) {
        this.items[count++] = item;
        return item;
    }

    /**
     * @param item - item, which need to update.
     */
    public void update(Item item) {
        for (int i = 0; i < n; i++) {
            if (items[i] != null && items[i].getId().equals(item.getId())) {
                items[i].setName(item.getName());
                items[i].setDesc(item.getDesc());
                items[i].setCreated(item.getCreated());
            }
        }
    }

    /**
     * @param item - item id, which need to delete.
     */
    public void delete(Item item) {
        for (int i = 0; i < n; i++) {
            if (items[i] != null && items[i].getId().equals(item.getId())) {
                items[i] = null;
            }
        }
        int counter = 0;
        for (Item it : items) {
            if (it == null) {
                counter++;
            }
        }
        for (int i = 0; i < items.length - counter; i++) {
            for (int j = 1; j < items.length - 1 - i; j++) {
                if (items[j] == null) {
                    Item temp = items[j];
                    items[j] = items[j + 1];
                    items[j + 1] = temp;
                }
            }
        }
    }

    /**
     * @param name - item name, which need to find.
     * @return - found item.
     */
    public Item[] findByName(String name) {
        int j = 0;
        Item[] itemName = new Item[n];
        for (int i = 0; i < n; i++) {
            if (items[i] != null && items[i].getName().equals(name)) {
                itemName[j++] = items[i];
            }
        }
        return Arrays.copyOf(itemName, j);
    }

    /**
     * @param id item name, which need to find.
     * @return - found item.
     */
    public Item findById(String id) {
        Item result = null;
        for (Item item : items) {
            if (item != null && item.getId().equals(id)) {
                result = item;
                break;
            }
        }
        return result;
    }

    /**
     * @return - all items of array.
     */
    public Item[] getAll() {
        int counter = 0;
        for (Item it : items) {
            if (it == null) {
                counter++;
                //System.out.print(counter);
            }
        }
        for (int i = 0; i < items.length - counter; i++) {
            for (int j = 0; j < items.length - 1 - i; j++) {
                if (items[j] == null) {
                    Item temp = items[j];
                    items[j] = items[j + 1];
                    items[j + 1] = temp;
                }
            }
        }
        //System.out.println("Длина: " + items.length  + "counter: " + counter);
        return Arrays.copyOf(items, items.length - counter);
    }
}