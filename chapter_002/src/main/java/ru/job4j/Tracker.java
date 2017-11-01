package ru.job4j;

import java.util.ArrayList;
//import java.util.Arrays;
import java.util.List;

/**
 * class Tracker is user interface of items array.
 */
public class Tracker {
    /**
     * array of items.
     */
    private List<Item> items = new ArrayList<>();

    /**
     * getter for items.
     * @return - items.
     */
    public List<Item> getItems() {
        return items;
    }

    /**
     * @param item - item, which need to update.
     */
    public void update(Item item) {
        for (Item itemTemp : items) {
            if (itemTemp != null && itemTemp.getId().equals(item.getId())) {
                itemTemp.setName(item.getName());
                itemTemp.setDesc(item.getDesc());
                itemTemp.setCreated(item.getCreated());
            }
        }
    }

    /**
     * @param name - item name, which need to find.
     * @return - found item.
     */
    public ArrayList<Item> findByName(String name) {
        ArrayList<Item> itemName = new ArrayList<>();
        for (Item itemTemp : items) {
            if (itemTemp != null && itemTemp.getName().equals(name)) {
                itemName.add(itemTemp);
            }
        }
        return itemName;
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
}