/**
 * Created by yamin on 4/2/17.
 */
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

public class SortingSamTests {
    private Item[] items;
    private Item[] itemsSorted;
    private ComparatorNum<Item> comp;
    private static final int TIMEOUT = 200;

    @Before
    public void setUp() {
        items = new Item[10];
        itemsSorted = new Item[10];

        items[0] = new Item(3, 'a');
        items[1] = new Item(1);
        items[2] = new Item(5, 'a');
        items[3] = new Item(8);
        items[4] = new Item(5, 'b');
        items[5] = new Item(13);
        items[6] = new Item(2);
        items[7] = new Item(3, 'b');
        items[8] = new Item(40);
        items[9] = new Item(5, 'c');

        itemsSorted[0] = items[1];
        itemsSorted[1] = items[6];
        itemsSorted[2] = items[0];
        itemsSorted[3] = items[7];
        itemsSorted[4] = items[2];
        itemsSorted[5] = items[4];
        itemsSorted[6] = items[9];
        itemsSorted[7] = items[3];
        itemsSorted[8] = items[5];
        itemsSorted[9] = items[8];

        comp = Item.getNumComparator();
    }

    @Test(timeout = TIMEOUT)
    public void testBubbleSort() {
        Sorting.bubbleSort(items, comp);
        assertArrayEquals(itemsSorted, items);
        assertTrue("Number of comparisons: " + comp.getCount(),
                comp.getCount() <= 44);    // got 39
    }

    @Test(timeout = TIMEOUT)
    public void testBubbleSorted() {
        items = new Item[100];
        itemsSorted = new Item[100];
        for (int i = 0; i < items.length; i++) {
            items[i] = new Item(i);
            itemsSorted[i] = items[i];
        }
        Sorting.bubbleSort(items, comp);
        assertArrayEquals(itemsSorted, items);
        assertTrue("Number of comparisons: " + comp.getCount(),
                comp.getCount() <= items.length);    // got 99 (items.length - 1)
    }

    @Test(timeout = TIMEOUT)
    public void testBubbleEfficiency() {
        items = new Item[100];
        itemsSorted = new Item[100];
        for (int i = 0; i < items.length; i++) {
            items[i] = new Item(i);
            itemsSorted[i] = items[i];
        }
        // first swap
        Item temp = items[0];
        items[0] = items[1];
        items[1] = temp;
        // second swap
        temp = items[90];
        items[90] = items[91];
        items[91] = temp;
        // third swap
        temp = items[50];
        items[50] = items[51];
        items[51] = temp;
        Sorting.bubbleSort(items, comp);
        assertArrayEquals(itemsSorted, items);
        assertTrue("Number of comparisons: " + comp.getCount(),
                comp.getCount() <= (items.length - 1) + (items.length - 2));
    }

    @Test(timeout = TIMEOUT)
    public void testBubbleReversed() {
        items = new Item[100];
        itemsSorted = new Item[100];
        for (int i = 0; i < items.length; i++) {
            items[i] = new Item(i);
            itemsSorted[i] = items[i];
        }
        // reversing the array
        for (int i = 0; i < items.length / 2; i++) {
            Item temp = items[i];
            items[i] = items[items.length - i - 1];
            items[items.length - i - 1] = temp;
        }
        Sorting.bubbleSort(items, comp);
        assertArrayEquals(itemsSorted, items);
        assertTrue("Number of comparisons: " + comp.getCount(),
                comp.getCount() <= (items.length - 1) * (items.length) / 2);
    }

    @Test(timeout = TIMEOUT)
    public void testBubbleOneSwap() {
        items = new Item[100];
        itemsSorted = new Item[100];
        itemsSorted[99] = new Item(99);
        for (int i = 0; i < items.length - 1; i++) {
            itemsSorted[i] = new Item(i);
            items[i + 1] = itemsSorted[i];
        }
        items[0] = itemsSorted[99];
        Sorting.bubbleSort(items, comp);
        assertArrayEquals(itemsSorted, items);
        assertTrue("Number of comparisons: " + comp.getCount(),
                comp.getCount() <= (items.length - 1) + (items.length - 2));
    }

