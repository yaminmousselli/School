/*
An anonymous class is a type of an inner class.

A second type of inner class is static class. You usually used static inner
classes to grouping classes together. where you want a normal class that isn't
associated with the fields of the enclosing class but you want to group it

NON-STATIC inner classes are used when you want to group some functionality
and you need the class to have access to the fields of the enclosing outer
class.

You can also declare inner classes within methods. If you do, you can not
add any access modifier to the class and create instances of it outside the method.
It's scope is in the method.

Brain will have access to the fields in Robot, even if it's private.
You can create seperate classes from Brain outside of Robot but that's never
done.


The most common way to use inner classes is you'll have a method that
creates an instance of your inner class and return it (You can see it with Iterable code)

*/

class Robot {

    private int id;

    public Robot(int id) {
        this.id = id;
    }

    static class Battery {
        public void charge() {
            System.out.println("Battery charging");
            //REMEMBER YOU CAN'T USE NON-STATIC FIELDS in a static method
        }
    }
    private class Brain {
        public void think() {
            id = 9;
            System.out.println("Robot: " + id + " is thinking.");
        }
    }

    public void start() {
        System.out.println("Robot Starting: " + id);

        Brain brain = new Brain();
        brain.think();

        String name = "Robert"; //No compiling error?

        class Temp {
            public void doSomething() {
                System.out.println("ID is: " + id);
                System.out.println("My name is: " + name);
            }
        };
        Temp temp = new Temp();
        temp.doSomething();
    }
}
