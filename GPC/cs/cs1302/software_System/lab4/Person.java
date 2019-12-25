/*
  Name: Yamin Mousselli
  Teacher Name: Mr. Johnson
  Class: CSCI 1302, Section 120
  Purpose: 
  Date: 03.08.2016
 */
public class Person implements Comparable<Object> {
    private String username;
    private String password;
    private Long studentID;
  
    public Person(String user, String pass, Long id) {
        this.username = user;
        this.password = pass;
        this.studentID = id;
    }
    public int compareTo(Object otherPerson) {
        Person other = (Person) otherPerson;
        if (other.studentID > this.studentID) {
            return 1;
        } else if (other.studentID < this.studentID) {
            return -1;
        } else {
            return 0;
        }
    }
 
    public String getPassword() {
        return this.password;
    }
  
    public String getUsername() {
        return this.username;
    }
  
    public Long getstudentID() {
        return this.studentID;
    }
}
