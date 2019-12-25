import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Tests all Exceptions and a few edge cases
 *
 * @author Martin Kurien
 * @version 1.0
 */
public class EdgesAndExceptions {

    private SearchableString validSS;
    private SearchableString zeroLengthSS;
    private SearchableString shortSS;
    private SearchableString longSS;
    public static final int TIMEOUT = 200;

    @Before
    public void setUp() {
        validSS = new SearchableString("Valid");
        zeroLengthSS = new SearchableString("");
        shortSS = new SearchableString("short");
        longSS = new SearchableString("longer");
    }


    @Test(timeout = TIMEOUT)
    public void kmpExceptions() {
        boolean mitt = false;
        try {
            StringSearching.kmp(null, validSS);
        } catch (IllegalArgumentException e) {
            mitt = true;
        }
        assertTrue("Check pattern is null", mitt);

        mitt = false;
        try {
            StringSearching.kmp(validSS, null);
        } catch (IllegalArgumentException e) {
            mitt = true;
        }
        assertTrue("Check text is null", mitt);

        mitt = false;
        try {
            StringSearching.kmp(zeroLengthSS, validSS);
        } catch (IllegalArgumentException e) {
            mitt = true;
        }
        assertTrue("Check pattern of zero length", mitt);
    }

    @Test(timeout = TIMEOUT)
    public void buildFailureTableExceptions() {
        boolean mitt = false;
        try {
            StringSearching.buildFailureTable(null);
        } catch (IllegalArgumentException e) {
            mitt = true;
        }
        assertTrue("Check pattern is null", mitt);
    }

    @Test(timeout = TIMEOUT)
    public void boyerMooreExceptions() {
        boolean mitt = false;
        try {
            StringSearching.boyerMoore(null, validSS);
        } catch (IllegalArgumentException e) {
            mitt = true;
        }
        assertTrue("Check pattern is null", mitt);

        mitt = false;
        try {
            StringSearching.boyerMoore(validSS, null);
        } catch (IllegalArgumentException e) {
            mitt = true;
        }
        assertTrue("Check text is null", mitt);

        mitt = false;
        try {
            StringSearching.boyerMoore(zeroLengthSS, validSS);
        } catch (IllegalArgumentException e) {
            mitt = true;
        }
        assertTrue("Check pattern of zero length", mitt);
    }

    @Test(timeout = TIMEOUT)
    public void buildLastTableExceptions() {
        boolean mitt = false;
        try {
            StringSearching.buildLastTable(null);
        } catch (IllegalArgumentException e) {
            mitt = true;
        }
        assertTrue("Check pattern is null", mitt);
    }

    @Test(timeout = TIMEOUT)
    public void rabinKarpExceptions() {
        boolean mitt = false;
        try {
            StringSearching.rabinKarp(null, validSS);
        } catch (IllegalArgumentException e) {
            mitt = true;
        }
        assertTrue("Check pattern is null", mitt);

        mitt = false;
        try {
            StringSearching.rabinKarp(validSS, null);
        } catch (IllegalArgumentException e) {
            mitt = true;
        }
        assertTrue("Check text is null", mitt);

        mitt = false;
        try {
            StringSearching.rabinKarp(zeroLengthSS, validSS);
        } catch (IllegalArgumentException e) {
            mitt = true;
        }
        assertTrue("Check pattern of zero length", mitt);
    }

    @Test(timeout = TIMEOUT)
    public void generateHashExceptions() {
        boolean mitt = false;
        try {
            StringSearching.generateHash(null, 1);
        } catch (IllegalArgumentException e) {
            mitt = true;
        }
        assertTrue("Check current is null", mitt);

        mitt = false;
        try {
            StringSearching.generateHash(validSS, -1);
        } catch (IllegalArgumentException e) {
            mitt = true;
        }
        assertTrue("Check length < 0", mitt);

        mitt = false;
        try {
            StringSearching.generateHash(validSS, 0);
        } catch (IllegalArgumentException e) {
            mitt = true;
        }
        assertTrue("Check length = 0", mitt);

        mitt = false;
        try {
            StringSearching.generateHash(validSS, validSS.length() + 1);
        } catch (IllegalArgumentException e) {
            mitt = true;
        }
        assertTrue("Check length > length of current", mitt);
    }

    @Test(timeout = TIMEOUT)
    public void updateHashExceptions() {
        boolean mitt = false;
        try {
            StringSearching.updateHash(0, -1, 'a', 'a');
        } catch (IllegalArgumentException e) {
            mitt = true;
        }
        assertTrue("Check length < 0", mitt);

        mitt = false;
        try {
            StringSearching.updateHash(0, 0,'a', 'a');
        } catch (IllegalArgumentException e) {
            mitt = true;
        }
        assertTrue("Check length = 0", mitt);
    }

    @Test(timeout = TIMEOUT)
    public void ShortCircuits() {
        List<Integer> result = StringSearching.kmp(longSS, shortSS);
        assertTrue("Returns nothing", result.isEmpty());
        assertTrue("No processing should have been done",
                longSS.getCount() == 0);
        assertTrue("No processing should have been done",
                shortSS.getCount() == 0);

        result = StringSearching.boyerMoore(longSS, shortSS);
        assertTrue("Returns nothing", result.isEmpty());
        assertTrue("No processing should have been done",
                longSS.getCount() == 0);
        assertTrue("No processing should have been done",
                shortSS.getCount() == 0);

        result = StringSearching.rabinKarp(longSS, shortSS);
        assertTrue("Returns nothing", result.isEmpty());
        assertTrue("No processing should have been done",
                longSS.getCount() == 0);
        assertTrue("No processing should have been done",
                shortSS.getCount() == 0);

    }

    @Test(timeout = TIMEOUT)
    public void Edges() {
        assertTrue((StringSearching.buildFailureTable(zeroLengthSS))
                .length == 0);
        assertTrue((StringSearching.buildLastTable(zeroLengthSS)).isEmpty());
    }


}