    @Test(timeout = TIMEOUT)
    public void testBubbleMany() {
        for (int j = 0; j < 100; j++) {
            items = new Item[100];
            itemsSorted = new Item[100];
            comp = Item.getNumComparator();
            for (int i = 0; i < items.length; i++) {
                items[i] = new Item(i);
                itemsSorted[i] = items[i];
            }
            // shuffling the array
            for (int i = 0; i < items.length; i++) {
                int index = (int) (Math.random() * items.length);
                Item temp = items[index];
                items[index] = items[i];
                items[i] = temp;
            }
            Sorting.bubbleSort(items, comp);
            assertArrayEquals(itemsSorted, items);
            assertTrue("Number of comparisons: " + comp.getCount(),
                    comp.getCount() <= (items.length - 1) * (items.length) / 2);
        }
    }

    @Test(timeout = TIMEOUT)
    public void testInsertionSort() {
        Sorting.insertionSort(items, comp);
        assertArrayEquals(itemsSorted, items);
        assertTrue("Number of comparisons: " + comp.getCount(),
                comp.getCount() <= 30);    // got 22
    }

    @Test(timeout = TIMEOUT)
    public void testInsertionSorted() {
        items = new Item[100];
        itemsSorted = new Item[100];
        for (int i = 0; i < items.length; i++) {
            items[i] = new Item(i);
            itemsSorted[i] = items[i];
        }
        Sorting.insertionSort(items, comp);
        assertArrayEquals(itemsSorted, items);
        assertTrue("Number of comparisons: " + comp.getCount(),
                comp.getCount() <= items.length - 1);
    }

    @Test(timeout = TIMEOUT)
    public void testInsertionReversed() {
        items = new Item[100];
        itemsSorted = new Item[100];
        for (int i = 0; i < items.length; i++) {
            items[i] = new Item(i);
            itemsSorted[i] = items[i];
        }
        // reversing the array
        for (int i = 0; i < items.length / 2; i++) {
            Item temp = items[i];
            items[i] = items[items.length - i - 1];
            items[items.length - i - 1] = temp;
        }
        Sorting.insertionSort(items, comp);
        assertArrayEquals(itemsSorted, items);
        assertTrue("Number of comparisons: " + comp.getCount(),
                comp.getCount() <= (items.length - 1) * (items.length) / 2);
    }

    @Test(timeout = TIMEOUT)
    public void testInsertionMany() {
        for (int j = 0; j < 100; j++) {
            items = new Item[100];
            itemsSorted = new Item[100];
            comp = Item.getNumComparator();
            for (int i = 0; i < items.length; i++) {
                items[i] = new Item(i);
                itemsSorted[i] = items[i];
            }
            // shuffling the array
            for (int i = 0; i < items.length; i++) {
                int index = (int) (Math.random() * items.length);
                Item temp = items[index];
                items[index] = items[i];
                items[i] = temp;
            }
            Sorting.insertionSort(items, comp);
            assertArrayEquals(itemsSorted, items);
            assertTrue("Number of comparisons: " + comp.getCount(),
                    comp.getCount() <= (items.length - 1) * (items.length) / 2);
        }
    }

    @Test(timeout = TIMEOUT)
    public void testQuickSort() {
        items[0] = new Item(3);
        items[1] = new Item(1);
        items[2] = new Item(5);
        items[3] = new Item(8);
        items[4] = new Item(142);
        items[5] = new Item(13);
        items[6] = new Item(2);
        items[7] = new Item(125);
        items[8] = new Item(40);
        items[9] = new Item(413);

        itemsSorted[0] = items[1];
        itemsSorted[1] = items[6];
        itemsSorted[2] = items[0];
        itemsSorted[3] = items[2];
        itemsSorted[4] = items[3];
        itemsSorted[5] = items[5];
        itemsSorted[6] = items[8];
        itemsSorted[7] = items[7];
        itemsSorted[8] = items[4];
        itemsSorted[9] = items[9];

        Sorting.quickSort(items, comp, new Random(0));
        assertArrayEquals(itemsSorted, items);
        assertTrue("Number of comparisons: " + comp.getCount(),
                comp.getCount() <= 44);    // got 25
    }

