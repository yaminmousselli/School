import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class LinkedListTests {
    private LinkedListInterface<String> list;

    public static final int TIMEOUT = 200;

    @Before
    public void SetUp() {
        list = new SinglyLinkedList<String>();
    }

    @Test(timeout = TIMEOUT)
    public void testAddStringsFront() {
        assertEquals(0, list.size());

        list.addToFront("0a");
        list.addToFront("1a");
        list.addToFront("2a");
        list.addToFront("3a");
        list.addToFront("4a");
        list.addToFront("5a"); //5a 4a 3a 2a 1a 0a

        assertEquals(6, list.size());

        LinkedListNode<String> current = list.getHead();
        assertNotNull(current);
        assertEquals("5a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("4a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("3a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("2a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("1a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("0a", current.getData());
        assertSame(current, list.getTail());
    }

    @Test(timeout = TIMEOUT)
    public void testAddStringsBack() {
        assertEquals(0, list.size());

        list.addToBack("0a");
        list.addToBack("1a");
        list.addToBack("2a");
        list.addToBack("3a");
        list.addToBack("4a");
        list.addToBack("5a"); //0a 1a 2a 3a 4a 5a

        assertEquals(6, list.size());

        LinkedListNode<String> current = list.getHead();
        assertNotNull(current);
        assertEquals("0a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("1a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("2a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("3a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("4a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("5a", current.getData());
        assertSame(current, list.getTail());
    }

    @Test(timeout = TIMEOUT)
    public void testAddStrings() {
        assertEquals(0, list.size());
        assertNull(list.getHead());

        list.addAtIndex(0, "0a"); //0a
        list.addAtIndex(0, "1a"); //1a 0a
        list.addAtIndex(2, "2a"); //1a 0a 2a
        list.addAtIndex(3, "3a"); //1a 0a 2a 3a
        list.addAtIndex(2, "4a"); //1a 0a 4a 2a 3a
        list.addAtIndex(3, "5a"); //1a 0a 4a 5a 2a 3a
        list.addAtIndex(6, "6a");

        assertEquals(7, list.size());

        LinkedListNode<String> current = list.getHead();
        assertNotNull(current);
        assertEquals("1a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("0a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("4a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("5a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("2a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("3a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("6a", current.getData());

        assertSame(current, list.getTail());
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveFromFront() {
        assertEquals(0, list.size());
        assertNull(null, list.getHead());
        assertNull(null, list.getTail());

        list.addToBack("1a");
        list.addToBack("2a");
        list.addToBack("3a");
        list.addToBack("4a");
        list.addToBack("5a"); //1a 2a 3a 4a 5a

        assertEquals("5a", list.getTail().getData());
        assertEquals(5, list.size());

        LinkedListNode<String> current = list.getHead();
        assertNotNull(current);
        assertEquals("1a", current.getData());

        assertEquals("1a", list.removeFromFront());
        assertEquals(4, list.size());

        assertEquals("2a", list.removeFromFront());
        assertEquals(3, list.size());

        assertEquals("3a", list.removeFromFront());
        assertEquals(2, list.size());

        // sanity check in middle
        assertEquals("5a", list.getTail().getData());
        assertEquals("4a", list.getHead().getData());

        assertEquals("4a", list.removeFromFront());
        assertEquals(1, list.size());

        assertEquals("5a", list.removeFromFront());
        assertEquals(0, list.size());

        assertNull(list.getHead());
        assertNull(list.getTail());
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveAtIndex() {
        assertEquals(0, list.size());
        assertNull(list.getHead());
        assertNull(list.getTail());

        list.addToBack("0a");
        list.addToBack("1a");
        list.addToBack("2a");
        list.addToBack("3a");
        list.addToBack("4a");
        list.addToBack("5a"); //0a 1a 2a 3a 4a 5a

        assertEquals(6, list.size());

        assertEquals("3a", list.removeAtIndex(3)); //0a 1a 2a 4a 5a
        assertEquals("5a", list.removeAtIndex(4)); //0a 1a 2a 4a
        assertEquals("0a", list.removeAtIndex(0)); //1a 2a 4a
        assertEquals("2a", list.removeAtIndex(1)); //1a 4a
        assertEquals("4a", list.removeAtIndex(1)); //1a
        assertEquals("1a", list.removeAtIndex(0)); //

        assertEquals(0, list.size());
        assertNull(list.getHead());
        assertNull(list.getTail());
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveFromBack() {
        assertEquals(0, list.size());
        assertNull(list.getHead());
        assertNull(list.getTail());

        for (int i = 0; i < 100; i++) {
            list.addToFront(Integer.toString(i) + "a");
        }

        assertEquals("0a", list.getTail().getData());
        assertEquals("99a", list.getHead().getData());
        assertEquals(100, list.size());

        for (int i = 0; i < 99; i++) {
            assertEquals(Integer.toString(i) + "a", list.removeFromBack());
        }

        assertEquals(1, list.size());
        assertEquals("99a", list.getHead().getData());
        assertEquals("99a", list.getTail().getData());
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveFirstOccurrence() {
        assertEquals(0, list.size());
        assertNull(list.getHead());
        assertNull(list.getTail());

        list.addToBack("0a");
        list.addToBack("1a");
        list.addToBack("2a");
        list.addToBack("3a");
        list.addToBack("4a");
        list.addToBack("5a");
        list.addToBack("0a");
        list.addToBack("1a");
        list.addToBack("2a");
        list.addToBack("3a");
        list.addToBack("4a");
        list.addToBack("5a"); //0a 1a 2a 3a 4a 5a 0a 1a 2a 3a 4a 5a

        assertEquals("0a", list.getHead().getData());
        assertEquals("5a", list.getTail().getData());
        assertEquals(12, list.size());

        assertEquals("1a", list.removeFirstOccurrence("1a"));//0a 2a 3a 4a 5a 0a 1a 2a 3a 4a 5a
        assertEquals("1a", list.removeFirstOccurrence("1a"));//0a 2a 3a 4a 5a 0a 2a 3a 4a 5a
        assertEquals("0a", list.removeFirstOccurrence("0a"));//2a 3a 4a 5a 0a 2a 3a 4a 5a
        assertEquals("0a", list.removeFirstOccurrence("0a"));//2a 3a 4a 5a 2a 3a 4a 5a
        assertEquals("5a", list.removeFirstOccurrence("5a"));//2a 3a 4a 2a 3a 4a 5a
        assertEquals("5a", list.removeFirstOccurrence("5a"));//2a 3a 4a 2a 3a 4a

        assertEquals("2a", list.getHead().getData());
        assertEquals("4a", list.getTail().getData());
        assertEquals(6, list.size());

        assertEquals("4a", list.removeFirstOccurrence("4a"));//2a 3a 2a 3a 4a
        assertEquals("4a", list.removeFirstOccurrence("4a"));//2a 3a 2a 3a

        assertEquals("2a", list.getHead().getData());
        assertEquals("3a", list.getTail().getData());
        assertEquals(4, list.size());
    }

    @Test(timeout = TIMEOUT)
    public void testGet() {
        assertEquals(0, list.size());
        assertNull(list.getHead());
        assertNull(list.getTail());

        for (int i = 0; i < 100; i++) {
            list.addToBack(Integer.toString(i));
        }

        assertEquals(100, list.size());
        assertEquals("0", list.getHead().getData());
        assertEquals("99", list.getTail().getData());

        for (int i = 0; i < 100; i++) {
            assertEquals(Integer.toString(i), list.get(i));
        }

        assertEquals(100, list.size());
    }

    @Test(timeout = TIMEOUT)
    public void testToArray() {
        assertEquals(0, list.size());
        assertNull(list.getHead());
        assertNull(list.getTail());

        for (int i = 0; i < 5; i++) {
            list.addToBack(i + "a");
        }

        Object[] expected = {"0a", "1a", "2a", "3a", "4a"};

        assertEquals(5, list.size());
        assertArrayEquals(expected, list.toArray());
    }

    @Test(timeout = TIMEOUT)
    public void testClear() {
        assertEquals(0, list.size());
        assertNull(list.getHead());
        assertNull(list.getTail());

        for (int i = 0; i < 5; i++) {
            list.addToBack(i + "a");
        }
        assertEquals(5, list.size());
        assertEquals("0a", list.getHead().getData());
        assertEquals("4a", list.getTail().getData());

        list.clear();

        assertEquals(0, list.size());
        assertNull(list.getHead());
        assertNull(list.getTail());
    }

    @Test(timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void addAtIndexBounds() {
        for (int i = 0; i < 5; i++) {
            list.addToBack(i + "a");
        }
        list.addAtIndex(6, "5a");
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void addAtIndexNullPassed() {
        for (int i = 0; i < 5; i++) {
            list.addToBack(i + "a");
        }
        list.addAtIndex(3, null);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void addToFrontNullPassed() {
        list.addToFront(null);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void addToBackNullPassed() {
        list.addToBack(null);
    }

    @Test(timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void removeAtIndexBounds() {
        for (int i = 0; i < 5; i++) {
            list.addToBack(i + "a");
        }
        list.removeAtIndex(5);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void removeFirstOccurrenceNull() {
        list.removeFirstOccurrence(null);
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void removeFirstOccurrenceNoElement() {
        for (int i = 0; i < 5; i++) {
            list.addToBack(i + "a");
        }
        assertEquals("3a", list.removeFirstOccurrence("3a"));
        list.removeFirstOccurrence("3a");
    }
}