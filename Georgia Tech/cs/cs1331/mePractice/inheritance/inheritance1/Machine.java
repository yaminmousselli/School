public class Machine {
    protected String name = "I am of type Machine"; //You're allowing access to the classes within the package or the subclass

    public void start() {
        System.out.println("Machine has started");
    }

    public void stop() {
        System.out.println("Machine has stopped!");
    }
}
