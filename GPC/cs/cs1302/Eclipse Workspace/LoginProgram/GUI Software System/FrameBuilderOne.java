
/*
 Name: Yamin Mousselli
 Teacher Name: Mr. Johnson
 Class: CSCI 1302; Section 120
 Date: 03.20.2016
 Purpose: 1) Use of a "counter" to allow the user 3 failed attempts.
          2) If the user fails three times to input the correct information, then the system will lockout for 15 seconds.
             2.1) You should show a countdown window by seconds only, not the entire system time.
             2.2) Include Javadoc with your one zip file, again, in a folder labeled "doc" with all the HTML files. 
 */
import javax.swing.*;//The asterik(*) tells Java to import everything that is being used in the package.
import java.awt.event.*;//Has the ActionEvent and KeyEvent. awt: Abstract Window Toolkit
import java.awt.event.ActionListener;
import java.util.*;//This does not have an asterik because I am only importing a specific tool (Scanner) from the java package,util. Scanner is the namespace of the package
import java.io.*; //Always use IOException when working with files. FileNotFoundException is a specific IO Exception. Exceptions are objects.
import javax.swing.BoxLayout;
import javax.swing.JPasswordField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class FrameBuilderOne extends JFrame {
	private JPanel loginPanel;
	private TimerPanel timerPanel;
	private JFrame Frame2;

	public FrameBuilderOne()// Constructor
	{
		createPanel();// Method Call
	}

	private void createPanel()// Actual Method being called
	{
		loginPanel = new LoginPanel(this);
		timerPanel = new TimerPanel(this);
		add(loginPanel);
	}

	public void lockout() {
		remove(loginPanel);
		add(timerPanel);
		timerPanel.start();
		revalidate();
	    repaint();	
	}

	public void unlock() {
		remove(timerPanel);
		add(loginPanel);
		timerPanel.reset();
		revalidate();
	    repaint();	
		
	}

}