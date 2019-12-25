public class Person implements Info {
    private String name;

    public Person(String name) {
        this.name = name;
    }

    @Override
    public void showInfo() {
        System.out.println("Person name is: " + name); //If you do not give it any implementation, java is okay. as long as you overrided it.
    }

    public void greet()
     {
        System.out.println("Person says Hello");
    }

    @Override
    public void identify() {
        System.out.printf("I'm a Person");
    }
    public static void hi() {
        
    }
}
