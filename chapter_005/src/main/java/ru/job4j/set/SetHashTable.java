package ru.job4j.set;

import java.util.Objects;

/**
 * set on hash table base.
 * @param <E>
 */
public class SetHashTable<E> {
    /**
     * size of base array.
     */
    private int arrsize;

    /**
     * getter.
     * @return - arrsize.
     */
    public int getArrsize() {
        return this.arrsize;
    }

    /**
     * remember index of element, which we find in findElem().
     */
    private int findInex;

    /**
     * array of elements.
     */
    private Object[] objects;

    /**
     * getter.
     * @return - objects.
     */
    public Object[] getObjects() {
        return this.objects;
    }

    /**
     * Constructor.
     * @param size - size of array for initialize.
     */
    public SetHashTable(int size) {
        arrsize = size;
        this.objects = new Object[arrsize];
    }

    /**
     * create index(key) for add elements to array.
     * @param elem - elem for which we create index(key).
     * @return - index(key).
     */
    int hashFunction(E elem) {
        int hash = Objects.hashCode(elem);
        return hash % arrsize;
    }

    /**
     * @param e - added element.
     * @return - true, if success.
     */
    boolean add(E e) {
        boolean flag = false;
        if (findElem(e)) {
            return flag;
        }
        flag = true;
        int key = hashFunction(e);
        if (objects[key] != null) {
            growSize();
            add(e);
        } else {
            objects[key] = e;
        }
        return flag;
    }

    /**
     * check presence element in set.
     * @param e - wanted element.
     * @return - true, if success.
     */
    boolean contains(E e) {
        boolean flag = false;
        if (findElem(e)) {
            flag = true;
        }
        return flag;
    }

    /**
     * delete element.
     * @param e - deleted element.
     * @return - true, if success.
     */
    boolean remove(E e) {
        boolean flag = false;
        if (findElem(e)) {
            objects[findInex] = null;
            flag = true;
        }
        return flag;
    }

    /**
     * find wanted element.
     * @param e - wanted element.
     * @return - true, if success.
     */
    boolean findElem(E e) {
        boolean flag = false;
        int tempIndex = hashFunction(e);
        if (objects[tempIndex] != null && objects[tempIndex].equals(e)) {
            flag = true;
            findInex = tempIndex;
        }
        return flag;
    }

    /**
     * increase size of array, if array cell is occupied.
     */
    public void growSize() {
        arrsize *= 1.5;
        Object[] tempTable = objects;
        objects = new Object[arrsize];
        for (Object tempElem :  tempTable) {
            if (tempElem != null) {
                add((E) tempElem);
            }
        }
    }
}
