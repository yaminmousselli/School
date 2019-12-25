import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * More comprehensive tests than the provided file,
 * including some edge cases and efficiency testing.
 *
 * @author Evan Bretl
 * @version 1.0
 */
public class StacksQueuesTestsEB {

    private StackInterface<Integer> stack;
    private QueueInterface<Integer> queue;

    public static final int TIMEOUT = 200;

    @Test(timeout = TIMEOUT)
    public void testArrayStackPush() {
        stack = new ArrayStack<>();
        assertEquals(0, stack.size());

        stack.push(34);
        stack.push(29);
        stack.push(48);
        stack.push(59);

        assertEquals(4, stack.size());
        assertTrue(!stack.isEmpty());

        Object[] backingArray = ((ArrayStack<Integer>) stack).getBackingArray();

        Object[] expected = new Object[11];
        expected[0] = 34;
        expected[1] = 29;
        expected[2] = 48;
        expected[3] = 59;

        assertArrayEquals(expected, backingArray);
    }

    @Test(timeout = TIMEOUT)
    public void testArrayStackRegrow() {
        ArrayStack<Integer> as = new ArrayStack<>();
        for (int i = 0; i < 15; i++) {
            as.push(i);
        }
        assertEquals(23, as.getBackingArray().length);

        for (int i = 14; i >= 0; i--) {
            assertEquals(new Integer(i), as.pop());
        }
        assertEquals(23, as.getBackingArray().length);
    }

    @Test(timeout = TIMEOUT)
    public void testArrayQueueRegrow() {
        ArrayQueue<Integer> aq = new ArrayQueue<>();
        for (int i = 0; i < 5; i++) {
            aq.enqueue(new Integer(i));
            aq.dequeue();
        }
        assertEquals(null, aq.getBackingArray()[0]);

        for (int i = 0; i < 15; i++) {
            aq.enqueue(i);
        }
        assertEquals(23, aq.getBackingArray().length);
        assertTrue(aq.getBackingArray()[0] != null);

        for (int i = 0; i < 15; i++) {
            assertEquals(new Integer(i), aq.dequeue());
        }
        assertEquals(23, aq.getBackingArray().length);
    }

    @Test(timeout = TIMEOUT)
    public void testArrayQueueEnqueueDequeue() {
        ArrayQueue<Integer> aq = new ArrayQueue<>();
        for (int i = 0; i < 100; i++) {
            aq.enqueue(i);
        }
        for (int i = 0; i < 50; i++) {
            aq.dequeue();
        }
        for (int i = 100; i < 1000; i++) {
            aq.enqueue(i);
        }

        for (int i = 50; i < 1000; i++) {
            assertEquals(new Integer(i), aq.dequeue());
        }
    }

    @Test(timeout = TIMEOUT)
    public void testLinkedQueueEnqueueDequeue() {
        LinkedQueue<Integer> lq = new LinkedQueue<>();
        for (int i = 0; i < 100; i++) {
            lq.enqueue(i);
        }
        for (int i = 0; i < 50; i++) {
            lq.dequeue();
        }
        for (int i = 100; i < 1000; i++) {
            lq.enqueue(i);
        }

        for (int i = 50; i < 1000; i++) {
            assertEquals(new Integer(i), lq.dequeue());
        }
    }


    @Test(timeout = TIMEOUT)
    public void testArrayStackPop() {
        stack = new ArrayStack<>();
        assertEquals(0, stack.size());

        stack.push(34);
        stack.push(29);
        stack.push(48);
        stack.push(59);
        assertEquals((Integer) 59, stack.pop());

        assertEquals(3, stack.size());

        Object[] backingArray = ((ArrayStack<Integer>) stack).getBackingArray();

        Object[] expected = new Object[11];
        expected[0] = 34;
        expected[1] = 29;
        expected[2] = 48;

        assertArrayEquals(expected, backingArray);

        stack.pop(); stack.pop(); stack.pop();

        assertTrue(stack.isEmpty());
        assertEquals(0, stack.size());
    }

    @Test(timeout = TIMEOUT)
    public void testLinkedStackPop() {
        stack = new LinkedStack<>();
        assertEquals(0, stack.size());

        stack.push(34);
        stack.push(29);
        stack.push(48);
        stack.push(59);
        assertEquals((Integer) 59, stack.pop());

        assertEquals(3, stack.size());

        stack.pop(); stack.pop();
        assertEquals((Integer) 34, stack.pop());

        assertTrue(stack.isEmpty());
        assertEquals(0, stack.size());
    }

    @Test(timeout = TIMEOUT)
    public void testArrayQueueEnqueue() {
        queue = new ArrayQueue<>();
        assertEquals(0, queue.size());

        queue.enqueue(34);
        queue.enqueue(29);
        queue.enqueue(48);
        queue.enqueue(59);

        assertEquals(4, queue.size());
        assertTrue(!queue.isEmpty());

        Object[] backingArray = ((ArrayQueue<Integer>) queue).getBackingArray();

        Object[] expected = new Object[11];
        expected[0] = 34;
        expected[1] = 29;
        expected[2] = 48;
        expected[3] = 59;

        assertArrayEquals(expected, backingArray);
    }

    @Test(timeout = TIMEOUT)
    public void testLinkedQueueEnqueue() {
        LinkedQueue<Integer> lq = new LinkedQueue<>();
        assertEquals(0, lq.size());
        assertEquals(null, lq.getHead());
        assertEquals(null, lq.getTail());

        lq.enqueue(34);
        lq.enqueue(29);

        assertEquals(2, lq.size());
        assertTrue(!lq.isEmpty());
        assertEquals((Integer) 34, lq.getHead().getData());
        assertEquals((Integer) 29, lq.getTail().getData());
    }

