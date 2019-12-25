import java.util.ArrayList;
import java.util.List;

class Tester {

    public static void main(String[] args) {
        ArrayList<Integer> numbers = new ArrayList<Integer>();

        numbers.add(10);
        numbers.add(100);
        numbers.add(40);

        //Retrieving
        System.out.println(numbers.get(0));

        System.out.println("Iteration 1");
        for (int i = 0; i < numbers.size(); ++i) {
            System.out.println(numbers.get(i));
        }
        System.out.println("\nIteration 2");

        for (Integer value : numbers) {
            System.out.println(value);
        }

        /*You need to be careful when removing elements form an arrayList because
        internally the arrayList stores an array, and when you exceed the size
        of the array, it'll create a bigger array and copy the items to that and
        then continue adding to that.  */
        numbers.remove(0);
        numbers.remove(numbers.size() - 1); //Removes the last element

        List<String> values = new ArrayList<String>();



    }
}
