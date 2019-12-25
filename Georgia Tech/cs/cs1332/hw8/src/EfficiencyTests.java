/**
 * Created by yamin on 4/2/17.
 */
import org.junit.Before;
import org.junit.Test;
import java.util.*;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class EfficiencyTests {
    private ComparatorPlus<Item> comp;
    private static final int TIMEOUT = 0;

    @Before
    public void setUp() {
        comp = Item.getComparator();
    }

    @Test(timeout = TIMEOUT)
    public void testMergeSortSpeed() {
        int n = 150000;
        Item[] arr = new Item[n];
        Item[] copy = new Item[n];
        Random rng = new Random();
        for (int i = 0; i < n - 2; i++) {
            arr[i] = new Item(rng.nextInt());
            if (i % 2 == 0) {
                arr[i].value *= -1;
            }
            copy[i] = arr[i];
        }
        arr[n - 2] = new Item(Integer.MIN_VALUE);
        copy[n - 2] = arr[n - 2];
        arr[n - 1] = new Item(Integer.MAX_VALUE);
        copy[n - 1] = arr[n - 1];
        Sorting.mergeSort(arr, comp);
        Arrays.sort(copy, Item.getComparator());
        assertArrayEquals(copy, arr);
    }

    @Test(timeout = TIMEOUT)
    public void testQuickSortSpeed() {
        int n = 150000;
        Item[] arr = new Item[n];
        Item[] copy = new Item[n];
        Random rng = new Random();
        for (int i = 0; i < n - 2; i++) {
            arr[i] = new Item(rng.nextInt());
            if (i % 2 == 0) {
                arr[i].value *= -1;
            }
            copy[i] = arr[i];
        }
        arr[n - 2] = new Item(Integer.MIN_VALUE);
        copy[n - 2] = arr[n - 2];
        arr[n - 1] = new Item(Integer.MAX_VALUE);
        copy[n - 1] = arr[n - 1];
        Sorting.quickSort(arr, comp, rng);
        Arrays.sort(copy, Item.getComparator());
        assertArrayEquals(copy, arr);
    }

    @Test(timeout = TIMEOUT)
    public void testLSDRadixSortSpeed() {
        int n = 150000;
        int[] arr = new int[n];
        int[] copy = new int[n];
        Random rng = new Random();
        for (int i = 0; i < n - 2; i++) {
            arr[i] = rng.nextInt();
            if (i % 2 == 0) {
                arr[i] *= -1;
            }
            copy[i] = arr[i];
        }
        arr[n - 2] = Integer.MIN_VALUE;
        copy[n - 2] = arr[n - 2];
        arr[n - 1] = Integer.MAX_VALUE;
        copy[n - 1] = arr[n - 1];
        Sorting.lsdRadixSort(arr);
        Arrays.sort(copy);
        assertArrayEquals(copy, arr);
    }

    @Test(timeout = TIMEOUT)
    public void testLSDRadixSortEdgeCase() {
        int n = 150000;
        int[] arr = new int[n];
        int[] copy = new int[n];
        Random rng = new Random();
        int x = rng.nextInt();
        for (int i = 0; i < n; i++) {
            arr[i] = x;
            copy[i] = x;
        }
        Sorting.lsdRadixSort(arr);
        Arrays.sort(copy);
        assertArrayEquals(copy, arr);
    }

    @Test(timeout = TIMEOUT)
    public void testLSDRadixSortZeroCase() {
        int n = 150000;
        int[] arr = new int[n];
        int[] copy = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = 0;
            copy[i] = 0;
        }
        Sorting.lsdRadixSort(arr);
        Arrays.sort(copy);
        assertArrayEquals(copy, arr);
    }

    @Test(timeout = TIMEOUT)
    public void testMSDRadixSortSpeed() {
        int n = 150000;
        int[] arr = new int[n];
        int[] copy = new int[n];
        Random rng = new Random();
        for (int i = 0; i < n - 2; i++) {
            arr[i] = rng.nextInt();
            if (i % 2 == 0) {
                arr[i] *= -1;
            }
            copy[i] = arr[i];
        }
        arr[n - 2] = Integer.MIN_VALUE;
        copy[n - 2] = arr[n - 2];
        arr[n - 1] = Integer.MAX_VALUE;
        copy[n - 1] = arr[n - 1];
        Sorting.msdRadixSort(arr);
        Arrays.sort(copy);
        assertArrayEquals(copy, arr);
    }

    @Test(timeout = TIMEOUT)
    public void testMSDRadixSortEdgeCase() {
        int n = 150000;
        int[] arr = new int[n];
        int[] copy = new int[n];
        Random rng = new Random();
        int x = rng.nextInt();
        for (int i = 0; i < n; i++) {
            arr[i] = x;
            copy[i] = x;
        }
        Sorting.msdRadixSort(arr);
        Arrays.sort(copy);
        assertArrayEquals(copy, arr);
    }

    @Test(timeout = TIMEOUT)
    public void testMSDRadixSortZeroCase() {
        int n = 150000;
        int[] arr = new int[n];
        int[] copy = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = 0;
            copy[i] = 0;
        }
        Sorting.msdRadixSort(arr);
        Arrays.sort(copy);
        assertArrayEquals(copy, arr);
    }


    private static class Item {
        private int value;
        public Item(int value) {
            this.value = value;
        }
        public static ComparatorPlus<Item> getComparator() {
            return new ComparatorPlus<Item>() {
                @Override
                public int compare(Item x, Item y) {
                    incrementCount();
                    return Integer.compare(x.value, y.value);
                }
            };
        }
        @Override
        public boolean equals(Object o) {
            if (o == null) return false;
            if (this == null) return true;
            if (!(o instanceof Item)) return false;
            Item that = (Item) o;
            if (this.value == that.value) return true;
            return false;
        }
    }

    private abstract static class ComparatorPlus<T> implements Comparator<T> {
        private int count;
        public int getCount() {
            return count;
        }
        public void incrementCount() {
            count++;
        }
    }
}
