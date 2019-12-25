/*
There are two ways to handle an exception. You can throw it from the method
header. This will propagate the error somewhere else-------ask TA where
and if it is not dealt with then it will print out the stack trace.
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

class Tester {

    public static void main(String[] args) { //put throws FileNotFoundException in method header and ask TA q
        try {
        openFile();
    } catch (FileNotFoundException e) {
        System.out.println(e.toString()); //prints nothing
        System.out.println(e.getMessage()); //prints nothing
        System.out.println("File does not exist");
    }
        /*File file = new File("File.txt");
        FileReader fr = new FileReader(file);*/

        /*If you want to read the file, you have to declare a filereader object
        It's bad practice to set your variables with two letters but it's okay for
        certain instances. The exception gets thrown if the file does not exist
        when you are searching for it

        try {
        FileReader fr = new FileReader(file);

        /*IMPORTANT NOTE; IF EXCEPTION IS THROWN, THEN THE REST OF THE CODE IN
        THE TRY STATEMENT WILL NOT BE EXECUTED. The flow will be interrupted and
        jump straight to the catch clause and execute the code within the catch
        clause

        System.out.println("Exception did not occur, We are fine!");
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found: " + file.toString());
        }

        /* If we had code after the catch statement, then it WILL BE EXECUTED.
        NORMAL FLOW WILL RESUME AFTER THE EXCEPTION HAS BEEN HANDLED. */

        System.out.println("This is the code after catch");
    }

    public static void openFile() throws FileNotFoundException {
        File file = new File("File1.txt");
        FileReader fr = new FileReader(file); //Exception starts here, then since it's not handled,
                                              //it's propagated back up to the method that called it. If whatever method called it doesn't handle it,
                                             //then you will have a compiling error.

    }
}
