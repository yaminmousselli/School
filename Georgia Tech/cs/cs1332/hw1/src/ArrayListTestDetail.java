import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class ArrayListTestDetail {
    private ArrayListInterface<String> list;

    public static final int TIMEOUT = 200;

    @Before
    public void setUp() {
        list = new ArrayList<String>();
    }

    @Test(timeout = TIMEOUT)
    public void testConstructor() {
        assertEquals(0, list.size());
        Object[] expected = new Object[ArrayListInterface.INITIAL_CAPACITY];
        assertArrayEquals(expected, list.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testAddAtIndex() {
        try {
            list.addAtIndex(1, "a1");
            assert false;
        } catch (IndexOutOfBoundsException e) {
            assert true;
        }
        try {
            list.addAtIndex(0, "a1");
            assert true;
        } catch (IndexOutOfBoundsException e) {
            assert false;
        }
        try {
            list.addAtIndex(0, null);
            assert false;
        } catch (IllegalArgumentException e) {
            assert true;
        }
        list.addAtIndex(0, "a0");
        list.addAtIndex(1, "a1.5");
        list.addAtIndex(list.size(), "a2");
        assert (list.size() == 4);
        Object[] expected = new Object[ArrayListInterface.INITIAL_CAPACITY];
        expected[0] = "a0";
        expected[1] = "a1.5";
        expected[2] = "a1";
        expected[3] = "a2";
        assertArrayEquals(expected, list.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testResize() {
        for (int i = 0; i < 100; i++) {
            list.addToFront("z" + i);
        }
        assert (list.size() == 100);
        assert (list.getBackingArray().length == 160);
    }

    @Test(timeout = TIMEOUT)
    public void testAddToFront() {
        for (int i = 0; i < 100; i++) {
            list.addToFront("z" + i);
        }
        try {
            list.addToFront(null);
            assert false;
        } catch (IllegalArgumentException e) {
            assert true;
        }
        assert (list.size() == 100);
        assert (list.getBackingArray()[0].equals("z99"));
    }

    @Test(timeout = TIMEOUT)
    public void testAddToBack() {
        for (int i = 0; i < 100; i++) {
            list.addToBack("z" + i);
        }
        try {
            list.addToFront(null);
            assert false;
        } catch (IllegalArgumentException e) {
            assert true;
        }
        assert (list.size() == 100);
        assert (list.getBackingArray()[0].equals("z0"));
    }

    @Test(timeout = TIMEOUT)
    public void testGet() {
        for (int i = 0; i < 100; i++) {
            list.addToBack("z" + i);
        }
        try {
            list.get(list.size());
            assert false;
        } catch (IndexOutOfBoundsException e) {
            assert true;
        }
        try {
            list.get(-4);
            assert false;
        } catch (IndexOutOfBoundsException e) {
            assert true;
        }
        for (int i = 0; i < 100; i++) {
            assert (list.get(i).equals("z" + i));
        }
    }

    @Test(timeout = TIMEOUT)
    public void testRemove() {
        assert (list.removeFromBack() == null);
        assert (list.removeFromFront() == null);
        for (int i = 0; i < 100; i++) {
            list.addToBack("z" + i);
        }
        try {
            list.removeAtIndex(list.size());
            assert false;
        } catch (IndexOutOfBoundsException e) {
            assert true;
        }
        try {
            list.removeAtIndex(-4);
            assert false;
        } catch (IndexOutOfBoundsException e) {
            assert true;
        }
        for (int i = 0; i < 100; i++) {
            assert (list.removeFromFront().equals("z" + i));
        }
        assert (list.isEmpty());
        list.addAtIndex(0, "stuff");
        assert (!list.isEmpty());
        list.clear();
        assert (list.isEmpty());
        for (int i = 0; i < 100; i++) {
            list.addToBack("z" + i);
        }
        for (int i = 0; i < 100; i++) {
            assert (list.removeFromBack().equals("z" + (99 - i)));
        }
    }
}
