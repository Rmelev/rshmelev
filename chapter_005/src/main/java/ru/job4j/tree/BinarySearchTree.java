package ru.job4j.tree;

/**
 * BST.
 * @param <E> -  - type parameter.
 */
public class BinarySearchTree<E extends Comparable<E>> {
    /**
     * root of tree.
     */
    private Node<E> root;

    /**
     * getter.
     * @return - root.
     */
    public Node<E> getRoot() {
        return this.root;
    }

    /**
     * current node, then we look for place to add.
     */
    private Node<E> currentNode;

    /**
     * true, if place to add is node left child.
     */
    private boolean leftFlag = false;

    /**
     * true, if place to add is node right child.
     */
    private boolean rightFlag = false;

    /**
     * BST Constructor.
     * @param value - root value.
     */
    public BinarySearchTree(E value) {
        root = new Node(value);
        currentNode = root;
    }

    /**
     * Class of elements.
     * @param <V> - type parameter of element.
     */
    class Node<V> {
        /**
         * left child.
         */
        private Node<V> left;

        /**
         * @return - left child.
         */
        public Node<V> getLeft() {
            return left;
        }

        /**
         * right child.
         */
        private Node<V> right;

        /**
         * @return - right child.
         */
        public Node<V> getRight() {
            return right;
        }

        /**
         * value of node.
         */
        private V value;

        /**
         * Constructor.
         * @param value - value.
         */
        Node(V value) {
            this.value = value;
        }

        /**
         * @return - value.
         */
        public V getValue() {
            return value;
        }
    }

    /**
     * add element in BST.
     * @param e - element.
     * @return - true, if successful.
     */
    boolean add(E e) {
        Node<E> nodeToAdd = compareToChoice(root, e);
        if (leftFlag) {
            nodeToAdd.left = new Node<>(e);
            leftFlag = false;
        }

        if (rightFlag) {
            nodeToAdd.right = new Node<>(e);
            rightFlag = false;
        }

        return true;
    }

    /**
     * Compare added element with current node for decide place to add.
     * @param node - start node to compare.
     * @param e - added element.
     * @return - node, where we must add.
     */
    Node<E> compareToChoice(Node<E> node, E e) {
        if (node.value.compareTo(e) < 0) {
            if (node.right == null) {
                rightFlag = true;
                return node;
            } else {
                currentNode = compareToChoice(node.right, e);
            }
        } else {
            if (node.left == null) {
                leftFlag = true;
                return node;
            } else {
                currentNode = compareToChoice(node.left, e);
            }
        }
        return currentNode;
    }

    /**
     * override for visibility input.
     * @return - String representation.
     */
    @Override
    public String toString() {
        treePrint(root);
        return "Thank you!";
    }

    /**
     * visit and print all elements in tree.
     * @param node - start node.
     */
    private void treePrint(Node<E> node) {
        System.out.print(node.value + "  ");
        if (node.left != null) {
            treePrint(node.left);
        }
        if (node.right != null) {
            treePrint(node.right);
        }
    }
}
