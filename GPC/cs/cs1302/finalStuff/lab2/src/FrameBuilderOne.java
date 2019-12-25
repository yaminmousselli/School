
import javax.swing.*; //The asterik(*) tells Java to import everything that
// is being used in the package.
import java.awt.event.*;
//Has the ActionEvent and KeyEvent. awt: Abstract Window Toolkit
import java.awt.event.ActionListener;
import java.util.*;
import java.io.*; //Always use IOException when working with files.
// FileNotFoundException is a specific IO Exception. Exceptions are objects.
import javax.swing.BoxLayout;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class FrameBuilderOne extends JFrame {
    private JButton loginBtn;
    private JButton exitBtn;
    private JPanel newPanel;
    private JPanel userNamePanel;
    private JPanel passWordPanel;
    private JPanel studentIdPanel;
    private JPanel buttonPanel;
    private JTextField usernameTextField;
    private JTextField passwordTextField;
    private JTextField studentIdTextField;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JLabel studentIdLabel;
    protected String passwordSaver = "";
  
    public FrameBuilderOne() {
        createPanel();
    }
  
    private void createPanel() {
        newPanel = new JPanel();
        newPanel.setBorder(new EtchedBorder());
        newPanel.setBorder(new TitledBorder(new EtchedBorder()));
        newPanel.setLayout(new BoxLayout(newPanel, BoxLayout.Y_AXIS));

        userNamePanel = new JPanel();
        usernameLabel = new JLabel("Username:");
        userNamePanel.add(usernameLabel);

        usernameTextField = new JTextField(10);
        userNamePanel.add(usernameTextField);

        newPanel.add(userNamePanel);

        passWordPanel = new JPanel();
        passwordLabel = new JLabel("Password:");
        passWordPanel.add(passwordLabel);

        passwordTextField = new JTextField(10);
        passWordPanel.add(passwordTextField);
        passwordTextField.addKeyListener(new KeyListen());

        newPanel.add(passWordPanel);

        studentIdPanel = new JPanel();
        studentIdLabel = new JLabel("Student ID:");
        studentIdPanel.add(studentIdLabel);
        studentIdTextField = new JTextField(10);
        studentIdPanel.add(studentIdTextField);

        newPanel.add(studentIdPanel);

        buttonPanel = new JPanel();
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
        private boolean validate(String u, String p, long s) {
            Scanner userInput;
            try {
                userInput = new Scanner(new File("USERDATA.txt"));
                //The reason why this isn't System.in is because you aren't
                // taking input from the keyboard. You are scanning the file.
                while (userInput.hasNextLine()) {
                    String finder = userInput.nextLine();
                    String[] loginInfo = finder.split(",");
                    //This is a delimeter
                    String usernameInfo = loginInfo[0];
                    String passwordInfo = loginInfo[1];
                    Long studentIdInfo = Long.parseLong(loginInfo[2]);
                    //System.out.println(""+u+", "+p+", "+s);
                    // System.out.println(""+usernameInfo+",
                    // "+passwordInfo+", "+ studentIdInfo);
                    if (u.equals(usernameInfo) && p.equals(passwordInfo)
                            && studentIdInfo.equals(s)) {
                        System.out.println("Username: " + usernameInfo
                                + "Password: " + passwordInfo + "Student ID: "
                                + studentIdInfo);
                        return true;
                    }
                }
                userInput.close();
            } catch (FileNotFoundException e) {
                JOptionPane.showMessageDialog(null, "Error"
                        + e.getMessage());
            }
            return false;
        }
    }

    public class KeyListen implements KeyListener {
        public void keyPressed(KeyEvent k) {
          //System.out.println("pressed");
        }

        public void keyTyped(KeyEvent k) {
            char ch = (char) k.getKeyCode();
            switch (ch) {
            case KeyEvent.VK_SHIFT:
                //code
                k.consume();
                break; //indent break after code for the case*/

            case KeyEvent.VK_SPACE:
                JOptionPane.showMessageDialog(null,
                        "No Spaces Are Allowed");
                passwordTextField.setText("");
                break;

            case KeyEvent.VK_BACK_SPACE:
                k.consume();
                break;

            case KeyEvent.VK_COMMA:
                JOptionPane.showMessageDialog(null,
                        "No Commas Are Allowed");
                passwordTextField.setText("");
                break;

            default:
                passwordSaver += ch;
                break;
            }
            //System.out.println("typed");
        }
        public void keyReleased(KeyEvent k) {
            passwordTextField.setText(passwordTextField.getText()
                    .replaceAll(".", "*")); //blanks out password. regex.
            //System.out.println("released");
        }
    }
    public boolean validatePassword(String passwordCheck) {
        //Check the password minimum, case sensitivity, and number in password
        int caseCount = 0;
        int numCount = 0;
        if (passwordSaver.length() < 10) {
            JOptionPane.showMessageDialog(null,
                    "Password must have ten characters");
            passwordTextField.setText("");
        } else if (passwordSaver.length() >= 10)
            for (int i = 0; i < passwordSaver.length(); i++) {
                char current = passwordSaver.charAt(i);
                if (Character.isUpperCase(current)) {
                    caseCount++;
                } else if (Character.isDigit(current)) {
                    numCount++;
                }
            }
        else {
            return true;
        }
        return false;
    }

    public class ButtonListen implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == loginBtn) {
                String username = usernameTextField.getText();
                String password = passwordSaver;
                String id_str = studentIdTextField.getText();
                long number = Long.parseLong(id_str);

                Verify verifyLogin = new Verify();

                if (validatePassword(password)) {
                    //create this method, and have it
                    // check for conditions for upercase and number conditions
                    JOptionPane.showMessageDialog(null,
                           "Password does not contain an "
                                   + "Upercase Letter and/or Number");
                }
                if (verifyLogin.validate(username, password, number)) {
                    JOptionPane.showMessageDialog(null,
                            "Login Successful");
                    //JOptionPane is in the swing package. You must include
                    // null,
                    // before the message you want to display.
                } else {
                    JOptionPane.showMessageDialog(null,
                            "Invalid Login Information");
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