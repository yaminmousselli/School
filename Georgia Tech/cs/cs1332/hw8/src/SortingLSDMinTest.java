import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

/**
 * Created by Austin on 4/3/17.
 * general array setup modified from Sam Baek's SortingSamTests
 */
public class SortingLSDMinTest {
    private static final int TIMEOUT = 00;
    //this will test a case where you must properly handle
    //Integer.MIN_VALUE
    @Test(timeout = TIMEOUT)
    public void testLsdMin() {
        int[] sortedArray = new int[]{Integer.MIN_VALUE, -918, -520, -315, -53,
                -23, -20, -19, -6, -3, 0, 3, 6, 14, 19, 20, 23, 53, 315, 526, 918};
        int[] unsortedArray = new int[]{3, 6, 19, 526, 918, 0, -6, 14,
                Integer.MIN_VALUE, -20, -19, -315, -53, -918, -23, 20, 23, 53, 315, -520, -3};
        assertArrayEquals(sortedArray, Sorting.lsdRadixSort(unsortedArray));
    }
}