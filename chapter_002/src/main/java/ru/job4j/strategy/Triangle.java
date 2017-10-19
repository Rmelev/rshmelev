package ru.job4j.strategy;

/**
 * class with 1 method, displaying triangle.
 */
public class Triangle implements Shape {
    /**
     * method, drawing triangle.
     * @return - string, displaying triangle.
     */
    public String pic() {
        return "   #   \n  ###  \n ##### \n#######\n";
    }
}