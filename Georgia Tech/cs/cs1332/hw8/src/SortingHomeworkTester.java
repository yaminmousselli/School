
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Tests for sorting algorithms.
 *
 * @author Karan Achtani
 * @version 1.0
 * boiler plate code taken from 1332 TA's SortingStudentTests.java
 */
public class SortingHomeworkTester {
    private Person[] peopleByName;
    private Person[] peopleRandom;
    private Person[] peopleReversed;
    private Person[] peopleInOrder;
    private Person[] duplicatePeople;
    private Person[] duplicatePeopleOrdered;
    private ComparatorPlus<Person> comp;
    private static final int TIMEOUT = 200;

    @Before
    public void setUp() {
        peopleByName = new Person[10];
        peopleByName[0] = new Person("Alpha");
        peopleByName[1] = new Person("Beta");
        peopleByName[2] = new Person("Delta");
        peopleByName[3] = new Person("Epsilon");
        peopleByName[4] = new Person("Iota");
        peopleByName[5] = new Person("Kappa");
        peopleByName[6] = new Person("Mu");
        peopleByName[7] = new Person("Nu");
        peopleByName[8] = new Person("Rho");
        peopleByName[9] = new Person("Sigma");
        peopleReversed = new Person[10];
        peopleReversed[0] = peopleByName[9];
        peopleReversed[1] = peopleByName[8];
        peopleReversed[2] = peopleByName[7];
        peopleReversed[3] = peopleByName[6];
        peopleReversed[4] = peopleByName[5];
        peopleReversed[5] = peopleByName[4];
        peopleReversed[6] = peopleByName[3];
        peopleReversed[7] = peopleByName[2];
        peopleReversed[8] = peopleByName[1];
        peopleReversed[9] = peopleByName[0];
        peopleRandom = new Person[10];
        peopleRandom[0] = peopleByName[8];
        peopleRandom[1] = peopleByName[0];
        peopleRandom[2] = peopleByName[9];
        peopleRandom[3] = peopleByName[6];
        peopleRandom[4] = peopleByName[3];
        peopleRandom[5] = peopleByName[2];
        peopleRandom[6] = peopleByName[5];
        peopleRandom[7] = peopleByName[1];
        peopleRandom[8] = peopleByName[4];
        peopleRandom[9] = peopleByName[7];
        peopleInOrder = peopleByName.clone();

        duplicatePeople = new Person[10];
        duplicatePeople[0] = new Person("Alpha");
        duplicatePeople[1] = new Person("Beta");
        duplicatePeople[2] = new Person("Delta");
        duplicatePeople[3] = new Person("Epsilon");
        duplicatePeople[4] = new Person("Iota");
        duplicatePeople[5] = new Person("Alpha");
        duplicatePeople[6] = new Person("Beta");
        duplicatePeople[7] = new Person("Delta");
        duplicatePeople[8] = new Person("Beta");
        duplicatePeople[9] = new Person("Alpha");
        duplicatePeopleOrdered = new Person[10];
        duplicatePeopleOrdered[0] = duplicatePeople[0];
        duplicatePeopleOrdered[1] = duplicatePeople[5];
        duplicatePeopleOrdered[2] = duplicatePeople[9];
        duplicatePeopleOrdered[3] = duplicatePeople[1];
        duplicatePeopleOrdered[4] = duplicatePeople[6];
        duplicatePeopleOrdered[5] = duplicatePeople[8];
        duplicatePeopleOrdered[6] = duplicatePeople[2];
        duplicatePeopleOrdered[7] = duplicatePeople[7];
        duplicatePeopleOrdered[8] = duplicatePeople[3];
        duplicatePeopleOrdered[9] = duplicatePeople[4];

        comp = Person.getNameComparator();
    }

    @Test(timeout = TIMEOUT)
    public void testRandomArrayBubble() {
        Sorting.bubbleSort(peopleRandom, comp);
        assertArrayEquals(peopleRandom, peopleByName);
        assertTrue("Number of comparisons: " + comp.getCount(),
                comp.getCount() <= 50); // my code got 42 comparisons
    }

    @Test(timeout = TIMEOUT)
    public void testRandomArrayInsertion() {
        Sorting.insertionSort(peopleRandom, comp);
        assertArrayEquals(peopleRandom, peopleByName);
        assertTrue("Number of comparisons: " + comp.getCount(),
                comp.getCount() <= 40); // my code got 33 comparisons
    }

