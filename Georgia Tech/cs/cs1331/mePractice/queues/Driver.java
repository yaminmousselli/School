/*
FIFO. First in, First Out structure. The front of the Queue is known as the head
of the Queue. Items will leave at the head of the Queue. We add items to the tail
of the Queue. You remove an item from the head of a Queue.

Queue's can have a size and a limit of how many items you can have. Queue's have
methods such as add, remove (removes and returns element), and element (returs the
head node). These methods will throw unchecked exceptions just incase you try
and do something that is forbidden. Throws the exception when you try to add
something that you can't or remove an element when there isn't one. For the later,
it will throw a NoSuchElementException

offer() will check to see if it can insert the specified element into
the queue if it is possible without violating capactiy restrictions. It will
return false if it can't add the items. It won't throw an exception. You can
also use poll() to retrieve and remove the head of the queue (or returns null
if the queue is empty). peek() retrieves but does not remove the head of the
queue.

Unlike a linked list which has an infinite size (A linked list is not bounded),
an ArrayBlockingQueue has a fixed size which you pass into the constructor.

*/
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
public class Driver {

    public static void main(String[] args) {

        Queue<Integer> q1 = new ArrayBlockingQueue<Integer>(3);

        q1.add(10);
        q1.add(20);
        q1.add(30);
        //q1.add(40); //--> This will cause an IllegalStateException()

        for (Integer value : q1) {
            System.out.println("Queue value: " + value);
        }

        //This will remove the head of the queue each time
        System.out.println("Removed from queue: " + q1.remove());
        System.out.println("Removed from queue: " + q1.remove());
        System.out.println("Removed from queue: " + q1.remove());
        //System.out.println("Removed from queue: " + q1.remove()); //-> This will cause a NoSuchElementException()

        System.out.println();

        Queue<Integer> q2 = new ArrayBlockingQueue<Integer>(2);

        q2.offer(100);
        q2.offer(200);
        //q2.offer(2); //-> Won't add the element and will not throw an exception.

        if (q2.offer(300) == false) {
            System.out.println("Offer failed to add third element to Queue");
        }

        for (Integer value : q2) {
            System.out.println("Queue value: " + value);
        }

        System.out.println("Queue 2 poll: " + q2.poll());
        System.out.println("Queue 2 poll: " + q2.poll());
        System.out.println("Queue 2 poll: " + q2.poll()); //-> this will return null
    }
}
