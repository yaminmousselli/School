public class Tester {

    public static void main(String[] args) {
        System.out.println("Before the Start, the count is: " + Machine.count);
        Machine machine1 = new Machine(); // Creating an instance is what calls a constructor = 'new Machine()'
                                          // This will call the no-arg constructor from the Machine Class.
                                          // since it doesn't have paramaters
                                          // Machine1 is an instance of the Machine class
        //System.out.println("Machine 1 Name is: " + machine1.getName() + '\n' + "Machine 1 Code is: " + machine1.getCode());

        //new Machine(); //in which scenarios will we need to invoke a counstructor a second time, in other words, overload a constructor?
                        //This is TECHNICALLY CREATING another instance of new machine.

        Machine machine2 = new Machine("Samantha");

        //System.out.println(machine2); // Invokes the toString() automatically and returns the name back to this print line. If no toString() exists...
                                      //Machine will call its superclass's (Object) toString() which will return the memory address of the instance.
        Machine machine3 = new Machine("Allie", 101793);

        System.out.println("At the End, the count is: " + Machine.count);

    }
}
