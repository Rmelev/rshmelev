package ru.job4j.generic;

/**
 * interface of stores.
 * @param <T>
 */
public interface Store<T extends Base> {
    /**
     * add element.
     * @param model - element.
     * @return - added element.
     */
    T add(T model);

    /**
     * update element.
     * @param model - updating element.
     * @return - updated element.
     */
    T update(T model);

    /**
     * delete element.
     * @param id - id of deleted element.
     * @return - true, if successful.
     */
    boolean delete(String id);

    /**
     * method only for testing code.
     * @param index - index  of element in array.
     * @return - String id meaning.
     */
    String getEl(int index);
}