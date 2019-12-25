class Temp implements AutoCloseable {

    public void close() throws Exception {
        System.out.println("Closing");
    }

    
}
