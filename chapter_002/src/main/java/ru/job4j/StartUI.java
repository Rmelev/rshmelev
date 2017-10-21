package ru.job4j;

/**
 * run programm.
 */
public class StartUI {
    /**
     * input.
     */
    private Input input;

    /**
     * @param input - input.
     */
    public StartUI(Input input) {
        this.input = input;
    }

    /**
     * method for run program.
     */
    public void init() {
        Tracker tracker = new Tracker();
        MenuTracker menu = new MenuTracker(this.input, tracker);
        menu.fillActions();
        do {
            menu.show();
            int key = Integer.valueOf(input.ask("Select: "));
            if (key != 1 && key != 2 && key != 3 && key != 4 && key != 5 && key != 6 && key != 7) {
                System.out.println("Please, enter number from 1 to 7");
                continue;
            }
            if (key == 7) {
                return;
            }
            menu.select(key);
        } while (!"y".equals(input.ask("Exit?(y):  ")));
    }

    /**
     * main method.
     * @param args - args.
     */
    public static void main(String[] args) {
        ConsoleInput input = new ConsoleInput();
        StartUI start = new StartUI(input);
        start.init();
    }
}