/**
 * Created by yamin on 1/16/17.
 */
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
    public class ArrayListStudentTestsJered {

        private ArrayListInterface<String> list;

        public static final int TIMEOUT = 200;

        @Before
        public void setUp() {
            list = new ArrayList<String>();
        }

        @Test(timeout = TIMEOUT)
        public void testAddStrings() {
            assertEquals(0, list.size());

            list.addAtIndex(0, "0a"); //0a
            list.addAtIndex(1, "1a"); //0a 1a
            list.addAtIndex(2, "2a"); //0a 1a 2a
            list.addAtIndex(3, "3a"); //0a 1a 2a 3a

            assertEquals(4, list.size());

            Object[] expected = new Object[ArrayListInterface.INITIAL_CAPACITY];
            expected[0] = "0a";
            expected[1] = "1a";
            expected[2] = "2a";
            expected[3] = "3a";
            assertArrayEquals(expected, list.getBackingArray());
        }

        @Test(timeout = TIMEOUT)
        public void testAddStringsFront() {
            assertEquals(0, list.size());

            list.addToFront("0a");
            list.addToFront("1a");
            list.addToFront("2a");
            list.addToFront("3a");
            list.addToFront("4a"); //4a 3a 2a 1a 0a

            assertEquals(5, list.size());

            Object[] expected = new Object[ArrayListInterface.INITIAL_CAPACITY];
            expected[0] = "4a";
            expected[1] = "3a";
            expected[2] = "2a";
            expected[3] = "1a";
            expected[4] = "0a";
            assertArrayEquals(expected, list.getBackingArray());
        }

        @Test(timeout = TIMEOUT)
        public void testAddStringsBack() {
            assertEquals(0, list.size());

            for (int i = 0; i < 5; i++) {
                list.addToBack(String.format("%da", i)); //4a 3a 2a 1a 0a
            }

            assertEquals(5, list.size());

            Object[] expected = new Object[ArrayListInterface.INITIAL_CAPACITY];
            for (int i = 0; i < 5; i++) {
                expected[i] = String.format("%da", i);
            }
            assertArrayEquals(expected, list.getBackingArray());
        }

        @Test(timeout = TIMEOUT)
        public void testResizeArray() {
            assertEquals(0, list.size());

            int listSize = 10;
            for (int i = 0; i < 1000; i++) {
                list.addToBack(String.format("%da", i)); // 0a 1a 2a 3a 4a ...
                if (i == listSize) {
                    listSize *= 2;
                }
            }

            assertEquals(listSize, list.getBackingArray().length);

            Object[] expected = new Object[listSize];
            for (int i = 0; i < 1000; i++) {
                expected[i] = String.format("%da", i);
            }
            assertArrayEquals(expected, list.getBackingArray());
        }

        @Test(timeout = TIMEOUT)
        public void testRemoveStrings() {
            assertEquals(0, list.size());

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
        }

        @Test(timeout = TIMEOUT)
        public void testRemoveStringsFront() {
            assertEquals(0, list.size());

            for (int i = 0; i < 5; i++) {
                list.addToFront(String.format("%da", i)); // 4a 3a 2a 1a 0a
            }

            assertEquals(5, list.size());

            Object[] expected = new Object[ArrayListInterface.INITIAL_CAPACITY];
            for (int i = 4; i >= 0; i--) {
                expected[4 - i] = String.format("%da", i);
            }

            assertArrayEquals(expected, list.getBackingArray());

            int limit = 4;
            while (list.size() != 0) {
                Object temp = list.removeFromFront();
                assertEquals(temp, expected[0]);
                for (int i = (limit - 1); i >= 0; i--) {
                    expected[limit - 1 - i] = String.format("%da", i);
                }
                expected[limit--] = null;
                assertArrayEquals(expected, list.getBackingArray());
            }
        }

        @Test(timeout = TIMEOUT)
        public void testRemoveStringsBack() {
            assertEquals(0, list.size());

            for (int i = 0; i < 5; i++) {
                list.addToBack(String.format("%da", i)); // 0a 1a 2a 3a 4a
            }

            assertEquals(5, list.size());

            Object[] expected = new Object[ArrayListInterface.INITIAL_CAPACITY];
            for (int i = 0; i < 5; i++) {
                expected[i] = String.format("%da", i);
            }

            assertArrayEquals(expected, list.getBackingArray());

            int index = list.size() - 1;
            while (list.size() != 0) {
                Object temp = list.removeFromBack();
                assertEquals(temp, expected[index]);
                expected[index--] = null;
                assertArrayEquals(expected, list.getBackingArray());
            }
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

        @Test(timeout = TIMEOUT)
        public void testGetResize() {
            assertEquals(0, list.size());

            for (int i = 0; i < 1000; i++) {
                list.addToBack(String.format("%da", i)); // 1000a 999a ...
            }

            for (int i = 0; i < 1000; i++) {
                assertEquals(list.get(i), String.format("%da", i));
            }
        }

        @Test(timeout = TIMEOUT)
        public void testSizeGeneral() {
            for (int i = 0; i < 5; i++) {
                assertEquals(i, list.size());
                list.addToBack(String.format("%da", i));
            }

            assertEquals(5, list.size());
        }

        @Test(timeout = TIMEOUT)
        public void testSizeResize() {
            for (int i = 0; i < 1000; i++) {
                assertEquals(i, list.size());
                list.addToBack(String.format("%da", i));
            }

            assertEquals(1000, list.size());
        }

        @Test(timeout = TIMEOUT)
        public void testClear() {
            for (int i = 0; i < 1000; i++) {
                assertEquals(i, list.size());
                list.addToBack(String.format("%da", i));
            }

            assertEquals(1000, list.size());

            Object[] expected = new Object[ArrayListInterface.INITIAL_CAPACITY];
            list.clear();

            assertEquals(0, list.size());
            assertArrayEquals(expected, list.getBackingArray());
        }

        @Test(timeout = TIMEOUT)
        public void testIsEmpty() {
            assertEquals(true, list.isEmpty());

            for (int i = 0; i < 1000; i++) {
                list.addToBack(String.format("%da", i));
                assertEquals(false, list.isEmpty());
            }

            list.clear();
            assertEquals(true, list.isEmpty());
        }

        @Test(timeout = TIMEOUT)
        public void testAddIndex() {
            assertEquals(0, list.size());
            assertEquals(true, list.isEmpty());

            list.addToBack("1");
            list.addToBack("2");
            list.addToBack("3");
            list.addToBack("4");
            list.addAtIndex(1, "9");

            assertEquals(5, list.size());
            assertEquals(false, list.isEmpty());

            Object[] expected = new Object[ArrayListInterface.INITIAL_CAPACITY];
            expected[0] = "1";
            expected[1] = "9";
            expected[2] = "2";
            expected[3] = "3";
            expected[4] = "4";

            assertArrayEquals(expected, list.getBackingArray());
        }
    }
