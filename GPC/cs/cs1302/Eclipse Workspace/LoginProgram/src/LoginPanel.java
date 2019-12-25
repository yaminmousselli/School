import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import javax.swing.BoxLayout;
import javax.swing.JButton;
//import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class LoginPanel extends JPanel {

	private JButton loginBtn, exitBtn;
	private JPanel userNamePanel, passWordPanel, studentIdPanel, buttonPanel;
	private JTextField usernameTextField;
	private JPasswordField passwordTextField;
	private JTextField studentIdTextField;
	private JLabel usernameLabel, passwordLabel, studentIdLabel;
	private FrameBuilderOne mainFrame;

	ArrayList<Person> personAry;
	private int attempts;

	public LoginPanel(FrameBuilderOne frame) {
		mainFrame = frame;
		personAry = checkInfo(new File("USERDATA.txt"));
		Collections.sort(personAry);
		
		setBorder(new EtchedBorder());
		setBorder(new TitledBorder(new EtchedBorder()));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		userNamePanel = new JPanel();
		usernameLabel = new JLabel("Username:");
		userNamePanel.add(usernameLabel);

		usernameTextField = new JTextField(10);
		userNamePanel.add(usernameTextField);

		add(userNamePanel);

		passWordPanel = new JPanel();
		passwordLabel = new JLabel("Password:");
		passWordPanel.add(passwordLabel);

		passwordTextField = new JPasswordField(10);
		passWordPanel.add(passwordTextField);

		add(passWordPanel);
		passwordTextField.setEchoChar('*');

		studentIdPanel = new JPanel();
		studentIdLabel = new JLabel("Student ID:");
		studentIdPanel.add(studentIdLabel);
		studentIdTextField = new JTextField(10);
		studentIdPanel.add(studentIdTextField);

		add(studentIdPanel);

		buttonPanel = new JPanel();
		loginBtn = new JButton("Login");
		buttonPanel.add(loginBtn);

		exitBtn = new JButton("Exit");
		buttonPanel.add(exitBtn);

		add(buttonPanel);

		ActionListener LoginListener = new ButtonsListen();// Create One Method
															// since since both
															// login and exit
															// buttons share the
															// same/similar
															// task.
		loginBtn.addActionListener(LoginListener);
		exitBtn.addActionListener(LoginListener);

	}

	public class Verify {
		/**
		 * This method implements the binary search within the personAry which
		 * contains the user's attributes.
		 * 
		 * @param Person
		 *            info - this is the information of the object Person which
		 *            contains the username, password, and studentID.
		 * @return - this method returns true if info within Person is found.
		 *         This returns it to the other method which displays the
		 *         appropriate message to the user.
		 */
		public boolean validate(Person info) {
			int low = 0;
			int high = personAry.size() - 1;
			int mid;
			while (low <= high) {
				mid = (low + high) / 2;
				Person middlePerson = personAry.get(mid);
				int position = middlePerson.compareTo(info);
				if (position < 0) {
					low = mid + 1;
				} else if (position > 0) {
					high = mid - 1;
				} else {
					return true;
				}
			}
			return false;
		}
	}

	public ArrayList<Person> checkInfo(File data) {
		Scanner userInput;
		ArrayList<Person> loginAry = new ArrayList<Person>();
		try {
			userInput = new Scanner(data);// The reason why this isn't System.in
											// is because you aren't taking
											// input from the keyboard. You are
											// scanning the file.
			while (userInput.hasNextLine()) {
				String finder = userInput.nextLine();
				String[] loginInfo = finder.split(",");// This is a delimeter
				String usernameInfo = loginInfo[0];
				String passwordInfo = loginInfo[1];
				long studentIdInfo = Long.parseLong(loginInfo[2]);

				Person moreInfo = new Person(usernameInfo, passwordInfo, studentIdInfo);
				loginAry.add(moreInfo);
			}
			userInput.close();
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Error" + e.getMessage());
		}
		return loginAry;
	}

	public boolean validatePassword(String passwordSaver)// Check the password
															// minimum, case
															// sensitivity, and
															// number in
															// password
	{
		int caseCount = 0;
		int numCount = 0;
		if (passwordSaver.length() < 10) {
			JOptionPane.showMessageDialog(null, "Password must have ten characters");
		} else if (passwordSaver.length() >= 10)
			for (int i = 0; i < passwordSaver.length(); i++) {
				char current = passwordSaver.charAt(i);
				if (Character.isUpperCase(current)) {
					caseCount++;
				} else if (Character.isDigit(current)) {
					numCount++;
				}
			}
		if (caseCount == 0) {
			JOptionPane.showMessageDialog(null, "Password must have at least one uppercase character");
			return false;
		} else if (numCount == 0) {
			JOptionPane.showMessageDialog(null, "Password must have at least one uppercase character");
			return false;
		} else {
			return true;
		}
	}

	public class ButtonsListen implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == loginBtn) {
				String username = usernameTextField.getText();
				String id_str = studentIdTextField.getText();
				long number = Long.parseLong(id_str);
				String password;

				char[] passArray = passwordTextField.getPassword();
				password = new String(passArray);// Study this, write it out

				Person txtFieldInfo = new Person(username, password, number);

				Verify verifyLogin = new Verify();

				if (verifyLogin.validate(txtFieldInfo)) {
					JOptionPane.showMessageDialog(null, "Login Successful");// JOptionPane is in the swing package. 
																		    //You must include
																			//null, before the message you want to display.
				}

				else {
					JOptionPane.showMessageDialog(null, "Invalid Login Information");
					attempts++;
					// System.out.println("attempts= "+attempts);

					if (attempts == 3) {
						mainFrame.lockout();
						attempts = 0;
					}
				}
			}

			if (e.getSource() == exitBtn) {
				JOptionPane.showMessageDialog(null, "Program Shutting Down");
				System.exit(0);
			}
		}
	}
}
