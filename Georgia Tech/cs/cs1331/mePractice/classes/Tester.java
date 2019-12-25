public class Tester {

    public static void main(String[] args) {
        Person person1 = new Person();
        person1.name = "Yamin";
        System.out.print("Person 1 Name is: " + person1.getName() + '\n' + "Person 1 Age is: " + person1.getAge());
        person1.age = 22;

        //System.out.println(age1);
        //person1.speak();
        //person1.walk();
        //int years1 = person1.calculatedYearsToRetirement();
        //System.out.println("You have " + years1 + " until retirement");

        //System.out.print("Person 1 name is: " + person1.name);
        //System.out.println("Person 1 age is: " + person1.age);

        Person person2 = new Person();
        person2.name = "Sally"; //ENCAPSULATION PREVENTS THIS FROM HAPPENING WHICH IS ACCESSING THE DATA, WHICH SHOULD BE PRIVATE, HERE.
        System.out.println("Person 2 Name is: " + person2.getName());
        person2.age = 25;
        System.out.println("Person 2 Age is: " + person2.getAge());
        //System.out.println("Person 2 name is: " + person2.name);
        //System.out.println("Person 2 height is: " + person2.height);
        //person2.speak();
        //person2.walk();
        //int years2 = person2.calculatedYearsToRetirement();
        //System.out.println("You have " + years2 + " until retirement");

    }
}
