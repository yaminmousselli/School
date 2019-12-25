/**
 * 
 * 
 * 
 * 
 * */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.border.*;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.Timer;
import java.util.ArrayList;
import java.util.*;
import java.io.*;

public class LoginGUI extends JFrame
{
  private JTextField txtUser;
  private JTextField txtPass;
  private JTextField txtID;
  private JLabel lblUser;
  private JLabel lblPass;
  private JLabel lblID;
  
  private JButton btnLogin;
  private JButton btnExit;
  private JPanel panel1;
  public Timer T;
  
  private String passwordSaver="";
  private ArrayList<Person> Users = new ArrayList<Person>();
  public LoginGUI()
  { 
    T = new Timer(1, null);
    //T.start();
    setLayout(new BorderLayout(10,10));
    panel1 = new JPanel();
    panel1.setPreferredSize(new Dimension(250, 250));
    
    txtUser = new JTextField();
    txtUser.setPreferredSize(new Dimension(150,20));
    txtPass = new JTextField();
    txtPass.setPreferredSize(new Dimension(150,20));
    txtID = new JTextField();
    txtID.setPreferredSize(new Dimension(150,20));
    
    KeyListener PassListen = new PassWordListener();
    txtPass.addKeyListener(PassListen);
    
    lblUser = new JLabel("Username: ");
    lblPass = new JLabel("Password: ");
    lblID = new JLabel("StudentID: ");
    btnLogin = new JButton("Login");
    btnExit = new JButton("Exit");
    
    panel1.add(lblUser);
    panel1.add(txtUser);
    panel1.add(lblPass);
    panel1.add(txtPass);
    panel1.add(lblID);
    panel1.add(txtID);
    panel1.add(btnLogin);
    panel1.add(btnExit);
    
    ActionListener LoginAction = new LoginListener();
    btnLogin.addActionListener(LoginAction); 
    
    ActionListener ExitAction = new ExitListener();
    btnExit.addActionListener(ExitAction);
    add(panel1, BorderLayout.CENTER);
    LoadUsers();
  }
  
  private void LoadUsers()
  {
    try 
    { 
      Scanner FileScan = new Scanner(new File("USERDATA.txt"));
      String S="";
      while (FileScan.hasNextLine())
      {
        S=FileScan.nextLine();
        String[] Sa= S.split(",");
        Users.add(new Person(Sa[0],Sa[1],Long.parseLong(Sa[2])));
      }
      FileScan.close();
    }
    catch(IOException e)
    { 
      JOptionPane.showMessageDialog(null, e.getMessage());
    }
  }
  
  class ExitListener implements ActionListener
  {
    public void actionPerformed(ActionEvent event)
    {
      System.exit(0);
    }
  }
  
  class LoginListener implements ActionListener
  { 
    public void actionPerformed(ActionEvent event)
    {
      Person P = new Person(txtUser.getText(),
                             passwordSaver,
                             Long.parseLong(txtID.getText()));
     
      boolean found=false;
      int iCode=0;
      for(Person U : Users)
      {
        iCode=U.compareTo(P);
        if (iCode==0)
        {
          found=true;
          break;
        }
        else
        {
          found=false;
        }
        
      }
      
      if (found)
      {
        JOptionPane.showMessageDialog(null,
                                      "Your user account has been validated!");
        txtUser.setText("");
        txtPass.setText("");
        txtID.setText("");
      }
      else
      {
        switch(iCode)
        {
          case -9:
             JOptionPane.showMessageDialog(null,
                                      "Your Username is incorrect. Please try again");
            break;
          case -10:
            JOptionPane.showMessageDialog(null,
                                      "Your Password is incorrect. Please try again");
            break;
          case -11:
            JOptionPane.showMessageDialog(null,
                                      "Your StudentID is incorrect. Please try again");
            break;
          default:
            JOptionPane.showMessageDialog(null,
                                      "Your 'somthing' is incorrect. Please try again");
            break;
        }
      }
    }
  }
  
  public class PassWordListener implements KeyListener
  {
    public void keyTyped(KeyEvent k)
    {
      char ch = (char) k.getKeyCode();
      switch(ch)
      {
        case KeyEvent.VK_SHIFT:
          return;
          //do nothing when shift key is pressed...
          
          
        case KeyEvent.VK_SPACE:
          JOptionPane.showMessageDialog(null, "No Spaces Are Allowed");
          txtPass.setText("");
          break;
          
        case KeyEvent.VK_COMMA:
          JOptionPane.showMessageDialog(null, "No Commas Are Allowed");
          txtPass.setText("");
          break;
          
        default:          
          passwordSaver += k.getKeyChar();
          if(txtPass.getText().length() == 0)
          {
            txtPass.setText("*");
          }
          else
          {
            txtPass.setText(txtPass.getText().substring(0,txtPass.getText().length()-1)+"*");
          }
          System.out.println(passwordSaver);
          break;
      }
    }
    
    public void keyPressed(KeyEvent k)
    {
      
    }
    public void keyReleased(KeyEvent k)
    {
      txtPass.setText(txtPass.getText().substring(0,txtPass.getText().length()-1)+"*"); 
    }
  }
}






