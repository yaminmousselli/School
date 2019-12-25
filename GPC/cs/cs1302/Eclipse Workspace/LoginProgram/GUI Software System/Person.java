/*
  Name: Yamin Mousselli
  Teacher Name: Mr. Johnson
  Class: CSCI 1302, Section 120
  Date: 03.20.2016
  Purpose: 1) Use of a "counter" to allow the user 3 failed attempts.
           2) If the user fails three times to input the correct information, then the system will lockout for 15 seconds.
             2.1) You should show a countdown window by seconds only, not the entire system time.
             2.2) Include Javadoc with your one zip file, again, in a folder labeled "doc" with all the HTML files. 
 */
public class Person implements Comparable <Object>//The Comparable interface automatiically populates an empty CompareTo method behind the scenes. 
{
  private String username;
  private String password;
  private Long StudentID;
  
  public Person(String User, String Pass, Long ID)
  {
    this.username = User;
    this.password = Pass;
    this.StudentID = ID;
  }
  public int compareTo(Object otherPerson)//Within the CompareTo method, you must pass in an object. This is overriding the empty CompareTo Method.
  {
    Person other = (Person) otherPerson;//You need to cast an object. tempStorage is going to store the username, password, and studentID. 
    
    if(other.StudentID > this.StudentID)
    {
      return 1;
    }
    else if(other.StudentID < this.StudentID)
    {
      return -1;
    }
    else
    {
      return 0;
    }
  }
 
  public String getPassword ()
  {
    return this.password;
  }
  
  public String getUsername()
  {
    return this.username;
  }
  
  public Long getStudentID()
  {
    return this.StudentID;
  }
}
