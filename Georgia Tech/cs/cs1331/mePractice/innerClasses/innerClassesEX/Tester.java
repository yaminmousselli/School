class Tester {

    public static void main(String[] args) {
        //robot.start will execute everything in the start method, including
        //inner classes FIRST, BEFORE, invoking battery.charge().
        Robot robot = new Robot(7);
        robot.start();

        Robot.Battery battery = new Robot.Battery();
        battery.charge();

        //Robot.Brain brain = robot.new Brain();
        //brain.think();
    }
}
