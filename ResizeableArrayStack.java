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

    /** Creates an empty stack for the array */
    public ResizeableArrayStack() {
        this(DEFAULT_CAPACITY);
    } // end default constructor

    /**
     * Creates an empty array stack given the initial capacity.
     *
     * @param initialCapacity The character capacity desired.
     */

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

    /**
     * Creates a stack containing charcater entires.
     * Initializes the index of the array.
     *
     * @param contents checks intergrity and capacity of object.
     */
    public void push(T newEntry) {
        checkIntegrity();
        ensureCapacity();
        stack[topIndex + 1] = newEntry;
        topIndex++;
    } // end push

    /**
     * Checks the integrity of the object.
     *
     * @param peek The character is checked for integrity
     * @return Returns to the top of the stack
     * @throws Exception Throws exception if stack is empty.
     */
    public T peek() {
        checkIntegrity();
        if (isEmpty())
            throw new EmptyStackException();
        else
            return stack[topIndex];
    } // end peek

    /**
     * @param pop The object will pop if higher precedence
     * @return Returns to the top of the stack once popped
     * @throws Exception Throws exception if stack is empty.
     */
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

    /**
     * Sees whether this stack is empty.
     *
     * @return True if this stack is empty, or false if not.
     */
    public boolean isEmpty() {
        return topIndex < 0;
    } // end isEmpty

    /** Removes all entires from the stack */
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

    public float evaluatePostfix(String expression) {
        return 0; // stub
    } // end evaluatePostfix

    /**
     * @param Checks the capacity of the stack to ensure no overflow.
     *               Doubles the size of the array if stack becomes full.
     */
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
    } // end checkintegrity
} // end ArrayStack
