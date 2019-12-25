public class Tester {

    public static void main(String[] args) {
        Machine mach1 = new Machine();
        mach1.start();
        //mach1.showInfo();

        Person p1 = new Person("Bob");
        //p1.greet();
        //p1.showInfo();

        Info machInfo = new Machine(); //This is totally legal. You can do this because Machine does implement the Info Interface.
                                    // This is a variable of type info pointing to an object of type Machine. You can use this variable to
                                    //access any method of the interface class that's implemented in Machine.
        machInfo.showInfo();

        Info personInfo = p1;
        personInfo.showInfo(); //This is doing the same thing as above because p1 is an object reference of the Person instance.

        System.out.println();

        outputInfo(mach1); //You can pass these arguments through to outputInfo because Machine implements the Info interface.
                           // This will output all machine behaviors first for BOTH METHODS before Person. Then it will print the
                           //the behaviors for person.

        outputInfo(p1);// Would this be an overloaded method? 
    }

    private static void outputInfo(Info info) { //This is the same thing as lines 12-18. Just a different way by using a method.
        info.showInfo();
        info.identify();
    }

}
