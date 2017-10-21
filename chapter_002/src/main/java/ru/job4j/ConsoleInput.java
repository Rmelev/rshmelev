package ru.job4j;

import java.util.Scanner;

/**
 * class for use console.
 */
public class ConsoleInput implements Input {
    /**
     * scanner.
     */
    private Scanner scanner = new Scanner(System.in);

    /**
     * @param question - text message for user.
     * @return - user's answer.
     */
    public String ask(String question) {
        System.out.print(question);
        return scanner.nextLine();
    }

    /**
     * overriding method with catching exceptions.
     * @param question - question.
     * @param range - array of choices.
     * @return - user's choice.
     */
    public int ask(String question, int[] range) {
        int key = Integer.valueOf(this.ask(question));
        boolean exist = false;
        for (int value : range) {
            if (value == key) {
                exist = true;
                break;
            }
        }
        if (exist) {
            return key;
        } else {
            throw new MenuOutException("Out of menu range. ");
        }
    }
}