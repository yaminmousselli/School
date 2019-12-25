/*
****GOLDEN Rule: If you only want to add or remove items from the end or near the end
of the list, then use an ArrayList. If you want to add or remove items from the
beggining or middle of the list, then use a LinkedList.

ArrayLists manage arrays internally. The elements in the arrayList are stored
right next to each other in memory and are easy and fast to traverse. When you
exceed the size of the arraylist, it will create another array of twice the size
and copy the items over.

Linked Lists are internnally different than arrayLists. They consist of elements
where each element has a pointer to the next and/or previous element. The last
element points to null. To traverse a linked list is slow because you need to
get all the pointers. The elements are not next to each other in memory.
*/

import java.util.List;
import java.util.LinkedList;
import java.util.ArrayList;
import java.io.PrintStream;

class Driver {

    public static void main(String[] args) {
        List<Integer> arrayList = new ArrayList<Integer>();//Initial size is 10
        List<Integer> linkedList = new LinkedList<Integer>();

        doTimings("ArrayList", arrayList);
        doTimings("LinkedList", linkedList);
    }

    private static void doTimings(String type, List<Integer> list) {
        //initially filling the array
        for (int i = 0; i < 1E5; i++) {
            list.add(i);
        }

        long start = System.currentTimeMillis();
        //adding items at the end of the list
        /*for (int i = 0; i < 1E5; i++) {
            //list.add(i); //ArrayList wins
            list.add(list.size() - 100, i); //adding 100 elements from the end of the list
        }*/

        //adding items at the beginning of the list
        for (int i = 0; i < 1E5; i++) {
            list.add(0,i); //the first index is where you want to add the element--LinkedList Wins.
            //list.add(list.size(), i);//Adds items near the end--ArrayList wins.

        }
        long end = System.currentTimeMillis();
        System.out.println("Time taken: " + (end - start) + " milliseconds for " + type); //type will display set type
    }
}
