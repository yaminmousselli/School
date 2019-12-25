/**<h1>
 Name: Yamin Mousselli
 Teacher Name: Mr. Johnson
 Class: CSCI 1302; Section 120
 Date: 03.20.2016 </h1>
 <p>
 Purpose: 1) Use of a "counter" to allow the user 3 failed attempts.
          2) If the user fails three times to input the correct information, then the system will lockout for 15 seconds.
             2.1) You should show a countdown window by seconds only, not the entire system time.
             2.2) Include Javadoc with your one zip file, again, in a folder labeled "doc" with all the HTML files. 
   @author Yamin Mousselli
   @since 20 March 2016
   @version 2.0
 */
import javax.swing.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
public class TimerLockout extends JFrame
{
  private Timer timerLock;
  private int currentTime = 15;
  private final int ENDING_TIME = 0;
  private FrameBuilderOne Frame1;
  private JPanel secondPanel;
  private JLabel timerMessage;
  private JLabel remainingTime;
  
  public TimerLockout()
  {
    TimeListener listener = new TimeListener();
    timerLock = new Timer(1000, listener);
    createSecondPanel();
    timerLock.start();
  }
  
  private void createSecondPanel()
  {
    secondPanel = new JPanel();
    
    timerMessage = new JLabel("You Are Temporarily Locked Out for: ");
    secondPanel.add(timerMessage);
    
    remainingTime = new JLabel("");
    secondPanel.add(remainingTime);
    
    add(secondPanel);
  }
  
  public class TimeListener implements ActionListener
  {
    public void actionPerformed(ActionEvent t)
    {
      currentTime--;
      remainingTime.setText("" + currentTime + " Seconds.");
      
      if(currentTime <= ENDING_TIME)
      {
        dispose();
        timerLock.stop();
      }
    }
  }
}