/*
   Name: Yamin Mousselli
   Teacher Name: Mr. Johnson
   Class: CSCI 1302, Section 120
   Date: 01.25.2016
   Purpose: Add Comparable interface and overwrite with compareTo method.
            person implements Comparable
           1) Open file in the main and delete the data validate class since
           were using the compareTo method,
           2) build an array list<Person>,
           3) populate arrayList
 */
import javax.swing.JFrame;
import java.io.IOException;
public class FrameViewerOne {

    public static void main(String[] args) throws IOException {
        JFrame f1 = new FrameBuilderOne();
        f1.setVisible(true);
        f1.setSize(200, 300);
        f1.setTitle("Lab 3 GUI");
        f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
   
   
