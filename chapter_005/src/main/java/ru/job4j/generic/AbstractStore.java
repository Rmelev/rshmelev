package ru.job4j.generic;

/**
 * common class for different stores.
 * @param <T> - type of store elements.
 */
abstract class AbstractStore<T extends Base> implements Store<T> {
    /**
     * store SimpleArray type.
     */
    private SimpleArray<T> store = new SimpleArray<>(10);
    /**
     * counter of current number of elements.
     */
    private int index = 0;

    /**
     * getter.
     * @return - index.
     */
    public int getIndex() {
        return index;
    }

    /**
     * add element.
     * @param elem - added element.
     * @return - added element.
     */
    public T add(T elem) {
        store.getObjects()[index++] = elem;
        return elem;
    }

    /**
     * update element.
     * @param model - updating element.
     * @return - updated element.
     */
    public T update(T model) {
        T returnElem = null;
        for (int i = 0; i < store.getObjects().length; i++) {
            T elem = (T) store.getObjects()[i];
            if (elem != null && elem.getId().equals(model.getId())) {
                elem.setId("Stub");
                returnElem = elem;
                break;
            }
        }
        return returnElem;
    }

    /**
     * delete element.
     * @param id - id of deleted element.
     * @return - true, if successful.
     */
    public boolean delete(String id) {
        boolean flag = false;
        int del = -1;
        for (int i = 0; i < store.getObjects().length; i++) {
            T elem = (T) store.getObjects()[i];
            if (elem != null && elem.getId().equals(id)) {
                flag = true;
                del = i;
                break;
            }
        }
        for (int i = del; i < store.getObjects().length - 1; i++) {
            store.getObjects()[i] = store.getObjects()[i + 1];
        }
        index--;
        return flag;
    }

    /**
     * only for store Test.
     * @param index - concrete position of element in array.
     * @return - id of concrete element of store.
     */
    public String getEl(int index) {
        return store.get(index).getId();
    }
}