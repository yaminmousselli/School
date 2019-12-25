/*
Hashmaps do not keep your keys in order.

LinkedHashMaps are the same as Hashmaps except that it has a doubly linked list
connecting the entries and that means that your key and values stay in the same
order that you add them to the map.

Say you have a class that does not override the toString(). When you create an
instance of the class and try to print it out, it will print the memory address
of the instance. The hashcode is the value that is after the @ sign. This code
is used to store things in the hashmap. That's why hashmap is called hashmap.
The hashcode of a map is the sum of each entry's hashcode.

A tree sorts the values that you add to it, usually in the natural order.
Natural order is a predefined way of ordering things.
*/

import java.util.HashMap;
import java.util.TreeMap;
import java.util.LinkedHashMap;
import java.util.Map;

class Tester {

    public static void main(String[] args) {
        /* Use the interface type when declaring your structures. */
        Map<Integer, String> hashMap = new HashMap<Integer, String>();
        Map<Integer, String> linkedHashMap = new LinkedHashMap<Integer, String>();
        Map<Integer, String> treeMap = new TreeMap<Integer, String>();

        testMap(hashMap);
        System.out.println();
        testMap(treeMap);
        System.out.println();
        testMap(linkedHashMap);

    }

    public static void testMap(Map<Integer,String> map) {
        map.put(9, "fox");
        map.put(4, "cat");
        map.put(8, "dog");
        map.put(1, "giraffe");
        map.put(0, "swan");
        map.put(15, "bear");
        map.put(6 , "snake");
        /*
        Map.keySet() returns a Collection called a set.
        */
        for (Integer key : map.keySet()) {
            String value = map.get(key);
            System.out.println(key + ": " + value);
        }
    }
}
