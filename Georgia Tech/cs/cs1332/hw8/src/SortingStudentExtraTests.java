import org.junit.Before;
import org.junit.Test;

import java.util.Comparator;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

/**
 * Extra student tests for sorting algorithms.
 *
 * @author Deb Banerji
 * @version 0118 999 881 999 119 725
 *          3
 */
public class SortingStudentExtraTests {
    private ComparatorPlus<Word> comp;
    private static final int TIMEOUT = 200;
    private static String[] lyricWords = {"Never", "gonna", "give",
        "let", "run", "around", "and", "desert", "you", "up", "down"};
    private static String errorText =
        "Sort failed. Find more information"
                + " about the error at: https://youtu.be/dQw4w9WgXcQ";

    @Before
    public void setUp() {
        comp = Word.getWordComparator();
    }

    @Test(timeout = TIMEOUT)
    public void testBubbleSort() {
        Word[] words = new Word[5];
        words[0] = new Word(8);
        words[1] = new Word(9);
        words[2] = new Word(1);
        words[3] = new Word(0);
        words[4] = new Word(2);
        Word[] line1 = new Word[5];
        line1[0] = words[3];
        line1[1] = words[2];
        line1[2] = words[4];
        line1[3] = words[0];
        line1[4] = words[1];
        Sorting.bubbleSort(words, comp);
        int maxComps = 10;

        // Test to see if the array is sorted
        assertArrayEquals(errorText, line1, words);

        // Test number of comparisons made
        assertTrue(errorText,
                comp.getCount() <= maxComps);
    }

    @Test(timeout = TIMEOUT)
    public void testInsertionSort() {
        Word[] words = new Word[5];
        words[0] = new Word(8);
        words[1] = new Word(10);
        words[2] = new Word(1);
        words[3] = new Word(0);
        words[4] = new Word(3);
        Word[] line2 = new Word[5];
        line2[0] = words[3];
        line2[1] = words[2];
        line2[2] = words[4];
        line2[3] = words[0];
        line2[4] = words[1];
        Sorting.insertionSort(words, comp);
        int maxComps = 9;

        // Test to see if the array is sorted
        assertArrayEquals(errorText, line2, words);

        // Test number of comparisons made
        assertTrue(errorText,
                comp.getCount() <= maxComps);
    }

    @Test(timeout = TIMEOUT)
    public void testMergeSort() {
        Word[] words = new Word[7];
        words[0] = new Word(8);
        words[1] = new Word(6);
        words[2] = new Word(1);
        words[3] = new Word(0);
        words[4] = new Word(5);
        words[5] = new Word(7);
        words[6] = new Word(4);
        Word[] line3 = new Word[7];
        line3[0] = words[3];
        line3[1] = words[2];
        line3[2] = words[6];
        line3[3] = words[4];
        line3[4] = words[1];
        line3[5] = words[5];
        line3[6] = words[0];
        Sorting.mergeSort(words, comp);
        int maxComps = 14;

        // Test to see if the array is sorted
        assertArrayEquals(errorText, line3, words);

        // Test number of comparisons made
        assertTrue(errorText,
                comp.getCount() <= maxComps);
    }

    /**
     * Class for testing sorting.
     */
    private static class Word {
        private String text;
        private int firstIndex;

        /**
         * Create a word.
         *
         * @param firstIndex index of the word in the lyricWords array
         */
        public Word(int firstIndex) {
            if (firstIndex < 0 || firstIndex >= lyricWords.length) {
                throw new IllegalArgumentException("Invalid index; please "
                        + "choose an index between 0 and " + lyricWords.length);
            }
            this.firstIndex = firstIndex;
            this.text = lyricWords[firstIndex];
        }


        @Override
        public String toString() {
            return this.text;
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || !(o instanceof Word)) {
                return false;
            }
            return ((Word) o).firstIndex == this.firstIndex;
        }

        @Override
        public int hashCode() {
            return this.firstIndex;
        }

        /**
         * Create a comparator that compares words according to
         * where they occur in the song
         *
         * @return comparator that compares the words
         */
        public static ComparatorPlus<Word> getWordComparator() {
            return new ComparatorPlus<Word>() {
                @Override
                public int compare(Word word1,
                                   Word word2) {
                    incrementCount();
                    return word1.firstIndex - word2.firstIndex;
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
