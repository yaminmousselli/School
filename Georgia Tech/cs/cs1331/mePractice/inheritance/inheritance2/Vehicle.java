public class Vehicle {
    private String make;
    private int year;

    public Vehicle(String make, int year) {
        this.make = make;
        this.year = year;
    }
    public String getMake() {
        return make;
    }
    public int getYear() {
        return year;
    }
    public String toString() {
        return "Year: " + year + "\nMake: " + make;
    }
}
