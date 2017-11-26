package ru.job4j.tree;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TreeTest {
    @Test//(expected = NoSuchElementException.class)
    public void whenAddToTreeThenCorrectResult() {
        Tree<Integer> tree = new Tree<>(9);
        assertThat(tree.add(9, 10), is(true));
        assertThat(tree.add(9, 11), is(true));
        assertThat(tree.add(9, 12), is(true));
        assertThat(tree.add(9, 13), is(true));
        assertThat(tree.add(9, 14), is(true));
        assertThat(tree.add(10, 15), is(true));
        assertThat(tree.add(10, 16), is(true));
        assertThat(tree.add(11, 17), is(true));
        assertThat(tree.add(11, 18), is(true));
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
        System.out.println(tree.root.children);
        System.out.println(tree.root.children.get(0).children);
        System.out.println(tree.root.children.get(1).children);
        System.out.println(tree.root.children.get(2).children);
        System.out.println(tree.root.children.get(3).children);
        Tree.Node lop15 = tree.root.children.get(0).children.get(0);
        Tree.Node lop26 = (Tree.Node) lop15.children.get(0);
        System.out.println(lop26.children.get(0));
        Tree.Node lop17 = tree.root.children.get(1).children.get(0);
        System.out.println(lop17.children);
        System.out.println(tree.toList(tree.root));
        System.out.println(tree.totalListVal);
        Iterator iter = tree.iterator();
        System.out.println(iter.next());
        System.out.println(iter.next());
        System.out.println(iter.next());
        System.out.println(iter.next());
        System.out.println(iter.next());
        System.out.println(iter.next());
        System.out.println(iter.next());
        System.out.println(iter.next());
    }
}