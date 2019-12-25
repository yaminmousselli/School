import org.junit.Test;
import org.junit.Before;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

public class MyLinkedListTests {
    private LinkedListInterface<String> list;

    public static final int TIMEOUT = 200;

    @Before
    public void setUp() {
        list = new SinglyLinkedList<String>();
    }

    @Test(timeout = TIMEOUT)
    public void testAddBack() {
        assertEquals(0, list.size());
        assertNull(list.getHead());
        list.addToBack("a");
        list.addToBack("b");
        list.addToBack("c");
        list.addToBack("d");

        assertEquals(4, list.size());
        LinkedListNode<String> current = list.getHead();
        assertNotNull(current);
        assertEquals("a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("b", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("c", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("d", current.getData());
        assertSame(current, list.getTail());
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveFront() {
        assertEquals(0, list.size());
        assertNull(list.getHead());
        list.addToBack("a");
        list.addToBack("b");
        list.addToBack("c");
        list.addToBack("d");

        assertEquals(4, list.size());

        list.removeFromFront();
        assertEquals(3, list.size());
        LinkedListNode<String> current = list.getHead();
        assertNotNull(current);
        assertEquals("b", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("c", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("d", current.getData());

        assertEquals(current, list.getTail());

    }

    @Test(timeout = TIMEOUT)
    public void testRemoveBack() {
        assertEquals(0, list.size());
        assertNull(list.getHead());
        list.addToBack("a");
        list.addToBack("b");
        list.addToBack("c");
        list.addToBack("d");

        assertEquals(4, list.size());

        list.removeFromBack();
        assertEquals(3, list.size());
        LinkedListNode<String> current = list.getHead();
        assertNotNull(current);
        assertEquals("a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("b", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("c", current.getData());

        assertEquals(current, list.getTail());

    }

    @Test(timeout = TIMEOUT)
    public void testRemoveOne() {
        assertEquals(0, list.size());
        assertNull(list.getHead());
        list.addToBack("a");
        assertNotNull(list.getHead());
        assertNotNull(list.getTail());

        list.removeAtIndex(0);
        assertNull(list.getHead());
        assertNull(list.getTail());
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveLots() {
        assertEquals(0, list.size());
        assertNull(list.getHead());
        list.addToBack("a");
        list.addToBack("b");
        list.addToBack("c");
        list.addToBack("d");

        assertEquals(4, list.size());

        list.removeFromBack();
        list.removeFromBack();
        list.removeFromBack();
        assertEquals(1, list.size());
        LinkedListNode<String> current = list.getHead();
        assertNotNull(current);
        assertEquals("a", current.getData());

        assertEquals(current, list.getTail());

    }

    @Test(timeout = TIMEOUT)
    public void testRemoveEmpty() {
        assertEquals(0, list.size());
        assertNull(list.getHead());
        assertNull(list.removeFromBack());
        assertNull(list.removeFromFront());
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveFirstInstance() {
        assertEquals(0, list.size());
        assertNull(list.getHead());
        list.addToBack("a");
        list.addToBack("b");
        list.addToBack("c");
        list.addToBack("d");

        assertEquals(4, list.size());

        assertEquals("b", list.removeFirstOccurrence("b"));

        assertEquals(3, list.size());
        LinkedListNode<String> current = list.getHead();
        assertNotNull(current);
        assertEquals("a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("c", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("d", current.getData());

        assertEquals(current, list.getTail());
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveFirstInstanceFront() {
        assertEquals(0, list.size());
        assertNull(list.getHead());
        list.addToBack("a");
        list.addToBack("b");
        list.addToBack("c");
        list.addToBack("d");

        assertEquals(4, list.size());

        assertEquals("a", list.removeFirstOccurrence("a"));

        assertEquals(3, list.size());
        LinkedListNode<String> current = list.getHead();
        assertNotNull(current);
        assertEquals("b", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("c", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("d", current.getData());

        assertEquals(current, list.getTail());
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveFirstInstanceBack() {
        assertEquals(0, list.size());
        assertNull(list.getHead());
        list.addToBack("a");
        list.addToBack("b");
        list.addToBack("c");
        list.addToBack("d");

        assertEquals(4, list.size());

        assertEquals("d", list.removeFirstOccurrence("d"));

        assertEquals(3, list.size());
        LinkedListNode<String> current = list.getHead();
        assertNotNull(current);
        assertEquals("a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("b", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("c", current.getData());

        assertEquals(current, list.getTail());
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveFirstInstanceDuplicate() {
        assertEquals(0, list.size());
        assertNull(list.getHead());
        list.addToBack("a");
        list.addToBack("b");
        list.addToBack("b");
        list.addToBack("c");

        assertEquals(4, list.size());

        assertEquals("b", list.removeFirstOccurrence("b"));

        assertEquals(3, list.size());
        LinkedListNode<String> current = list.getHead();
        assertNotNull(current);
        assertEquals("a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("b", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("c", current.getData());

        assertEquals(current, list.getTail());
    }
}
