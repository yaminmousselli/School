public class Car extends Machine {

    @Override //abstract override
    public void start() {
        System.out.println("Car is starting");
    }

    @Override //abstract override
    public void run() {
        System.out.println("Car has run out of film");
    }

    @Override //abstract override
    public void shutDown() {
        System.out.println("Car is shutting down");
    }
}
