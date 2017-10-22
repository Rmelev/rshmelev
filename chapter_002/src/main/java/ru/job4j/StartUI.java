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
     * tracker.
     */
    private Tracker tracker;

    /**
     * @param input - input.
     * @param tracker - tracker.
     */
    public StartUI(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * method for run program.
     */
    public void init() {
        MenuTracker menu = new MenuTracker(this.input, tracker);
        int[] ranges = menu.initializer();
        do {
            menu.show();
            int key = input.ask("Select: ", ranges);
            if (key == 7) {
                return;
            }
            menu.select(key);
        } while (true);
    }

    /**
     * main method.
     * @param args - args.
     */
    public static void main(String[] args) {
        Input input = new ValidateInput();
        Tracker tracker = new Tracker();
        new StartUI(input, tracker).init();
    }
}