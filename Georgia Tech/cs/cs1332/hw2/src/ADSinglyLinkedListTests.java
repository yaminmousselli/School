import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * A nearly comprehensive list of tests for SinglyLinkedList.
 *
 * When using these tests, understand that if your addAtIndex
 * function, get function, or size updates do not work properly,
 * many tests may fail.
 *
 * @author AD
 */
public class ADSinglyLinkedListTests {

    /**
     * Setup
     */

    private SinglyLinkedList<String> list;
    private static final int TIMEOUT = 500;


    @Before
    public void setUp() throws Exception {
        list = new SinglyLinkedList<>();
    }

    /**
     * Methods below test general functionality
     */
    @Test (timeout = TIMEOUT)
    public void testAddAtIndex() {
        list.addAtIndex(0, "1a");
        list.addAtIndex(1, "2a");
        list.addAtIndex(2, "3a");

        String[] expectedArray = new String[3];
        expectedArray[0] = "1a";
        expectedArray[1] = "2a";
        expectedArray[2] = "3a";

        assertArrayEquals(expectedArray, list.toArray());
        assertEquals("1a", list.getHead().getData());
        assertEquals("3a", list.getTail().getData());
    }
    @Test (timeout = TIMEOUT)
    public void testAddToFront() {
        list.addToFront("3a");
        list.addToFront("2a");
        list.addToFront("1a");

        String[] expectedArray = new String[3];
        expectedArray[0] = "1a";
        expectedArray[1] = "2a";
        expectedArray[2] = "3a";

        assertArrayEquals(expectedArray, list.toArray());
        assertEquals("1a", list.getHead().getData());
        assertEquals("3a", list.getTail().getData());
    }
    @Test (timeout = TIMEOUT)
    public void testAddToBack() {
        list.addToBack("1a");
        list.addToBack("2a");
        list.addToBack("3a");

        String[] expectedArray = new String[3];
        expectedArray[0] = "1a";
        expectedArray[1] = "2a";
        expectedArray[2] = "3a";

        assertArrayEquals(expectedArray, list.toArray());
        assertEquals("1a", list.getHead().getData());
        assertEquals("3a", list.getTail().getData());
    }
    @Test (timeout = TIMEOUT)
    public void testRemoveAtIndex() {
        list.addAtIndex(0, "1a");
        list.addAtIndex(1, "2a");
        list.addAtIndex(2, "3a");
        list.addAtIndex(3, "4a");
        assertEquals("3a", list.removeAtIndex(2));

        String[] expectedArray = new String[3];
        expectedArray[0] = "1a";
        expectedArray[1] = "2a";
        expectedArray[2] = "4a";

        assertArrayEquals(expectedArray, list.toArray());
        assertEquals("1a", list.getHead().getData());
        assertEquals("4a", list.getTail().getData());
    }
    @Test (timeout = TIMEOUT)
    public void testRemoveFromFront() {
        list.addAtIndex(0, "1a");
        list.addAtIndex(1, "2a");
        list.addAtIndex(2, "3a");
        list.addAtIndex(3, "4a");
        assertEquals("1a", list.removeFromFront());

        String[] expectedArray = new String[3];
        expectedArray[0] = "2a";
        expectedArray[1] = "3a";
        expectedArray[2] = "4a";

        assertArrayEquals(expectedArray, list.toArray());
        assertEquals("2a", list.getHead().getData());
        assertEquals("4a", list.getTail().getData());
    }
    @Test (timeout = TIMEOUT)
    public void testRemoveFromBack() {
        list.addAtIndex(0, "1a");
        list.addAtIndex(1, "2a");
        list.addAtIndex(2, "3a");
        list.addAtIndex(3, "4a");
        assertEquals("4a", list.removeFromBack());

        String[] expectedArray = new String[3];
        expectedArray[0] = "1a";
        expectedArray[1] = "2a";
        expectedArray[2] = "3a";

        assertArrayEquals(expectedArray, list.toArray());
        assertEquals("1a", list.getHead().getData());
        assertEquals("3a", list.getTail().getData());
    }
    @Test (timeout = TIMEOUT)
    public void testRemoveFirstOccurrence() {
        list.addAtIndex(0, "1a");
        list.addAtIndex(1, "2a");
        list.addAtIndex(2, "3a");
        list.addAtIndex(3, "2a");

        assertEquals("2a", list.removeFirstOccurrence("2a"));

        String[] expectedArray = new String[3];
        expectedArray[0] = "1a";
        expectedArray[1] = "3a";
        expectedArray[2] = "2a";

        assertArrayEquals(expectedArray, list.toArray());
        System.out.println(list.get(0));
        System.out.println(list.get(1));
        System.out.println(list.get(2));
        assertEquals("1a", list.getHead().getData());
        assertEquals("2a", list.getTail().getData());
    }
    @Test (timeout = TIMEOUT)
    public void testGet() {
        list.addAtIndex(0, "1a");
        assertEquals("1a", list.get(0));
        list.addAtIndex(1, "2a");
        assertEquals("2a", list.get(1));
    }
    @Test (timeout = TIMEOUT)
    public void testIsEmpty() {
        assertTrue(list.isEmpty());
        list.addAtIndex(0, "1a");
        assertFalse(list.isEmpty());
        list.clear();
    }
    @Test (timeout = TIMEOUT)
    public void testClear() {
        list.addAtIndex(0, "1a");
        list.addAtIndex(1, "2a");
        assertEquals(2, list.size());

        list.clear();
        assertEquals(0, list.size());
        assertEquals(null, list.getHead());
        assertEquals(null, list.getTail());
    }

