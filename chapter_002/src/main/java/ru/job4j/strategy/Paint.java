package ru.job4j.strategy;

/**
 * class for paint figures with common interface.
 */
public class Paint {
    /**
     * @param shape - objects implement interface Shape.
     * @return - string, depending on object, which called method.
     */
    public String draw(Shape shape) {
        return shape.pic();
    }
}