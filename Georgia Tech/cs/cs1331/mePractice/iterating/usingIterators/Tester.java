/*
You don't have to import iterable. It is in the the java.lang package. However,
the interface iterator is in the util package and you need to import it.

If you want to remove items in the middle of the list or anywhere, you must
use an iterator. An enhanced for loop uses an iterator behind the scenes. If you
want to add items to your list, then use a list iterator.

*/
import java.util.LinkedList;
import java.util.Iterator;
class Tester {

    public static void main(String[] args) {
        LinkedList<String> animals = new LinkedList<String>();
        animals.add("Fox");
        animals.add("Dog");
        animals.add("cat");
        animals.add("Rabbit");
        animals.add("Snake");

        ///Before Java 5, to iterate you would do this:
        //The advantage of this is that you can use it to remove items.
        Iterator<String> iterator = animals.iterator();
        while (iterator.hasNext()) {
            //The iterator is originally pointing before the first element, therefore,
            //you need to call next to point to the first element.
            String value = iterator.next();
            System.out.println(value);

            if (value.equals("cat")) {
                iterator.remove();
            }
        }

        System.out.println();
        //Modern Iteration, Java 5 and later
        for (String animal : animals) {
            System.out.println(animal);
        }
    }
}
