public class Person {
    String name;
    int age;
    double height;
    static final int MAX_AGE = 70;

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getHeight() {
        return height;
    }


    void speak() {
        System.out.println("My name is " + name + " and I am " + age + " years old.");
    }

    void walk() {
        for (int i = 0; i <= 3; ++i) {
            if (i >= 1) {
                System.out.println("You have taken more than one step");
                return;
            }
            else {
                System.out.println("You have taken your first step");
            }
        }
    }

    int calculatedYearsToRetirement() {
        int yearsLeft = MAX_AGE - age;
        //System.out.println("You have " + yearsLeft + " until retirement");
        return yearsLeft; //'return' WILL ALWAYS BREAK OUT OF YOUR METHOD
    }
}
