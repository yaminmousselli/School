import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

public class Thing {

    public void run() throws IOException, ParseException {
        /*If you throw in the method body, then you must have throws in the
        method header. It is very rare that you have a method throw an exception*/
        //throw new IOException();

        throw new ParseException("Error in command list", 2); //W
    }

    public void input() throws FileNotFoundException {

    }
}
