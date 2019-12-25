/**
 * Your implementation of a linked queue.
 *
 * @author Yamin Mousselli
 * @version 1.0
 */
import java.util.NoSuchElementException;

public class LinkedQueue<T> implements QueueInterface<T> {

    // Do not add new instance variables.
    private LinkedNode<T> head;
    private LinkedNode<T> tail;
    private int size;

    @Override
    public T dequeue() {
        if (size == 0) {
            throw new NoSuchElementException("You can remove any elements "
                    + "from the empty Linked List Backed Queue");
        } else if (size == 1) {
            T toBeReturned = head.getData();
            head = null;
            tail = null;
            size--;
            return toBeReturned;
        } else {
            T toBeReturned = head.getData();
            head = head.getNext();
            size--;
            return toBeReturned;
        }
    }

    @Override
    public void enqueue(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Null is not a valid input for"
                    + " this Linked list Based Queue");
        } else if (size == 0) {
            head = new LinkedNode<T>(data, null);
            tail = head;
            size++;
        } else {
            LinkedNode<T> newNode = new LinkedNode<T>(data, null);
            tail.setNext(newNode);
            tail = newNode;
            size++;
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
     * Returns the head of this queue.
     * Normally, you would not do this, but we need it for grading your work.
     *
     * DO NOT USE THIS METHOD IN YOUR CODE.
     *
     * @return the head node
     */
    public LinkedNode<T> getHead() {
        // DO NOT MODIFY!
        return head;
    }

    /**
     * Returns the tail of this queue.
     * Normally, you would not do this, but we need it for grading your work.
     *
     * DO NOT USE THIS METHOD IN YOUR CODE.
     *
     * @return the tail node
     */
    public LinkedNode<T> getTail() {
        // DO NOT MODIFY!
        return tail;
    }
}