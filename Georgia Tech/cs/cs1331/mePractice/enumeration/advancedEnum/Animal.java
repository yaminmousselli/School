/*
Enum is intended to replace certain things that you would use static final variables for.
Enum inherits from java.lang.Enum. You can give the Enum type a constructor. You can't declare your constructor public.
You can't create instances of enum but you can pass arguments as paramters for your constructor*/
public enum Animal {
    CAT("Fergus"), DOG("Fido"), LION("Jerry"); //Must end with a semi-colon. You can give them arguments but you must give your enum type instance data
                                               // fOR PROGRAMMING PURPOSES, YOUR ENUM VALUES SHOULD BE STRINGS
                                               //CAT, DOG, AND LION ARE OBJECTS...THEY ARE JUST CALLED ENUM CONSTANTS WHEN USING ENUM.

    private String name;

    private Animal (String name) { //Declare the constructor private or leave off the modifier. You do that because you could supply paramters
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return "This animal is called " + name;
    }
}
