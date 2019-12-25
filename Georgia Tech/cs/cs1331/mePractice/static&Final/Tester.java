public class Tester {
    public static void main(String[] args) {
        Thing obj1 = new Thing();
        Thing obj2 = new Thing();
        Thing obj3 = new Thing();
        Thing obj4 = new Thing();

        obj1.name = "Yamin";
        obj2.name = "David";
        System.out.println("Object 1 Name: " + obj1.name + '\n' + "Object 2 Name: " + obj2.name);

        Thing.description = "I am something"; // This is how you access the static variables! YOU ACCESS THE CLASS!!!
        //System.out.println(obj2.description);
        System.out.println(Thing.description); // Use this because it is more explicit to the user that you are referencing a static variable

        obj1.showName();
        obj2.showName();

        Thing.printSomething(); //Static Method that you are accessing from the Thing class.
    }
}
