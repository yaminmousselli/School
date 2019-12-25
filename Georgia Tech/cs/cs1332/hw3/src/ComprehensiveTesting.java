import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class ComprehensiveTesting {

    private StackInterface<Integer> stack;
    private QueueInterface<Integer> queue;

    public static final int TIMEOUT = 00;

    @Test(timeout = TIMEOUT)
    public void testArrayStackPush() {
        stack = new ArrayStack<>();
        assertEquals(0, stack.size());

        stack.push(34);
        stack.push(29);
        stack.push(48);
        stack.push(59);

        assertEquals(4, stack.size());

        Object[] backingArray = ((ArrayStack<Integer>) stack).getBackingArray();

        Object[] expected = new Object[11];
        expected[0] = 34;
        expected[1] = 29;
        expected[2] = 48;
        expected[3] = 59;

        assertArrayEquals(expected, backingArray);
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
    }

    @Test(expected = NoSuchElementException.class)
    public void testArrayStackPopEmpty() {
        stack = new ArrayStack<>();
        assertEquals(0, stack.size());
        stack.pop();
    }

    @Test(timeout = TIMEOUT)
    public void testArrayStackPop1Element() {
        stack = new ArrayStack<>();
        assertEquals(0, stack.size());

        stack.push(34);
        Object[] backingArray = ((ArrayStack<Integer>) stack).getBackingArray();
        Object[] expected = new Object[11];
        expected[0] = 34;
        assertArrayEquals(expected, backingArray);

        assertEquals((Integer) 34, stack.pop());
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

        Object[] backingArray = ((ArrayQueue<Integer>) queue).getBackingArray();

        Object[] expected = new Object[11];
        expected[0] = 34;
        expected[1] = 29;
        expected[2] = 48;
        expected[3] = 59;

        assertArrayEquals(expected, backingArray);
    }

    @Test(timeout = TIMEOUT)
    public void testArrayQueueEnqueueCircular() {
        queue = new ArrayQueue<>();
        assertEquals(0, queue.size());

        queue.enqueue(34);
        queue.enqueue(29);
        queue.enqueue(48);
        queue.enqueue(59);
        queue.enqueue(59);
        queue.enqueue(59);
        queue.enqueue(59);
        queue.enqueue(59);
        queue.enqueue(59);
        queue.enqueue(59);
        queue.enqueue(59);

        assertEquals(11, queue.size());

        Object[] backingArray = ((ArrayQueue<Integer>) queue).getBackingArray();

        Object[] expected = new Object[11];
        expected[0] = 34;
        expected[1] = 29;
        expected[2] = 48;
        expected[3] = 59;
        expected[4] = 59;
        expected[5] = 59;
        expected[6] = 59;
        expected[7] = 59;
        expected[8] = 59;
        expected[9] = 59;
        expected[10] = 59;

        assertArrayEquals(expected, backingArray);

        assertEquals((Integer) 34, queue.dequeue());
        queue.enqueue(0);

        expected[0] = 0;

        assertArrayEquals(expected, backingArray);
    }

    @Test(timeout = TIMEOUT)
    public void testArrayQueueDequeueCircular() {
        queue = new ArrayQueue<>();
        assertEquals(0, queue.size());

        queue.enqueue(34);
        queue.enqueue(29);
        queue.enqueue(48);
        queue.enqueue(59);
        queue.enqueue(59);
        queue.enqueue(59);
        queue.enqueue(59);
        queue.enqueue(59);
        queue.enqueue(59);
        queue.enqueue(59);
        queue.enqueue(59);

        assertEquals(11, queue.size());

        Object[] backingArray = ((ArrayQueue<Integer>) queue).getBackingArray();

        Object[] expected = new Object[11];
        expected[0] = 34;
        expected[1] = 29;
        expected[2] = 48;
        expected[3] = 59;
        expected[4] = 59;
        expected[5] = 59;
        expected[6] = 59;
        expected[7] = 59;
        expected[8] = 59;
        expected[9] = 59;
        expected[10] = 59;

        assertArrayEquals(expected, backingArray);

        assertEquals((Integer) 34, queue.dequeue());
        queue.enqueue(0);

        expected[0] = 0;

        assertArrayEquals(expected, backingArray);
        queue.dequeue();

        queue.enqueue(10);
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();

        assertEquals((Integer) 0, queue.dequeue());
        assertEquals((Integer) 10, queue.dequeue());
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
    }

    @Test(timeout = TIMEOUT)
    public void testLinkedQueueEnqueue() {
        queue = new LinkedQueue<>();
        assertEquals(0, queue.size());

        queue.enqueue(18);
        queue.enqueue(27);
        LinkedNode current = ((LinkedQueue<Integer>) queue).getHead();

        assertEquals((Integer) 18, current.getData());
        current = current.getNext();
        assertEquals((Integer) 27, current.getData());
    }

    @Test(timeout = TIMEOUT)
    public void testLinkedQueueDequeue() {
        queue = new LinkedQueue<>();
        assertEquals(0, queue.size());

        queue.enqueue(18);
        queue.enqueue(27);
        LinkedNode current = ((LinkedQueue<Integer>) queue).getHead();

        assertEquals((Integer) 18, current.getData());
        current = current.getNext();
        assertEquals((Integer) 27, current.getData());

        assertEquals((Integer) 18, queue.dequeue());
        assertEquals((Integer) 27, queue.dequeue());
    }

    @Test(timeout = TIMEOUT)
    public void testLinkedQueueisEmpty() {
        queue = new LinkedQueue<>();
        assertEquals(0, queue.size());

        queue.enqueue(18);
        queue.enqueue(27);
        LinkedNode current = ((LinkedQueue<Integer>) queue).getHead();

        assertEquals((Integer) 18, current.getData());
        current = current.getNext();
        assertEquals((Integer) 27, current.getData());

        assertEquals((Integer) 18, queue.dequeue());
        assertEquals((Integer) 27, queue.dequeue());

        assertEquals(true, queue.isEmpty());
    }

    @Test(timeout = TIMEOUT)
    public void testLinkedStackEnqueueDequeueisEmpty() {
        stack = new LinkedStack<>();
        assertEquals(0, stack.size());

        stack.push(18);
        stack.push(27);
        LinkedNode current = ((LinkedStack<Integer>) stack).getHead();

        assertEquals((Integer) 27, current.getData());
        current = current.getNext();
        assertEquals((Integer) 18, current.getData());

        assertEquals((Integer) 27, stack.pop());
        assertEquals((Integer) 18, stack.pop());

        assertEquals(true, stack.isEmpty());
    }

    @Test(timeout = TIMEOUT)
    public void testArrayQueueEnqueueDoublingNoCircle() {
        queue = new ArrayQueue<>();
        assertEquals(0, queue.size());

        queue.enqueue(34);
        queue.enqueue(29);
        queue.enqueue(48);
        queue.enqueue(59);
        queue.enqueue(59);
        queue.enqueue(59);
        queue.enqueue(59);
        queue.enqueue(59);
        queue.enqueue(59);
        queue.enqueue(59);
        queue.enqueue(59);

        assertEquals(11, queue.size());
        queue.enqueue(37);
        assertEquals(23, ((ArrayQueue) queue).getBackingArray().length);

    }

    @Test(timeout = TIMEOUT)
    public void testArrayQueueEnqueueDoublingCircular() {
        queue = new ArrayQueue<>();
        assertEquals(0, queue.size());

        queue.enqueue(34);
        queue.enqueue(29);
        queue.enqueue(48);
        queue.enqueue(59);
        queue.enqueue(59);
        queue.enqueue(59);
        queue.enqueue(59);
        queue.enqueue(59);
        queue.enqueue(59);
        queue.enqueue(59);
        queue.enqueue(59);

        assertEquals(11, queue.size());

        Object[] backingArray = ((ArrayQueue<Integer>) queue).getBackingArray();

        Object[] expected = new Object[11];
        expected[0] = 34;
        expected[1] = 29;
        expected[2] = 48;
        expected[3] = 59;
        expected[4] = 59;
        expected[5] = 59;
        expected[6] = 59;
        expected[7] = 59;
        expected[8] = 59;
        expected[9] = 59;
        expected[10] = 59;

        assertArrayEquals(expected, backingArray);

        assertEquals((Integer) 34, queue.dequeue());
        queue.enqueue(0);

        expected[0] = 0;

        assertArrayEquals(expected, backingArray);
        queue.enqueue(69);
        assertEquals(23, ((ArrayQueue) queue).getBackingArray().length);
        expected = new Object[23];

        expected[0] = 29;
        expected[1] = 48;
        expected[2] = 59;
        expected[3] = 59;
        expected[4] = 59;
        expected[5] = 59;
        expected[6] = 59;
        expected[7] = 59;
        expected[8] = 59;
        expected[9] = 59;
        expected[10] = 0;
        expected[11] = 69;

        backingArray = ((ArrayQueue<Integer>) queue).getBackingArray();

        assertArrayEquals(expected, backingArray);


    }
}

