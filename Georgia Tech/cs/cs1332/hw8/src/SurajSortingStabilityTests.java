import org.junit.Before;
import org.junit.Test;
import java.util.Comparator;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author Suraj Masand
 */
public class SurajSortingStabilityTests {

    private TestNumbers[] testNums;
    private TestNumbers[] testNumsSorted;
    private ComparatorPlus<TestNumbers> comp;
    private static final int TIMEOUT = 200;

    @Before
    public void setUp() {
        testNums = new TestNumbers[10];
        testNums[0] = new TestNumbers("23A");
        testNums[1] = new TestNumbers("1944A");
        testNums[2] = new TestNumbers("327a");
        testNums[3] = new TestNumbers("14a");
        testNums[4] = new TestNumbers("18a");
        testNums[5] = new TestNumbers("9a");
        testNums[6] = new TestNumbers("23B");
        testNums[7] = new TestNumbers("1944B");
        testNums[8] = new TestNumbers("23C");
        testNums[9] = new TestNumbers("72a");
        testNumsSorted = new TestNumbers[10];
        testNumsSorted[0] = testNums[5];
        testNumsSorted[1] = testNums[3];
        testNumsSorted[2] = testNums[4];
        testNumsSorted[3] = testNums[0];
        testNumsSorted[4] = testNums[6];
        testNumsSorted[5] = testNums[8];
        testNumsSorted[6] = testNums[9];
        testNumsSorted[7] = testNums[2];
        testNumsSorted[8] = testNums[1];
        testNumsSorted[9] = testNums[7];

        comp = TestNumbers.getNumComparator();
    }

    @Test(timeout = TIMEOUT)
    public void testBubbleSort() {
        Sorting.bubbleSort(testNums, comp);
        assertArrayEquals(testNumsSorted, testNums);
        assertTrue("Number of comparisons: " + comp.getCount(),
                comp.getCount() <= 44);
    }

    @Test(timeout = TIMEOUT)
    public void testInsertionSort() {
        Sorting.insertionSort(testNums, comp);
        assertArrayEquals(testNumsSorted, testNums);
        assertTrue("Number of comparisons: " + comp.getCount(),
                comp.getCount() <= 30);
    }

    @Test(timeout = TIMEOUT)
    public void testMergeSort() {
        Sorting.mergeSort(testNums, comp);
        assertArrayEquals(testNumsSorted, testNums);
        assertTrue("Number of comparisons: " + comp.getCount(),
                comp.getCount() <= 48);
    }


    private static class TestNumbers {
        private String str;

        public TestNumbers(String s) {
            str = s;
        }

        public void setStr(String s) {
             this.str = s;
        }

        public int getNum() {
            return Integer.parseInt(str.substring(0, str.length() - 1));
        }

        public String toString() {
            return "String: " + str;
        }


        public static ComparatorPlus<TestNumbers> getNumComparator() {
            return new ComparatorPlus<TestNumbers>() {
                @Override
                public int compare(TestNumbers num1,
                                   TestNumbers num2) {
                    incrementCount();
                    return num1.getNum() - num2.getNum();
                }
            };
        }

    }

    /**
     * Inner class that allows counting how many comparisons were made.
     */
    private abstract static class ComparatorPlus<T> implements Comparator<T> {
        private int count;

        /**
         * Get the number of comparisons made.
         * @return number of comparisons made
         */
        public int getCount() {
            return count;
        }

        /**
         * Increment the number of comparisons made by one. Call this method in
         * your compare() implementation.
         */
        public void incrementCount() {
            count++;
        }
    }
}
