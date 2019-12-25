class Tester {
    public static void main(String[] args) {

        Person person = new Person("Bob");

        System.out.println("1. Person name is: " + person);

        show(person);

        System.out.println("4. Person name is: " + person);
        /* Will print out Sue because it is set. */

    }

    public static void show(Person person) {

        System.out.println("2. Person name is: " + person);

        person.setName("Sue"); //You might think that this will not change the
                            // name in the main method from Bob to Sue, but it
                            //actually does.

        person = new Person("Mike");
        person.setName("Riley");
        //Since we're creating a new instance on line 23, it will actually print
        //Riley below.
        System.out.println("3. Person name is: " + person);
    }
}
