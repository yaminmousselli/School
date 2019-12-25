import java.util.NoSuchElementException;

/**
 * Your implementation of a max heap.
 *
 * @author Yamin Mousselli
 * @version 1.0
 */
public class MaxHeap<T extends Comparable<? super T>>
    implements HeapInterface<T> {

    private T[] backingArray;
    private int size;

    /**
     * Creates a Heap with an initial length of {@code
     * INITIAL_CAPACITY} for the
     * backing array.
     *
     * Use the constant field in the interface. Do not use magic numbers!
     */
    public MaxHeap() {

        backingArray = (T[]) new Comparable[INITIAL_CAPACITY];
    }

    /**
     * Helper method to double the size of the array when it is full in O(n)
     */
    private void resize() {
        T[] tempArray = (T[]) new Comparable[(backingArray.length * 2) + 1];
        for (int i = 1; i < backingArray.length; i++) {
            tempArray[i] = backingArray[i];
        }
        backingArray = tempArray;
    }

    @Override
    public void add(T item) {
        int index = size + 1;
        int parentIndex = index / 2;
        if (item == null) {
            throw new IllegalArgumentException("You can not add the value, "
                    + "null, to this data structure");
        }
        if (size == 0) {
            backingArray[index] = item;
            size++;
        } else {
            //defensive programming
            if (size + 1 >= backingArray.length) {
                resize();
            }
            backingArray[index] = item;
            size++;
            //what would the condition be if this was a minheap?
            while (parentIndex
                    >= 1 && backingArray[parentIndex].compareTo(backingArray[index]) < 0) {
                T temp = backingArray[parentIndex];
                backingArray[index] = temp;
                backingArray[parentIndex] = item;
                index = parentIndex;
                parentIndex = parentIndex / 2;
            }
        }
    }

    /**
     * Helper Method to recursivly heapify and locate. There will never be a
     * left child without a right child due to the nature of completeness
     * @param current the index in which you start downheaping
     */
    private void recursiveSwap(int current) {
        int leftIndex = 2 * current;
        int rightIndex = (2 * current) + 1;
        int maxIndex = leftIndex;

        if (current > size || leftIndex >= backingArray.length) {
            return;
        }
        if (backingArray[leftIndex] == null) {
            return;
        }
        if (backingArray[rightIndex] != null && backingArray[rightIndex]
                .compareTo(backingArray[leftIndex]) > 0) {
            maxIndex = rightIndex;
        }
        if (backingArray[maxIndex].compareTo(backingArray[current]) > 0) {
            T rootSwap = backingArray[current];
            backingArray[current] = backingArray[maxIndex];
            backingArray[maxIndex] = rootSwap;
            //current = maxIndex;
            recursiveSwap(maxIndex);
        } else {
            return;
        }
    }

    @Override
    public T remove() {
        int index = size;

        if (size == 0) {
            throw new NoSuchElementException("You can not remove any elements"
                    + " because this data structure is empty");
        } else if (size == 1) {
            T toBeRemoved = backingArray[index];
            //Do i need to set it to null?
            backingArray[index] = null;
            size--;
            return toBeRemoved;
        } else {
            T toBeRemoved = backingArray[1];
            T replaceRoot = backingArray[index];
            backingArray[1] = replaceRoot;
            backingArray[index] = null;
            size--;
            index = 1;
            recursiveSwap(index);
            return toBeRemoved;
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

    @Override
    public void clear() {
        backingArray = (T[]) new Comparable[INITIAL_CAPACITY];
        size = 0;
    }

    @Override
    public Comparable[] getBackingArray() {
        // DO NOT CHANGE THIS METHOD!
        return backingArray;
    }

}
