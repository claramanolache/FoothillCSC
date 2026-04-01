package lazyTrees;
import java.util.*;

public class LazySearchTree<E extends Comparable< ? super E > >
        implements Cloneable
{
    int mSizeHard; // number of nodes including deleted ones
    protected int mSize; // number of undeleted nodes
    protected LazySTNode mRoot; // first node of the tree, we will use to start recursion

    /**
     * Default constructor, start with empty tree
     */
    public LazySearchTree()
    {
        clear();
    }

    /**
     * Checks if the tree is empty
     * @return true if empty, false otherwise
     */
    public boolean empty() {
        return (mSize == 0);
    }

    /**
     * Returns the soft size of the tree (number of undeleted nodes)
     * @return Returns the int soft size of the tree
     */
    public int size() {
        return mSize;
    }

    /**
     * Returns the hard size of the tree (number of total nodes)
     * @return Returns the int hard size of the tree
     */
    public int sizeHard(){
        return mSizeHard;
    }

    /**
     * Clears the tree, sets size to 0 and root to null. Hard size remains
     */
    public void clear() {
        mSize = 0; // only soft size reset
        mRoot = null; // if root set to null, no way of getting to rest of tree
    }

    /**
     * Public contains method called by users outside the class
     * @param x element to search for
     * @return true if found, false otherwise
     */
    public boolean contains(E x) {
        return contains(mRoot, x);
    }

    /**
     * Uses Traverser to check for element, private helper method
     * @param root of tree we currently at
     * @param x element to search for
     * @return true if found, false otherwise
     */
    private boolean contains(LazySTNode root, E x) {
        if (root == null) { // null check
            return false;
        }
        // uses inner class to traverse tree, implementation of traverser
        class Finder extends Traverser<E> {
            private boolean found = false; // flag to indicate if found
            /**
             * Visit method called during traversal, compares current node to x
             * sets found to true if match
             * @param node current node being visited
             */
            @Override
            public void visit(E node) {
                if (node.compareTo(x) == 0) { // uses compareTo to check for equality
                    found = true;
                }
            }
            /**
             * Getter for found flag
             * @return true if found, false otherwise
             */
            public boolean isFound() {
                return found;
            }
        }
        // create instance of inner class and traverse tree
        Finder find = new Finder();
        traverse(find); // uses soft traversal to exclude deleted nodes
        return find.isFound(); // return result
    }

    /**
     * Public find method called by users outside the class
     * @param x element to search for
     * @return the element if found, null otherwise
     */
    public E find(E x) {
        return find(mRoot, x);
    }

    /**
     * private helper method to find element using Traverser
     * @param root of tree we currently at
     * @param x element to search for
     * @return the element if found, null otherwise
     */
    private E find(LazySTNode root, E x) {
        if (root == null) { // null check
            return null;
        }
        // Inner class to traverse tree and find element
        class Finder extends Traverser<E> {
            private E foundNode = null; // variable to store found node
            // same methode as contains but stores the node instead of a flag
            @Override
            public void visit(E node) {
                if (node.compareTo(x) == 0) {
                    foundNode = node;
                }
            }
            // getter for found node
            public E getFoundNode() {
                return foundNode;
            }
        }
        // create instance of inner class and traverse tree
        Finder find = new Finder();
        traverse(find);
        return find.getFoundNode();
    }

    /**
     * Public method to find the minimum element in the tree.
     * Uses compare to for comparison.
     * @return element with minimum value
     */
    public E findMin() {
        return findMin(mRoot); // start at root to search whole tree
    }

    /**
     * Private helper method to find the minimum element in the tree.
     * Uses in-order traversal + inner traverser.
     * @param root of a tree
     * @return the Node containing the minimum element
     */
    private E findMin(LazySTNode root) {
        if (root == null) { // null check
            return null;
        }
        // Inner class to traverse tree and find minimum element
        class MinFinder extends Traverser<E> {
            private E min = null; // where we store the minimum element found (gets updated during traversal)

            @Override
            public void visit(E node) {
                if (min == null || node.compareTo(min) < 0) { // compare current node to min
                    min = node;
                }
            }
            // getter for minimum element found
            public E getMin() {
                return min;
            }
        }
        // create instance of inner class and traverse tree
        MinFinder minFinder = new MinFinder();
        traverse(minFinder);
        if (minFinder.getMin() == null) { // null check for min
            throw new NoSuchElementException("No minimum element found.");
        }
        return minFinder.getMin();
    }

    /**
     * Public method to find the maximum element in the tree.
     * Uses compare to for comparison.
     * @return element with maximum value
     */
    public E findMax() {
        return findMax(mRoot); // start at root to search whole tree
    }

    /**
     * Private helper method to find the maximum element in the tree.
     * Uses in-order traversal + inner traverser.
     * @param root of a tree
     * @return the Node containing the maximum element
     */
    private E findMax(LazySTNode root) {
        if (root == null) { // null check
            return null;
        }
        class MaxFinder extends Traverser<E> {
            private E max = null;

            @Override
            public void visit(E node) {
                if (max == null || node.compareTo(max) > 0) {
                    max = node;
                }
            }

            public E getMax() {
                return max;
            }
        }
        // create instance of inner class and traverse tree
        MaxFinder maxFinder = new MaxFinder();
        traverse(maxFinder);
        if (maxFinder.getMax() == null) { // null check for max
            throw new NoSuchElementException("No maximum element found.");
        }
        return maxFinder.getMax();
    }

    /**
     * The public insert method that will be called by users outside the class.
     * checks to see if size has changed to determine if insert was successful
     * @param x element to insert
     * @return true if insert was successful, false otherwise
     */
    public boolean insert( E x )
    {
        int oldSize = mSize; // store old size to compare later
        mRoot = insert(mRoot, x); // call to private recursive insert method, starts at root
        return (mSize != oldSize); // if size changed, insert was successful
    }

    /**
     * Private recursive insert method
     * @param root to start searching where to insert
     * @param x element to insert
     * @return
     */
    protected LazySTNode insert(LazySTNode root, E x )
    {
        int compareResult;  // avoid multiple calls to compareTo()

        // found the spot to insert, create new leaf node with x value
        if (root == null)
        {
            mSize++; // increment soft size
            mSizeHard++; // increment hard size
            return new LazySTNode(x, null, null);
        }

        compareResult = x.compareTo(root.data); // compare x to current node data, store result
        if ( compareResult < 0 ) // if x is less than current node data, go left
            root.lftChild = insert(root.lftChild, x);
        else if ( compareResult > 0 ) // if x is greater than current node data, go right
            root.rtChild = insert(root.rtChild, x);

        return root;
    }

    /**
     * The public remove method that will be called by users outside the class.
     * checks to see if size has changed to determine if remove was successful
     * @param x element to remove
     * @return true if remove was successful, false otherwise
     */
    public boolean remove( E x )
    {
        int oldSize = mSize; // store old size to compare later
        mRoot = remove(mRoot, x); // call to private recursive remove method, starts at root
        return (mSize != oldSize); // if size changed, remove was successful
    }

    /**
     * Private recursive remove method
     * @param root to start searching where to remove
     * @param x element to remove
     * @return the new root of the subtree
     */
    protected LazySTNode remove(LazySTNode root, E x)
    {
        int compareResult;  // avoid multiple calls to compareTo()

        if (root == null) // not found
            return null;

        compareResult = x.compareTo(root.data); // compare x to current node data, store result (for smaller vs bigger)
        if ( compareResult < 0 ) // if x is less than current node data, go left
            root.lftChild = remove(root.lftChild, x); // recursive call to left child
        else if ( compareResult > 0 ) // if x is greater than current node data, go right
            root.rtChild = remove(root.rtChild, x); // recursive call to right child

            // found the node
        else if (root.lftChild != null && root.rtChild != null)
        {
            root.data = findMin(root.rtChild); // get the minimum value from the right subtree, replace current node data with it
            root.rtChild = remove(root.rtChild, root.data); // recruvsive remove the duplicate node from the right subtree
        }
        else // one or no children
        {
            root = (root.lftChild != null)? root.lftChild : root.rtChild;
            mSize--;
        }
        return root;
    }

    /**
     * Defult public soft traverseral (Excludes the "deleted" nodes)
     * @param func the function we will be applying to each node (.visit of func)
     * @param <F> type of Traverser
     */
    public < F extends Traverser< ? super E > >
    void traverse(F func)
    {
        traverseSoft(func, mRoot); // calls to the private Soft traverser because it is the defult.
    }

    /**
     * Public hard traverseral (Includes the "deleted" nodes
     * in the traversal).
     * @param func the function we will be applying to each node (.visit of func)
     * @param <F> type of Traverser
     */
    public < F extends Traverser< ? super E > >
    void traverseHard(F func)
    {
        traverseHard(func, mRoot); // call to the private helper
    }

    /**
     * Private hard traversal (Includes the "deleted" nodes
     * in the traversal).
     * @param func which processes, extends from Traverser interface, (.visit)
     * @param treeNode current node in recursion
     * @param <F> type of Traverser
     */
    protected < F extends Traverser< ? super E > >
    void traverseHard(F func, LazySTNode treeNode)
    {
        // stop when we reach a null node, base case
        if (treeNode == null)
            return;
        // recursive in-order traversal
        traverseHard(func, treeNode.lftChild); // first go to left child
        func.visit(treeNode.data); // first visit would be left most child
        traverseHard(func, treeNode.rtChild); // go to right child
    }

    /**
     * Public soft traverseral (Includes the "deleted" nodes
     * in the traversal).
     * @param func the function we will be applying to each node (.visit of func)
     * @param <F> type of Traverser
     */
    public < F extends Traverser< ? super E > >
    void traverseSoft(F func)
    {
        traverseSoft(func, mRoot);
    }

    /**
     * Private hard traversal (Includes the "deleted" nodes
     * in the traversal).
     * @param func which processes, extends from Traverser interface
     * @param treeNode current node in recursion
     * @param <F> type of Traverser
     */
    protected < F extends Traverser< ? super E > >
    void traverseSoft(F func, LazySTNode treeNode)
    {
        if (treeNode == null)
            return;

        traverseSoft(func, treeNode.lftChild);
        if (!treeNode.deleted)
            func.visit(treeNode.data);
        traverseSoft(func, treeNode.rtChild);
    }


    /**
     * Node class for Lazy Search Tree
     */
    private class LazySTNode  /*NOTE: no parameters here! */
    {
        protected LazySTNode lftChild, rtChild;   // tied to children (left smaller, right larger)
        E data; // generic data element of the node
        boolean deleted; // true if the node has been "deleted", stored for soft vs hard traversals, lazy because we only mark as deleted

        /**
         * Constructor for LazySTNode
         * @param data the node stores
         * @param lft smaller child
         * @param rt larger child
         */
        LazySTNode(E data, LazySTNode lft, LazySTNode rt) {
            this.data = data;
            this.lftChild = lft;
            this.rtChild = rt;
            this.deleted = false;
        }
    }
}

