class Tester {

    public static void main(String[] args) {
        try (Temp temp = new Temp()) {

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
