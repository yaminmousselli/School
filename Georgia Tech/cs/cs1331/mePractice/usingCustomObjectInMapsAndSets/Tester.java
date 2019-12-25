import java.util.Map;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Set;

class Person {
    private int id;
    private String name;

    public Person(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "ID is: " + id + ", Name is: " + name;
    }
    /* hashCode() is supposed to produce an id that is the same for the same
    objects and a different one for two different objects.
     */
    @Override
    public int hashCode() {
        final int constant = 17; //my favorite number
        final int prime = 31;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }
    //equals method has a default implementation of allias testing
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
        if (id != otherPerson.id) {
            return false;
        }
        if (name == null) {
            if (otherPerson.name != null) {
                return false;
            } else if (!(name.equals(otherPerson.name))) {
                return false;
            }
        }
        return true;
    }
}

class Tester {

    public static void main(String[] args) {
        Person p1 = new Person(0, "Bob");
        Person p2 = new Person(1, "Sue");
        Person p3 = new Person(2, "Mike");
        Person p4 = new Person(1, "Sue");

        Map<Person, Integer> map = new LinkedHashMap<Person, Integer>();
        map.put(p1, 1);
        map.put(p2, 2);
        map.put(p3, 3);
        map.put(p4, 1); //Keys in a map are unique and will not get repeated.

        /* When creating your own custom objects for sets and maps, it can not
        tell whether two objects are duplciates or have the same keys. We have to
        give them equals methods and hashcode methods. You need to decide which
        field(s) is/are important when deciding how to compare objects. */
        for (Person key : map.keySet()) {
            System.out.println(key + ": " + map.get(key)); //the get retrieves the value
            //System.out.println(key + ": " + map.keySet());
        }

        Set<Person> set = new LinkedHashSet<Person>();
        set.add(p1);
        set.add(p2);
        set.add(p3);
        set.add(p4); //Set does not contain duplicates.

        System.out.println(set);

    }
}
