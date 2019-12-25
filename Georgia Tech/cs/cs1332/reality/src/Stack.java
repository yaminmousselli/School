/**
 * Created by yamin on 1/23/17.
 */
public class Stack implements StackADT<T> {

    private T[] backingArray;
    private int size;

    public Stack() {
        backingArray = (T[]) new Object[100];
    }

    public void push(T item) {
        if (size == backingArray.length) {
            throw new java.lang.RuntimeException("You have exceeded the size "
                    + "of the array");
        }
        backingArray[size++] = T;
    }

    public T pop() {
        if (size == 0) {
            throw new java.util.EmptyStackException("Stack is empty");
        }
        T toBeReturned = backingArray[size - 1];
        backingArray[size--] = null;
    }
}

