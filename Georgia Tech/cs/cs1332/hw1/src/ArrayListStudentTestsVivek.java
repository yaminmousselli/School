import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * Vivek Raja's JUnit tests for Homework 1.
 * @version 1
 */
public class ArrayListStudentTestsVivek {

    private ArrayListInterface<String> list;

    public static final int TIMEOUT = 200;

    @Before
    public void setUp() {
        list = new ArrayList<String>();

    }

    @Test(timeout = TIMEOUT)
    public void testAddStringsIndex() {
        assertEquals(0, list.size());

        assertTrue(list.isEmpty());

        for (int i = 0; i < 160; i++) {
            list.addAtIndex(i, "" + i + "a");
        }

        assertEquals(160, list.size());
        assertFalse(list.isEmpty());

        Object[] expected = new Object[160];
        for (int i = 0; i < 160; i++) {
            expected[i] = "" + i + "a";
        }

        assertArrayEquals(expected, list.getBackingArray());

        list.clear();
        assertTrue(list.isEmpty());
        assertEquals(0, list.size());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testAddStringsIndexNegative() {
        for (int i = 0; i < 100; i++) {
            list.addAtIndex(i, "" + i + "a");
        }
        list.addAtIndex(-1, "-1a");
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testAddStringsIndexOutOfBoundsException() {
        for (int i = 0; i < 100; i++) {
            list.addAtIndex(i, "" + i + "a");
        }
        list.addAtIndex(101, "-1a");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddStringsIndexDataNull() {
        for (int i = 0; i < 100; i++) {
            list.addAtIndex(i, "" + i + "a");
        }
        list.addAtIndex(67, null);
    }

    @Test(timeout = TIMEOUT)
    public void testAddStringsIndexMiddle() {
        list.addAtIndex(0, "0a");
        list.addAtIndex(1, "1a");
        list.addAtIndex(2, "2a");
        list.addAtIndex(3, "3a");
        list.addAtIndex(2, "middle-o");

        Object[] expected = new Object[10];
        expected[0] = "0a";
        expected[1] = "1a";
        expected[2] = "middle-o";
        expected[3] = "2a";
        expected[4] = "3a";

        assertArrayEquals(expected, list.getBackingArray());
    }

    @Test (timeout = TIMEOUT)
    public void addStringIndexFront() {
        list.addAtIndex(0, "0a");
        list.addAtIndex(1, "1a");
        list.addAtIndex(2, "2a");
        list.addAtIndex(3, "3a");
        list.addAtIndex(0, "Surprise");

        assertEquals(5, list.size());

        Object[] expected = new Object[10];
        expected[0] = "Surprise";
        expected[1] = "0a";
        expected[2] = "1a";
        expected[3] = "2a";
        expected[4] = "3a";

        assertArrayEquals(expected, list.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testGetGeneral() {
        list.addAtIndex(0, "0a");
        list.addAtIndex(1, "1a");
        list.addAtIndex(2, "2a");
        list.addAtIndex(3, "3a");
        list.addAtIndex(4, "4a"); //0a 1a 2a 3a 4a

        assertEquals("0a", list.get(0));
        assertEquals("1a", list.get(1));
        assertEquals("2a", list.get(2));
        assertEquals("3a", list.get(3));
        assertEquals("4a", list.get(4));
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void testGetException1() {
        list.get(-1);
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void testGetException2() {
        list.get(list.size());
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void testGetException3() {
        list.get(list.size() + 1);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testAddFrontException() {
        list.addToFront(null);
    }

    @Test(timeout = TIMEOUT)
    public void testAddFront() {
        for (int i = 0; i < 1000; i++) {
            list.addToFront("" + i + "a");
        }

        Object[] expected = new Object[list.getBackingArray().length];
        for (int i = 0; i < 1000; i++) {
            expected[i] = "" + (999 - i) + "a";
        }

        assertArrayEquals(expected, list.getBackingArray());
    }

    @Test (expected = IllegalArgumentException.class)
    public void testAddBackException() {
        list.addToBack(null);
    }

    @Test(timeout = TIMEOUT)
    public void testAddBack() {
        for (int i = 0; i < 1000; i++) {
            list.addToBack("" + i);
        }
        Object[] expected = new Object[list.getBackingArray().length];
        for (int i = 0; i < 1000; i++) {
            expected[i] = "" + i;
        }

        assertArrayEquals(expected, list.getBackingArray());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveStringsException1() {
        list.removeAtIndex(-1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveStringsException2() {
        list.removeAtIndex(0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveStringsException3() {
        list.removeAtIndex(1);
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveStrings() {

        list.addAtIndex(0, "0a");
        list.addAtIndex(1, "1a");
        list.addAtIndex(2, "2a");
        list.addAtIndex(3, "3a");
        list.addAtIndex(4, "4a");
        list.addAtIndex(5, "5a"); //0a 1a 2a 3a 4a 5a

        assertEquals(6, list.size());

        assertEquals("2a", list.removeAtIndex(2)); //0a 1a 3a 4a 5a

        assertEquals(5, list.size());
        Object[] expected = new Object[ArrayListInterface.INITIAL_CAPACITY];
        expected[0] = "0a";
        expected[1] = "1a";
        expected[2] = "3a";
        expected[3] = "4a";
        expected[4] = "5a";

        assertArrayEquals(expected, list.getBackingArray());

        list.addAtIndex(2, "2a");
        expected[0] = "0a";
        expected[1] = "1a";
        expected[2] = "2a";
        expected[3] = "3a";
        expected[4] = "4a";
        expected[5] = "5a";

        assertArrayEquals(expected, list.getBackingArray());

        list.removeFromBack();
        expected[5] = null;
        assertArrayEquals(expected, list.getBackingArray());

        list.removeFromFront();
        expected[0] = "1a";
        expected[1] = "2a";
        expected[2] = "3a";
        expected[3] = "4a";
        expected[4] = null;

        assertArrayEquals(expected, list.getBackingArray());
    }








}