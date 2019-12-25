public class Tester {

    public static void main(String[] args) {
        Machine mach1 = new Machine(); //Allocates new memory to object
        Car car1 = new Car();

        car1.start();
        car1.stop();
        car1.wipeWindShield();
        car1.showInfo();

        mach1.start();
        mach1.stop();

    }
}
