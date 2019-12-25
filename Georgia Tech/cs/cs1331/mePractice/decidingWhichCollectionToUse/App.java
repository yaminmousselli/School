public class App {

    public static void main(String[] args) {
        /*
            Consider:
            1. What you need the collection to do.
            2. Are you using the fastest collection for your purposes
            (think about insertion, deletion, retrieval)
        */
////////////////////////////////Lists///////////////////////////////////////////
    //Store Lists of Objects
    //Duplicates are allowed
    //Objects remain in order
    //Elements are indexed via an Integer
    //Checking for a particular item in a list is slow
    //Looking an item up by index is fast
    //Iterating through lists is relatively fast
    //Note: you can sort lists if you want.

    //If you only add or remove items at the end of the list, use an ArrayList
    List<String> list1 = new ArrayList<String>();

    //Removing or adding items elsewhere in the list? Use a linkedlist
    List<String> list2 = new LinkedList<String>();

////////////////////////////////Sets///////////////////////////////////////////
    //only store unique values
    //Great for removing duplicates
    //Not indexed, unlike lists
    //very fast to check if a particular object exists
    //if you want to use your own objects, you must implement hashCode() and equals()

    //Order is unimportant
    //HashSet is unordered
    Set<String> set1 = new HashSet<String>();

    //Use a TreeSet if you want the sorting to be done in natural ordering (tree algorithm)
    //You must use implement comparable for your own objects and comparator
    //if you wanted sorted in a way other than the natural ordering.
    //(1,2,3....., a,b,c...........etc)
    Set<String> set2 = new TreeSet<String>();

    //Use a linked structure if you want the elements to remain in the order they were added
    Set<String> set3 = new LinkedHashSet<String>();

///////////////////////////////////Maps////////////////////////////////////////
    //key-value pairs
    //Like lookup tables, use key to lookup object/value.
    //Retrieving a value by key is fast
    //Iterating over maps values is very slow
    //Maps are not optimized for iteration.
    //If you want to use your own objects as keys (does not apply for values),
    //you must implement hashCode() and equals()

    //Keys not in any particular order, and order liable to change.
    Map<String, String> map1 = new HashMap<String, String>();

    //Keys sorted in natural order
    Map<String, String> map2 = new TreeMap<String, String>();

    //Keys remain in order that were added
    Map<String, String> map3 = new LinkedHashMap<String, String>();

    //There are also SortedSet and SortedMap interfaces.
    }
}