    /**
     * Methods below test special cases
     */

    //Test adding to empty list
    @Test (timeout = TIMEOUT)
    public void testAddingAtIndexToEmptyList() {
        list.addAtIndex(0, "1a");
        assertEquals("1a", list.get(0));
        assertEquals(1, list.size());
        assertEquals("1a", list.getTail().getData());
        assertEquals("1a", list.getHead().getData());
    }
    @Test (timeout = TIMEOUT)
    public void testAddingToFrontToEmptyList() {
        list.addToFront("1a");
        assertEquals("1a", list.get(0));
        assertEquals(1, list.size());
        assertEquals("1a", list.getTail().getData());
        assertEquals("1a", list.getHead().getData());
    }
    @Test (timeout = TIMEOUT)
    public void testAddingToBackToEmptyList() {
        list.addToBack("1a");
        assertEquals("1a", list.get(0));
        assertEquals(1, list.size());
        assertEquals("1a", list.getTail().getData());
        assertEquals("1a", list.getHead().getData());
    }

    //Test removing from list of size 0
    @Test (timeout = TIMEOUT)
    public void testRemoveFromFrontOfEmptyList() {
        list.removeFromFront();
    }
    @Test (timeout = TIMEOUT)
    public void testRemoveFromBackOfEmptyList() {
        list.removeFromBack();
    }

    //Test removing from list of size 1 using
    @Test (timeout = TIMEOUT)
    public void testRemoveAtIndexOfListSizeOne() {
        list.addAtIndex(0, "1a");
        assertEquals(1, list.size());

        assertEquals("1a", list.removeAtIndex(0));
        assertEquals(0, list.size());
        assertEquals(null, list.getHead());
        assertEquals(null, list.getTail());
    }
    @Test (timeout = TIMEOUT)
    public void testRemoveFromFrontOfListSizeOne() {
        list.addAtIndex(0, "1a");
        assertEquals(1, list.size());

        assertEquals("1a", list.removeFromFront());
        assertEquals(0, list.size());
        assertEquals(null, list.getHead());
        assertEquals(null, list.getTail());
    }
    @Test (timeout = TIMEOUT)
    public void testRemoveFromBackOfListSizeOne() {
        list.addAtIndex(0, "1a");
        assertEquals(1, list.size());

        assertEquals("1a", list.removeFromBack());
        assertEquals(0, list.size());
        assertEquals(null, list.getHead());
        assertEquals(null, list.getTail());
    }
    @Test (timeout = TIMEOUT)
    public void testRemoveFirstOccurrenceOfListSizeOne() {
        list.addAtIndex(0, "1a");
        assertEquals(1, list.size());

        assertEquals("1a", list.removeFirstOccurrence("1a"));
        assertEquals(0, list.size());
        assertEquals(null, list.getHead());
        assertEquals(null, list.getTail());
    }