    @Test(timeout = TIMEOUT)
    public void testRandomArrayQuick() {
        Sorting.quickSort(peopleRandom, comp, new Random(5));
        assertArrayEquals(peopleRandom, peopleByName);
        assertTrue("Number of comparisons: " + comp.getCount(),
                comp.getCount() <= 30); // my code got 25 comparisons
    }

    @Test(timeout = TIMEOUT)
    public void testRandomArrayMerge() {
        Sorting.mergeSort(peopleRandom, comp);
        assertArrayEquals(peopleRandom, peopleByName);
        assertTrue("Number of comparisons: " + comp.getCount(),
                comp.getCount() <= 30); // my code got 22 comparisons
    }

    @Test(timeout = TIMEOUT)
    public void testRandomLsdRadixSort() {
        int[] unsortedArray = new int[] {11173, 34747, 92159, 7991, 41665, 48557, 22326, 215, 4, 48557, 0};
        int[] sortedArray = new int[] {0, 4, 215, 7991, 11173, 22326, 34747, 41665, 48557, 48557, 92159};
        assertArrayEquals(sortedArray, Sorting.lsdRadixSort(unsortedArray));
    }

    @Test(timeout = TIMEOUT)
    public void testMsdRandomRadixSort() {
        int[] unsortedArray = new int[] {11173, 34747, 92159, 7991, 41665, 48557, 22326, 215, 4, 48557, 0};
        int[] sortedArray = new int[] {0, 4, 215, 7991, 11173, 22326, 34747, 41665, 48557, 48557, 92159};
        assertArrayEquals(sortedArray, Sorting.msdRadixSort(unsortedArray));
    }

    @Test(timeout = TIMEOUT)
    public void testReversedArrayBubble() {
        Sorting.bubbleSort(peopleReversed, comp);
        assertArrayEquals(peopleReversed, peopleByName);
        assertTrue("Number of comparisons: " + comp.getCount(),
                comp.getCount() <= 50); // my code got 45 comparisons
    }

    @Test(timeout = TIMEOUT)
    public void testReversedArrayInsertion() {
        Sorting.insertionSort(peopleReversed, comp);
        assertArrayEquals(peopleReversed, peopleByName);
        assertTrue("Number of comparisons: " + comp.getCount(),
                comp.getCount() <= 50); // my code got 45 comparisons
    }

    @Test(timeout = TIMEOUT)
    public void testReversedArrayQuick() {
        Sorting.quickSort(peopleReversed, comp, new Random(5));
        assertArrayEquals(peopleReversed, peopleByName);
        assertTrue("Number of comparisons: " + comp.getCount(),
                comp.getCount() <= 35); // my code got 30 comparisons
    }

    @Test(timeout = TIMEOUT)
    public void testReversedArrayMerge() {
        Sorting.mergeSort(peopleReversed, comp);
        assertArrayEquals(peopleReversed, peopleByName);
        assertTrue("Number of comparisons: " + comp.getCount(),
                comp.getCount() <= 20); // my code got 15 comparisons
    }

    @Test(timeout = TIMEOUT)
    public void testReversedLsdRadixSort() {
        int[] unsortedArray = new int[] {1234, 999, 998, 500, 3, 3, 0, 0, -1, -98, -99, -651, -1121, -10000};
        int[] sortedArray = new int[] {-10000, -1121, -651, -99, -98, -1, 0, 0, 3, 3, 500, 998, 999, 1234};
        assertArrayEquals(sortedArray, Sorting.lsdRadixSort(unsortedArray));
    }

    @Test(timeout = TIMEOUT)
    public void testReversedMsdRadixSort() {
        int[] unsortedArray = new int[] {1234, 999, 998, 500, 3, 3, 0, 0, -1, -98, -99, -651, -1121, -10000};
        int[] sortedArray = new int[] {-10000, -1121, -651, -99, -98, -1, 0, 0, 3, 3, 500, 998, 999, 1234};
        assertArrayEquals(sortedArray, Sorting.msdRadixSort(unsortedArray));
    }

    @Test(timeout = TIMEOUT)
    public void testOrderedArrayBubble() {
        Sorting.bubbleSort(peopleInOrder, comp);
        assertArrayEquals(peopleInOrder, peopleByName);
        assertTrue("Number of comparisons: " + comp.getCount(),
                comp.getCount() <= 15); // my code had 9 comparisons
    }

