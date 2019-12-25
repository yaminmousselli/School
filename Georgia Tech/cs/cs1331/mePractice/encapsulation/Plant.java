/* The whole point of encapsulation is to encapsulate the inner workings of the class within the class. In other words,
you stop people outside the class getting the guts of that class and you just expose certain methods to use outside the class.
If you want people to access the private variables, we use getters and setters (methods that let you access private data)


RULE OF THUMB: You want to make everything you can private, if you can't make it private, then make it private. Keep everything
                hidden in a class. If it needs to be accessible in a child class, then make it protected. The stuff that you
                want your end user to be able to use should be public, but try not make any data public EXCEPT finals.


Things NOT TO DO: Reduce classes getting into another class's stuff and using it (THIS IS BAD).
                  In addition, avoid creating tangled nests. Have a few methods that are defined public,
                  clearly documented, and don't change very often.


ADVANTEGES: 1) HIDE AWAY THE IMPLEMENTATION DETAILS WITHIN YOUR CLASS AND PROVIDE AN API (public methods).
            2) You prevent classes being tightly coupled. Two classes are tightly coupled if they are calling each stuff all the time (tangled nests)

  */
class Plant {
    private String name;
    public static final int ID = 7; //This is not an instance variable. It's just a class variable because it is static.

    public String getData() { //Supp
        String data = "some stuff " + calculateGrowth();
        return data;
    }

    /*Suppose calculateGrowth is only used in the Plant class internally.
    IN that case, you would want its scope to be private because you want to ENCAPSULATE IT,
    prevent people from using it outside the class. */

    public int calculateGrowth() { //This is a helper method for getData();
        return 9;
    }

    public String getName() { //These public methods (getters and setters) can be used outside the class.
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

}
