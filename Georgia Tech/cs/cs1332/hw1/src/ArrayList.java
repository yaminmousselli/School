/**
 * Your implementation of an ArrayList.
 *
 * @author Yamin Mousselli
 * @version 1.0
 */
@SuppressWarnings("unchecked")
public class ArrayList<T> implements ArrayListInterface<T> {
    private T[] backingArray;
    private int size;

    /**
     * Constructs a new ArrayList.
     *
     * You may add statements to this method.
     */
    public ArrayList() {
        backingArray = (T[]) new Object[ArrayListInterface.INITIAL_CAPACITY];
        size = 0;
    }
    /**
     *Doubles the size of the array and copies over the elements to the bigger
     * array.
     */
    private void resize() {
        T[] tempArray = (T[]) new Object[backingArray.length * 2];
        for (int i = 0; i < backingArray.length; i++) {
            tempArray[i] = backingArray[i];
        }
        backingArray = tempArray;
    }

    @Override
    public void addAtIndex(int index, T data) {
        if (index < 0) {
            throw new java.lang.IndexOutOfBoundsException("You have specified"
                    + " either an index to small");
        }
        if (index > size) {
            throw new java.lang.IndexOutOfBoundsException("You have specified"
                    + " an index to big");
        }
        if (data == null) {
            throw new java.lang.IllegalArgumentException("The value, null, is"
                    + " not a valid input");
        }
        if (size == 0) {
            backingArray[0] = data;
        }
        if (size == index) {
            if (size == backingArray.length) {
                resize();
            }
            backingArray[index] = data;
        } else {
            if (size == backingArray.length) {
                T oldData = backingArray[index];
                T tempArray[] = (T[]) new Object[backingArray.length * 2];
                for (int i = 0; i < index; i++) {
                    tempArray[i] = backingArray[i];
                }
                tempArray[index] = oldData;
                size++;
                for (int i = index; i < size; i++) {
                    tempArray[i] = backingArray[i];
                }
            }
            for (int i = size - 1; i >= index; i--) {
                backingArray[i + 1] = backingArray[i];
            }
            backingArray[index] = data;
        }
        size++;
    }

    @Override
    public void addToFront(T data) {
        if (data == null) {
            throw new java.lang.IllegalArgumentException("The value, null, is"
                    + " not a valid input");
        }
        if (size == 0) {
            backingArray[0] = data;
        } else {
            /*if (size == backingArray.length) {
                resize();
            }*/
            if (size == backingArray.length) {
                T tempArray[] = (T[]) new Object[backingArray.length * 2];
                for (int i = 0; i < size; i++) {
                    tempArray[i + 1] = backingArray[i];
                }
                tempArray[0] = data;
                backingArray = tempArray;
                size++;
            }
            for (int i = size - 1; i >= 0; i--) {
                backingArray[i + 1] = backingArray[i];
            }
            backingArray[0] = data;
        }
        size++;
    }

    @Override
    public void addToBack(T data) {
        if (data == null) {
            throw new java.lang.IllegalArgumentException("The value, null, is"
                    + " not a valid input");
        } else {
            if (size == backingArray.length) {
                resize();
            }
            backingArray[size] = data;
        }
        size++;
    }
    @Override
    public T removeAtIndex(int index) {
        T toBeReturned = null;
        if (index < 0 || index >= size) {
            throw new java.lang.IndexOutOfBoundsException("You have either "
                    + "specified an "
                    + "index to small or to big");
        }
        if (index == (size - 1)) {
            toBeReturned = backingArray[index];
            backingArray[index] = null;
            size--;
            return toBeReturned;
        } else {
            toBeReturned = backingArray[index];
            for (int i = index; i < size; i++) {
                if (i == (size - 1)) {
                    backingArray[i] = null;
                } else {
                    backingArray[i] = backingArray[i + 1];
                }
            }
            size--;
            return toBeReturned;
        }
    }

    @Override
    public T removeFromFront() {
        T toBeReturned = null;
        if (size == 0) {
            return toBeReturned;
        } else {
            toBeReturned = backingArray[0];
            for (int i = 0; i < size - 1; i++) {
                backingArray[i] = backingArray[i + 1];
            }
            backingArray[size - 1] = null;
            size--;
            return toBeReturned;
        }

    }
    @Override
    public T removeFromBack() {
        T toBeReturned = null;
        if (size == 0) {
            return toBeReturned;
        } else {
            toBeReturned = backingArray[size - 1];
            backingArray[size - 1] = null;
            size--;
        }
        return toBeReturned;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new java.lang.IndexOutOfBoundsException("You have either "
                          + "specified "
                       + "an "
                    + "index to small or to big");
        }
        return backingArray[index];
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        backingArray = (T[]) new Object[ArrayListInterface.INITIAL_CAPACITY];
        size = 0;
    }
    @Override
    public Object[] getBackingArray() {
        // DO NOT MODIFY.
        return backingArray;
    }
}
