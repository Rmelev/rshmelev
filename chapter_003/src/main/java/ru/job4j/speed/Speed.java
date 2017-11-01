package ru.job4j.speed;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.TreeSet;
import java.util.Collection;
import java.util.Iterator;

/**
 * class for add & del elems from collections.
 */
class CollAddDel {
    /**
     * ArrayList.
     */
    private ArrayList<String> arrArray = new ArrayList<>();
    /**
     * LinkedList.
     */
    private LinkedList<String> arrLinked = new LinkedList<>();
    /**
     * TreeSet.
     */
    private TreeSet<String> arrTree = new TreeSet<>();
    //ArrayList<String> toDelete = new ArrayList<>();

    /**
     * getter.
     * @return - arrArray.
     */
    public ArrayList<String> getArrArray() {
        return arrArray;
    }

    /**
     * getter.
     * @return - arrLinked.
     */
    public LinkedList<String> getArrLinked() {
        return arrLinked;
    }

    /**
     * getter.
     * @return - arrTree.
     */
    public TreeSet<String> getArrTree() {
        return arrTree;
    }

    /**
     * add element.
     * @param collection - collection for add.
     * @param amount - number of added elements.
     * @return - formal.
     */
    public long add(Collection<String> collection, int amount) {
        long start = System.nanoTime();
        for (int i = 0; i < amount; i++) {
            String randomString = Double.toString(System.currentTimeMillis());
            //if (i > 0 && i < 700000) {
            //   toDelete.add(randomString);
            //}
            collection.add(randomString);
        }
        long end = System.nanoTime();
        return end - start;
    }

    /**
     * add element.
     * @param collection - collection for delete some items.
     * @param amount - numder of deleted elements.
     * @return - formal.
     */
    public long delete(Collection<String> collection, int amount) {
        long start = System.nanoTime();
        Iterator iterator = collection.iterator();
        for (int i = 0; i < amount && iterator.hasNext(); i++) {
            iterator.next();
            iterator.remove();
        }
        //for (int i = 0; i < toDelete.size(); i++) {
        //    collection.remove(toDelete.get(i));
        //}
        //return System.currentTimeMillis();
        long end = System.nanoTime();
        return end - start;
    }
}

/**
 * class for add & delete elements in collections.
 */
public class Speed {
    /**
     * main.
     * @param args - no args.
     */
    public static void main(String[] args) {
        CollAddDel test = new CollAddDel();
        System.out.println(String.format("Время метода add:  %, d", test.add(test.getArrArray(), 20000000)));
        System.out.println(String.format("Время метода del:  %, d", test.add(test.getArrArray(), 1400000)));
        //System.out.println(String.format("Время метода add:  %, d", test.add(test.arrLinked, 20000000)));
        //System.out.println(String.format("Время метода del:  %, d", test.add(test.arrLinked, 1400000)));
        //System.out.println(String.format("Время метода add:  %, d", test.add(test.arrTree, 20000000)));
        //System.out.println(String.format("Время метода del:  %, d", test.add(test.arrTree, 1400000)));
    }
}