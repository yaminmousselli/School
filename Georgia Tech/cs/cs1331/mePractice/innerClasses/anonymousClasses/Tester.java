class Tester {

    public static void main(String[] args) {
        Machine mach1 = new Machine() {

            @Override
            public void start() {
                System.out.println("Camera Starting");
            }
        };
        mach1.start();
        /* The above will work but it will not run the start() in machine. It
        will run the start() that you have overriden in this class. This works
        as a one time use.

        You can't instantiate interfaces but you can do it as an anonymous class.

        You usually declare anonymous classes while you pass it to a method
        at the same time.

        */

        Plant plant = new Plant() {
            @Override
            public void grow() {
                System.out.println("Plant growing");
            }
        };

        plant.grow();
    }
}
