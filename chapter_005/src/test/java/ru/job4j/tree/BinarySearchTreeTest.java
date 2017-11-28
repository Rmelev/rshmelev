package ru.job4j.tree;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test binary search tree.
 */
public class BinarySearchTreeTest {
    /**
     * Test1.
     */
    @Test
    public void whenBSTThenResult() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>(7);
        bst.add(9);
        bst.add(6);
        bst.add(4);
        bst.add(5);
        bst.add(11);
        bst.add(8);
        bst.add(3);
        bst.add(17);
        bst.add(21);
        bst.add(10);
        assertThat(bst.getRoot().getValue(), is(7));
        assertThat(bst.getRoot().getLeft().getValue(), is(6));
        assertThat(bst.getRoot().getRight().getValue(), is(9));
        assertThat(bst.getRoot().getLeft().getLeft().getValue(), is(4));
        assertThat(bst.getRoot().getLeft().getLeft().getRight().getValue(), is(5));
        assertThat(bst.getRoot().getRight().getRight().getValue(), is(11));
        assertThat(bst.getRoot().getRight().getLeft().getValue(), is(8));
        assertThat(bst.getRoot().getLeft().getLeft().getLeft().getValue(), is(3));
        System.out.println(bst);
    }

    /**
     * test2. Duplicate elements.
     */
    @Test
    public void whenAddEqualsElementsThenCorrectResult() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>(12);
        bst.add(9);
        bst.add(17);
        bst.add(9);
        bst.add(8);
        bst.add(6);
        bst.add(11);
        bst.add(21);
        bst.add(13);
        assertThat(bst.getRoot().getLeft().getValue(), is(9));
        assertThat(bst.getRoot().getLeft().getLeft().getValue(), is(9));
        assertThat(bst.getRoot().getLeft().getLeft().getLeft().getValue(), is(8));
        System.out.println(bst);
    }

    /**
     * Test3. For check productivity.
     */
    @Test
    public void whenAutoFillTreeThenCorrectResult() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>(500);
        int i = 0;
        while (i < 10000) {
            bst.add((int) (Math.random() * 10000));
            i++;
        }
    }
}