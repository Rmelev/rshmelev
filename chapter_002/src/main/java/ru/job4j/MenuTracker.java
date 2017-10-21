package ru.job4j;

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
    private UserAction[] actions = new UserAction[7];

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
     * filling of action's array.
     */
    public void fillActions() {
        this.actions[0] = new MenuTracker.AddItem();
        this.actions[1] = new MenuTracker.ShowItems();
        this.actions[2] = new MenuTracker.EditItem();
        this.actions[3] = new MenuTracker.DeleteItem();
        this.actions[4] = new MenuTracker.FindItemById();
        this.actions[5] = new MenuTracker.FindItemByName();
        this.actions[6] = new MenuTracker.Exit();
    }

    /**
     * type of reaction select.
     * @param key - numder parameter of reaction.
     */
    public  void select(int key) {
        this.actions[key - 1].execute(this.input, this.tracker);
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
    private static class AddItem implements UserAction {
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
            tracker.add(new Item(name, desc, 12345L));
        }

        /**
         * info about action for menu.
         * @return - string.
         */
        public String info() {
            return String.format("%s. %s", this.key(), "Add the new item.");
        }
    }

    /**
     * action class for show all.
     */
    private static class ShowItems implements UserAction {
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
            for (Item item : tracker.getAll()) {
                System.out.println(String.format("%s  %s  %s", item.getId(), item.getName(), item.getDesc()));
            }
        }

        /**
         * info about action for menu.
         * @return - string.
         */
        public String info() {
            return String.format("%s. %s", this.key(), "Show all items.");
        }
    }

    /**
     * action class for edit.
     */
    private static class EditItem implements UserAction {
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

        /**
         * info about action for menu.
         * @return - string.
         */
        public String info() {
            return String.format("%s. %s", this.key(), "Edit the item.");
        }
    }

    /**
     * action class for delete.
     */
    private static class DeleteItem implements UserAction {
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
            for (Item item : tracker.getAll()) {
                if (item != null && item.getId().equals(id)) {
                    System.out.println("Deleted: " + item.getId() + " " + item.getName() + " " + item.getDesc());
                }
            }
            tracker.delete(tracker.findById(id));

        }

        /**
         * info about action for menu.
         * @return - string.
         */
        public String info() {
            return String.format("%s. %s", this.key(), "Delete item.");
        }
    }

    /**
     * action class for find by id.
     */
    private static class FindItemById implements UserAction {
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
            for (Item item : tracker.getAll()) {
                if (item != null && item.getId().equals(id)) {
                    System.out.println(item.getId() + " " + item.getName() + " " + item.getDesc());
                }
            }

        }

        /**
         * info about action for menu.
         * @return - string.
         */
        public String info() {
            return String.format("%s. %s", this.key(), "Find item by id.");
        }
    }

    /**
     * action class for find by name.
     */
    private static class FindItemByName implements UserAction {
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
            if (tracker.findById(name) == null) {
                System.out.println("Tracker haven't items with this name");
                return;
            }
            for (Item item : tracker.getAll()) {
                if (item != null && item.getName().equals(name)) {
                    System.out.println(item.getId() + " " + item.getName() + " " + item.getDesc());
                }
            }

        }

        /**
         * info about action for menu.
         * @return - string.
         */
        public String info() {
            return String.format("%s. %s", this.key(), "Find item by name.");
        }
    }

    /**
     * action class for exit.
     */
    private static class Exit implements UserAction {
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

        /**
         * info about action for menu.
         * @return - string.
         */
        public String info() {
            return String.format("%s. %s", this.key(), "EXIT.");
        }
    }
}