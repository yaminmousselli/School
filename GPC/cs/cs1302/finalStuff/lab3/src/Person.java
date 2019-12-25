
public class Person implements Comparable<Object> {
    //The Comparable interface automatically populates an empty
    //CompareTo method behind the scenes.

    private String username;
    private String password;
    private Long studentID;

    public Person(String user, String pass, Long iD) {
        this.username = user;
        this.password = pass;
        this.studentID = iD;
    }
    public int compareTo(Object tempStorage) {
        //Within the CompareTo method, you must pass in an object. This is
        // overriding the empty CompareTo Method.

        Person login = (Person) tempStorage;
        //You need to cast an object.
        // tempStorage is going to store the username, password, and studentID.
        if (login.getUsername().equals(username) && login.getPassword()
            .equals(password) && login.getStudentID() == studentID) {
            return 0; //0 denotes equality, aka true in this scenario
        } else {
            return -1;
            //A negative number denotes less than, aka false in this scenario
        }
    }
    public String getPassword() {
        return this.password;
    }
    public String getUsername() {
        return this.username;
    }
    public Long getStudentID() {
        return this.studentID;
    }
}
