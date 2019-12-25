/**
 * Your implementation of a SinglyLinkedList
 *
 * Always check to see what you would if size is 0 and if size is 1 in every
 * method. especially removing and adding.
 *
 * @author Yamin Mousselli
 * @version 1.0
 */
public class SinglyLinkedList<T> implements LinkedListInterface<T> {
    private LinkedListNode<T> head;
    private LinkedListNode<T> tail;
    private int size;

    @Override
    public void addAtIndex(int index, T data) {
        if (index < 0) {
            throw new java.lang.IndexOutOfBoundsException("You have "
                    + "specified and index to small or to big");
        } else if (index > size) {
            throw new java.lang.IndexOutOfBoundsException("The index you "
                    + "specified is to big");
        } else if (data == null) {
            throw new java.lang.IllegalArgumentException("Null is not a valid"
                    + "input");
        } else if (index == 0) {
            addToFront(data);
        } else if (index == size) {
            addToBack(data);
        } else {
            LinkedListNode<T> current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.getNext();
            }
            LinkedListNode<T> newNode = new LinkedListNode<T>(data, current
                    .getNext());
            current.setNext(newNode);
            size++;
        }
    }

    @Override
    public void addToFront(T data) {
        if (data == null) {
            throw new java.lang.IllegalArgumentException("The value, null, is"
                    + " can not be added to the list");
        }
        head = new LinkedListNode<T>(data, head);
        /*
        Say they don't have a constructor that takes a next pointer, then you
         would do the following:

        LinkedListNode<T> temp = new LinkedListNode<T>(data);
        temp.setNext(head);
        head = temp;
         */
        if (tail == null) {
            tail = head;
        }
        size++;
    }

    @Override
    public void addToBack(T data) {
        if (data == null) {
            throw new java.lang.IllegalArgumentException("Null is not a valid"
                    + " element that can be added to the list");
        }
        if (head == null) {
            addToFront(data);
        } else {
            tail.setNext(new LinkedListNode<T>(data));
            tail = tail.getNext();
            size++;
        }
    }

    @Override
    public T removeAtIndex(int index) {
        if (index < 0) {
            throw new java.lang.IndexOutOfBoundsException("You have specified"
                    + " an index that is to small");
        }
        if (index >= size) {
            throw new java.lang.IndexOutOfBoundsException("You have specified"
                    + " an index to big");
        }
        if (index == 0) {
            return removeFromFront();
        }
        if (index == size - 1) {
            return removeFromBack();
        } else {
            LinkedListNode<T> current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.getNext();
            }
            T toBeReturned = current.getNext().getData();
            current.setNext(current.getNext().getNext());
            size--;
            return toBeReturned;
        }
    }

    @Override
    public T removeFromFront() {
        if (size == 0) {
            return null;
        }
        if (head == tail) {
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
    public T removeFromBack() {
        if (size == 0) {
            return null;
        }
        if (head == tail) {
            T toBeReturned = tail.getData();
            head = null;
            tail = null;
            size--;
            return toBeReturned;
        } else {
            LinkedListNode<T> current = head;
            while (current.getNext() != tail) {
                current = current.getNext();
            }
            T toBeReturned = current.getNext().getData();
            current.setNext(null);
            tail = current;
            size--;
            return toBeReturned;

            /*
            Without a tail pointer,
            you would do the following after checking for when size is 0 and
            when size is 1:

            LinkedListNode<T> current = head;
            while (current.getNext().getNext() != null) {
                current = current.getNext();
             }
             T temp = current.getNext().getData();
             current.setNext(null);
             size--;
             return temp;
           */
        }
    }

    @Override
    public T removeFirstOccurrence(T data) {
        if (data == null) {
            throw new java.lang.IllegalArgumentException("You can not remove "
                    + "null");
        }
        LinkedListNode<T> current = head;
        LinkedListNode<T> previous = head;
        if (size == 1 && current.getData() == data) {
            return removeFromFront();
        }
        int count = 0;
        while (count < size && !(current.getData().equals(data))) {
            if (current.getNext() == null) {
                throw new java.util.NoSuchElementException("The element you "
                        + " you are looking to remove does not exist");
            }
            previous = current;
            count++;
            current = current.getNext();
        }
        if (current == tail) {
            T toBeReturned = current.getData();
            previous.setNext(current.getNext());
            tail = previous;
            size--;
            return toBeReturned;
        }
        T toBeReturned = current.getData();
        previous.setNext(current.getNext());
        size--;
        return toBeReturned;
    }

    @Override
    public T get(int index) {
        if (index < 0) {
            throw new java.lang.IndexOutOfBoundsException("You have specified"
                    + " an index to small");
        }
        if (index >= size) {
            throw new java.lang.IndexOutOfBoundsException("You have specified"
                    + " an index to big");
        }
        if (index == 0) {
            return head.getData();
        }
        if (index == (size - 1)) {
            return tail.getData();
        } else {
            LinkedListNode<T> current = head;
            for (int i = 0; i < index; i++) {
                current = current.getNext();
            }
            return current.getData();
        }
    }

    @Override
    public Object[] toArray() {
        if (isEmpty()) {
            Object[] emptyArray = new Object[0];
            return emptyArray;
        }
        Object[] toBeReturned = new Object[size];
        int index = 0;
        LinkedListNode<T> current = head;
        while (current != null) { //iterate through the entire list, not
            // current.getNext
            toBeReturned[index] = current.getData();
            current = current.getNext();
            index++;
        }
        return toBeReturned;
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
        head = null;
        tail = null;
        size = 0;
    }























    @Override
    public LinkedListNode<T> getHead() {
        // DO NOT MODIFY!
        return head;
    }

    @Override
    public LinkedListNode<T> getTail() {
        // DO NOT MODIFY!
        return tail;
    }
}