/*
   Name: Yamin Mousselli
   Teacher Name: Mr. Johnson
   Class: CSCI 1302, Section 120
   Date: 01.25.2016
   Purpose: Design a GUI that accepts a username and a password. Only
            restriction is NO COMMA allowed in password.
*/
import javax.swing.JFrame;
//The Frame class is part of the javax.swing
// package.Swing is the nickname for the gui interface library in java.
import java.io.IOException;
public class FrameViewerOne {
   public static void main(String[] args) throws IOException {
   {
      JFrame F1 = new FrameBuilderOne();
      F1.setVisible(true);
      F1.setSize(300,200);
      F1.setTitle("Lab One GUI");
      F1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      //This adds the x button on the top right. If you omit this step,
       // the program will keep running even after you close the frame.
   }
  }
}
   
   
