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
     *
     * @param question - text message for user.
     * @return - user's answer.
     */
    public String ask(String question) {
        System.out.print(question);
        return scanner.nextLine();
    }
}