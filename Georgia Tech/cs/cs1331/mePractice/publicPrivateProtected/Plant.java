public class Plant {
    private int age;
    protected String name;
    private double height;
    public String color;
    protected String type;

    public Plant() {
        this.name = "Rosie";
        this.age = 10;
        this.height = 2.0;
        this.color = "Green";
        this.type = "Plant";
    }

    public int getAge() {
        return age;
    }

    public double getHeight() {
        return height;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public void setAge(int newAge) {
        age = newAge;
    }

    public void setType(String newType) {
        type = newType;
    }

    public void setName(String newName) {
        name = newName;
    }

    public void setHeight(double newHeight) {
        height = newHeight;
    }

    public void setColor(String newColor) {
        color = newColor;
    }

    public void sunshine() {
        System.out.println("Plant is sunny");
    }

    public void oxygen() {
        System.out.println("Plant is breathing");
    }
}
