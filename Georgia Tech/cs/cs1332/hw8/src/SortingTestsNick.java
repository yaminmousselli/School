import org.junit.Before;
import org.junit.Test;

import java.util.Comparator;
import java.util.Random;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

/**
 * Student tests for sorting algorithms.
 *
 * @author Nick Soong
 * @version 1.0
 */
public class SortingTestsNick {
    private HarryPotterSeries[] tas;
    private HarryPotterSeries[] tasByName;
    private ComparatorPlus<HarryPotterSeries> comp;
    private static final int TIMEOUT = 200;

    @Before
    public void setUp() {
        tas = new HarryPotterSeries[7];
        tas[0] = new HarryPotterSeries("1. Harry Potter and the Philosopher's Stone");
        tas[1] = new HarryPotterSeries("2. Harry Potter and the Chamber of Secrets");
        tas[2] = new HarryPotterSeries("3. Harry Potter and the Prisoner of Azkaban");
        tas[3] = new HarryPotterSeries("4. Harry Potter and the Goblet of Fire");
        tas[4] = new HarryPotterSeries("5. Harry Potter and the Order of the Phoenix");
        tas[5] = new HarryPotterSeries("6. Harry Potter and the Half-Blood Prince");
        tas[6] = new HarryPotterSeries("7. Harry Potter and the Deathly Hallows");
        tasByName = new HarryPotterSeries[7];
        tasByName[0] = tas[0];
        tasByName[1] = tas[1];
        tasByName[2] = tas[2];
        tasByName[3] = tas[3];
        tasByName[4] = tas[4];
        tasByName[5] = tas[5];
        tasByName[6] = tas[6];

        comp = HarryPotterSeries.getNameComparator();
    }

    @Test(timeout = TIMEOUT)
    public void testBubbleSort() {
        Sorting.bubbleSort(tas, comp);
        assertArrayEquals(tasByName, tas);
        assertTrue("Number of comparisons: " + comp.getCount(),
                comp.getCount() <= 44);
        System.out.println("You passed the Bubble Sort test!");
    }

    @Test(timeout = TIMEOUT)
    public void testInsertionSort() {
        Sorting.insertionSort(tas, comp);
        assertArrayEquals(tasByName, tas);
        assertTrue("Number of comparisons: " + comp.getCount(),
                comp.getCount() <= 30);
        System.out.println("You passed the Insertion Sort test!");
    }

    @Test
    public void testQuickSort() {
        Sorting.quickSort(tas, comp, new Random(0x600dc0de));
        assertArrayEquals(tasByName, tas);
        assertTrue("Number of comparisons: " + comp.getCount(),
                comp.getCount() <= 45);
        System.out.println("You passed the Quick Sort test!");
    }

    @Test
    public void testMergeSort() {
        Sorting.mergeSort(tas, comp);
        assertArrayEquals(tasByName, tas);
        assertTrue("Number of comparisons: " + comp.getCount(),
                comp.getCount() <= 48);
        System.out.println("You passed the Merge Sort test!");

    }

    @Test
    public void testLsdRadixSort() {
        int[] unsortedArray = new int[] {3, 20, 28, 54, 58, 84, 85, 122};
        int[] sortedArray = new int[] {3, 20, 28, 54, 58, 84, 85, 122};
        assertArrayEquals(sortedArray, Sorting.lsdRadixSort(unsortedArray));
        System.out.println("You passed the LSD Radix Sort test!");
    }

    @Test
    public void testMsdRadixSort() {
        int[] unsortedArray = new int[] {-3, 20, 28, 54, 58, 84, 85, 122};
        int[] sortedArray = new int[] {-3, 20, 28, 54, 58, 84, 85, 122};
        assertArrayEquals(sortedArray, Sorting.msdRadixSort(unsortedArray));
        System.out.println("You passed the MSD Radix Sort test!");
    }

    /**
     * Class for testing proper sorting.
     */
    private static class HarryPotterSeries {
        private String name;

        /**
         * Create a teaching assistant.
         *
         * @param name name of TA
         */
        public HarryPotterSeries(String name) {
            this.name = name;
        }

        /**
         * Get the name of the teaching assistant.
         *
         * @return name of teaching assistant
         */
        public String getName() {
            return name;
        }

        /**
         * Set the name of the teaching assistant.
         *
         * @param name name of the teaching assistant
         */
        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Name: " + name;
        }

        /**
         * Create a comparator that compares the names of the teaching
         * assistants.
         *
         * @return comparator that compares the names of the teaching assistants
         */
        public static ComparatorPlus<HarryPotterSeries> getNameComparator() {
            return new ComparatorPlus<HarryPotterSeries>() {
                @Override
                public int compare(HarryPotterSeries ta1,
                                   HarryPotterSeries ta2) {
                    incrementCount();
                    return ta1.getName().compareTo(ta2.getName());
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
