/*
A generic class is a class that can work with other objects and you specify what
type of object it can work with when you instantiate the class/create objects
from the class. If you learn one thing from generics, it should be how to use
the Arraylist class in its generic form.
*/
import java.util.ArrayList;
import java.util.HashMap;
class App {

    public static void main(String[] args) {
        //Does the following okay because of type erasure?
        ArrayList<String> strings = new ArrayList();
        strings.add("cat");
        strings.add("dog");
        strings.add("alligator");

        String animal = strings.get(1);//returns the string at the specified index in the list
        System.out.println(animal);

        for (String name : strings) {
            System.out.println(name);
        }

        ArrayList<Integer> nums = new ArrayList<Integer>();
        nums.add(1);
        nums.add(2);
        nums.add(3);

        for (int i = 0; i < nums.size(); i++) {
            System.out.println(nums[i]);
        }

        /*System.out.println(nums.get(1));

        for (Integer numbers : nums) {
            System.out.println(numbers);
        }*/

        //You can have paramterized classes that have more than 1 type paramater. This will be discussed in the collections video.

        //Unlike HashTable, HashMap permits nulls and is unsynchronized. It does not guarantee that order will remain constant over time.
        HashMap<Integer, String> map = new HashMap<Integer, String>();
    }
}
