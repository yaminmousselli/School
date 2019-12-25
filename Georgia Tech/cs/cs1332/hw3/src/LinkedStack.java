/**
 * Your implementation of a linked stack.
 *
 * @author Yamin Mousselli
 * @version 1.0
 */
import java.util.NoSuchElementException;

public class LinkedStack<T> implements StackInterface<T> {

    private LinkedNode<T> head;
    private int size;

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    @Override
    public T pop() {
        if (size == 0) {
            throw new NoSuchElementException("The LinkedList Stack is empty "
                    + "and therefore, you can not remove any elements from "
                    + "this Linked List Based Stack");
        } if (size == 1) {
            T toBeReturned = head.getData();
            head = null;
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
    public void push(T data) {
        if (data == null) {
            throw new IllegalArgumentException("You can not insert a null "
                    + "value into this Linked List Based Stack");
        } else {
            head = new LinkedNode<T>(data, head);
            size++;
        }
    }

    @Override
    public int size() {
        return size;
    }

    /**
     * Returns the head of this stack.
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
}