    @Test(timeout = TIMEOUT)
    public void testQuickSorted() {
        items = new Item[100];
        itemsSorted = new Item[100];
        for (int i = 0; i < items.length; i++) {
            items[i] = new Item(i);
            itemsSorted[i] = items[i];
        }
        Sorting.quickSort(items, comp, new Random());
        assertArrayEquals(itemsSorted, items);
        assertTrue("Number of comparisons: " + comp.getCount(),
                comp.getCount() <= (items.length - 1) * (items.length - 2) / 2);
    }

    @Test(timeout = TIMEOUT)
    public void testQuickWorstCase() {
        items = new Item[100];
        itemsSorted = new Item[100];
        for (int i = 0; i < items.length; i++) {
            items[i] = new Item(i);
            itemsSorted[i] = items[i];
        }

        class ZeroRandom extends Random {
            @Override
            public int nextInt(int bound) {
                return 0;
            }
        }
        Sorting.quickSort(items, comp, new ZeroRandom());
        assertArrayEquals(itemsSorted, items);
        assertTrue("Number of comparisons: " + comp.getCount(),
                comp.getCount() <= (items.length - 1) * (items.length - 2));
        assertTrue("Number of comparisons: " + comp.getCount(),
                comp.getCount() >= (items.length - 1) * (items.length - 2) / 2);
    }

    @Test(timeout = TIMEOUT)
    public void testQuickMany() {
        for (int j = 0; j < 100; j++) {
            items = new Item[100];
            itemsSorted = new Item[100];
            comp = Item.getNumComparator();
            for (int i = 0; i < items.length; i++) {
                items[i] = new Item(i);
                itemsSorted[i] = items[i];
            }
            // shuffling the array
            for (int i = 0; i < items.length; i++) {
                int index = (int) (Math.random() * items.length);
                Item temp = items[index];
                items[index] = items[i];
                items[i] = temp;
            }
            Sorting.quickSort(items, comp, new Random());
            assertArrayEquals(itemsSorted, items);
            assertTrue("Number of comparisons: " + comp.getCount(),
                    comp.getCount() <= (items.length - 1) * (items.length));
        }
    }

    @Test(timeout = TIMEOUT)
    public void testMergeSort() {
        Sorting.mergeSort(items, comp);
        assertArrayEquals(itemsSorted, items);
        assertTrue("Number of comparisons: " + comp.getCount(),
                comp.getCount() <= 25);    // got 20
    }

    @Test(timeout = TIMEOUT)
    public void testMergeSorted() {
        items = new Item[1024];
        itemsSorted = new Item[1024];
        for (int i = 0; i < items.length; i++) {
            items[i] = new Item(i);
            itemsSorted[i] = items[i];
        }
        Sorting.mergeSort(items, comp);
        assertArrayEquals(itemsSorted, items);
        assertTrue("Number of comparisons: " + comp.getCount(),
                comp.getCount() <= 5500);    // got 5120
    }

    @Test(timeout = TIMEOUT)
    public void testMergeMany() {
        for (int j = 0; j < 100; j++) {
            items = new Item[100];
            itemsSorted = new Item[100];
            comp = Item.getNumComparator();
            for (int i = 0; i < items.length; i++) {
                items[i] = new Item(i);
                itemsSorted[i] = items[i];
            }
            // shuffling the array
            for (int i = 0; i < items.length; i++) {
                int index = (int) (Math.random() * items.length);
                Item temp = items[index];
                items[index] = items[i];
                items[i] = temp;
            }
            Sorting.mergeSort(items, comp);
            assertArrayEquals(itemsSorted, items);
            assertTrue("Number of comparisons: " + comp.getCount(),
                    comp.getCount() <= items.length * Math.log(items.length) * 2);
        }
    }

