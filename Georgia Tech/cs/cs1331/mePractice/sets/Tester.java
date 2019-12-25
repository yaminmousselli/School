/*
A set is a collection that does not have any order and does not contain any
duplicates. */

import java.util.Set;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.TreeSet;
class Tester {

    public static void main(String[] args) {

        /*HashSet does not retain order and the order is unpredictable when
        iterating through this data structure.This is your TO-GO SET because
        it has better performance than both TreeSet and LinkedHashSet */
        //Set<String> set1 = new HashSet<String>();

        /*LinkedHashSet remembers the order you added the items in and lets you
        iterate through the elements in the order that you added them. This works
        because it has a doubly linked list running through your items so it
        keeps them in the right order. */
        //Set<String> set1 = new LinkedHashSet<String>();

        /*Use a TreeSet if you want your set to be in natural order. For Strings,
        the natural order is alphabetically and for integers, the natural order
        is numerically*/
        Set<String> set1 = new TreeSet<String>();

        set1.add("dog");
        set1.add("cat");
        set1.add("mouse");
        set1.add("snake");
        set1.add("bear");

        //Adding duplicate items does nothing
        set1.add("mouse");

        System.out.println(set1);

////////////////////////// Iteration /////////////////////////////////////////

        for (String element : set1) {
            System.out.println(element);
        }

//////////////// Does this set contain a given item? /////////////////////////
        if (set1.isEmpty()) {
            System.out.println("Set is empty");
        }
        if (set1.contains("eagle")) {
            System.out.println("Contains eagle");
        }
        if (set1.contains("cat")) {
            System.out.println("Contains cat");
        }

        //Contains some elements from set 1 and some new elements.
        Set<String> set2 = new TreeSet<String>();
        set2.add("dog");
        set2.add("cat");
        set2.add("giraffe");
        set2.add("monkey");
        set2.add("ant");
///////////////////////// Intersection //////////////////////////////////////
    //^^^^^^ some code is located above^^^^^^^^^^^^^
    /* When you to find out which elements are common to both steps,
    the first step is to create a copy of one of your sets */

        Set<String> intersection = new HashSet<String>(set1);
        //System.out.println(intersection);

        intersection.retainAll(set2);
        System.out.println(intersection); //prints elements similar in sets 1 & 2

////////////////////// Difference between two sets /////////////////////////////
        /* We are trying to find the elements that are in set 1 that are NOT in
        set 2, we must first create a copy of the set and then use the removeAll().
        */

        Set<String> difference = new HashSet<String>(set1);

        difference.removeAll(set2);
        System.out.println(difference);
    }
}
