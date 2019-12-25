public class Person {
    private String name;
    private int id;

    public Person(String name, int id) { //WOULD THIS BE REDUNDANT (DOING THE SETTERS JOB) IF I HAD A USER-DEFINED CONSTRUCTOR. You only use the constructor once.
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setName(String newName) {
        name = newName;
    }

    public void setId(int newId) {
        id = newId;
    }

    @Override
    public String toString() {
        return "Person info: " + getName() + ", " + getId();
    }
}
