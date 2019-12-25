class Machine {
    private String name;
    private int code;
    public static int count = 0;

    public Machine() { //THIS IS A DEFAULT CONSTRUCTOR...INITIALIZES THE PRIVATE INSTANCE VARIABLES!!!
                       //just like a method, a Constructor can also TAKE PARAMATERS!!

        //this("Amanda", 69); //This is called CONSTRUCTOR CHAINING. You are invoking the constructor with the appropiate paramaters.
                            // A CALL TO A CONSTRUCTOR MUST BE ON THE FIRST LINE OF THE METHOD
        ++count;

        name = "Yamin"; //All Machine objects will have name initially intantiated to "Yamin"
        System.out.println("First Constructor is Running!");
    }

    public Machine(String name) {
        //this(name, 0);

        System.out.println("Second Constructor is Running!");
        this.name = name;
    }

    public Machine(String name, int code) {
        System.out.println("Third Constructor is Running!");

        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public int getCode() {
        return code;
    }

    @Override //This is an annotation to the COMPILER to make sure that we have overriden correctly. This also tells the user we're overriding.
    public String toString() {
        return name; //This is how you return the name back to the method.
    }
}