    @Test(timeout = TIMEOUT)
    public void testOrderedArrayInsertion() {
        Sorting.insertionSort(peopleInOrder, comp);
        assertArrayEquals(peopleInOrder, peopleByName);
        assertTrue("Number of comparisons: " + comp.getCount(),
                comp.getCount() <= 15); // my code had 9 comparisons
    }

    @Test(timeout = TIMEOUT)
    public void testOrderedArrayQuick() {
        Sorting.quickSort(peopleInOrder, comp, new Random(5));
        assertArrayEquals(peopleInOrder, peopleByName);
        assertTrue("Number of comparisons: " + comp.getCount(),
                comp.getCount() <= 35); // my code had 28 comparisons
    }

    @Test(timeout = TIMEOUT)
    public void testOrderedArrayMerge() {
        Sorting.mergeSort(peopleInOrder, comp);
        assertArrayEquals(peopleInOrder, peopleByName);
        assertTrue("Number of comparisons: " + comp.getCount(),
                comp.getCount() <= 25); // my code had 19 comparisons
    }

    @Test(timeout = TIMEOUT)
    public void testOrderedLsdRadixSort() {
        int[] unsortedArray = new int[] {-2, -1, 0, 1, 3, 3, 98, 99, 500, 998, 999, 1234};
        int[] sortedArray = new int[] {-2, -1, 0, 1, 3, 3, 98, 99, 500, 998, 999, 1234};
        assertArrayEquals(sortedArray, Sorting.lsdRadixSort(unsortedArray));
    }

    @Test(timeout = TIMEOUT)
    public void testOrderedMsdRadixSort() {
        int[] unsortedArray = new int[] {-2, -1, 0, 1, 3, 3, 98, 99, 500, 998, 999, 1234};
        int[] sortedArray = new int[] {-2, -1, 0, 1, 3, 3, 98, 99, 500, 998, 999, 1234};
        assertArrayEquals(sortedArray, Sorting.msdRadixSort(unsortedArray));
    }

    @Test(timeout = TIMEOUT)
    public void testDuplicateArrayBubble() {
        Sorting.bubbleSort(duplicatePeople, comp);
        assertArrayEquals(duplicatePeople, duplicatePeopleOrdered);
        assertTrue("Number of comparisons: " + comp.getCount(),
                comp.getCount() <= 50); // my code had 44 comparisons
    }

    @Test(timeout = TIMEOUT)
    public void testDuplicateArrayInsertion() {
        Sorting.insertionSort(duplicatePeople, comp);
        assertArrayEquals(duplicatePeople, duplicatePeopleOrdered);
        assertTrue("Number of comparisons: " + comp.getCount(),
                comp.getCount() <= 35); // my code had 29 comparisons
    }

    @Test(timeout = TIMEOUT)
    public void testDuplicateArrayMerge() {
        Sorting.mergeSort(duplicatePeople, comp);
        assertArrayEquals(duplicatePeople, duplicatePeopleOrdered);
        assertTrue("Number of comparisons: " + comp.getCount(),
                comp.getCount() <= 30); // my code had 23 comparisons
    }

    @Test(timeout = TIMEOUT)
    public void testSingleEntryArray() {
        Person[] test = {new Person("Test")};
        Person[] test2 = test.clone();
        Sorting.bubbleSort(test, comp);
        assertArrayEquals(test, test2);
        Sorting.insertionSort(test, comp);
        assertArrayEquals(test, test2);
        Sorting.mergeSort(test, comp);
        assertArrayEquals(test, test2);
        Sorting.quickSort(test, comp, new Random(0));
        assertArrayEquals(test, test2);

        int[] tester = {1};
        int[] tester2 = {1};
        Sorting.lsdRadixSort(tester);
        assertArrayEquals(tester, tester2);
        Sorting.msdRadixSort(tester);
        assertArrayEquals(tester, tester2);
    }

