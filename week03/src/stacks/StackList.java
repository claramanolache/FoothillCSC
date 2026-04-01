package stacks;

import java.util.Iterator;
import java.util.List;

/**
 * A generic stack implementation using a list.
 * @param <Type>
 */
public class StackList<Type> implements Iterable<Type> {
    String name;
    Type top;
    List<Type> elements;

    /**
     * Constructor for StackList, sets name and initializes list.
     * @param name of the stack
     */
    public StackList(String name){
        this.name = name;
        this.elements = new java.util.ArrayList<Type>();
    }
    /**
     * Pushes an item onto the top of the stack.
     * @param item that will be placed on top
     */
    public void push(Type item){
        if (item == null || item.equals("")){
            return;
        }
        elements.add(item);
        top = item;
    }
    /**
     * Pops the top item off the stack and returns it.
     * @return the item that was on top of the stack
     */
    public Type pop(){
        if (isEmpty()){
            return null;
        }
        top = (size() != 1)? elements.get(elements.size() - 2): null;
        return elements.remove(elements.size() - 1);
    }
    /**
     * Peeks at the top item of the stack without removing it.
     * @return
     */
    public Type peek(){
        if (isEmpty()){
            return null;
        }
        return top;
    }
    /**
     * Returns the size of the stack.
     * @return number of elements in the stack
     */
    public int size(){
        return elements.size();
    }
    /**
     * Clears all elements from the stack.
     */
    public void clear(){
        if (elements.isEmpty()){
            return;
        }
        elements.clear();
        top = null;
    }
    /**
     * Checks if the stack is empty.
     * @return true if stack is empty, false otherwise
     */
    public boolean isEmpty(){
        return size() == 0;
    }
    /**
     * Returns a string representation of the stack.
     * @return string showing name and elements of the stack
     */
    @Override
    public String toString(){
        return name + " with " + size() + " links: \n" + elements.toString();
    }
    /**
     * Returns an iterator to traverse the stack from top to bottom.
     * @return iterator for the stack from inner class StackListIterator
     */
    @Override
    public Iterator<Type> iterator() {
        return new StackListIterator();
    }
    /**
     * Inner Node class representing each element in the stack.
     */
    public class Node {
        Type data;
        Node next;

        public Node(Type data) {
            this.data = data;
        }
    }
    /**
     * Inner class implementing the Iterator interface for StackList.
     */
    public class StackListIterator implements Iterator<Type> {
        private int index;
        /**
         * Constructor initializes the index to the top of the stack.
         */
        public StackListIterator() {
            this.index = elements.size() - 1;
        }
        /**
         * Checks if there are more elements to iterate over.
         * @return true if more elements exist, false otherwise
         */
        @Override
        public boolean hasNext() {
            return index >= 0;
        }
        /**
         * Returns the next element in the iteration and decrements the index.
         * @return
         */
        @Override
        public Type next() {
            Type item = elements.get(index);
            index--;
            return item;
        }
    }
}