    @Test(timeout = TIMEOUT)
    public void testLsdZeros() {
        int[] sortedArray = new int[] {0, 0, 0, 0, 0};
        int[] unsortedArray = new int[] {0, 0, 0, 0, 0};
        assertArrayEquals(sortedArray, Sorting.lsdRadixSort(unsortedArray));
    }

    @Test(timeout = TIMEOUT)
    public void testLsdPositive() {
        int[] sortedArray = new int[] {3, 6, 19, 20, 23, 53, 315, 526, 918};
        int[] unsortedArray = new int[] {6, 20, 19, 315, 53, 918, 23, 526, 3};
        assertArrayEquals(sortedArray, Sorting.lsdRadixSort(unsortedArray));
    }

    @Test(timeout = TIMEOUT)
    public void testLsdNegative() {
        int[] sortedArray = new int[] {-918, -520, -315, -53, -23, -20, -19, -6, -3};
        int[] unsortedArray = new int[] {-6, -20, -19, -315, -53, -918, -23, -520, -3};
        assertArrayEquals(sortedArray, Sorting.lsdRadixSort(unsortedArray));
    }

    @Test(timeout = TIMEOUT)
    public void testLsdMixed() {
        int[] sortedArray = new int[] {-918, -520, -315, -53, -23, -20, -19, -6,
                -3, 0, 3, 6, 19, 20, 23, 53, 315, 526, 918};
        int[] unsortedArray = new int[] {3, 6, 19, 526, 918, 0, -6, -20, -19,
                -315, -53, -918, -23, 20, 23, 53, 315, -520, -3};
        assertArrayEquals(sortedArray, Sorting.lsdRadixSort(unsortedArray));
    }

    @Test(timeout = TIMEOUT)
    public void testLsdMinMax() {
        int[] sortedArray = new int[] {Integer.MIN_VALUE, -918, -520, -315, -53,
                -23, -20, -19, -6, -3, 0, 3, 6, 19, 20, 23, 53, 315, 526, 918, Integer.MAX_VALUE};
        int[] unsortedArray = new int[] {3, 6, 19, 526, 918, 0, -6, Integer.MAX_VALUE,
                Integer.MIN_VALUE, -20, -19, -315, -53, -918, -23, 20, 23, 53, 315, -520, -3};
        assertArrayEquals(sortedArray, Sorting.lsdRadixSort(unsortedArray));
    }

    @Test(timeout = TIMEOUT)
    public void testLsdMany() {
        for (int j = 0; j < 100; j++) {
            int[] items = new int[100];
            int[] itemsSorted = new int[100];
            Random random = new Random();
            for (int i = 0; i < items.length; i++) {
                items[i] = random.nextInt();
                itemsSorted[i] = items[i];
            }
            // shuffling the array
            for (int i = 0; i < items.length; i++) {
                int index = (int) (Math.random() * items.length);
                int temp = items[index];
                items[index] = items[i];
                items[i] = temp;
            }
            Sorting.lsdRadixSort(items);
            Arrays.sort(itemsSorted);
            assertArrayEquals(itemsSorted, items);
        }
    }

    @Test(timeout = TIMEOUT)
    public void testMsdZeros() {
        int[] sortedArray = new int[] {0, 0, 0, 0, 0};
        int[] unsortedArray = new int[] {0, 0, 0, 0, 0};
        assertArrayEquals(sortedArray, Sorting.msdRadixSort(unsortedArray));
    }

    @Test//(timeout = TIMEOUT)
    public void testMsdPositive() {
        int[] sortedArray = new int[] {3, 6, 19, 20, 23, 53, 315, 526, 918};
        int[] unsortedArray = new int[] {6, 20, 19, 315, 53, 918, 23, 526, 3};
        assertArrayEquals(sortedArray, Sorting.msdRadixSort(unsortedArray));
    }

    @Test(timeout = TIMEOUT)
    public void testMsdNegative() {
        int[] sortedArray = new int[] {-918, -520, -315, -53, -23, -20, -19, -6, -3};
        int[] unsortedArray = new int[] {-6, -20, -19, -315, -53, -918, -23, -520, -3};
        assertArrayEquals(sortedArray, Sorting.msdRadixSort(unsortedArray));
    }

