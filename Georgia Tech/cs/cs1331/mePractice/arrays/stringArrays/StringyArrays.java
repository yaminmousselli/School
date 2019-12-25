public class StringyArrays {

    public static void main(String[] args) {
        String[] words = new String[3];//YOU MUST GIVE AN ARRAY A DIMENSION. THE COMPILER WILL ALLOCATE MEMORY FOR 3 REFERENCES to strings WHICH words
                                    //will reference to.

        words[0] = "Hello"; //allcoating the memory for the Strings in each position of the array.
        words[1] = "to";
        words[2] = "you";

        String[] fruits = {"apple", "banana", "pear", "kewi"};

        for (String fruit : fruits) { //In an enhanced for loop, you must declare a variable/object in which you will store each element of the array
                                     // followed by the data structure that you are traversing
            System.out.println(fruit);
        }

        String text = null;//This is allocating enough memory for a reference. This reference stores a memory address
        System.out.println(text);

    }
}
