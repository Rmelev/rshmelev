package ru.job4j;

/**
 * class for interaction user with programm.
 */
public class StartUI {
    /**
     * variable for interaction with input system.
     */
    private Input input;

    /**
     * Constractor for JUnit testing.
     * @param input - Input object.
     * @param tracker - Tracker object.
     */
    public StartUI(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }
    /**
     * new Tracker object.
     */
    private Tracker tracker = new Tracker();

    /**
     * user' menu.
     */
    public void menuPrint() {
        System.out.println();
        System.out.println("0. Add new Item");
        System.out.println("1. Show all items");
        System.out.println("2. Edit item");
        System.out.println("3. Delete item");
        System.out.println("4. Find item by Id");
        System.out.println("5. Find items by name");
        System.out.println("6. Exit Program");
    }

    /**
     *
     * @param tracker - tracker for adding items.
     */
    public void addItem(Tracker tracker) {
        Item itemSwitch = new Item(input.ask("Input user name: "), input.ask("Input item description: "), 1234L);
        tracker.add(itemSwitch);
    }

    /**
     * method for show all items.
     */
    public void showAllItems() {
        for (Item item : tracker.getAll()) {
            if (item != null) {
                System.out.print(item.getId() + " " + item.getName() + " " + item.getDesc());
                System.out.println();
            }
        }
    }

    /**
     * method for update item.
     */
    public void updateItem() {
        //idSwitch = input.ask("Input id of the item to update: ");
        Item returnItem = tracker.findById(input.ask("Input id of the item to update: "));
        returnItem.setName(input.ask("Input new user name: "));
        returnItem.setDesc(input.ask("Input new item description: "));
        tracker.update(returnItem);
    }

    /**
     * method for delete item.
     */
    public void deleteItem() {
        String idSwitch = input.ask("Input id of the item to remove: ");
        if (tracker.findById(idSwitch) == null) {
            System.out.println("Tracker haven't this item");
            return;
        }
        tracker.delete(tracker.findById(idSwitch));
    }

    /**
     * method for find item, using id.
     */
    public void findItemById() {
        //idSwitch = input.ask("Input id of the item to find: ");
        for (Item item : tracker.getAll()) {
            if (item != null && item.getId().equals(input.ask("Input id of the item to find: "))) {
                System.out.println(item.getId() + " " + item.getName() + " " + item.getDesc());
            }
        }
    }

    /**
     * method for find item, using user's name.
     */
    public void findItemByName() {
        //nameSwitch = input.ask("Input user name to search for related items: ");
        for (Item item : tracker.getAll()) {
            if (item != null && item.getName().equals(input.ask("Input user name to search for related items: "))) {
                System.out.println(item.getId() + " " + item.getName() + " " + item.getDesc());
            }
        }
    }

    /**
     * method to execute user's choice.
     */
    public void init() {
        while (true) {
            switch (input.ask("Select: ")) {
                case "0":
                    this.addItem(tracker);
                    //this.menuPrint();
                    break;
                case "1": // get all items
                    this.showAllItems();
                    //this.menuPrint();
                    break;
                case "2": // Update item
                    //System.out.println();
                    //System.out.println("List of items to choose id below:");
                    //this.showAllItems();
                    this.updateItem();
                    //this.menuPrint();
                    break;
                case "3": // Delete item
                    //System.out.println();
                    //System.out.println("List of items to choose id below:");
                    //this.showAllItems();
                    this.deleteItem();
                    //this.menuPrint();
                    break;
                case "4": // find item by id
                    this.findItemById();
                    //this.menuPrint();
                    break;
                case "5": // find by name
                    this.findItemByName();
                    //this.menuPrint();
                    break;
                case "6" :
                    //System.out.println("You decided to quit");
                    return;
                default:
                    System.out.println("Please, choose number from 1 to 6");
                    break;
            }
        }

    }

    /**
     * main().
     * @param args - arguments from console.
     */
    public static void main(String[] args) {
        Input input = new ConsoleInput();
        StartUI startUI = new StartUI(input, new Tracker());
        startUI.menuPrint();
        startUI.init();
    }
}