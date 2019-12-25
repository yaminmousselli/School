public class Child extends Parent {

    public Child(String name, int age, String food) {
        super(name, age, food);
    }
    public String tostring() { //WHy is this still running when I mispell toString?
        return "My name is " + super.getName() + " and I am " + super.getAge() + " years old" + "\n" + super.getFood();
    }
    public int print(int y, int x) {
        int z = x +y;
        System.out.println(z);
        return z;
    }

}
