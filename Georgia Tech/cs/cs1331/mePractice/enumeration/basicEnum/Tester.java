class Tester {

    public static void main(String[] args) {
        Animal animal = Animal.CAT;//to refer to the enumerator type, use.
        //Animal animalTwo = Animal.DOG; //Why won't this statement

        Animal animal2 = Animal.DOG;

        Animal.yesOrNo(animal1, animal2);


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
    }
}
