/*
 Name: Yamin Mousselli
 Teacher Name: Mr. Johnson
 Class: CSCI 1302; Section 120
 Date: 01.25.2016
 Purpose: Design a GUI that accepts a username and a password.
 Only restriction is NO COMMA allowed in password.
 */
import javax.swing.*; //The asterik(*) tells Java to import everything that
// is being used in the package.
import java.awt.event.ActionEvent; //awt = "Abstract Window Toolkit"
import java.awt.event.ActionListener;
import java.util.*;
import java.io.*; //Always use IOException when working with files.
// FileNotFoundException is a specific IO Exception. Exceptions are objects.
import javax.swing.JPasswordField;
import java.awt.GridLayout;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
 
public class FrameBuilderOne extends JFrame {
    private JButton loginBtn;
    private JButton exitBtn;
    private JPanel newPanel;
    private JTextField usernameTextField;
    private JPasswordField passwordTextField; //instance variable/global
    private JLabel usernameLabel;
    private JLabel passwordLabel;

    public FrameBuilderOne() {
        createPanel(); //Method Call
    }

    private void createPanel() {
        newPanel = new JPanel(); //The reserve word, new,
        // denotes that a constructor method is being called
        newPanel.setBorder(new EtchedBorder());
        newPanel.setBorder(new TitledBorder(new EtchedBorder()));
        newPanel.setLayout(new GridLayout(3, 1));
        JPanel userNamePanel = new JPanel();
        usernameLabel = new JLabel("Username:");
        userNamePanel.add(usernameLabel);

        usernameTextField = new JTextField(10);
        userNamePanel.add(usernameTextField);

        newPanel.add(userNamePanel);

        JPanel passWordPanel = new JPanel();
        passwordLabel = new JLabel("Password:");
        passWordPanel.add(passwordLabel);

        passwordTextField = new JPasswordField(10);
        passWordPanel.add(passwordTextField);

        newPanel.add(passWordPanel);
        passwordTextField.setEchoChar('*');

        JPanel buttonPanel = new JPanel();
        loginBtn = new JButton("Login");
        buttonPanel.add(loginBtn);

        exitBtn = new JButton("Exit");
        buttonPanel.add(exitBtn);

        newPanel.add(buttonPanel);

        ActionListener loginListener = new ButtonListen();
        //Create One Method since since both login and exit buttons share the
        // same/similar task.

        loginBtn.addActionListener(loginListener);
        exitBtn.addActionListener(loginListener);

        add(newPanel);
    }

    public class Verify {
        private boolean validate(String u, String p) {
            //This is a warning to the compiler that we are throwing an
            // exception! Proper syntax is to have this AFTER the method header.
            Scanner userInput;
            try {
                userInput = new Scanner(new File("USERDATA.txt"));
                //The reason why this isn't System.in is because you aren't
                // taking input from the keyboard. You are scanning the file.
                // File is a data type.
                while (userInput.hasNextLine()) {
                    String finder = userInput.nextLine();
                    String[] loginInfo = finder.split(",");
                    //This is a delimeter
                    String usernameInfo = loginInfo[0];
                    String passwordInfo = loginInfo[1];

                    if (u.equals(usernameInfo) && p.equals(passwordInfo)) {
                        System.out.println("Username: " + usernameInfo + "Password: "
                                + passwordInfo);
                        return true;
                    }
                    userInput.close();
                }
            } catch (FileNotFoundException e) {
                JOptionPane.showMessageDialog(null, "Error"
                        + e.getMessage());
           }
           return false;
        }
    }
    public class ButtonListen implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == loginBtn) {
                String username = usernameTextField.getText();
                char[] passArray = passwordTextField.getPassword();
                String password = new String(passArray); //Study this, write it out
                Verify dv = new Verify();

                if (dv.validate(username, password)) {
                    JOptionPane.showMessageDialog(null,
                          "Login Successful");
                    //JOptionPane is in the swing package. You must include null,
                    // before the message you want
                    // to display.
                    } else {
                    JOptionPane.showMessageDialog(null,
                          "Invalid Username/Password");
                    }
            }
            if (e.getSource() == exitBtn) {
                JOptionPane.showMessageDialog(null,
                    "Program Shutting Down");
                System.exit(0);
            }
        }
    }
}