    @Test(timeout = TIMEOUT)
    public void testMsdMixed() {
        int[] sortedArray = new int[] {-918, -520, -315, -53, -23, -20, -19, -6,
                -3, 0, 3, 6, 19, 20, 23, 53, 315, 526, 918};
        int[] unsortedArray = new int[] {3, 6, 19, 526, 918, 0, -6, -20, -19,
                -315, -53, -918, -23, 20, 23, 53, 315, -520, -3};
        assertArrayEquals(sortedArray, Sorting.msdRadixSort(unsortedArray));
    }

    @Test(timeout = TIMEOUT)
    public void testMsdMinMax() {
        int[] sortedArray = new int[] {Integer.MIN_VALUE, -918, -520, -315, -53,
                -23, -20, -19, -6, -3, 0, 3, 6, 19, 20, 23, 53, 315, 526, 918, Integer.MAX_VALUE};
        int[] unsortedArray = new int[] {3, 6, 19, 526, 918, 0, -6, Integer.MAX_VALUE,
                Integer.MIN_VALUE, -20, -19, -315, -53, -918, -23, 20, 23, 53, 315, -520, -3};
        assertArrayEquals(sortedArray, Sorting.msdRadixSort(unsortedArray));
    }

    @Test(timeout = TIMEOUT)
    public void testMsdMany() {
        for (int j = 0; j < 100; j++) {
            int[] items = new int[100];
            int[] itemsSorted = new int[100];
            Random random = new Random();
            for (int i = 0; i < items.length; i++) {
                items[i] = random.nextInt();
                itemsSorted[i] = items[i];
            }
            // shuffling the array
            for (int i = 0; i < items.length; i++) {
                int index = (int) (Math.random() * items.length);
                int temp = items[index];
                items[index] = items[i];
                items[i] = temp;
            }
            Sorting.msdRadixSort(items);
            Arrays.sort(itemsSorted);
            assertArrayEquals(itemsSorted, items);
        }
    }


    /**
     * tests empty and one element array,
     * also makes sure that there're no comparisons made
     */

    @Test(timeout = TIMEOUT)
    public void testBubbleEmpty() {
        items = new Item[0];
        itemsSorted = new Item[0];
        Sorting.bubbleSort(items, comp);
        assertArrayEquals(itemsSorted, items);
        assertTrue("Number of comparisons: " + comp.getCount(),
                comp.getCount() == 0);
    }

    @Test(timeout = TIMEOUT)
    public void testBubbleOne() {
        items = new Item[1];
        items[0] = new Item(3);
        itemsSorted = new Item[1];
        itemsSorted[0] = new Item(3);
        Sorting.bubbleSort(itemsSorted, comp);
        assertTrue("Number of comparisons: " + comp.getCount(),
                comp.getCount() == 0);
    }

    @Test(timeout = TIMEOUT)
    public void testInsertionEmpty() {
        items = new Item[0];
        itemsSorted = new Item[0];
        Sorting.insertionSort(items, comp);
        assertArrayEquals(itemsSorted, items);
        assertTrue("Number of comparisons: " + comp.getCount(),
                comp.getCount() == 0);
    }

    @Test(timeout = TIMEOUT)
    public void testInsertionOne() {
        items = new Item[1];
        items[0] = new Item(3);
        itemsSorted = new Item[1];
        itemsSorted[0] = new Item(3);
        Sorting.insertionSort(itemsSorted, comp);
        assertTrue("Number of comparisons: " + comp.getCount(),
                comp.getCount() == 0);
    }

    @Test(timeout = TIMEOUT)
    public void testQuickEmpty() {
        items = new Item[0];
        itemsSorted = new Item[0];
        Sorting.quickSort(items, comp, new Random());
        assertArrayEquals(itemsSorted, items);
        assertTrue("Number of comparisons: " + comp.getCount(),
                comp.getCount() == 0);
    }

