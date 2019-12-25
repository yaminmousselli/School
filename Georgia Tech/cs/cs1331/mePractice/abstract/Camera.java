public class Camera extends Machine implements Methods {

    @Override //abstract override
    public void start() {
        System.out.println("Car is starting");
    }

    @Override //abstract override
    public void run() {
        System.out.println("Camera has run out of film");
    }

    @Override //abstract override
    public void shutDown() {
        System.out.println("Camera is shutting down")
    }

    @Override //interface override
    public void showInfo() {
        System.out.print("I am a Camera");
    }
}
