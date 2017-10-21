package ru.job4j;

/**
 * class for use JUnit testing.
 */
public class StubInput implements Input {
    /**
     * array of auto-answers.
     */
    private String[] answers;
    /**
     * position of array, where we take next answer.
     */
    private int position = 0;

    /**
     *
     * @param answers - array of auto-answers.
     */
    public StubInput(String[] answers) {
        this.answers = answers;
    }

    /**
     *
     * @param question - text message for inquiry more information.
     * @return - answers in order.
     */
    public String ask(String question) {
        return answers[position++];
    }

    /**
     * @param question - question.
     * @param range - array of choice.
     * @return - stub.
     */
    public int ask(String question, int[] range) {
        return Integer.valueOf(answers[position++]);
    }
}