    @Test(timeout = TIMEOUT)
    public void testQuickOne() {
        items = new Item[1];
        items[0] = new Item(3);
        itemsSorted = new Item[1];
        itemsSorted[0] = new Item(3);
        Sorting.quickSort(itemsSorted, comp, new Random());
        assertTrue("Number of comparisons: " + comp.getCount(),
                comp.getCount() == 0);
    }

    @Test(timeout = TIMEOUT)
    public void testMergeEmpty() {
        items = new Item[0];
        itemsSorted = new Item[0];
        Sorting.mergeSort(items, comp);
        assertArrayEquals(itemsSorted, items);
        assertTrue("Number of comparisons: " + comp.getCount(),
                comp.getCount() == 0);
    }

    @Test(timeout = TIMEOUT)
    public void testMergeOne() {
        items = new Item[1];
        items[0] = new Item(3);
        itemsSorted = new Item[1];
        itemsSorted[0] = new Item(3);
        Sorting.mergeSort(itemsSorted, comp);
        assertTrue("Number of comparisons: " + comp.getCount(),
                comp.getCount() == 0);
    }

    @Test(timeout = TIMEOUT)
    public void testLsdEmpty() {
        int[] nums = new int[0];
        int[] numsSorted = new int[0];
        Sorting.lsdRadixSort(nums);
        assertArrayEquals(numsSorted, nums);
    }

    @Test(timeout = TIMEOUT)
    public void testLsdOne() {
        int[] nums = new int[1];
        nums[0] = 3;
        int[] numsSorted = new int[1];
        numsSorted[0] = 3;
        Sorting.lsdRadixSort(nums);
        assertArrayEquals(numsSorted, nums);
    }

    @Test(timeout = TIMEOUT)
    public void testMsdEmpty() {
        int[] nums = new int[0];
        int[] numsSorted = new int[0];
        Sorting.msdRadixSort(nums);
        assertArrayEquals(numsSorted, nums);
    }

    @Test(timeout = TIMEOUT)
    public void testMsdOne() {
        int[] nums = new int[1];
        nums[0] = 3;
        int[] numsSorted = new int[1];
        numsSorted[0] = 3;
        Sorting.msdRadixSort(nums);
        assertArrayEquals(numsSorted, nums);
    }

    /**
     * tests all possible exceptions
     */

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testBubbleArrNull() {
        Sorting.bubbleSort(null, comp);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testBubbleCompNull() {
        Sorting.bubbleSort(items, null);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testInsertionArrNull() {
        Sorting.insertionSort(null, comp);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testInsertionCompNull() {
        Sorting.insertionSort(items, null);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testQuickArrNull() {
        Sorting.quickSort(null, comp, new Random());
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testQuickCompNull() {
        Sorting.quickSort(items, null, new Random());
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testQuickRandomNull() {
        Sorting.quickSort(items, comp, null);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testMergeArrNull() {
        Sorting.mergeSort(null, comp);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testMergeCompNull() {
        Sorting.mergeSort(items, null);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testLsdArrNull() {
        Sorting.lsdRadixSort(null);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testMsdArrNull() {
        Sorting.msdRadixSort(null);
    }


    /**
     * Class for testing sorting.
     * It primarily sorts the Integer values,
     * but has char to test stability sortings
     */
    private static class Item {
        private int num;
        private char letter;

        public Item(int num) {
            this(num, 'a');
        }

        public Item(int num, char letter) {
            this.num = num;
            this.letter = letter;
        }

        public int getNum() {
            return num;
        }

        public char getLetter() {
            return letter;
        }

        @Override
        public String toString() {
            return num + ":" + letter;
        }

        /**
         * Create a comparator that compares the Item (nums only)
         *
         * @return comparator that compares the Item (nums only)
         */
        public static ComparatorNum<Item> getNumComparator() {
            return new ComparatorNum<Item>() {
                @Override
                public int compare(Item o1, Item o2) {
                    incrementCount();
                    return o1.getNum() - o2.getNum();
                }
            };
        }
    }

    /**
     * Inner class that allows counting how many comparisons were made.
     */
    private abstract static class ComparatorNum<T> implements Comparator<T> {
        private int count;

        /**
         * Get the number of comparisons made.
         *
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
