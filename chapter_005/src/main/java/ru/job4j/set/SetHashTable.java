package ru.job4j.set;

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
        String temp = elem.toString();
        char[] chars = temp.toCharArray();
        int sum = 0;
        for (char charsElem : chars) {
            sum += charsElem;
        }
        return sum % arrsize;
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
        objects[hashFunction(e)] = e;
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
        for (int i = 0; i < arrsize; i++) {
            E temp = (E) objects[i];
            if (temp != null && temp.equals(e)) {
                flag = true;
                findInex = i;
            }
        }
        return flag;
    }
}
