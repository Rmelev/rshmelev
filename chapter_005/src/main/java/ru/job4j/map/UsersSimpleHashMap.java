package ru.job4j.map;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * user's hash map.
 * @param <K> - key.
 * @param <V> - value.
 */
public class UsersSimpleHashMap<K, V> implements Iterable<V> {
    /**
     * size of array for elements.
     */
    private int size;

    /**
     * getter.
     * @return - size.
     */
    public int getSize() {
        return this.size;
    }

    /**
     * array if elements.
     */
    private Node<K, V>[] table;

    /**
     * Constructor for user's hashmap.
     * @param size - start size of hashmap.
     */
    public UsersSimpleHashMap(int size) {
        this.size = size;
        this.table = new Node[size];
    }

    /**
     * Class of hashmap elements.
     * @param <K> - key.
     * @param <V> - value.
     */
    class Node<K, V> {
        /**
         * hash code of element.
         */
        private int hash;

        /**
         * getter.
         * @return - hash code of element.
         */
        public int getHash() {
            return this.hash;
        }

        /**
         * key of element.
         */
        private K key;

        /**
         * getter.
         * @return - key.
         */
        public K getKey() {
            return this.key;
        }

        /**
         * value of element.
         */
        private V value;

        /**
         * getter.
         * @return - value.
         */
        public V getValue() {
            return this.value;
        }

        /**
         * Constructor.
         * @param key - key.
         * @param value - value.
         */
        Node(K key, V value) {
            this.hash = hashFunction(key);
            this.key = key;
            this.value = value;
        }

        /**
         * @return - String representation of element.
         */
        @Override
        public String toString() {
            return key + "=" + value;
        }
    }

    /**
     * hash function on the key base.
     * @param key - key of element.
     * @param <K> - type parameter of key.
     * @return - hash code.
     */
    <K> int hashFunction(K key) {
        int hash = Objects.hashCode(key);
        return hash % table.length;
    }

    /**
     * add element to hash map.
     * @param key - key.
     * @param value - value.
     * @return - true, if successful.
     */
    boolean insert(K key, V value) {
        Node<K, V> newNode = new Node<>(key, value);
        boolean flag = table[newNode.getHash()] == null;
        if (flag) {
            table[newNode.getHash()] = newNode;
        } else {
            growSize();
            insert(key, value);
        }
        return flag;
    }

    /**
     * get element, using key.
     * @param key - key.
     * @return - value parameter of element by determined key.
     */
    V get(K key) {
        return table[hashFunction(key)] == null ? null : table[hashFunction(key)].getValue();
    }

    /**
     * delete element with specified key from hash map.
     * @param key - key.
     * @return - true, if successful.
     */
    boolean delete(K key) {
        table[hashFunction(key)] = null;
        return true;
    }

    /**
     * increase size of hash map, if collision.
     */
    public void growSize() {
        size *= 1.5;
        Node<K, V>[] tempTable = table;
        table = new Node[size];
        for (Node tempNode :  tempTable) {
            if (tempNode != null) {
                Node<K, V> newNode = new Node<>((K) tempNode.getKey(), (V) tempNode.getValue());
                insert(newNode.getKey(), newNode.getValue());
            }
        }
    }

    /**
     * overrided iterator.
     * @return
     */
    @Override
    public Iterator<V> iterator() {
        return new Iterator<V>() {
            private int iterCounter = 0;
            @Override
            public boolean hasNext() {
                boolean flag = false;
                for (int i = iterCounter; i < table.length; i++) {
                    if (table[i] != null) {
                        flag = true;
                        iterCounter = i;
                        return flag;
                    }
                }
                return flag;
            }

            @Override
            public V next() {
                if (hasNext()) {
                    return table[iterCounter++].getValue();
                } else {
                    throw new NoSuchElementException();
                }
            }
        };
    }

    /**
     * overridded toString() for hash map.
     * @return - String representation of hash map.
     */
    @Override
    public String toString() {

        for (int i = 0; i < size; i++) {
            if (table[i] != null) {
                System.out.print(table[i] + "  ");
            }
        }
        return "TheEnd";
    }
}
