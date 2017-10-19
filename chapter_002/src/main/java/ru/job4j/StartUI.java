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
     * variable for reading users id input.
     */
    private String idSwitch;
    /**
     * variable for reading users name input.
     */
    private String nameSwitch;
    /**
     * variable for reading users description input.
     */
    private String descSwitch;
    /**
     * user's choice.
     */
    private String name;

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
        nameSwitch = input.ask("Input user name: ");
        descSwitch = input.ask("Input item description: ");
        Item itemSwitch = new Item(nameSwitch, descSwitch, 1234L);
        tracker.add(itemSwitch);
        //System.out.print("Added item:   ");
        //System.out.println(itemSwitch.getId() + " " + itemSwitch.getName() + " " + itemSwitch.getDesc());
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
        idSwitch = input.ask("Input id of the item to update: ");
        Item returnItem = tracker.findById(idSwitch);
        nameSwitch = input.ask("Input new user name: ");
        returnItem.setName(nameSwitch);
        descSwitch = input.ask("Input new item description: ");
        returnItem.setDesc(descSwitch);
        tracker.update(returnItem);
        //System.out.println("Done");
    }

    /**
     * method for delete item.
     */
    public void deleteItem() {
        idSwitch = input.ask("Input id of the item to remove: ");
        if (tracker.findById(idSwitch) == null) {
            System.out.println("Tracker haven't this item");
            return;
        }
        tracker.delete(tracker.findById(idSwitch));
        //System.out.println("Done");
    }

    /**
     * method for find item, using id.
     */
    public void findItemById() {
        idSwitch = input.ask("Input id of the item to find: ");
        for (Item item : tracker.getAll()) {
            if (item != null && item.getId().equals(idSwitch)) {
                System.out.println(item.getId() + " " + item.getName() + " " + item.getDesc());
            }
        }
    }

    /**
     * method for find item, using user's name.
     */
    public void findItemByName() {
        nameSwitch = input.ask("Input user name to search for related items: ");
        for (Item item : tracker.getAll()) {
            if (item != null && item.getName().equals(nameSwitch)) {
                System.out.println(item.getId() + " " + item.getName() + " " + item.getDesc());
            }
        }
    }

    /**
     * method to execute user's choice.
     */
    public void init() {
        while (true) {
            name = input.ask("Select: ");
            switch (name) {
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