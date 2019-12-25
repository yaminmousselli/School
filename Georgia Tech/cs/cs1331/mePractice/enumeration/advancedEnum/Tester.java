public class Tester {

    public static void main(String[] args) {

        Animal animal = Animal.CAT;//to refer to the enumerator type, use.
                                    //Animal animalTwo = Animal.DOG;

        switch(animal) {
            case DOG: //DO NOT USE THE Animal. in the instance, just everything after the accessor.
                System.out.println("Animal is a Dog");
                break;
            case CAT:
                System.out.println("Animal is a Cat");
                break;
            case LION:
                System.out.println("Animal is a Lion");
                break;
            default:
                System.out.println("You don't have an animal");
                break;
        }
        System.out.println(Animal.DOG);

        System.out.println(Animal.DOG.getClass()); //We can see that the class of these objects are Animal. These are objects of type Animal.

        System.out.println(Animal.DOG instanceof Animal); //Outputs true because DOG is an instance of the Animal class.
                                                        //instanceof operator tells us whether the object is an instance of a particular class or NOT

        System.out.println(Animal.CAT instanceof Enum); //The objects of child classes are instances of the parent classes.

        System.out.println(Animal.LION.getName());

        System.out.println(Animal.DOG);

        System.out.println("Enum name as a String: " + Animal.DOG.name());//The name() is a special predefined, inherited method from java.lang.Enum.
                                                                          //This is how you recieve the name of the ENUM object.

        Animal animal2 = Animal.valueOf("CAT");//valueOf is a static method. You're providing a string and then you get the appropiate the enum.
                                               //toString() with Fergus is printed
        System.out.println(animal2);

    }
}
