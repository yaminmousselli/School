/*
 Name: Yamin Mousselli
 Teacher Name: Mr. Johnson
 Class: CSCI 1302; Section 120
 Date: 02.18.2016
 Purpose: Design a GUI that accepts a username, password, and studentID as long
 data type. No JPasswordField() usage. Encode password characters with *.
 Password minimum is 10 characters, no commas allowed, requires one
 capitalized letter, one number, and no blanks.
 */
import javax.swing.JFrame;
import java.io.IOException;
public class FrameViewerOne {

    public static void main(String[] args) throws IOException {
        JFrame f1 = new FrameBuilderOne();
        f1.setVisible(true);
        f1.setSize(200, 300);
        f1.setTitle("Lab 2 GUI");
        f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //This adds the x button on the top right. If you omit this step,
        // the program will keep running even after you close the frame.
    }
}
   
   
