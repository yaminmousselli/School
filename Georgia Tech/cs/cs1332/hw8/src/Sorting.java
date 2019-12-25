import java.util.Comparator;
import java.util.LinkedList;
import java.util.Random;

/**
 * Your implementation of various sorting algorithms.
 *
 * @author Yamin Mousselli
 * @version 1.0
 */
public class Sorting {

    /**
     * Implement bubble sort.
     *
     * It should be:
     *  in-place
     *  stable
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n)
     *
     * Any duplicates in the array should be in the same relative position after
     * sorting as they were before sorting. (stable).
     *
     * See the PDF for more info on this sort.
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void bubbleSort(T[] arr, Comparator<T> comparator) {
        if (arr.length == 1 || arr.length == 0) {
            return;
        }
        if (comparator == null) {
            throw new IllegalArgumentException("You can not have a null "
                    + "comparator for Bubble Sort");
        }
        if (arr == null) {
            throw new IllegalArgumentException("You can not have a null "
                    + "array for Bubble Sort");
        }
        int length = arr.length;
        int j = 0;
        boolean swapped = true;
        while (swapped) {
            swapped = false;
            j++;
            for (int i = 0; i < length - j; i++) {
                if (comparator.compare(arr[i], arr[i + 1]) > 0) {
                    T temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                    swapped = true;
                }
            }
        }
    }
    /**
     * Implement insertion sort.
     *
     * It should be:
     *  in-place
     *  stable
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n)
     *
     * Any duplicates in the array should be in the same relative position after
     * sorting as they were before sorting. (stable).
     *
     * See the PDF for more info on this sort.
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void insertionSort(T[] arr, Comparator<T> comparator) {
        if (arr.length == 1 || arr.length == 0) {
            return;
        }
        if (comparator == null) {
            throw new IllegalArgumentException("You can not have a null "
                    + "comparator for Insertion Sort");
        }
        if (arr == null) {
            throw new IllegalArgumentException("You can not have a null "
                    + "array for Insertion Sort");
        }
        for (int i = 1; i < arr.length; i++) {
            for (int index = i; index > 0 && comparator.compare(arr[index - 1], arr[index]) > 0; index--) {
                T temp = arr[index];
                arr[index] = arr[index - 1];
                arr[index - 1] = temp;
            }
        }
    }

    /**
     * Implement quick sort.
     *
     * Use the provided random object to select your pivots.
     * For example if you need a pivot between a (inclusive)
     * and b (exclusive) where b > a, use the following code:
     *
     * int pivotIndex = r.nextInt(b - a) + a;
     *
     * It should be:
     *  in-place
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n log n)
     *
     * Note that there may be duplicates in the array.
     *
     * Make sure you code the algorithm as you have been taught it in class.
     * There are several versions of this algorithm and you may not get full
     * credit if you do not use the one we have taught you!
     *
     * @throws IllegalArgumentException if the array or comparator or rand is
     * null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     * @param rand the Random object used to select pivots
     */
    public static <T> void quickSort(T[] arr, Comparator<T> comparator,
                                     Random rand) {
        if (comparator == null) {
            throw new IllegalArgumentException("You can not have a null "
                    + "comparator for QuickSort");
        }
        if (rand == null) {
            throw new IllegalArgumentException("You can not have a null "
                    + "random object for QuickSort");
        }
        if (arr == null) {
            throw new IllegalArgumentException("You can not have a null "
                    + "array for QuickSort");
        }
        if (arr.length == 1 || arr.length == 0) {
            return;
        }
        int low = 0;
        int high = arr.length - 1;
        quickSortHelper(arr, comparator, rand, low, high);
    }

    /**
     * This is my recursive method helper to sort the array using quicksort.
     * I will call this method every time I want to recurse.
     * @param <T> the data type to be sorted
     * @param arr the array that's passed in
     * @param comparator comparator to compare values at indicies of the array
     * @param rand the random generator to generate a random pivot
     * @param low represents i, starting point for sorting lower half of array
     * @param high represent j, starting point for sorting greater half of array
     */
    private static <T> void quickSortHelper(T[] arr, Comparator<T> comparator,
                                           Random rand, int low, int high) {
        // This is the stupid case where they force high to be
        // greater than low.
        if (high > low && low >= 0) {
            //You want inclusive on the upper bound vs rand.nextInt() exclusive
            // on upper bound.
            int pivotIndex = rand.nextInt(high + 1 - low) + low;
            T temp = arr[pivotIndex];
            arr[pivotIndex] = arr[low];
            arr[low] = temp;

            int i = low + 1;
            int j = high;
            //this is when they haven't criss-crossed.find out why it' equal to.
            while (i <= j) {
                while (i <= j && comparator.compare(arr[i], arr[low]) < 0) {
                    i++;
                }
                while (i <= j && comparator.compare(arr[j], arr[low]) > 0) {
                    j--;
                }
                if (i < j) {
                    T temp2 = arr[j];
                    arr[j] =  arr[i];
                    arr[i] = temp2;
                    i++;
                    j--;
                }
            }
            //This is for when i and j criss-cross. Remember when you switch the
            // pivot with j, the pivot is at it's sorted spot in the array.
            T temp3 = arr[j];
            arr[j] = arr[low];
            arr[low] = temp3;
            quickSortHelper(arr, comparator, rand, low, j - 1);
            //Remember j - 1 for the last paramater in the recursive call above
            // is the high paramater for the paramater in the
            // recursive call below.
            quickSortHelper(arr, comparator, rand, j + 1, high);
        }
    }

