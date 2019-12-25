
import javax.swing.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.util.*;
import java.io.*;
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
    String passwordSaver = "";
    ArrayList<Person> passAry;
  
    public FrameBuilderOne() {
        createPanel();
        passAry = checkInfo(new File("USERDATA.txt"));
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
        loginBtn.addActionListener(loginListener);
        exitBtn.addActionListener(loginListener);

        add(newPanel);
    }
  
    public ArrayList<Person> checkInfo(File data) {
        Scanner userInput;
        ArrayList<Person> loginAry = new ArrayList<Person>();
        try {
            userInput = new Scanner(data);
            while (userInput.hasNextLine()) {
                String finder = userInput.nextLine();
                String[] loginInfo = finder.split(",");
                String usernameInfo = loginInfo[0];
                String passwordInfo = loginInfo[1];
                long studentIdInfo = Long.parseLong(loginInfo[2]);
                Person moreInfo = new Person(usernameInfo, passwordInfo,
                        studentIdInfo);
                loginAry.add(moreInfo);
            }
            userInput.close();
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Error"
                    + e.getMessage());
        }
        return loginAry;
    }
  
    public class Verify {
        public boolean validate(Person info) {
            for (Person p : passAry) {
                if (p.compareTo(info) == 0) {
                    //When using the compareTo method, 0
                    // evaluates to the info in the passAry is the same as
                    // the info that the user entered
                    return true;
                } else {
                    return false;
                }
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
                k.consume();
                break;

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
                passwordSaver += k.getKeyChar();
                break;
            }
        }
        public void keyReleased(KeyEvent k) {
            passwordTextField.setText(passwordTextField.getText()
                    .replaceAll(".", "*"));
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
                String password = passwordTextField.getText();
                String id_str = studentIdTextField.getText();
                long number = Long.parseLong(id_str);
                Person txtFieldInfo = new Person(username, password, number);
                Verify verifyLogin = new Verify();
    
                if (validatePassword(password)) {
                    JOptionPane.showMessageDialog(null,
                            "Password does not contain an Upercase Letter "
                                    + "and/or Number");
                }
                if (verifyLogin.validate(txtFieldInfo)) {
                    JOptionPane.showMessageDialog(null,
                        "Login Successful");
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