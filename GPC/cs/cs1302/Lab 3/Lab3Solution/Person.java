/**
 *
 */
public class Person implements Comparable<Object> {
  private String username;
  private String password;
  private Long studentID;
  
  public Person(String u, String p, Long id) { 
    this.username = u;
    this.password = p;
    this.studentID = id;
  }
  public String getUser()
  {
    return this.username;
  }
  public String getPassword()
  {
    return this.password;
  }
  public Long getStudentID()
  {
    return this.studentID;
  }
  
  public int compareTo(Object o)
  {
    Person temp = (Person)o;
    
    if(getUser().toLowerCase().equals(temp.getUser().toLowerCase())
         && getPassword().equals(temp.getPassword())
         && getStudentID().equals(temp.getStudentID())
      ) 
    {
      return 0;
    }
    else if(!(getUser().toLowerCase().equals(temp.getUser().toLowerCase())))
    {
      return -9;
    }
    else if(!(getPassword().equals(temp.getPassword())))
    {
      return -10;
    }
    else
    {
      return -11;
    }
  }
}  

