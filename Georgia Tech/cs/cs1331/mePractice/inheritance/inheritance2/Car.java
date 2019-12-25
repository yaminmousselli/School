public class Car extends Vehicle {
    private String model;

    public Car(String make, int year, String model) {
        super(make, year); //This super call invokes the superclass's constructor with matching paramaters.
                          //It must be the first in the constructor
        this.model = model;
    }
    public String getModel() {
        return model;
    }
    public String toString() {
        return super.toString() + "\nModel: " + model;
    }
}
