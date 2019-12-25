public class Tester {

    public static void main(String[] args) {
        Person p1 = new Person("Yamin", 22);
        Person p2 = new Person("Yamin", 22);
        //System.out.println(p1 == p2); //This evaluates if the object references have the same memory address/or point to the
                                      //same object. This returns a boolean. This HAS NOTHING TO DO WITH WHETHER THE OBJECTS
                                    //ARE OR AREN'T THE SAME, LITERALLY SPEAKING.

        //System.out.println(p1.equals(p2)); //This uses the equals() from Person's superclass which is Object. This will still
                                            //return false because we have not overriden the equals method with instructions
                                            //on how to denote equality. We must override the equals method and give it our own
                                           //implementation.
        Integer num1 = 10;
        Integer num2 = 10;
        System.out.println(num1 == num2); //This returns true because it there aren't any decimals, it truncates at 10.

        Double number1 = 7.0; //This is false because it doesn't truncate at the whole number.
        Double number2 = 7.0;
        System.out.println(number1 == number2);

        String text1 = "Hello";
        String text2 = new String("Hello"); //String interring
        System.out.println(text1.equals(text2));
        System.out.println(text1 == text2);//WHAT THE HELL?
    }
}
