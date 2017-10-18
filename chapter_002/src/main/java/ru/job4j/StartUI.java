package ru.job4j;

public class StartUI {
    private Input input;

    public StartUI(Input input) {
        this.input = input;
    }
    Tracker tracker = new Tracker();
    private String idSwitch = null;
    private String nameSwitch = null;
    private String descSwitch = null;
    String name = null;

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

    public void addItem() {
        nameSwitch = input.ask("Input user name: ");
        descSwitch = input.ask("Input item description: ");
        Item itemSwitch = new Item(nameSwitch, descSwitch, 1234L);
        tracker.add(itemSwitch);
        System.out.print("Added item:   ");
        System.out.println(itemSwitch.getId() + " " + itemSwitch.getName() + " " + itemSwitch.getDesc());
    }

    public void showAllItems() {
        for (Item item : tracker.getAll()) {
            if (item != null) {
                System.out.print(item.getId() + " " + item.getName() + " " + item.getDesc());
                System.out.println();
            }
        }
    }

    public void updateItem() {
        idSwitch = input.ask("Input id of the item to update: ");
        Item returnItem = tracker.findById(idSwitch);
        nameSwitch = input.ask("Input new user name: ");
        returnItem.setName(nameSwitch);
        descSwitch = input.ask("Input new item description: ");
        returnItem.setDesc(descSwitch);
        tracker.update(returnItem);
        System.out.println("Done");
    }

    public void deleteItem() {
        idSwitch = input.ask("Input id of the item to remove: ");
        tracker.delete(tracker.findById(idSwitch));
        System.out.println("Done");
    }

    public void findItemById() {
        idSwitch = input.ask("Input id of the item to find: ");
        for (Item item : tracker.getAll()) {
            if (item != null && item.getId().equals(idSwitch)) {
                System.out.println(item.getId() + " " + item.getName() + " " + item.getDesc());
            }
        }
    }

    public void findItemByName() {
        nameSwitch = input.ask("Input user name to search for related items: ");
        for (Item item : tracker.getAll()) {
            if (item != null && item.getName().equals(nameSwitch)) {
                System.out.println(item.getId() + " " + item.getName() + " " + item.getDesc());
            }
        }
    }

    public void init() {
        while (true) {
            name = input.ask("Select: ");
            switch (name) {
                case "0":
                    this.addItem();
                    this.menuPrint();
                    break;
                case "1": // get all items
                    this.showAllItems();
                    this.menuPrint();
                    break;
                case "2": // Update item
                    System.out.println();
                    System.out.println("List of items to choose id below:");
                    this.showAllItems();
                    this.updateItem();
                    this.menuPrint();
                    break;
                case "3": // Delete item
                    System.out.println();
                    System.out.println("List of items to choose id below:");
                    this.showAllItems();
                    this.deleteItem();
                    this.menuPrint();
                    break;
                case "4": // find item by id
                    this.findItemById();
                    this.menuPrint();
                    break;
                case "5": // find by name
                    this.findItemByName();
                    this.menuPrint();
                    break;
                case "6" :
                    System.out.println("You decided to quit");
                    return;
                default:
                    System.out.println("Please, choose number from 1 to 6");
                    break;
            }
        }

    }

    public static void main(String[] args) {
        Input input = new ConsoleInput();
        StartUI startUI = new StartUI(input);
        startUI.menuPrint();
        startUI.init();
    }
}