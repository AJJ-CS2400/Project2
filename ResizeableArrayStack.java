import java.util.EmptyStackException;

/**
 * A class of stacks whose entries are stored in an array.
 *
 * @author Frank M. Carrano and Timothy M. Henry
 * @version 5.0
 */
public final class ResizeableArrayStack<T> implements StackInterface<T> {
    private T[] stack; // Array of stack entries
    private int topIndex; // Index of top entry
    private boolean integrityOK = false;
    private static final int DEFAULT_CAPACITY = 50;
    private static final int MAX_CAPACITY = 10000;

    /** Creates an empty stack with 50 capacity */
    public ResizeableArrayStack() {
        this(DEFAULT_CAPACITY);
    } // end default constructor

    /** Creates an empty array stack given an initial capacity.
     * @param initialCapacity The capacity of the new stack.
     * @throws IllegalStateException if initialCapacity is > 10000 */
    public ResizeableArrayStack(int initialCapacity) {
        integrityOK = false;
        checkCapacity(initialCapacity);

        // The cast is safe because the new array contains null entries
        @SuppressWarnings("unchecked")
        T[] tempStack = (T[]) new Object[initialCapacity];
        stack = tempStack;
        topIndex = -1;
        integrityOK = true;
    } // end constructor

    /** Adds a new entry to the top of this stack.
     * @param newEntry Entry to be added to the top of the stack. */
    public void push(T newEntry) {
        checkIntegrity();
        ensureCapacity();
        stack[topIndex + 1] = newEntry;
        topIndex++;
    } // end push

    /** Retrieves this stack's top entry.
     * @return The object at the top of the stack.
     * @throws EmptyStackException if the stack is empty. */
    public T peek() {
        checkIntegrity();
        if (isEmpty())
            throw new EmptyStackException();
        else
            return stack[topIndex];
    } // end peek

    /** Removes and returns this stack's top entry.
     * @return The object at the top of the stack.
     * @throws EmptyStackException if the stack is empty. */
    public T pop() {
        checkIntegrity();
        if (isEmpty())
            throw new EmptyStackException();
        else {
            T top = stack[topIndex];
            stack[topIndex] = null;
            topIndex--;
            return top;
        } // end if
    } // end pop

    /** Detects whether this stack is empty.
     * @return True if the stack is empty, false if not. */
    public boolean isEmpty() {
        return topIndex < 0;
    } // end isEmpty

    /** Removes all entries from this stack. */
    public void clear() {
        checkIntegrity();

        // Remove references to the objects in the stack,
        // but do not deallocate the array
        while (topIndex > -1) {
            stack[topIndex] = null;
            topIndex--;
        } // end while
        // Assertion: topIndex is -1
    } // end clear

    // Doubles capacity of stack if limit is reached
    private void ensureCapacity() {
        if (topIndex >= stack.length - 1) // If array is full, double its size
        {
            int newLength = 2 * stack.length;
            checkCapacity(newLength);
            @SuppressWarnings("unchecked")
            T[] tempStack = (T[]) new Object[newLength];
            stack = tempStack;
        } // end if
    } // end ensureCapacity

    // Throws an exception if the client requests a capacity that is too large.
    private void checkCapacity(int capacity) {
        if (capacity > MAX_CAPACITY)
            throw new IllegalStateException("Attempt to create a bag whose capacity exceeds " +
                    "allowed maximum of " + MAX_CAPACITY);
    } // end checkCapacity

    // Throws an exception if receiving object is not initialized.
    private void checkIntegrity() {
        if (!integrityOK)
            throw new SecurityException("ArrayBag object is corrupt.");
    } // end checkIntegrity
} // end ArrayStack