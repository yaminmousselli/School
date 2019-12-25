/**
 * Your implementation of an array-backed queue.
 *
 * @author Yamin Mousselli
 * @version 1.0
 *
 * REMEMBER THIS: back = (front + size) % lengthOfArray
 *                front = (front + 1) % lengthOfArray
 */
import java.util.NoSuchElementException;

public class ArrayQueue<T> implements QueueInterface<T> {

    private T[] backingArray;
    private int front;
    private int back;
    private int size;


    /**
     * Constructs a new ArrayQueue.
     */
    public ArrayQueue() {
        backingArray = (T[]) new Object[INITIAL_CAPACITY];
    }

    /**
     * Dequeue from the front of the queue.
     *
     * Do not shrink the backing array.
     * If the queue becomes empty as a result of this call, you must not
     * explicitly reset front or back to 0.
     *
     * @see QueueInterface#dequeue()
     */
    @Override
    public T dequeue() {
        if (size == 0) {
            throw new NoSuchElementException("You can not remove an element "
                    + "from an empty Array Based Queue");
        } else {
            T toBeReturned = backingArray[front];
            backingArray[front] = null;
            front = (front + 1) % backingArray.length;
            size--;
            return toBeReturned;
        }
    }

    /**
     * Add the given data to the queue.
     *
     * If sufficient space is not available in the backing array, you should
     * regrow it to (double the current length) + 1; in essence, 2n + 1, where n
     * is the current capacity. If a regrow is necessary, you should copy
     * elements to the front of the new array and reset front to 0.
     *
     * @see QueueInterface#enqueue(T)
     */
    @Override
    public void enqueue(T data) {
        if (data == null) {
            throw new IllegalArgumentException("The data value, null, is not "
                    + "an acceptable input for this Array Based Queue");
        } else if (size == backingArray.length) {
            T[] tempArray = (T[]) new Object[(backingArray.length * 2) + 1];
                int nextIndex = 0;
                for (int i = front; i < size; i++) {
                    tempArray[nextIndex] = backingArray[i];
                    nextIndex++;
                }
                for (int j = 0; j < front; j++) {
                    tempArray[nextIndex] = backingArray[j];
                    nextIndex++;
                }
                backingArray = tempArray;
                //Now you finally fucking enqueue.
                backingArray[size] = data;
                size++;
                //make front the beginning of the array
                front = 0;
                //You can't update back without updating front because back
                // is dependent on front
                back = (front + size) % backingArray.length;
        } else {
            backingArray[back] = data;
            size++;
            back = (front + size) % backingArray.length;
        }
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    @Override
    public int size() {
        return size;
    }

    /**
     * Returns the backing array of this queue.
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