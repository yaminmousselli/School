import java.io.IOException;
import java.text.ParseException;

class Tester {

    public static void main(String[] args) throws IOException, ParseException {
        Thing thing = new Thing();
        try {
            thing.run();
        } catch (IOException a) {
            a.printStackTrace();
            System.out.println("The file does not exist");
        } catch (Exception b) {
            System.out.println("The file has finished parsing the file");
        }

        //thing.input();

        /*You are forced to handle one of those exceptions because
        this method could throw Could throw either of two
        exceptions but it may not throw any if runs smoothly

        If you're catching a GENERAL EXCEPTION (catch(Exception e)), then you
        can not have any other catches at the end because you have already caught
        it at the first level and it will result in a compiling error.

        Also, you have to have specific exceptions first, down to general
        exceptions. If you don't, you have an unreachable catch block.

        CHECKED EXCEPTIONS: PARESEXCEPTION, IOEXCEPTIONS,

        UNCHECKED EXCEPTIONS: NULLPOINTEREXCEPTION, ARITHMETICEXCEPTION,
            ARRAYINDEXOUTOFBOUNDSEXCEPTION. An ERROR is an unchecked exception

        Methods to use with exceptions: Although you can print a line explaining
        what the exception or error is in your code, you can use getMessage() to
        display where in your code the exception occurred. You can also use
        toString() to identify the exception to the user and dsiplay where the
        exception occurred. You can also use printStackTrace() by printing out
        stack trace of the exception. 

        */


    }
}
