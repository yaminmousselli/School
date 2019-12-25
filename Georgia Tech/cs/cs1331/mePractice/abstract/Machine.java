/*
Abstract classes: Say both camera's and cars share some functionality, such as both of them having ID's. Say they have something that's
common in ALL OF THE MACHINES, then use an abstract class because then you force all your child classes to have whatever methods you want them to have
Make sure when you use an abstract class that whatever class is inheriting the abstract class, that it becomes the abstract class.  */
public abstract class Machine {
    private int id;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public abstract void start();
    
    public abstract void run();

    public abstract void shutDown();

    /*public void run() { //WHY would you want to do this., You can call these methods in run.
        start();
        run();
        shutDown();
    } */

}
