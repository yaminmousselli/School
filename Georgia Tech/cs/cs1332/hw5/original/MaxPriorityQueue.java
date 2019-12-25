/**
 * Your implementation of a max priority queue.
 * 
 * @author YOUR NAME HERE
 * @version 1.0
 */
public class MaxPriorityQueue<T extends Comparable<? super T>>
    implements PriorityQueueInterface<T> {

    private HeapInterface<T> backingHeap;
    // Do not add any more instance variables. Do not change the declaration
    // of the instance variables above.

    /**
     * Creates a priority queue.
     */
    public MaxPriorityQueue() {

    }

    @Override
    public void enqueue(T item) {

    }

    @Override
    public T dequeue() {

    }

    @Override
    public boolean isEmpty() {

    }

    @Override
    public int size() {

    }

    @Override
    public void clear() {

    }

    @Override
    public HeapInterface<T> getBackingHeap() {
        // DO NOT CHANGE THIS METHOD!
        return backingHeap;
    }

}
