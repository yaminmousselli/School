import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

class Tester {

    public static void main(String[] args) throws FileNotFoundException {
        ;

        File textFile = new File("filePractice.txt"); //Always remember that if you do not specify the path of your file,
                                                    //java will read files from the root directory. It is important that the file is in the root directory
                                                    //of your project because that is the working directory of your java program when you run.

        Scanner input = new Scanner(textFile); //If it's not found, this is where the exception gets thrown?

        while(input.hasNextLine()) {
            String line = input.nextLine();

            System.out.println(line);
        }

        input.close();

        /*try {
            Scanner input = new Scanner(textFile); //You don't need throws at the method header when doing this?
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }*/
    }
}
