/*
Since our person objects do not have a natural order to them, we must define one.
We do so by implementing Comparable on the thing you want to compare your class to
so the placeholder will be <Person>.

The compareTo() takes in one object and you are comparing it with the class's
object that you refer to by this. You want your paramter for the comapreTo to be
the same as your type paramter.

*/
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;
import java.util.SortedSet;
import java.util.Collection;
import java.util.Collections;

class Person implements Comparable<Person> {
    private String name;

    public Person(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return name;
    }
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof Person)) {
            return false;
        }
        Person otherPerson = (Person) other;
        if (name == null) {
            if (otherPerson.name != null) {
                return false;
            } else if (!(name.equals(otherPerson.name))) {
                return false;
            }
        }
        return true;
    }
    /*We want to sort these Person objects in lexiographic order of their names,
    and the compareTo already defines the natural order for strings. */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 17;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }
    @Override
    public int compareTo(Person other) {
        return name.length() - other.name.length();

        //return this.name.compareTo(other.name);
    }
}
class Tester {

    public static void main(String[] args) {
        List<Person> list = new ArrayList<Person>();
        SortedSet<Person> set = new TreeSet<Person>(); //You can use SortedSet for things that are sorted in natural order
        //You can't add elements to a TreeSet unless they have a natural order that is defined.

        addElements(list);
        addElements(set);

        Collections.sort(list);

        showElements(list);
        System.out.println();
        showElements(set);
    }
    //Use Collection for the paramter because it is the supertype interface for both list and set.
    private static void addElements(Collection<Person> col) {
        col.add(new Person("Joe"));
        col.add(new Person("Sue"));
        col.add(new Person("Yamin"));
        col.add(new Person("Mike"));
        col.add(new Person("Amanda"));
    }
    private static void showElements(Collection<Person> col) {
        for (Person element : col) {
            System.out.println(element);
        }
    }
}
