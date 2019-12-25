/**<h1>
 Name: Yamin Mousselli
 Teacher Name: Mr. Johnson
 Class: CSCI 1302; Section 120
 Date: 03.20.2016</h1>
 <p>
 Purpose: 1) Use of a "counter" to allow the user 3 failed attempts.
          2) If the user fails three times to input the correct information, then the system will lockout for 15 seconds.
             2.1) You should show a countdown window by seconds only, not the entire system time.
             2.2) Include Javadoc with your one zip file, again, in a folder labeled "doc" with all the HTML files.
   @author Yamin Mousselli
   @since 20 March 2016
   @version 2.0
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
/**
  This class, FrameBuilderOne, inherits the JFrame properties that are coded within the FrameViewerOne class.
  @return Nothing is return. 
  @param There aren't any parameters. These are simply global variables.
  @return Nothin is returned.
*/
public class FrameBuilderOne extends JFrame{
  private JButton loginBtn, exitBtn;
  private JPanel newPanel, greetingPanel, userNamePanel, passWordPanel, studentIdPanel, buttonPanel;
  private JTextField usernameTextField;
  private JPasswordField passwordTextField;
  private JTextField studentIdTextField;
  private JLabel greetingLabel, usernameLabel, passwordLabel, studentIdLabel;
  ArrayList<Person> personAry;
  private int attempts;
  private JFrame Frame2;
  /**
   FrameBuilderOne is a user-defined constructor that is calling certain methods. The Collections() method is called to sort the personAry arraylist.
   @return Nothing is returned
   @param There aren't any paramaters.
 */
  public FrameBuilderOne()//Constructor
  {
    createPanel();//Method Call
    personAry = checkInfo(new File("USERDATA.txt"));
    Collections.sort(personAry);
  } 
  /**
   This method's purpose is to create newPanel which is fed all other panels (userNamePanel, passWordPanel, studentIDPanel, and buttonPanel.
   In addition, all panels fed into newPanel contain their set of JComponents. The ActionListener method, LoginListener, is being created and ButtonsListen class 
   will eventually implement the ActionListener.
   <p>
   @param There aren't any paramaters
   @return This method doesn't return anything. 
  */
  private void createPanel()//Actual Method being called
  {
    newPanel = new JPanel();//The reserve word, new, denotes that a constructor method is being called
    newPanel.setBorder(new EtchedBorder());
    newPanel.setBorder(new TitledBorder(new EtchedBorder()));
    newPanel.setLayout(new BoxLayout(newPanel, BoxLayout.Y_AXIS));
    
    greetingPanel = new JPanel();
    greetingLabel = new JLabel("Welcome! Please enter your information below:");
    greetingPanel.add(greetingLabel);
    
    newPanel.add(greetingPanel);
    
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
    
    ActionListener LoginListener = new ButtonsListen();//Create One Method since since both login and exit buttons share the same/similar task. 
    loginBtn.addActionListener(LoginListener);
    exitBtn.addActionListener(LoginListener);
    
    add(newPanel);
  }
  /**
   This method will scan the userinput and then create an ArrayList of Person Objects. While the scanner is reading the file, it will store each
   input in a string array according to the postition of the data. It will then store this information into a Person object which is then passed into the 
   arraylist of person objects. Finally, the scanner is closed. I have thrown a FileNotFound Exception in the case that the file can not be located. If the file
   is not found, the JVM will catch the exception and display a window to the user that states "Error". This method will return the loginAry to the Person class
   to compare the user's input with the information in the file. 
   <p>
   @param File data which is passed from the user-defined constructor.
   @return loginAry which is passed to the Person class.
   @exception A FileNotFound exception is thrown in the case that the file can not be located in the directory.
   @see IO Exception, User-Defined Constructor.
   */
  public ArrayList<Person> checkInfo(File data)
  {
    Scanner userInput;
    ArrayList <Person> loginAry = new ArrayList<Person>();
    try
      {
        userInput = new Scanner(data);//The reason why this isn't System.in is because you aren't taking input from the keyboard. You are scanning the file.
        while (userInput.hasNextLine())
        {
          String finder = userInput.nextLine();
          String [] loginInfo = finder.split(",");//This is a delimeter
          String usernameInfo = loginInfo [0];
          String passwordInfo = loginInfo [1];
          long studentIdInfo = Long.parseLong(loginInfo[2]);
          
          Person moreInfo = new Person(usernameInfo, passwordInfo, studentIdInfo);
          loginAry.add(moreInfo);
        }
        userInput.close();
    }
    catch (FileNotFoundException f){
    JOptionPane.showMessageDialog(null, "Error" + f.getMessage());
    }
    return loginAry;
  }
  