    //Test removing at end of the list
    @Test (timeout = TIMEOUT)
    public void testRemoveAtIndexAtEndOfList() {
        list.addAtIndex(0, "1a");
        list.addAtIndex(1, "2a");
        list.addAtIndex(2, "3a");
        list.addAtIndex(3, "4a");

        assertEquals("4a", list.removeAtIndex(3));
        assertEquals(3, list.size());
        assertEquals("1a", list.getHead().getData());
        assertEquals("3a", list.getTail().getData());
    }
    @Test (timeout = TIMEOUT)
    public void testRemoveFirstOccurrenceAtEndOfList() {
        list.addAtIndex(0, "1a");
        list.addAtIndex(1, "2a");
        list.addAtIndex(2, "3a");
        list.addAtIndex(3, "4a");

        assertEquals("4a", list.removeFirstOccurrence("4a"));
        assertEquals(3, list.size());
        assertEquals("1a", list.getHead().getData());
        System.out.println(list.getTail());
        System.out.println(list.get(0));
        System.out.println(list.get(1));
        System.out.println(list.get(2));
        //System.out.println(list.get(3));
        assertEquals("3a", list.getTail().getData());
    }



    /**
     * Methods below test exception cases
     */

    //Exceptions for addAtIndex() method
    @Test (timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void testAddAtIndexThrowsIOBE() {
        list.addAtIndex(1, "1a");
    }
    @Test (timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void testAddAtIndexThrowsIOBE2() {
        list.addAtIndex(-1, "1a");
    }
    @Test (timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testAddAtIndexThrowsIAE() {
        list.addAtIndex(0, null);
    }

    //Exceptions for addToFront() method
    @Test (timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testAddToFrontThrowsIAE() {
        list.addToFront(null);
    }

    //Exceptions for addToBack() method
    @Test (timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testAddToBackThrowsIAE() {
        list.addToBack(null);
    }

    //Exceptions for removeAtIndex() method
    @Test (timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void testRemoveAtIndexThrowsIOBE() {
        list.addAtIndex(0, "1a");
        assertEquals(1, list.size());

        list.removeAtIndex(1);
    }
    @Test (timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void testRemoveAtIndexThrowsIOBE2() {
        list.addAtIndex(0, "1a");
        assertEquals(1, list.size());

        list.removeAtIndex(-1);
    }

    //Exceptions for removeFirstOccurrence() method
    @Test (timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testRemoveAtFirstOccurrenceThrowsIAE() {
        list.removeFirstOccurrence(null);
    }
    @Test (timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testRemoveAtFirstOccurrenceThrowsNSEE() {
        list.addAtIndex(0, "1a");
        list.removeFirstOccurrence("2a");
    }
    @Test (timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testRemoveAtFirstOccurrenceThrowsNSEE2() {
        list.addAtIndex(0, "1a");
        list.addAtIndex(1, "2a");
        list.addAtIndex(2, "3a");
        list.removeFirstOccurrence("4a");
    }

    //Exceptions for get() method
    @Test (timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void testGetThrowsIOBE() {
        list.addAtIndex(0, "1a");
        assertEquals(1, list.size());
        list.get(2);
    }
    @Test (timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void testGetThrowsIOBE2() {
        list.get(-1);
    }

}