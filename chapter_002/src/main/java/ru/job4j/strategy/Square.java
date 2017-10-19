package ru.job4j.strategy;

/**
 * class with 1 method, displaying square.
 */
public class Square implements Shape {
    /**
     * method, drawing square.
     * @return - string, displaying square.
     */
    public String pic() {
        return "#######\n#######\n#######\n#######\n#######\n";
    }
}