  public class Verify
  {
    /** 
     * This method implements the binary search within the personAry which contains the user's attributes.
     * @param Person info - this is the information of the object Person which contains the username, password, and studentID.
     * @return - this method returns true if info within Person is found. This returns it to the other method which displays the appropriate message to the user. 
    */
    public boolean validate(Person info)
    {
      int low = 0;
      int high = personAry.size()-1;
      int mid;
      while(low <= high)
      {
        mid = (low + high) / 2;
        Person middlePerson = personAry.get(mid);
        int position = middlePerson.compareTo(info);
        if(position < 0)
        {
           low = mid + 1; 
        }
        else if(position > 0)
        {
          high = mid - 1;
        }
        else
        {
          return true;
        }
    }
    return false;
  }
 }
  /**
   There are certain requirements for the password. This method will check to see if the user has fulfilled the particular password requirements and will 
   return a boolean of either true or false, accordingly. The global variable, passwordSaver, is being passed into this method. The boolean value value
   determined within this method will be passed into the ActionListener which will display a message according to the boolean that is recieved.
   <p>
   @param Global Variable passwordSaver
   @return A boolean of true or false
 */
  public boolean validatePassword(String passwordSaver)//Check the password minimum, case sensitivity, and number in password 
  {
    int caseCount=0;
    int numCount=0;
    if(passwordSaver.length() < 10)
    {
      JOptionPane.showMessageDialog(null, "Password must have ten characters");
    }
    else if(passwordSaver.length() >= 10)
      for(int i = 0; i < passwordSaver.length(); i++)
      {
        char current = passwordSaver.charAt(i);
        if(Character.isUpperCase(current)) {caseCount++;}
        else if(Character.isDigit(current)){numCount++;}
      }
    if(caseCount == 0)
    {
      JOptionPane.showMessageDialog(null, "Password must have at least one uppercase character");
      return false;
    }
    else if (numCount == 0)
    {
      JOptionPane.showMessageDialog(null, "Password must have at least one uppercase character");
      return false;
    }
    else
    {
      return true;
    }
  }
/**
 This method is setting the properties of the the second JFrame, F2. These properties will be applied to the TimerLockout class.
 @return Nothing is returned
 @param The private global variable JFrame F2.
 */
public void createTimerClass(JFrame F2)
{
  this.setEnabled(false);
  //F2.pack;//causes the window to be sized to fit the preferred size and layouts of its subcomponents
  F2.setSize(250,100);
  F2.setLocationRelativeTo(null);//A trick, in that, when null, the API uses the screen device, and will (based on the current size of the window) center it in the screen.
  F2.setVisible(true);
  F2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
  this.setEnabled(true);
}
/**
 This Action Listener will perform a multitude of actions. First, it will recieve the user's input from each textfield. Next, it will encode all password
 characters in an asterik. Then, it will create a person object which stores the information entered in each textfield. Next, it will create an instance of 
 the inner class, Verify, and display a certain message depending on the boolean determined from the Verify class. All of the above occurs immedietly after the 
 user clicks the login button. When the user fails to enter the correct login information, a message will be displayed. If the user fails to enter on each third
 attempt, then the timerLockout class will be invoked and will lock the user our for a given period of time. After the lockout time is up, the user will be
 permitted to input their login information and if necessary, the previously described cycle will occur until the user enters the correct information.
 <p>
 @return Returns nothin
 @param An ActionEvent
 @see Global variables and the inner class
*/
public class ButtonsListen implements ActionListener{
  public void actionPerformed(ActionEvent e){
   
    if(e.getSource()==loginBtn)
    {
      String username = usernameTextField.getText();
      String id_str = studentIdTextField.getText();
      long number = Long.parseLong(id_str);
      String password;
      
      char[] passArray = passwordTextField.getPassword();
      password = new String(passArray);//Study this, write it out
      
      Person txtFieldInfo = new Person(username, password, number);
      
      Verify verifyLogin = new Verify();
    
      if (verifyLogin.validate(txtFieldInfo))
       {
         JOptionPane.showMessageDialog(null, "Login Successful");//JOptionPane is in the swing package. You must include null, before the message you want to display.
       } 
       
       else
       {
         JOptionPane.showMessageDialog(null,"Invalid Login Information");
         attempts++;
         //System.out.println("attempts= "+attempts);
        
        if(attempts == 3)
        {
          Frame2 = new TimerLockout();
          createTimerClass(Frame2);
          attempts = 0;
        }
      }
    }
    
    if (e.getSource()==exitBtn)
    {
      JOptionPane.showMessageDialog(null, "Program Shutting Down");
      System.exit(0);
    }
  }
 }
} 