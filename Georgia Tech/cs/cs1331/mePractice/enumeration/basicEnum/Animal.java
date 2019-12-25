/*
Enum is intended to replace. Say you have int animal; Say */
public enum Animal {
    CAT, DOG, LION; //These are actually object of the type Animal

    public boolean yesOrNo(Animal animal1, Animal animal2) {
        if (animal.ordinal() < animal2.ordinal()) {
            System.out.println(Animal.valueOf(DOG));
            return true;
        }
        else if (animal2.ordinal() > animal2.ordinal) {
            System.out.println(Animal.valueOf(CAT));
            return false;
        }
        else {return false;}
    }
}
