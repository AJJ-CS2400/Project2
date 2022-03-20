import java.util.EmptyStackException;

/**
 * A class of stacks whose entries are stored in a chain of nodes.
 *
 * @author Frank M. Carrano and Timothy M. Henry
 * @version 5.0
 */
public final class LinkedStack<T> implements StackInterface<T> {
    private Node topNode; // References the first node in the chain

    /**
     * Creates the first node, which is topNode to null
     *
     * @param topNode, allows it to be null.
     */
    public LinkedStack() {
        topNode = null;
    } // end default constructor

    /**
     * Adds a new entry to the top of this stack.
     *
     * @param newEntry An object to be added to the stack.
     */
    public void push(T newEntry) {
        Node newNode = new Node(newEntry, topNode);
        topNode = newNode;
    } // end push

    /**
     * Retrieves this stack's top entry.
     *
     * @return The object at the top of the stack.
     * @throws EmptyStackException if the stack is empty.
     */
    public T peek() {
        if (isEmpty())
            throw new EmptyStackException();
        else
            return topNode.getData();
    } // end peek

    /**
     * Removes and returns this stack's top entry.
     *
     * @return The object at the top of the stack.
     * @throws EmptyStackException if the stack is empty before the operation.
     */
    public T pop() {
        T top = peek(); // Might throw EmptyStackException

        // Assertion: topNode != null
        topNode = topNode.getNextNode();

        return top;
    } // end pop

    /**
     * Detects whether this stack is empty.
     *
     * @return True if the stack is empty.
     */
    public boolean isEmpty() {
        return topNode == null;
    } // end isEmpty

    /** Removes all entries from this stack. */
    public void clear() {
        topNode = null;
    } // end clear

    /**
     * This is the class node 
     *
     * @param Checks and links nodes within the stack.
     *               Checks integrity and capacity
     */
    private class Node {
        private T data; // Entry in stack
        private Node next; // Link to next node

        private Node(T dataPortion) {
            this(dataPortion, null);
        } // end constructor

        private Node(T dataPortion, Node linkPortion) {
            data = dataPortion;
            next = linkPortion;
        } // end constructor

        /**
         * Gets data that is on the top of this stack.
         *
         * @param getData An object is retrieved from the stack.
         * @return data is return to the stack.
         */
        private T getData() {
            return data;
        } // end getData

        private void setData(T newData) {
            data = newData;
        } // end setData

        /**
         * Gets the next node of the data
         *
         * @param getNextNode The next node is retrieved from the stack.
         * @return next is return to the stack.
         */
        private Node getNextNode() {
            return next;
        } // end getNextNode

        private void setNextNode(Node nextNode) {
            next = nextNode;
        } // end setNextNode
    } // end Node
} // end LinkedStack
