package ru.job4j.tree;

import org.junit.Test;
import ru.job4j.bank.User;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * test class for user's tree.
 */
public class TreeTest {
    /**
     * test1.
     */
    @Test//(expected = NoSuchElementException.class)
    public void whenAddToTreeThenCorrectResult() {
        Tree<Integer> tree = new Tree<>(9);
        assertThat(tree.add(8, 10), is(false));
        assertThat(tree.add(80, 100), is(false));
        assertThat(tree.add(9, 10), is(true));
        assertThat(tree.add(9, 10), is(false));
        assertThat(tree.add(9, 12), is(true));
        assertThat(tree.add(9, 13), is(true));
        assertThat(tree.add(9, 14), is(true));
        assertThat(tree.add(10, 15), is(true));
        assertThat(tree.add(10, 16), is(true));
        assertThat(tree.add(9, 11), is(true));
        assertThat(tree.add(11, 17), is(true));
        assertThat(tree.add(11, 17), is(false));
        assertThat(tree.add(11, 18), is(true));
        assertThat(tree.add(11, 18), is(false));
        assertThat(tree.add(11, 19), is(true));
        assertThat(tree.add(12, 20), is(true));
        assertThat(tree.add(12, 21), is(true));
        assertThat(tree.add(13, 22), is(true));
        System.out.println("Ниже этого реальность");
        assertThat(tree.add(15, 26), is(true));
        assertThat(tree.add(15, 27), is(true));
        assertThat(tree.add(15, 28), is(true));
        assertThat(tree.add(17, 29), is(true));
        assertThat(tree.add(17, 30), is(true));
        assertThat(tree.add(17, 31), is(true));
        assertThat(tree.add(26, 32), is(true));
        assertThat(tree.add(31, 47), is(true));
        System.out.println("root.children" + tree.getRoot().getChildren());
        System.out.println(tree.getRoot().getChildren().get(0).getChildren());
        System.out.println(tree.getRoot().getChildren().get(1).getChildren());
        System.out.println(tree.getRoot().getChildren().get(2).getChildren());
        System.out.println(tree.getRoot().getChildren().get(4).getChildren() + "control");
        Tree.Node lop15 = tree.getRoot().getChildren().get(0).getChildren().get(0);
        Tree.Node lop26 = (Tree.Node) lop15.getChildren().get(0);
        System.out.println(lop26.getChildren().get(0));
        Tree.Node lop17 = tree.getRoot().getChildren().get(4).getChildren().get(0);
        System.out.println(lop17.getChildren());
        //System.out.println("tree.toList(tree.root)" + tree.toList(tree.root));
        Iterator iter = tree.iterator();
        System.out.println(iter.next() + "  " + iter.next() + "  " + iter.next() + "  " + iter.next() + "  " + iter.next()
                + "  " + iter.next() + "  " + iter.next() + "  " + iter.next());
        iter.next(); iter.next(); iter.next(); iter.next(); iter.next(); iter.next(); iter.next(); iter.next();
        iter.next(); iter.next(); iter.next(); iter.next(); iter.next();
        assertThat(iter.next(), is(47));
        assertThat(iter.hasNext(), is(false));
        List<Integer> testResult = new ArrayList<>(Arrays.asList(9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 26, 27, 28, 29, 30, 31, 32, 47));
        assertThat(tree.getAdded(), is(testResult));
        System.out.println(tree.getAdded());
        //iter.next();
    }
    /**
     * test2.
     */
    @Test//(expected = NoSuchElementException.class)
    public void whenStringAddToTreeThenCorrectResult() {
        Tree<String> tree = new Tree<>("aaaa");
        tree.add("aaaa", "cccc");
        tree.add("aaaa", "ffff");
        tree.add("cccc", "dddd");
        tree.add("ffff", "eeee");
        tree.add("eeee", "bbbb");
        Iterator iter = tree.iterator();
        while (iter.hasNext()) {
            System.out.print(iter.next() + "  ");
        }
    }

    /**
     * test3.
     */
    @Test
    public void whenIsBinaryIntegerThenTrueAndConversely() {
        Tree<Integer> tree = new Tree<>(9);
        tree.add(9, 10);
        tree.add(9, 11);
        //tree.add(9, 19);
        tree.add(10, 12);
        tree.add(10, 13);
        tree.add(11, 14);
        tree.add(11, 15);
        tree.add(12, 16);
        tree.add(12, 17);
        //tree.add(11, 18);
        assertThat(tree.isBinary(), is(true));
        tree.add(17, 20);
        assertThat(tree.isBinary(), is(true));
        tree.add(11, 19);
        assertThat(tree.isBinary(), is(false));
    }

    /**
     * test4.
     */
    @Test
    public void whenIsBinaryStringThenTrueAndConversely() {
        User userJohn = new User("John", 11111);
        User userPaul = new User("Paul", 22222);
        User userTony = new User("Tony", 33333);
        User userBrad = new User("Brad", 44444);
        User userMicky = new User("Micky", 55555);
        User userClaudia = new User("Claudia", 66666);
        Tree<User> tree = new Tree<>(userJohn);
        tree.add(userJohn, userPaul);
        tree.add(userJohn, userTony);
        tree.add(userTony, userBrad);
        tree.add(userTony, userMicky);
        assertThat(tree.isBinary(), is(true));
        tree.add(userTony, userClaudia);
        assertThat(tree.isBinary(), is(false));
    }

}