public class Thing {
    public String name; //This is an instance variable because every object has its own instance of the variable.
    public static String description; //Static variables, AKA class variables belong to the class and their is only one copy. Each object has the SAME COPY
                            //It is wise to create final variable static because you are saving memory, instead of creating a final instance for each object.
    public static int count;

    public Thing() {
        count = 0;
        ++count;
        System.out.println(count);
    }

    public void showName() { //INSTANCE METHODS CAN ACCESS STATIC DATA AND STATIC METHODS
        System.out.println(description + ": " + name);
        printSomething();
    }

    public static void printSomething() { //Static methods do the same thing as class variables because you can call them from just about anywhere using the...
                                          // CLASS AS A REFRENCE!
                                          //static methods can access static data BUT CAN NOT ACCESS INSTANCE VARIABLES OR INSTANCES

        //System.out.println(name); //NON-STATIC variable cannot be accessed from a static context ^

        System.out.println(description);
    }
}
/* WHEN TO USE STATIC METHODS: The use of static methods completely depend on what you're doing. For example, it makes sense to make the Math class static
                               because you should be able to invoke the methods in the math class whenever you want to perform an opeartion. You can use static
                               methods that takes in arguments and outputs some data but never deals with the instance data of the class. For example, it makes
                               sense for PI to be a static final constant.

  WHEN TO USE INSTANCE METHODS: only use these when you interact with the instance variables of the class.

  */
