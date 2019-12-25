public class Parent {
    protected String name; //Available for sublcasses and package
    protected int age;
    protected String food;

    public Parent(String name, int age, String food) {
        this.name = name;
        this.age = age;
        this.food = food;
        System.out.println("Super duper!");
    }
    public String getName() {
        return name;
    }

    public String getFood() {
        return food;
    }
    public int getAge() {
        return age;
    }
    @Override
    public String toString() {
        //return "My name is " + name + " and I am " + getAge() + " years old" + "\n" + getFood();
        return "hi";
    }
    public int print(int x) {
        int y = x * 2;
        System.out.println(y);
        return y;
    }

}