    /**
     * Implement merge sort.
     *
     * It should be:
     *  stable
     *
     * Have a worst case running time of:
     *  O(n log n)
     *
     * And a best case running time of:
     *  O(n log n)
     *
     * You can create more arrays to run mergesort, but at the end,
     * everything should be merged back into the original T[]
     * which was passed in.
     *
     * Any duplicates in the array should be in the same relative position after
     * sorting as they were before sorting.
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array to be sorted
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void mergeSort(T[] arr, Comparator<T> comparator) {
        if (arr == null) {
            throw new IllegalArgumentException("You're array is null can be "
                   + "sorted by Merge Sort");
        }
        if (arr.length == 0 || arr.length == 1) {
            return;
        }
        if (comparator == null) {
            throw new IllegalArgumentException("You're comparator is null can "
                   + "not be sorted by Merge Sort");
        }
        mergeSortHelperOne(arr, comparator, 0, arr.length - 1);
    }
    /**
     * This method recursively splits the array into half every time until
     * elements are in their own array
     * @param <T> the data type to be sorted.
     * @param arr the array to be sorted by Merge Sort
     * @param comparator the comparator used to compare elements at indicies
     * of the array.
     * @param minIndex the starting index of the array
     * @param maxIndex the last index of the array
     */
    private static <T> void mergeSortHelperOne(T[] arr,
                                               Comparator<T> comparator,
                                           int minIndex, int maxIndex) {
        if (minIndex == maxIndex) {
            return;
        }
        int midIndex = (minIndex + maxIndex) / 2;
        mergeSortHelperOne(arr, comparator, minIndex, midIndex);
        mergeSortHelperOne(arr, comparator, midIndex + 1, maxIndex);
        mergeSortHelperTwo(arr, comparator, minIndex, midIndex, maxIndex);
    }

    /**
     * This method merges the left half and right half into their respective
     * arrays and then merges the two halves in one array.
     * @param <T> the data type to be sorted.
     * @param arr the array to be sorted.
     * @param comparator the comparator to compare elements at certain
     * indicies of the array
     * @param minIndex the starting index of the array
     * @param midIndex the middle index of the array
     * @param maxIndex the last index of the array
     */
    private static <T> void mergeSortHelperTwo(T[] arr, Comparator<T>
            comparator, int minIndex, int midIndex, int maxIndex) {

        T[] tempArray = (T[]) new Object[(maxIndex - minIndex) + 1];
        int leftIndex = minIndex;
        int rightIndex = midIndex + 1;
        int currentIndex = 0;

        while (leftIndex <= midIndex && rightIndex <= maxIndex) {
            if (comparator.compare(arr[leftIndex], arr[rightIndex])
                    <= 0) {
                tempArray[currentIndex] = arr[leftIndex];
                leftIndex++;
            } else {
                tempArray[currentIndex] = arr[rightIndex];
                rightIndex++;
            }
            currentIndex++;
        }

        while (leftIndex <= midIndex) {
            tempArray[currentIndex] = arr[leftIndex];
            leftIndex++;
            currentIndex++;
        }

        while (rightIndex <= maxIndex) {
            tempArray[currentIndex] = arr[rightIndex];
            rightIndex++;
            currentIndex++;
        }
        for (int i = 0; i < tempArray.length; i++) {
            arr[minIndex + i] = tempArray[i];
        }
    }

