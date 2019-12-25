public class Car extends Machine {
    /* Inheritance is described as an "Is a" relationship. You can also say
    Car is a Machine. You can also say Car is derived from Machine. You can also
    say Car is a child class of Machine. Machine is a parent of Car. Car inherits
    from Machine
    YOU CAN ONLY EXTEND ONE CLASS
    */

    public void wipeWindShield() {
        System.out.println("Wiping windshield");
    }

    @Override //This will have the compiler check to see if you are correctly overriding a method
    public void start() { //We are overriding this method because we don't want it to print out the machine's string but instead its own, the car string
        System.out.println("Car has started");
    }

    @Override
    public void stop() {
        System.out.println("Car has stopped!");
    }

    public void showInfo() {
        System.out.println("Car is a : " + name); //We're allowed to use this because of protected.
    }

}
