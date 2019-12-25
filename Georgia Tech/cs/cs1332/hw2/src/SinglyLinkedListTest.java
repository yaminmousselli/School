import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.*;

/**
 * This is a basic set of unit tests for SinglyLinkedList. Passing these does
 * NOT guarantee any grade on this assignment. This is only a sanity check to
 * help you get started on the homework and writing JUnits in general.
 *
 * @author The 1332 TAs
 * @version 1.0
 */
public class SinglyLinkedListTest {
    private LinkedListInterface<String> list;
    private final java.util.Random random = new java.util.Random();
    private final java.util.LinkedList<String> aList = new java.util.LinkedList<>();
    // Convenience method for printing this linked list
    private void printList() {
        System.out.println("List (size = " + list.size() + "):");
        if (list.isEmpty()) { 
            System.out.println("[]");
            return;
        }
        
        LinkedListNode cur = list.getHead();
        String out = "[" + cur.getData();
        for (int i = 1; i < list.size(); i++) {
            cur = cur.getNext();
            out += ", " + cur.getData();
        }
        System.out.println(out + "]");
    }

    public static final int TIMEOUT = 200;

    @Before
    public void setUp() {
        list = new SinglyLinkedList<>();
    }
    
    @Test(timeout = TIMEOUT)
    public void testAddStrings() {
        assertEquals(0, list.size());
        assertNull(list.getHead());

        list.addAtIndex(0, "0a"); //0a
        list.addAtIndex(1, "1a"); //0a 1a
        list.addAtIndex(2, "2a"); //0a 1a 2a
        list.addAtIndex(3, "3a"); //0a 1a 2a 3a

        assertEquals(4, list.size());

        LinkedListNode<String> current = list.getHead();
        assertNotNull(current);
        assertEquals("0a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("1a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("2a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("3a", current.getData());
        assertSame(current, list.getTail());
    }

    @Test(timeout = TIMEOUT)
    public void testAddStringsFront() {
        assertEquals(0, list.size());

        list.addToFront("0a");
        list.addToFront("1a");
        list.addToFront("2a");
        list.addToFront("3a");
        list.addToFront("4a");
        list.addToFront("5a"); //5a 4a 3a 2a 1a 0a

        assertEquals(6, list.size());

        LinkedListNode<String> current = list.getHead();
        assertNotNull(current);
        assertEquals("5a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("4a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("3a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("2a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("1a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("0a", current.getData());
        assertSame(current, list.getTail());
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

        LinkedListNode<String> current = list.getHead();
        assertNotNull(current);
        assertEquals("0a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("1a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("3a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("4a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("5a", current.getData());
        assertSame(current, list.getTail());
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
    public void testToArray() {
        String[] expectedItems = new String[10];

        for (int x = 0; x < expectedItems.length; x++) {
            expectedItems[x] = "a" + x;
            list.addToBack(expectedItems[x]);
        }

        Object[] array = list.toArray();
        assertArrayEquals(expectedItems, array);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void removeFirstOccurrenceNullItemPassed() {
        list.removeFirstOccurrence(null);
    }
    
    @Test(expected = IndexOutOfBoundsException.class)
    public void testAddAtNegative() {
        list.addAtIndex(-1, "data");
    }
    
    @Test(expected = IndexOutOfBoundsException.class)
    public void testAddBeyondSize() {
        int n = random.nextInt(10) + 10;
        for (int i = 0; i < n; i++) { list.addToBack(""); }
        assertEquals(n, list.size());
        
        list.addAtIndex(n + 1, "");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testAddNull() {
        list.addAtIndex(0, null);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testAddNullFront() {
        list.addToFront(null);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testAddNullBack() {
        list.addToBack(null);
    }
    
    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveNegative() {
        list.addAtIndex(-7, "something");
    }
    
    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveAtSize() {
        int n = random.nextInt(10) + 10;
        for(int i = 0; i < n; i++) { list.addToBack(""); } 
        
        assertEquals(n, list.size());
        list.removeAtIndex(n);
    }
    
    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveBeyondSize() {
        int n = random.nextInt(10) + 10;
        for(int i = 0; i < n; i++) { list.addToBack(""); } 
        
        assertEquals(n, list.size());
        list.removeAtIndex(n + 1);
    }
    
    @Test()
    public void testRemoveFromEmptyList() {
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        
        assertTrue(list.removeFromFront() == null);
        assertTrue(list.removeFromBack() == null);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testRemoveFirstOccurrenceNull() {
        int n = random.nextInt(10) + 10;
        for (int i = 0; i < n; i++) { list.addToFront(""); }
        
        assertEquals(n, list.size());
        
        list.removeFirstOccurrence(null);
    }
    
    @Test(expected = java.util.NoSuchElementException.class)
    public void removeFirstOccurrenceNotPresent() {
        list.addToBack("a");
        list.addToBack("b");
        list.addToBack("v");
        list.addToBack("d");
        list.addToBack("e");
        list.addToBack("f");
        list.addToBack("g");
        list.addToBack("h");
        
        list.removeFirstOccurrence("c");
    }
    
    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetNegative() {
        list.get(-1);
    }
    
    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetAtsize() {
        int n = random.nextInt(10) + 10;
        for (int i = 0; i < n; i++) { list.addToBack(""); }
        assertEquals(n, list.size());
        
        list.get(n);
    }
    
    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetBeyondSize() {
        int n = random.nextInt(10) + 10;
        for (int i = 0; i < n; i++) { list.addToBack(""); }
        assertEquals(n, list.size());
        
        list.get(n + 1);
    }
    
    @Test
    public void testAddAtIndex() {
        int n = random.nextInt(90);
        
        assertEquals(0, list.size());
        for (int i = 0; i < 10; i++) {
            list.addAtIndex(i, "" + i);
            aList.add(i, "" + i);
        }
        assertEquals(10, list.size());
        
        for (int i = 0; i < n; i++) {
            int m = random.nextInt(list.size());
            list.addAtIndex(m, "" + i);
            aList.add(m, "" + i);
        }
        assertEquals(10 + n, list.size());
        
        assertEquals(aList.size(), list.size());
        assertArrayEquals(aList.toArray(), list.toArray());
    }
    
    @Test
    public void testAddToFront() {
        int n = random.nextInt(90);
        
        assertEquals(0, list.size());
        for (int i = 0; i < 10; i++) {
            list.addToFront("" + i);
            aList.add(0, "" + i);
        }
        assertEquals(10, list.size());
        
        for (int i = 0; i < n; i++) {
            list.addToFront("" + i);
            aList.add(0, "" + i);
        }
        assertEquals(10 + n, list.size());
        
        assertEquals(aList.size(), list.size());
        assertArrayEquals(aList.toArray(), list.toArray());
    }
    
    @Test
    public void testAddToBack() {
        
        int n = random.nextInt(90);
        
        assertEquals(0, list.size());
        for (int i = 0; i < 10; i++) {
            list.addToBack("" + i);
            aList.add("" + i);
        }
        assertEquals(10, list.size());
        
        for (int i = 0; i < n; i++) {
            list.addToBack("" + i);
            aList.add("" + i);
        }
        assertEquals(10 + n, list.size());
        
        assertEquals(aList.size(), list.size());
        assertArrayEquals(aList.toArray(), list.toArray());
    }
    
    @Test
    public void testRemoveAtIndex() {
        for (int i = 0; i < 100; i++) {
            list.addToBack("" + i);
            aList.add("" + i);
        }
        assertEquals(aList.size(), list.size());
        assertArrayEquals(aList.toArray(), list.toArray());
        
        for (int i = 0; i < 100; i++) {
            int m = random.nextInt(aList.size());
            assertEquals(aList.remove(m), list.removeAtIndex(m));
            assertEquals(aList.size(), list.size());
            assertArrayEquals(aList.toArray(), list.toArray());
        }
    }
    
    @Test
    public void testRemoveFromFront() {
        for (int i = 0; i < 100; i++) {
            list.addToBack("" + i);
            aList.add("" + i);
        }
        assertEquals(aList.size(), list.size());
        assertArrayEquals(aList.toArray(), list.toArray());
        
        for (int i = 0; i < 100; i++) {
            assertEquals(aList.removeFirst(), list.removeFromFront());
            assertEquals(aList.size(), list.size());
            assertArrayEquals(aList.toArray(), list.toArray());
        }
    }
    
    @Test
    public void testRemoveFromBack() {
        for (int i = 0; i < 100; i++) {
            list.addToBack("" + i);
            aList.add("" + i);
        }
        assertEquals(aList.size(), list.size());
        assertArrayEquals(aList.toArray(), list.toArray());
        
        for (int i = 0; i < 100; i++) {
            assertEquals(aList.removeLast(), list.removeFromBack());
            assertEquals(aList.size(), list.size());
            assertArrayEquals(aList.toArray(), list.toArray());
        }
    }
    
    @Test
    public void testRemoveFirstOccurrence() {
        for (int i = 0; i < 100; i++) {
            list.addToBack("" + i);
            aList.add("" + i);
        }
        assertEquals(aList.size(), list.size());
        assertArrayEquals(aList.toArray(), list.toArray());
        
        for (int i = 0; i < 20; i++) {
            int m = random.nextInt(aList.size());
            m = aList.indexOf(aList.get(m));
            assertEquals(aList.get(m), list.removeFirstOccurrence(aList.get(m)));
            aList.remove(m);
        }
        
        assertEquals(aList.size(), list.size());
        assertArrayEquals(aList.toArray(), list.toArray());
    }
    
    @Test
    public void testGet() {
        for (int i = 0; i < 100; i++) {
            list.addToBack("" + i);
            aList.add("" + i);
        }
        assertEquals(aList.size(), list.size());
        assertArrayEquals(aList.toArray(), list.toArray());
        
        for (int i = 0; i < 100; i++) {
            int m = random.nextInt(aList.size());
            assertEquals(aList.get(m), list.get(m));
        }
    }
    
    @Test
    public void testIsEmpty() {
        assertTrue(list.isEmpty());
        
        list.addToBack("not empty");
        
        assertFalse(list.isEmpty());
    }
    
    @Test
    public void testSize() {
        assertEquals(0, list.size());
        
        list.addToBack("0");
        list.addToFront("1");
        list.addAtIndex(2, "2");
        
        assertEquals(3, list.size());
        
        int n = random.nextInt(100) + 1;
        for (int i = 0; i < n; i++) {
            int m = random.nextInt(3);
            
            if (m == 0) { list.addToBack("" + i); }
            else if (m == 1) { list.addAtIndex(random.nextInt(list.size()), "" + i); }
            else { list.addToFront("" + i); }
        }
        assertEquals(n + 3, list.size());
        
        list.removeFirstOccurrence("2");
        list.removeFromBack();
        list.removeFromFront();
        list.removeAtIndex(random.nextInt(n));
        
        assertEquals(n - 1, list.size());
    }
    
    @Test
    public void testClear() {
        int n = random.nextInt(100);
        for (int i = 0; i < n; i++) {
            list.addToBack("" + i);
        }
        assertEquals(n, list.size());
        
        list.clear();
        assertTrue(list.isEmpty());
        assertTrue(list.size() == 0);
        
        assertTrue(list.getHead() == null);
        assertTrue(list.getTail() == null);
        
        assertArrayEquals(new Object[] {}, list.toArray());
    }
    
    @Test
    public void testEdgeCases() {
        aList.add("test");
        list.addAtIndex(0, "test");
        assertArrayEquals(aList.toArray(), list.toArray());
        assertFalse(list.getTail() == null);
        assertFalse(list.getHead() == null);
        
        list.addToBack("");
        aList.add("");
        assertFalse(list.getTail() == null);
        assertFalse(list.getHead() == null);
        assertArrayEquals(aList.toArray(), list.toArray());
        
        list.clear();
        aList.clear();
        assertArrayEquals(aList.toArray(), list.toArray());
        list.addToBack("test2");
        aList.add("test2");
        assertFalse(list.getTail() == null);
        assertFalse(list.getHead() == null);
        assertArrayEquals(aList.toArray(), list.toArray());
        assertFalse(list.getTail() == null);
        assertFalse(list.getHead() == null);
        
        list.addToBack("");
        aList.add("");
        
        assertArrayEquals(aList.toArray(), list.toArray());
        
        list.clear();
        aList.clear();
        list.addToFront("test3");
        aList.add("test3");
        assertFalse(list.getTail() == null);
        assertFalse(list.getHead() == null);
        assertArrayEquals(aList.toArray(), list.toArray());
        assertFalse(list.getTail() == null);
        assertFalse(list.getHead() == null);
        
        list.removeFirstOccurrence("test3");
        assertTrue(list.getHead() == null);
        assertTrue(list.getTail() == null);
        
        list.addToFront("test4");
        assertFalse(list.getTail() == null);
        assertFalse(list.getHead() == null);
        list.addAtIndex(1, "");
        list.addAtIndex(0,"");
        list.addToFront("");
        list.removeAtIndex(1);
        list.removeAtIndex(0);
        list.removeAtIndex(1);
        assertFalse(list.getTail() == null);
        assertFalse(list.getHead() == null);
        list.removeAtIndex(0);
        assertTrue(list.getTail() == null);
        assertTrue(list.getHead() == null);
        
        list.clear();
        list.addAtIndex(0, "test5");
        list.addAtIndex(1, "");
        assertFalse(list.getTail() == list.getHead());
        list.removeFirstOccurrence("");
        assertTrue(list.getTail() == list.getHead());
        
        list.clear();
        list.addAtIndex(0, "test6");
        list.addAtIndex(1, "");
        assertFalse(list.getTail() == list.getHead());
        list.removeAtIndex(1);
        assertTrue(list.getTail() == list.getHead());
        
        list.clear();
        list.addAtIndex(0, "test7");
        assertTrue(list.getTail() == list.getHead());
        list.addToFront("");
        assertFalse(list.getTail() == list.getHead());
        list.removeFromBack();
        assertTrue(list.getTail() == list.getHead());
        
        list.clear();
        list.addAtIndex(0, "test8");
        list.addAtIndex(1, "");
        assertFalse(list.getTail() == list.getHead());
        list.removeFromFront();
        assertTrue(list.getTail() == list.getHead());
        
        list.clear();
        list.addToFront("test9");
        assertTrue(list.getTail() == list.getHead());
        list.addToFront("");
        assertFalse(list.getTail() == list.getHead());
        
        list.clear();
        list.addToBack("test10");
        assertTrue(list.getTail() == list.getHead());
        list.addToBack("");
        assertFalse(list.getTail() == list.getHead());
        
        list.clear();
        list.addAtIndex(0, "test11");
        assertTrue(list.getTail() == list.getHead());
        list.addAtIndex(1, "");
        assertFalse(list.getTail() == list.getHead());
    }
}