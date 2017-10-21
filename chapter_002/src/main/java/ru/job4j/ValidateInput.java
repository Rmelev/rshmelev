package ru.job4j;

/**
 * handling errors.
 */
public class ValidateInput extends ConsoleInput {

    /**
     * check user's input.
     * @param question - question.
     * @param range - array of choice.
     * @return - user's choice.
     */
    public int ask(String question, int[] range) {
        boolean invalid = true;
        int value = -1;
        do {
            try {
                value = super.ask(question, range);
                invalid = false;
            } catch (MenuOutException moe) {
                System.out.println("Please, select key from menu. ");
            } catch (NumberFormatException nfe) {
                System.out.println("Please, enter validate data again. ");
            }
        } while (invalid);
        return value;
    }
}