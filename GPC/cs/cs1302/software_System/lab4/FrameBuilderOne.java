import javax.swing.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.util.*;
import java.io.*;
import javax.swing.BoxLayout;
import javax.swing.border.EtchedBorder;
import javax.swing.JPasswordField;
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
    private JPasswordField passwordTextField;
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

        passwordTextField = new JPasswordField(10);
        passWordPanel.add(passwordTextField);

        newPanel.add(passWordPanel);
        passwordTextField.setEchoChar('*');

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

        ActionListener loginListener = new ButtonsListen();
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
        /**
         * This method implements the binary search within the personAry which
         * contains the user's attributes.
         * @param info - this is the information of the object Person
         * which contains the username, password, and studentID.
         * @return returns true if info within Person is found. This returns it
         * to the other method which displays the appropriate message to the
         * user.
        */
        public boolean validate(Person info) {
            int low = 0;
            int high = passAry.size() - 1;
            int mid;
            while (low <= high) {
                mid = (low + high) / 2;
                Person middlePerson = passAry.get(mid);
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
  
    public boolean validatePassword(String passwordSaver) {
        int caseCount = 0;
        int numCount = 0;
        if (passwordSaver.length() < 10) {
            JOptionPane.showMessageDialog(null,
                    "Password must have ten characters");
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
            JOptionPane.showMessageDialog(null,
                    "Password must have at least one uppercase character");
            return false;
        } else if (numCount == 0) {
            JOptionPane.showMessageDialog(null,
                    "Password must have at least one uppercase character");
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
                password = new String(passArray);
      
                //validate student id
      
                Person txtFieldInfo = new Person(username, password, number);
      
                Verify verifyLogin = new Verify();
    
                if (!validatePassword(password)) {
                    //also tells the user what went wrong
                    return; //stop, user already know what they did.
                }
      
                if (verifyLogin.validate(txtFieldInfo)) {
                    JOptionPane.showMessageDialog(null,
                             "Login Successful");
                    //JOptionPane is in the swing package. You must include
                    // null,
                    // before the message you want to display.
                } else {
                    JOptionPane.showMessageDialog(null,
                            "Invalid Login " + "Information");
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