/**
 * Your implementation of an array-backed stack.
 *
 * @author Yamin Mousselli
 * @version 1.0
 */
import java.util.NoSuchElementException;

public class ArrayStack<T> implements StackInterface<T> {

    private T[] backingArray;
    private int size;

    /**
     * Constructs a new ArrayStack.
     */
    public ArrayStack() {
        backingArray = (T[]) new Object[INITIAL_CAPACITY];
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    /**
     * Pop from the stack.
     *
     * Do not shrink the backing array.
     *
     * @see StackInterface#pop()
     */
    @Override
    public T pop() {
        if (size == 0) {
            throw new NoSuchElementException("The array-based stack is empty "
                    + "and therefore, you can not remove any elements from "
                    + "this array based stack");
        }
        T toBeReturned = backingArray[size - 1];
        backingArray[size - 1] = null;
        size--;
        return toBeReturned;
    }

    /**
     * Push the given data onto the stack.
     *
     * If sufficient space is not available in the backing array, you should
     * regrow it to (double the current length) + 1; in essence, 2n + 1, where n
     * is the current capacity.
     *
     * @see StackInterface#push(T)
     */
    @Override
    public void push(T data) {
        if (data == null) {
            throw new IllegalArgumentException("You can not insert a null "
                    + "value into this Array-Based Stack");
        }
        if (size == backingArray.length) {
            T[] tempArray = (T[]) new Object[(backingArray.length * 2) + 1];
            for (int i = 0; i < backingArray.length; i++) {
                tempArray[i] = backingArray[i];
            }
            backingArray = tempArray;
            backingArray[size] = data;
            size++;
        } else {
            backingArray[size] = data;
            size++;
        }
    }

    @Override
    public int size() {
        return size;

    }

    /**
     * Returns the backing array of this stack.
     * Normally, you would not do this, but we need it for grading your work.
     *
     * DO NOT USE THIS METHOD IN YOUR CODE.
     *
     * @return the backing array
     */
    public Object[] getBackingArray() {
        // DO NOT MODIFY!
        return backingArray;
    }
}
