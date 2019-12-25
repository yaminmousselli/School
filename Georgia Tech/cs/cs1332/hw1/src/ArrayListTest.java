import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.Random;

public class ArrayListTest {

    private ArrayListInterface<Integer> list;

    public static final int TIMEOUT = 200;

    @Before
    public void setUp() {
        list = new ArrayList<Integer>();
    }

    @Test(timeout = TIMEOUT)
    public void testAddBack() {
        /*
           This tests whether addToBack(), size() and get() are O(1).
           It also tests whether regrowing happens properly when needed.
        */
        int sz = 500000;
        Object[] expected = new Object[sz];
        assertEquals(0, list.size());
        long expectedSize = 0, mySize = 0;
        Random rng = new Random();
        for (int i = 0; i < sz; i++) {
            int x = rng.nextInt();
            expected[i] = x;
            list.addToBack(x);
            expectedSize += (i + 1);
            mySize += list.size();
        }
        assertEquals(expectedSize, mySize);
        for (int i = 0; i < sz; i++) {
            assertEquals(expected[i], list.get(i));
        }
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveBack() {
        /*
           This tests whether removeFromBack(), size() and get() are O(1).
        */
        int sz = 600000, rem = 500000;
        Object[] expected = new Object[sz];
        assertEquals(0, list.size());
        long expectedSize = 0, mySize = 0;
        Random rng = new Random();
        for (int i = 0; i < sz; i++) {
            int x = rng.nextInt();
            expected[i] = x;
            list.addToBack(x);
            expectedSize += i + 1;
            mySize += list.size();
        }
        assertEquals(expectedSize, mySize);
        for (int i = 0; i < sz; i++) {
            assertEquals(expected[i], list.get(i));
        }
        for (int i = 0; i < rem; i++) {
            list.removeFromBack();
        }
        for (int i = 0; i < sz - rem; i++) {
            assertEquals(expected[i], list.get(i));
        }
    }   
}