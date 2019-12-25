public class Car {
    private int carYear;
    private String carMake;
    private String carModel;
    //TO DO: I need to parse to String
    public Car(int year, String make, String model) {
        this.carYear = year;
        this.carMake = make;
        this.carModel = model;
    }
    public String toString() {
        return "I love my " + getYear() + " " + getMake() + " " + getModel();
    }
    public String getMake() {
        return carMake;
    }
    public String getModel() {
        return carModel;
    }
    public int getYear() {
        return carYear;
    }
}
