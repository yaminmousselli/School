/**
 Please modify the code below to print the average of each row
 and the average of each column.
 */
class MatrixLab
{
  public static void main(String[] args) {

     int [][] scores = {{ 20, 18, 22, 20, 16 },
                        { 18, 20, 18, 21, 20 },
                        { 16, 18, 16, 20, 24 },
                        { 25, 24, 22, 24, 25 }};
     outputArray(scores);
  }

  public static void outputArray(int[][] array) {
     int rowSize = array.length;
     int columnSize = array[1].length;

     System.out.println("Row size= " +rowSize);
     System.out.println("Column size = " + columnSize);
     for(int i = 0; i <= rowSize-1; i++) {
        System.out.print("[");
        for(int j = 0; j <= columnSize-1; j++) {
           System.out.print(" " + array[i][j]);
        }
        System.out.println(" ]");
     }
     System.out.println();
    }
}
