import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class SinglyLinkedListTests {
    SinglyLinkedList<String> sll;
    private static final int TIMEOUT = 200;
    private String letters = "abcdefghijklmnopqrstuvwxyz";

    @Before
    public void setUp() {
        sll = new SinglyLinkedList<>();
    }

    @Test(timeout = TIMEOUT)
    public void testAddAtIndexInOrder() {
        for (int i = 0; i < 26; i++) {
            sll.addAtIndex(i, Integer.toString(i) + letters.charAt
                    (i));
        }

        assertEquals(26, sll.size());

        LinkedListNode<String> iter = sll.getHead();
        for (int j = 0; j < 26; j++) {
            assertEquals(Integer.toString(j) + letters.charAt
                    (j), iter.getData());
            iter = iter.getNext();
        }
    }

    @Test(timeout = TIMEOUT)
    public void testAddAtIndexNotInOrder() {
        for (int i = 0; i < 26; i++) {
            sll.addAtIndex(i, Integer.toString(i) + letters.charAt(i));
        }
        assertEquals(26, sll.size());

        sll.addAtIndex(7, "26aa");
        sll.addAtIndex(9, "27bb");
        sll.addAtIndex(17, "28cc");
        sll.addAtIndex(5, "30ee");
        sll.addAtIndex(3, "29dd");

        assertEquals(31, sll.size());

        LinkedListNode<String> iter = sll.getHead();
        for (int j = 0; j < 31; j++) {
            if (j == 3) {
                assertEquals("29dd", iter.getData());
            } else if (j == 6) {
                assertEquals("30ee", iter.getData());
            } else if (j == 9) {
                assertEquals("26aa", iter.getData());
            } else if (j == 11) {
                assertEquals("27bb", iter.getData());
            } else if (j == 19) {
                assertEquals("28cc", iter.getData());
            }
            iter = iter.getNext();
        }
    }

    @Test(timeout = TIMEOUT)
    public void testAddToFront() {
        for (int i = 0; i < 26; i++) {
            sll.addToFront(Integer.toString(i) + letters.charAt
                    (i));
        }

        assertEquals(26, sll.size());

        LinkedListNode<String> iter = sll.getHead();
        for (int j = 25; j >= 0; j--) {
            assertEquals(Integer.toString(j) + letters.charAt
                    (j), iter.getData());
            iter = iter.getNext();
        }
    }

    @Test(timeout = TIMEOUT)
    public void testAddToBack() {
        for (int i = 0; i < 26; i++) {
            sll.addToBack(Integer.toString(i) + letters.charAt
                    (i));
        }

        assertEquals(26, sll.size());
        LinkedListNode<String> iter = sll.getHead();
        for (int j = 0; j < 26; j++) {
            assertEquals(Integer.toString(j) + letters.charAt
                    (j), iter.getData());
            iter = iter.getNext();
        }
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveAtIndexInOrder() {
        for (int i = 0; i < 26; i++) {
            sll.addAtIndex(i, Integer.toString(i) + letters.charAt
                    (i));
        }

        assertEquals(26, sll.size());

        LinkedListNode<String> iter = sll.getHead();
        int index = 0;
        for (int j = 0; j < 26; j++) {
            assertEquals(Integer.toString(j) + letters.charAt
                    (j), sll.removeAtIndex(index));
            assertEquals(25 - j, sll.size());
        }
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveAtIndexNotInOrder() {
        for (int i = 0; i < 26; i++) {
            sll.addAtIndex(i, Integer.toString(i) + letters.charAt
                    (i));
        }

        assertEquals(26, sll.size());

        assertEquals("7h", sll.removeAtIndex(7));
        assertEquals("10k", sll.removeAtIndex(9));
        assertEquals("19t", sll.removeAtIndex(17));
        assertEquals("5f", sll.removeAtIndex(5));
        assertEquals("3d", sll.removeAtIndex(3));

        assertEquals(21, sll.size());
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveFromFront() {
        for (int i = 0; i < 26; i++) {
            sll.addToFront(Integer.toString(i) + letters.charAt
                    (i));
        }

        assertEquals(26, sll.size());

        for (int j = 25; j >= 0; j--) {
            assertEquals(Integer.toString(j) + letters.charAt
                    (j), sll.removeFromFront());
            assertEquals(j, sll.size());
        }
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveFromBack() {
        for (int i = 0; i < 26; i++) {
            sll.addToFront(Integer.toString(i) + letters.charAt
                    (i));
        }

        assertEquals(26, sll.size());

        for (int j = 0; j < 26; j++) {
            assertEquals(Integer.toString(j) + letters.charAt
                    (j), sll.removeFromBack());
            assertEquals(25 - j, sll.size());
        }
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveFirstOccurrence() {
        for (int i = 0; i < 26; i++) {
            sll.addToFront(Integer.toString(i) + letters.charAt
                    (i));
        }
        sll.addToFront("4e");

        String removed = sll.removeFirstOccurrence("4e");
        assertEquals("4e", removed);
        assertEquals(26, sll.size());

        boolean oneMore4e = false;
        boolean atFront = false;
        LinkedListNode<String> iter = sll.getHead();

        for (int j = 0; j < 26; j++) {
            if (iter.getData().equals("4e") && j == 0) {
                oneMore4e = true;
                atFront = true;
                j = 27;
            } else if (iter.getData().equals("4e")) {
                oneMore4e = true;
                j = 27;
            } else {
                iter = iter.getNext();
            }
        }
        assertTrue(oneMore4e);
        assertFalse(atFront);
    }

    @Test(timeout = TIMEOUT)
    public void testGet() {
        for (int i = 0; i < 26; i++) {
            sll.addToFront(Integer.toString(i) + letters.charAt
                    (i));
        }

        LinkedListNode<String> iter = sll.getHead();
        for (int i = 0; i < 26; i++) {
            assertEquals(iter.getData(), sll.get(i));
            iter = iter.getNext();
        }
    }

    @Test(timeout = TIMEOUT)
    public void testToArray() {
        for (int i = 0; i < 26; i++) {
            sll.addToFront(Integer.toString(i) + letters.charAt
                    (i));
        }

        Object[] expected = new Object[26];
        for (int j = 25; j >= 0; j--) {
            expected[25 - j] = Integer.toString(j) + letters.charAt
                    (j);
        }

        assertArrayEquals(expected, sll.toArray());
    }

    @Test(timeout = TIMEOUT)
    public void testIsEmpty() {
        assertTrue(sll.isEmpty());
    }

    @Test(timeout = TIMEOUT)
    public void testSize() {
        assertEquals(0, sll.size());
    }

    @Test(timeout = TIMEOUT)
    public void testClear() {
        assertNull(sll.getHead());
        assertNull(sll.getTail());
        assertEquals(0, sll.size());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testAddAtIndexThrowsIOOBENegativeIndex() {
        sll.addAtIndex(-1, "-1-a");
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testAddAtIndexThrowsIOOBEIndexGreaterThanSize() {
        sll.addAtIndex(10, "10l");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddAtIndexThrowsIAENullData() {
        sll.addAtIndex(0, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddToFrontThrowsIAENullData() {
        sll.addToFront(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddToBackThrowsIAENullData() {
        sll.addToBack(null);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveAtIndexThrowsIOOBENegativeIndex() {
        sll.removeAtIndex(-1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveAtIndexThrowsIOOBEIndexGreaterThanSize() {
        sll.removeAtIndex(10);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveAtIndexThrowsIOOBEIndexEqualToSize() {
        sll.removeAtIndex(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveFirstOccurrenceThrowsIAENullData() {
        sll.removeFirstOccurrence(null);
    }

    @Test(expected = java.util.NoSuchElementException.class)
    public void testRemoveFirstOccurrenceThrowsNSEE() {
        for (int i = 0; i < 26; i++) {
            sll.addToFront(Integer.toString(i) + letters.charAt
                    (i));
        }
        sll.removeFirstOccurrence("27jlnd");
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetThrowsIOOBENegativeIndex() {
        sll.get(-1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetThrowsIOOBEIndexGreaterThanSize() {
        sll.get(10);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetThrowsIOOBEIndexEqualToSize() {
        sll.get(0);
    }
}