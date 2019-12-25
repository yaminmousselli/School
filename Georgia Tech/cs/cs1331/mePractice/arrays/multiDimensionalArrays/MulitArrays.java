class MultiArrays {

    public static void main(String[] args) {

        //A multidimensional array is just an array of array(s). IT IS ROW,COLUMN MAJOR
        int[] values = {3, 5, 234};

        //Because we are dealing with a two-dimensional array, each element in th
        int[][] grid = {
                        {3, 5, 2343}, //In a multidimensional array, the first row starts with 0.
                        {2, 4}, //The first array does not have to have the same number of elements in each row. The is Row 1
                        {1, 2, 3, 4} //No comma or semi-colon on the last row. Also this is row 3.
        };

        //Say we want to print element, 4 (#4)
        //System.out.println(grid[1][1]); //REMEMBER POSITION, POSITION ALWAYS STARTS AT 0 FOR BOTH ROW AND ELEMENTS

        //Say we want to print out 234
        //System.out.println(grid[0][2]); // REMEMBER POSITION, POSITION ALWAYS STARTS AT 0 FOR BOTH ROW AND ELEMENTS

        //String[][] texts = new String[2][3]; //You have 2 rows and 3 columns. This is an array of an array of Strings.
                                            //Technically a 2-d array of references to strings
        //texts[0][1] = "hello there";
        //System.out.println(texts[0][1]);

        //STICK WITH A REGULAR FOR LOOP WHEN ITERATING THROUGH A 2-D ARRAY
        for (int i = 0; i < grid.length; ++i) { //This first for loop iterates through the rows
            for (int j = 0; j < grid[i].length; ++j) {
                System.out.print(grid[i][j] + "\t");
            }
            System.out.println();
        }
        String[][] words = new String[2][];
        System.out.println(words[0]);
        words[0] = new String[3];//You have to set up your subArrays one by one.
        words[0][1] = "hi there";
        System.out.println(words[0][1]);
    }
}