    @Test(timeout = TIMEOUT)
    public void testArrayQueueDequeue() {
        queue = new ArrayQueue<>();
        assertEquals(0, queue.size());

        queue.enqueue(34);
        queue.enqueue(29);
        queue.enqueue(48);
        queue.enqueue(59);
        assertEquals((Integer) 34, queue.dequeue());

        assertEquals(3, queue.size());

        Object[] backingArray = ((ArrayQueue<Integer>) queue).getBackingArray();

        Object[] expected = new Object[11];
        expected[1] = 29;
        expected[2] = 48;
        expected[3] = 59;

        assertArrayEquals(expected, backingArray);

        queue.dequeue(); queue.dequeue(); queue.dequeue();
    }

    @Test(timeout = TIMEOUT)
    public void testAddNull() {
        stack = new ArrayStack<>();
        try {
            stack.push(null);
            assertTrue(false);
        } catch (IllegalArgumentException e) { }

        stack = new LinkedStack<>();
        try {
            stack.push(null);
            assertTrue(false);
        } catch (IllegalArgumentException e) { }

        queue = new ArrayQueue<>();
        try {
            queue.enqueue(null);
            assertTrue(false);
        } catch (IllegalArgumentException e) { }

        queue = new LinkedQueue<>();
        try {
            queue.enqueue(null);
            assertTrue(false);
        } catch (IllegalArgumentException e) { }

    }

    @Test(timeout = TIMEOUT)
    public void testRemoveFromEmpty() {
        stack = new ArrayStack<>();
        try {
            stack.pop();
            assertTrue(false);
        } catch (NoSuchElementException e) { }

        stack = new LinkedStack<>();
        try {
            stack.pop();
            assertTrue(false);
        } catch (NoSuchElementException e) { }

        queue = new ArrayQueue<>();
        try {
            queue.dequeue();
            assertTrue(false);
        } catch (NoSuchElementException e) { }

        queue = new LinkedQueue<>();
        try {
            queue.dequeue();
            assertTrue(false);
        } catch (NoSuchElementException e) { }
    }

    @Test(timeout = TIMEOUT)
    public void testLinkedStackPush() {
        LinkedStack<Integer> ls = new LinkedStack<>();
        assertEquals(0, ls.size());

        ls.push(34);
        assertEquals(new Integer(34), ls.getHead().getData());
        assertEquals(1, ls.size());
        assertTrue(!ls.isEmpty());

        ls.push(29);
        ls.push(48);
        ls.push(59);

        assertEquals(4, ls.size());
        assertEquals(new Integer(59), ls.getHead().getData());

        ls.pop();
        assertEquals(new Integer(48), ls.pop());

        ls.pop(); ls.pop();

        assertEquals(null, ls.getHead());
        assertTrue(ls.isEmpty());
    }

    private long timeStackOps(StackInterface<Integer> s, int ops) {
        long tStart = System.nanoTime();
        for (int i = 0; i < ops; i++) {
            s.push(i);
        }
        for (int i = 0; i < ops; i++) {
            s.pop();
        }
        return System.nanoTime() - tStart;
    }

    private long timeQueueOps(QueueInterface<Integer> q, int ops) {
        long tStart = System.nanoTime();
        for (int i = 0; i < ops; i++) {
            q.enqueue(i);
        }
        for (int i = 0; i < ops; i++) {
            q.dequeue();
        }
        return System.nanoTime() - tStart;
    }

    @Test //no timeout
    public void testArrayStackEfficiency() {
        int ops = 100000;
        stack = new ArrayStack<>();
        long t1 = timeStackOps(stack, ops);
        stack = new ArrayStack<>();
        long t2 = timeStackOps(stack, ops * 10);

        double ratio = (double) t2 / t1;
        if (ratio > 15) {
            assertTrue("Possibly inefficient algorithm. 10x the operations took " + ratio + "x as long", false);
        }
    }

    @Test //no timeout
    public void testLinkedStackEfficiency() {
        int ops = 100000;
        stack = new LinkedStack<>();
        long t1 = timeStackOps(stack, ops);
        stack = new LinkedStack<>();
        long t2 = timeStackOps(stack, ops * 10);

        double ratio = (double) t2 / t1;
        if (ratio > 15) {
            assertTrue("Possibly inefficient algorithm. 10x the operations took " + ratio + "x as long", false);
        }
    }

    @Test //no timeout
    public void testArrayQueueEfficiency() {
        int ops = 100000;
        queue = new ArrayQueue<>();
        long t1 = timeQueueOps(queue, ops);
        queue = new ArrayQueue<>();
        long t2 = timeQueueOps(queue, ops * 10);

        double ratio = (double) t2 / t1;
        if (ratio > 15) {
            assertTrue("Possibly inefficient algorithm. 10x the operations took " + ratio + "x as long", false);
        }
    }

    @Test //no timeout
    public void testLinkedQueueEfficiency() {
        int ops = 100000;
        queue = new LinkedQueue<>();
        long t1 = timeQueueOps(queue, ops);
        queue = new LinkedQueue<>();
        long t2 = timeQueueOps(queue, ops * 10);

        double ratio = (double) t2 / t1;
        if (ratio > 15) {
            assertTrue("Possibly inefficient algorithm. 10x the operations took " + ratio + "x as long", false);
        }
    }
}
