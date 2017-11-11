package ru.job4j.generic;

/**
 * own array with generic type of elements.
 * @param <E> - generic type of elements.
 */
public class SimpleArray<E> {
    /**
     * array of Objects.
     */
    private Object[] objects;
    /**
     * counter of elements ib array.
     */
    private int index = 0;

    /**
     * Constructor.
     * @param size - size of array.
     */
    public SimpleArray(int size) {
        this.objects = new Object[size];
    }

    /**
     * add method.
     * @param elem - new element in array.
     */
    public void add(E elem) {
        this.objects[index++] = elem;
    }

    /**
     * get element by it's position.
     * @param position = position of element.
     * @return element.
     */
    public E get(int position) {
        return (E) this.objects[position];
    }

    /**
     * get index of element.
     * @param elem - element.
     * @return - index of element.
     */
    public int getIndex(E elem) {
        int indexOfElem = -1;
        for (int i = 0; i < objects.length; i++) {
            if (elem.equals((E) objects[i])) {
                indexOfElem = i;
            }
        }
        return indexOfElem;
    }

    /**
     * update of element.
     * @param elem - updated element.
     * @param newElem - new element.
     * @return - new element.
     */
    public E update(E elem, E newElem) {
        for (int i = 0; i < objects.length; i++) {
            if (elem.equals((E) objects[i])) {
                objects[i] = newElem;
            }
        }
        return newElem;
    }

    /**
     * delete method.
     * @param elem - deleted element.
     * @return - true if successful.
     */
    public boolean delete(E elem) {
        boolean flag = false;
        int del = getIndex(elem);
        for (int i = 0; i < objects.length; i++) {
            if (elem.equals((E) objects[i])) {
                flag = true;
            }
        }
        for (int i = del; i < objects.length - 1; i++) {
            objects[i] = objects[i + 1];
        }
        index--;
        return flag;
    }
}