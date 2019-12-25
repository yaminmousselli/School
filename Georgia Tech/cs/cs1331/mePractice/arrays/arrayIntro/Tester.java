class Tester {
    public static void main(String[] args) {

        int value = 7; //Telling the compiler to create enough memory to hold an int (32 bits) and input some value into that memory.
                        //value holds the integer 7. Value is technically a value variable.

        int[] values; //Unlike primitives, values is a reference to the elements of the array

        values = new int[3]; //The new keyword denotes that you are allocating memory for 3 references which values will refer to.
                             //YOU MUST GIVE ALL ARRAYS A DIMENSION

        System.out.println(values); //This will denote the memory location of the object
        System.out.println(values[0]); //***Java will automatically give arrays a default of value of 0.
                                       //An array of Strings have a default value of null.

        int[] numArray = {1,2,3,4,5}; //Use curly braces if you have a finite set of elements

        for (int i = 0; i < numArray.length; ++i) {
            System.out.print(numArray[i]);
        }

    }
}
