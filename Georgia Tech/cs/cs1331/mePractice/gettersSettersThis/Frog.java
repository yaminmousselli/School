class Frog {
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) { //USE SETTERS WHEN YOU WANT TO UPDATE OR CHANGE A PRIVATE INSTANCE VARIABLE IN ANOTHER CLASS.

        this.name = name; //this takes in the argument passed into the method and the parameter is assigned to the instance variable, name.
                        //YOU DON'T WANT TO HAVE the 'this' keyword all over the place because then it becomes superfluous!!
    }

    public void setAge(int age) {
        this.age = age; //You will get the default values if you have ambiguity because you're not setting the parameter to the instance variables which hold the values
                        // that you are passing through. because I'M GETTING THE DEFAULT VALUES FOR BOTH TYPES...
    }

    public void setInfo(String name, int age) { //This is proper. Instead of calling 100 different setters, you call one which calls other.
        setName(name);
        setAge(age); //IN THIS CASE, YOU DO NOT WANT TO USE THE 'this' keyword because it will end up superfluous
    }

}
