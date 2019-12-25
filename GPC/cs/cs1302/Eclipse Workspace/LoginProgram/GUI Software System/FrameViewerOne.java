/*
   Name: Yamin Mousselli
   Teacher Name: Mr. Johnson
   Class: CSCI 1302, Section 120
   Date: 03.20.2016
   Purpose: 1) Use of a "counter" to allow the user 3 failed attempts.
            2) If the user fails three times to input the correct information, then the system will lockout for 15 seconds.
             2.1) You should show a countdown window by seconds only, not the entire system time.
             2.2) Include Javadoc with your one zip file, again, in a folder labeled "doc" with all the HTML files. 
*/
import javax.swing.JFrame;//The Frame class is part of the javax.swing package.Swing is the nickname for the gui onterface library in java.
import java.io.*;
public class FrameViewerOne
{
   public static void main(String[] args)throws IOException{//This is a warning to the compiler that we are throwing an exception! Proper syntax is to have this AFTER the method header.
   {
      JFrame Frame1 = new FrameBuilderOne();
      Frame1.setVisible(true);
      Frame1.setSize(250,300);
      Frame1.setTitle("Lab Five GUI");
      Frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//This adds the x button on the top right. If you omit this step, the program will keep running even after you close the frame.
    }
  }
}
   
   
