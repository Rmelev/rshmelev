package ru.job4j;

import java.util.ArrayList;
import java.util.List;

/**
 * helper class for munu work.
 */
public class MenuTracker {
    /**
     * choice stub or console.
     */
    private Input input;
    /**
     * tracker for our items.
     */
    private Tracker tracker;
    /**
     * set of user actions.
     */
    private List<UserAction> actions = new ArrayList<>();

    /**
     * Constructor.
     * @param input - way of interaction.
     * @param tracker - tracker.
     */
    public MenuTracker(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * initialization array of user's choice.
     * @return - initializated array
     */
    public int[] initializer() {
        fillActions();
        int[] ar = new int[this.actions.size()];
        int i = 0;
        for (UserAction action : actions) {
            ar[i++] = action.key();
        }
        return ar;
    }

    /**
     * filling of action's array.
     */
    public void fillActions() {
        this.actions.add(new MenuTracker.AddItem("Add the new item.", 1));
        this.actions.add(new MenuTracker.ShowItems("Show all items.", 2));
        this.actions.add(new MenuTracker.EditItem("Edit the item.", 3));
        this.actions.add(new MenuTracker.DeleteItem("Delete item.", 4));
        this.actions.add(new MenuTracker.FindItemById("Find item by id.", 5));
        this.actions.add(new MenuTracker.FindItemByName("Find item by name.", 6));
        this.actions.add(new MenuTracker.Exit("EXIT.", 7));
    }

    /**
     * type of reaction select.
     * @param key - numder parameter of reaction.
     */
    public  void select(int key) {
        this.actions.get(key - 1).execute(this.input, this.tracker);
    }

    /**
     * show all items.
     */
    public void show() {
        for (UserAction action : this.actions) {
            if (action != null) {
                System.out.println(action.info());
            }
        }
    }

    /**
     * action class for add.
     */
    private static class AddItem extends BaseAction {
        /**
         * Constructor with reference to superclass.
         * @param name - name of operation.
         * @param key - name of operation.
         */
        AddItem(String name, int key) {
            super(name, key);
        }

        /**
         * @return - numder of action.
         */
        public int key() {
            return 1;
        }

        /**
         * @param input - input.
         * @param tracker - tracker.
         */
        public void execute(Input input, Tracker tracker) {
            String name = input.ask("Please, enter the task's name: ");
            String desc = input.ask("Please, enter the task's desk: ");
            tracker.getItems().add(new Item(name, desc, 12345L));
        }
    }

    /**
     * action class for show all.
     */
    private static class ShowItems extends BaseAction {
        /**
         * Constructor with reference to superclass.
         * @param name - name of operation.
         * @param key - name of operation.
         */
        ShowItems(String name, int key) {
            super(name, key);
        }
        /**
         * @return - numder of action.
         */
        public int key() {
            return 2;
        }

        /**
         * @param input - input.
         * @param tracker - tracker.
         */
        public void execute(Input input, Tracker tracker) {
            for (Item item : tracker.getItems()) {
                System.out.println(String.format("%s  %s  %s", item.getId(), item.getName(), item.getDesc()));
            }
        }
    }

    /**
     * action class for edit.
     */
    private static class EditItem extends BaseAction {
        /**
         * Constructor with reference to superclass.
         * @param name - name of operation.
         * @param key - name of operation.
         */
        EditItem(String name, int key) {
            super(name, key);
        }

        /**
         * @return - numder of action.
         */
        public int key() {
            return 3;
        }

        /**
         * @param input - input.
         * @param tracker - tracker.
         */
        public void execute(Input input, Tracker tracker) {
            String id = input.ask("Please, enter the task's id: ");
            if (tracker.findById(id) == null) {
                System.out.println("Tracker haven't this item");
                return;
            }
            String name = input.ask("Please, enter the task's name: ");
            String desc = input.ask("Please, enter the task's desk: ");
            Item item = new Item(name, desc, 12345L);
            item.setId(id);
            tracker.update(item);
        }
    }

    /**
     * action class for delete.
     */
    private static class DeleteItem extends BaseAction {
        /**
         * Constructor with reference to superclass.
         * @param name - name of operation.
         * @param key - name of operation.
         */
        DeleteItem(String name, int key) {
            super(name, key);
        }

        /**
         * @return - numder of action.
         */
        public int key() {
            return 4;
        }

        /**
         * @param input - input.
         * @param tracker - tracker.
         */
        public void execute(Input input, Tracker tracker) {
            String id = input.ask("Please, enter the task's id: ");
            if (tracker.findById(id) == null) {
                System.out.println("Tracker haven't this item");
                return;
            }
            tracker.getItems().remove(tracker.findById(id));

        }
    }

    /**
     * action class for find by id.
     */
    private static class FindItemById extends BaseAction {
        /**
         * Constructor with reference to superclass.
         * @param name - name of operation.
         * @param key - name of operation.
         */
        FindItemById(String name, int key) {
            super(name, key);
        }

        /**
         * @return - numder of action.
         */
        public int key() {
            return 5;
        }

        /**
         * @param input - input.
         * @param tracker - tracker.
         */
        public void execute(Input input, Tracker tracker) {
            String id = input.ask("Input id of the item to find: ");
            if (tracker.findById(id) == null) {
                System.out.println("Tracker haven't this item");
                return;
            }
            for (Item item : tracker.getItems()) {
                if (item != null && item.getId().equals(id)) {
                    System.out.println(item.getId() + " " + item.getName() + " " + item.getDesc());
                }
            }

        }
    }

    /**
     * action class for find by name.
     */
    private static class FindItemByName extends BaseAction {
        /**
         * Constructor with reference to superclass.
         * @param name - name of operation.
         * @param key - name of operation.
         */
        FindItemByName(String name, int key) {
            super(name, key);
        }

        /**
         * @return - numder of action.
         */
        public int key() {
            return 6;
        }

        /**
         * @param input - input.
         * @param tracker - tracker.
         */
        public void execute(Input input, Tracker tracker) {
            String name = input.ask("Input name to search for related items: ");
            if (tracker.findByName(name) == null) {
                System.out.println("Tracker haven't items with this name");
                return;
            }
            for (Item item : tracker.getItems()) {
                if (item != null && item.getName().equals(name)) {
                    System.out.println(item.getId() + " " + item.getName() + " " + item.getDesc());
                }
            }

        }
    }

    /**
     * action class for exit.
     */
    private static class Exit extends BaseAction {
        /**
         * Constructor with reference to superclass.
         * @param name - name of operation.
         * @param key - name of operation.
         */
        Exit(String name, int key) {
            super(name, key);
        }

        /**
         * @return - numder of action.
         */
        public int key() {
            return 7;
        }

        /**
         * @param input - input.
         * @param tracker - tracker.
         */
        public void execute(Input input, Tracker tracker) {

        }
    }
}