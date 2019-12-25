/*
    Maps store pairs of values where one member of the pair is the value and the
    other is a key <K,V>. When you create a map, yuou have to specify what kinds of
    objects you want to store in it. If you have your own custom objects, then
    you have to implement you own hasCode() and Equals().

    HashMap does not maintain order and is not sorted.
*/
import java.util.HashMap;
import java.util.Map;

class Tester {

    public static void main(String[] args) {

        HashMap<Integer, String> map = new HashMap<Integer, String>();

        map.put(5, "Five"); //You can look up the value using the key.
        map.put(8, "Eight");
        map.put(6, "Six");
        map.put(4, "Four");
        map.put(2, "Two"); //Java will automatically autobox these integers.

        map.put(6, "Hello");

        String text = map.get(4);
        map.put(4, "Ten");
        /*Pass in the key to retrieve a value. If you try to get a value
        that doesn't exist in the map, then value returned will be null. If you
        try to add an object that has the same key as an existing object, The
        value of the object your inputting will override your original value.
        You can have duplicate values but you can not have a duplicate key because
        you will overwrite your key.
        */


        System.out.println(text);
        System.out.println(map.get(1)); //prints null because it doesn't exist.

        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            Integer key = entry.getKey();
            String value = entry.getValue();

            System.out.println(key + ": " + value);
        }
        System.out.println();

        for (Integer key : map.keySet()) {
            System.out.println(key + ": " + map.keySey());
        }

    }
}
