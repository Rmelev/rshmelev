package ru.job4j.speed;

import java.util.*;

class CollAddDel {
    /**
     * ArrayList.
     */
    ArrayList<String> arrArray = new ArrayList<>();
    /**
     * LinkedList.
     */
    LinkedList<String> arrLinked = new LinkedList<>();
    /**
     * TreeSet.
     */
    TreeSet<String> arrTree = new TreeSet<>();
    //ArrayList<String> toDelete = new ArrayList<>();
    Set<String> jknf;

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
        System.out.println(String.format("Время метода add:  %, d", test.add(test.arrArray, 20000000)));
        System.out.println(String.format("Время метода del:  %, d", test.add(test.arrArray, 1400000)));
        //System.out.println(String.format("Время метода add:  %, d", test.add(test.arrLinked, 20000000)));
        //System.out.println(String.format("Время метода del:  %, d", test.add(test.arrLinked, 1400000)));
        //System.out.println(String.format("Время метода add:  %, d", test.add(test.arrTree, 20000000)));
        //System.out.println(String.format("Время метода del:  %, d", test.add(test.arrTree, 1400000)));
    }
}