    /**
     * Implement LSD (least significant digit) radix sort.
     *
     * Remember you CANNOT convert the ints to strings at any point in your
     * code!
     *
     * It should be:
     *  stable
     *
     * k is the number of digits in your biggest number
     * Have a worst case running time of:
     *  O(kn)
     *
     * And a best case running time of:
     *  O(kn)
     *
     * Any duplicates in the array should be in the same relative position after
     * sorting as they were before sorting. (stable)
     *
     * Do NOT use {@code Math.pow()} in your sort. Instead, if you need to, use
     * the provided {@code pow()} method below.
     *
     * You may use {@code java.util.ArrayList} or {@code java.util.LinkedList}
     * if you wish, but it may only be used inside radix sort and any radix sort
     * helpers. Do NOT use these classes with other sorts.
     *
     * @throws IllegalArgumentException if the array is null
     * @param arr the array to be sorted
     * @return the sorted array
     */
    public static int[] lsdRadixSort(int[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("We can not LSD Radix sort a "
                    + "null "
                    + "array");
        }
        if (arr.length == 0 || arr.length == 1) {
            return arr;
        }
        LinkedList<Integer>[] buckets = new LinkedList[19];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new LinkedList<>();
        }
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            if (Math.abs((long) arr[i]) > Math.abs((long) arr[max])) {
                max = i;
            }
        }
        int numDigits = 0;
        int a = arr[max];
        while (a != 0) {
            a = a / 10;
            numDigits++;
        }
        for (int i = 0; i < numDigits; i++) {
            //loop to place ints into its bucket in buckets
            for (int j = 0; j < arr.length; j++) {
                buckets[arr[j] / pow(10, i) % 10 + 9].add(arr[j]);
            }
            int position = 0;
            //removes each item in every bucket and places it into the array
            for (LinkedList<Integer> list : buckets) {
                while (list.size() != 0) {
                    arr[position] = list.remove();
                    position++;
                }
            }
        }
        return arr;
    }

    /**
     * Implement MSD (most significant digit) radix sort.
     *
     * Remember you CANNOT convert the ints to strings at any point in your
     * code!
     *
     * It should:
     *
     * Have a worst case running time of:
     *  O(kn)
     *
     * And a best case running time of:
     *  O(kn)
     *
     * Do NOT use {@code Math.pow()} in your sort. Instead, if you need to, use
     * the provided {@code pow()} method below.
     *
     * You may use {@code java.util.ArrayList} or {@code java.util.LinkedList}
     * if you wish, but it may only be used inside radix sort and any radix sort
     * helpers. Do NOT use these classes with other sorts.
     *
     * @throws IllegalArgumentException if the array is null
     * @param arr the array to be sorted
     * @return the sorted array
     */
    public static int[] msdRadixSort(int[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("We can not LSD Radix sort a "
                    + "null "
                    + "array");
        }
        if (arr.length == 0 || arr.length == 1) {
            return arr;
        }
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            if (Math.abs((long) arr[i]) > Math.abs((long) arr[max])) {
                max = i;
            }
        }
        int numDigits = 0;
        int a = arr[max];
        while (a != 0) {
            a = a / 10;
            numDigits++;
        }
        return msdRadixSortHelper(arr, numDigits - 1);
    }

    /**
     * This method recursively sorts everything in the array
     * @param numDigits the number of digits of the largest number
     * @param arr the array to be sorted
     * @return the sorted array.
     */
    private static int[] msdRadixSortHelper(int[] arr, int numDigits) {
        //base case for when there aren't any more digits to sort
        if (numDigits < 0) {
            return arr;
        }
        LinkedList<Integer>[] buckets = new LinkedList[19];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new LinkedList<>();
        }
        for (int j = 0; j < arr.length; j++) {
            buckets[((arr[j] / pow(10, numDigits)) % 10) + 9].add(arr[j]);
        }
        int arrIndex = 0;

        for (int k = 0; k < buckets.length; k++) {
            if (buckets[k].size() > 1) {
                int[] tempArray = new int[buckets[k].size()];
                int position = 0;
                while (buckets[k].size() != 0) {
                    tempArray[position] = buckets[k].remove();
                    position++;
                }

                int[] sortedBucket = msdRadixSortHelper(
                        tempArray, numDigits - 1);
                for (int i = 0; i < sortedBucket.length; i++) {
                    arr[arrIndex] = sortedBucket[i];
                    arrIndex++;
                }
            } else if (buckets[k].size() == 1) {
                arr[arrIndex] = buckets[k].remove();
                arrIndex++;
            }
        }
        return arr;
    }

    /**
     * Calculate the result of a number raised to a power. Use this method in
     * your radix sorts instead of {@code Math.pow()}.
     *
     * DO NOT MODIFY THIS METHOD.
     *
     * @throws IllegalArgumentException if both {@code base} and {@code exp} are
     * 0
     * @throws IllegalArgumentException if {@code exp} is negative
     * @param base base of the number
     * @param exp power to raise the base to. Must be 0 or greater.
     * @return result of the base raised to that power
     */
    private static int pow(int base, int exp) {
        if (exp < 0) {
            throw new IllegalArgumentException("Exponent cannot be negative.");
        } else if (base == 0 && exp == 0) {
            throw new IllegalArgumentException(
                    "Both base and exponent cannot be 0.");
        } else if (exp == 0) {
            return 1;
        } else if (exp == 1) {
            return base;
        }
        int halfPow = pow(base, exp / 2);
        if (exp % 2 == 0) {
            return halfPow * halfPow;
        } else {
            return halfPow * halfPow * base;
        }
    }
}
