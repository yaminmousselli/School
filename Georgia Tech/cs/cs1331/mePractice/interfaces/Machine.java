public class Machine implements Info {
    /* A class can implement a multitude of interfaces, you would just have to seperate them with commas.
    However a class can only extend one parent class. */
    private int id;
    private int machNums;

    public Machine() {
        this.id = 7;
        this.machNums = 1;
    }

    //when you implement an interface, do you have to override all of the interfaces methods or can you be selective?
    //Is an interface technically an abstract class? When when you implement an interface but don't override its method(s), it says the class the
    //that's implementing the interface is not abstract and does not override abstract method showInfo() in the interface class.
    @Override
    public void showInfo() {
        System.out.println("Machine id is " + id);
    }

    public void start() {
        System.out.println("Machine started");
    }

    @Override
    public void identify() {
        System.out.println("I'm a Machine and there are only: " + machNums);
    }
    public static void hi() {

    }

}