    @Test(timeout = TIMEOUT)
    public void testEmptyArray() {
        Person[] test = {};
        Person[] test2 = test.clone();
        Sorting.bubbleSort(test, comp);
        assertArrayEquals(test, test2);
        Sorting.insertionSort(test, comp);
        assertArrayEquals(test, test2);
        Sorting.mergeSort(test, comp);
        assertArrayEquals(test, test2);
        Sorting.quickSort(test, comp, new Random(0));
        assertArrayEquals(test, test2);

        int[] tester = {};
        int[] tester2 = {};
        Sorting.lsdRadixSort(tester);
        assertArrayEquals(tester, tester2);
        Sorting.msdRadixSort(tester);
        assertArrayEquals(tester, tester2);
    }

    @Test(timeout = TIMEOUT)
    public void testExceptions() {
        boolean caught = false;
        try {
            Sorting.bubbleSort(null, comp);
        } catch (IllegalArgumentException e) {
            caught = true;
        }
        if (!caught) fail("Exception not thrown.");
        caught = false;
        try {
            Sorting.bubbleSort(peopleRandom, null);
        } catch (IllegalArgumentException e) {
            caught = true;
        }
        if (!caught) fail("Exception not thrown.");
        caught = false;
        try {
            Sorting.insertionSort(null, comp);
        } catch (IllegalArgumentException e) {
            caught = true;
        }
        if (!caught) fail("Exception not thrown.");
        caught = false;
        try {
            Sorting.insertionSort(peopleRandom, null);
        } catch (IllegalArgumentException e) {
            caught = true;
        }
        if (!caught) fail("Exception not thrown.");
        caught = false;
        try {
            Sorting.mergeSort(null, comp);
        } catch (IllegalArgumentException e) {
            caught = true;
        }
        if (!caught) fail("Exception not thrown.");
        caught = false;
        try {
            Sorting.mergeSort(peopleRandom, null);
        } catch (IllegalArgumentException e) {
            caught = true;
        }
        if (!caught) fail("Exception not thrown.");
        caught = false;
        try {
            Sorting.quickSort(null, comp, new Random());
        } catch (IllegalArgumentException e) {
            caught = true;
        }
        if (!caught) fail("Exception not thrown.");
        caught = false;
        try {
            Sorting.quickSort(peopleRandom, null, new Random());
        } catch (IllegalArgumentException e) {
            caught = true;
        }
        if (!caught) fail("Exception not thrown.");
        caught = false;
        try {
            Sorting.quickSort(peopleRandom, comp, null);
        } catch (IllegalArgumentException e) {
            caught = true;
        }
        if (!caught) fail("Exception not thrown.");
    }

    @Test(timeout = TIMEOUT)
    public void testLsdRadixSortEdgeCases() {
        int[] unsortedArray = new int[] {Integer.MAX_VALUE, 0, -3, 3, 00000, -0, Integer.MIN_VALUE, 73984705, 130932, -2838949};
        int[] sortedArray = new int[] {Integer.MIN_VALUE, -2838949, -3, 0, 0, 0, 3, 130932, 73984705, Integer.MAX_VALUE};
        assertArrayEquals(sortedArray, Sorting.lsdRadixSort(unsortedArray));
    }

    @Test(timeout = TIMEOUT)
    public void testMsdRadixSortEdgeCases() {
        int[] unsortedArray = new int[] {Integer.MAX_VALUE, 0, -3, 3, 00000, -0, Integer.MIN_VALUE, 73984705, 130932, -2838949};
        int[] sortedArray = new int[] {Integer.MIN_VALUE, -2838949, -3, 0, 0, 0, 3, 130932, 73984705, Integer.MAX_VALUE};
        assertArrayEquals(sortedArray, Sorting.msdRadixSort(unsortedArray));
    }

    /**
     * Class for testing proper sorting.
     */
    private static class Person {
        private String name;
        private static int numOfPeople;
        private int id;

        public Person(String name) {
            this.name = name;
            id = numOfPeople++;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return "Name: " + name + "(" + id + ")";
        }

        public static ComparatorPlus<Person> getNameComparator() {
            return new ComparatorPlus<Person>() {
                @Override
                public int compare(Person ta1,
                                   Person ta2) {
                    incrementCount();
                    return ta1.getName().compareTo(ta2.getName());
                }
            };
        }

        @Override
        public boolean equals(Object o) {
            if (o == null) return false;
            if (this == null) return true;
            if (!(o instanceof Person)) return false;
            Person that = (Person) o;
            if (this.name.equals(that.name) && this.id == that.id) return true;
            return false;
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