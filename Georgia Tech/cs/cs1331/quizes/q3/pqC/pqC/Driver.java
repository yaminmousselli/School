import java.util.ArrayList;
import java.util.Collections;

public class Driver {
    public static void main(String[] args) {
        Animal a1 = new Animal("dog", 4, 15);
        Animal a2 = new Animal("centipede", 100, 1);
        Animal a3 = new Animal("snake", 0, 8);
        Animal a4 = new Animal("bird", 2, 25);
        Animal a5 = new Animal("octopus", 8, 5);

        // Add the above animals to an ArrayList
        ArrayList<Animal> animals = new ArrayList<Animal>();
        animals.add(a1);
        animals.add(a2);
        animals.add(a3);
        animals.add(a4);
        animals.add(a5);


        // Sort the animals by their natural ordering below
        Collections.sort(animals);


        //Race any two animals below and print out the winner
        //System.out.println(a1.race(a2));
    }

    // You can use the below method to help you debug your code if you would like.
    public static void display(ArrayList<Animal> aList) {
        for (Animal a : aList) {
            System.out.print(a + " ");
        }
        System.out.println();
    }
}
