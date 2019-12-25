
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
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import java.awt.event.*;
import java.awt.event.ActionListener;


public class TimerPanel extends JPanel {
	private Timer timerLock;
	private int currentTime = 15;
	private final int ENDING_TIME = 0;
	private FrameBuilderOne Frame1;
	private JPanel secondPanel;
	private JLabel timerMessage;
	private JLabel remainingTime;
	private FrameBuilderOne mainFrame;

	public TimerPanel(FrameBuilderOne frame) {
		mainFrame = frame;
		TimeListener listener = new TimeListener();
		timerLock = new Timer(1000, listener);
		createSecondPanel();
	}

	private void createSecondPanel() {
		setBorder(new EtchedBorder());
		setBorder(new TitledBorder(new EtchedBorder()));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		timerMessage = new JLabel("You Are Temporarily Locked Out For: ");
		add(timerMessage);

		remainingTime = new JLabel("" + currentTime + " seconds");
		add(remainingTime);
	}
	
	public void start(){
		timerLock.start();
	}
	
	public void reset(){
		timerLock.stop();
		currentTime = 15;
	}

	public class TimeListener implements ActionListener {
		public void actionPerformed(ActionEvent t) {
			currentTime--;
			remainingTime.setText("" + currentTime + " seconds");

			if (currentTime <= ENDING_TIME) {
				mainFrame.unlock();
				timerLock.stop();
			}
		}
	}
}