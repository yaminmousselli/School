/*
Say you want to sort your animals in order of string length.
We'll have to use Comparator. Comparator has one method that is compare()
which takes in two objects and compares them using compareTo. You must
create a separate Comparator instance if you want to have more than one
compare()

Comparator and Comparable use Generics for their type to make sure at
compile time, that there is strong type checking. If you leave it out,
it will be Object.

To use Comparator, you have to supply an instance of it to your sort.

Compare will be supplied with two objects from your list. It has to
say which one is greater in each case. There are three scenarios:

1) s1 = s2, then it returns 0. This means that the objects are the same
even if they are stored in two differnt places in memory.

2) s1 < s2, then it returns -1. If the first object sorts before the
second object.

3) s1 > s2, then it returns 1. In other words,
the first object sorts later in the list then the second one.
*/
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Comparator;
//import java.util.Comparable;

class Tester {

    public static void main(String[] args) {

///////////////////////// Sorting Strings /////////////////////////////////////
        List<String> animals = new ArrayList<String>();

        animals.add("cat");
        animals.add("elephant");
        animals.add("tiger");
        animals.add("snake");
        animals.add("mongoose");
        animals.add("lion");

        System.out.println(animals); //Why does it print it in an Array vs in a list ----------> does it have to do with the toString()?

        class StringLengthComparator implements Comparator<String> {
            @Override
            public int compare(String s1, String s2) {
                return s1.length() - s2.length(); //Ascending order
                //return s2.length() - s1.length(); //Descending order
            }
        }
        /* We want to see if one string is alphabetically higher than the other
        string and to do that we will be using the compareTo method. */
        class AlphabeticalComparator implements Comparator<String> {
            @Override
            public int compare(String s1, String s2) {
                //String has its own implementation of compareTo().
                //The natural ordering of string is alphabetical order.
                return s1.compareTo(s2);
            }
        }

        Collections.sort(animals, new StringLengthComparator());

        for (String animal : animals) {
            System.out.println(animal);
        }
////////////////////// Sorting Numbers /////////////////////////////////////////
        List<Integer> numbers = new ArrayList<Integer>();

        numbers.add(3);
        numbers.add(36);
        numbers.add(73);
        numbers.add(40);
        numbers.add(1);

        //Collections.sort(numbers) is sorting things in natural
        //ordering. Natural ordering is the ordering that TreeSets and
        //TreeMaps sorts its keys in. If you want to sort a collection
        //different from its nartural orderdering, you must implement Comparator
        Collections.sort(numbers, new Comparator<Integer>() {
            public int compare(Integer num1, Integer num2) {
                return num1.compareTo(num2);
                //To do in reverse order, do return num2.compareTo(num1); OR
                //-num1.compareTo(num2);
            }
        });
        System.out.println();
        for (int number : numbers) {
            System.out.println(number);
        }
////////////////// Sorting Arbritary Objects ///////////////////////////////////
    class Person {
        private int id;
        private String name;
        //private Double height;

        public Person(int id, String name) {
            this.id = id;
            this.name = name;
        }
        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setId(int id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return id + ": " + name;
        }

    }

    List<Person> people = new ArrayList<Person>();
    people.add(new Person(1, "Yamin"));
    people.add(new Person(2, "David"));
    people.add(new Person(3, "Jessie"));
    people.add(new Person(5, "Angela"));
    people.add(new Person(5, "Sarah"));

    //Collections.sort(people); //Doesn't work
    /*This won't work because the person object doesn't have any natural ordering.
    It doesn't implement the comparable interface. You must use the Comparable
    interface*/
    System.out.println();
    Collections.sort(people, new Comparator<Person>() {
        @Override
        public int compare(Person p1, Person p2) {
            //First, we're gonna sort them in order of id
            if (((Integer)p1.getId()).compareTo((Integer)p2.getId()) == 0) {
                return p1.getName().compareTo(p2.getName());
            }
            return ((Integer)p1.getId()).compareTo((Integer)p2.getId());
            /*if (p1.getId() > p2.getId()) {
                return 1;
            }
            else if (p1.getId() < p2.getId()) {
                return -1;
            }
            return 0;*/
        }
    });

    for (Person person : people) {
        System.out.println(person);
    }
    System.out.println();
    Collections.sort(people, new Comparator<Person>() {
        @Override
        public int compare(Person p1, Person p2) {
            //First, we're gonna sort them in order of name
            return p1.getName().compareTo(p2.getName());
        }
    });

    for (Person person : people) {
        System.out.println(person);
    }
  }
}
